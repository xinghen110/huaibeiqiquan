package com.ruanyun.web.dao.mall;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;


import com.ruanyun.web.model.mall.TRechargeMeal;
import com.ruanyun.web.model.sys.TUser;


@Repository("rechargeMealDao")
public class RechargeMealDao extends BaseDaoImpl<TRechargeMeal> {
    
	/**
	 * 功能描述:查询
	 * @author zhujingbo 2016-10-29 
	 * @param rechargeMeal
	 * @param user
	 * @return
	 */
	public Page<TRechargeMeal> queryPage(Page<TRechargeMeal> page,TRechargeMeal trechargeMeal,TUser user){
		StringBuffer hql = new StringBuffer("from TRechargeMeal where 1=1");
		if(EmptyUtils.isNotEmpty(trechargeMeal)){
			
			hql.append(SQLUtils.popuHqlMinDate("createTime", trechargeMeal.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", trechargeMeal.getCreateTime()));
		}
		
		hql.append(" order by createTime ASC");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	/**
	 * 功能描述:获取套餐列表
	 * @author cqm  2017-8-16 上午11:00:47
	 * @param trechargeMeal
	 * @return
	 */
	public List<TRechargeMeal> getList(TRechargeMeal trechargeMeal){
		StringBuffer hql = new StringBuffer("from TRechargeMeal where 1=1");
		hql.append(" order by amount asc ");
		return hqlDao.getAll(hql.toString());
	}
	
}
