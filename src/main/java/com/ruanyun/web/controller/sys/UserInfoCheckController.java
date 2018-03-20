package com.ruanyun.web.controller.sys;

import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.TUserInfoCheck;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.UserInfoCheckService;
import com.ruanyun.web.service.web.UserInfoService;
import com.ruanyun.web.sms.SendMessage;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 功能说明：
 * <p>
 * 时间: 2017/10/12 18:03 Created by ShiXinBing
 */
@Controller
public class UserInfoCheckController extends BaseController {

    @Autowired
    private UserInfoCheckService userInfoCheckService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    @RequestMapping("userInfoCheck/list")
    public String queryList(Model model, Page page, TUserInfoCheck userInfoCheck,HttpSession session,TUser user,String belongValue) {
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0].toString());
            parentCodeIndex = Integer.parseInt(belongInfo[1].toString());
        }
        model.addAttribute("userList", JSONArray.fromObject(userService.list(HttpSessionUtils.getCurrentUser(session))));
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
//        String checkResult = userInfoCheck.getCheckResult();
//        if(checkResult.equals("未审核")) {
//            userInfoCheck.setCheckResult("0");
//        }else if(checkResult.equals("审核通过")) {
//            userInfoCheck.setCheckResult("1");
//        }else {
//            userInfoCheck.setCheckResult("2");
//        }
        Page pageList = userInfoCheckService.queryUserInfoCheckList(page, userInfoCheck,user,currentUser,belongUserId,parentCodeIndex);
        model.addAttribute("pageList", pageList);
        model.addAttribute("user", user);
        return "pc/sys/userinfocheck/list";
    }

    @RequestMapping(value = "userInfoCheck/check", method = RequestMethod.GET)
    public String toCheck(TUserInfoCheck userInfoCheck, Model model) {
        model.addAttribute("url","userInfoCheck/check");
        model.addAttribute("userInfoCheck",userInfoCheckService.get(TUserInfoCheck.class,userInfoCheck.getId()));
        return "pc/sys/userinfocheck/check";
    }

    @RequestMapping(value = "userInfoCheck/check/detail", method = RequestMethod.GET)
    public String toCheckDetail(TUserInfoCheck userInfoCheck, Model model) {
//        model.addAttribute("url","userInfoCheck/check");
        model.addAttribute("userInfoCheck",userInfoCheckService.get(TUserInfoCheck.class,userInfoCheck.getId()));
        return "pc/sys/userinfocheck/detail";
    }

    @RequestMapping(value = "userInfoCheck/check", method = RequestMethod.POST)
    public void doCheck(HttpServletResponse response, TUserInfoCheck userInfoCheck, Model model) {
        try {
            String sendMessage = "";
            String checkResult = userInfoCheck.getCheckResult();
            if (EmptyUtils.isEmpty(checkResult)) {
                throw new RuntimeException("状态值为空");
            }
            TUserInfoCheck tUserInfoCheck = userInfoCheckService.get(TUserInfoCheck.class, userInfoCheck.getId());
            tUserInfoCheck.setCheckResult(checkResult);
            userInfoCheckService.update(tUserInfoCheck);
            TUserInfo userInfo = new TUserInfo();
            if ("0".equals(checkResult)) {
                checkResult = "0";
                sendMessage = "实名认证未通过";
            }
            if ("1".equals(checkResult)) {
                BeanUtils.copyProperties(tUserInfoCheck, userInfo);
                userInfo.setStatus(1);
                userInfoService.update(userInfo);
                sendMessage = "实名认证通过";
            }
//        try {
//            SendMessage.doAliyunSend(user.getLoginName(),Integer.parseInt(checkResult));
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "userInfoCheck/list", "forward"));
        }catch (Exception e){
            logger.error(e);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, e.getMessage(), "", "", ""));
        }
    }
}
