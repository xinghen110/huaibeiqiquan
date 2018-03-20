package com.ruanyun.web.controller.mall;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TRechargeMeal;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.RechargeMealService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wangyf on 2017/8/5.
 */
@Controller
@RequestMapping("rechargemeal")
public class RechargeMealController extends BaseController {

    @Autowired
    private RechargeMealService rechargeMealService;
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
     * 功能描述：查看充值订单列表
     * @param page
     * @param model
     * @param rechargeMeal
     * @param usTUser
     * @param session
     * @return
     */
    @RequestMapping("list")
    public String getList(Page<TRechargeMeal> page, Model model, TRechargeMeal rechargeMeal, TUser usTUser, HttpSession session){
        TUser user = HttpSessionUtils.getCurrentUser(session);
        addModel(model, "pageList", rechargeMealService.getList(page,rechargeMeal, user));
        addModel(model, "bean", rechargeMeal);
        return "pc/backend/rechargemeal/list";
    }

    @RequestMapping("del")
    public void delete(HttpServletResponse response, String rechargeMealNum){

		int result = rechargeMealService.delete(TRechargeMeal.class,"rechargeMealNum", rechargeMealNum);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","rechargemeal/list", "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
    }

    /**
     * 功能描述:详情页
     * @param model
     * @param rechargeMealNum
     * @return
     */
    @RequestMapping("toDetailsEdit")
    public String toDetailsEdit(Model model,String rechargeMealNum){
		if (EmptyUtils.isNotEmpty(rechargeMealNum)) {
		TRechargeMeal rechargeMeal=	rechargeMealService.get(TRechargeMeal.class,"rechargeMealNum",rechargeMealNum);
			String userNum=rechargeMeal.getUserNum();

			addModel(model, "bean", rechargeMealService.get(TRechargeMeal.class,"rechargeMealNum",rechargeMealNum));
			addModel(model, "user", userService.get(TUser.class, "userNum",userNum));
		}
        return "pc/backend/rechargemeal/detailsEdit";
    }

    /**
     * 功能跳转充值页面
     * @param model
     * @param rechargeMealNum
     * @return
     */
    @RequestMapping("toEdit")
    public String toEdit(Model model,String rechargeMealNum){
        if (EmptyUtils.isNotEmpty(rechargeMealNum)) {

			addModel(model, "bean", rechargeMealService.get(TRechargeMeal.class,"rechargeMealNum",rechargeMealNum));
			TRechargeMeal rechargeMeal =rechargeMealService.get(TRechargeMeal.class,"rechargeMealNum",rechargeMealNum);
			/*String userNum= rechargeMeal.getUserNum();
			addModel(model, "user", userService.get(TUser.class,"userNum",userNum));*/
        }
        return "pc/backend/rechargemeal/edit";
    }

    /**
     * 功能描述:保存和修改
     * @param session
     * @param rechargeMeal
     * @param response
     */
    @RequestMapping("saveOrUpdate")
    public void saveOrUpdate(HttpSession session,TRechargeMeal rechargeMeal,HttpServletResponse response) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
		int result = rechargeMealService.saveOrUpdate(rechargeMeal, user);
		if(result == 1){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","rechargemeal/list", "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
    }
}
