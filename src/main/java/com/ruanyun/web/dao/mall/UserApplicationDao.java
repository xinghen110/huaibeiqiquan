package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TUserApplication;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.SecurityUtils;

@Repository("userApplicationDao")
public class UserApplicationDao extends BaseDaoImpl<TUserApplication>{
	
	public Page<TUserApplication> queryPage(Page<TUserApplication> page,TUserApplication userApplication,TUser user){
		StringBuffer hql = new StringBuffer("from TUserApplication where 1=1");
		if(EmptyUtils.isNotEmpty(userApplication)){
			hql.append(SQLUtils.popuHqlLike("userName", userApplication.getUserName()));
			hql.append(SQLUtils.popuHqlLike("accountNumber", userApplication.getAccountNumber()));
			hql.append(SQLUtils.popuHqlEq("userApplicationNum", userApplication.getUserApplicationNum()));
			hql.append(SQLUtils.popuHqlMinDate("createTime", userApplication.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", userApplication.getCreateTime()));
			hql.append(SQLUtils.popuHqlMinDate("handleTime", userApplication.getHandlestartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("handleTime", userApplication.getHandleTime()));
			hql.append(SQLUtils.popuHqlEq("status", userApplication.getStatus()));
		}
		if(SecurityUtils.isGranted(ConstantAuth.SHOP_AUTH, user)){   //店铺角色
			hql.append(SQLUtils.popuHqlEq("shopNum", user.getShopNum()));
		}
		
//		hql.append(SQLUtils.popuHqlEq("storeNum", user.getStoreNum()));
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	
}

