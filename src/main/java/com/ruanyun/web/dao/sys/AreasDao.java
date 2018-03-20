/**
 * 手机端接口: files
 *@author feiyang
 *@date 2016-3-16
 */
package com.ruanyun.web.dao.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TAccountRecharge;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.SecurityUtils;


/**
 *@author feiyang
 *@date 2016-3-16
 */
@Repository
public class AreasDao extends BaseDaoImpl<TAreas>{

	
	/**
	 * 
	 * 功能描述:根据cityCode获取所有的区域
	 * @param cityCode
	 * @return
	 *@author feiyang
	 *@date 2016-3-16
	 */
	public List<TAreas> getAllAreas(String cityCode,TUser user  ){
		StringBuffer sql=new StringBuffer(" SELECT * from t_areas WHERE 1=1");
		sql.append(" and  city_code='"+cityCode+"'");
		
		return sqlDao.getAll(TAreas.class, sql.toString());
	}
	public List<TAreas> getAllAreas(String cityCode ){
		StringBuffer sql=new StringBuffer(" SELECT * from t_areas WHERE 1=1");
		if (EmptyUtils.isNotEmpty(cityCode)) {
			sql.append(" and  city_code='"+cityCode+"'");
		}
		return sqlDao.getAll(TAreas.class, sql.toString());
	}
	
	
	/**
	 * 功能描述:根据城市查询区域列表
	 * @author cqm  2016-11-8 上午10:40:57
	 * @param page
	 * @param areas
	 * @return
	 */
	public List<TAreas> getAreasList(TAreas areas){
		StringBuffer sql=new StringBuffer(" SELECT * from t_areas WHERE 1=1 ");
		if (EmptyUtils.isNotEmpty(areas.getCityCode())) {
			sql.append(" and  city_code='"+areas.getCityCode()+"' ");
		}
		return sqlDao.getAll(TAreas.class, sql.toString());
	}
	
	/**
	 * 后台查看所有：
	 * 
	 */
	public Page<TAreas> getPageAreas(Page<TAreas> page,TAreas areas){
		StringBuffer hql = new StringBuffer("from TAreas where 1=1");
		if(EmptyUtils.isNotEmpty(areas)){
			
			hql.append(SQLUtils.popuHqlEq("level", areas.getLevel()));
			hql.append(SQLUtils.popuHqlEq("isOpen", areas.getIsOpen()));
			hql.append(SQLUtils.popuHqlEq("area", areas.getArea()));
			hql.append(SQLUtils.popuHqlEq("cityName", areas.getCityName()));
			
		}
		hql.append(" order by id DESC");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	
	public int updateQuery(String filedValue,String filed,String queryFiledValue,String tableName,String queryFiled){
		String sqls= "update "+tableName+" set "+filed+" = "+filedValue+" where "+queryFiled+"='"+queryFiledValue+"'";
		sqlDao.update(sqls);
		return 1;
	}	
	
	/**
	 * 功能描述:根据id获取信息
	 * @author cqm  2016-12-29 上午11:52:10
	 * @param areaid
	 * @return
	 */
	public TAreas getAreas(String  areaid){
		String sql = "select * from t_areas where areaid='"+areaid+"'";
		return sqlDao.get(TAreas.class,sql);
	}
		
	
}
