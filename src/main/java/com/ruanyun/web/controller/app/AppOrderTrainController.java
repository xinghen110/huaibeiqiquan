package com.ruanyun.web.controller.app;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.app.AppOrderTrainService;

@Controller
@RequestMapping("/app/orderTrain")
public class AppOrderTrainController extends BaseController{
	
	@Autowired
	private AppOrderTrainService appOrderTrainService;
	
	/**
	 * 功能描述:添加订单转换信息
	 * @author cqm  2017-1-9 下午06:14:27
	 * @param orderNum
	 * @param totalPrice
	 * @return
	 */
	@RequestMapping("add")
	public void addOrderTrain(HttpServletResponse response,String orderNum,BigDecimal totalPrice){
		AppCommonModel model = null;
		try {
			model=appOrderTrainService.addOrderTrain(orderNum, totalPrice);
		} catch (Exception e) {
			logger.error("/app/orderTrain/add:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
