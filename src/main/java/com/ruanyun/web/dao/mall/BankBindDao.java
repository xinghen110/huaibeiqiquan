package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TBankBind;
import com.ruanyun.web.model.sys.TUser;
@Repository("bankBindDao")
public class BankBindDao extends BaseDaoImpl<TBankBind> {
	
	/**
	 * 功能描述:查询银行卡绑定信息
	 * @author zhujingbo
	 * @param page
	 * @param bankBind
	 * @return
	 */
	public Page<TBankBind> queryPage(Page<TBankBind> page,TBankBind bankBind){
		StringBuffer hql = new StringBuffer("from TBankBind where 1=1");
		if(EmptyUtils.isNotEmpty(bankBind)){
			hql.append(SQLUtils.popuHqlLike("userName", bankBind.getUserName()));
			hql.append(SQLUtils.popuHqlEq("cardNo", bankBind.getCardNo()));
			hql.append(SQLUtils.popuHqlEq("commonNum", bankBind.getCommonNum()));
			hql.append(SQLUtils.popuHqlCityEq("city", "area", bankBind.getCity()));
		}
		
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	/**
	 * 功能描述:ajax判断登录名称是否重复
	 *
	 * @author L H T  2013-11-26 下午06:24:41
	 * 
	 * @param loginName 登录名称
	 * @return
	 */
	public TBankBind getAjaxshopNum(String shopNum){
		String sql = "from TBankBind where shopNum = ?";
		return hqlDao.get(sql.toString(), shopNum);
	}
	
}
