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
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.UserRecordService;
import com.ruanyun.web.util.HttpSessionUtils;

/**
 * 	会员流水
 *
 *  创建说明: 2016-11-4 下午03:19:21 wsp  创建文件<br/>
 */
@Controller
@RequestMapping("userrecord")
public class UserRecordController extends BaseController{
	
	@Autowired
	private UserRecordService userRecordService  ;//
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 功能描述:跳转到list页面
	 * @author wsp  2016-11-4 下午03:20:31
	 * @param page
	 * @param model
	 * @param userRecord
	 * @param session
	 * @return
	 */
	@RequestMapping("list")
	public String getList(Page<TUserRecord> page,Model model,TUserRecord userRecord,HttpSession session){
		TUser user = HttpSessionUtils.getCurrentUser(session);
		addModel(model, "pageList", userRecordService.queryPage(page, userRecord,user));
		addModel(model, "bean", userRecord);
		return "pc/backend/userrecord/list";
	}
	
	
	/**
	 * 功能描述:删除
	 * @author wsp  2016-9-7 下午05:46:23
	 * @param response
	 * @param adverInfoId
	 */
//	@RequestMapping("del")
//	public void delete(HttpServletResponse response,Integer adverInfoId){
//		int result = adverInfoService.delete(TUserRecord.class, adverInfoId);
//		if(result == 1){
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_SUCCESS_CODE,IPSConstants.MESSAGE_SUCCESS, "main_","adverinfo/list", "redirect"));
//		}else{
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_FAILD_CODE, IPSConstants.MESSAGE_FAILED, "","", ""));
//		}
//	}
	
	
}









