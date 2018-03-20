package com.ruanyun.web.controller.sys;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.service.sys.AreasService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
@Controller
@RequestMapping("areas")
public class AreasController extends BaseController {
	@Autowired
	private AreasService areasService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	@RequestMapping("list")
	public String getList(Page<TAreas> page,Model model,TAreas areas,HttpSession session){
		
		addModel(model, "pageList", areasService.getlist(page, areas));
		addModel(model, "bean", areas);
		return "pc/backend/areas/list";
	}

	@RequestMapping("isOpen")
	public void isOpen(HttpServletResponse response,String filed,String filedValue,String tableName,String queryFiled,String queryFiledValue){
		int result=areasService.updateQuery(filedValue, filed, queryFiledValue, tableName, queryFiled);
		if (result==1) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "areas/list", "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}
	
	@RequestMapping("savelevel")
	public void savelevel(HttpServletResponse response,String areaid,Integer level){
		
		int result=areasService.saveLevel(level, areaid);
		if (result==1) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "areas/list", "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
		
	}
}
