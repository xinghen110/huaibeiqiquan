package com.ruanyun.web.service.app;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
 
import com.ruanyun.web.model.mall.TAccountRecharge;
import com.ruanyun.web.model.mall.TRechargeMeal;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.RechargeMealService;
import com.ruanyun.web.service.mall.UserRecordService;
import com.ruanyun.web.service.sys.UserService;

/**
 * 
 *  #(c) IFlytek czy <br/>
 *
 *  版本说明: $id:$ <br/>
 *
 *  功能说明: 用户账户类
 * 
 *  <br/>创建说明: 2016-10-9 下午09:29:45 yanzy  创建文件<br/>
 * 
 *  修改历史:<br/>
 *
 */
@Service
public class AppAccountService {
	@Autowired
	private UserService userService;  
	@Autowired
	private AccountRechargeService accountRechargeService;  
	/**
	 * 功能描述:生成充值订单
	 *
	 * @author chenqian  2017-01-11 上午09:30:10
	 * 
	 * @param userNum
	 * @param TUserRecord 用户流水
	 * @return
	 */
	public AppCommonModel save(String userNum,TAccountRecharge accountRecharge){
		TUser user=userService.getUserByUserNum(userNum, true);
		accountRecharge.setUserNum(userNum);
		accountRecharge = accountRechargeService.saveAccountRecharge(accountRecharge, 1, user);
		return new AppCommonModel(1,"充值订单生成，请进行支付",accountRecharge);
	}

}
