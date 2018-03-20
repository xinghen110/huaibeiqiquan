package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.app.AppVersionUpdateService;

@Controller
@RequestMapping("/app/appUpdate")
public class AppVersionUpdateController extends BaseController{
	
	@Autowired
	private AppVersionUpdateService appVersionUpdateService;
	
	/**
	 * 功能描述:获取版本更新通知
	 * @author cqm  2017-4-5 下午01:57:55
	 * @param response
	 * @param request
	 * @param type
	 */
	@RequestMapping("checkUpdate")
	public void Update(HttpServletResponse response,HttpServletRequest request,Integer type){
		AppCommonModel model = null;
		try {
			model=appVersionUpdateService.getVersionUpdate(type);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/appUpdate/checkUpdate:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
}
