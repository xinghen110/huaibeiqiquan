package com.ruanyun.web.service.mall;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.ruanyun.web.model.TUserAccount;
import com.ruanyun.web.model.TUserInfoCheck;
import com.ruanyun.web.service.web.UserAccountFlowService;
import com.ruanyun.web.service.web.UserAccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.TimeUtil;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.OrderInfoDao;
import com.ruanyun.web.jgpush.JpushClientUtil;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TOrderMeal;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.service.sys.AreasService;
import com.ruanyun.web.service.sys.CityService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.NumUtils;

//订单
@Service("orderInfoService")
public class OrderInfoService extends BaseServiceImpl<TOrderInfo>{
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	
	@Autowired
	private ShopInfoService shopInfoService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private OrderRecordService orderRecordService;
	
	@Autowired
	private MealInfoService mealInfoService;
	
	@Autowired
	private TypeInfoService typeInfoService;
	
	@Autowired
	private OrderMealService orderMealService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private AreasService areasService;
	
	@Autowired
	private UserCenterService userCenterService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserAccountFlowService userAccountFlowService;
	
	/**
	 * 功能描述:查询订单信息
	 * @author cqm  2017-8-8 下午03:26:55
	 * @param page
	 * @param orderInfo
	 * @return
	 */
	public Page<TOrderInfo> getList(Page<TOrderInfo> page,TOrderInfo orderInfo){
		Page<TOrderInfo> _page= orderInfoDao.queryPage(page, orderInfo);
		if(EmptyUtils.isNotEmpty(_page.getResult())){
			//查询店铺信息
			String shopNums=CommonUtils.getAttributeValue(TOrderInfo.class, _page.getResult(), "shopNum");
			Map<String,TShopInfo> shopInfoMap=shopInfoService.getShopInfoByShopNum(shopNums);
			CommonUtils.setAttributeValue(TOrderInfo.class,  _page.getResult(), shopInfoMap, "shopNum", "shopInfo");
			//查询用户信息
			String userNums=CommonUtils.getAttributeValue(TOrderInfo.class,  _page.getResult(), "userNum");
			Map<String,TUser> userMap=userService.getUserByUserNums(userNums);
			CommonUtils.setAttributeValue(TOrderInfo.class,  _page.getResult(), userMap, "userNum", "user");
			//订单套餐信息
			String orderNums=CommonUtils.getAttributeValue(TOrderInfo.class,  _page.getResult(), "orderNum");
			Map<String,TOrderMeal> orderMealMap=orderMealService.getOrderMealByOrderNum(orderNums);
			CommonUtils.setAttributeValue(TOrderInfo.class,  _page.getResult(), orderMealMap, "orderNum", "orderMeal");
		}
		return _page;
	}

	/**
	 * 功能描述:查询分销提成列表信息
	 * @author cqm  2017-8-8 下午03:26:55
	 * @param page
	 * @param orderInfo
	 * @return
	 */
	public Page<TOrderInfo> getFXList(Page<TOrderInfo> page,TOrderInfo orderInfo){
		Page<TOrderInfo> _page= orderInfoDao.getFXList(page, orderInfo);
		if(EmptyUtils.isNotEmpty(_page.getResult())){
			//查询用户信息
			String userNums=CommonUtils.getAttributeValue(TOrderInfo.class,  _page.getResult(), "userNum");
			Map<String,TUser> userMap=userService.getUserByUserNums(userNums);
			CommonUtils.setAttributeValue(TOrderInfo.class,  _page.getResult(), userMap, "userNum", "user");
		}
		return _page;
	}
	
	/**
	 * 功能描述:获取订单详情
	 * @author cqm  2017-8-10 上午09:55:45
	 * @param orderNum
	 * @param isRequired
	 * @return
	 */
	public TOrderInfo getOrderInfo(String orderNum,boolean isRequired){
		TOrderInfo orderInfo = get(TOrderInfo.class, "orderNum", orderNum);
		if(isRequired && orderInfo==null){
			throw new RuanYunException("订单信息不存在");
		}
		return orderInfo;
	}
	
	/**
	 * 功能描述: 生成充值订单
	 *
	 * @author chenqian  2017年01月11日 上午8:13:22
	 *
	 */
	public TOrderInfo saveOrderInfo(TUser user,BigDecimal actualPrice){
		 TOrderInfo orderInfo = new TOrderInfo();
		 orderInfo.setOrderStatus(Constants.ORDER_STATUS_1);
		 orderInfo.setOrderType(Constants.ORDER_TYPE_3);
		 orderInfo.setUserNum(user.getUserNum());
		 orderInfo.setOrderUserName(user.getNickName());
		 orderInfo.setOrderLoginName(user.getLoginName());
		 orderInfo.setOrderCreateTime(new Date());
	     orderInfo.setTotalCount(1);
	     orderInfo.setActualPrice(actualPrice);
	     save(orderInfo);

		String orderNum=NumUtils.getOrderNum(NumUtils.PIX_ORDER_INFO_CZ,DateUtils.doFormatDate(new Date(), "yyyyMMddHHmmss"),10);
		orderInfo.setOrderNum(orderNum);
		orderRecordService.saveOrderRecord(user, orderNum, orderInfo.getOrderStatus());

		return orderInfo;
		 
	}
	
	
	/**
	 * 功能描述:更新订单支付状态
	 *
	 * @author chenqian  2016-11-25 下午06:45:25
	 * 
	 * @param orderInfo
	 * @param user
	 * @param payMethod 支付方式(1会员卡支付  2支付宝 3微信 )
	 * @param outTradeNo 第三方流水编号
	 * @param payParamsMap 第三返回参数
	 * @return
	 */
	@Transactional
	public TOrderInfo updateOrderPayInfo(TOrderInfo orderInfo,TUser user,Integer payMethod,String outTradeNo,String payParamsMap){
		orderInfo.setPayParamsMap(payParamsMap);
		orderInfo.setPayMethod(payMethod); //更新支付方式
		orderInfo.setPayTime(new Date()); //更新支付时间
		orderInfo.setUpdateTime(new Date());//更新时间
		orderInfo.setPayThirtAccount(outTradeNo); //更新第三方流水号
		update(orderInfo);
		orderRecordService.saveOrderRecord(user, orderInfo.getOrderNum(), 2); //记录订单流水
		return orderInfo;
	}
	
	/**
	 * 功能描述:生成订单
	 * @author cqm  2017-8-17 下午03:20:05
	 * @param ordersJsonString 
	 */
	public TOrderInfo addOrderInfo(String ordersJsonString){
		System.out.println("====ordersJsonString===="+ordersJsonString);
		JSONObject ordersJson=JSONObject.fromObject(ordersJsonString);
		
		TOrderInfo orderInfo = new TOrderInfo();
		orderInfo.setOrderStatus(1);//默认未付款
		String userNum = ordersJson.getString("userNum");//下单人num
		orderInfo.setUserNum(userNum);
		
		TUser user = userService.getUserByUserNum(userNum, true);
		orderInfo.setOrderLoginName(user.getLoginName());
		orderInfo.setOrderUserName(user.getNickName());
		orderInfo.setOrderCreateTime(new Date());
		
		String serviceType = ordersJson.getString("serviceType");//1技师上门 2到店服务
		orderInfo.setServiceType(Integer.valueOf(serviceType));
		
		String arrivaTime = ordersJson.getString("arrivaTime");//接单时间
		orderInfo.setArrivaTime(TimeUtil.doFormatDate(arrivaTime, "yyyy-MM-dd HH:mm"));
		
		String totalCount = ordersJson.getString("totalCount");//订单数量
		orderInfo.setTotalCount(Integer.valueOf(totalCount));
		
		String mealInfoNum = ordersJson.getString("mealInfoNum");//套餐编号
		TMealInfo mealInfo = mealInfoService.getMealInfo(mealInfoNum, true);
		orderInfo.setSinglePrice(mealInfo.getMealPrice());
		
		String shopNum = ordersJson.getString("shopNum");
		orderInfo.setShopNum(shopNum);
		TTypeInfo typeInfo = typeInfoService.getTypeInfo(mealInfo.getTypeNum(), true);
		orderInfo.setShopTypeName(typeInfo.getTypeInfoName());
		
		String orderRemark = ordersJson.getString("orderRemark");//备注
		orderInfo.setOrderRemark(orderRemark);
//		String province = ordersJson.getString("province");//省
		
		String city = ordersJson.getString("city");//市
		TCity ci = cityService.get(TCity.class, "cityName", city);
		if(EmptyUtils.isNotEmpty(ci)){
		   orderInfo.setCity(ci.getCityCode());
		}
		String area = ordersJson.getString("area");//区域
		TAreas areas = areasService.get(TAreas.class, "area", area);
		if(EmptyUtils.isNotEmpty(areas)){
		    orderInfo.setArea(areas.getAreaid());
		}
		
		String orderLinkMan = ordersJson.getString("orderLinkMan");//联系人姓名
		orderInfo.setOrderLinkMan(orderLinkMan);
	
		String orderLongitude = ordersJson.getString("orderLongitude");//订单收货人的经度
		orderInfo.setOrderLongitude(orderLongitude);
		String orderLatitude = ordersJson.getString("orderLatitude");//订单收货人的纬度
		orderInfo.setOrderLatitude(orderLatitude);
//		String orderType = ordersJson.getString("orderType");//订单类型
		orderInfo.setOrderType(1);
		String price = ordersJson.getString("totalPrice");//支付金额
		BigDecimal totalPrice = new BigDecimal(price);
		orderInfo.setActualPrice(totalPrice);//支付金额字段
		orderInfo.setTotalPrice(totalPrice);
//		orderInfo.setUpdatePrice(totalPrice);
		String payType = ordersJson.getString("payType");//1 定金 2全款
		if(payType.equals("1")){
			orderInfo.setFreight(totalPrice);
		}
		
		String orderAddress = ordersJson.getString("orderAddress");//下单人地址
		orderInfo.setOrderAddress(orderAddress);
		String orderLinkTel = ordersJson.getString("orderLinkTel");//下单人联系方式
		orderInfo.setOrderLinkTel(orderLinkTel);
        save(orderInfo);
        String orderNum = NumUtils.getOrderNum(NumUtils.PIX_ORDER_INFO,DateUtils.doFormatDate(new Date(), "yyyyMMdd"),10);
        orderInfo.setOrderNum(orderNum);
        
        orderMealService.addOrderMeal(orderInfo, mealInfo);//生成订单套餐信息
        
       
		return orderInfo;
	}
	
	/**
	 * 功能描述:更新订单状态
	 * @author cqm  2017-8-21 下午04:05:21
	 * @param orderNum 订单编号
	 * @param orderStatus 订单状态
	 * @param cancelReason 取消原因
	 */
	public void updateOrderStatus(String orderNum,Integer orderStatus,String cancelReason){
		TOrderInfo orderInfo = getOrderInfo(orderNum, true);
		TUser user = userService.getUserByUserNum(orderInfo.getUserNum(), true);
		if(orderStatus==-1){//取消订单 商家取消订单扣全款
			TShopInfo shopInfo = shopInfoService.getShopInfo(orderInfo.getShopNum(), true);
			orderInfo.setCancelTime(new Date());//取消时间
			orderInfo.setReturnPrice(orderInfo.getActualPrice());//退还金额
			orderInfo.setCancelUserNum(shopInfo.getUserNum());//退还人userNum
			orderInfo.setCancelReason(cancelReason);//取消原因
			//平台扣去钱
			userCenterService.updateAccountBalance("SY00000000000001",orderInfo.getActualPrice().multiply(new BigDecimal(-1)), Constants.CONSUM_TYPE_1, orderInfo.getPayMethod(),  orderInfo.getOrderNum(), Constants.USER_TYPE_2,Constants.CONSUM_TYPE7_title,orderInfo.getPayMethod());
			
			//用户所得钱
			userCenterService.updateAccountBalance(orderInfo.getUserNum(),orderInfo.getActualPrice(), Constants.CONSUM_TYPE_1, orderInfo.getPayMethod(),  orderInfo.getOrderNum(), Constants.USER_TYPE_1,Constants.CONSUM_TYPE7_title,orderInfo.getPayMethod());
			
			/*//推送消息
			if(EmptyUtils.isNotEmpty(user.getRegistrationId())){
			   JpushClientUtil.sendToRegistrationId(user.getRegistrationId(), "您的订单已被取消", "您的订单已被取消", "很抱歉,由于工作太忙,您的订单已被取消,钱已全款退给您！！", 1, orderNum);
			}*/
		}else if(orderStatus==3){//接单
			orderInfo.setReceiveTime(new Date());//接单时间
			/*if(EmptyUtils.isNotEmpty(user.getRegistrationId())){
			   JpushClientUtil.sendToRegistrationId(user.getRegistrationId(), "您的订单已接单", "您的订单已接单", "您好,您的订单已被接单,请做好准备", 1, orderNum);
			}*/
		}
		orderInfo.setOrderStatus(orderStatus);
		update(orderInfo);
	}


	public TOrderInfo createOrderInfo(BigDecimal rechargeMoney, TUser curUser) {

		//生成订单
		TOrderInfo orderInfo =new TOrderInfo();
		//订单创建时间
		orderInfo.setOrderCreateTime(new Date());
		orderInfo.setActualPrice(rechargeMoney);
		orderInfo.setOrderLoginName(curUser.getLoginName());

		//因为该系统都是userid形式，而订单里没有userid选项，所以在user_num中方userid
		orderInfo.setUserNum(String.valueOf(curUser.getUserId()));


		//1 代付款 2已付款
		orderInfo.setOrderStatus(Constants.ORDER_STATUS_1);
		save(orderInfo);

		orderInfo.setOrderNum(NumUtils.getCommondNum(NumUtils.PIX_ORDER_INFO,orderInfo.getOrderId()));

		update(orderInfo);

		return orderInfo;
	}


	/**
	 * 功能描述: 根据充值成功回调，更新订单状态
	 * 创建者: zhangwei
	 * 创建时间: 2018/03/01 13:46
	 * @param orderNum 订单编号
	 */
	public Integer updateOrderInfo( String orderNum){

		TOrderInfo orderInfo=super.get(TOrderInfo.class,"orderNum",orderNum);

		logger.warn("orderNum======="+orderNum);

		TUser _user=userService.get(TUser.class,Integer.parseInt(orderInfo.getUserNum()));

		if(orderInfo.getOrderStatus()==Constants.ORDER_STATUS_1){

			orderInfo.setOrderStatus(Constants.ORDER_STATUS_2);
			orderInfo.setPayTime(new Date());
			update(orderInfo);

			logger.warn("--------getOrderStatus------------------"+orderInfo.getOrderStatus());

			//修改账户金额
			TUserAccount userAccount=userAccountService.updateAccount(_user.getUserId(),orderInfo.getActualPrice(), orderNum);
			logger.warn("money============"+userAccount.getMoney());
			return 1;
		}
		return  0;

	}
}









