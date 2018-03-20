package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TSmsInfo;
import com.ruanyun.web.service.app.AppSmsInfoService;

@Controller
@RequestMapping("/app/smsinfo")
public class AppSmsInfoController extends BaseController {
	@Autowired
	private AppSmsInfoService appSmsInfoService;
	
	@RequestMapping("list/{userNum}")
	public void smsInfo(HttpServletResponse response,@PathVariable String userNum,Page<TSmsInfo> page,TSmsInfo smsInfo){
		AppCommonModel model=null;
		try {
			model=appSmsInfoService.getSmsInfoList(page, smsInfo);
		} catch (Exception e) {
			logger.error("getUserFriendList:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
}
