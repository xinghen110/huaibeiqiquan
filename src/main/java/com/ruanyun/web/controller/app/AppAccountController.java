package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TAccountRecharge;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.service.app.AppAccountService;
import com.ruanyun.web.service.app.AppPayInfoService;

@Controller
@RequestMapping("/app/{userNum}/account/")
public class AppAccountController extends BaseController {
	@Autowired
	private AppAccountService appAccountService;
	
	@Autowired
	private AppPayInfoService appPayService;
	
	/**
	 * 功能描述:执行充值
	 *
	 * @author chenqian  2016-10-9 下午10:01:00
	 * 
	 * @param response
	 * @param userNum
	 * @param accountRecharge
	 */
	@RequestMapping("add")
	public void save(HttpServletResponse response,@PathVariable String userNum,TAccountRecharge accountRecharge){
		AppCommonModel model=null;
		try {
			if(EmptyUtils.isEmpty(accountRecharge.getScore())){
				accountRecharge.setScore(0);
			}
			model=appAccountService.save(userNum, accountRecharge);
		} catch (Exception e) {
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:使用账户余额支付
	 *
	 * @author chenqian  2016-11-9 下午05:20:48
	 * 
	 * @param response
	 * @param userCenter 支付用户userNum，支付金额accountBalance 
	 * @param orderNum 支付订单编号，多个用,分割
	 * @param payMethod 支付方式 1账号支付 2支付宝 3微信 4、积分账户
	 * @param orderType 订单类型 1普通订单
	 */
	@RequestMapping("pay")
	public void pay(HttpServletResponse response,@PathVariable String userNum,TUserCenter userCenter,String orderNum,Integer payMethod,Integer orderType){
		AppCommonModel model=null;
		try {
			model=appPayService.accountPay(userCenter, orderNum,payMethod,orderType);
		} catch (Exception e) {
			e.printStackTrace();
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	 
	
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd HH:mm:ss", true);
	}

}
