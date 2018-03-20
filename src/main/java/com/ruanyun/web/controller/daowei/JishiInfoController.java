package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TJishiInfo;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.mall.TCommentInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.JiShiInfoService;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.mall.CommentInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangyf on 2017/8/5.
 */
@Controller
@RequestMapping("jishiinfo")
public class JishiInfoController extends BaseController {

    @Autowired
    private JiShiInfoService jiShiInfoService;
    @Autowired
    private MealInfoService mealInfoService;
    @Autowired
    private CommentInfoService commentInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private UserService userService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    /**
     * 功能描述：店内技师详情
     * @param jishiInfoNum
     * @param model
     * @return
     */
    @RequestMapping("jishi_diannei_details")
    public String jishi_diannei_details(String jishiInfoNum, Model model) {
        TJishiInfo jishiInfo = jiShiInfoService.get(TJishiInfo.class, "jishiInfoNum", jishiInfoNum);
        //设置技师的年龄
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(jishiInfo.getUserBirth());
        calendar2.setTime(new Date());
        jishiInfo.setFlag1(String.valueOf(calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)));
        addModel(model, "jishiInfo", jishiInfo);
        return "pc/app/index/indiana/jishi_diannei_details";
    }

}
