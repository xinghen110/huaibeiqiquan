package com.ruanyun.web.util;

import com.pay.ipspay.utils.IPSConstants;
import com.pay.ipspay.utils.Verify;
import com.pay.yinshengpay.utils.YinshengPayConstants;
import com.pay.yspay.bean.PayOrder;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.sys.TUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PayChannels {

    private Logger logger = Logger.getLogger(PayChannels.class);

    //银盛支付
    public String yspay(TOrderInfo orderInfo, String bank, Model model, TUser currentUser){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //创建wap手机直连对象
        PayOrder bean = new PayOrder();
        bean.setOut_trade_no(orderInfo.getOrderNum());
        bean.setTimestamp(sdf.format(orderInfo.getOrderCreateTime()));
        bean.setSubject("银盛支付"+ currentUser +"充值" + orderInfo.getTotalPrice() + "元");
        bean.setTotal_amount(orderInfo.getActualPrice().doubleValue());
        bean.setBank_type(bank);
        bean.setBank_account_type("personal");

        model.addAttribute("payModel", bean);

        return "pay/webDirectPay";
    }

    //环迅支付
    public String ipspay(TOrderInfo orderInfo, String bank, Model model, TUser currentUser){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        IPSConstants ipsConstants = new IPSConstants();
        // 组装请求
        // body部分
        String bodyXml = "<body>" +
                "<MerBillNo>" + orderInfo.getOrderNum() + "</MerBillNo>" +
                "<Lang>GB</Lang>" +
                "<Amount>" + orderInfo.getActualPrice().doubleValue() + "</Amount>" +
                "<Date>" + sdf.format(orderInfo.getOrderCreateTime()) + "</Date>" +
                "<CurrencyType>156</CurrencyType>" +
                "<GatewayType>01</GatewayType>" +
                "<Merchanturl><![CDATA[" + ipsConstants.getMerchanturl() + "]]></Merchanturl>" +
                "<FailUrl><![CDATA[" + ipsConstants.getFailUrl() + "]]></FailUrl>" +
                "<Attach><![CDATA[]]></Attach>" +
                "<OrderEncodeType>5</OrderEncodeType>" +
                "<RetEncodeType>17</RetEncodeType>" +
                "<RetType>1</RetType>" +
                "<ServerUrl><![CDATA[" + ipsConstants.getServerUrl() + "]]></ServerUrl>" +
                "<BillEXP>2</BillEXP>" +
                "<GoodsName>充值</GoodsName>" +
                "<IsCredit></IsCredit>" +
                "<BankCode></BankCode>" +
                "<ProductType></ProductType>" +
                "</body>";
        String sign = DigestUtils
                .md5Hex(Verify.getBytes(bodyXml + ipsConstants.getMERCODE() + ipsConstants.getDIRECT_STR(),
                        "UTF-8"));
        logger.info("签名信息：body-"+bodyXml+"， mercode-"+ipsConstants.getMERCODE()+"，md5-"+ipsConstants.getDIRECT_STR()+" ，sign-"+sign);
        // xml
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String xml = "<Ips>" +
                "<GateWayReq>" +
                "<head>" +
                "<Version>v1.0.0</Version>" +
                "<MerCode>" + ipsConstants.getMERCODE() + "</MerCode>" +
                "<MerName></MerName>" +
                "<Account>" + ipsConstants.getACCOUNT() + "</Account>" +
                "<MsgId>" + "msg" + date + "</MsgId >" +
                "<ReqDate>" + date + "</ReqDate >" +
                "<Signature>" + sign + "</Signature>" +
                "</head>" +
                bodyXml +
                "</GateWayReq>" +
                "</Ips>";
        logger.info(">>>>> 【环迅】订单支付 请求信息: " + xml);
        model.addAttribute("xml", xml);
        model.addAttribute("action", ipsConstants.getGATEWAY_URL());

        return "pay/ipspayForm";
    }

    //银生支付
    public String yinshengPay(TOrderInfo orderInfo, String bank, Model model, TUser currentUser){

        YinshengPayConstants constants = new YinshengPayConstants();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = DateUtils.doFormatDate(orderInfo.getOrderCreateTime(), "yyyyMMddHHmmss");

        String remark = "银生支付"+ currentUser +"充值" + orderInfo.getTotalPrice() + "元";

        //创建wap手机直连对象
        String s = "merchantId=" + constants.getMerchantId() +
                "&merchantUrl=" + constants.getMerchantUrl() +
                "&responseMode=3" +
                "&orderId=" + orderInfo.getOrderNum() +
                "&currencyType=CNY" +
                "&amount=" + orderInfo.getActualPrice().doubleValue() +
                "&assuredPay=false" +
                "&time=" + time +
                "&remark=" + remark +
                "&merchantKey=" + constants.getMerchantKey();

        String md5 = MD5Util.encoderByMd5(s);

        model.addAttribute("version", constants.getVersion());
        model.addAttribute("requestUrl", constants.getRequestUrl());
        model.addAttribute("merchantId", constants.getMerchantId());
        model.addAttribute("merchantKey", constants.getMerchantKey());
        model.addAttribute("merchantUrl", constants.getMerchantUrl());
        model.addAttribute("frontURL", constants.getFrontURL());
        model.addAttribute("responseMode", "3");
        model.addAttribute("assuredPay", "false");
        model.addAttribute("cardAssured", "0");
        model.addAttribute("time", time);
        model.addAttribute("orderId", orderInfo.getOrderNum());
        model.addAttribute("amount", orderInfo.getActualPrice().doubleValue());
        model.addAttribute("remark", remark);
        model.addAttribute("mac", md5);




        logger.info(">>>>> 【银生支付】订单支付 请求信息.");
        logger.warn("s:"+ s+"@mac:"+md5);

        return "pay/yinshengpayForm";
    }
}
