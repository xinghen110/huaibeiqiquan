package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.app.AppCityService;

@Controller
@RequestMapping("/app/city")
public class AppCityController extends BaseController{
	
	@Autowired
	private AppCityService appCityService;
	
	/**
	 * 功能描述:查询城市列表
	 * @author cqm  2016-11-8 上午10:56:07
	 * @param response
	 * @param cityCode
	 * @param page
	 * @param city
	 * @param user 
	 */
	@RequestMapping("list")
	public void getCityList(HttpServletResponse response,TCity city){
		AppCommonModel model=null;
		try {
			model=appCityService.getCityList(city);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/city/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
