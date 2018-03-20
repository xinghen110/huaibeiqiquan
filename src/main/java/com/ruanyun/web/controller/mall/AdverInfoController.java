package com.ruanyun.web.controller.mall;

import javax.servlet.http.HttpServletRequest;
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
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.AdverInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.sys.CityService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.CommonUtils;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import com.ruanyun.web.util.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  广告
 *
 *  创建说明: 2016-9-7 下午05:41:50 wsp  创建文件<br/>
 */
@Controller
@RequestMapping("adverinfo")
public class AdverInfoController extends BaseController{
	
	@Autowired
	private AdverInfoService adverInfoService  ;//
	@Autowired
	private CityService cityService  ;//
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
	 * 功能描述:跳转到list页面
	 * @param page
	 * @param model
	 * @param adverInfo
	 * @param session
	 * @param adverType
	 * @return
	 */
	@RequestMapping("list")
	public String getList(Page<TAdverInfo> page,Model model,TAdverInfo adverInfo,HttpSession session,Integer adverType){
		TUser user = HttpSessionUtils.getCurrentUser(session);
		adverInfo.setAdverType(adverType);
		addModel(model, "pageList", adverInfoService.getList(page, adverInfo));
		addModel(model, "bean", adverInfo);
		addModel(model,"adverType",adverType);
		if (adverType == 1) {
			return "pc/backend/adverinfo/list";
		} else {
			return "pc/backend/adverinfo/list-hy";
		}
	}
	
	/**
	 * 功能描述:跳转到添加 修改页面
	 * @author wsp  2016-9-7 下午05:44:16
	 * @param model
	 * @param adverInfoId
	 * @return
	 */
	@RequestMapping("toEdit")
	public String toEdit(Model model,Integer adverInfoId,Integer adverType){
		if (EmptyUtils.isNotEmpty(adverInfoId)) {
			TAdverInfo adverInfo=adverInfoService.get(TAdverInfo.class,"adverInfoId",adverInfoId);
			addModel(model, "bean", adverInfo);
			
			if (EmptyUtils.isNotEmpty(adverInfo.getShopNum())) {
				TShopInfo shopInfo =shopInfoService.get(TShopInfo.class, "shopNum",adverInfo.getShopNum());
				if(EmptyUtils.isNotEmpty(shopInfo))
					addModel(model, "shopName", shopInfo.getShopName());
			}
			
		}
		addModel(model, "cityList", cityService.getAllcity(null));
		addModel(model,"adverType",adverType);
		if (adverType == 1) {
			return "pc/backend/adverinfo/edit";
		} else {
			return "pc/backend/adverinfo/edit-hy";
		}
	}
	
	/**
	 * 功能描述:保存和修改
	 * @author wsp  2016-9-7 下午05:45:23
	 * @param session
	 * @param model
	 * @param adverInfo
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("saveOrUpdate")
	public void saveOrUpdate(HttpSession session, Model model,TAdverInfo adverInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		TUser user = HttpSessionUtils.getCurrentUser(session);
		int result = adverInfoService.saveOrUpdate(adverInfo, request, user);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","adverinfo/list?adverType="+adverInfo.getAdverType(), "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}
	
	/**
	 * 功能描述:删除
	 * @author wsp  2016-9-7 下午05:46:23
	 * @param response
	 * @param adverInfoId
	 */
	@RequestMapping("del")
	public void delete(HttpServletResponse response,Integer adverInfoId,Integer adverType){
		int result = adverInfoService.delete(TAdverInfo.class, adverInfoId);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","adverinfo/list?adverType="+adverType, "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}
	
	/**
	 * 功能描述:是否显示
	 * @author wsp  2016-9-7 下午07:19:22
	 * @param response
	 * @param ids
	 * @param type
	 */
	@RequestMapping("ishomeshow")
	public void ishomeshow(HttpServletResponse response,String ids,Integer type){
		int result=adverInfoService.saveIsHomeShow(type , ids);
		if (result==1) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "adverinfo/list", "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}
	
	@RequestMapping("/easemob")
	public String easemob(HttpSession session,Model model){
		TUser currentUser = HttpSessionUtils.getCurrentUser(session);
		addModel(model, "currentUser", currentUser);;
		return "pc/easemob/easemob";
	}
	
	@RequestMapping("getFindBack")
	public String findBack(Model model, TShopInfo shopInfo, Page<TShopInfo> page,HttpSession session) {
		shopInfo.setShopStatus(1);
		Page<TShopInfo> pageList = shopInfoService.getlist(page, shopInfo);
		addModel(model, "pageList", pageList);
		addModel(model, "bean", shopInfo);
		return "pc/backend/adverinfo/findBack";
	}

	/**
	 * 借用广告表编辑前台页面
	 * @return
	 */
	@RequestMapping(value = "edit/page",method = RequestMethod.GET)
	public String toEditAboutUs(){
		return "pc/admin/page_edit";
	}

	/**
	 * 执行编辑页面
	 * @param response
	 * @param adverInfo
	 */
	@RequestMapping(value = "edit/page",method = RequestMethod.POST)
	public void doEditAboutUs(HttpServletResponse response,TAdverInfo adverInfo){
		adverInfoService.update(adverInfo);
		super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "index", "redirect"));
	}

	/**
	 * 为ajax提供页面的数据
	 * @param response
	 * @param adverInfo
	 */
	@RequestMapping(value = "get/page/info",method = RequestMethod.POST)
	public void getPageInfo(HttpServletResponse response,TAdverInfo adverInfo){
		super.writeJsonData(response,"utf-8",adverInfoService.getAdverInfo(adverInfo));
	}

	
}









