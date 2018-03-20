package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.service.app.AppAdverInfoService;
@Controller
@RequestMapping("/app/{city}/adver")
public class AppAdverInfoController extends BaseController {
	@Autowired
	private AppAdverInfoService appAdverInfoService;
	
	/**
	 * 功能描述: 获取广告列表
	 *
	 * @author yangliu  2016年7月27日 下午3:14:51
	 * 
	 * @param userNum 用户编号
	 * @return
	 */
	@RequestMapping("list")
	public void adverPage(HttpServletResponse response,@PathVariable String city,Page<TAdverInfo> page,TAdverInfo adverInfo){
		AppCommonModel model=null;
		try {
			model=appAdverInfoService.queryPage(page, adverInfo);
		} catch (Exception e) {
			logger.error("getUserFriendList:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
