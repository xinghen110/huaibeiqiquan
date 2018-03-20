package com.pay.yspay.controller;


import com.ruanyun.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class YSPayController extends BaseController {

    @RequestMapping("/pay/yspay")
    public String yspay(){
        return "pay/webDirectPay";
    }

}
