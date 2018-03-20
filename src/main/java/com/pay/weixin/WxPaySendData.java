package com.pay.weixin;

public class WxPaySendData {
	private String appid;//微信开放平台审核通过的应用APPID
	private String mch_id;//微信支付分配的商户号
	private String nonce_str;//随机字符串，不长于32位
	private String sign;
	private String body;
	private String out_trade_no;
	private int total_fee;
	private String spbill_create_ip;
	private String notify_url;
	private String trade_type;	
	private String attach;
//	private String device_info;
	
	public WxPaySendData(){		
	}
	public WxPaySendData(String appid, String attach, String body,
			String mchId, String nonceStr, String notifyUrl, String outTradeNo,
			double totalFee, String tradeType, String spbillCreateIp) {

		this.attach = attach;
		this.body = body;
		this.mch_id = mchId;
		this.nonce_str = nonceStr;
		this.notify_url = notifyUrl;
		this.out_trade_no = outTradeNo;
		this.total_fee = (int)(totalFee*100);
		this.trade_type = tradeType;
		this.spbill_create_ip = spbillCreateIp;
//		this.device_info = deviceInfo;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int totalFee) {
		total_fee = totalFee;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbillCreateIp) {
		spbill_create_ip = spbillCreateIp;
	}
//	public String getDevice_info() {
//		return device_info;
//	}
//	public void setDevice_info(String deviceInfo) {
//		device_info = deviceInfo;
//	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
