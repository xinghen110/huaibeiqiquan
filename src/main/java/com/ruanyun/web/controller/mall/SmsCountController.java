package com.ruanyun.web.controller.mall;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.mall.TSmsCount;
import com.ruanyun.web.model.mall.TSmsInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.SmsCountService;
import com.ruanyun.web.util.HttpSessionUtils;
@Controller
@RequestMapping("smsCount")
public class SmsCountController extends BaseController {
	
	@Autowired
	private SmsCountService smsCountService;

	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	@RequestMapping("list")
	public String getList(Page<TSmsCount> page,Model model,TSmsCount smsCount,HttpSession session){
		TUser user = HttpSessionUtils.getCurrentUser(session);
		addModel(model, "pageList", smsCountService.getList(page, smsCount,user));
		addModel(model, "bean",smsCount);
		return "pc/backend/smscount/list";
	}

}
