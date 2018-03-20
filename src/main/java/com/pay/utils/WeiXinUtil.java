package com.pay.utils;


import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.weixin.WxPayReturnData;
import com.pay.weixin.WxPaySendData;
import com.pay.weixin.WxRefundData;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.util.HttpRequestUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 
 *  #(c) IFlytek mangguo <br/>
 *
 *  版本说明: $id:$ <br/>
 *
 *  功能说明: 公共平台接口类，请求菜单
 *   
 * 
 *  <br/>创建说明: 2014-3-24 下午03:41:40 L H T  创建文件<br/>
 * 
 *  修改历史:<br/>
 *
 */
public class WeiXinUtil {
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);
	//信息已是正式的
	//protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	public static String WEIXIN_APP_ID="wxe49ae3b61092042d";  //微信开放平台审核通过的应用APPID（真实的）
	public static String WEIXIN_MCH_ID="1489027432";   //微信分配的商户号
	public static String WEIXIN_PAY_KEY="698adcde98opqcdr7268091898xd2287";  //商户平台api秘钥
    /**
     * 功能描述:微信支付第一步——统一下单
     *
     * @author yanzy  2016-1-18 下午10:01:20
     * 
     * @param data
     * @param key
     * @return
     */
//    public static String unifiedOrder(WxPaySendData data,String key){
//        //统一下单支付
//        String returnXml = null;
//        try {
//            //生成sign签名
//            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
//            parameters.put("appid", data.getAppid()); 
//            parameters.put("attach", data.getAttach());
//            parameters.put("body", data.getBody());
//            parameters.put("mch_id", data.getMch_id());
//            parameters.put("nonce_str", data.getNonce_str());
//            parameters.put("notify_url", data.getNotify_url());
//           // parameters.put("openid", data.getOpenid());
//            parameters.put("out_trade_no", data.getOut_trade_no());
//            parameters.put("spbill_create_ip", "127.0.0.1");//data.getSpbill_create_ip()
//            parameters.put("total_fee", data.getTotal_fee());
//            parameters.put("trade_type", data.getTrade_type());
////            parameters.put("device_info", data.getDevice_info());
//            log.info("SIGN:"+WeiXinUtil.createSign(parameters,key));
//            data.setSign(WeiXinUtil.createSign(parameters,key));
//            
//            XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
//            xs.alias("xml", WxPaySendData.class);
//            String xml = xs.toXML(data);
//            log.info("统一下单xml为:\n" + xml);
//            returnXml = HttpRequestUtil.sendPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
//            log.info("返回结果:" + returnXml);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } 
//        return returnXml;
//    }
	
	
	public static String unifiedOrder(WxPaySendData data,String key){
        //统一下单支付
        String returnXml = null;
        try {
            //生成sign签名
            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
            parameters.put("appid", data.getAppid()); 
            parameters.put("attach", data.getAttach());
            parameters.put("body", data.getBody());
            parameters.put("mch_id", data.getMch_id());
            parameters.put("nonce_str", data.getNonce_str());
            parameters.put("notify_url", data.getNotify_url());
           // parameters.put("openid", data.getOpenid());
            parameters.put("out_trade_no", data.getOut_trade_no());
            parameters.put("spbill_create_ip", "127.0.0.1"); //data.getSpbill_create_ip()
            parameters.put("total_fee", data.getTotal_fee());
            parameters.put("trade_type", data.getTrade_type());
//            parameters.put("device_info", data.getDevice_info());
            log.info("SIGN:"+WeiXinUtil.createSign(parameters,key));
            data.setSign(WeiXinUtil.createSign(parameters,key));
            
            XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
            xs.alias("xml", WxPaySendData.class);
            String xml = xs.toXML(data);
            log.info("统一下单xml为:\n" + xml);
            returnXml = HttpRequestUtil.sendPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
            log.info("返回结果:" + returnXml);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return returnXml;
    }
    
    /**
     * 功能描述:微信退款
     *
     * @author yanzy  2016-10-25 下午01:52:19
     * 
     * @param data
     * @param key
     * @return
     */
    public static String refund(WxRefundData data,String key){
        String returnXml = null;
        try {
            //生成sign签名
            SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
            parameters.put("appid", data.getAppid()); 
            parameters.put("mch_id", data.getMch_id());
            parameters.put("nonce_str", data.getNonce_str());
            parameters.put("out_trade_no", data.getOut_trade_no());
            parameters.put("out_refund_no", data.getOut_refund_no());
            parameters.put("total_fee", data.getTotal_fee());
            parameters.put("refund_fee", data.getRefund_fee());
            parameters.put("op_user_id", data.getOp_user_id());
            log.info("SIGN:"+WeiXinUtil.createSign(parameters,key));
            data.setSign(WeiXinUtil.createSign(parameters,key));
            
            XStream xs = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_", "_")));
            xs.alias("xml", WxRefundData.class);
            String xml = xs.toXML(data);
            log.info("微信退款xml为:\n" + xml);
            returnXml = HttpRequestUtil.sendCertPostXml("https://api.mch.weixin.qq.com/secapi/pay/refund", data.getMch_id(), xml);
            log.info("返回结果:" + returnXml);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return returnXml;
    }
    
    private static String characterEncoding = "UTF-8";
    
//    @SuppressWarnings("rawtypes")
//    public static String createSign(SortedMap<Object,Object> parameters,String key){  
//        StringBuffer sb = new StringBuffer();  
//        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
//        Iterator it = es.iterator();  
//        while(it.hasNext()) {  
//            Map.Entry entry = (Map.Entry)it.next();  
//            String k = (String)entry.getKey();  
//            Object v = entry.getValue();  
//            if(null != v && !"".equals(v)   
//                    && !"sign".equals(k) && !"key".equals(k)) {  
//                sb.append(k + "=" + v + "&");  
//            }  
//        }  
//        sb.append("key=" + key);
//        System.out.println("accsii排序:"+sb.toString());
//        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
//        return sign;  
//    }
    
    @SuppressWarnings("rawtypes")
    public static String createSign(SortedMap<Object,Object> parameters,String key){  
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)   
                    && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + key);
        System.out.println("accsii排序:"+sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
        return sign;  
    }
    
      
    /**
     * 功能描述:生成随机字符串
     *
     * @author yanzy  2016-1-18 下午07:11:35
     * 
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }
  
    /**
     * 功能描述:时间戳
     *
     * @author yanzy  2016-1-18 下午07:11:53
     * 
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 功能描述: 获取客户端IP
     *
     * @author yanzy  2016-1-18 下午08:19:45
     * 
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip; 
    } 
}
