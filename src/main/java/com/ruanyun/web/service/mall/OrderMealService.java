package com.ruanyun.web.service.mall;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.OrderMealDao;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TOrderMeal;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.util.NumUtils;

//订单-商品
@Service("orderMealService")
public class OrderMealService extends BaseServiceImpl<TOrderMeal>{
	
	@Autowired
	private OrderMealDao orderMealDao;
	

    /**
     * 功能描述:生成
     * @author cqm  2017-8-18 下午06:36:23
     * @param orderInfo
     * @param mealInfo
     */
	public void addOrderMeal(TOrderInfo orderInfo,TMealInfo mealInfo){
		TOrderMeal orderMeal = new TOrderMeal();
		orderMeal.setMealNum(mealInfo.getMealInfoNum());
		orderMeal.setMainPhoto(mealInfo.getMainPhoto());
		orderMeal.setShopNum(orderInfo.getShopNum());
		orderMeal.setOrderNum(orderInfo.getOrderNum());
		orderMeal.setMealName(mealInfo.getTitle());
		orderMeal.setCreateTime(new Date());
		orderMeal.setSingleActualPrice(orderInfo.getSinglePrice());
		orderMeal.setUserNum(orderInfo.getUserNum());
		orderInfo.setTotalPrice(orderInfo.getTotalPrice());
		orderMeal.setActualPrice(orderInfo.getActualPrice());
		orderMeal.setGoodsCount(orderInfo.getTotalCount());
		orderMeal.setMealLog(mealInfo.getMealLog());
		orderMeal.setSalePrice(mealInfo.getMealPrice());
		save(orderMeal);
		String orderGoodsNum = NumUtils.getCommondNum(NumUtils.PIX_ORDER_MEAL,orderMeal.getOrderGoodsId());
		orderMeal.setOrderGoodsNum(orderGoodsNum);
	}
	
	/**
	 * 功能描述:查询订单套餐信息
	 * @author cqm  2017-8-19 上午11:03:58
	 * @param orderNums
	 * @return
	 */
	public Map<String, TOrderMeal> getOrderMealByOrderNum(String orderNums){
		if(EmptyUtils.isEmpty(orderNums)) 
			return null;
		return orderMealDao.getOrderMealByOrderNum(orderNums);
		
	}
	
}










