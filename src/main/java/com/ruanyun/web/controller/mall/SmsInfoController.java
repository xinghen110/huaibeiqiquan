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
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TAuditShopLog;
import com.ruanyun.web.model.mall.TSmsInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.SmsInfoService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.HttpSessionUtils;
@Controller
@RequestMapping("smsInfo")
public class SmsInfoController extends BaseController {
	
	@Autowired
	private SmsInfoService smsInfoService;
	@Autowired
	private UserService userService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	@RequestMapping("list")
	public String getList(Page<TSmsInfo> page,Model model,TSmsInfo smsInfo,HttpSession session){
		TUser user = HttpSessionUtils.getCurrentUser(session);
		addModel(model, "pageList", smsInfoService.getList(page, smsInfo,user));
		addModel(model, "bean", smsInfo);
		return "pc/backend/smsinfo/list";
	}
	
	/**
	 * 功能描述:详情页
	 * @author zhujingbo
	 * @param model
	 * @param orderNum
	 * @return
	 */
	@RequestMapping("toDetailsEdit")
	public String toEdit(Model model,String smsInfoNum){
		if (EmptyUtils.isNotEmpty(smsInfoNum)) {
			TSmsInfo bean = smsInfoService.get(TSmsInfo.class,"smsInfoNum",smsInfoNum);
			
			addModel(model, "bean", bean);
			addModel(model, "user", userService.get(TUser.class,"userNum",bean.getUserNum()));
		
		}
	    return "pc/backend/smsinfo/details";
	}

}


