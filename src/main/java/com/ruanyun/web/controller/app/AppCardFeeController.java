package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.app.AppCardFeeService;

@Controller
@RequestMapping("/app/cardFee")
public class AppCardFeeController extends BaseController{
	
	@Autowired
	private AppCardFeeService appCardFeeService;
	

	/**
	 * 功能描述:获取会员列表
	 * @author cqm  2017-8-18 下午05:15:27
	 * @param response
	 */
	@RequestMapping("list")
	public void getCardFees(HttpServletResponse response,String userNum){
		AppCommonModel model=null;
		try {
			model=appCardFeeService.getCardFees(userNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/cardFee/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
