package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;
import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.web.model.mall.TUserLogin;

@Repository("userLoginDao")
public class UserLoginDao extends BaseDaoImpl<TUserLogin>{
	
	
	/**
	 * 功能描述:查询是否绑定
	 * @author cqm  2016-12-1 上午11:51:03
	 * @param userNum
	 * @param thridNum
	 * @return
	 */
	public TUserLogin getUserLogin(String thirdNum,String userNum){
		String sqlString="select * from t_user_login where user_num='"+userNum+"' and third_num='"+thirdNum+"'";
		return sqlDao.get(sqlString);
	}

}
