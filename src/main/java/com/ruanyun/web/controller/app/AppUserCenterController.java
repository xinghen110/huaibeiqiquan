package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.app.AppUserCenterService;

@Controller
@RequestMapping("/app/{userNum}/center")
public class AppUserCenterController extends BaseController{
	
	@Autowired
	private AppUserCenterService appUserCenterService;
	/**
	 * 功能描述:根据当前账号获取积分余额或账户余额
	 * @author cqm  2016-11-3 下午12:13:47
	 * @param response
	 * @param userNum
	 */
	@RequestMapping("get")
	public void getUserCouponInfo(HttpServletResponse response,@PathVariable String userNum){
		AppCommonModel model=null;
		try {
			model=appUserCenterService.getUserCenter(userNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/{userNum}/center/get:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
