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

import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.AuditShopLogService;
import com.ruanyun.web.util.CommonUtils;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.HttpSessionUtils;
import com.ruanyun.web.util.SecurityUtils;
@Controller
@RequestMapping("auditShopLog")
public class AuditShopLogController extends BaseController {
	
	@Autowired
	private AuditShopLogService auditShopLogService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 查看所有
	 * 
	 */
	@RequestMapping("list")
	public String getList(Page<TAuditShopLog> page,Model model,TAuditShopLog auditShopLog,HttpSession session,Integer type){
		TUser user = HttpSessionUtils.getCurrentUser(session);
		if(SecurityUtils.isGranted(ConstantAuth.SYSATEM_AUTH, user)){  //区域管理员
			auditShopLog.setCity(CommonUtils.getUserCity(user));
			}
		addModel(model, "pageList", auditShopLogService.getList(page, auditShopLog,user));
		addModel(model, "bean", auditShopLog);

		return "pc/backend/auditshoplog/list";
	}
	
	/**
	 * 功能描述:详情页
	 * @author zhujingbo
	 * @param model
	 * @param orderNum
	 * @return
	 */
	@RequestMapping("toDetailsEdit")
	public String toEdit(Model model,String auditShopLogNum){
		if (EmptyUtils.isNotEmpty(auditShopLogNum)) {
			TAuditShopLog bean = auditShopLogService.get(TAuditShopLog.class,"auditShopLogNum",auditShopLogNum);
			
			addModel(model, "bean", bean);
		
		}
	    return "pc/backend/auditshoplog/details";
	}

}
