package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.jgpush.JpushClientUtil;
import com.ruanyun.web.jgpush.JpushShopUtil;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.UserMemberService;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.SmsInfoService;
import com.ruanyun.web.service.sys.UserService;


@Service
public class AppOrderInfoService {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private UserMemberService userMemberService;
	@Autowired
	private UserService userService;
	@Autowired
	private SmsInfoService smsInfoService;
	
	/**
	 * 功能描述:获取订单列表
	 * @author cqm 2016-10-25 上午11:04:03
	 * @param userNum
	 * @return
	 */
	public AppCommonModel orderInfoList(Page<TOrderInfo> page,String userNum,TOrderInfo orderInfo){
		orderInfo.setUserNum(userNum);
		return new AppCommonModel(1,"获取订单列表成功",orderInfoService.getList(page, orderInfo));
	}
	
	/**
	 * 功能描述:生成订单
	 * @author cqm  2017-8-17 下午03:23:39
	 * @param ordersJsonString
	 * @return
	 */
	public AppCommonModel addOrderInfo(String ordersJsonString){
		return new AppCommonModel(1,"订单已生成,请及时支付",orderInfoService.addOrderInfo(ordersJsonString));
	}
	
	/**
	 * 功能描述:生成会员订单
	 * @author cqm  2017-8-19 上午09:36:55
	 * @param userNum
	 * @param cardFeeNum
	 * @return
	 */
	public AppCommonModel saveOrderMemeber(String userNum,String cardFeeNum){
		return new AppCommonModel(1,"充值会员订单已生成,请及时支付",userMemberService.saveOrderMemeber(userNum, cardFeeNum));
	}
	
	/**
	 * 功能描述:更新订单
	 * @author cqm  2017-8-21 下午04:43:41
	 * @param orderNum
	 * @param orderStatus
	 * @param cancelReason
	 * @return
	 */
	public AppCommonModel updateOrderStatus(String orderNum,Integer orderStatus,String cancelReason){
		AppCommonModel model = new AppCommonModel(-1, "","");
		orderInfoService.updateOrderStatus(orderNum, orderStatus, cancelReason);
		TOrderInfo orderInfo = orderInfoService.getOrderInfo(orderNum, true);
		
		TUser shopUser = userService.getUserByUserNum(orderInfo.getUserNum(), true);
		System.out.println("=="+shopUser.getRegistrationId());
		TUser user= userService.get(TUser.class, "shopNum",orderInfo.getShopNum());//商家用户编号
		if(orderStatus==-1){
		    if(EmptyUtils.isNotEmpty(shopUser.getRegistrationId())){
		    	JpushClientUtil.sendToRegistrationId(shopUser.getRegistrationId(), "您的订单已被取消", "您的订单已被取消", "很抱歉,由于工作太忙,您的订单已被取消,钱已全款退给您！！", 1, orderNum);
		    	smsInfoService.saveSmsInfo(orderInfo.getUserNum(), "您的订单被取消", user.getUserNum(), "DDQX");
		    }
			model.setMsg("取消成功");
			model.setObj("{}");
			model.setResult(1);
			return model;
		}
		if(orderStatus==3){
			 if(EmptyUtils.isNotEmpty(shopUser.getRegistrationId())){
			    	JpushClientUtil.sendToRegistrationId(shopUser.getRegistrationId(), "商家接受了您的订单", "商家接受了您的订单", "您好,商家接受了您的订单", 1, orderNum);
			    	smsInfoService.saveSmsInfo(orderInfo.getUserNum(), "商家接受了您的订单", user.getUserNum(), "DDJS");
			 }
			model.setMsg("接单成功");
			model.setObj("{}");
			model.setResult(1);
			return model;
		}
		return model;
	}

}
