package com.ruanyun.web.model.payeasy;

import java.math.BigDecimal;

/**
 * 标准支付请求参数
 */
public class StandardPaymentRequestEntity {


    private Integer      v_mid;
    private String       v_oid;
    private String       v_rcvname;
    private String       v_rcvaddr;
    private String       v_rcvtel;
    private String       v_rcvpost;
    private BigDecimal   v_amount;
    private String       v_ymd;
    private Integer      v_orderstatus;
    private String       v_ordername;
    private Integer      v_moneytype;
    private String       v_url;
    private String       v_md5info;
    private String       v_userref;
    private String       v_pmode;

    public StandardPaymentRequestEntity() {
    }

    public StandardPaymentRequestEntity(Integer v_mid, String v_oid, String v_rcvname, String v_rcvaddr, String v_rcvtel, String v_rcvpost, BigDecimal v_amount, String v_ymd, Integer v_orderstatus, String v_ordername, Integer v_moneytype, String v_url, String v_md5info, String v_userref, String v_pmode) {
        this.v_mid = v_mid;
        this.v_oid = v_oid;
        this.v_rcvname = v_rcvname;
        this.v_rcvaddr = v_rcvaddr;
        this.v_rcvtel = v_rcvtel;
        this.v_rcvpost = v_rcvpost;
        this.v_amount = v_amount;
        this.v_ymd = v_ymd;
        this.v_orderstatus = v_orderstatus;
        this.v_ordername = v_ordername;
        this.v_moneytype = v_moneytype;
        this.v_url = v_url;
        this.v_md5info = v_md5info;
        this.v_userref = v_userref;
        this.v_pmode = v_pmode;
    }

    public Integer getV_mid() {
        return v_mid;
    }

    public void setV_mid(Integer v_mid) {
        this.v_mid = v_mid;
    }

    public String getV_oid() {
        return v_oid;
    }

    public void setV_oid(String v_oid) {
        this.v_oid = v_oid;
    }

    public String getV_rcvname() {
        return v_rcvname;
    }

    public void setV_rcvname(String v_rcvname) {
        this.v_rcvname = v_rcvname;
    }

    public String getV_rcvaddr() {
        return v_rcvaddr;
    }

    public void setV_rcvaddr(String v_rcvaddr) {
        this.v_rcvaddr = v_rcvaddr;
    }

    public String getV_rcvtel() {
        return v_rcvtel;
    }

    public void setV_rcvtel(String v_rcvtel) {
        this.v_rcvtel = v_rcvtel;
    }

    public String getV_rcvpost() {
        return v_rcvpost;
    }

    public void setV_rcvpost(String v_rcvpost) {
        this.v_rcvpost = v_rcvpost;
    }

    public BigDecimal getV_amount() {
        return v_amount;
    }

    public void setV_amount(BigDecimal v_amount) {
        this.v_amount = v_amount;
    }

    public String getV_ymd() {
        return v_ymd;
    }

    public void setV_ymd(String v_ymd) {
        this.v_ymd = v_ymd;
    }

    public Integer getV_orderstatus() {
        return v_orderstatus;
    }

    public void setV_orderstatus(Integer v_orderstatus) {
        this.v_orderstatus = v_orderstatus;
    }

    public String getV_ordername() {
        return v_ordername;
    }

    public void setV_ordername(String v_ordername) {
        this.v_ordername = v_ordername;
    }

    public Integer getV_moneytype() {
        return v_moneytype;
    }

    public void setV_moneytype(Integer v_moneytype) {
        this.v_moneytype = v_moneytype;
    }

    public String getV_url() {
        return v_url;
    }

    public void setV_url(String v_url) {
        this.v_url = v_url;
    }

    public String getV_md5info() {
        return v_md5info;
    }

    public void setV_md5info(String v_md5info) {
        this.v_md5info = v_md5info;
    }

    public String getV_userref() {
        return v_userref;
    }

    public void setV_userref(String v_userref) {
        this.v_userref = v_userref;
    }

    public String getV_pmode() {
        return v_pmode;
    }

    public void setV_pmode(String v_pmode) {
        this.v_pmode = v_pmode;
    }
}
