package com.pay.weixin;

public class WxRefundData {
	private String appid;//微信开放平台审核通过的应用APPID
	private String mch_id;//微信支付分配的商户号
	private String nonce_str;//随机字符串，不长于32位
	private String sign;
	private String out_trade_no;
	private String out_refund_no;
	private int total_fee;
	private int refund_fee;
	private String op_user_id;	
	
	public WxRefundData(){		
	}

	public WxRefundData(String appid,String mch_id,String nonce_str,String out_trade_no,String out_refund_no,Integer total_fee,Integer refund_fee,String op_user_id){
		this.appid = appid;
		this.mch_id = mch_id;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.out_refund_no = out_refund_no;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.refund_fee = refund_fee;
		this.op_user_id = op_user_id;
	}
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

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String outRefundNo) {
		out_refund_no = outRefundNo;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int totalFee) {
		total_fee = totalFee;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refundFee) {
		refund_fee = refundFee;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String opUserId) {
		op_user_id = opUserId;
	}

}
