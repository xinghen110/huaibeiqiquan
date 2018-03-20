package com.ruanyun.web.controller.mall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TVersionUpdate;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.VersionUpdateService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
@Controller
@RequestMapping("versionUpdate")
public class VersionUpdateController extends BaseController {

	@Autowired
	private VersionUpdateService versionUpdateService;
	
	@RequestMapping("edit")
	public String edit(Integer type,Model model,Integer num){
		TVersionUpdate versionUpdate =versionUpdateService.get(TVersionUpdate.class,"type",type);
		addModel(model, "type", type);
		addModel(model, "num", num);
		addModel(model, "bean", versionUpdate);
		return "pc/backend/vertsion/edit";
		
	}
	
	@RequestMapping("saveOrUpdate")
	public void saveOrUpdate(Model model,TVersionUpdate bean,HttpServletRequest request,Integer num,HttpSession session,HttpServletResponse response,Integer type) throws Exception{
		TUser user = HttpSessionUtils.getCurrentUser(session);
		int result = versionUpdateService.saveOrUpdate(bean, request, type, num,user);
		if(result == 1){
			if(EmptyUtils.isNotEmpty(num)){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","versionUpdate/edit?type="+type+"&num="+num, "forward"));
			}else{
				super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","versionUpdate/edit?type="+type, "forward"));
			}
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
		
	}
}
