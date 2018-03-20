package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;

import com.ruanyun.web.model.mall.TAccountRecharge;
import com.ruanyun.web.model.sys.TUser;

@Repository
public class AccountRechargeDao extends BaseDaoImpl<TAccountRecharge>{
	
	
	public Page<TAccountRecharge> queryPage(Page<TAccountRecharge> page,TAccountRecharge accountRecharge,TUser user) {
		StringBuffer hql = new StringBuffer(" from TAccountRecharge where 1=1");
		if(EmptyUtils.isNotEmpty(accountRecharge)){
			hql.append(SQLUtils.popuHqlLike("rechargeMealNum", accountRecharge.getRechargeMealNum()));
			hql.append(SQLUtils.popuHqlMinDate("createTime", accountRecharge.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", accountRecharge.getCreateTime()));
			
		}
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
	}

	
}

