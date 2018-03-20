package com.ruanyun.web.dao.mall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.sys.TAttachInfo;

@Repository("attachInfoDao")
public class AttachInfoDao extends BaseDaoImpl<TAttachInfo>{
	
	/**
	 * 功能描述:根据关联获取所有附件列表
	 * @author wsp  2016-10-9 下午03:42:08
	 * @param attachType
	 * @param glNum
	 * @return
	 */
	public List<TAttachInfo> getList(String attachType,String glNum){
		StringBuffer hql = new StringBuffer(" from TAttachInfo where 1=1 ");
		hql.append(SQLUtils.popuHqlEq("attachType", attachType));
		hql.append(SQLUtils.popuHqlIn("glNum", SQLUtils.sqlForIn(glNum)));
		hql.append(" order by createTime desc");
		return hqlDao.getAll(hql.toString());
	}

	/**
	 * 功能描述：根据关联获取附件数量
	 * @param attachType
	 * @param glNum
	 * @return
	 */
	public Integer getCount(String attachType,String glNum){
		String sql = "SELECT COUNT(*) FROM t_attach_info where 1=1 and attach_type = '"+attachType+"' and gl_num = '"+glNum+"'";
		return sqlDao.getCount(sql);
	}
	
	/**
	 * 功能描述: 删除附件
	 *
	 * @author yangliu  2016年9月3日 下午7:17:12
	 * 
	 * @param attachIds 附件ID
	 * @param attachType 类型
	 * @param glNum 关联主键
	 * @return
	 */
	public int delAttachInfo(String attachIds,String attachType,String glNum){
		StringBuffer sql = new StringBuffer("delete from TAttachInfo where 1=1 ");
		sql.append(SQLUtils.popuHqlIn("attachId", attachIds));
		sql.append(SQLUtils.popuHqlEq("attachType", attachType));
		sql.append(SQLUtils.popuHqlEq("glNum", glNum));
		return hqlDao.execute(sql.toString());
	}
	
	/**
	 * 功能描述:根据用户编号获取用户信息 返回值为map key为glNum value 为 tAttachInfo对象
	 * @author wsp  2016-10-20 下午08:08:54
	 * @param glNums
	 * @return
	 */
	public Map<String,TAttachInfo> getAttachInfoByGlNums(String attachType,String glNums){
		final Map<String,TAttachInfo> map = new HashMap<String, TAttachInfo>();
		String sql ="select * from t_attach_info where attach_type ='"+attachType+"' gl_num in ("+SQLUtils.sqlForIn(glNums)+")";
		jdbcTemplate.query(sql, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String glNum=rs.getString("gl_num");
				TAttachInfo attachInfo=new TAttachInfo();
				attachInfo.setGlNum(glNum);
				attachInfo.setCreateTime(rs.getDate("create_time"));
				attachInfo.setAttachType(rs.getString("attach_type"));
				attachInfo.setFilePath(rs.getString("file_path"));
				attachInfo.setFileName(rs.getString("file_name"));
				map.put(glNum, attachInfo);
			}
		});
		return map;
	}
	
	public List<TAttachInfo> getList(String glNum){
		StringBuffer hql = new StringBuffer("select file_path from t_attach_info where 1=1 and gl_num='"+glNum+"' ");
		return sqlDao.getAll(TAttachInfo.class,hql.toString());
	}
	public List<TAttachInfo> getGoodsList(String glNum){
		StringBuffer hql = new StringBuffer("SELECT * from t_attach_info where gl_num in (SELECT comment_num from t_comment_info where goods_num='"+glNum+"') ");
		return sqlDao.getAll(TAttachInfo.class,hql.toString());
	}
	
	
}

