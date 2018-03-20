package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;

import com.ruanyun.web.model.mall.TPayInfo;
import com.ruanyun.web.model.sys.TUser;

@Repository("payInfoDao")
public class PayInfoDao extends BaseDaoImpl<TPayInfo>{
	
	public Page<TPayInfo> queryPage(Page<TPayInfo> page,TPayInfo payInfo,TUser user){
		StringBuffer hql = new StringBuffer("from TPayInfo where 1=1");
		if(EmptyUtils.isNotEmpty(payInfo)){
			
		} 
		
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
		
	}
	
	
}

