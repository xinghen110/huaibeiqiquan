package com.ruanyun.web.controller.mall;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;

import com.ruanyun.web.model.mall.TPayInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.PayInfoService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
@Controller
@RequestMapping("payInfo")
public class PayInfoController extends BaseController {
	
	@Autowired 
	private PayInfoService payInfoService;
	
	/**                                                               
	 * 功能描述:绑定时间                                               
	 */                                                                
	@InitBinder                                                       
	public void initBinders(WebDataBinder binder) {                     
		super.initBinder(binder, "yyyy-MM-dd", true);               
	}           
	
	
	@RequestMapping("toEdit")
	public String toEdit(Model model,HttpSession session,Integer payType){
		TPayInfo bean= payInfoService.get(TPayInfo.class,"payType",payType);
		addModel(model, "payType", payType);
		addModel(model, "bean", bean);
	    return "pc/backend/payinfo/edit";
	}
	
	@RequestMapping("saveOrUpdate")
	public void saveOrUpdate(HttpSession session,Model model,TPayInfo bean,Integer payType,HttpServletRequest request,HttpServletResponse response,MultipartFile fileName) throws IOException{
		TUser user =HttpSessionUtils.getCurrentUser(session);
		int result = payInfoService.saveOrUpdate(bean, user);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","payInfo/toEdit?payType="+payType, "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}

}
