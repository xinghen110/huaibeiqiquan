package com.pay.yspay.bean;


import com.pay.yspay.utils.Constants;
import com.pay.yspay.utils.DateUtil;

import java.io.Serializable;

/**
 * 银盛支付接口所有业务请求参数
 *
 * @author chang
 */
@SuppressWarnings("unused")
public class PayOrder implements Serializable {

    //---------------生成支付订单号 开始------------------------
    //14位 当前时间 yyyyMMddHHmmss
    Constants constants = new Constants();

    String currTime = DateUtil.getCurrentDate("yyyyMMddHHmmss");
    //8位日期
    String strTime = currTime.substring(8, currTime.length());
    //四位随机数
    String strRandom = DateUtil.getRandom(4) + "";
    //10位序列号,可以自行调整。
    String strReq = strTime + strRandom;

    private static final long serialVersionUID = 1L;

    /**
     * 接口名称
     */
    private String method = "ysepay.online.directpay.createbyuser";
    /**
     * 商户号
     */
    private String partner_id = constants.getPARTNER_ID();
    /**
     * 起始时间
     */
    java.util.Date now = new java.util.Date();
    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateNow = format.format(now);
    private String timestamp = dateNow;
    /**
     * 编码
     */
    private String charset = constants.getDEFAULT_CHARSET();
    /**
     * 签名类型
     */
    private String sign_type = constants.getSIGN_ALGORITHM();
    /**
     * 异步通知地址
     */
    private String notify_url = constants.getNOTIFY_URL();
    /**
     * 通知页面
     */
    private String return_url = constants.getRETURN_URL();
    /**
     * 版本号
     */
    private String version = constants.getVERSION();
    /**
     * 业务参数
     */
    private String biz_content;
    /**
     * 订单号
     */
    //订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
    String orderid = currTime + strReq + strRandom;
    private String out_trade_no = orderid;
    /**
     * 流水号
     */
    private String trade_no;
    /**
     * 商品描述
     */
    private String subject;
    /**
     * 总金额
     */
    private Double total_amount;
    /**
     * 退款请求订单号
     */
    private String out_request_no;
    /**
     * 商户号
     */
    private String seller_id = constants.getPARTNER_ID();
    /**
     * 商户名称
     */
    private String seller_name = constants.getSELLER_NAME();
    /**
     * 未付款时间
     */
    private String timeout_express = "96h";
    /**
     * 支付模式
     */
    private String pay_mode = "internetbank";
    /**
     * 银行类型
     */
    private String bank_type;
    /**
     * 银行帐户类型
     */
    private String bank_account_type;
    /**
     * 银行卡类型
     */
    private String support_card_type = "debit";
    /**
     * 信息扩展
     */
    private String extend_params;
    /**
     * 业务代码
     */
    private String business_code = "3010001";
    /**
     * 公共回传参数
     */
    private String extra_common_param;

    /**
     * 商户ID
     */
    private String mer_outside_custid;

    /**
     * 银行帐号
     */
    private String bank_account_no;

    /**
     * 姓名
     */
    private String fast_pay_name;

    /**
     * 证件号
     */
    private String fast_pay_id_no;

    /**
     * 二级商户信息
     */
    private String sub_merchant;

    public String getMer_outside_custid() {
        return mer_outside_custid;
    }

    /**
     * 商户用户id
     *
     */
    public void setMer_outside_custid(String mer_outside_custid) {
        this.mer_outside_custid = mer_outside_custid;
    }

    public String getBank_account_no() {
        return bank_account_no;
    }

    /**
     * 银行帐号 当pay_mode为fastpay时必填
     *
     */
    public void setBank_account_no(String bank_account_no) {
        this.bank_account_no = bank_account_no;
    }

    public String getFast_pay_name() {
        return fast_pay_name;
    }

    /**
     * 姓名， 当pay_mode为fastpay时必填
     *
     */
    public void setFast_pay_name(String fast_pay_name) {
        this.fast_pay_name = fast_pay_name;
    }

    public String getFast_pay_id_no() {
        return fast_pay_id_no;
    }

    /**
     * 证件号，当pay_mode为fastpay时必填
     *
     */
    public void setFast_pay_id_no(String fast_pay_id_no) {
        this.fast_pay_id_no = fast_pay_id_no;
    }

    public String getSub_merchant() {
        return sub_merchant;
    }

    /**
     * 二级商户信息, Json格式，暂包括merName、merShortName、merAddr、mobileNo、merNo、category
     *
     */
    public void setSub_merchant(String sub_merchant) {
        this.sub_merchant = sub_merchant;
    }

    public String getMethod() {
        return method;
    }

    /**
     * 接口方法 接口方法 该参数用于区分不同的业务方法接口
     *
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public String getPartner_id() {
        return partner_id;
    }

    /**
     * 商户号 商户在银盛支付平台开设的用户号[商户号]
     *
     */
    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 请求时间 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     *
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCharset() {
        return charset;
    }

    /**
     * 编码 商户网站使用的编码格式，如utf-8、utf-8、gb2312等。
     *
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    /**
     * 签名类型 RSA
     *
     */
    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    /***
     * 支付结果通知 银盛支付服务器主动通知商户网站里指定的页面http路径。
     *
     */
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    /**
     * 页面跳转 银盛支付处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。
     *
     */
    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    /**
     * 订单号 银盛支付合作商户网站唯一订单号。该参数支持汉字，前8位必须为当前日期，最大长度为32
     *
     */
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    /**
     * 银盛流水号 该交易在银盛支付系统中的交易流水号.
     *
     */
    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getSubject() {
        return subject;
    }

    /***
     * 商品描述 商品的标题 /交易标题/订单标题/订单关键字等。 该参数最长为250个汉字。
     *
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    /**
     * 订单金额
     * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。Number(10,2)
     * 指10位长度，2位精度
     *
     */
    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    /**
     * 退款订单号 标识一次退款请求，同一笔交易多次退款需要保证唯一。该参数支持汉字，
     * 最大长度为16个。用同一订单号同一退款流水号继续退款时，则会返回第一次退款的结果不会继续退款
     *
     */
    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getSeller_id() {
        return seller_id;
    }

    /**
     * 商户号 收款方银盛支付用户，由银盛业务人员提供=商户号。
     *
     */
    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    /**
     * 收款方银盛支付客户名（注册时公司名称） 由银盛业务人员提供
     *
     */
    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    /**
     * 设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。取值范围：3d～15d。 m-分钟，h-小时，d-天。
     * 该参数数值不接受小数点，如1.5h，可转换为90m。如果小于3天，系统取3天为超时时间。
     *
     */
    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getPay_mode() {
        return pay_mode;
    }

    /**
     * 直连银行信息,直联模式使用,锁定指定的支付方式， 目前支持网银:internetbank,快捷:fastpay,原生app支付:native
     *
     */
    public void setPay_mode(String pay_mode) {
        this.pay_mode = pay_mode;
    }

    public String getBank_type() {
        return bank_type;
    }

    /**
     * 银行代码 直连银行信息,和paymode配合使用,若填写了则直接锁定该银行支付,如需要查看银行代码请在接口文档中查找,
     *
     */
    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getBank_account_type() {
        return bank_account_type;
    }

    /**
     * 直连银行信息,和paymode配合使用，付款方银行账户类型，bank_type非空时，pay_mode为internetbank此处必填。
     * 对公账户:corporate,对私账户:personal 直连快捷默认为对私
     *
     */
    public void setBank_account_type(String bank_account_type) {
        this.bank_account_type = bank_account_type;
    }

    public String getSupport_card_type() {
        return support_card_type;
    }

    /**
     * 直连银行信息,和paymode配合使用，支持卡类型, bank_type非空时，pay_mode为internetbank此处必填。
     * 借记卡:debit,信用卡:credit
     *
     */
    public void setSupport_card_type(String support_card_type) {
        this.support_card_type = support_card_type;
    }

    public String getExtend_params() {
        return extend_params;
    }

    /**
     * 业务扩展参数，一个json字符串 {“param1”:123}
     *
     */
    public void setExtend_params(String extend_params) {
        this.extend_params = extend_params;
    }

    public String getBusiness_code() {
        return business_code;
    }

    /**
     * 业务代码 业务代码由银盛技术人员提供
     *
     */
    public void setBusiness_code(String business_code) {
        this.business_code = business_code;
    }

    public String getExtra_common_param() {
        return extra_common_param;
    }

    /**
     * 公用回传参数
     *
     */
    public void setExtra_common_param(String extra_common_param) {
        this.extra_common_param = extra_common_param;
    }

    public String getVersion() {
        return version;
    }

    /**
     * 接口版本号
     *
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public String getBiz_content() {
        return biz_content;
    }

    /**
     * 业务参数 （参数名biz_content,值为一个json格式对象，下面列表描述json对象的值）：
     *
     */
    public void setBiz_content(String biz_content) {
        this.biz_content = biz_content;
    }

}