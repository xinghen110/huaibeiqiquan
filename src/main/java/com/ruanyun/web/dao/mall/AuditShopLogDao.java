package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;

import com.ruanyun.web.model.mall.TAuditShopLog;

import com.ruanyun.web.model.sys.TUser;

@Repository("auditShopLogDao")
public class AuditShopLogDao extends BaseDaoImpl<TAuditShopLog> {
	
	public Page<TAuditShopLog> queryPage(Page<TAuditShopLog> page,TAuditShopLog auditShopLog,TUser user){
		StringBuffer hql = new StringBuffer("from TAuditShopLog where 1=1");
		if(EmptyUtils.isNotEmpty(auditShopLog)){
			
			hql.append(SQLUtils.popuHqlLike("shopName", auditShopLog.getShopName()));
			hql.append(SQLUtils.popuHqlMinDate("createTime", auditShopLog.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", auditShopLog.getCreateTime()));
			hql.append(SQLUtils.popuHqlCityEq("city", "area", auditShopLog.getCity()));
		}
		
		
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
	}

	/**
	 * 功能描述：根据shopNum获取该商家最新的审核记录
	 * @param shopNum
	 * @return
	 */
	public TAuditShopLog getNew(String shopNum) {
		String sql = "select * from t_audit_shop_log where create_time = (select max(create_time) from t_audit_shop_log where shop_num='"+shopNum+"')";
		return sqlDao.get(TAuditShopLog.class, sql);
	}
	
	

}
