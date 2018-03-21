package com.pay.yspay.bean;

/**
 * 银盛订单支付结果后台异步通知
 */
public class PayResult {
    private String sign_type;
    private String sign;
    private String notifu_type;
    private String notify_time;
    private String out_trade_no;
    private String total_amount;
    private String trade_no;
    private String trade_status;
    private String account_date;

    public PayResult() {
    }

    public PayResult(String sign_type, String sign, String notifu_type,
                     String notify_time, String out_trade_no, String total_amount,
                     String trade_no, String trade_status, String account_date) {
        this.sign_type = sign_type;
        this.sign = sign;
        this.notifu_type = notifu_type;
        this.notify_time = notify_time;
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.trade_no = trade_no;
        this.trade_status = trade_status;
        this.account_date = account_date;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNotifu_type() {
        return notifu_type;
    }

    public void setNotifu_type(String notifu_type) {
        this.notifu_type = notifu_type;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getAccount_date() {
        return account_date;
    }

    public void setAccount_date(String account_date) {
        this.account_date = account_date;
    }
}
