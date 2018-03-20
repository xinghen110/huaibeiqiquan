package com.ruanyun.web.controller.mall;

import java.util.Date;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TJishiInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.JiShiInfoService;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.service.sys.AreasService;
import com.ruanyun.web.service.sys.CityService;
import com.ruanyun.web.service.sys.UserRoleService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	店铺管理
 *
 *  创建说明: 2016-9-20 下午02:25:57 wsp  创建文件<br/>
 */
@Controller
@RequestMapping("shopinfo")
public class ShopInfoController extends BaseController{
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private JiShiInfoService jiShiInfoService;
	@Autowired
	private MealInfoService mealInfoService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserCenterService userCenterService;
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
     * 审核列表和审核通过店铺技师列表
     * Zjb 2017 下午02:18:39
     * @param page
     * @param shopInfo
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String liString(Page<TShopInfo> page,TShopInfo shopInfo,Model model){
    	addModel(model, "pageList", shopInfoService.getlist(page, shopInfo));
    	addModel(model, "bean", shopInfo);
    	return "pc/backend/shopinfo/list";
    }
    /**
     * 查看审核技师信息
     * Zjb 2017 下午02:18:27
     * @param shopNum
     * @param model
     * @return
     */
    @RequestMapping("show")
    public String toshow(String shopNum,Model model){
    	TShopInfo shopInfo=shopInfoService.get(TShopInfo.class, "shopNum",shopNum);
    	addModel(model, "shopInfo", shopInfo);
    	String typeName="";
		String[] list2 =shopInfo.getTypeNum().split(",");
		for(int j = 0; j < list2.length; j++){
			typeName+=typeInfoService.get(TTypeInfo.class, "typeNum",list2[j]).getTypeInfoName()+" ";
		}
		typeName = typeName;
		addModel(model, "typeName", typeName);
    	return "pc/backend/shopinfo/show";
    }
	/**
	 * 功能描述：修改商家技师是否推荐
	 * @param ids
	 * @param response
	 */
	@RequestMapping("update")
	public void update(String ids,HttpServletResponse response) {
		int result = 0;
		Integer length = null;
		if (EmptyUtils.isNotEmpty(ids)) {
			String[] shopNums = ids.split(",");
			length = shopNums.length;
			for (int i =0; i < shopNums.length; i++) {
				result += shopInfoService.update(shopNums[i]);
			}
		}
		if (result == length) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "mealinfo/list", "redirect"));
		} else if (result > 0 && result < length){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, "部分数据变更成功！", "main_", "mealinfo/list", "redirect"));
		} else {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}

	@RequestMapping("updateStatus")
	public void updateStatus(String shopNum, Integer status,HttpServletResponse response) {
		int result = shopInfoService.updateStatus(shopNum, status);
		if (result > 0) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "mealinfo/list", "redirect"));
		} else {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}
	/**
	 * 功能描述：修改商家技师是否合作
	 * @param shopNum
	 * @param isHezuo
	 * @param response
	 */
	@RequestMapping("updateIsHezuo")
	public void updateIsHezuo(String shopNum, Integer isHezuo,HttpServletResponse response) {
		int result = shopInfoService.updateIsHezuo(shopNum, isHezuo);
		if (result > 0) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "mealinfo/list", "redirect"));
		} else {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}
    /**
     * 审批店铺和技师
     * Zjb 2017 下午02:18:14
     * @param shopInfo
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping("updateExamine")
    public void updateExamin(TShopInfo shopInfo,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    	TUser user=HttpSessionUtils.getCurrentUser(session);
    	int reuslt =shopInfoService.updateExamine(shopInfo,user);
    	if (reuslt==1){
    		super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","shopinfo/list?shopStatus=2", "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
    }

	/**
	 * 功能描述：查看店内技师
	 * @param page
	 * @param jsInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("jishi")
    public String liString(Page<TJishiInfo> page,TJishiInfo jsInfo,Model model){
    	addModel(model, "pageList",jiShiInfoService.getlist(page, jsInfo));
    	addModel(model, "bean", jsInfo);
    	return "pc/backend/shopinfo/jishilist";
    }

    /**
	 * 功能描述：进入添加套餐页面
	 * @param model
	 * @return
	 */
	@RequestMapping("add_packages")
	public String add_packages(String shopNum, Model model){
		addModel(model, "shopNum", shopNum);
		addModel(model, "typeInfo", typeInfoService.getTypes(12));
		return "pc/backend/shopinfo/add_packages";
	}

	/**
	 * 功能描述:进入成为技师选择界面
	 */
	@RequestMapping("registered")
	public String registered_technician(){
		return "pc/backend/shopinfo/registered";
	}

	/**
	 * 功能描述：进入审核材料上传页面
	 */
	@RequestMapping("registered2")
	public String registered2(Integer shopType, Model model){
		addModel(model, "shopType", shopType);
		addModel(model, "typeInfo", typeInfoService.getTypes(12));
		if (shopType == 1) {
			return "pc/backend/shopinfo/merchant";
		} else {
			return "pc/backend/shopinfo/technician";
		}
	}

	@RequestMapping("add_shopinfo")
	public void add_shopinfo(HttpSession session, String loginName, String password1, String shopType, String address, String nickName, String userBirth, String userSex, String userPhone, String typeNum, String longitude, String latitude, String city, String area, HttpServletResponse response) {
		TUser user=HttpSessionUtils.getCurrentUser(session);
		TUser user1 = userService.get(TUser.class, new String[]{"loginName", "userType"}, new Object[]{loginName, 2});
		if (EmptyUtils.isEmpty(user1)) {
			//获取省级编号
			TCity city1 = cityService.get(TCity.class, "cityName", city);
			TAreas areas = areasService.get(TAreas.class, new String[]{"cityCode", "area"}, new Object[]{city1.getCityCode(), area});
			//先在shopinfo里面注册商家或者技师
			Integer sex = null;
			if (EmptyUtils.isNotEmpty(userSex)) {
				sex = Integer.parseInt(userSex);
			}
			Date birth = null;
			if (EmptyUtils.isNotEmpty(userBirth)) {
				birth = DateUtils.doFormatDate(userBirth, "yyyy-MM-dd");
			}
			TShopInfo shopInfo = new TShopInfo(null, Integer.parseInt(shopType), nickName, typeNum, 0, sex, (double) 0, userBirth, null, 2, city1.getProvinceCode(), city1.getCityCode(), areas.getAreaid(), longitude, latitude, address, 0, null, null, userPhone, null, new Date(), 1, null, null, null);
			shopInfo.setIsHezuo(2);
			shopInfo.setStatus(1);
			shopInfo = shopInfoService.save(shopInfo);
			shopInfo.setShopNum(NumUtils.getCommondNum(NumUtils.PIX_SHOP_INFO, shopInfo.getShopInfoId()));
			TUser user2 = new TUser(null, nickName, loginName, password1, shopInfo.getShopName(), shopInfo.getShopNum(), sex, userPhone, user.getUserNum(), new Date(), 1, null, 2, city1.getCityCode(), city1.getProvinceCode(), areas.getAreaid(), birth, password1, 1, 1, null, 1, 1);
			user2.setIsDefaultAddress(2);
			user2 = userService.save(user2);
			if (Integer.parseInt(shopType) == 2) {
				user2.setUserNum(NumUtils.getCommondNum(NumUtils.PIX_JS_USER, user2.getUserId()));
			} else {
				user2.setUserNum(NumUtils.getCommondNum(NumUtils.PIX_SP_USER, user2.getUserId()));
			}
			user2 = userService.update(user2);
			shopInfo.setUserNum(user2.getUserNum());
			shopInfo = shopInfoService.update(shopInfo);
			userRoleService.save(String.valueOf(Constants.USER_ROLE_SHOP), String.valueOf(user2.getUserId()), user2);
			userCenterService.saveUserCenter(user2.getUserNum());
			super.writeText(response,"true");
		} else {
			super.writeText(response,"false");
		}
	}
}









