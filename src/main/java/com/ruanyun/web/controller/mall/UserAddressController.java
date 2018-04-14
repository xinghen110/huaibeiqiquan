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
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.UserAddressService;
import com.ruanyun.web.util.HttpSessionUtils;

/**
 * 	收货地址
 *
 *  创建说明: 2016-9-29 上午09:46:16 wsp  创建文件<br/>
 */
@Controller
@RequestMapping("useraddress")
public class UserAddressController extends BaseController{
	
	@Autowired
	private UserAddressService userAddressService  ;//
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 功能描述:跳转到list页面
	 * @author wsp  2016-9-29 上午09:47:57
	 * @param page
	 * @param model
	 * @param userAddress
	 * @param session
	 * @return
	 */
	@RequestMapping("list")
	public String getList(Page<TUserAddress> page,Model model,TUserAddress userAddress,HttpSession session){
		TUser user = HttpSessionUtils.getCurrentUser(session);
		addModel(model, "pageList", userAddressService.getList(page, userAddress,user));
		addModel(model, "bean", userAddress);
		return "pc/backend/useraddress/list";
	}
	
	/**
	 * 功能描述:跳转到添加 修改页面
	 * @author wsp  2016-9-29 上午09:48:33
	 * @param model
	 * @param userAddressNum
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(Model model,String userAddressNum){
		if (EmptyUtils.isNotEmpty(userAddressNum)) {
			addModel(model, "bean", userAddressService.get(TUserAddress.class,"userAddressNum",userAddressNum));
		}
	    return "pc/backend/useraddress/detailsEdit";
	}
	
	/**
	 * 功能描述:保存和修改
	 * @author wsp  2016-9-29 上午09:50:03
	 * @param session
	 * @param model
	 * @param userAddress
	 * @param response
	 * @throws Exception
	 */
//	@RequestMapping("saveOrUpdate")
//	public void saveOrUpdate(HttpSession session, Model model,TUserAddress userAddress,HttpServletResponse response) throws Exception{
//		TUser user = HttpSessionUtils.getCurrentUser(session);
//		int result = userAddressService.saveOrUpdate(userAddress,user);
//		if(result == 1){
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_SUCCESS_CODE,IPSConstants.MESSAGE_SUCCESS, "main_","useraddress/list", "forward"));
//		}else{
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_FAILD_CODE, IPSConstants.MESSAGE_FAILED, "","", ""));
//		}
//	}
	
	/**
	 * 功能描述:删除
	 * @author wsp  2016-9-29 上午09:55:33
	 * @param response
	 * @param userAddressNum
	 */
//	@RequestMapping("del")
//	public void delete(HttpServletResponse response,String userAddressNum){
//		int result = userAddressService.delete(TUserAddress.class,"userAddressNum", userAddressNum);
//		if(result == 1){
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_SUCCESS_CODE,IPSConstants.MESSAGE_SUCCESS, "main_","useraddress/list", "redirect"));
//		}else{
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_FAILD_CODE, IPSConstants.MESSAGE_FAILED, "","", ""));
//		}
//	}
	
}









