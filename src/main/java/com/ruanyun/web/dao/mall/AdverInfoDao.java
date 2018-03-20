package com.ruanyun.web.dao.mall;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.sys.TUser;

@Repository("adverInfoDao")
public class AdverInfoDao extends BaseDaoImpl<TAdverInfo>{
	
	public Page<TAdverInfo> queryPage(Page<TAdverInfo> page,TAdverInfo adverInfo){
		StringBuffer hql = new StringBuffer("from TAdverInfo where 1=1");
		if(EmptyUtils.isNotEmpty(adverInfo)){
			hql.append(SQLUtils.popuHqlEq("position", adverInfo.getPosition()));
			hql.append(SQLUtils.popuHqlEq("status", adverInfo.getStatus()));
//			hql.append(SQLUtils.popuHqlEq("position", adverInfo.getPosition()));
			hql.append(SQLUtils.popuHqlLike("title", adverInfo.getTitle()));
			hql.append(SQLUtils.popuHqlMinDate("createTime", adverInfo.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", adverInfo.getCreateTime()));
			hql.append(SQLUtils.popuHqlEq("adverType", adverInfo.getAdverType()));
		}
		hql.append(" order by sortNum ");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	/**
	 * 功能描述:获取排序最大值
	 * @author wsp  2016-9-7 下午07:01:19
	 * @return
	 */
	public Integer getMaxSortNum(Integer adverType) {
		String sql= "select ifnull(max(sort_num),0) from t_adver_info where adver_type = "+adverType;
		return sqlDao.getCount(sql);
	}

	/**
	 * 功能描述:获取排序最大值
	 * @author wsp  2016-9-7 下午07:01:19
	 * @return
	 */
	public Integer getMaxSortNum() {
		String sql= "select ifnull(max(sort_num),0) from t_adver_info";
		return sqlDao.getCount(sql);
	}
	
	/**
	 * 功能描述:获取首页广告信息
	 * @author cqm  2017-6-28 下午03:36:26
	 * @return
	 */
	public List<TAdverInfo> getAdverList(){
		StringBuffer hql = new StringBuffer(" select * from t_adver_info where 1=1 ");
		//hql.append(SQLUtils.popuHqlEq("position", 1));//
		hql.append(SQLUtils.popuHqlEq("adver_type", 1));//广告类型（1首页广告 2普通会员 3高级会员）
		return sqlDao.getAll(TAdverInfo.class, hql.toString());
	}
	
}

