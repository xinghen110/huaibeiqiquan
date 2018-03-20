package com.pay.weixin;

public class WxPayReturnData {
	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String return_code;
	private String return_msg;
	private String trade_type;
	private String prepay_id;
	private String code_url;
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String resultCode) {
		result_code = resultCode;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String errCode) {
		err_code = errCode;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String errCodeDes) {
		err_code_des = errCodeDes;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepayId) {
		prepay_id = prepayId;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String codeUrl) {
		code_url = codeUrl;
	}
	
	
	
}
