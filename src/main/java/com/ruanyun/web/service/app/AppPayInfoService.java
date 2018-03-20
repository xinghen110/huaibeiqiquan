package com.ruanyun.web.service.app;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pay.alipay.util.AlipayCore;
import com.pay.utils.AliPayUtils;
import com.pay.utils.WeiXinUtil;
import com.pay.weixin.WxPayReturnData;
import com.pay.weixin.WxPaySendData;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.jgpush.JpushShopUtil;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TAccountRecharge;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TOrderTrain;
import com.ruanyun.web.model.mall.TPayInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.UserMemberService;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.OrderTrainService;
import com.ruanyun.web.service.mall.PayInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.SmsInfoService;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.MessageUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import net.sf.json.JSONObject;

@Service
public class AppPayInfoService {

	 /**
     * 微信支付类型 
     */
    public String WEIXIN_TRADE_TYPE="APP";
    @Autowired
	private OrderInfoService orderInfoService; //订单类
    @Autowired
	private UserCenterService userCenterService; //账户类
    @Autowired
    private UserService userService;//用户类
    @Autowired
    private PayInfoService payInfoService; //支付账户信息配置

    @Autowired
	private OrderTrainService orderTrainService; //订单中间支付类
    @Autowired
    private ShopInfoService shopInfoService; 
    @Autowired
    private AccountRechargeService accountRechargeService; 
    
    @Autowired
    private UserMemberService userMemberService;
    @Autowired
    private SmsInfoService smsInfoService;
    
    /**
     * 功能描述:支付宝支付
     * @author cqm  2017-8-10 下午02:58:27
     * @param request
     * @param orderNum
     * @return
     */
	public AppCommonModel getAlipayPay(String orderNum) {
		AppCommonModel model=new AppCommonModel(-1,"支付失败");
		TOrderInfo orderInfo = orderInfoService.getOrderInfo(orderNum, true);
		System.out.println("====需要支付的钱====="+orderInfo.getActualPrice());
		String body = AliPayUtils.getPay(orderNum, orderInfo.getActualPrice().toString());
		if(body.equals("-1")){
			model.setMsg("支付失败");
			return model;
		}
				
		model.setMsg("支付成功");
		model.setResult(1);
		model.setObj(body);
		return model;
	}
	
   /**
     * 功能描述:账户余额支付
     * @author cqm  2017-8-18 上午08:44:07
     * @param userCenter
     * @param orderNum 订单编号
     * @param payMethod 支付方式
     * @param orderType 订单类型
     * @return
     * @throws Exception
     */
	@Transactional
	public AppCommonModel accountPay(TUserCenter userCenter,String orderNum,Integer payMethod,Integer orderType) throws Exception{
		TOrderInfo orderInfo = orderInfoService.getOrderInfo(orderNum, true);
		AppCommonModel appCommonModel = userCenterService.updateAccountBalance(userCenter, payMethod,orderNum,orderType,Constants.USER_TYPE_3,orderInfo.getActualPrice());
		if (appCommonModel.getResult() == 1) { //支付成功，进行业务处理
			if(orderInfo.getOrderType()==1 || orderInfo.getOrderType()==2){
				updatePayResultService(orderNum,payMethod, null,null);
			}
			/*if(orderInfo.getOrderType()==1){
				TShopInfo shopInfo = shopInfoService.getShopInfo(orderInfo.getShopNum(), true);
			    TUser shopUser = userService.getUserByUserNum(shopInfo.getUserNum(), true);
			    if(EmptyUtils.isNotEmpty(shopUser.getRegistrationId())){
			    	System.out.println("进来极光推送"+"===设备号==="+shopUser.getRegistrationId());
			       JpushShopUtil.sendToRegistrationId(shopUser.getRegistrationId(), "您有新的订单", "您有新的订单", "您好,您有新的订单,请做好准备", 1, orderNum);
			       TUser user =userService.get(TUser.class, "shopNum",orderInfo.getShopNum());
			       smsInfoService.saveSmsInfo(user.getUserNum(), "您有新的订单",orderInfo.getUserNum(),"XDDD");
			    }
			}*/
		}
		appCommonModel.setObj("{}");
		return appCommonModel;
	}
	
	 
	
	/**
	 * 功能描述: 支付完成后执行业务逻辑处理 (普通订单)
	 *
	 * @author chenqian  2016-11-9 上午11:36:10
	 * 
	 * @param orderNo 订单编号
	 * @param payMethod 支付方式(1会员卡支付  2支付宝 3微信 )
	 * @param outTradeNo 第三方流水号
	 * @param payParamsMap 第三方参数
	 * @return
	 * @throws ParseException 
	 */
	@Transactional
	public int updatePayResultService(String orderNum,Integer payMethod,String outTradeNo,String payParamsMap) throws ParseException{
			TOrderInfo orderInfo = orderInfoService.getOrderInfo(orderNum, false);
			if (EmptyUtils.isNotEmpty(orderInfo)) {
				System.out.println("进去支付");
				TUser user = userService.getUserByUserNum(orderInfo.getUserNum(), false);
				if(orderInfo.getOrderType()==Constants.ORDER_TYPE_3 || orderInfo.getOrderType()==Constants.ORDER_TYPE_2){   //充值订单
					orderInfo.setOrderStatus(Constants.ORDER_STATUS_6); //支付成功交易成功
				}else{
					orderInfo.setOrderStatus(Constants.ORDER_STATUS_2); //支付成功待发货状态
				}
				String payThirtAccount="";//第三方账号
				if(payMethod==Constants.PAY_TYPE_2)
					payThirtAccount=outTradeNo;
				if(payMethod==Constants.PAY_TYPE_3)
					payThirtAccount=orderNum;
				orderInfoService.updateOrderPayInfo(orderInfo, user, payMethod, payThirtAccount,payParamsMap);
				if(orderInfo.getOrderType()==Constants.ORDER_TYPE_2){ //会员充值订单
					//生成会员信息
					userMemberService.addUserMember(orderInfo.getUserNum(), orderInfo.getTotalCount(),orderInfo.getFalg1());
					//更新账户余额
					userCenterService.updateAccountBalance(orderInfo.getUserNum(),orderInfo.getActualPrice().multiply(new BigDecimal(-1)), Constants.CONSUM_TYPE_1, payMethod,  orderInfo.getOrderNum(), user.getUserType(),Constants.CONSUM_TYPE10_title,orderInfo.getPayMethod());
				}else if(orderInfo.getOrderType()==Constants.ORDER_TYPE_3){  //充值订单
					userCenterService.updateAccountBalance(orderInfo.getUserNum(),orderInfo.getActualPrice(), Constants.CONSUM_TYPE_3, payMethod,  orderInfo.getOrderNum(), user.getUserType(),Constants.CONSUM_TYPE3_title,0);
					TAccountRecharge accountRecharge = accountRechargeService.get(TAccountRecharge.class, "rechargeMealNum", orderInfo.getOrderNum());//获取充值信息
					if(EmptyUtils.isNotEmpty(accountRecharge.getScore()))    //如果充值里积分，则加入积分
					userCenterService.updateScoreBalance(orderInfo.getUserNum(),new BigDecimal(Double.parseDouble(accountRecharge.getScore()+"")).doubleValue(),Constants.CONSUM_TYPE_5, orderInfo.getPayMethod(),orderInfo.getOrderNum(),user.getUserType(),Constants.CONSUM_TYPE5_title);
				}else{
					userCenterService.updateAccountBalance(orderInfo.getUserNum(),orderInfo.getActualPrice().multiply(new BigDecimal(-1)), Constants.CONSUM_TYPE_1, payMethod,  orderInfo.getOrderNum(), user.getUserType(),Constants.CONSUM_TYPE1_title,orderInfo.getPayMethod());
				}
				if(orderInfo.getOrderType()==1){
					TShopInfo shopInfo = shopInfoService.getShopInfo(orderInfo.getShopNum(), true);
				    TUser shopUser = userService.getUserByUserNum(shopInfo.getUserNum(), true);
				    if(EmptyUtils.isNotEmpty(shopUser.getRegistrationId())){
				    	System.out.println("进来极光推送"+"===设备号==="+shopUser.getRegistrationId());
				       JpushShopUtil.sendToRegistrationId(shopUser.getRegistrationId(), "您有新的订单", "您有新的订单", "您好,您有新的订单,请做好准备", 1, orderNum);
				       TUser user1 =userService.get(TUser.class, "shopNum",orderInfo.getShopNum());
				       smsInfoService.saveSmsInfo(user1.getUserNum(), "您有新的订单",orderInfo.getUserNum(),"XDDD");
				    }
				}
				
			}
		
				
		
	return 1;

}
	
	 
	/**
	 * 功能描述:微信预付款
	 *
	 * @author yangliu  2016年5月9日 上午11:11:06
	 * @param request
	 * @param wxPaySendData
	 * @param model
	 * @param accountDetailCode
	 * @return
	 */
//	@Transactional
//	public AppCommonModel weixinDoPay(HttpServletRequest request,String outTrainOrder) {
//		AppCommonModel appCommonModel=new AppCommonModel(-1,"支付失败");
//		WxPaySendData wxPaySendData= new WxPaySendData();
//		//TOrderTrain bean = orderTrainService.getOrderTrainInfo(outTrainOrder, false);
//		TOrderInfo orderInfo = orderInfoService.getOrderInfo(outTrainOrder, true);
//		if (EmptyUtils.isNotEmpty(orderInfo) && EmptyUtils.isNotEmpty(orderInfo.getTotalPrice())) {
//		 
//			wxPaySendData.setAppid(WeiXinUtil.WEIXIN_APP_ID); // 微信开放平台核通过的应用APPID
//			wxPaySendData.setAttach("91到位"); // 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
//			wxPaySendData.setBody("91到位APP支付-订单编号："+outTrainOrder); //商品或支付单简要描述
//			wxPaySendData.setMch_id(WeiXinUtil.WEIXIN_MCH_ID); // 微信支付分配的商户号
//			wxPaySendData.setNonce_str(WeiXinUtil.getNonceStr()); // 随机字符串，不长于32位
//		    String basePath =request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
//			wxPaySendData.setNotify_url(basePath+"app/payinfo/weixinReturnUrl");// 回调地址
//			wxPaySendData.setTotal_fee((orderInfo.getTotalPrice().multiply(BigDecimal.valueOf(100))).intValue());
//			wxPaySendData.setSpbill_create_ip(WeiXinUtil.getClientIp(request));
//			wxPaySendData.setOut_trade_no(orderInfo.getOrderNum()); // 商户订单号
//			wxPaySendData.setTrade_type(WEIXIN_TRADE_TYPE); // 支付类型
//			// 请求
//			String returnXml = WeiXinUtil.unifiedOrder(wxPaySendData,WeiXinUtil.WEIXIN_PAY_KEY);
//			WxPayReturnData reData = new WxPayReturnData();
//			XStream xs1 = new XStream(new DomDriver());
//			xs1.alias("xml", WxPayReturnData.class);
//			reData = (WxPayReturnData) xs1.fromXML(returnXml);
//			if (reData.getReturn_code().equals("SUCCESS")
//					&& reData.getResult_code().equals("SUCCESS")) {
//				String timeStamp = WeiXinUtil.getTimeStamp(); // 时间戳
//				String _package = "prepay_id=" + reData.getPrepay_id();
//				SortedMap<Object, Object> signMap = new TreeMap<Object, Object>();
//				signMap.put("appid", reData.getAppid());
//				signMap.put("timestamp", timeStamp);
//				signMap.put("noncestr", reData.getNonce_str());
//				signMap.put("package", _package);
//				signMap.put("signtype", "MD5");
//				String paySign = WeiXinUtil.createSign(signMap,WeiXinUtil.WEIXIN_PAY_KEY);		
//				wxPaySendData.setSign(paySign);
//				SortedMap<Object, Object> iosSignMap = new TreeMap<Object, Object>();
//				iosSignMap.put("appid", wxPaySendData.getAppid());
//				iosSignMap.put("partnerid", wxPaySendData.getMch_id());
//				iosSignMap.put("prepayid", reData.getPrepay_id());
//				iosSignMap.put("package", "Sign=WXPay");
//				iosSignMap.put("noncestr", wxPaySendData.getNonce_str());
//				iosSignMap.put("timestamp", timeStamp);
//				String iosSign = WeiXinUtil.createSign(iosSignMap,WeiXinUtil.WEIXIN_PAY_KEY); // IOS端需要的SIGN
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("timestamp", timeStamp);
//				map.put("prepay_id", reData.getPrepay_id());
//				map.put("sign", iosSign);
//				map.put("nonce_str", wxPaySendData.getNonce_str());	
//				map.put("package", _package);
//				map.put("partnerid", wxPaySendData.getMch_id());
//				appCommonModel.setResult(1);
//				appCommonModel.setMsg("提交成功");
//				appCommonModel.setObj(map);
//			}
//		}
//		return appCommonModel;
//	}
	
	@Transactional
	public AppCommonModel weixinDoPay(HttpServletRequest request,String orderNum) {
		AppCommonModel appCommonModel=new AppCommonModel(-1,"支付失败");
		WxPaySendData wxPaySendData= new WxPaySendData();
		TOrderInfo orderInfo = orderInfoService.getOrderInfo(orderNum, false);
		if (EmptyUtils.isNotEmpty(orderInfo) && EmptyUtils.isNotEmpty(orderInfo.getActualPrice())) {
			wxPaySendData.setAppid(WeiXinUtil.WEIXIN_APP_ID); // 微信开放平台核通过的应用APPID
			wxPaySendData.setAttach("111"); // 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
			wxPaySendData.setBody("222："+orderNum); //商品或支付单简要描述
			wxPaySendData.setMch_id(WeiXinUtil.WEIXIN_MCH_ID); // 微信支付分配的商户号
			wxPaySendData.setNonce_str(WeiXinUtil.getNonceStr()); // 随机字符串，不长于32位
//			wxPaySendData.setNotify_url("http://15f9p37480.imwork.net/czy/app/pay/weixinReturnUrl");// 回调地址
		    String basePath =request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			wxPaySendData.setNotify_url(basePath+"app/pay/weixinReturnUrl");// 回调地址
			wxPaySendData.setTotal_fee(1);
			wxPaySendData.setSpbill_create_ip(WeiXinUtil.getClientIp(request));
			wxPaySendData.setOut_trade_no(orderNum); // 商户订单号
			wxPaySendData.setTrade_type(WEIXIN_TRADE_TYPE); // 支付类型
			// 请求
			String returnXml = WeiXinUtil.unifiedOrder(wxPaySendData,
					WeiXinUtil.WEIXIN_PAY_KEY);
			WxPayReturnData reData = new WxPayReturnData();
			XStream xs1 = new XStream(new DomDriver());
			xs1.alias("xml", WxPayReturnData.class);
			reData = (WxPayReturnData) xs1.fromXML(returnXml);
			if (reData.getReturn_code().equals("SUCCESS")
					&& reData.getResult_code().equals("SUCCESS")) {
				String timeStamp = WeiXinUtil.getTimeStamp(); // 时间戳
				String _package = "prepay_id=" + reData.getPrepay_id();
				SortedMap<Object, Object> signMap = new TreeMap<Object, Object>();
				signMap.put("appid", reData.getAppid());
				signMap.put("timestamp", timeStamp);
				signMap.put("noncestr", reData.getNonce_str());
				signMap.put("package", _package);
				signMap.put("signtype", "MD5");
				String paySign = WeiXinUtil.createSign(signMap,WeiXinUtil.WEIXIN_PAY_KEY);		
				wxPaySendData.setSign(paySign);
				SortedMap<Object, Object> iosSignMap = new TreeMap<Object, Object>();
				iosSignMap.put("appid", wxPaySendData.getAppid());
				iosSignMap.put("partnerid", wxPaySendData.getMch_id());
				iosSignMap.put("prepayid", reData.getPrepay_id());
				iosSignMap.put("package", "Sign=WXPay");
				iosSignMap.put("noncestr", wxPaySendData.getNonce_str());
				iosSignMap.put("timestamp", timeStamp);
				String iosSign = WeiXinUtil.createSign(iosSignMap,WeiXinUtil.WEIXIN_PAY_KEY); // IOS端需要的SIGN
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("timestamp", timeStamp);
				map.put("prepay_id", reData.getPrepay_id());
				map.put("sign", iosSign);
				map.put("nonce_str", wxPaySendData.getNonce_str());	
				map.put("package", _package);
				map.put("partnerid", wxPaySendData.getMch_id());
				appCommonModel.setResult(1);
				appCommonModel.setMsg("提交成功");
				appCommonModel.setObj(map);
			}
		}
		return appCommonModel;
	}
	
	/**
	 * 功能描述:微信支付结果通知
	 *
	 * @author yangliu  2016年5月9日 上午11:48:16
	 * 
	 * @param request
	 * @throws Exception
	 */
	@Transactional
	public int weixinPayResult(HttpServletRequest request) throws Exception{
		Map<String, String> requestMap = MessageUtil.parseXml(request);
		System.out.println("requestMap:"+requestMap.toString());
		if (requestMap.get("result_code").equals("SUCCESS")) {  //支付成功
			String outTradeNo = requestMap.get("out_trade_no");   //订单编号
			String transactionId = requestMap.get("transaction_id"); // 微信中的订单号
			String openid=requestMap.get("openid");// 用户唯一标识
			updatePayResultService(outTradeNo, Constants.PAY_TYPE_3, transactionId, requestMap.toString());  //普通订单，支付订单
				
		}
		return 1;
	}
	
	 
	 /**
     * 功能描述:获取支付配置信息
     *
     * @author yanzy  2016-10-14 下午06:47:08
     * 
     * @param userNum
     * @return
     */
    public TPayInfo getPayInfo(Integer payType){
    	return payInfoService.getPayInfo(payType);
    }
    
	/**
	 * 功能描述: 支付宝支付通知接口
	 *
	 * @author yangliu  2016年5月9日 上午9:55:02
	 * 
	 * @param request 请求参数
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@Transactional
	public int zhifubaoPayResult(HttpServletRequest request) throws NumberFormatException, Exception{	
		Map<String, String>paramsMap =AlipayCore.updateMap(request);
		String payParamsMap = JSONObject.fromObject(paramsMap).toString();
		System.out.println("--------------------"+JSONObject.fromObject(paramsMap).toString());
		String outTradeNo=paramsMap.get("out_trade_no");    //订单流水号
		//String alipayNum=paramsMap.get("buyer_email");    //购买账号
		String orderNo=paramsMap.get("trade_no");         // 支付宝账号
		String tradeStatus=paramsMap.get("trade_status"); //付款状态			
		boolean isVerify=true;//AlipayNotify.verify(paramsMap);       //验证参数
		System.out.println("isVerify:"+isVerify);
		if (isVerify==true && "TRADE_SUCCESS".equalsIgnoreCase(tradeStatus)) {//支付成功，进行业务处理
			return updatePayResultService(outTradeNo,Constants.PAY_TYPE_2,orderNo,payParamsMap); //普通订单，支付订单
			
	
		}
		return -1;
	}
}
