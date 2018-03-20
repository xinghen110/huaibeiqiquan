package com.ruanyun.web.model.bank;

import java.math.BigDecimal;

public class BankGatewayPaymentReturn {
    public String mchNo;
    public String orderNo;
    public String gwTradeNo;
    public String tradeNo3rd;
    public BigDecimal amount;
    public String status;
    public String timeStamp;
    public String sign;
    public String result;
    public String msg;

    public BankGatewayPaymentReturn() {
    }

    public BankGatewayPaymentReturn(String mchNo, String orderNo, String gwTradeNo, String tradeNo3rd, BigDecimal amount, String status, String timeStamp, String sign, String result, String msg) {
        this.mchNo = mchNo;
        this.orderNo = orderNo;
        this.gwTradeNo = gwTradeNo;
        this.tradeNo3rd = tradeNo3rd;
        this.amount = amount;
        this.status = status;
        this.timeStamp = timeStamp;
        this.sign = sign;
        this.result = result;
        this.msg = msg;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGwTradeNo() {
        return gwTradeNo;
    }

    public void setGwTradeNo(String gwTradeNo) {
        this.gwTradeNo = gwTradeNo;
    }

    public String getTradeNo3rd() {
        return tradeNo3rd;
    }

    public void setTradeNo3rd(String tradeNo3rd) {
        this.tradeNo3rd = tradeNo3rd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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
}
