package com.pay.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.pay.alipay.config.AlipayConfig;

import net.sf.json.JSONObject;

public class AliPayUtils {
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AliPayUtils.class);
	
	
	/**
	 * 功能描述: 格式化参数
	 *
	 * @author yangliu  2016年5月9日 上午9:30:15
	 * 
	 * @param request
	 * @return
	 */
	 public static Map<String, String> updateMap(HttpServletRequest request){
		 Map<String, String[]> map=request.getParameterMap();
		 Map<String, String> params=new HashMap<String, String>();
		 for (String key : map.keySet()) {  			 
			   String[] str=map.get(key);
			   if(str!=null && str.length>0)
				   params.put(key, str[0]);
		}
		 logger.error("==============="+JSONObject.fromObject(map).toString());
		 logger.error("==============="+JSONObject.fromObject(params).toString());
		 return params;
	}
	 
	 public static void main(String[] args) {
		 getPay("hahhah","0.01");
	}
	 /**
	  * 功能描述:JAVA服务端SDK生成APP支付订单信息
	  * @author cqm  2017-8-10 上午10:38:22
	  * @param orderNum
	  */
	public static String getPay(String orderNum,String price){
		//实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.partner, AlipayConfig.private_key, "json", AlipayConfig.input_charset,AlipayConfig.alipay_public_key, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("91到位支付支付");
		model.setSubject("91到位订单");
		model.setOutTradeNo(orderNum);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(price);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.IP_ADDRESS);
		try {
	        //这里和普通的接口调用不同，使用的是sdkExecute
	 		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
	        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
	        return response.getBody();
		} catch (AlipayApiException e) {
		    e.printStackTrace();
		}
		return "-1";
	}
	
	
	
}
