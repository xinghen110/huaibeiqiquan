package com.ruanyun.web.controller.mall;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserApplication;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.UserApplicationService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 	提现
 *
 *  创建说明: 2016-9-23 下午05:23:28 wsp  创建文件<br/>
 */
@Controller
@RequestMapping("userapplication")
public class UserApplicationController extends BaseController{

	@Autowired
	private UserApplicationService userApplicationService  ;//
	
	@Autowired
	private ShopInfoService shopInfoService  ;//
	@Autowired
	private UserService userService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 功能描述:跳转到list页面
	 * @author wsp  2016-7-21 上午11:48:49
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String getList(Page<TUserApplication> page,Model model,TUserApplication userApplication,HttpSession session){
		TUser user = HttpSessionUtils.getCurrentUser(session);
//		if(SecurityUtils.isGranted(ConstantAuth.SYSATEM_AUTH, user)){  //区域管理员
//			userApplication.setCity(CommonUtils.getUserCity(user));
//		}
		int userType= user.getUserType();
		addModel(model, "pageList", userApplicationService.getList(page, userApplication,user));
		addModel(model, "bean", userApplication);
		addModel(model, "userType", userType);
		return "pc/backend/userapplication/list";
	}
	
	/**
	 * 功能描述:详情页
	 * @author wsp  2016-9-23 下午06:43:15
	 * @param model
	 * @return
	 */
	@RequestMapping("toDetailsEdit")
	public String toDetailsEdit(Model model,String userApplicationNum){
		
		if (EmptyUtils.isNotEmpty(userApplicationNum)) {
			TUserApplication bean = userApplicationService.get(TUserApplication.class,"userApplicationNum",userApplicationNum);
			addModel(model, "bean", bean);
			if(EmptyUtils.isNotEmpty(bean.getHandleNum()))
				addModel(model, "user", userService.get(TUser.class, "userNum",bean.getHandleNum()));
			
		}
	    return "pc/backend/userapplication/detailsEdit";
	}
	
	
	/**
	 * 功能描述：跳转到添加页面
	 * 
	 * 
	 * 
	 */
	
	@RequestMapping("toEdit")
	public String toEdit(Model model,String userApplicationNum,HttpSession session){
		TUser user = HttpSessionUtils.getCurrentUser(session);
	
		if(EmptyUtils.isNotEmpty(userApplicationNum)){
			addModel(model, "bean", userApplicationService.get(TUserApplication.class,"userApplicationNum",userApplicationNum));
			addModel(model, "userNum", user.getUserNum());
			addModel(model, "userType", user.getUserType());
		}
		return "pc/backend/userapplication/edit";
	}
	
	/**
	 * 功能描述:保存和修改
	 * @author wsp  2016-4-27 上午09:25:01
	 * @param session
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("saveOrUpdate")
	public void saveOrUpdate(Model model,TUserApplication bean,HttpServletRequest request,HttpServletResponse response,MultipartFile fileName,HttpSession session) throws IOException{
		TUser user = HttpSessionUtils.getCurrentUser(session);
		int result = userApplicationService.saveOrUpdate(bean, request,user);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","userapplication/list", "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}

}









