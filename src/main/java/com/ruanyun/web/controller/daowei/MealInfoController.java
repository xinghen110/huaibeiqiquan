package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.service.daowei.MealInfoService;
import com.ruanyun.web.service.daowei.TypeInfoService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangyf on 2017/8/4.
 */
@Controller
@RequestMapping("mealinfo")
public class MealInfoController extends BaseController {

    @Autowired
    private MealInfoService mealInfoService;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private TypeInfoService typeInfoService;

    /**
     * 功能描述:绑定时间
     */
    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd", true);
    }

    /**
     * 功能描述：获取套餐列表，根据 mealType 来区分是技师套餐列表还是商家套餐列表
     * @param page
     * @param mealInfo
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Page<?> page, TMealInfo mealInfo, Model model) {
        page = mealInfoService.getList2(page, mealInfo);
        List<TTypeInfo> typeInfos = typeInfoService.getTypes(12);
        /*for (TMealInfo info : page.getResult()) {
            TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", info.getShopNum());
            info.setFlag1(shopInfo.getShopName());
            info.setFlag2(typeInfoService.getTypeInfoNames(info.getTypeNum(), typeInfos));
        }*/

        addModel(model, "typeInfo", typeInfos);
        addModel(model, "bean", mealInfo);
        addModel(model, "pageList", page);
        return "pc/backend/mealinfo/list";
    }

    /**
     * 功能描述：查看套餐详情
     * @param mealInfoNum
     * @param model
     * @return
     */
    @RequestMapping("details")
    public String details(String mealInfoNum, Model model) {
        TMealInfo mealInfo = mealInfoService.get(TMealInfo.class, "mealInfoNum", mealInfoNum);
        mealInfo.setFlag1(typeInfoService.get(TTypeInfo.class, "typeNum", mealInfo.getTypeNum()).getTypeInfoName());
        TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "shopNum", mealInfo.getShopNum());
        addModel(model, "mealInfo", mealInfo);
        addModel(model, "shopInfo", shopInfo);
        return "pc/backend/mealinfo/details";
    }

    /**
     * 功能描述：修改套餐是否推荐
     * @param mealInfoNum
     * @param ids
     * @param response
     */
    @RequestMapping("update")
    public void update(String mealInfoNum, String ids,HttpServletResponse response) {
        int result = 0;
        if (EmptyUtils.isNotEmpty(mealInfoNum) && EmptyUtils.isEmpty(ids)) {
            result = mealInfoService.update(mealInfoNum);
        }
        if (EmptyUtils.isNotEmpty(ids) && EmptyUtils.isEmpty(mealInfoNum)) {
            String[] mealInfoNums = ids.split(",");
            for (int i =0; i < mealInfoNums.length; i++) {
                result += mealInfoService.update(mealInfoNums[i]);
            }
        }
        if (result > 0) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "mealinfo/list", "redirect"));
        } else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    }

}
