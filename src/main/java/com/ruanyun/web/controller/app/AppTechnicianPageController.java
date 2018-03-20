package com.ruanyun.web.controller.app;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.ImageUtil;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.model.daowei.TCommonProblem;
import com.ruanyun.web.model.daowei.TJishiInfo;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.*;
import com.ruanyun.web.model.sys.*;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.service.mall.*;
import com.ruanyun.web.service.sys.AreasService;
import com.ruanyun.web.service.sys.CityService;
import com.ruanyun.web.service.sys.ProvincService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.daowei.JiShiInfoService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.NumUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/app/pageshow")
public class AppTechnicianPageController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	private MealInfoService mealInfoService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderMealService orderMealService;
	@Autowired
	private CommentInfoService commentInfoService;
	@Autowired
	private AttachInfoService attachInfoService;
	@Autowired
	private JiShiInfoService jiShiInfoService;
	@Autowired
	private AdverInfoService adverInfoService;
	@Autowired
	private ShopVideoService shopVideoService;
	@Autowired
	private ProvincService provincService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreasService areasService;

	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}

	/**
	 * 功能描述:技师端(技师，商家 资料编辑)
	 */
	@RequestMapping("technician_data")
	public String technician_data(String userNum, String array, Model model){
		//获取商家或技师信息
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "userNum", userNum);
		//将jsonString中的数据设置到对象中
		if (EmptyUtils.isNotEmpty(array)) {
			shopInfo = shopInfoService.setShopInfoByJsonStr(shopInfo, array);
		}
		//设置服务类别
		List<TTypeInfo> typeInfoList = typeInfoService.getTypes(12);
		List<TTypeInfo> typeInfos = new ArrayList<TTypeInfo>();
		if (EmptyUtils.isNotEmpty(shopInfo.getTypeNum())) {
			String[] typeNum = shopInfo.getTypeNum().split(",");
			for (int i = 0; i < typeNum.length; i++) {
				for (int j = 0; j < typeInfoList.size(); j++) {
					if (typeNum[i].equals(typeInfoList.get(j).getTypeNum())) {
						typeInfos.add(typeInfoList.get(j));
						break;
					}
				}
			}
		}
		//设置营业时间
		if (EmptyUtils.isNotEmpty(shopInfo.getBusinessTime())) {
			String[] businessTime = shopInfo.getBusinessTime().split("~");
			shopInfo.setFlag1(businessTime[0]);
			shopInfo.setFlag2(businessTime[1]);
		}

		Integer count = attachInfoService.getCount(Constants.ATTACH_INFO_ATTACHTYPE_2, shopInfo.getShopNum());
		Integer videocount = shopVideoService.getCount(shopInfo.getShopNum());
		addModel(model, "shopInfo", shopInfo);
		addModel(model, "typeInfos", typeInfos);
		addModel(model, "userNum", userNum);
		addModel(model, "count", count);
		addModel(model, "videocount", videocount);
		return "pc/app/technician_item/data_editor/technician_data/technician_data";
	}

	/**
	 * 功能描述：修改技师或商家信息
	 * @param userNum
	 * @param array
	 * @param response
	 */
	@RequestMapping("save_data")
	public void save_data(String userNum, String array, HttpServletResponse response) {
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "userNum", userNum);
		//将jsonString中的数据设置到对象中
		if (EmptyUtils.isNotEmpty(array)) {
			shopInfo = shopInfoService.setShopInfoByJsonStr(shopInfo, array);
		}
		if (shopInfo.getShopType() == 1) {
			String flag1 = shopInfo.getFlag1();
			String flag2 = shopInfo.getFlag2();
			shopInfo.setBusinessTime(flag1+"~"+flag2);
			shopInfo.setFlag1(null);
			shopInfo.setFlag2(null);
		}
		try {
			shopInfoService.update(shopInfo);
			//如果店铺信息修改，则连user表里面也修改
			TUser user = userService.get(TUser.class, "userNum", userNum);
			user.setShopName(shopInfo.getShopName());
			user.setNickName(shopInfo.getShopName());
			user.setUserSex(shopInfo.getUserSex());
			if (EmptyUtils.isNotEmpty(shopInfo.getUserBirth())) {
				user.setUserBirth(DateUtils.doFormatDate(shopInfo.getUserBirth(), "yyyy-MM-dd"));
			}
			user.setUserPhone(shopInfo.getLinkTel());
			user.setProvince(shopInfo.getProvince());
			user.setCity(shopInfo.getCity());
			user.setArea(shopInfo.getArea());
			userService.update(user);
			super.writeText(response,"true");
		} catch (Exception e) {
			e.printStackTrace();
			super.writeText(response,"false");
		}
	}

	/**
	 * 功能描述：更换头像
	 * @param shopNum
	 * @param userPhoto
	 * @param response
	 */
	@RequestMapping("change_picture")
	public void change_picture(String shopNum, String userPhoto, HttpServletResponse response) {
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", shopNum);
		TUser user = userService.get(TUser.class, "shopNum", shopNum);
		if(EmptyUtils.isNotEmpty(userPhoto)){
			try {
				UploadVo vo= ImageUtil.GenerateImage2(userPhoto);
				user.setUserPhoto(vo.getFilename());
				shopInfo.setMainPhoto(vo.getFilename());
				userService.update(user);
				shopInfoService.update(shopInfo);
				super.writeText(response, "true");
			} catch (Exception e) {
				e.printStackTrace();
				super.writeText(response, "false");
			}
		}
		super.writeText(response, "false");
	}
	/**
	 * 功能描述:技师端(视频介绍)
	 */
	@RequestMapping("video_list")
	public String video_list(){
		return "pc/app/technician_item/data_editor/video_list/video_list";
	}
	/**
	 * 功能描述:技师端(新增视频)
	 */
	@RequestMapping("add_video")
	public String add_video(){
		return "pc/app/technician_item/data_editor/video_list/add_video";
	}
	/**
	 * 功能描述:技师端(服务类别)
	 */
	@RequestMapping("service_type")
	public String service_type(String array, String userNum, Model model){
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		JSONObject data = JSONObject.fromObject(array);
		if (data.containsKey("typeNums")) {
			String typeNums = data.getString("typeNums");
			addModel(model, "typeNums", typeNums);
		}
		addModel(model, "array", array);
		addModel(model, "userNum", userNum);
		addModel(model, "typeInfos", typeInfos);
		return "pc/app/technician_item/data_editor/service_type";
	}
	/**
	 * 功能描述:技师端(店铺图片)
	 */
	@RequestMapping("shop_img")
	public String shop_img(String array, String userNum, String shopNum, Model model){
		addModel(model, "array", array);
		addModel(model, "userNum", userNum);
		addModel(model, "shopNum", shopNum);
		//获取所有图片
		List<TAttachInfo> attachInfos = attachInfoService.getAttachInfoList(Constants.ATTACH_INFO_ATTACHTYPE_2, shopNum);
		addModel(model, "attachInfos", attachInfos);
		return "pc/app/technician_item/data_editor/shop_img";
	}

	/**
	 * 功能描述：删除图片
	 * @param attachId
	 * @param shopNum
	 * @param response
	 */
	@RequestMapping("del_shop_img")
	public void del_shop_img(String attachId, String shopNum, HttpServletResponse response) {
		try {
			int i = attachInfoService.delAttachInfo(attachId, Constants.ATTACH_INFO_ATTACHTYPE_2, shopNum);
			if (i == 0) {
				super.writeText(response, "false");
			} else {
				super.writeText(response, "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeText(response, "false");
		}
	}

	/**
	 * 功能描述：新增图片
	 * @param userNum
	 * @param img
	 * @param shopNum
	 * @param response
	 */
	@RequestMapping("add_shop_img")
	public void add_shop_img(String array, String userNum, String img, String shopNum, HttpServletResponse response) {
		if (EmptyUtils.isNotEmpty(img) && EmptyUtils.isNotEmpty(userNum) && EmptyUtils.isNotEmpty(shopNum)) {
			UploadVo vo= ImageUtil.GenerateImage2(img);
			TAttachInfo attachInfo = new TAttachInfo(vo.getFilename(), vo.getFilename(), vo.getFileSize(), userNum, new Date(), Constants.ATTACH_INFO_ATTACHTYPE_2, shopNum);
			try {
				attachInfo = attachInfoService.save(attachInfo);
				super.writeText(response, attachInfo.getAttachId());
			} catch (Exception e) {
				e.printStackTrace();
				super.writeText(response, "false");
			}
		} else {
			super.writeText(response, "false");
		}
	}
	/**
	 * 功能描述:技师端(意见反馈)
	 */
	@RequestMapping("feedback")
	public String feeback(String userNum, Model model){
		addModel(model, "userNum", userNum);
		addModel(model, "user", userService.get(TUser.class, "userNum", userNum));
		return "pc/app/feedback/feedback";
	}
	/**
	 * 功能描述:技师端(全部套餐)
	 */
	@RequestMapping("all_package")
	public String all_package(String userNum, String title, String typeNums, Integer type, Model model) {
		int memberLevel = 1;
		if(EmptyUtils.isNotEmpty(userNum)){
			TUser user =userService.get(TUser.class, "userNum",userNum);
			memberLevel=user.getMemberLevel();
		}
		addModel(model, "memberLevel", memberLevel);
		addModel(model, "userNum", userNum);
		addModel(model, "typeInfo", typeInfoService.getTypes(12));
		addModel(model, "title", title);
		addModel(model, "typeNums", typeNums);
		addModel(model, "type", type);
		List mealInfos = mealInfoService.getMealInfoByCondition(title, typeNums, type, memberLevel, 0);
		addModel(model, "mealInfos", mealInfos);
		return "pc/app/all_package/all_package";
	}

	/**
	 * 功能描述：分页获取套餐
	 * @param title
	 * @param typeNums
	 * @param type
	 * @param startNum
	 * @param response
	 */
	@RequestMapping("ajaxAll_package")
	public void ajaxAll_package(String title, String typeNums, Integer type, Integer memberLevel, Integer startNum, HttpServletResponse response) {
		List mealInfos = mealInfoService.getMealInfoByCondition(title, typeNums, type, memberLevel, startNum);
		super.writeJsonData(response, mealInfos);
	}

	/**
	 * 功能描述：技师端(全部技师)
	 */
	@RequestMapping("all_technician")
	public String all_technician(String userNum, String shopName, String typeNums, Integer type, Model model) {
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		int memberLevel=1;
		addModel(model, "userNum", userNum);
		addModel(model, "typeInfo", typeInfos);
		addModel(model, "shopName", shopName);
		addModel(model, "typeNums", typeNums);
		addModel(model, "type", type);
		if(EmptyUtils.isNotEmpty(userNum)){
		TUser user =userService.get(TUser.class, "userNum",userNum);
			memberLevel=user.getMemberLevel();
		}
		addModel(model, "memberLevel", memberLevel);
		List<TShopInfo> shopInfos = shopInfoService.getShopInfoByCondition(shopName, typeNums, type, null,memberLevel, null, 2, 0);
		for (TShopInfo shopInfo : shopInfos) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		addModel(model, "shopInfos", shopInfos);
		return "pc/app/all_technician/all_technician";
	}

	/**
	 * 功能描述：分页全部技师
	 * @param shopName
	 * @param typeNums
	 * @param type
	 * @param startNum
	 * @param memberLevel
	 * @param response
	 */
	@RequestMapping("ajaxAll_technician")
	public void ajaxAll_technician(String shopName, String typeNums, Integer type, Integer startNum, Integer memberLevel, HttpServletResponse response) {
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		List<TShopInfo> shopInfos = shopInfoService.getShopInfoByCondition(shopName, typeNums, type, null,memberLevel, null, 2, startNum);
		for (TShopInfo shopInfo : shopInfos) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		super.writeJsonData(response, shopInfos);
	}

	/**
	 * 功能描述：技师端(全部商家)
	 */
	@RequestMapping("all_merchant")
	public String all_merchant(String userNum, String shopName, String typeNums, Integer type, String longitude, String latitude, Model model) {
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		int memberLevel=1;
		addModel(model, "userNum", userNum);
		addModel(model, "typeInfo", typeInfos);
		addModel(model, "shopName", shopName);
		addModel(model, "typeNums", typeNums);
		addModel(model, "type", type);
		addModel(model, "longitude", longitude);
		addModel(model, "latitude", latitude);
		if(EmptyUtils.isNotEmpty(userNum)){
			TUser user =userService.get(TUser.class, "userNum",userNum);
				memberLevel=user.getMemberLevel();
			}
		addModel(model, "memberLevel", memberLevel);
		List<TShopInfo> shopInfos = shopInfoService.getShopInfoByCondition(shopName, typeNums, type,  longitude, memberLevel, latitude, 1, 0);
		for (TShopInfo shopInfo : shopInfos) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		addModel(model, "shopInfos", shopInfos);
		return "pc/app/all_merchant/all_merchant";
	}

	/**
	 * 功能描述：分页全部商家
	 * @param shopName
	 * @param typeNums
	 * @param type
	 * @param startNum
	 * @param memberLevel
	 * @param longitude
	 * @param latitude
	 * @param response
	 */
	@RequestMapping("ajaxAll_merchant")
	public void ajaxAll_merchant(String shopName, String typeNums, Integer type, Integer startNum, Integer memberLevel, String longitude, String latitude, HttpServletResponse response) {
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		List<TShopInfo> shopInfos = shopInfoService.getShopInfoByCondition(shopName, typeNums, type, longitude ,memberLevel, latitude, 1, startNum);
		for (TShopInfo shopInfo : shopInfos) {
			String flag1 = typeInfoService.getTypeInfoNames(shopInfo.getTypeNum(), typeInfos);
			shopInfo.setFlag1(flag1);
		}
		super.writeJsonData(response, shopInfos);
	}

	/**
	 * 功能描述:进入评价页面
	 */
	@RequestMapping("evaluate")
	public String evaluate(String orderNum, String userNum, Model model){
		addModel(model, "userNum", userNum);
		//获取订单详情
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		addModel(model, "orderInfo", orderInfo);
		//获取订单商品详情
		TOrderMeal orderMeal = orderMealService.get(TOrderMeal.class, "orderNum", orderNum);
		addModel(model, "orderMeal", orderMeal);
		//获取商家详情
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", orderInfo.getShopNum());
		addModel(model, "shopInfo", shopInfo);
		return "pc/app/all_evaluation/evaluate";
	}

	/**
	 * 功能描述：评论
	 * @param orderNum
	 * @param userNum
	 * @param content
	 * @param score
	 * @param response
	 */
	@RequestMapping("scoring")
	public void scoring(String orderNum, String userNum, String content, String score, HttpServletResponse response) {
		TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderNum);
		TOrderMeal orderMeal = orderMealService.get(TOrderMeal.class, "orderNum", orderNum);
		orderInfo.setOrderStatus(5);
		TCommentInfo commentInfo = new TCommentInfo(null, orderInfo.getShopNum(), orderMeal.getMealName(), orderMeal.getMealNum(), userNum, orderNum, Double.valueOf(score), content, new Date(), 1, null, null, null);
		commentInfo.setUpdateTime(new Date());
		try {
			commentInfoService.save(commentInfo);
			commentInfo.setCommentNum(NumUtils.getCommondNum(NumUtils.PIX_COMMENT_INFO, commentInfo.getCommentId()));
			commentInfoService.update(commentInfo);
			orderInfoService.update(orderInfo);
			shopInfoService.updateScore(orderInfo.getShopNum());
			super.writeText(response,"true");
		} catch (Exception e) {
			e.printStackTrace();
			super.writeText(response,"false");
		}
	}
	
	/**
	 * 功能描述:店内技师详情
	 */
	@RequestMapping("shop_technician_details")
	public String shop_technician_details(String jishiInfoNum, Model model){
		TJishiInfo jishiInfo = jiShiInfoService.get(TJishiInfo.class, "jishiInfoNum", jishiInfoNum);
		List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
		String flag1 = typeInfoService.getTypeInfoNames(jishiInfo.getTypeNum(), typeInfos);
		jishiInfo.setFlag1(flag1);
		//获取技师的年龄
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(jishiInfo.getUserBirth());
		calendar2.setTime(new Date());
		addModel(model, "nianling", calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR));
		addModel(model, "jishiInfo", jishiInfo);
		return "pc/app/technician_details/shop_technician_details";
	}

	/**
	 * 功能描述：自定义广告
	 * @param model
	 * @return
	 */
	@RequestMapping("advertisement")
	public String protocol(String adverInfoId ,Model model) {
		addModel(model, "adverInfo", adverInfoService.get(TAdverInfo.class, "adverInfoId", Integer.parseInt(adverInfoId)));
		return "pc/app/advertisement/advertisement";
	}

	/**
	 * 功能描述：协议
	 */
	@RequestMapping("agreement")
	public String agreement(String commonProblemNum, Model model) {
		addModel(model, "commonProblem", commentInfoService.get(TCommonProblem.class, "commonProblemNum" ,commonProblemNum));
		return "pc/app/mealinfo_details/agreement";
	}
	

	/**
	 * 功能描述:视频详情
	 */
	@RequestMapping("video_details")
	public String video_details(String videoNum, Model model){
		TShopVedio shopVedio = shopVideoService.get(TShopVedio.class, "videoNum", videoNum);
		addModel(model, "shopVedio", shopVedio);
		return "pc/app/video_details/video_details";
	}

	/**
	 * 功能描述:图片详情
	 */
	@RequestMapping("picture_details")
	public String picture_details() {
		return null;
	}
	/**
	 * 功能描述:广告位
	 */
	@RequestMapping("ad_position")
	public String ad_position(Page<TAdverInfo> page, TAdverInfo adverInfo, Model model){
		List<TAdverInfo> adverInfos = adverInfoService.getList(page, adverInfo).getResult();
		addModel(model, "adverInfos", adverInfos);
		String title = null;
		if (adverInfo.getAdverType() == 2) {
			title = "普通";
		} else if (adverInfo.getAdverType() == 3) {
			title = "高级";
		}
		addModel(model, "title", title);
		return "pc/app/ad_position/ad_position";
	}

	/**
	 * 功能描述：第一次登陆设置归属地
	 * @param userNum
	 * @param province
	 * @param city
	 * @param area
	 */
	@RequestMapping("saveUserAreaid")
	public void saveUserAreaid(String userNum, String province, String city, String area, HttpServletResponse response) {
		AppCommonModel model;
		if (EmptyUtils.isNotEmpty(userNum) && EmptyUtils.isNotEmpty(province) && EmptyUtils.isNotEmpty(city) && EmptyUtils.isNotEmpty(area)) {
			try {
				TUser user = userService.get(TUser.class, "userNum", userNum);
				if (EmptyUtils.isEmpty(user.getAreaid())) {
					TProvince province1 = provincService.get(TProvince.class, "provinceName", province);
					TCity city1 = cityService.get(TCity.class, new String[]{"cityName", "provinceCode"}, new Object[]{city, province1.getProvinceCode()});
					TAreas areas1 = areasService.get(TAreas.class, new String[]{"cityCode", "area"}, new Object[]{city1.getCityCode(), area});
					user.setAreaid(areas1.getAreaid());
					userService.update(user);
					model = new AppCommonModel(1, "设置归属地成功！", new Object());
				} else {
					model = new AppCommonModel(1, "已有归属地！", new Object());
				}
			} catch (Exception e) {
				e.printStackTrace();
				model = new AppCommonModel(-1, e.getMessage(), new Object());
			}
		} else {
			model = new AppCommonModel(-1, "参数不全！", new Object());
		}
		super.writeAppDataEncryption(response, model);
	}

}
