package com.ruanyun.web.service.app;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.mall.OrderTrainService;

@Service
public class AppOrderTrainService {
	
	@Autowired
	private OrderTrainService orderTrainService;
	
	/**
	 * 功能描述:添加订单转换信息
	 * @author cqm  2017-1-9 下午06:14:27
	 * @param orderNum
	 * @param totalPrice
	 * @return
	 */
   	public AppCommonModel addOrderTrain(String orderNum,BigDecimal totalPrice){
		return new AppCommonModel(1,"添加成功",orderTrainService.addOrderTrain(orderNum, totalPrice));
	}

}
