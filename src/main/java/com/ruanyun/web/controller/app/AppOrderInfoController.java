package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.service.app.AppOrderInfoService;

@Controller
@RequestMapping("/app/order")
public class AppOrderInfoController extends BaseController {
	
    @Autowired
	private AppOrderInfoService appOrderInfoService;
	/**
	 * 功能描述:获取我的订单列表
	 * @author cqm  2016-10-25 上午11:07:14
	 * @param response
	 * @param userNum
	 */
	@RequestMapping("list")
	public void getOrederInfoList(HttpServletResponse response,String userNum,Page<TOrderInfo> page,TOrderInfo orderInfo){
		AppCommonModel model=null;
		try {
			model=appOrderInfoService.orderInfoList(page, userNum, orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/order/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:生成订单
	 * @author cqm  2017-8-17 下午03:24:33
	 * @param response
	 * @param ordersJsonString
	 */
	@RequestMapping("add")
	public void addOrderInfo(HttpServletResponse response,String ordersJsonString){
		AppCommonModel model=null;
		try {
			model=appOrderInfoService.addOrderInfo(ordersJsonString);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/order/add:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:更新订单
	 * @author cqm  2017-8-21 下午04:47:23
	 * @param response
	 * @param orderNum
	 * @param orderStatus
	 * @param cancelReason
	 */
	@RequestMapping("update")
	public void updateOrderStatus(HttpServletResponse response,String orderNum,Integer orderStatus,String cancelReason){
		AppCommonModel model=null;
		try {
			model=appOrderInfoService.updateOrderStatus(orderNum, orderStatus, cancelReason);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/order/update:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:生成会员订单
	 * @author cqm  2017-8-19 上午09:37:31
	 * @param response
	 * @param userNum
	 * @param cardFeeNum
	 */
	@RequestMapping("add_member")
	public void addOrderMember(HttpServletResponse response,String userNum,String cardFeeNum){
		AppCommonModel model=null;
		try {
			model=appOrderInfoService.saveOrderMemeber(userNum, cardFeeNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/order/add_member:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
}