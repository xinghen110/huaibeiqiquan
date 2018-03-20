package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.service.app.AppMealInfoService;

@Controller
@RequestMapping("/app/meal")
public class AppMealInfoController extends BaseController{
	
	
	@Autowired
	private AppMealInfoService appMealInfoService;
	
	/**
	 * 功能描述:获取套餐列表
	 * @author cqm  2017-8-21 上午10:28:35
	 * @param response
	 * @param page
	 * @param mealInfo
	 */
	@RequestMapping("list")
	public void getOrederInfoList(HttpServletResponse response,Page<TMealInfo> page, TMealInfo mealInfo){
		AppCommonModel model=null;
		try {
			model=appMealInfoService.getMealInfoList(page, mealInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/meal/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
