package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.app.AppCommonInfoService;

@Controller
@RequestMapping("/app/common")
public class AppCommonInfoController extends BaseController{
	
	@Autowired
	private AppCommonInfoService appCommonInfoService;
	
	
	/**
	 * 功能描述:获取首页信息
	 * @author cqm  2017-6-28 下午04:08:27
	 * @param response
		 */
	@RequestMapping("list")
	public void getCommon(HttpServletResponse response,String longitude,String latitude,Integer memberLevel){
		AppCommonModel model=null;
		try {
			model=appCommonInfoService.getCommon(longitude, latitude,memberLevel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/common/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	

}
