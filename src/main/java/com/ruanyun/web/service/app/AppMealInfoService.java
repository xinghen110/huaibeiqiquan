package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.service.daowei.MealInfoService;

@Service
public class AppMealInfoService {
	
	
	@Autowired
	private MealInfoService mealInfoService;
	
	
	/**
	 * 功能描述:获取套餐列表
	 * @author cqm  2017-8-21 上午10:25:36
	 * @param page
	 * @param mealInfo
	 * @return
	 */
	public AppCommonModel getMealInfoList(Page<TMealInfo> page, TMealInfo mealInfo){
		return new AppCommonModel(1,"获取套餐列表成功",mealInfoService.getList(page, mealInfo));
	}

}
