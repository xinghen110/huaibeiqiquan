package com.pay.yspay.controller;


import com.google.gson.JsonObject;
import com.qiniu.util.Json;
import com.ruanyun.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class YSPayController extends BaseController {

/*    @RequestMapping("/pay/yspay")
    public String yspay(){
        return "pay/webDirectPay";
    }

    @RequestMapping("/yspay/returnPage")
    public String returnPage(){
        return "pc/web/realName_authentication";
    }

    @RequestMapping("/yspay/receiveNotify")
    public void receiveNotify(HttpServletRequest request, HttpServletResponse response){
        String s = Json.encode(request.getParameterMap());
        logger.warn("receiveNotify:" + s);
        try {
            PrintWriter writer = response.getWriter();
            writer.print("success");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }*/
}
