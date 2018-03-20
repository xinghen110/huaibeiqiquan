package com.ruanyun.web.controller.mall;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TOrderMeal;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.OrderMealService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 	订单
 *
 *  创建说明: 2016-9-23 下午05:02:15 wsp  创建文件<br/>
 */
@Controller
@RequestMapping("orderinfo")
public class OrderInfoController extends BaseController{
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private MealInfoService mealInfoService;
    @Autowired
    private OrderMealService orderMealService;
    @Autowired
    private UserRecordService userRecordService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    @RequestMapping("list")
    public String getList(Page<TOrderInfo> page, TOrderInfo orderInfo, Model model) {
        addModel(model, "pageList", orderInfoService.getList(page, orderInfo));
        addModel(model, "bean", orderInfo);
        if (orderInfo.getOrderType() == 1) {
            return "pc/backend/orderinfo/list";
        } else if (orderInfo.getOrderType() == 2) {
            return "pc/backend/orderinfo/list-hyk";
        } else if (orderInfo.getOrderType() == 3) {
            return "pc/backend/orderinfo/list-cz";
        } else {
            return null;
        }
    }

    @RequestMapping("toDetailsEdit")
    public String toEdit(Model model,String orderNum){
        if (EmptyUtils.isNotEmpty(orderNum)) {
            TOrderInfo bean = orderInfoService.get(TOrderInfo.class,"orderNum",orderNum);
            if (bean.getOrderType() == 1) {
                bean.setShopInfo(shopInfoService.get(TShopInfo.class, "shopNum", bean.getShopNum()));
                TOrderMeal orderMeal = orderMealService.get(TOrderMeal.class, "orderNum", bean.getOrderNum());
                TMealInfo mealInfo = mealInfoService.get(TMealInfo.class, "mealInfoNum", orderMeal.getMealNum());
                addModel(model, "orderMeal", orderMeal);
                addModel(model, "mealInfo", mealInfo);
            }
            addModel(model, "bean", bean);
        }
        return "pc/backend/orderinfo/detailsEdit";
    }

    /**
     * 功能描述：获取分销提成列表
     * @param page
     * @param orderInfo
     * @param model
     * @return
     */
    @RequestMapping("list-fx")
    public String listfx(Page<TOrderInfo> page, TOrderInfo orderInfo, Model model) {
        Page<TOrderInfo> pageList = orderInfoService.getFXList(page, orderInfo);
        addModel(model, "pageList", pageList);
        addModel(model, "bean", orderInfo);
        return "pc/backend/orderinfo/list-fx";
    }

    /**
     * 功能描述：分销详情
     * @param orderNum
     * @param model
     * @return
     */
    @RequestMapping("detail-fx")
    public String detailFX(String orderNum, Model model) {
        List<TUserRecord> userRecords = userRecordService.getAllByCondition(TUserRecord.class, "orderNum", orderNum);
        userRecords = userRecordService.setUser(userRecords);
        TUserRecord userRecords1 = userRecords.get(userRecords.size()-1);
        List<TUserRecord> userRecords2 = new ArrayList<TUserRecord>();
        for (int i = 0; i < userRecords.size() - 1; i++) {
            userRecords2.add(userRecords.get(i));
        }
        addModel(model, "userRecord", userRecords1);
        addModel(model, "userRecords", userRecords2);
        return "pc/backend/orderinfo/detailFX";
    }
}









