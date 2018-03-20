package com.ruanyun.web.dao.daowei;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.daowei.TCardFee;

@Repository("cardFeeDao")
public class CardFeeDao extends BaseDaoImpl<TCardFee>{
	
	/**
	 * 功能描述:获取会员列表
	 * @author cqm  2017-8-18 下午05:06:02
	 * @return
	 */
	public List<TCardFee> getCardFees(Integer memberLevel){
		StringBuffer hql = new StringBuffer(" from TCardFee where 1=1 ");
		hql.append(SQLUtils.popuHqlEq("cardType", 1));
		hql.append(" and memberLevel>"+memberLevel+" ");
		return hqlDao.getAll(hql.toString());
	}
	
}
