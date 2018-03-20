package com.ruanyun.web.model.bank;

import java.math.BigDecimal;

public class BankGatewayPaymentRequest {
    public String mchNo;
    public String mchType;
    public String payChannel;
    public String payChannelTypeNo;
    public String bankCode;
    public String orderNo;
    public BigDecimal amount;
    public String goodsName;
    public String goodsDesc;
    public String timeStamp;
    public String sign;

    public BankGatewayPaymentRequest() {
    }

    public BankGatewayPaymentRequest(String mchNo, String mchType, String payChannel, String payChannelTypeNo, String bankCode, String orderNo, BigDecimal amount, String goodsName, String goodsDesc, String timeStamp, String sign) {
        this.mchNo = mchNo;
        this.mchType = mchType;
        this.payChannel = payChannel;
        this.payChannelTypeNo = payChannelTypeNo;
        this.bankCode = bankCode;
        this.orderNo = orderNo;
        this.amount = amount;
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.timeStamp = timeStamp;
        this.sign = sign;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getMchType() {
        return mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayChannelTypeNo() {
        return payChannelTypeNo;
    }

    public void setPayChannelTypeNo(String payChannelTypeNo) {
        this.payChannelTypeNo = payChannelTypeNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
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
}
