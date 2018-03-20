package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TRechargeMeal;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.app.AppRechargeMealService;
@Controller
@RequestMapping("/app/recharge")
public class AppRechargeMealController extends BaseController{
	
	@Autowired
	private AppRechargeMealService appRechargeMealService;
	
	/**
	 * 功能描述:查询套餐列表
	 * @author cqm  2016-11-5 下午05:40:49
	 * @param response
	 * @param userNum
	 * @param page
	 * @param rechargeMeal
	 * @param user
	 */
	@RequestMapping("list")                                                  
	public void getComplaintInfoList(HttpServletResponse response,TRechargeMeal rechargeMeal){
		AppCommonModel model=null;
		try {
			model=appRechargeMealService.getRechargeInfo(rechargeMeal);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/recharge/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
