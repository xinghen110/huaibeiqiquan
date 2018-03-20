package com.ruanyun.web.controller.sys;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.TUserAccountOperation;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.UserAccountFlowService;
import com.ruanyun.web.service.web.UserAccountOperationService;
import com.ruanyun.web.service.web.UserInfoService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.HttpSessionUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 功能说明：
 * <p>
 * 时间: 2017/10/12 18:03 Created by ShiXinBing
 */
@Controller
public class UserAccountOperationController extends BaseController {

    @Autowired
    private UserAccountOperationService userAccountOperationService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountFlowService userAccountFlowService;

    @RequestMapping("user/account/operation/list")
    public String queryList(Model model, Page page, TUserAccountOperation userAccountOperation, HttpSession session, TUserInfo userInfo, String startTime, String endTime,String belongValue,String loginName) {
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0].toString());
            parentCodeIndex = Integer.parseInt(belongInfo[1].toString());
        }
        Page pageList = userAccountOperationService.queryUserAccountOperation(page, userAccountOperation, userInfo, startTime, endTime,belongUserId,parentCodeIndex,loginName);
        model.addAttribute("pageList", pageList);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("userList", JSONArray.fromObject(userService.list(HttpSessionUtils.getCurrentUser(session))));
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return "pc/admin/user_account_operation_list";
    }

    @RequestMapping(value = "user/account/operation/update", method = RequestMethod.GET)
    public String toCheck(TUserAccountOperation userAccountOperation, Model model) {
        model.addAttribute("url", "user/account/operation/update");
        TUserAccountOperation tUserAccountOperation = userAccountOperationService.get(TUserAccountOperation.class, userAccountOperation.getId());
        TUser appllyedUser = userService.get(TUser.class, tUserAccountOperation.getUserId());
        TUserInfo appllyedUserInfo = userInfoService.get(TUserInfo.class, tUserAccountOperation.getUserId());
        TUser appllyUser = userService.get(TUser.class, tUserAccountOperation.getApplyUserId());
        model.addAttribute("userAccountOperation", tUserAccountOperation);
        model.addAttribute("appllyedUser", appllyedUser);
        model.addAttribute("appllyedUserInfo", appllyedUserInfo);
        model.addAttribute("appllyUser", appllyUser);
        return "pc/admin/user_account_operation_update";
    }

    @RequestMapping(value = "user/account/operation/update", method = RequestMethod.POST)
    public void doCheck(HttpServletResponse response, TUserAccountOperation userAccountOperation, Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        Integer handleUserId = currentUser.getUserId();

        userAccountOperation.setHandleUserId(handleUserId);
        userAccountOperation.setStatus(
                StringUtils.isEmpty(userAccountOperation.getStatus()) ?
                        "0" : userAccountOperation.getStatus()
        );
        try {
            userAccountOperationService.handle(userAccountOperation);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "user/account/operation/list", "forward"));
        } catch (Exception e) {
            logger.error(e, e);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, e.getMessage(), "main_", "user/account/operation/list", "forward"));
        }

    }


    @RequestMapping(value = "user/account/operation/add", method = RequestMethod.GET)
    public String toAdd() {
        return "pc/admin/user_account_operation_add";
    }

    @RequestMapping(value = "user/account/operation/add", method = RequestMethod.POST)
    public void doAdd(HttpServletResponse response, TUserAccountOperation userAccountOperation, HttpSession session) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        userAccountOperationService.save(userAccountOperation, user);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "user/account/operation/list", "redirect"));
    }
}
