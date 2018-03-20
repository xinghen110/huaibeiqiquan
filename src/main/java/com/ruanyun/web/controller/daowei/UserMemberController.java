package com.ruanyun.web.controller.daowei;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.daowei.TUserMember;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.UserMemberService;
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
 * Created by wangyf on 2017/8/28.
 */
@Controller
@RequestMapping("usermember")
public class UserMemberController extends BaseController {

    @Autowired
    private UserMemberService userMemberService;
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
     * 功能描述：获取所有会员用户
     * @param page
     * @param userMember
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("list")
    public String list(Page<TUserMember> page, TUserMember userMember, String nickName, Model model, HttpSession session) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        addModel(model, "pageList", userMemberService.getList(page, userMember, nickName));
        addModel(model, "bean", userMember);
        addModel(model, "user", user);
        addModel(model, "nickName", nickName);
        return "pc/backend/usermember/list";
    }

    /**
     * 功能描述：赠送会员
     * @param userNum
     */
    @RequestMapping("giveMember")
    public void giveMember(HttpServletResponse response, String userNum) {
        int result = userMemberService.saveGiveOrderMember(userNum);
        if (result==1) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "user/users?userType=3", "redirect"));
        }else{
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
        }
    }
}
