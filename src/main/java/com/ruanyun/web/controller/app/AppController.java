package com.ruanyun.web.controller.app;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.TStock;
import com.ruanyun.web.service.web.WebInterface;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController extends BaseController {
    @Autowired
    @Qualifier("webService")
    WebInterface webService;

    /**
     * 手机版的计算器
     * @return
     */
    @RequestMapping("app/calc")
    public String toCalc(){
        return "pc/web/calc";
    }
}
