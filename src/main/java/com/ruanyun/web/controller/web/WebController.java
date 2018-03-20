package com.ruanyun.web.controller.web;

import com.pay.yspay.bean.PayOrder;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.ImageUtil;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.bank.BankGatewayPaymentRequest;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.payeasy.OrderParmentResultReturnEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRequestEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRetuenEntity;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.web.SearchPriceContentModel;
import com.ruanyun.web.service.mall.AdverInfoService;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.BusinessOrderService;
import com.ruanyun.web.service.web.UserInfoService;
import com.ruanyun.web.service.web.WebInterface;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateJsonValueProcessor;
import com.ruanyun.web.util.HttpSessionUtils;
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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class WebController extends BaseController {

    @Autowired
    @Qualifier("webService")
    private WebInterface webService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private AdverInfoService adverInfoService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private BusinessOrderService businessOrderService;


    /**
     * 访问首页
     *
     * @return
     */
    @RequestMapping(value = "/web/index", method = RequestMethod.GET)
    public String toWebIndex(Model model, Page page, TArticle article) {
        page.setNumPerPage(3);
        article.setMediaType(1);
        model.addAttribute("dataList", JSONArray.fromObject(webService.queryStockList(new TStock())));
        model.addAttribute("page", webService.queryArticleList(page, article));
        return "pc/web/web_index";
    }

    /**
     * 跳转到投资保障界面
     *
     * @return
     */
    @RequestMapping(value = "/web/protection", method = RequestMethod.GET)
    public String toInvestProtect(TAdverInfo adverInfo, Model model, String flag1) {
        if (EmptyUtils.isNotEmpty(flag1)) {
            adverInfo.setFlag1(flag1);
        } else {
            adverInfo.setFlag1("1");
        }
        TAdverInfo tAdverInfo = adverInfoService.getAdverInfo(adverInfo);
        model.addAttribute("adverInfo", tAdverInfo);
        return "pc/web/invest_protect";
    }

    /**
     * 跳转到文章详情详情
     *
     * @return
     */
    @RequestMapping(value = "/web/news/detail", method = RequestMethod.GET)
    public String toNewsDetail(TArticle article, Model model) {
        model.addAttribute("article", webService.queryArticle(article.getArticleId()));
        model.addAttribute("preAndNext", webService.preAndNextArticle(article));
        return "pc/web/news_details";
    }

    /**
     * 跳转到产品信息界面
     *
     * @return
     */
    @RequestMapping(value = "/web/product", method = RequestMethod.GET)
    public String toProductIntroduction(Model model, TArticle article, Page page) {
        article.setArticleType(3);
        article.setMediaType(2);
        page.setNumPerPage(8);
        model.addAttribute("dataList", JSONArray.fromObject(webService.queryStockList(new TStock())));
        model.addAttribute("pageList", webService.queryArticleList(page, article));
        return "pc/web/product_introduction";
    }

    /**
     * 跳转个股计算
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/count", method = RequestMethod.GET)
    public String toStockCount() {
        return "pc/web/stock_count";
    }


    /**
     * 跳转到视频列表界面
     *
     * @return
     */
    @RequestMapping(value = "/web/video/list", method = RequestMethod.GET)
    public String toVideoList() {
        return "pc/web/video_list";
    }

    /**
     *
     */
    @RequestMapping(value = "/web/video/list", method = RequestMethod.POST)
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
    @RequestMapping(value = "/web/video/detail", method = RequestMethod.GET)
    public String toVideoDetail() {
        return "pc/web/video_detail";
    }

    /**
     * 跳转到产品介绍界面
     *
     * @return
     */
    @RequestMapping(value = "/web/product/about", method = RequestMethod.GET)
    public String toProductAbout() {
        return "pc/web/product_about";
    }

    /**
     * 跳转到期货案例界面
     *
     * @return
     */
    @RequestMapping(value = "/web/product/case", method = RequestMethod.GET)
    public String toProductCase() {
        return "pc/web/product_case";
    }

    /**
     * 产品特点
     *
     * @return
     */
    @RequestMapping(value = "/web/product/feature", method = RequestMethod.GET)
    public String toProductFeature() {
        return "pc/web/product_feature";
    }

    /**
     * 期权期货区别
     *
     * @return
     */
    @RequestMapping(value = "/web/product/difference", method = RequestMethod.GET)
    public String toProductDifference() {
        return "pc/web/product_difference";
    }

//    /**
//     * 跳转到行业资讯列表
//     *value值与后面的重复，且无法体现具体的功能意义，于是注释了此URL
//     * @return
//     */
//    @RequestMapping(value = "/web/news/list",method = RequestMethod.GET)
//    public String toNewsList() {
//        return "pc/web/product_difference";
//    }


    /**
     * 跳转行业资讯
     * 将url做了修改，从url本身并不能看出意义，索性改成具体含义的url
     *
     * @return
     */
    @RequestMapping(value = "/web/industry/information/list", method = RequestMethod.GET)
    public String toIndustryInfoList(TArticle article, Page page, Model model) {
        article.setMediaType(1);//作为媒体类型，文章1  视频2
        article.setArticleType(1);//作为文章的类型 行业资讯1 实时解盘2 视频分类暂定为3
        model.addAttribute("page", webService.queryArticleList(page, article));
        return "pc/web/industry_information";
    }

    @RequestMapping(value = "/web/industry/information/list", method = RequestMethod.POST)
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
//    @RequestMapping(value = "/web/industry/information/detail", method = RequestMethod.GET)
//    public String toIndustryInfoDetail() {
//        return "pc/web/industry_information";
//    }

    /**
     * 实时开户
     * 跳转到实时开户
     *
     * @return
     */
    @RequestMapping(value = "/web/realTime/open", method = RequestMethod.GET)
    public String toRealTimeOpen(HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/web/stock/login");
        }
//        if ("没有实名验证".equals("没有实名验证")) {
//            return redirect("/web/realTime/authentication");
//        }
        return redirect("/web/stock/userinfo");
//        return "pc/web/realTimeOpening_personalData";
    }

    /**
     * 实名认证
     * 跳转到实时开户
     *
     * @return
     */
//    @Deprecated
//    @RequestMapping(value = "/web/realTime/authentication", method = RequestMethod.GET)
//    public String toRealTimeAuthentication(HttpSession session) {
//        return "pc/web/realTimeOpening_realName_authentication";
//    }

    /**
     * 关于我们
     *
     * @return
     */
    @RequestMapping(value = "/web/aboutus", method = RequestMethod.GET)
    public String toAboutsUs() {
        return "pc/web/abouts_us";
    }

    /**
     * 集团介绍
     *
     * @return
     */
    @RequestMapping(value = "/web/aboutgroup", method = RequestMethod.GET)
    public String toAboutGroup() {
        return "pc/web/abouts_us";
    }

    /**
     * 跳转到实时解盘列表界面
     *
     * @return
     */
    @RequestMapping(value = "/web/latest/analysis/information/list", method = RequestMethod.GET)
    public String toWebNewsList(TArticle article, Page page, Model model) {
        article.setMediaType(1);//作为媒体类型，文章1  视频2
        article.setArticleType(2);//作为文章的类型 行业资讯1 实时解盘2
        model.addAttribute("pageList", webService.queryArticleList(page, article));
        return "pc/web/realTime_Jiepan";
    }

    @RequestMapping(value = "/web/latest/analysis/information/list", method = RequestMethod.POST)
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
//    @RequestMapping(value = "/web/realtime/analysis/news/detail", method = RequestMethod.GET)
//    public String toWebNewsDetail() {
//        return "pc/web/web_news_detail";
//    }

    /**
     * 跳转到软件下载界面，由扫描二维码链接到资源位置进行下载
     *
     * @return
     */
    @RequestMapping(value = "/web/download", method = RequestMethod.GET)
    public String toWebDownload() {
        return "pc/web/web_download";
    }

    /**
     * 跳转到快速登录
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/login", method = RequestMethod.GET)
    public String toWebStockLogin() {
        return "pc/web/realTimeOpening_login";
    }

    /**
     * 执行登录操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/login", method = RequestMethod.POST)
    public String doWebStockLogin(Model model, HttpServletRequest request, HttpSession session, TUser user) {
        if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getLoginPass())) {
            addModel(model, "msg", "请输入手机号和密码");
            return redirect("/web/stock/login");
        }
        try {
            TUser loginUser = webService.webLogin(request, session, user.getLoginName(), user.getLoginPass());
            if (loginUser.getUserType() != Constants.USER_TYPE_CUSTOM_05) {
                addModel(model, "msg", "当前用户不可登录！");
                return redirect("/web/stock/login");
            }
            return redirect("/web/realTime/open");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
            return redirect("/web/stock/login");
        }
    }

    /**
     * 快速注册
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/register", method = RequestMethod.GET)
    public String toWebStockRegister(HttpSession session, String parentCode) {
        session.setAttribute("parentCode", parentCode);
        return "pc/web/realTimeOpening_login";
    }

    /**
     * 执行注册操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/register", method = RequestMethod.POST)
    public String doWebStockRegister(Model model, String nickName, String loginName, String loginPass, String parentCode,String identityCode) {
        String oldParentCode = null;
        String newParentCode = null;
        if (EmptyUtils.isEmpty(parentCode)) {
            addModel(model, "msg", "请输入推广码！");
            return redirect("/web/stock/register");
        } else {
            Integer userId = Integer.parseInt(parentCode);
            TUser user = userService.get(TUser.class, userId);
            if (EmptyUtils.isNotEmpty(user)) {
                if(user.getUserType()!=Constants.USER_TYPE_SPREAD_04){
                    addModel(model, "msg", "请输入正确的推广码！");
                    return redirect("/web/stock/register");
                }
                oldParentCode = user.getParentCode();
            } else {
                addModel(model, "msg", "推广链接失效，请重新获取链接");
                return redirect("/web/stock/register");
            }
        }

        if (EmptyUtils.isEmpty(oldParentCode)) {
            newParentCode = parentCode;
        } else {
            newParentCode = oldParentCode + "," + parentCode;
        }

        try {
            webService.webRegister(nickName, loginName, loginPass, newParentCode,identityCode);
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
            return redirect("/web/stock/login");
        }
        return redirect("/web/stock/login");
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/logout", method = RequestMethod.GET)
    public String doWebStockLogout(HttpSession session) {
        webService.webLoginout(session);
        return redirect("/web/stock/login");
    }

    /**
     * 跳转到修改密码界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/password/update", method = RequestMethod.GET)
    public String toWebStockpasswordUpdate() {
        return "pc/web/realTimeOpening_update_password";
    }

    /**
     * 执行修改密码操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/password/update", method = RequestMethod.POST)
    public String doWebStockpasswordUpdate(Model model,HttpSession session,String loginName,String password,String identityCode) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        int result = 1;
        try {
            result = webService.passwordUpdate(user,loginName,password,identityCode);
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
            return redirect("/web/stock/userinfo");
        }
        if(result==1){
            model.addAttribute("msg","密码修改成功");
            return redirect("/web/stock/logout");
        }else {
            model.addAttribute("msg","密码修改失败");
            return redirect("/web/stock/userinfo");
        }
    }

    /**
     * 跳转到找回密码界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/password/forget", method = RequestMethod.GET)
    public String toWebStockPasswordForget() {
        return "pc/web/web_stock_password_forget";
    }

    /**
     * 执行找回密码操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/password/forget", method = RequestMethod.POST)
    public String doWebStockPasswordForget(Model model,String userTel,String newPassword,String identityCode) {
        try {
            webService.passwordForget(userTel,identityCode,newPassword);
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
            return redirect("/web/stock/login");
        }
        return redirect("/web/stock/logout");
    }

    /**
     * 跳转到实名认证及银行卡绑定界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/authentication", method = RequestMethod.GET)
    public String toWebStockAuthentication(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo userInfo = webService.queryUserInfo(currentUser.getUserId());
        addModel(model, "userInfo", userInfo);
        return "pc/web/realTimeOpening_realName_authentication";
    }

    @RequestMapping(value = "/web/stock/update/authentication", method = RequestMethod.POST)
    public void updateWebStockAuthentication(HttpServletResponse response, Model model, HttpSession session, String imgBase64Data) {
        UploadVo vo = ImageUtil.GenerateImage2(imgBase64Data);
        super.writeText(response, vo.getFilename());
    }

    /**
     * 执行实名认证及银行卡绑定操作
     *
     * @return // TODO: 2017/10/21 验证功能要完善,身份证绑定过后，无法修改银行卡了（怎么解决）
     */
    @RequestMapping(value = "/web/stock/authentication", method = RequestMethod.POST)
    public String doWebStockAuthentication(HttpSession session, TUserInfo userInfo, Model model) {
        if (EmptyUtils.isEmpty(userInfo.getUserName())) {
            addModel(model, "msg", "姓名不能为空");
            return redirect("/web/stock/userinfo");
        }
        if (EmptyUtils.isEmpty(userInfo.getIdNumber())) {
            addModel(model, "msg", "身份证不能为空");
            return redirect("/web/stock/userinfo");
        }
        if (EmptyUtils.isEmpty(userInfo.getIdCardFrontView())) {
            addModel(model, "msg", "身份证正面照不能为空或不能为默认图片");
            return redirect("/web/stock/userinfo");
        }
        if (EmptyUtils.isEmpty(userInfo.getIdCardBackView())) {
            addModel(model, "msg", "身份证反面照不能为空或不能为默认图片");
            return redirect("/web/stock/userinfo");
        }
        if (EmptyUtils.isEmpty(userInfo.getBankId())) {
            addModel(model, "msg", "所属银行不能为空");
            return redirect("/web/stock/userinfo");
        }
        if (EmptyUtils.isEmpty(userInfo.getBankCardNumber())) {
            addModel(model, "msg", "银行卡号不能为空");
            return redirect("/web/stock/userinfo");
        }
        if (EmptyUtils.isEmpty(userInfo.getBackCardPhoto())) {
            addModel(model, "msg", "银行照片不能为空或不能为默认图片");
            return redirect("/web/stock/userinfo");
        }
        TUser user = HttpSessionUtils.getCurrentUser(session);
        Integer userId = user.getUserId();
        String idCardNumber = userInfo.getIdNumber();
        int isOnly = webService.idCardNumCheck(idCardNumber,userId);
        if(isOnly == 1) {
            addModel(model, "msg", "该身份证已绑定！");
            return redirect("/web/stock/userinfo");
        }
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfoCheck userInfoCheck = new TUserInfoCheck();
        TUserInfo oldUserInfo = userInfoService.get(TUserInfo.class,currentUser.getUserId());
        BeanUtils.copyProperties(userInfo, userInfoCheck);
        TUserInfoCheck oldUserInfoCheck = webService.queryUserInfoCheck(userInfoCheck.getUserId(), "0");
        if (EmptyUtils.isNotEmpty(oldUserInfoCheck)) {
            addModel(model, "msg", "您有未审核的认证，请勿再次认证！");
        } else if (userInfo.getBankId().equals(oldUserInfo.getBankId()) && userInfo.getBankCardNumber().equals(oldUserInfo.getBankCardNumber())) {
            addModel(model, "msg", "您银行卡或卡号未修改，请勿重复绑定相同的银行卡！");
        } else {
            userInfoCheck.setCheckResult("0");
            // userInfo.setUserId(currentUser.getUserId());
            webService.authenticationCheck(userInfoCheck);
            addModel(model, "msg", "等待审核中！");
        }
        return redirect("/web/stock/userinfo");
    }

    /**
     * 个人信息展示
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/userinfo", method = RequestMethod.GET)
    public String toWebStockUserinfo(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo userInfo = webService.queryUserInfo(currentUser.getUserId());
        TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
        addModel(model, "userInfo", userInfo);
        addModel(model, "userAccount", userAccount);
//        return "pc/web/realTimeOpening_personalData";
        return "pc/web/realName_authentication";
    }


    /**
     * 跳转到账户充值界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/deposit", method = RequestMethod.GET)
    public String toWebStockDeposit(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
        addModel(model, "userAccount", userAccount);
//        return "pc/web/realTimeOpening_accountRecharge";
        return "pc/web/account_recharge";
    }

    /**
     * 执行账户充值操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/deposit", method = RequestMethod.POST)
    public String doWebStockDeposit(Model model, HttpSession session, BigDecimal money, String payType) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.deposit(currentUser.getUserId(), money, payType);
            addModel(model, "msg", "充值成功");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
        }
        return redirect("/web/stock/deposit");
    }

    /**
     * 跳转到账户提现界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/withdraw", method = RequestMethod.GET)
    public String toWebStockWithdraw(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
        addModel(model, "userAccount", userAccount);
//        return "pc/web/realTimeOpening_accountWithdrawals";
        return "pc/web/web_user_account_withdraw";
    }

    /**
     * 执行账户提现操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/withdraw", method = RequestMethod.POST)
    public String doWebStockWithdraw(Model model, HttpSession session, BigDecimal money) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        try {
            webService.withdraw(currentUser.getUserId(), money);
            addModel(model, "msg", "提现申请成功，请等待人工审核");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", e.getMessage());
        }
        return redirect("/web/stock/withdraw");
    }

    /**
     * 推广链接
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/promotion", method = RequestMethod.GET)
    public String toWebPromotion() {
//        return "pc/web/realTimeOpening_promoteMoney";
        return "pc/web/promote_money";
    }

    /**
     * 跳转到管理方案界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/plan/list", method = RequestMethod.GET)
    public String toWebStockPlanList(Model model, HttpSession session) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/web/stock/login");
        }

        TStockPlan stockPlan = new TStockPlan();
        stockPlan.setUserId(currentUser.getUserId());
        List<Map> stockPlanListMap = webService.queryStockPlanListMap(stockPlan, currentUser);
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

//        return "pc/web/realTimeOpening_myProgramme";
        return "pc/web/my_plan";
    }

    /**
     * 跳转到方案详情界面
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/plan/detail", method = RequestMethod.GET)
    public String toWebStockPlanDetail(HttpServletResponse response, TStockPlan stockPlan) {
        TStockPlan tStockPlan = webService.queryStockPlan(stockPlan);
        super.writeJsonData(response, tStockPlan);
        return "web/stock/plan/detail";
    }

    /**
     * 选择股票标的
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/center", method = RequestMethod.GET)
    public String toWebStockCenter(Model model, HttpSession session, String[] symbols,TUserStock userStock) {
        model.addAttribute("dataList", JSONArray.fromObject(webService.queryStockList(new TStock())));
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser != null) {
            List<TUserStock> userStockList = webService.queryUserStockList(currentUser.getUserId(),userStock);
            addModel(model, "userStockList", JSONArray.fromObject(userStockList));
            TUserAccount userAccount = webService.queryUserAccount(currentUser.getUserId());
            addModel(model, "userAccount", userAccount);
        } else {
            addModel(model, "userStockList", "[]");
        }
        return "pc/web/trading_center";
    }

    /**
     * 跳转到申请方案界面
     *
     * @return
     * @deprecated 并没使用上这个页面
     */
    @Deprecated
    @RequestMapping(value = "/web/stock/plan/create", method = RequestMethod.GET)
    public String toWebStockPlanCreate(
            TStockPlan stockPlan, HttpSession session,
            String cycle, String priceType, String symbol, String symbolName, String price, String managePriceRate, String managePrice
            //cycle=1d&buyPriceType=0&symbol=600757&symbolName=长江传媒&offerPrice=5908&price=5908&managePriceRate=59.08&managePrice=5908&price=5908
    ) {
//        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
//        stockPlan.setSymbolId(symbol);
        TStockPlan tStockPlan = webService.createStockPlan(stockPlan);
        return redirect("/web/realTime/open");
    }

    /**
     * 执行确认申请的操作
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/plan/create", method = RequestMethod.POST)
    public String doWebStockPlanCreate(TStockPlan stockPlan, HttpSession session, Model model) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/web/stock/login");
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
            return redirect("/web/stock/center");
        }
        return redirect("/web/stock/plan/list");
    }

    /**
     * 行权
     *
     * @param model
     * @param stockPlan
     * @return
     */
    @RequestMapping("web/stock/exercise")
    public String doWebStockExercise(Model model, TStockPlan stockPlan) {
        try {
            webService.exercise(stockPlan);
            addModel(model, "msg", "行权申请成功");
        } catch (Exception e) {
            logger.error(e);
            addModel(model, "msg", "行权申请失败,原因:" + e.getMessage());
        }
        return redirect("/web/stock/plan/list");
    }

    /**
     * 查询股票列表返回json
     *
     * @return
     */
    @RequestMapping(value = "/web/stock/list/json")
    public void toWebStockListJson(HttpServletResponse response,
                                   SearchPriceContentModel stockModel, String Page_size, String Page_no) {
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
    }

    /**
     * 查询自选标的列表
     * @param response
     * @param stockModel
     * @param Page_size
     * @param Page_no
     * @param session
     */
    @RequestMapping(value = "/web/stock/optional/labels/json")
    public void toMobileOptionalLabelsStockListJson(HttpServletResponse response,
                                                    SearchPriceContentModel stockModel, String Page_size, String Page_no,HttpSession session,TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        List stockList = webService.toMobileOptionalLabelsStockList(stockModel,currentUser,userStock);
        super.writeJsonData(response, stockList);
    }

    @RequestMapping(value = "/web/user/stock/save")
    public void doWebUserStockSave(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String msg;
        try {
            if (currentUser != null) {
                webService.saveUserStock(currentUser.getUserId(), userStock.getSymbol());
            }
            msg = "success";
        } catch (Exception e) {
            msg = "收藏失败,原因:" + e.getMessage();
        }
        super.writeText(response, msg);
    }

    @RequestMapping(value = "/web/user/stock/delete")
    public void doWebUserStockDelete(HttpServletResponse response, HttpSession session, TUserStock userStock) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String msg;
        try {
            if (currentUser != null) {
                webService.deleteUserStock(currentUser.getUserId(), userStock.getSymbol());
            }
            msg = "success";
        } catch (Exception e) {
            msg = "删除收藏失败,原因:" + e.getMessage();
        }
        super.writeText(response, msg);
    }

    /**
     * 投顾协议
     *
     * @return
     */
    @RequestMapping("web/investment/agreement")
    public String touuguxieyi() {
        return "pc/web/investment_agreement";
    }

    /**
     * 网站服务协议
     *
     * @return
     */
    @RequestMapping("web/network/service/protocol")
    public String networkServiceProtocol() {
        return "pc/web/network_service_protocol";
    }

    /**
     * 请求新浪股票数据
     */
    @RequestMapping("web/query/sina/data")
    public void queryDataFromSina(HttpServletResponse response, String symbol) {
        String[] result = webService.queryDataFromSina(symbol);
        super.writeJsonData(response, "utf-8", JSONArray.fromObject(result));
    }

    /**
     * 发送验证码到手机
     * @param response
     * @param userTel
     */
    @RequestMapping("web/user/register/send/identity/code")
    public void sendIdentityCode(HttpServletResponse response,String userTel){
        int result = webService.sendIdentityCode(userTel);
        super.writeJsonData(response,"utf-8", JSONArray.fromObject(result));
    }


    /**
     * 发送订单信息
     * @param model
     * @param session
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/web/stock/payeasy/deposit", method = RequestMethod.POST)
    public String doWebStockvPayeasyDeposit(Model model, HttpSession session,String payType,BigDecimal money,StandardPaymentRequestEntity requestEntity) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        requestEntity.setV_amount(money);
        model.addAttribute("payment",webService.getStandardPaymentInfo(requestEntity,currentUser));
        return "pc/web/payeasy_deposit";
    }

    /**
     * 接收首信易支付返回值
     * @return
     */
    @RequestMapping("web/stock/payeasy/receive/data")
    public String getReturnData(StandardPaymentRetuenEntity retuenEntity, Model model) {
        model.addAttribute("result", webService.getStandardPaymentReturnData(retuenEntity));
        return "pc/web/payment_result";
    }

    /**
     * 接收首易信发送订单信息
     * @param orderParmentResultReturnEntity
     */
    @RequestMapping("web/payeasy/query/order/result")
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
     * 支付
     *
     * @return
     */
    @RequestMapping(value = "web/payment")
    public String WebPaymentGet(HttpSession session, BigDecimal money, String bank, Model model) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser == null) {
            return redirect("/web/index");
        }


        //先创建订单,再进行支付
        TBusinessOrder businessOrder = new TBusinessOrder(
                null,
                currentUser.getUserId(),
                null,
                1,
                null,
                null,
                null,
                null,
                money,
                null,
                null,
                "银盛支付充值",
                "充值" + money,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                currentUser.getLoginName(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        businessOrder = businessOrderService.save(businessOrder);

        //创建wap手机直连对象
        PayOrder bean = new PayOrder();
        bean.setSubject(currentUser.getUserId() + " 银盛支付充值"+money.toString()+"元");
        bean.setTotal_amount(money.doubleValue());
        bean.setBank_type(bank);
        bean.setBank_account_type("personal");

        model.addAttribute("payModel", bean);

        //return "pc/web/pay_new";
        return "pay/webDirectPay";
    }

}
