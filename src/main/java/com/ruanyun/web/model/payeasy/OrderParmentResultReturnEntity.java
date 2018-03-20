package com.ruanyun.web.model.payeasy;

/**
 * 订单支付结果后台返回
 */
public class OrderParmentResultReturnEntity {

    public Integer v_count;
    public String  v_oid;
    public String  v_pmode;
    public String  v_pstatus;
    public String  v_pstring;
    public String  v_amount;
    public String  v_moneytype;
    public String  v_md5money;
    public String  v_mac;
    public String  v_sign;

    public OrderParmentResultReturnEntity() {
    }

    public OrderParmentResultReturnEntity(Integer v_count, String v_oid, String v_pmode, String v_pstatus, String v_pstring, String v_amount, String v_moneytype, String v_md5money, String v_mac, String v_sign) {
        this.v_count = v_count;
        this.v_oid = v_oid;
        this.v_pmode = v_pmode;
        this.v_pstatus = v_pstatus;
        this.v_pstring = v_pstring;
        this.v_amount = v_amount;
        this.v_moneytype = v_moneytype;
        this.v_md5money = v_md5money;
        this.v_mac = v_mac;
        this.v_sign = v_sign;
    }

    public Integer getV_count() {
        return v_count;
    }

    public void setV_count(Integer v_count) {
        this.v_count = v_count;
    }

    public String getV_oid() {
        return v_oid;
    }

    public void setV_oid(String v_oid) {
        this.v_oid = v_oid;
    }

    public String getV_pmode() {
        return v_pmode;
    }

    public void setV_pmode(String v_pmode) {
        this.v_pmode = v_pmode;
    }

    public String getV_pstatus() {
        return v_pstatus;
    }

    public void setV_pstatus(String v_pstatus) {
        this.v_pstatus = v_pstatus;
    }

    public String getV_pstring() {
        return v_pstring;
    }

    public void setV_pstring(String v_pstring) {
        this.v_pstring = v_pstring;
    }

    public String getV_amount() {
        return v_amount;
    }

    public void setV_amount(String v_amount) {
        this.v_amount = v_amount;
    }

    public String getV_moneytype() {
        return v_moneytype;
    }

    public void setV_moneytype(String v_moneytype) {
        this.v_moneytype = v_moneytype;
    }

    public String getV_md5money() {
        return v_md5money;
    }

    public void setV_md5money(String v_md5money) {
        this.v_md5money = v_md5money;
    }

    public String getV_mac() {
        return v_mac;
    }

    public void setV_mac(String v_mac) {
        this.v_mac = v_mac;
    }

    public String getV_sign() {
        return v_sign;
    }

    public void setV_sign(String v_sign) {
        this.v_sign = v_sign;
    }
}
