package com.pay.yinshengpay.controller;


import com.pay.yinshengpay.utils.YinshengPayConstants;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.MD5Util;
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
public class YinshengPayController extends BaseController{

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 接收银生支付返回值（同步）
     */
    @RequestMapping(value = "/yinshengpay/frontURL")
    public String frontURL(HttpServletRequest req, HttpServletResponse resp, Model model) {

        int m = 1;
        if(pares(req)){
            m = 0;
        }
        model.addAttribute("result", m);
        return "pc/web/payment_result";
    }

    /**
     * 接收银生支付返回值（异步）
     */
    @RequestMapping(value = "/yinshengpay/merchanturl")
    public String merchanturl(HttpServletRequest req, HttpServletResponse resp, Model model) {

        pares(req);
        return "";
    }

    private boolean pares(HttpServletRequest req){

        logger.warn("【银生支付】接收服务端返回：===================");

        YinshengPayConstants constants = new YinshengPayConstants();

        String merchantId = req.getParameter("merchantId");
        String merchantKey = constants.getMerchantKey(); //密钥
        String responseMode = req.getParameter("responseMode");
        String orderId = req.getParameter("orderId");
        String currencyType = req.getParameter("currencyType");
        String amount = req.getParameter("amount");
        String returnCode = req.getParameter("returnCode");
        String returnMessage = req.getParameter("returnMessage");
        String mac = req.getParameter("mac") ;
        boolean success = "0000".equals(returnCode);
        boolean paid = "0001".equals(returnCode);
        StringBuilder s = new StringBuilder(50);
            //拼成数据串
            s.append("merchantId=").append(merchantId);
            s.append("&responseMode=").append(responseMode);
            s.append("&orderId=").append(orderId);
            s.append("&currencyType=").append(currencyType);
            s.append("&amount=").append(amount);
            s.append("&returnCode=").append(returnCode);
            s.append("&returnMessage=").append(returnMessage);
            s.append("&merchantKey=").append(merchantKey);
        //md5加密
        String nowMac = MD5Util.encoderByMd5(s.toString());

        logger.warn("返回string:" + s.toString());
        logger.warn("receive mac:" + mac);
        logger.warn("now mac:" + nowMac);

        if(nowMac.equals(mac)){ //若mac校验匹配

            if(success && paid){
                //交易成功，系统处理返回数据
                TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class,"orderNum",orderId);

                //要求对金额做比对
                if (orderInfo.getActualPrice().compareTo(new BigDecimal(amount)) != 0) {
                    String msg = "订单金额不符，订单号：" + orderId
                            + "，订单金额：" + orderInfo.getActualPrice() + "，回执金额：" + amount;
                    logger.error(msg);
                    return false;
                }
                // 5、商户自己业务逻辑处理
                logger.info("starting updateOrderInfo:" + orderInfo.getOrderNum());
                orderInfoService.updateOrderInfo(orderInfo.getOrderNum());
            }else{
                logger.error(returnMessage);
                return false;
            }
        }else{
            logger.error("接收到的数据mac与计算mac不匹配！");
            return false;
        }
        return true;
    }
}
