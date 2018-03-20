package com.ruanyun.web.service.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.mall.AuditShopLogDao;
import com.ruanyun.web.model.mall.TAuditShopLog;
import com.ruanyun.web.model.sys.TUser;
@Service("auditShopLogService")
public class AuditShopLogService extends BaseServiceImpl<TAuditShopLog> {

	@Autowired
	private AuditShopLogDao auditShopLogDao;
	
	/**
	 * 功能描述:查询
	 * @author zhujingbo  
	 * @param page
	 * @param 
	 * @param user
	 * @return
	 */
	public Page<TAuditShopLog> getList(Page<TAuditShopLog> page,TAuditShopLog auditShopLog,TUser user){
		return auditShopLogDao.queryPage(page,auditShopLog,user);
	}

	/**
	 * 功能描述：根据shopNum获取该商家最新的审核记录
	 * @param shopNum
	 * @return
	 */
	public TAuditShopLog getNew(String shopNum) {
		return auditShopLogDao.getNew(shopNum);
	}
	
}
