package com.ruanyun.web.service.mall;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.OrderTrainDao;
import com.ruanyun.web.model.mall.TOrderTrain;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.NumUtils;
 
@Service("orderTrainService")
public class OrderTrainService extends BaseServiceImpl<TOrderTrain>{
	
	@Autowired
	private OrderTrainDao orderTrainDao;
	
	
	/**
	 * 功能描述:生成订单转换信息
	 * @author cqm  2017-1-9 下午06:08:39
	 * @param orderNum
	 * @param totalPrice
	 * @return
	 */
	public TOrderTrain addOrderTrain(String orderNum,BigDecimal totalPrice){
		System.out.println("===");
		TOrderTrain orderTrain = new TOrderTrain();
		orderTrain.setOrderNum(orderNum);
		orderTrain.setTotalPrice(totalPrice);
		orderTrain.setCreateTime(new Date());
		save(orderTrain);
		orderTrain.setOutTrainOrder(NumUtils.getCommondNum(NumUtils.PIX_ORDER_TRAIN, orderTrain.getOrderTrainId()));
		update(orderTrain);
		return orderTrain;
	}
	
	public TOrderTrain getOrderTrainInfo(String outTrainOrder,boolean isRequired){
		return orderTrainDao.getOrderTrainInfo(outTrainOrder, isRequired);
	}
	public TOrderTrain getOrderTrainByOrderNum(String orderNum){
		return orderTrainDao.getOrderTrainByOrderNum(orderNum);
	}
	
	public Map<String,TOrderTrain> getorderTrainByorderNum(String orderNum){
		if(EmptyUtils.isEmpty(orderNum))
			return null;
		return orderTrainDao.getorderTrainByorderNum(orderNum);
	}
}










