package com.ruanyun.web.model;

public class PaymentNotifyModel {

    /**
     * {
     * "timeStamp": "20171209115845",
     * "extend": "",
     * "result": "SUCCESS",
     * "msg": "支付成功|,|goodsName:银行网关测试|,|goodsDesc:商品描述",
     * "amount": "0.01",
     * "orderNo": "test1512791689",
     * "sign": "05c581cec781a0e97f106f34438784d6",
     * "tradeNo3rd": "",
     * "gwTradeNo": "2017120911570401737784217",
     * "mchNo": "MER1000021",
     * "status": "1"
     * }
     */

    String timeStamp;
    String extend;
    String result;
    String msg;
    String amount;
    String orderNo;
    String sign;
    String tradeNo3rd;
    String gwTradeNo;
    String mchNo;
    String status;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeNo3rd() {
        return tradeNo3rd;
    }

    public void setTradeNo3rd(String tradeNo3rd) {
        this.tradeNo3rd = tradeNo3rd;
    }

    public String getGwTradeNo() {
        return gwTradeNo;
    }

    public void setGwTradeNo(String gwTradeNo) {
        this.gwTradeNo = gwTradeNo;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
