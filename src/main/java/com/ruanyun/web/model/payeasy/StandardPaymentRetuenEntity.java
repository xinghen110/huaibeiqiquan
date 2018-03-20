package com.ruanyun.web.model.payeasy;

/**
 * 订单支付结果页面返回
 */
public class StandardPaymentRetuenEntity {

    private String v_url;
    private String v_oid;
    private String v_pmode;
    private String v_pstatus;
    private String v_pstring;
    private String v_md5info;
    private String v_amount;
    private String v_moneytype;
    private String v_md5money;
    private String v_sign;

    public StandardPaymentRetuenEntity() {
    }

    public StandardPaymentRetuenEntity(String v_url, String v_oid, String v_pmode, String v_pstatus, String v_pstring, String v_md5info, String v_amount, String v_moneytype, String v_md5money, String v_sign) {
        this.v_url = v_url;
        this.v_oid = v_oid;
        this.v_pmode = v_pmode;
        this.v_pstatus = v_pstatus;
        this.v_pstring = v_pstring;
        this.v_md5info = v_md5info;
        this.v_amount = v_amount;
        this.v_moneytype = v_moneytype;
        this.v_md5money = v_md5money;
        this.v_sign = v_sign;
    }

    public String getV_url() {
        return v_url;
    }

    public void setV_url(String v_url) {
        this.v_url = v_url;
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

    public String getV_md5info() {
        return v_md5info;
    }

    public void setV_md5info(String v_md5info) {
        this.v_md5info = v_md5info;
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

    public String getV_sign() {
        return v_sign;
    }

    public void setV_sign(String v_sign) {
        this.v_sign = v_sign;
    }
}
