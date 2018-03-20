package com.ruanyun.web.controller.web;

//import com.ahnimeng.common.controller.BaseController;
//import com.ahnimeng.transaction.PayHandler;
//import com.ahnimeng.transaction.alipay.config.AlipayConfig;
//import com.ahnimeng.transaction.alipay.config.WeiXinPayConfig;
//import com.ahnimeng.web.service.sys.DictionaryService;
//import com.ahnimeng.web.service.sys.TransactionAliPayService;
//import com.ahnimeng.web.service.sys.TransactionWeiXinService;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.model.PaymentNotifyModel;
import com.ruanyun.web.service.web.BusinessOrderService;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by funcong-tp on 2016-05-19.
 */
@Controller
@RequestMapping("transaction")
public class TransactionController extends BaseController {
    @Autowired
    private BusinessOrderService businessOrderService;


//    @Autowired
//    DictionaryService dictionaryService;
//    @Autowired
//    TransactionAliPayService transactionAliPayService;
//    @Autowired
//    TransactionWeiXinService transactionWeiXinService;

//    {
//        //支付宝
////        AlipayConfig.partner_id = "2088421399073507";//
////        AlipayConfig.seller_id = "106747261@qq.com";//
////        AlipayConfig.notify_url = "http://121.40.202.253/transaction/notify";//
//        AlipayConfig.notify_url = "http://101.37.79.150/transaction/notify";
//        AlipayConfig.return_url = "http://101.37.79.150/login";
//        //微信
//        WeiXinPayConfig.notify_url = "http://101.37.79.150/transaction/notify";
//        WeiXinPayConfig.return_url = "http://101.37.79.150/login";
//        WeiXinPayConfig.mobile = "13955142125";
//        WeiXinPayConfig.mchId = "1430501802";
//        WeiXinPayConfig.appid = "wx7071e89271c2a9a9";
//        WeiXinPayConfig.appSecret = "fd6877aedfb50be7a82df50e3bb3c7d9";
//        WeiXinPayConfig.secretKey = "JfTbHYAzQifFIaASy1WP4dYZHmI1ORKq";
//    }

    @RequestMapping("notify")
    @ResponseBody
    public Object toNotifyCallback(HttpServletRequest request) {
        Map<String, String[]> stringMap = request.getParameterMap();
        JSONObject jsonObject = JSONObject.fromObject(stringMap);

        try {
            PaymentNotifyModel paymentNotifyModel = notifySign(jsonObject.toString());
            if (!paymentNotifyModel.getStatus().equals("1")) {
                //支付失败
                return "fail";
            }
            //支付成功
            //修改订单状态
            businessOrderService.paySuccess(paymentNotifyModel);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * 验证回调
     * @return
     */
    public PaymentNotifyModel notifySign(String json) throws Exception {
//        String json = "{\"timeStamp\":[\"20171209115845\"],\"extend\":[\"\"],\"result\":[\"SUCCESS\"],\"msg\":[\"支付成功|,|goodsName:银行网关测试|,|goodsDesc:商品描述\"],\"amount\":[\"0.01\"],\"orderNo\":[\"test1512791689\"],\"sign\":[\"05c581cec781a0e97f106f34438784d6\"],\"tradeNo3rd\":[\"\"],\"gwTradeNo\":[\"2017120911570401737784217\"],\"mchNo\":[\"MER1000021\"],\"status\":[\"1\"]}";
        //去除多余的方括号
        json = json.replaceAll("\\[|\\]", "");

        JSONObject jsonObject = JSONObject.fromObject(json);
        PaymentNotifyModel paymentNotifyModel = (PaymentNotifyModel) JSONObject.toBean(jsonObject, PaymentNotifyModel.class);
        String sign = jsonObject.getString("sign");

        Map<String, String> map = jsonObject;
//        System.out.println("回调Json:" + map);

        List<String> list = new ArrayList<String>();
        for (String s : map.keySet()) {
            list.add(s);
        }
        //这三项不参与签名
        list.remove("sign");
        list.remove("result");
        list.remove("msg");
        Collections.sort(list);

        //组装参数
        StringBuilder sb = new StringBuilder();

        for (String s : list) {
            if (map.get(s).isEmpty()) continue;
            sb.append(s).append("=").append(map.get(s)).append("&");
        }

        //增加密钥
        sb.append("key=").append("58e0131ca0e5525b8cc0372ce5611c76");
//        System.out.println("待签名的内容:" + sb.toString());

        String byMd5 = MD5Util.MD5Encode(sb.toString(), "utf-8");
//        System.out.println("MD5:" + byMd5);

        if(!sign.equals(byMd5)){
            throw new Exception("回调签名未通过");
        }
        return paymentNotifyModel;
    }

}
