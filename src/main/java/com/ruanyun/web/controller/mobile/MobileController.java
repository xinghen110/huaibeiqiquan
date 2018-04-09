package com.ruanyun.web.controller.mobile;


import com.qiniu.util.Json;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.ImageUtil;
import com.ruanyun.web.aliyun.GetGuPiao;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.payeasy.OrderParmentResultReturnEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRequestEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRetuenEntity;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.web.SearchPriceContentModel;
import com.ruanyun.web.service.app.AppUserRecordService;
import com.ruanyun.web.service.app.sys.AppUserStockService;
import com.ruanyun.web.service.mall.AdverInfoService;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.WebInterface;
import com.ruanyun.web.util.*;
import com.ruanyun.zf.HttpClientUtil;
import com.ruanyun.zf.PayUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MobileController extends BaseController {

    @Autowired
    @Qualifier("webService")
    private WebInterface webService;

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private AdverInfoService adverInfoService;
    @Autowired
    private UserService userService;

    @Autowired
    private AppUserStockService appUserStockService;


    @Autowired
    private AppUserRecordService appUserRecordService;

    @Autowired
    private OrderInfoService orderInfoService;


    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping(value = "/mobile/index", method = RequestMethod.GET)
    public String toWebIndex(Model model, Page page, TArticle article) {
        page.setNumPerPage(3);
        article.setMediaType(1);
        model.addAttribute("dataList", JSONArray.fromObject(webService.queryStockList(new TStock())));
        model.addAttribute("page", webService.queryArticleList(page, article));
        return "mobile/stock/login";
    }

    /**
     * 跳转到投资保障界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/protection", method = RequestMethod.GET)
    public String toInvestProtect(TAdverInfo adverInfo, Model model, String flag1) {
        if (EmptyUtils.isNotEmpty(flag1)) {
            adverInfo.setFlag1(flag1);
        } else {
            adverInfo.setFlag1("1");
        }
        TAdverInfo tAdverInfo = adverInfoService.getAdverInfo(adverInfo);
        model.addAttribute("adverInfo", tAdverInfo);
        return "pc/mobile/invest_protect";
    }

    /**
     * 跳转到文章详情详情
     *
     * @return
     */
    @RequestMapping(value = "/mobile/news/detail", method = RequestMethod.GET)
    public String toNewsDetail(TArticle article, Model model) {
        model.addAttribute("article", webService.queryArticle(article.getArticleId()));
        model.addAttribute("preAndNext", webService.preAndNextArticle(article));
        return "pc/mobile/news_details";
    }

    /**
     * 跳转到产品信息界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/product", method = RequestMethod.GET)
    public String toProductIntroduction(Model model, TArticle article, Page page) {
        article.setArticleType(3);
        article.setMediaType(2);
        page.setNumPerPage(8);
        model.addAttribute("dataList", JSONArray.fromObject(webService.queryStockList(new TStock())));
        model.addAttribute("pageList", webService.queryArticleList(page, article));
        return "pc/mobile/product_introduction";
    }

    /**
     * 跳转个股计算
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/count", method = RequestMethod.GET)
    public String toStockCount() {
        return "pc/mobile/stock_count";
    }


    /**
     * 跳转到视频列表界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/video/list", method = RequestMethod.GET)
    public String toVideoList() {
        return "pc/mobile/video_list";
    }

    /**
     *
     */
    @RequestMapping(value = "/mobile/video/list", method = RequestMethod.POST)
    public void doVideoList(HttpServletResponse response, TArticle article, Page page) {
        article.setArticleType(3);
        article.setMediaType(2);
        page.setNumPerPage(8);
        super.writeJsonData(response, "utf-8", webService.queryArticleList(page, article));
    }

    /**
     * 跳转到视频详情界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/video/detail", method = RequestMethod.GET)
    public String toVideoDetail() {
        return "pc/mobile/video_detail";
    }

    /**
     * 跳转到产品介绍界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/product/about", method = RequestMethod.GET)
    public String toProductAbout() {
        return "pc/mobile/product_about";
    }

    /**
     * 跳转到期货案例界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/product/case", method = RequestMethod.GET)
    public String toProductCase() {
        return "pc/mobile/product_case";
    }

    /**
     * 产品特点
     *
     * @return
     */
    @RequestMapping(value = "/mobile/product/feature", method = RequestMethod.GET)
    public String toProductFeature() {
        return "pc/mobile/product_feature";
    }

    /**
     * 期权期货区别
     *
     * @return
     */
    @RequestMapping(value = "/mobile/product/difference", method = RequestMethod.GET)
    public String toProductDifference() {
        return "pc/mobile/product_difference";
    }

//    /**
//     * 跳转到行业资讯列表
//     *value值与后面的重复，且无法体现具体的功能意义，于是注释了此URL
//     * @return
//     */
//    @RequestMapping(value = "/mobile/news/list",method = RequestMethod.GET)
//    public String toNewsList() {
//        return "pc/mobile/product_difference";
//    }


    /**
     * 跳转行业资讯
     * 将url做了修改，从url本身并不能看出意义，索性改成具体含义的url
     *
     * @return
     */
    @RequestMapping(value = "/mobile/industry/information/list", method = RequestMethod.GET)
    public String toIndustryInfoList(TArticle article, Page page, Model model) {
        article.setMediaType(1);//作为媒体类型，文章1  视频2
        article.setArticleType(1);//作为文章的类型 行业资讯1 实时解盘2 视频分类暂定为3
        model.addAttribute("page", webService.queryArticleList(page, article));
        return "pc/mobile/industry_information";
    }

    @RequestMapping(value = "/mobile/industry/information/list", method = RequestMethod.POST)
    public void doIndustryInfoList(HttpServletResponse response, TArticle article, Page page, Model model) {
        article.setMediaType(1);//作为媒体类型，文章1  视频2
        article.setArticleType(1);//作为文章的类型 行业资讯1 实时解盘2
        super.writeJsonData(response, "utf-8", webService.queryArticleList(page, article));
    }

//    /**
//     * 跳转行业资讯详细页
//     *
//     * @return
//     */
//    @RequestMapping(value = "/mobile/industry/information/detail", method = RequestMethod.GET)
//    public String toIndustryInfoDetail() {
//        return "pc/mobile/industry_information";
//    }

    /**
     * 实时开户
     * 跳转到实时开户
     *
     * @return
     */
    @RequestMapping(value = "/mobile/realTime/open", method = RequestMethod.GET)
    public String toRealTimeOpen(HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/mobile/stock/login");
        }
//        if ("没有实名验证".equals("没有实名验证")) {
//            return redirect("/mobile/realTime/authentication");
//        }
        return redirect("/mobile/stock/userinfo");
//        return "pc/mobile/realTimeOpening_personalData";
    }

    /**
     * 实名认证
     * 跳转到实时开户
     *
     * @return
     */
//    @Deprecated
//    @RequestMapping(value = "/mobile/realTime/authentication", method = RequestMethod.GET)
//    public String toRealTimeAuthentication(HttpSession session) {
//        return "pc/mobile/realTimeOpening_realName_authentication";
//    }

    /**
     * 关于我们
     *
     * @return
     */
//    @RequestMapping(value = "/mobile/aboutus", method = RequestMethod.GET)
//    public String toAboutsUs() {
//        return "pc/mobile/abouts_us";
//    }

    /**
     * 集团介绍
     *
     * @return
     */
//    @RequestMapping(value = "/mobile/aboutgroup", method = RequestMethod.GET)
//    public String toAboutGroup() {
//        return "pc/mobile/abouts_us";
//    }

    /**
     * 跳转到实时解盘列表界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/latest/analysis/information/list", method = RequestMethod.GET)
    public String toWebNewsList(TArticle article, Page page, Model model) {
        article.setMediaType(1);//作为媒体类型，文章1  视频2
        article.setArticleType(2);//作为文章的类型 行业资讯1 实时解盘2
        model.addAttribute("pageList", webService.queryArticleList(page, article));
        return "pc/mobile/realTime_Jiepan";
    }

    @RequestMapping(value = "/mobile/latest/analysis/information/list", method = RequestMethod.POST)
    public void doWebNewsList(HttpServletResponse response, TArticle article, Page page, Model model) {
        article.setMediaType(1);//作为媒体类型，文章1  视频2
        article.setArticleType(2);//作为文章的类型 行业资讯1 实时解盘2
        super.writeJsonData(response, "utf-8", webService.queryArticleList(page, article));
    }

//    /**
//     * 跳转到实时解盘详细界面
//     *
//     * @return
//     */
//    @RequestMapping(value = "/mobile/realtime/analysis/news/detail", method = RequestMethod.GET)
//    public String toWebNewsDetail() {
//        return "pc/mobile/web_news_detail";
//    }

    /**
     * 跳转到软件下载界面，由扫描二维码链接到资源位置进行下载
     *
     * @return
     */
    @RequestMapping(value = "/mobile/download", method = RequestMethod.GET)
    public String toWebDownload() {
        return "pc/mobile/web_download";
    }

    /**
     * 跳转到快速登录
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/login", method = RequestMethod.GET)
    public String toWebStockLogin(Model model) {
//        addModel(model,"url", "mobile/stock/login");
        return "pc/mobile/login";
    }

    /**
     * 执行登录操作
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/login", method = RequestMethod.POST)
    public String doWebStockLogin(Model model, HttpServletRequest request, HttpSession session, TUser user) {
        if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getLoginPass())) {
            addModel(model, "msg", "请输入手机号和密码");
            return redirect("/mobile/stock/login");
        }
        try {
            TUser loginUser = webService.webLogin(request, session, user.getLoginName(), user.getLoginPass());
            if (loginUser.getUserType() != Constants.USER_TYPE_CUSTOM_05) {
                addModel(model, "msg", "当前用户不可登录！");
                return redirect("/mobile/stock/login");
            }
            return REDIRECT + "/mobile/stock/userinfo";
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
            return redirect("/mobile/stock/login");
        }
    }

    /**
     * 快速注册
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/register", method = RequestMethod.GET)
    public String toWebStockRegister(HttpSession session, String parentCode) {
        session.setAttribute("parentCode", parentCode);
        return "pc/mobile/register";
    }

    /**
     * 执行注册操作
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/register", method = RequestMethod.POST)
    public String doWebStockRegister(Model model, String nickName, String loginName, String loginPass, String parentCode, String identityCode) {
        String oldParentCode = null;
        String newParentCode = null;
        if (EmptyUtils.isEmpty(parentCode)) {
            addModel(model, "msg", "请输入推广码！");
            return redirect("/mobile/stock/register");
        } else {
            Integer userId = Integer.parseInt(parentCode);
            TUser user = userService.get(TUser.class, userId);
            if (EmptyUtils.isNotEmpty(user)) {
                if (user.getUserType() != Constants.USER_TYPE_SPREAD_04) {
                    addModel(model, "msg", "请输入正确的推广码！");
                    return redirect("/mobile/stock/register");
                }
                oldParentCode = user.getParentCode();
            } else {
                addModel(model, "msg", "推广链接失效，请重新获取链接");
                return redirect("/mobile/stock/register");
            }
        }

        if (EmptyUtils.isEmpty(oldParentCode)) {
            newParentCode = parentCode;
        } else {
            newParentCode = oldParentCode + "," + parentCode;
        }

        try {
            webService.webRegister(nickName, loginName, loginPass, newParentCode, identityCode);
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
            return redirect("/mobile/stock/login");
        }
        //新增一个改动.当注册成功时,跳转到一个二维码的页面,而不是登录页了
//        return "pc/mobile/register_success";
        return redirect("/mobile/register/success");
//        return redirect("/mobile/stock/login?msg=registerSuccess");
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/logout", method = RequestMethod.GET)
    public String doWebStockLogout(HttpSession session) {
        webService.webLoginout(session);
        return redirect("/mobile/stock/login");
    }

    /**
     * 跳转到修改密码界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/password/update", method = RequestMethod.GET)
    public String toWebStockpasswordUpdate() {
        return "pc/mobile/update_password";
    }

    /**
     * 执行修改密码操作
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/password/update", method = RequestMethod.POST)
    public String doWebStockpasswordUpdate(Model model, HttpSession session, String loginName, String password, String identityCode) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.passwordUpdate(user, loginName, password, identityCode);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            String redirect = redirect("/mobile/stock/password/update");
            return redirect;
        }
        return redirect("/mobile/stock/logout");
    }

    /**
     * 跳转到找回密码界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/password/forget", method = RequestMethod.GET)
    public String toWebStockPasswordForget() {
        return "pc/mobile/forgot_password";
    }

    /**
     * 执行找回密码操作
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/password/forget", method = RequestMethod.POST)
    public String doWebStockPasswordForget(Model model, String userTel, String newPassword, String identityCode) {
        try {
            webService.passwordForget(userTel, identityCode, newPassword);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return redirect("/mobile/stock/password/forget");
        }
        return redirect("/mobile/stock/logout");
    }

    /**
     * 跳转到实名认证及银行卡绑定界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/realname/bindingcard", method = RequestMethod.GET)
    public String toWebStockAuthentication(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo userInfo = webService.queryUserInfo(currentUser.getUserId());
        addModel(model, "userInfo", userInfo);
        return "pc/mobile/real_name_binding_card";
    }

    /**
     * 气流图片
     *
     * @param response
     * @param model
     * @param session
     * @param imgBase64Data
     */
    @RequestMapping(value = "/mobile/stock/update/authentication", method = RequestMethod.POST)
    public void updateWebStockAuthentication(HttpServletResponse response, Model model, HttpSession session, String imgBase64Data) {
        UploadVo vo = ImageUtil.GenerateImage2(imgBase64Data);
        super.writeText(response, vo.getFilename());
    }

    /**
     * 执行实名认证及银行卡绑定操作
     *
     * @return // TODO: 2017/10/21 验证功能要完善
     */
    @RequestMapping(value = "/mobile/realname/bindingcard", method = RequestMethod.POST)
    public String doWebStockAuthentication(HttpSession session, TUserInfo userInfo, Model model) {
        if (EmptyUtils.isEmpty(userInfo.getUserName())) {
            addModel(model, "msg", "姓名不能为空");
            return redirect("/mobile/realname/bindingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getIdNumber())) {
            addModel(model, "msg", "身份证不能为空");
            return redirect("/mobile/realname/bindingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getIdCardFrontView())) {
            addModel(model, "msg", "身份证正面照不能为空或不能为默认图片");
            return redirect("/mobile/realname/bindingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getIdCardBackView())) {
            addModel(model, "msg", "身份证反面照不能为空或不能为默认图片");
            return redirect("/mobile/realname/bindingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getBankId())) {
            addModel(model, "msg", "所属银行不能为空");
            return redirect("/mobile/realname/bindingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getBankCardNumber())) {
            addModel(model, "msg", "银行卡号不能为空");
            return redirect("/mobile/realname/bindingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getBackCardPhoto())) {
            addModel(model, "msg", "银行照片不能为空或不能为默认图片");
            return redirect("/mobile/realname/bindingcard");
        }
        TUser user = HttpSessionUtils.getCurrentUser(session);
        Integer userId = user.getUserId();
        String idCardNumber = userInfo.getIdNumber();
        int isOnly = webService.idCardNumCheck(idCardNumber, userId);
        if (isOnly == 1) {
            addModel(model, "msg", "该身份证已绑定！");
            return redirect("/mobile/realname/bindingcard");
        }
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfoCheck userInfoCheck = new TUserInfoCheck();
        BeanUtils.copyProperties(userInfo, userInfoCheck);
        TUserInfoCheck oldUserInfoCheck = webService.queryUserInfoCheck(userInfoCheck.getUserId(), "0");
        if (EmptyUtils.isNotEmpty(oldUserInfoCheck)) {
            addModel(model, "msg", "您有未审核的认证，请勿再次认证！");
        } else if (webService.checkBankNumberAndBankId(userInfo, currentUser.getUserId()) == 0) {
            addModel(model, "msg", "您银行卡或卡号未修改，请勿重复绑定相同的银行卡！");
        } else {
            userInfoCheck.setCheckResult("0");
            // userInfo.setUserId(currentUser.getUserId());
            webService.authenticationCheck(userInfoCheck);
            addModel(model, "msg", "等待审核中！");
        }
//        return redirect("/mobile/stock/real_name_binding_card_success");
        return redirect("/mobile/stock/userinfo");
    }

    /**
     * 用户绑定的信息
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/mobile/realname/bandingcard/success", method = RequestMethod.GET)
    public String toMobileUserinfo(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo userInfo = webService.queryUserInfo(currentUser.getUserId());
        addModel(model, "userInfo", userInfo);
        return "pc/mobile/real_name_binding_card_success";
    }

    /**
     * 换绑银行卡
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/mobile/update/bandingcard", method = RequestMethod.GET)
    public String toUpdateBandingCard(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo userInfo = webService.queryUserInfo(currentUser.getUserId());
        addModel(model, "userInfo", userInfo);
        return "pc/mobile/updata_yh_card";
    }

    /**
     * 执行换绑银行卡
     *
     * @return // TODO: 2017/10/21 验证功能要完善
     */
    @RequestMapping(value = "/mobile/update/bandingcard", method = RequestMethod.POST)
    public String toUpdateBandingCard(HttpSession session, TUserInfo userInfo, Model model) {
        addModel(model, "ceshe", "cse");
        if (EmptyUtils.isEmpty(userInfo.getBankId())) {
            addModel(model, "msg", "银行卡不能为空");
            return redirect("/mobile/update/bandingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getBankCardNumber())) {
            addModel(model, "msg", "银行卡号不能为空");
            return redirect("/mobile/update/bandingcard");
        }
        if (EmptyUtils.isEmpty(userInfo.getBackCardPhoto())) {
            addModel(model, "msg", "银行照片不能为空");
            return redirect("/mobile/update/bandingcard");
        }
        String idCardNumber = userInfo.getIdNumber();
//        int isOnly = webService.idCardNumCheck(idCardNumber);
//        if(isOnly == 1) {
//            addModel(model, "msg", "该身份证已绑定！");
//            return redirect("/mobile/realname/bindingcard");
//        }
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo oldUserInfo = webService.queryUserInfo(currentUser.getUserId());
        oldUserInfo.setBankId(userInfo.getBankId());
        oldUserInfo.setBankCardNumber(userInfo.getBankCardNumber());
        oldUserInfo.setBackCardPhoto(userInfo.getBackCardPhoto());

        TUserInfoCheck userInfoCheck = new TUserInfoCheck();
        BeanUtils.copyProperties(oldUserInfo, userInfoCheck);
        TUserInfoCheck oldUserInfoCheck = webService.queryUserInfoCheck(userInfoCheck.getUserId(), "0");
        if (EmptyUtils.isNotEmpty(oldUserInfoCheck)) {
            addModel(model, "msg", "您有未审核的认证，请勿再次认证！");
            return redirect("/mobile/realname/bandingcard/success");
        } else if (webService.checkBankNumberAndBankId(userInfo, currentUser.getUserId()) == 0) {
            addModel(model, "msg", "您银行卡或卡号未修改，请勿重复绑定相同的银行卡！");
            return redirect("/mobile/update/bandingcard");
        } else {
            userInfoCheck.setCheckResult("0");
            // userInfo.setUserId(currentUser.getUserId());
            webService.authenticationCheck(userInfoCheck);
            addModel(model, "msg", "等待审核中！");
            return REDIRECT + "/mobile/realname/bandingcard/success";
        }
//        return redirect("/mobile/stock/real_name_binding_card_success");
//        return redirect("/mobile/stock/userinfo");
    }

    /**
     * 个人信息展示
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/userinfo", method = RequestMethod.GET)
    public String toWebStockUserinfo(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo userInfo = webService.queryUserInfo(currentUser.getUserId());
        TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
        addModel(model, "userInfo", userInfo);
        addModel(model, "userAccount", userAccount);
        return "pc/mobile/personal_center";
    }


    /**
     * 跳转到账户充值界面
     *
     * @return
     */
    @RequestMapping(value = "/mobile/recharge", method = RequestMethod.GET)
    public String toWebStockDeposit(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
        addModel(model, "userAccount", userAccount);
        return "pc/mobile/recharge";
    }

    /**
     * 执行账户充值操作
     *
     */
    @RequestMapping(value = "/mobile/recharge", method = RequestMethod.POST)
    public String doWebStockDeposit(Model model, HttpSession session, BigDecimal money, String payType) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.deposit(currentUser.getUserId(), money, payType);
            addModel(model, "msg", "充值成功");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
        }
        return redirect("/mobile/recharge");
    }

    /**
     * 跳转到账户提现界面
     *
     */
    @RequestMapping(value = "/mobile/withdraw", method = RequestMethod.GET)
    public String toWebStockWithdraw(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
        addModel(model, "userAccount", userAccount);
        return "pc/mobile/withdrawals";
    }

    /**
     * 执行账户提现操作
     */
    @RequestMapping(value = "/mobile/withdraw", method = RequestMethod.POST)
    public String doWebStockWithdraw(Model model, HttpSession session, BigDecimal money) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.withdraw(currentUser.getUserId(), money);
            addModel(model, "msg", "提现申请成功，请等待人工审核");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
        }
        return redirect("/mobile/withdraw");
    }

    /**
     * 推广链接
     *
     */
    @RequestMapping(value = "/mobile/stock/promotion", method = RequestMethod.GET)
    public String toWebPromotion() {
        return "pc/mobile/realTimeOpening_promoteMoney";
    }

    /**
     * 跳转到管理方案界面
     */
    @RequestMapping(value = "/mobile/stock/plan/list", method = RequestMethod.GET)
    public String toWebStockPlanList(Model model, HttpSession session, TStockPlan tStockPlan) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/mobile/stock/login");
        }
        tStockPlan.setUserId(currentUser.getUserId());
        List<Map> stockPlanListMap = webService.queryStockPlanListMap(tStockPlan, currentUser);
        //前台用户不能看平台的管理费,在这里就要相加,隐藏平台的管理费
        for (Map map : stockPlanListMap) {
            map.put("serviceFee", "");
            map.put("manageFee", "");
        }

        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
        config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor());
        config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());

        addModel(model, "stockPlanListMap", stockPlanListMap);
        addModel(model, "stockPlanListMapJson", JSONArray.fromObject(stockPlanListMap, config));

        if (Constants.STOCK_PLAN_ORDER_STATUS_APPLY.equals(tStockPlan.getOrderStatus())) {
            return "pc/mobile/my_scheme_apply";
        } else if (Constants.STOCK_PLAN_ORDER_STATUS_POSITION.equals(tStockPlan.getOrderStatus())) {
            return "pc/mobile/my_scheme_position";
        } else if (Constants.STOCK_PLAN_ORDER_STATUS_EXERCISE.equals(tStockPlan.getOrderStatus())) {
            return "pc/mobile/my_scheme_exercise";
        } else if (Constants.STOCK_PLAN_ORDER_STATUS_SETTLEMENT.equals(tStockPlan.getOrderStatus())) {
            return "pc/mobile/my_scheme_settlement";
        } else {
            return "pc/mobile/my_scheme_failureApplication";
        }
    }

    /**
     * 跳转到方案详情界面
     */
    @RequestMapping(value = "/mobile/stock/plan/detail", method = RequestMethod.GET)
    public String toWebStockPlanDetail(HttpServletResponse response, TStockPlan stockPlan) {
        TStockPlan tStockPlan = webService.queryStockPlan(stockPlan);
        super.writeJsonData(response, tStockPlan);
        return "mobile/stock/plan/detail";
    }

    /**
     * 选择股票标的
     */
    @RequestMapping(value = "/mobile/stock/center", method = RequestMethod.GET)
    public String toWebStockCenter(Model model, HttpSession session, String[] symbols, TUserStock userStock) {
        model.addAttribute("dataList", JSONArray.fromObject(webService.queryStockList(new TStock())));
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser != null) {
            List userStockList = webService.queryUserStockList(currentUser.getUserId(), userStock);
            addModel(model, "userStockList", JSONArray.fromObject(userStockList));
            TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
            addModel(model, "userAccount", userAccount);
        } else {
            addModel(model, "userStockList", "[]");
        }
        return "pc/mobile/all_subject_matter";
    }

    /**
     * 跳转到申请方案界面
     *
     * @deprecated 并没使用上这个页面
     */
    @Deprecated
    @RequestMapping(value = "/mobile/stock/plan/create", method = RequestMethod.GET)
    public String toWebStockPlanCreate(
            TStockPlan stockPlan, HttpSession session,
            String cycle, String priceType, String symbol, String symbolName, String price, String managePriceRate, String managePrice
            //cycle=1d&buyPriceType=0&symbol=600757&symbolName=长江传媒&offerPrice=5908&price=5908&managePriceRate=59.08&managePrice=5908&price=5908
    ) {
//        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
//        stockPlan.setSymbolId(symbol);
        TStockPlan tStockPlan = webService.createStockPlan(stockPlan);
        return redirect("/mobile/realTime/open");
    }

    /**
     * 执行确认申请的操作
     *
     */
    @RequestMapping(value = "/mobile/stock/plan/create", method = RequestMethod.POST)
    public String doWebStockPlanCreate(TStockPlan stockPlan, HttpSession session, Model model) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/mobile/stock/login");
        }
        if (stockPlan.getBuyPriceType().equals("0")) {
            stockPlan.setBuyLimitPrice(BigDecimal.ZERO);
        }
        stockPlan.setUserId(currentUser.getUserId());
        try {
            if (stockPlan.getBuyPriceType().equals("1")
                    && stockPlan.getBuyLimitPrice().compareTo(BigDecimal.ZERO) < 1) {
                //如果是限价就不能小于等于0,市价时为0
                throw new RuntimeException("必须输入正确的限价");
            }
            TStockPlan tStockPlan = webService.createStockPlan(stockPlan);
            addModel(model, "msg", "申请成功");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
            return redirect("/mobile/option/labels");
        }
        return redirect("/mobile/stock/plan/list?orderStatus=1");
    }

    /**
     * 行权

     */
    @RequestMapping("mobile/stock/exercise")
    public String doWebStockExercise(Model model, TStockPlan stockPlan) {
        try {
            webService.exercise(stockPlan);
            addModel(model, "msg", "行权申请成功");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", "行权申请失败,原因:" + e.getMessage());
        }
        return redirect("/mobile/stock/plan/list?orderStatus=3");
    }

    /**
     * 查询股票列表返回json
     *
     * @return
     */
    @RequestMapping(value = "/mobile/stock/list/json")
    public void toWebStockListJson(HttpServletResponse response,
                                   SearchPriceContentModel stockModel, String Page_size, String Page_no) {
        String msg;
        Map map = new HashMap();
        try {
            String max = stockModel.getMax_option_price();
            String min = stockModel.getMin_option_price();

            if (StringUtils.isNotEmpty(max) || StringUtils.isNotEmpty(min)) {
                TDictionary dictionary = dictionaryService.getDictionary("MANAGE_FEE", true);
                BigDecimal chuShu = new BigDecimal(dictionary.getItemCode()).add(BigDecimal.ONE);

                if (StringUtils.isNotEmpty(max)) {
                    BigDecimal bigDecimalMax = new BigDecimal(max).divide(chuShu, 3, BigDecimal.ROUND_HALF_UP);
                    stockModel.setMax_option_price(bigDecimalMax.toString());
                }
                if (StringUtils.isNotEmpty(min)) {
                    BigDecimal bigDecimalMin = new BigDecimal(min).divide(chuShu, 3, BigDecimal.ROUND_HALF_UP);
                    stockModel.setMin_option_price(bigDecimalMin.toString());
                }
            }

            List stockList = webService.queryStockListByApi(
                    stockModel.getSymbols(),
                    stockModel.getCycle(),
                    stockModel.getMax_option_price(),
                    stockModel.getMin_option_price(),
                    "15",
                    "1");
            super.writeJsonData(response, stockList);
        } catch (Exception e) {
            List stockList = new ArrayList();
            logger.error(e);
            msg = "系统繁忙请重新提交";
            map.put("stockList", stockList);
            map.put("msg", msg);
            super.writeJsonData(response, JSONArray.fromObject(map));
        }
    }

    /**
     * 查询自选标的列表
     *
     * @param response
     * @param stockModel
     * @param Page_size
     * @param Page_no
     * @param session
     */
    @RequestMapping(value = "/mobile/stock/optional/labels/json")
    public void toMobileOptionalLabelsStockListJson(HttpServletResponse response,
                                                    SearchPriceContentModel stockModel, String Page_size, String Page_no, HttpSession session, TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        List stockList = webService.toMobileOptionalLabelsStockList(stockModel, currentUser, userStock);
        super.writeJsonData(response, stockList);
    }

    @RequestMapping(value = "/mobile/user/stock/save")
    public void doWebUserStockSave(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        Map map = new HashMap();
        List<TUserStock> userStockList = new ArrayList<TUserStock>();
        String msg;
        try {
            if (currentUser != null) {
                webService.saveUserStock(currentUser.getUserId(), userStock.getSymbol());
                userStock.setSymbol(null);
                userStockList = webService.queryUserStockList(currentUser.getUserId(), userStock);
            }
            msg = "success";
        } catch (Exception e) {
            msg = "收藏失败,原因:" + e.getMessage();
        }
        map.put("userStockList", userStockList);
        map.put("msg", msg);
        super.writeJsonData(response, JSONArray.fromObject(map));
    }

    @RequestMapping(value = "/mobile/user/stock/delete")
    public void doWebUserStockDelete(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        Map map = new HashMap();
        List<TUserStock> userStockList = new ArrayList<TUserStock>();
        String msg;
        try {
            if (currentUser != null) {
                webService.deleteUserStock(currentUser.getUserId(), userStock.getSymbol());
                userStock.setSymbol(null);
                userStockList = webService.queryUserStockList(currentUser.getUserId(), userStock);
            }
            msg = "success";
        } catch (Exception e) {
            msg = "删除收藏失败,原因:" + e.getMessage();
        }
        map.put("userStockList", userStockList);
        map.put("msg", msg);
        super.writeJsonData(response, JSONArray.fromObject(map));
    }

    /**
     * 投顾协议
     *
     * @return
     */
    @RequestMapping("mobile/investment/agreement")
    public String touuguxieyi() {
        return "pc/mobile/investment_agreement";
    }

    /**
     * 网站服务协议
     *
     * @return
     */
    @RequestMapping("mobile/network/service/protocol")
    public String networkServiceProtocol() {
        return "pc/mobile/network_service_protocol";
    }

    /**
     * 请求新浪股票数据
     */
    @RequestMapping("mobile/query/sina/data")
    public void queryDataFromSina(HttpServletResponse response, String symbol) {
        String[] result = webService.queryDataFromSina(symbol);
        super.writeJsonData(response, "utf-8", JSONArray.fromObject(result));
    }

    /**
     * 发送验证码到手机
     *
     * @param response
     * @param userTel
     */
    @RequestMapping("mobile/user/register/send/identity/code")
    public void sendIdentityCode(HttpServletResponse response, String userTel) {
        int result = webService.sendIdentityCode(userTel);
        super.writeJsonData(response, "utf-8", JSONArray.fromObject(result));
    }


    /**
     * 发送订单信息
     *
     * @param model
     * @param session
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/mobile/stock/payeasy/deposit", method = RequestMethod.POST)
    public String doWebStockvPayeasyDeposit(Model model, HttpSession session, String payType, BigDecimal money, StandardPaymentRequestEntity requestEntity) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        requestEntity.setV_amount(money);
        model.addAttribute("payment", webService.getStandardPaymentInfo(requestEntity, currentUser));
        return "pc/mobile/payeasy_deposit";
    }

    /**
     * 接收首信易支付返回值
     *
     * @return
     */
    @RequestMapping("mobile/stock/payeasy/receive/data")
    public String getReturnData(StandardPaymentRetuenEntity retuenEntity, Model model) {
        model.addAttribute("result", webService.getStandardPaymentReturnData(retuenEntity));
        return "pc/mobile/payment_result";
    }

    /**
     * 接收首易信发送订单信息
     *
     * @param orderParmentResultReturnEntity
     */
    @RequestMapping("mobile/payeasy/query/order/result")
    public void getOrderPaymentResult(HttpServletResponse response, OrderParmentResultReturnEntity orderParmentResultReturnEntity) {
        int result = webService.getOrderPaymentResult(orderParmentResultReturnEntity);
        String message = "success";
        if (result == 0) {
            message = "error";
        }
        //TODO 需要返回message
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转申请方案
     *
     * @return
     */
    @RequestMapping("mobile/application/scheme")
    public String toApplicationScheme(Model model, String symbol, String symbolName, BigDecimal manageFee, BigDecimal curPrice) {
        model.addAttribute("symbol", symbol);
        model.addAttribute("symbolName", symbolName);
        model.addAttribute("manageFee", manageFee);
        model.addAttribute("curPrice", curPrice);
        return "pc/mobile/application_scheme";
    }

    @RequestMapping("mobile/option/labels")
    public String toUserStockList(HttpSession session, Model model, TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser != null) {
            List<TUserStock> userStockList = webService.queryUserStockList(currentUser.getUserId(), userStock);
            addModel(model, "userStockList", JSONArray.fromObject(userStockList));
        } else {
            addModel(model, "userStockList", "[]");
        }
        addModel(model, "manageFee", dictionaryService.get(TDictionary.class, "parentCode", "MANAGE_FEE"));
        return "pc/mobile/optional_labels";
    }

    /**
     * mobile确认方案的跳转路径
     *
     * @return
     */
    @RequestMapping("mobile/validation/scheme")
    public String toValidationScheme(Model model, String symbol, String symbolName, String manageFee,
                                     String hiddenMarketValue, String stockPlanCycleValue,
                                     String stockPriceType, String buyPrice, String payManageFee, String curPrice) {
        Map map = new HashMap();
        map.put("symbol", symbol);
        map.put("curPrice", curPrice);
        map.put("symbolName", symbolName);
        map.put("manageFee", manageFee);
        map.put("hiddenMarketValue", hiddenMarketValue);
        map.put("stockPlanCycleValue", stockPlanCycleValue);
        map.put("stockPriceType", stockPriceType);
        map.put("buyPrice", buyPrice);
        map.put("payManageFee", payManageFee);
        model.addAttribute("planInfo", map);
        return "pc/mobile/validation_scheme";
    }

    /**
     * 个人中心中的规则中心
     *
     * @return
     */
    @RequestMapping("mobile/rule/center")
    public String toRuleCenter() {
        return "pc/mobile/capital_details";
    }

    @RequestMapping("mobile/register/success")
    public String toMobileRegisterSuccess() {
        return "pc/mobile/register_success";
    }


    @RequestMapping("mobile/testscanpay")
    public String testscanpay() {
        return "pc/mobile/testscanpay";
    }


    /**
     * 功能描述: 获取股票行情
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 15:06
     *
     * @param response
     * @param symbol
     */
    @RequestMapping("mobile/getMarket")
    public void getMarket(HttpServletResponse response, String symbol) {

        AppCommonModel model = null;

        try {
            model = appUserStockService.getMarket(symbol);
        } catch (Exception ex) {
            logger.error("mobile/getMarket:" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }

        super.writeAppDataEncryption(response, model);
    }


    /**
     * 功能描述:获取用户自选股列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 14:37
     *
     * @param response
     * @param userStock
     */
    @RequestMapping("mobile/getUserStockList")
    public void getUserStockList(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser curUser = HttpSessionUtils.getCurrentUser(session);
        AppCommonModel model = null;

        try {
            model = appUserStockService.getList(userStock, curUser);
        } catch (Exception ex) {
            logger.error("mobile/getUserStockList:" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }

        super.writeAppDataEncryption(response, model);
    }


    /**
     * 功能描述: 获取股票基本信息列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 16:38
     *
     * @param response
     * @param stockInfo
     */
    @RequestMapping("mobile/getList2")
    public void getList(HttpServletResponse response, TStockInfo stockInfo) {

        AppCommonModel model = null;

        try {
            model = appUserStockService.getList2(stockInfo);
        } catch (Exception ex) {
            logger.error("mobile/getList2:" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }

        super.writeAppDataEncryption(response, model);
    }


    /**
     * 功能描述: 自选股-加入自选股
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 17:05
     *
     * @param response
     * @param userStock
     */
    @RequestMapping("mobile/createUserStock")
    public void createUserStock(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser curUser = HttpSessionUtils.getCurrentUser(session);
        AppCommonModel model = null;

        try {
            model = appUserStockService.createUserStock(userStock, curUser);
        } catch (Exception ex) {
            logger.error("mobile/createUserStock:" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }

        super.writeAppDataEncryption(response, model);
    }


    /**
     * 功能描述: 自选股-删除自选股
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 18:33
     *
     * @param response
     * @param userStock
     */
    @RequestMapping("mobile/deleteUserStock")
    public void deleteUserStock(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser curUser = HttpSessionUtils.getCurrentUser(session);
        AppCommonModel model = null;

        try {
            model = appUserStockService.deleteUserStock(userStock, curUser);
        } catch (Exception ex) {
            logger.error("mobile/deleteUserStock:" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }

        super.writeAppDataEncryption(response, model);
    }


    /**
     * 功能描述: 根据股票代码，获取指定的股票名称
     * 创建者: zhangwei
     * 创建时间: 2018/01/17 13:45
     *
     * @param response
     * @param code     股票代码
     */
    @RequestMapping("mobile/getStockInfo")
    public void getStockInfo(HttpServletResponse response, String code) {
        AppCommonModel model = null;
        try {
            model = appUserStockService.getStockInfo(code);
        } catch (Exception ex) {
            logger.error("mobile/getStockInfo:" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }
        super.writeAppDataEncryption(response, model);
    }


    @RequestMapping("mobile/getUserRecord")
    public void getList(HttpServletResponse response, TUserAccountFlow userAccountFlow, HttpSession session) {
        TUser curUser = HttpSessionUtils.getCurrentUser(session);
        AppCommonModel model = null;
        try {
            model = appUserRecordService.getList(userAccountFlow, curUser);
        } catch (Exception ex) {
            logger.error("mobile/getUserRecord" + ex.getMessage());
            model = new AppCommonModel(-1, ex.getMessage());
        }

        super.writeAppDataEncryption(response, model);
    }


    /**
     * 加载K线图数据
     *
     * @param response
     */
    @RequestMapping("mobile/getKlineData")
    public void getKlineData(HttpServletResponse response, String code, String KlineType) {
        List data = GetGuPiao.getKlineData(code, KlineType);
        super.writeJsonData(response, data);
    }

    /**
     * 功能描述：获取走势图数据
     *
     * @param productCode
     * @param response
     * @author wangyf 2017-12-25 16:58
     */
    @RequestMapping("mobile/getZSData")
    public void getZSData(String productCode, HttpServletResponse response) {
        List list = GetGuPiao.getZSData(productCode);
        super.writeJsonData(response, list);
    }

    @RequestMapping("mobile/toPay")
    public String toPay(HttpServletResponse response, HttpServletRequest request) {
        return "pc/mobile/recharge";
    }

    @RequestMapping("mobile/pay")
    public void pay(HttpServletResponse response, HttpServletRequest request, HttpSession session, String payType, BigDecimal rechargeMoney) throws Exception {

        TUser curUser = HttpSessionUtils.getCurrentUser(session);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        TOrderInfo orderInfo = orderInfoService.createOrderInfo(rechargeMoney, curUser);


        // 支付请求地址
        String reqUrl = "https://api.ddbill.com/gateway/api/scanpay";

        // 支付请求返回结果
        String result = null;

        // 接收表单提交参数
        request.setCharacterEncoding("UTF-8");
        String merchant_code = "1000774999";
        String service_type = payType;
        String notify_url = CommonMethod.getVisitPath(request) + "mobile/notifyUrl";
        //String notify_url ="http://121.199.25.216:9191/huaibeiqiquan/mobile/notifyUrl";
        String interface_version = "V3.3";
        String client_ip = request.getRemoteAddr();
        String sign_type = "RSA-S";
        String order_no = orderInfo.getOrderNum();
        String order_time = sdf.format(orderInfo.getOrderCreateTime());
        String order_amount = String.valueOf(orderInfo.getActualPrice());
        String product_name = "充值";

        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("merchant_code", merchant_code);
        reqMap.put("service_type", service_type);
        reqMap.put("notify_url", notify_url);
        reqMap.put("interface_version", interface_version);
        reqMap.put("client_ip", client_ip);
        reqMap.put("sign_type", sign_type);
        reqMap.put("order_no", order_no);
        reqMap.put("order_time", order_time);
        reqMap.put("order_amount", order_amount);
        reqMap.put("product_name", product_name);

        /** 数据签名
         签名规则定义如下：
         （1）参数列表中，除去sign_type、sign两个参数外，其它所有非空的参数都要参与签名，值为空的参数不用参与签名；
         （2）签名参数排序按照参数名a到z的顺序排序，若遇到相同首字母，则看第二个字母，以此类推，组成规则如下：
         参数名1=参数值1&参数名2=参数值2&……&参数名n=参数值n		*/

        StringBuffer signSrc = new StringBuffer();
        signSrc.append("client_ip=").append(client_ip).append("&");
        signSrc.append("interface_version=").append(interface_version).append("&");
        signSrc.append("merchant_code=").append(merchant_code).append("&");
        signSrc.append("notify_url=").append(notify_url).append("&");
        signSrc.append("order_amount=").append(order_amount).append("&");
        signSrc.append("order_no=").append(order_no).append("&");
        signSrc.append("order_time=").append(order_time).append("&");
        signSrc.append("product_name=").append(product_name).append("&");
        signSrc.append("service_type=").append(service_type);

        String signInfo = signSrc.toString();
        String sign = "";

        // sign_type = "RSA-S"
        if ("RSA-S".equals(sign_type)) {

            /**
             1)merchant_private_key，商户私钥，商户按照《密钥对获取工具说明》操作并获取商户私钥；获取商户私钥的同时，也要获取商户公钥（merchant_public_key）；调试运行
             代码之前首先先将商户公钥上传到多得宝商家后台"支付管理"->"公钥管理"（如何获取和上传请查看《密钥对获取工具说明》），不上传商户公钥会导致调试运行代码时报错。
             2)demo提供的merchant_private_key是测试商户号1111110166的商户私钥，请自行获取商户私钥并且替换	*/

            String merchant_private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANussHVpZX4rPDkx\n" +
                    "1S6l/5wgstTnomF3lOJJaC3GGdYufodLrODdYF0o5Ynx1/iNbsL6OHBlD5P8RVy/\n" +
                    "OBi5c20GyktrHXkwmf8rHGAZy4SBJNX8ef+E6ua5rvFco+HSNKqboGCzcuC2NTf9\n" +
                    "bGtDwKx3VwGDtRuJ/umAEGSaa3lLAgMBAAECgYEAkBlRj1NZ3k+iK6qCuxzs9Y+F\n" +
                    "f5+KpwD4Sw/4JL6kSBkrqrh7aO4Ovk2LdNeN4l90XrYgu6mvdFWxckuFEsLj8VO5\n" +
                    "335x0oJNBoFOWFoLwzlQw7Q8pfSuXo+dtufGAJjfNyXCD4dRXxiKgZ6R9xAF2aZm\n" +
                    "SLqc9u5afLJOvChwQFECQQDt8u4qoyzsyB3ZNsiE+LyaYyfJUU90dvNOXvilnKQR\n" +
                    "XxhrZ1ogz+g/sXDtZ/hIgQlbt65AgcWIHGB19XGcutU/AkEA7Fbc3OeHRuhA9xFs\n" +
                    "CXd8L0l4Jb8YREImGGIYVqAZwb2SopjFulmp4gnPFVTjx1o1sQrdEJkMRCqGAnl5\n" +
                    "XJWc9QJAV0axgMYVJkxIVnG3nr41P1N7NmYyhH9Uwrwm62fG7fA5Xrur3Tkk8Ke+\n" +
                    "yfVGpiFeM1vt+A8hSslNwDZkk54btQJAUHU11YKAvDP/Qd1NgV+LCHIjk/4dXceD\n" +
                    "PpPOo1b3zcwi6nXGAq3ZttkOBb4TmV9tnesL3UWtNlufqaH/HfTVLQJBALyaXZYy\n" +
                    "SWF8CFqqiD61ZvEy8hS3jjviOqNC2aQ7+GlhypKnXJhUYwNUSoSuoz6UKMjWF49m\n" +
                    "nJ6N5Fd9qS/D1wI=";

            // 签名   signInfo签名参数排序，  merchant_private_key商户私钥
            sign = PayUtil.signByPrivateKey(signInfo, merchant_private_key);
            reqMap.put("sign", sign);

            // 向多得宝发送POST请求
            result = new HttpClientUtil().doPost(reqUrl, reqMap, "utf-8");
        }

       /* if ("RSA".equals(sign_type)) { // 数字证书加密方式 sign_type = "RSA"

            // 请在商家后台"支付管理"->"证书下载"处申请和下载pfx数字证书，一般要1~3个工作日才能获取到，1111110166.pfx是测试商户号1111110166的数字证书
            String webRootPath = request.getSession().getServletContext().getRealPath("/");
            String merPfxPath = webRootPath + "pfx/1111110166.pfx";                // 商家的pfx证书文件路径
            String merPfxPass = "1111110166";                                            // 商家的pfx证书密码,初始密码是商户号
            RSAWithHardware mh = new RSAWithHardware();
            mh.initSigner(merPfxPath, merPfxPass);
            sign = mh.signByPriKey(signInfo);                                        // 签名   signInfo签名参数排序
            reqMap.put("sign", sign);
            result = new HttpClientUtil().doPost(reqUrl, reqMap, "utf-8");            // 向多得宝发送POST请求
        }*/

        System.out.println("签名参数排序：" + signInfo.length() + " --> " + signInfo);
        System.out.println("sign值：" + sign.length() + " --> " + sign);
        System.out.println("result值：" + result);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

        PrintWriter pw = response.getWriter();

        // 返回result数据给请求页面
        pw.write(result);
        pw.flush();
        pw.close();
    }


    @RequestMapping("mobile/notifyUrl")
    public void toNotifyUrl(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

//        TUser curUser=HttpSessionUtils.getCurrentUser(session);

        // 接收多得宝返回的参数
        request.setCharacterEncoding("UTF-8");
        String interface_version = (String) request.getParameter("interface_version");
        String merchant_code = (String) request.getParameter("merchant_code");
        String notify_type = (String) request.getParameter("notify_type");
        String notify_id = (String) request.getParameter("notify_id");
        String sign_type = (String) request.getParameter("sign_type");
        String dinpaySign = (String) request.getParameter("sign");
        String order_no = (String) request.getParameter("order_no");
        String order_time = (String) request.getParameter("order_time");
        //该笔订单实际支付金额，以元为单位，精确到小数点后两位
        String order_amount = (String) request.getParameter("order_amount");
        String extra_return_param = (String) request.getParameter("extra_return_param");
        String trade_no = (String) request.getParameter("trade_no");
        String trade_time = (String) request.getParameter("trade_time");
        String trade_status = (String) request.getParameter("trade_status");
        String bank_seq_no = (String) request.getParameter("bank_seq_no");
        //订单原金额,与币种对应
        String orginal_money = (String) request.getParameter("orginal_money");

        System.out.println("interface_version = " + interface_version + "\n" +
                "merchant_code = " + merchant_code + "\n" +
                "notify_type = " + notify_type + "\n" +
                "notify_id = " + notify_id + "\n" +
                "sign_type = " + sign_type + "\n" +
                "dinpaySign = " + dinpaySign + "\n" +
                "order_no = " + order_no + "\n" +
                "order_time = " + order_time + "\n" +
                "order_amount = " + order_amount + "\n" +
                "extra_return_param = " + extra_return_param + "\n" +
                "trade_no = " + trade_no + "\n" +
                "trade_time = " + trade_time + "\n" +
                "trade_status = " + trade_status + "\n" +
                "bank_seq_no = " + bank_seq_no + "\n");

        /** 数据签名
         签名规则定义如下：
         （1）参数列表中，除去sign_type、sign两个参数外，其它所有非空的参数都要参与签名，值为空的参数不用参与签名；
         （2）签名参数排序按照参数名a到z的顺序排序，若遇到相同首字母，则看第二个字母，以此类推，组成规则如下：
         参数名1=参数值1&参数名2=参数值2&……&参数名n=参数值n		*/

        StringBuilder signStr = new StringBuilder();
        if (null != bank_seq_no && !bank_seq_no.equals("")) {
            signStr.append("bank_seq_no=").append(bank_seq_no).append("&");
        }
        if (null != extra_return_param && !extra_return_param.equals("")) {
            signStr.append("extra_return_param=").append(extra_return_param).append("&");
        }
        signStr.append("interface_version=").append(interface_version).append("&");
        signStr.append("merchant_code=").append(merchant_code).append("&");
        signStr.append("notify_id=").append(notify_id).append("&");
        signStr.append("notify_type=").append(notify_type).append("&");
        signStr.append("order_amount=").append(order_amount).append("&");
        signStr.append("order_no=").append(order_no).append("&");
        signStr.append("order_time=").append(order_time).append("&");
        //订单原金额,与币种对应
        signStr.append("orginal_money=").append(orginal_money).append("&");
        signStr.append("trade_no=").append(trade_no).append("&");
        signStr.append("trade_status=").append(trade_status).append("&");
        signStr.append("trade_time=").append(trade_time);


        String signInfo = signStr.toString();
        System.out.println("多得宝返回的签名参数排序：" + signInfo.length() + " -->" + signInfo);
        System.out.println("多得宝返回的签名：" + dinpaySign.length() + " -->" + dinpaySign);
        boolean result = false;

        // sign_type = "RSA-S"
        if ("RSA-S".equals(sign_type)) {

            /**
             1)dinpay_public_key，多得宝公钥，每个商家对应一个固定的多得宝公钥（不是使用工具生成的商户公钥merchant_public_key，不要混淆），
             即为多得宝商家后台"支付管理"->"公钥管理"->"多得宝公钥"里的绿色字符串内容
             2)demo提供的dinpay_public_key是测试商户号1111110166的多得宝公钥，请自行复制对应商户号的多得宝公钥进行调整和替换	*/
            String dinpay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgGTIN7NoiJr7yyD2wa7SelUifJ5PUtPosQkE46me3CJ8SBHCJZ5sm+tnlLQ6obBSZZ8nUatdkBeoUau0yFFPQbVKKCgt4hMIArBs8rpVRGAGf3LsmAmTu6x1Qki3NgcqKVeXbNUwJVaWs8gi+6NYouu6wuIY/B5d2qdr8qiPs6QIDAQAB";

            // 验签   signInfo多得宝返回的签名参数排序， dinpay_public_key多得宝公钥， dinpaySign多得宝返回的签名
            result = PayUtil.validateSignByPublicKey(signInfo, dinpay_public_key, dinpaySign);
        }

       /* if("RSA".equals(sign_type)){ // 数字证书加密方式 sign_type = "RSA"

            // 请在商家后台"支付管理"->"证书下载"处申请和下载pfx数字证书，一般要1~3个工作日才能获取到，1111110166.pfx是测试商户号1111110166的数字证书
            String webRootPath = request.getSession().getServletContext().getRealPath("/");
            String merPfxPath = webRootPath + "pfx/1111110166.pfx"; 									// 商家的pfx证书文件路径
            String merPfxPass = "87654321";			  													// 商家的pfx证书密码,初始密码是商户号
            RSAWithHardware mh = new RSAWithHardware();
            mh.initSigner(merPfxPath, merPfxPass);
            result = mh.validateSignByPubKey(merchant_code, signInfo, dinpaySign);						// 验签    merchant_code为商户号， signInfo多得宝返回的签名参数排序， dinpaySign多得宝返回的签名
        }
*/
        PrintWriter pw = response.getWriter();
        if (result) {

            System.out.println("验签结果result的值：" + result + " -->SUCCESS");

            //修改订单状态,增加用户金额和增加流水
            int resultFlag = orderInfoService.updateOrderInfo(order_no);

            if (resultFlag == 1) {
                // 验签成功，响应SUCCESS
                pw.write("SUCCESS");
                System.out.println("SUCCESS");

                super.writeText(response, "SUCCESS");
            }


        } else {
            // 验签失败，业务结束
            super.writeText(response, "Signature Error");
            System.out.println("验签结果result的值：" + result + " -->Signature Error");
        }

        pw.flush();
        pw.close();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
    }


}
