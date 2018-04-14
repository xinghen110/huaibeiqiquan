package com.pay.ipspay.controller;


import com.pay.ipspay.utils.IPSConstants;
import com.pay.ipspay.utils.Verify;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.service.mall.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
@SuppressWarnings("unused")
public class IPSPayController extends BaseController {

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 接收环迅支付【成功】返回值（同步）
     */
    @RequestMapping(value = "/ipspay/merchanturl")
    public String merchanturl(HttpServletRequest req, HttpServletResponse resp, Model model) {

        int m = 1;
        if(success(req,resp)){
            m = 0;
        }
        model.addAttribute("result", m);
        return "pc/web/payment_result";
    }

    /**
     * 接收环迅支付【失败】返回值（同步）
     */
    @RequestMapping(value = "/ipspay/failurl")
    public String failurl(HttpServletRequest req, HttpServletResponse resp, Model model) {

        int m = 1;

        model.addAttribute("result", m);
        return "pc/web/payment_result";
    }

    /**
     * 接收环迅支付返回值（异步）
     */
    @RequestMapping(value = "/ipspay/serverurl")
    public String serverurl(HttpServletRequest req, HttpServletResponse resp, Model model) {
        success(req,resp);
        return "";
    }

    /**
     * 接收环迅支付【主动对账】返回值（异步）
     */
    @RequestMapping(value = "/ipspay/callback_check")
    public String callbackCheck(HttpServletRequest req, HttpServletResponse resp, Model model) {

        if(success(req,resp)){
            return "ipscheckok";
        }
        return "";
    }

    private boolean success(HttpServletRequest req, HttpServletResponse resp) {

        try{
            System.out.println("接收服务端返回：===================");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            IPSConstants ipsConstants = new IPSConstants();

            // 获取xml
            String resultXml = req.getParameter("paymentResult");

            logger.warn("paymentResult:" + resultXml);

            logger.warn(Verify.getValueByTag(resultXml, "RspMsg"));

            if(!s2sNotify(resultXml, ipsConstants.getMERCODE(), ipsConstants.getDIRECT_STR(), ipsConstants.getRSA_PUB())){
                logger.warn("s2sNotify:false");
                return false;
            }

            //4、通过返回商户订单编号获取商户系统该笔订单金额和订单日期 与报文返回订单金额和订单日期进行比较
            logger.warn("1111111");
            String MerBillNo = Verify.getValueByTag(resultXml, "MerBillNo");
            logger.warn("2222222");
            String Amount = Verify.getValueByTag(resultXml, "Amount");
            logger.warn("3333333");
            TOrderInfo orderInfo = orderInfoService.get(
                    TOrderInfo.class,
                    "orderNum",
                    MerBillNo);
            logger.warn("4444444");
            //要求对金额做比对
            if (orderInfo.getActualPrice().compareTo(new BigDecimal(Amount)) != 0) {
                String msg = "订单金额不符，订单号：" + MerBillNo
                        + "，订单金额：" + orderInfo.getActualPrice() + "，回执金额：" + Amount;
                logger.error(msg);
                throw new Exception(msg);
            }

            // 5、商户自己业务逻辑处理
            logger.info("starting updateOrderInfo:" + orderInfo.getOrderNum());
            orderInfoService.updateOrderInfo(orderInfo.getOrderNum());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    private boolean s2sNotify(String resultXml, String merCode, String directStr, String ipsRsaPub) {

        System.out.println(">>>>>(merchant/s2sxml) received message from IPS......" + resultXml);
        // 1、获取签名方式 验签
        if (!Verify.checkSign(resultXml, merCode, directStr, ipsRsaPub)) {
            logger.warn("验签失败");
            return false;
        }
        //2、验签通过，判断IPS返回状态码
        if (!Verify.getRspCode(resultXml).equals("000000")) {
            // 具体错误信息可获取<RspMsg></RspMsg>
            logger.warn("请求响应不成功");
            return false;
        }
        logger.warn("验签完成");
        //3、IPS返回成功  根据交易状态做相应处理
        logger.warn("交易状态校验："+Verify.getStatus(resultXml));
        return Verify.getStatus(resultXml).equals("Y");
    }
}
