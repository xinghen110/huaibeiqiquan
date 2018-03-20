package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserApplication;
import com.ruanyun.web.service.app.AppUserApplicationService;

@Controller
@RequestMapping("/app/application")
public class AppUserApplicationController extends BaseController{
	
	@Autowired
	private AppUserApplicationService appUserApplicationService;
	
	/**
	 * 功能描述:申请提现
	 * @author cqm  2017-2-23 下午03:51:56
	 * @param response
	 * @param userApplication
	 */
	@RequestMapping("add")
	public void saveOrUpdateUserApplication(HttpServletResponse response,TUserApplication userApplication,String payPassword){
		AppCommonModel model=null;
		try {
			model=appUserApplicationService.saveOrUpdateUserApplication(userApplication,payPassword);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/application/add:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
