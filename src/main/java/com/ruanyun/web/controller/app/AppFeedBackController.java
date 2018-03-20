package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.daowei.TFeedBack;
import com.ruanyun.web.service.app.AppFeedBackService;

@Controller
@RequestMapping("app/feebBack")
public class AppFeedBackController extends BaseController{
	
	@Autowired
	private AppFeedBackService appFeedBackService;
	
	/**
	 * 功能描述:意见反馈
	 * @author cqm  2017-8-10 下午01:58:28
	 * @param response
	 * @param feedBack
	 */
	@RequestMapping("add")
	public void addFeedBack(HttpServletResponse response,TFeedBack feedBack){
		AppCommonModel model=null;
		try {
			model=appFeedBackService.addFeedBack(feedBack);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/common/add:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
