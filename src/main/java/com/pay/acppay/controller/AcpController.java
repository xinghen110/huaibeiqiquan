package com.pay.acppay.controller;

import com.pay.acppay.utils.AcpService;
import com.pay.acppay.utils.SDKConstants;
import com.pay.acppay.utils.SDKUtil;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.service.mall.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class AcpController extends BaseController{
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 接收银联支付返回值（同步）
     */
    @RequestMapping(value = "/acppay/fronturl")
    public String merchanturl( Model model) {
        int m = 1;
        model.addAttribute("result", m);
        return "pc/web/payment_result";
    }

    /**
     * 接收银联支付返回值（异步）
     */
    @RequestMapping(value = "/acppay/merchanturl")
    public String serverurl(HttpServletRequest req) {
        getParameter(req);
        return "succes";
    }
    
    private void getParameter(HttpServletRequest req){
        logger.warn("FrontRcvResponse前台接收报文返回开始");

        String encoding = req.getParameter(SDKConstants.param_encoding);
        logger.warn("返回报文中encoding=[" + encoding + "]");
        Map<String, String> respParam = getAllRequestParam(req);
        // 打印返回报文
        logger.warn("银联返回报文：" + SDKUtil.coverMap2String(respParam));

        Map<String, String> valideData;
        if (!respParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = respParam.entrySet()
                    .iterator();
            valideData = new HashMap<String, String>(respParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = e.getKey();
                String value = e.getValue();
                valideData.put(key, value);
            }
        }else{
            return;
        }
        if (!AcpService.validate(valideData, encoding)) {
            logger.error("验证签名结果[失败].");
        } else {
            logger.warn("验证签名结果[成功].");
            String respCode = valideData.get("respCode");
            String respMsg = valideData.get("respMsg");
            //判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
            //如果交易成功，更新订单数据
            if(respCode != null && (respCode.equals("00") || respCode.equals("A6"))){
                String orderId = valideData.get("orderId");
                String settleAmt = valideData.get("settleAmt");
                TOrderInfo orderInfo = orderInfoService.get(
                        TOrderInfo.class,
                        "orderNum",
                        orderId);
                //要求对金额做比对
                if (orderInfo.getActualPrice().compareTo(new BigDecimal(settleAmt).divide(new BigDecimal(100))) != 0) {
                    String msg = "订单金额不符，订单号：" + orderId
                            + "，订单金额：" + orderInfo.getActualPrice() + "，回执金额：" + settleAmt;
                    logger.error(msg);
                }
                logger.warn("starting updateOrderInfo:" + orderInfo.getOrderNum());
                orderInfoService.updateOrderInfo(orderInfo.getOrderNum());
            }else{
                logger.error("支付失败：" + respMsg);
            }
        }

        logger.warn("FrontRcvResponse前台接收报文返回结束");
    }

    /**
     * 获取请求参数中所有的信息
     * 当商户上送frontUrl或backUrl地址中带有参数信息的时候，
     * 这种方式会将url地址中的参数读到map中，会导多出来这些信息从而致验签失败，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
     */
    private static Map<String, String> getAllRequestParam(
            final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
