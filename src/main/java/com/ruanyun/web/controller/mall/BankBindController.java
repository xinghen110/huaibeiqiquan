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
import com.ruanyun.common.utils.EmptyUtils;

import com.ruanyun.web.model.mall.TBankBind;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.BankBindService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.CommonUtils;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import com.ruanyun.web.util.SecurityUtils;
@Controller
@RequestMapping("bankBind")
public class BankBindController extends BaseController {
	@Autowired
	private BankBindService bankBindService;
	@Autowired
	private ShopInfoService shopInfoService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 查看所有银行卡绑定信息
	 * zhujingbo
	 */
	@RequestMapping("list")
	public String getList(Page<TBankBind> page,Model model,TBankBind bankBind,HttpSession session){
		TUser user=HttpSessionUtils.getCurrentUser(session);
		if(SecurityUtils.isGranted(ConstantAuth.SYSATEM_AUTH, user)){  //区域管理员
			bankBind.setCity(CommonUtils.getUserCity(user));
			}
		addModel(model, "pageList", bankBindService.getList(page, bankBind));
		addModel(model, "bean", bankBind);

		return "pc/backend/bankbind/list";
	}
	
	/**
	 * 修改或者添加的页面跳转
	 * zhujingbo
	 * @param model
	 * @param session
	 * @param bankBind
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(Model model,HttpSession session,String bankBindNum){
		if(EmptyUtils.isNotEmpty(bankBindNum)){
				addModel(model, "bean", bankBindService.get(TBankBind.class,"bankBindNum",bankBindNum));
		}
	    return "pc/backend/bankbind/edit";
	}
	
	
	
	@RequestMapping("saveOrUpdate")
	public void saveOrUpdate(Model model,TBankBind bean,HttpServletRequest request,HttpServletResponse response,MultipartFile fileName,HttpSession session) throws IOException{
		TUser user=HttpSessionUtils.getCurrentUser(session);
		int result = bankBindService.saveOrUpdate(bean, request,user);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","bankBind/list", "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}

	/**
	 * 功能描述:查找带回
	 * @author zhujingbo
	 * @param model
	 * @param shopInfo
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("getFindBack")
	public String findBack(Model model, TShopInfo shopInfo, Page<TShopInfo> page,HttpSession session) {
		TUser user= HttpSessionUtils.getCurrentUser(session);
		if(SecurityUtils.isGranted(ConstantAuth.SYSATEM_AUTH, user)){  //区域管理员
			shopInfo.setCity(CommonUtils.getUserCity(user));
			}
//		if(user.getUserType()==1){//管理员
//			shopInfo.setCity(CommonUtils.getshopInfoCity(shopInfo));
//		}
////		shopInfo.setShopStatus(1);
//		Page<TShopInfo> pageList = shopInfoService.getList(page, shopInfo, user);
//		
//		addModel(model, "pageList", pageList);
//		addModel(model, "bean", shopInfo);
		return "pc/backend/bankbind/findBack";
	}

	
	/***
	 * 
	 * ajax判断店铺是否存在
	 * 
	 */
	@RequestMapping("searchAjaxshopNum")
	public void getshop(HttpServletResponse response,String shopNum){
		String result = null;
		TBankBind info = bankBindService.getAjaxshopNum(shopNum);
		if(info == null){
			result = "success";
		}else{
			result = "fail";
		}
		super.writeText(response,result);
	}

}
