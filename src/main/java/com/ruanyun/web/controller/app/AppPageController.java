package com.ruanyun.web.controller.app;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.ImageUtil;
import com.ruanyun.web.jgpush.JpushClientUtil;
import com.ruanyun.web.jgpush.JpushShopUtil;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.model.daowei.TJishiInfo;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.*;
import com.ruanyun.web.model.sys.*;
import com.ruanyun.web.service.daowei.JiShiInfoService;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.service.mall.*;
import com.ruanyun.web.service.sys.*;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.NumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app/page")
public class AppPageController extends BaseController{

	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private MealInfoService mealInfoService;
	@Autowired
	private JiShiInfoService jiShiInfoService;
	@Autowired
	private CommentInfoService commentInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private ProvincService provincService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderMealService orderMealService;
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private ShopVideoService shopVideoService;
	@Autowired
	private AttachInfoService attachInfoService;
	@Autowired
	private SmsInfoService smsInfoService;
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
	 * 功能描述:商家详情
	 */
	@RequestMapping("merchant_details")
	public String merchant_details(String shopNum, String userNum, Model model){
		//获取商家详情
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", shopNum);
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
		shopInfo.setFlag1(flag1);
		addModel(model, "shopInfo", shopInfo);
		//获取该商家所有套餐
		List mealInfos = mealInfoService.getMealInfos(shopNum);
		addModel(model, "mealInfos", mealInfos);
		//获取该商家所有的技师
		List<TJishiInfo> jishiInfos = jiShiInfoService.getAllByCondition(TJishiInfo.class, "shopInfoNum", shopNum);
		//设置技师的年龄，设置类别名
		for (TJishiInfo jishiInfo : jishiInfos) {
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.setTime(jishiInfo.getUserBirth());
			calendar2.setTime(new Date());
			jishiInfo.setFlag1(String.valueOf(calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)));
			String flag2 = typeInfoService.getTypeInfoNames(jishiInfo.getTypeNum(), typeInfos);
			jishiInfo.setFlag2(flag2);
		}
		addModel(model, "jishiInfos", jishiInfos);
		//获取店铺的三个评价
		//获取评论数量
		List<TCommentInfo> commentInfos = commentInfoService.getSomeListByShopNum(shopNum, 0 ,3);
		int commentInfoCount = commentInfoService.getCountByShopNum(shopNum);
		List<TShopVedio> shopVedios = shopVideoService.getAllByCondition(TShopVedio.class, "shopNum", shopNum);
		addModel(model, "shopVedios", shopVedios);
		addModel(model, "commentInfos", commentInfos);
		addModel(model, "commentInfoCount", commentInfoCount);
		addModel(model, "userNum", userNum);
		List<TAttachInfo> attachInfos = attachInfoService.getAllByCondition(TAttachInfo.class, "glNum", shopNum);
		if (EmptyUtils.isNotEmpty(shopInfo.getMainPhoto())) {
			addModel(model, "picture_count", attachInfos.size()+1);
		} else {
			addModel(model, "picture_count", attachInfos.size());
		}
		if(attachInfos.size()>0){
			String imgUrlList = "";
			for (int i = 0; i < attachInfos.size(); i++) {
				imgUrlList +=Constants.QINIU_USER_IMGURL+attachInfos.get(i).getFilePath()+",";
			}
			addModel(model, "imgUrlList", imgUrlList.substring(0, imgUrlList.lastIndexOf(",")));
		}

		return "pc/app/merchant_details/merchant_details";
	}

	/**
	 * 功能描述:套餐详情
	 */
	@RequestMapping("mealinfo_details")
	public String package_details(String mealInfoNum, String userNum, Model model) {
		TMealInfo mealInfo = mealInfoService.get(TMealInfo.class, "mealInfoNum", mealInfoNum);
		mealInfo.setFlag1(typeInfoService.get(TTypeInfo.class, "typeNum", mealInfo.getTypeNum()).getTypeInfoName());
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", mealInfo.getShopNum());
		List<TCommentInfo> commentInfoList = commentInfoService.getSomeListByMealNum(mealInfoNum, 0, 3);
		addModel(model, "mealInfo", mealInfo);
		addModel(model, "shopInfo", shopInfo);
		addModel(model, "commentInfoList", commentInfoList);
		addModel(model, "commentInfoCount", commentInfoService.getCountByMealInfoNum(mealInfoNum));
		addModel(model, "userNum", userNum);
		return "pc/app/mealinfo_details/mealinfo_details";
	}

	/**
	 * 功能描述:套餐详情进入预约
	 */
	@RequestMapping("meal_bespeak")
	public String bespeak(String mealInfoNum, String userNum, Model model){
		TMealInfo mealInfo = mealInfoService.get(TMealInfo.class, "mealInfoNum", mealInfoNum);
		mealInfo.setFlag1(typeInfoService.get(TTypeInfo.class, "typeNum", mealInfo.getTypeNum()).getTypeInfoName());
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", mealInfo.getShopNum());
		TUserAddress userAddress = userAddressService.get(TUserAddress.class, "userNum", userNum);
		addModel(model, "mealInfo", mealInfo);
		addModel(model, "shopInfo", shopInfo);
		addModel(model, "userAddress", userAddress);
		addModel(model, "userNum", userNum);
		return "pc/app/mealinfo_details/bespeak";
	}

	/**
	 * 功能描述:分类
	 */
	@RequestMapping("classfly")
	public String classfly(String userNum, String typeNum, String typeNums, Integer type, Model model){
		Integer memberLevel = 1;
				addModel(model, "userNum", userNum);
		TTypeInfo typeInfo = typeInfoService.get(TTypeInfo.class, "typeNum", typeNum);
		addModel(model, "typeInfo", typeInfo);
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		if (EmptyUtils.isNotEmpty(typeNums)) {
			typeNum = typeNums;
			addModel(model, "typeNums", typeNums);
		}
		if (EmptyUtils.isNotEmpty(type)) {
			addModel(model, "type", type);
		}
		if(EmptyUtils.isNotEmpty(userNum)){
			TUser user =userService.get(TUser.class, "userNum",userNum);
			memberLevel = user.getMemberLevel();
		}
		addModel(model, "memberLevel", memberLevel);
		//商家
		List<TShopInfo> merchant = shopInfoService.getShopInfoByCondition(null, typeNum, type, null, memberLevel, null, 1, 0);
		for (TShopInfo shopInfo : merchant) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		//技师
		List<TShopInfo> technician = shopInfoService.getShopInfoByCondition(null, typeNum, type, null, memberLevel, null, 2, 0);
		for (TShopInfo shopInfo : technician) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		addModel(model, "typeInfos", typeInfos);
		addModel(model, "technician", technician);
		addModel(model, "merchant", merchant);
		return "pc/app/classfly/classfly";
	}

	@RequestMapping("ajaxClassfly")
	public void ajaxClassfly(Integer memberLevel, Integer startNum, String typeNums, Integer shopType, Integer type, HttpServletResponse response) {
		List<TShopInfo> shopInfos = shopInfoService.getShopInfoByCondition(null, typeNums, type, null, memberLevel, null, shopType, startNum);
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		for (TShopInfo shopInfo : shopInfos) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		super.writeJsonData(response, shopInfos);
	}

	/**
	 * 功能描述:技师详情
	 */
	@RequestMapping("technician_details")
	public String technician_details(String shopNum, String userNum, Model model) {
		addModel(model, "userNum", userNum);
		//获取技师详情
		TUser jishi = userService.get(TUser.class, "shopNum", shopNum);
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", shopNum);
		addModel(model, "shopInfo", shopInfo);
		jishi.setShopInfo(shopInfo);
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		String flag1 = typeInfoService.getTypeInfoNames(jishi.getShopInfo().getTypeNum(), typeInfos);
		jishi.getShopInfo().setFlag1(flag1);
		addModel(model, "jishi", jishi);
		//获取技师的所有套餐
		List<TMealInfo> mealInfos = mealInfoService.getMealInfos(shopNum);
		addModel(model, "mealInfos", mealInfos);
		//获取最近三条评论
		//获取评论数量
		int commentInfoCount = commentInfoService.getCountByShopNum(shopNum);
		List<TCommentInfo> commentInfos = commentInfoService.getSomeListByShopNum(shopNum, 0, 3);
		List<TShopVedio> shopVedios = shopVideoService.getAllByCondition(TShopVedio.class, "shopNum", shopNum);
		addModel(model, "shopVedios", shopVedios);
		addModel(model, "commentInfos", commentInfos);
		addModel(model, "commentInfoCount", commentInfoCount);
		if (EmptyUtils.isNotEmpty(jishi.getUserBirth())) {
			//获取技师的年龄
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.setTime(jishi.getUserBirth());
			calendar2.setTime(new Date());
			addModel(model, "nianling", calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR));
		}
		addModel(model, "picture_count", attachInfoService.getCount(Constants.ATTACH_INFO_ATTACHTYPE_2, shopNum));
		return "pc/app/technician_details/technician_details";
	}
	/**
	 * 功能描述:订单详情
	 */
	@RequestMapping("order")
	public String order(String orderNum, String userNum, Model model){
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		TOrderMeal orderMeal = orderMealService.get(TOrderMeal.class, "orderNum", orderNum);
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", orderInfo.getShopNum());
		 
		String provinceCode = orderInfo.getArea().substring(0,2)+"0000";
		orderInfo.setFalg1(provinceCode);
		addModel(model, "orderInfo", orderInfo);
		addModel(model, "orderMeal", orderMeal);
		addModel(model, "shopInfo", shopInfo);
		addModel(model, "userNum", userNum);
	 
		if (EmptyUtils.isNotEmpty(orderInfo.getCancelUserNum())) {
			addModel(model, "cancelUser", userService.get(TUser.class, "userNum", orderInfo.getCancelUserNum()));
		}
		return "pc/app/order/order";
	}

	/**
	 * 功能描述：进入取消原因输入页面
	 * @param userNum
	 * @param orderNum
	 * @param model
	 * @return
	 */
	@RequestMapping("cancel")
	public String cancel(String userNum, String orderNum, Model model) {
		addModel(model, "userNum", userNum);
		addModel(model, "orderNum", orderNum);
		return "pc/app/order/cancel_reason";
	}
	/**
	 * 功能描述：取消订单
	 * @param orderNum
	 * @param userNum
	 * @param response
	 */
	@RequestMapping("cancel_order")
	public void cancel_order(String orderNum, String userNum, String reason, HttpServletResponse response) {
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		orderInfo.setCancelTime(new Date());
		orderInfo.setCancelUserNum(userNum);
		orderInfo.setCancelReason(reason);
		BigDecimal returnPrice = new BigDecimal(0);
		if (orderInfo.getOrderStatus() == 2 || orderInfo.getOrderStatus() == 1) {//订单未结单或未付款 退全款
			returnPrice = orderInfo.getActualPrice();
			orderInfo.setReturnPrice(returnPrice);
		} else if (orderInfo.getOrderStatus() ==  3) {//订单已接单 退百分之八十
			returnPrice = orderInfo.getActualPrice().multiply(new BigDecimal(0.8));
			orderInfo.setReturnPrice(returnPrice);
		}
		orderInfo.setOrderStatus(-1);
		orderInfoService.update(orderInfo);
		//平台扣去钱
		userCenterService.updateAccountBalance("SY00000000000001",returnPrice.multiply(new BigDecimal(-1)), Constants.CONSUM_TYPE_1, orderInfo.getPayMethod(),  orderInfo.getOrderNum(), Constants.USER_TYPE_2,Constants.CONSUM_TYPE7_title,orderInfo.getPayMethod());
		
		//用户所得钱
		userCenterService.updateAccountBalance(orderInfo.getUserNum(),returnPrice, Constants.CONSUM_TYPE_1, orderInfo.getPayMethod(),  orderInfo.getOrderNum(), Constants.USER_TYPE_1,Constants.CONSUM_TYPE7_title,orderInfo.getPayMethod());
		TUser user =userService.getUserByUserNum(orderInfo.getUserNum(), true);
		TUser user2=userService.get(TUser.class, "shopNum",orderInfo.getShopNum());
		if(userNum.equals(orderInfo.getUserNum())){
			if(EmptyUtils.isNotEmpty(user2.getRegistrationId())){
				JpushShopUtil.sendToRegistrationId(user2.getRegistrationId(), "您的订单被取消了！", "您的订单被取消了！", "您的订单被取消了！", 1, null);
				smsInfoService.saveSmsInfo(user2.getUserNum(), "您的订单被取消了！", orderInfo.getUserNum(), "KHQXDD");
			}
		}else {
				if(EmptyUtils.isNotEmpty(user.getRegistrationId())){
					JpushClientUtil.sendToRegistrationId(user.getRegistrationId(), "店家取消了您的订单", "店家取消了您的订单", "店家取消了您的订单", 1, null);
			    	smsInfoService.saveSmsInfo(user.getUserNum(), "店家取消了您的订单", user2.getUserNum(), "DJQXDD");	
				}
			}
			
		
		
		super.writeText(response,"true");
	}

	/**
	 * 功能描述:个人资料
	 */
	@RequestMapping("personal_data")
	public String personal_data(String userNum, Model model){
		addModel(model, "user", userService.get(TUser.class, "userNum", userNum));
		return "pc/app/personal_data/personal_data";
	}
	/**
	 * 功能描述:修改昵称
	 */
	@RequestMapping("modify_nickname")
	public String modify_nickname(String userNum, Model model){
		addModel(model, "userNum", userNum);
		return "pc/app/personal_data/modify_nickname";
	}
	/**
	 * 功能描述:修改电话号码
	 */
	@RequestMapping("modify_tel")
	public String modify_tel(String userNum, Model model){
		addModel(model, "userNum", userNum);
		return "pc/app/personal_data/modify_tel";
	}

	/**
	 * 功能描述：修改头像
	 * @param session
	 * @param userPhoto
	 * @param userNum
	 * @param response
	 */
	@RequestMapping("save_my_data")
	public void save_my_data(HttpSession session, String userPhoto, String userNum, HttpServletResponse response){
		//TUser user = HttpSessionUtils.getCurrentUser(session);
		TUser user = userService.get(TUser.class, "userNum", userNum);
		if(EmptyUtils.isNotEmpty(userPhoto)){
			UploadVo vo= ImageUtil.GenerateImage2(userPhoto);
			user.setUserPhoto(vo.getFilename());
			userService.update(user);
		}
		//跳转到个人资料修改页面
		super.writeText(response, 1);
	}



	/**
	 * 功能描述：更新user,如果user是技师或者商家，则连店铺信息一起修改
	 * @param userNum
	 * @param nickName
	 * @param userPhone
	 * @param model
	 * @return
	 */
	@RequestMapping("updateUser")
	public String updateUser(String userNum,String nickName,String userPhone, Model model){
		TUser user = userService.get(TUser.class, "userNum", userNum);
		if(EmptyUtils.isNotEmpty(nickName)){
			user.setNickName(nickName);
		}
		if(EmptyUtils.isNotEmpty(userPhone)){
			user.setUserPhone(userPhone);
		}
		userService.update(user);
		addModel(model, "user", user);
		return "redirect:/app/page/personal_data?userNum=" + userNum;
	}
	/**
	 * 功能描述:添加地址
	 */
	@RequestMapping("no_address")
	public String no_address(String userNum, Model model){
		addModel(model, "userNum", userNum);
		TUserAddress address = userAddressService.get(TUserAddress.class, "userNum", userNum);
		if (EmptyUtils.isEmpty(address)) {
			return "pc/app/personal_data/address/no_address";
		} else {
			return "redirect:/app/page/add_delivery_address?userAddressNum=" + address.getUserAddressNum();
		}
	}
	/**
	 * 功能描述:添加地址
	 */
	@RequestMapping("add_delivery_address")
	public String add_delivery_address(String userNum, String userAddressNum, String linkMan, String linkTel, String address, String areaid, Model model, String longitude, String latitude){
		addModel(model, "userNum", userNum);
		addModel(model, "user", userService.get(TUser.class, "userNum", userNum));
		if (EmptyUtils.isNotEmpty(userAddressNum) && EmptyUtils.isEmpty(areaid)) {
			TUserAddress userAddress = userAddressService.get(TUserAddress.class, "userAddressNum", userAddressNum);
			TProvince province = provincService.get(TProvince.class,"provinceCode", userAddress.getProvince());
			TAreas areas = areasService.get(TAreas.class,"areaid", userAddress.getAreas());
			//所在地区的全称
			System.out.println(province.getProvinceName()+areas.getCityName()+areas.getArea());
			addModel(model, "szqy", province.getProvinceName()+areas.getCityName()+areas.getArea());
			addModel(model, "bean", userAddress);
			addModel(model, "areaid", areas.getAreaid());
		}
		if (EmptyUtils.isNotEmpty(areaid)) {
			TUserAddress userAddress;
			if (EmptyUtils.isNotEmpty(userAddressNum)) {
				userAddress = userAddressService.get(TUserAddress.class, "userAddressNum", userAddressNum);
			} else {
				userAddress = new TUserAddress();
			}
			userAddress.setLinkMan(linkMan);
			userAddress.setLinkTel(linkTel);
			userAddress.setAddress(address);
			userAddress.setLongitude(longitude);
			userAddress.setLatitude(latitude);
			addModel(model, "areaid", areaid);

			//获取省级编号
			String provinceCode = areaid.substring(0,2)+"0000";
			TProvince province = provincService.get(TProvince.class,"provinceCode", provinceCode);
			TAreas areas = areasService.get(TAreas.class,"areaid", areaid);
			//所在地区的全称
			System.out.println(province.getProvinceName()+areas.getCityName()+areas.getArea());
			addModel(model, "szqy", province.getProvinceName()+areas.getCityName()+areas.getArea());
			addModel(model, "bean", userAddress);
		}
		return "pc/app/personal_data/address/add_delivery_address";
	}
	/**
	 * 功能描述:选择省
	 */
	@RequestMapping("choose_province")
	public String choose_province(Model model, String userAddressNum, String linkMan, String linkTel, String address, String userNum, String longitude, String latitude){
		//个人资料页需要的数据
		List<TProvince> listProvince = provincService.getAllprovice("");
		addModel(model, "listProvince", listProvince);
		addModel(model, "userAddressNum", userAddressNum);
		addModel(model, "linkMan", linkMan);
		addModel(model, "linkTel", linkTel);
		addModel(model, "address", address);
		addModel(model,"userNum",userNum);
		addModel(model, "longitude", longitude);
		addModel(model, "latitude", latitude);

		//审核页面的数据

		return "pc/app/personal_data/address/area/choose_province";
	}
	/**
	 * 功能描述:选择市
	 */
	@RequestMapping("choose_city")
	public String choose_city(Model model, String userAddressNum, String provinceId, String linkMan, String linkTel, String address, String userNum, String longitude, String latitude){
		//个人资料页需要的数据
		List<TCity> listCity = cityService.getAllcity(provinceId);
		addModel(model, "listCity", listCity);
		addModel(model, "userAddressNum", userAddressNum);
		addModel(model, "linkMan", linkMan);
		addModel(model, "linkTel", linkTel);
		addModel(model, "address", address);
		addModel(model,"userNum",userNum);
		addModel(model, "longitude", longitude);
		addModel(model, "latitude", latitude);

		//审核页面的数据

		return "pc/app/personal_data/address/area/choose_city";
	}
	/**
	 * 功能描述:选择区
	 */
	@RequestMapping("choose_area")
	public String choose_area(Model model, String cityId, String provinceId, String userAddressNum, String linkMan, String linkTel, String address, String userNum, String longitude, String latitude){
		//个人资料页需要的数据
		List<TAreas> listAreas = areasService.getAllAreas(cityId);
		addModel(model, "userAddressNum", userAddressNum);
		addModel(model, "linkMan", linkMan);
		addModel(model, "linkTel", linkTel);
		addModel(model, "listAreas", listAreas);
		addModel(model, "address", address);
		addModel(model,"userNum",userNum);
		addModel(model, "longitude", longitude);
		addModel(model, "latitude", latitude);

		//审核页面的数据

		return "pc/app/personal_data/address/area/choose_area";
	}

	/**
	 * 功能描述：如果是修改收货地址，则更新，否则，新建收货地址
	 * @param model
	 * @param linkMan
	 * @param linkTel
	 * @param address
	 * @param userAddressNum
	 * @param userNum
	 * @param areaid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAddress")
	public void saveAddress(Model model, String linkMan, String linkTel, String address, String userAddressNum, String userNum, String areaid, String longitude, String latitude, HttpServletResponse response) throws Exception {
		TUserAddress userAddress = new TUserAddress();
		if(EmptyUtils.isNotEmpty(userNum) && EmptyUtils.isEmpty(userAddressNum)){
			userAddress = userAddressService.get(TUserAddress.class, "userNum", userNum);
			if (EmptyUtils.isEmpty(userAddress)) {
				userAddress = new TUserAddress();
			}
		}
		if(EmptyUtils.isNotEmpty(userAddressNum)){
			userAddress = userAddressService.get(TUserAddress.class, "userAddressNum", userAddressNum);
			if (EmptyUtils.isEmpty(userAddress)) {
				userAddress = new TUserAddress();
			}
		}
		userAddress.setLinkTel(linkTel);
		userAddress.setLinkMan(linkMan);
		userAddress.setAddress(address);
		userAddress.setLongitude(longitude);
		userAddress.setLatitude(latitude);
		if(EmptyUtils.isNotEmpty(userAddress.getUserAddressNum())){
			userAddressService.update(userAddress, userNum,areaid);//更新
		}else{
			userAddressService.save(userAddress, userNum,areaid);//新增
		}
		TUser user = userService.get(TUser.class, "userNum", userNum);
		user.setIsDefaultAddress(1);
		userService.update(user);
		//return "redirect:/app/page/personal_data?userNum=" + userNum;
		super.writeText(response,"true");
	}

	/**
	 * 功能描述：删除地址
	 * @param model
	 * @param userAddressNum
	 * @param userNum
	 * @return
	 */
	@RequestMapping("deleteAddress")
	public void deleteAddress(Model model,String userAddressNum, String userNum, HttpServletResponse response){
		TUserAddress userAddress = userAddressService.get(TUserAddress.class,"userAddressNum", userAddressNum);
		if(EmptyUtils.isNotEmpty(userAddress)){
			userAddressService.delete(userAddress);
		}

		TUser user = userService.get(TUser.class, "userNum", userNum);
		user.setIsDefaultAddress(2);
		userService.update(user);
		//return "redirect:/app/page/no_address?userNum=" + userNum;//我的收货地址列表
		super.writeText(response,"true");
	}

	/**
	 * 功能描述:进入成为技师选择界面
	 */
	@RequestMapping("registered")
	public String registered_technician(String userNum, Model model){
		addModel(model, "userNum", userNum);
		TUser user = userService.getShopUser(userNum);
		if (EmptyUtils.isNotEmpty(user)) {
			TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", user.getShopNum());
			addModel(model, "user", user);
			addModel(model, "shopType", shopInfo.getShopType());
			addModel(model, "typeInfo", typeInfoService.getTypes(12));
			addModel(model, "shopInfo", shopInfo);
			if (shopInfo.getShopStatus()==-1) {
				TAuditShopLog auditShopLog = auditShopLogService.getNew(shopInfo.getShopNum());
				addModel(model, "auditShopLog", auditShopLog);
			}
			if (shopInfo.getShopType() == 1) {
				return "pc/app/registered/merchant";
			} else {
				return "pc/app/registered/technician";
			}
		} else {
			return "pc/app/registered/registered";
		}
	}
	/**
	 * 功能描述：进入审核材料上传页面
	 */
	@RequestMapping("registered2")
	public String registered2(String userNum, Integer shopType, Model model){
		addModel(model, "user", userService.get(TUser.class, "createUserNum", userNum));
		addModel(model, "shopType", shopType);
		addModel(model, "userNum", userNum);
		addModel(model, "typeInfo", typeInfoService.getTypes(12));
		if (shopType == 1) {
			return "pc/app/registered/merchant";
		} else {
			return "pc/app/registered/technician";
		}
	}

	/**
	 * 功能描述：提交审核后返回原页面，显示不一样的显示
	 * @param userNum
	 * @param shopType
	 * @param address
	 * @param nickName
	 * @param userBirth
	 * @param userSex
	 * @param userPhone
	 * @param typeNum
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@RequestMapping("audits")
	public void audits(String userNum, Integer shopInfoId, String shopNum, String shopType, String address, String nickName, String userBirth, String userSex, String userPhone, String typeNum, String longitude, String latitude, String city, String area, HttpServletResponse response) {
		//获取省级编号
		TCity city1 = cityService.get(TCity.class, "cityName", city);
		TAreas areas = areasService.get(TAreas.class, new String[]{"cityCode", "area"}, new Object[]{city1.getCityCode(), area});
		TUser user1 = userService.get(TUser.class, "userNum", userNum);
		//设置性别
		Integer sex = null;
		if (EmptyUtils.isNotEmpty(userSex)) {
			sex = Integer.parseInt(userSex);
		}
		//设置生日
		Date birth = null;
		if (EmptyUtils.isNotEmpty(userBirth)) {
			birth = DateUtils.doFormatDate(userBirth, "yyyy-MM-dd");
		}
		//先在shopinfo里面注册商家或者技师
		if (EmptyUtils.isEmpty(shopNum) && EmptyUtils.isEmpty(shopInfoId)) {
			TShopInfo shopInfo = new TShopInfo(null, Integer.parseInt(shopType), nickName, typeNum, 0, sex, (double) 0, userBirth, user1.getUserPhoto(), 2, city1.getProvinceCode(), city1.getCityCode(), areas.getAreaid(), longitude, latitude, address, 0, null, null, userPhone, null, new Date(), 2, null, null, null);
			shopInfo.setCreatorNum(userNum);
			shopInfo.setIsHezuo(2);
			shopInfo.setStatus(1);
			shopInfo = shopInfoService.save(shopInfo);
			
			shopInfo.setShopNum(NumUtils.getCommondNum(NumUtils.PIX_SHOP_INFO, shopInfo.getShopInfoId()));
			TUser user2 = new TUser(null, nickName, user1.getLoginName(), user1.getLoginPass(), shopInfo.getShopName(), shopInfo.getShopNum(), sex, userPhone, user1.getUserNum(), new Date(), 1, user1.getUserPhoto(), 2, city1.getCityCode(), city1.getProvinceCode(), areas.getAreaid(), birth, user1.getPayPassword(), user1.getWorkStatus(), 2, null, user1.getSendMsgStatus(), user1.getIsMember());
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
		} else if (EmptyUtils.isNotEmpty(shopNum) && EmptyUtils.isNotEmpty(shopInfoId)) {
			TUser user2 = userService.get(TUser.class, "shopNum", shopNum);
			TShopInfo shopInfo = new TShopInfo(shopNum, Integer.parseInt(shopType), nickName, typeNum, 0, sex, (double) 0, userBirth, user1.getUserPhoto(), 2, city1.getProvinceCode(), city1.getCityCode(), areas.getAreaid(), longitude, latitude, address, 0, null, null, userPhone, user2.getUserNum(), new Date(), 2, null, null, null);
			shopInfo.setCreatorNum(userNum);
			shopInfo.setIsHezuo(2);
			shopInfo.setShopInfoId(shopInfoId);
			shopInfoService.update(shopInfo);
			user2.setNickName(nickName);
			user2.setUserBirth(birth);
			user2.setUserSex(sex);
			user2.setUserPhone(userPhone);
			user2.setShopName(shopInfo.getShopName());
			user2.setCity(city1.getCityCode());
			user2.setProvince(city1.getProvinceCode());
			user2.setArea(areas.getAreaid());
			user2.setAuditShopStatus(2);
			userService.update(user2);
		}
		super.writeText(response,"true");
	}

	/**
	 * 功能描述:我是技师
	 */
	@RequestMapping("vip_card")
	public String vip(){
		return "pc/app/vip/vip_card";
	}
	/**
	 * 功能描述:查看全部评价
	 */
	@RequestMapping("all_evaluation")
	public String all_evaluation(String shopNum, String mealNum, String userNum, Model model){
		List<TCommentInfo> commentInfos = new ArrayList<TCommentInfo>();
		//获取商家或技师的全部评价
		if (EmptyUtils.isNotEmpty(shopNum) && EmptyUtils.isEmpty(mealNum)) {
			int count = commentInfoService.getCountByShopNum(shopNum);
			commentInfos.addAll(commentInfoService.getSomeListByShopNum(shopNum, 0, count));
		}
		//获取某一套餐的全部评价
		if (EmptyUtils.isEmpty(shopNum) && EmptyUtils.isNotEmpty(mealNum)) {
			int count = commentInfoService.getCountByMealInfoNum(mealNum);
			commentInfos.addAll(commentInfoService.getSomeListByMealNum(mealNum, 0, count));
		}
		addModel(model, "commentInfos", commentInfos);
		addModel(model, "userNum", userNum);
		return "pc/app/all_evaluation/all_evaluation";
	}
	/**
	 * 功能描述:技师端(接单详情)
	 */
	@RequestMapping("receiving_details")
	public String receiving_details(String orderNum, String userNum, Model model){
		//获取订单详情
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		//获取订单商品详情
		TOrderMeal orderMeal = orderMealService.get(TOrderMeal.class, "orderNum", orderNum);
		if (orderInfo.getOrderStatus() == 5) {
			//获取评论
			TCommentInfo commentInfo = commentInfoService.get(TCommentInfo.class, new String[]{"orderNum", "status"}, new Object[]{orderNum, 1});
			addModel(model, "commentInfo", commentInfo);
		}
		//获取商家详情
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", orderInfo.getShopNum());
		String provinceCode = orderInfo.getArea().substring(0,2)+"0000";
		orderInfo.setFalg1(provinceCode);
		addModel(model, "orderInfo", orderInfo);
		addModel(model, "orderMeal", orderMeal);
		addModel(model, "shopInfo", shopInfo);
		addModel(model, "userNum", userNum);
		//取消用户信息
		if (EmptyUtils.isNotEmpty(orderInfo.getCancelUserNum())) {
			addModel(model, "cancelUser", userService.get(TUser.class, "userNum", orderInfo.getCancelUserNum()));
		}
		addModel(model, "orderInfo", orderInfo);
		return "pc/app/technician_item/receiving_details/receiving_details";
	}

	/**
	 * 功能描述：快速接单
	 * @param userNum
	 * @param orderNum
	 * @param response
	 */
	@RequestMapping("quick_order")
	public void quick_order(String userNum, String orderNum, HttpServletResponse response) {
		//获取订单详情
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		orderInfo.setReceiveTime(new Date());
		orderInfo.setOrderStatus(3);
		try {
			TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", orderInfo.getShopNum());
			orderInfoService.update(orderInfo);
			shopInfo.setJiedanCount(shopInfo.getJishiCount()+1);
			shopInfoService.update(shopInfo);
			TUser user=userService.getUserByUserNum(orderInfo.getUserNum(), true);
			 JpushClientUtil.sendToRegistrationId(user.getRegistrationId(), "您的订单已接单", "您的订单已接单", "您好,您的订单已被接单,请做好准备", 1, orderNum);
		       TUser user1 =userService.get(TUser.class, "shopNum",orderInfo.getShopNum());
		       smsInfoService.saveSmsInfo(orderInfo.getUserNum(), "您的订单已接单",user1.getUserNum(),"DDJS");
			super.writeText(response,"true");
		} catch (Exception e) {
			e.printStackTrace();
			super.writeText(response,"false");
		}
	}

	/**
	 * 功能描述：完成订单
	 * @param userNum
	 * @param orderNum
	 * @param response
	 */
	@RequestMapping("complete_order")
	public void complete_order(String userNum, String orderNum, HttpServletResponse response) {
		//获取订单详情
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		orderInfo.setCompleteTime(new Date());
		orderInfo.setOrderStatus(4);

		try {
			orderInfoService.update(orderInfo);
			//返还金额
			TUser user = userService.get(TUser.class, "userNum", orderInfo.getUserNum());
			BigDecimal returnPrice = new BigDecimal(0);
			if (user.getMemberLevel() == 2) {
				returnPrice = orderInfo.getActualPrice().multiply(BigDecimal.valueOf(0.05));
			} else if (user.getMemberLevel() == 3) {
				returnPrice = orderInfo.getActualPrice().multiply(BigDecimal.valueOf(0.1));
			} else if (user.getMemberLevel() == 4) {
				returnPrice = orderInfo.getActualPrice().multiply(BigDecimal.valueOf(0.15));
			}
			if (user.getMemberLevel() != 1) {
				//平台扣钱
				userCenterService.updateAccountBalance("SY00000000000001",returnPrice.multiply(BigDecimal.valueOf(-1)), Constants.CONSUM_TYPE_6, orderInfo.getPayMethod(),  orderInfo.getOrderNum(), Constants.USER_TYPE_3,orderNum+Constants.CONSUM_TYPE11_title,orderInfo.getPayMethod());
				//确认收货，平台返现
				userCenterService.updateAccountBalance(user.getUserNum(),returnPrice, Constants.CONSUM_TYPE_6, orderInfo.getPayMethod(),  orderInfo.getOrderNum(), Constants.USER_TYPE_3,orderNum+Constants.CONSUM_TYPE11_title,orderInfo.getPayMethod());
			}
			super.writeText(response,"true");
		} catch (Exception e) {
			e.printStackTrace();
			super.writeText(response,"false");
		}
	}
	/**
	 * 功能描述:技师端(新增套餐或者修改套餐)
	 */
	@RequestMapping("add_packages")
	public String add_packages(String userNum, String mealInfoNum, Model model){
		addModel(model, "userNum", userNum);
		addModel(model, "shopNum", shopInfoService.get(TShopInfo.class, "userNum", userNum).getShopNum());
		addModel(model, "typeInfo", typeInfoService.getTypes(12));
		if (EmptyUtils.isNotEmpty(mealInfoNum)) {
			TMealInfo mealInfo = mealInfoService.get(TMealInfo.class, "mealInfoNum", mealInfoNum);
			addModel(model, "mealInfo", mealInfo);
		}
		return "pc/app/technician_item/add_packages/add_packages";
	}

	/**
	 * 功能描述：保存或更新套餐信息
	 * @param mealInfoNum
	 * @param shopNum
	 * @param title
	 * @param day
	 * @param mealPrice
	 * @param greader
	 * @param makePrice
	 * @param mealLog
	 * @param fwlc
	 * @param gntx
	 * @param jjzz
	 * @param userPhoto
	 * @param response
	 */
	@RequestMapping("save_packages")
	public void save_packages(String mealInfoNum,Integer isMake, String shopNum, String title, String day, String mealPrice, String greader, String makePrice, Integer mealLog, String fwlc, String gntx, String jjzz, String yyxz, String userPhoto, HttpServletResponse response) {
		TMealInfo mealInfo = new TMealInfo();
		if (EmptyUtils.isNotEmpty(mealInfoNum)) {
			mealInfo = mealInfoService.get(TMealInfo.class, "mealInfoNum", mealInfoNum);
		}
		//设置对象的值
		mealInfo.setTitle(title);
		mealInfo.setTypeNum(day);
		mealInfo.setMealPrice(new BigDecimal(mealPrice));
		mealInfo.setMakeMethod(Integer.parseInt(greader));
		if (Integer.parseInt(greader) == 1) {
			mealInfo.setMakePrice(Double.valueOf(makePrice));
		}
		mealInfo.setMealLog(mealLog);
		mealInfo.setIsMake(isMake);
		mealInfo.setFwlc(fwlc);
		mealInfo.setGntx(gntx);
		mealInfo.setJjzz(jjzz);
		mealInfo.setYyxz(yyxz);
		if (EmptyUtils.isNotEmpty(userPhoto)) {
			UploadVo vo= ImageUtil.GenerateImage2(userPhoto);
			mealInfo.setMainPhoto(vo.getFilename());
		}
		if (EmptyUtils.isEmpty(mealInfoNum)) {
			mealInfo.setSold(0);
			mealInfo.setIsRecommend(2);
			mealInfo.setCreateTime(new Date());
			mealInfo.setShopNum(shopNum);
			mealInfo.setMealType(shopInfoService.get(TShopInfo.class, "shopNum", shopNum).getShopType());
			mealInfoService.save(mealInfo);
			mealInfo.setMealInfoNum(NumUtils.getCommondNum(NumUtils.PIX_MEAL_INFO, mealInfo.getMealInfoId()));
			mealInfoService.update(mealInfo);
		} else {
			mealInfoService.update(mealInfo);
		}
		super.writeText(response,"true");
	}
	/**
	 * 功能描述:技师端(技师管理)
	 */
	@RequestMapping("technician_management")
	public String technician_management(String userNum, Model model){
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "userNum", userNum);
		List<TJishiInfo> jishiInfos = jiShiInfoService.getAllByCondition(TJishiInfo.class, "shopInfoNum", shopInfo.getShopNum());
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		for (TJishiInfo jishiInfo : jishiInfos) {
			String flag1 = typeInfoService.getTypeInfoNames(jishiInfo.getTypeNum(), typeInfos);
			jishiInfo.setFlag1(flag1);
		}
		addModel(model, "userNum", userNum);
		addModel(model, "shopNum", shopInfo.getShopNum());
		addModel(model, "jishiInfos", jishiInfos);
		return "pc/app/technician_item/technician_management/technician_management";
	}

	/**
	 * 功能描述：删除商家下的技师
	 * @param jishiInfoNum
	 * @param response
	 */
	@RequestMapping("delete_technician")
	public void delete_technician(String jishiInfoNum, String shopNum, HttpServletResponse response) {
		int i = 0;
		try {
			i = jiShiInfoService.delete(TJishiInfo.class, "jishiInfoNum", jishiInfoNum);
			if (i > 0) {
				int j = shopInfoService.updateJishiCount(shopNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.writeText(response, i);
		}
	}

	/**
	 * 功能描述:技师端(新增技师或修改技师)
	 */
	@RequestMapping("add_technician")
	public String add_technician(String shopNum, String userNum, String jishiInfoNum, Model model){
		addModel(model, "typeInfo", typeInfoService.getTypes(12));
		addModel(model, "userNum", userNum);
		addModel(model, "shopNum", shopNum);
		if (EmptyUtils.isNotEmpty(jishiInfoNum)) {
			TJishiInfo jishiInfo = jiShiInfoService.get(TJishiInfo.class, "jishiInfoNum", jishiInfoNum);
			addModel(model, "jishiInfo", jishiInfo);
		}
		return "pc/app/technician_item/technician_management/add_technician";
	}

	/**
	 * 功能描述：保存商家下的技师
	 * @param shopNum
	 * @param jishiInfoNum
	 * @param userPhoto
	 * @param userName
	 * @param userBirth
	 * @param gender
	 * @param day
	 * @param description
	 * @param response
	 */
	@RequestMapping("save_technician")
	public void save_technician(String shopNum, String jishiInfoNum, String userPhoto, String userName, String userBirth, String gender, String day, String description,HttpServletResponse response) {
		TJishiInfo jishiInfo = new TJishiInfo();
		if (EmptyUtils.isNotEmpty(jishiInfoNum)) {
			jishiInfo = jiShiInfoService.get(TJishiInfo.class, "jishiInfoNum", jishiInfoNum);
		}
		jishiInfo.setUserName(userName);
		jishiInfo.setUserSex(Integer.parseInt(gender));
		jishiInfo.setUserBirth(DateUtils.doFormatDate(userBirth, "yyyy-MM-dd"));
		jishiInfo.setDescription(description);
		jishiInfo.setTypeNum(day);
		if (EmptyUtils.isNotEmpty(userPhoto)) {
			UploadVo vo= ImageUtil.GenerateImage2(userPhoto);
			jishiInfo.setMainPhoto(vo.getFilename());
		}
		if (EmptyUtils.isEmpty(jishiInfoNum)) {
			jishiInfo.setCreateTime(new Date());
			jishiInfo.setShopInfoNum(shopNum);
			jiShiInfoService.save(jishiInfo);
			jishiInfo.setJishiInfoNum(NumUtils.getCommondNum(NumUtils.PIX_JISHI_INFO, jishiInfo.getJishiInfoId()));
			jiShiInfoService.update(jishiInfo);
			int i = shopInfoService.updateJishiCount(shopNum);
		} else {
			jiShiInfoService.update(jishiInfo);
		}
		super.writeText(response,"true");
	}
	
	/**
	 * 功能描述:好友分享
	 * @author chenqian 2017-7-27 下午05:11:17
	 * @Controller AppPageController
	 */
	@RequestMapping("share_friend/{userNum}")
	public String agentfx(Model model,@PathVariable String userNum){
		addModel(model, "user", userService.get(TUser.class, "userNum", userNum));
		/*return "pc/app/sharefriend";*/
		return "pc/app/share";
	}

	/**
	 * 功能描述:
	 * @author chenqian 2017-7-27 下午05:11:17
	 * @Controller AppPageController
	 */
	@RequestMapping("share_code/{userNum}")
	public String sharecode(Model model,@PathVariable String userNum) throws UnknownHostException {
		//生成微信登录二维码链接
		String src = "http://www.91daowei.cc/daowei/app/page/share_friend/"+userNum;
		addModel(model, "src", src);
		return "pc/app/sharecode";
	}
}
