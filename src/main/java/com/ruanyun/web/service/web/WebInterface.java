package com.ruanyun.web.service.web;

import com.pay.yspay.bean.PayResult;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.payeasy.OrderParmentResultReturnEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRequestEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRetuenEntity;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.web.SearchPriceContentModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface WebInterface {

    /////////////////////////////
    //        sxb             //
    ////////////////////////////

    /**
     * 查询文章列表
     *
     * @param article 文章对象
     * @return
     */
    Page queryArticleList(Page page, TArticle article);

    /**
     * 查询文章详情
     *
     * @param articleId 文章id
     * @return
     */
    TArticle queryArticle(Integer articleId);

    Map preAndNextArticle(TArticle article);

    /**
     * web用户登录
     *
     * @param loginName
     * @param password
     * @return
     */
    TUser webLogin(HttpServletRequest request, HttpSession session, String loginName, String password);

    /**
     * web用户注册
     *
     * @param name
     * @param telephoneNumber
     * @param password
     * @param parentCode
     * @return
     */
    int webRegister(String name, String telephoneNumber, String password, String parentCode,String identityCode);

    /**
     * 登出
     *
     * @param session
     * @return
     */
    int webLoginout(HttpSession session);

    /**
     * 修改密码
     * @param user
     * @param loginName
     * @param password
     * @param identityCode
     * @return
     */
    int passwordUpdate(TUser user,String loginName,String password, String identityCode);

    /**
     * 找回密码
     *
     * @param telephoneNumber
     * @param identifyingCode
     * @param newPassword
     * @return
     */
    int passwordForget(String telephoneNumber, String identifyingCode, String newPassword);

    /**
     * 实名认证及银行卡绑定
     *
     * @param userInfo
     */
    void authentication(TUserInfo userInfo);

    /**
     * 实名认证及银行卡绑定
     *
     * @param userInfoCheck
     */
    void authenticationCheck(TUserInfoCheck userInfoCheck);

    /**
     * 个人信息展示
     *
     * @param session
     * @return
     */
    TUser queryUserSelfInfo(HttpSession session);

    /**
     * 用户充值
     *
     * @param session
     * @param depositMoney
     * @param depositType
     */
    @Deprecated
    TUserAccount WebStockDeposit(HttpSession session, BigDecimal depositMoney, int depositType);

    /**
     * 用户提现
     *
     * @param session
     * @param withdrawMoney
     */
    @Deprecated
    void WebStockWithdraw(HttpSession session, BigDecimal withdrawMoney);

    /**
     * 生成推广链接
     *
     * @param session
     * @return
     */
    String queryPromotion(HttpSession session);


    /***********************/
    /*** 下面是交易中心部分 ***/
    /***********************/


    /**
     * 查询报价信息列表
     *
     * @param searchPriceContentModel
     * @return
     */
//    @Deprecated
//    List queryStockListByApi(OptionModel optionModel, SearchPriceContentModel searchPriceContentModel);

    /**
     * 调用股票接口查询费率等信息,这是新版
     * @param symbols
     * @param cycle
     * @param Max_option_price
     * @param Min_option_price
     * @param pageSize
     * @param pageNo
     * @return
     */
    List queryStockListByApi(String[] symbols, String cycle, String Max_option_price, String Min_option_price, String pageSize, String pageNo);


    /**
     * 查询股票
     *
     * @param stock
     * @return
     */
    List<TStock> queryStockList(TStock stock);


    String[] queryDataFromSina(String data);
    /**
     * 申请方案
     *
     * @param stockPlan
     * @return
     */
    TStockPlan createStockPlan(TStockPlan stockPlan);

    /**
     * 查询单个股权方案
     *
     * @param stockPlan
     * @return
     */
    TStockPlan queryStockPlan(TStockPlan stockPlan);

    /**
     * 查询多个股权方案
     *
     * @param stockPlan
     * @return
     */
    List<TStockPlan> queryStockPlanList(TStockPlan stockPlan);

    List<Map> queryStockPlanListMap(TStockPlan stockPlan, TUser user);

    /**
     * 充值
     *
     * @param money
     * @param payType
     */
    void deposit(int userId, BigDecimal money, String payType);

    /**
     * 提现
     *
     * @param money
     */
    void withdraw(int userId, BigDecimal money);

    /**
     * 行权
     *
     * @param stockPlan
     */
    void exercise(TStockPlan stockPlan);

    /**
     * 查询用户资金账户
     *
     * @param userId
     * @return
     */
    TUserAccount queryUserAccount(int userId);

    /**
     * 查询用户认证信息
     *
     * @param userId
     * @return
     */
    TUserInfo queryUserInfo(int userId);

    /**
     * 查询用户审核认证信息
     *
     * @param userId
     * @return
     */
    TUserInfoCheck queryUserInfoCheck(int userId, String checkResult);

    /**
     * 查询用户收藏的股票
     *
     * @param userId
     * @return
     */
    List queryUserStockList(int userId,TUserStock userStock);


    /**
     * 自选标的列表
     * @return
     */
    List toMobileOptionalLabelsStockList(SearchPriceContentModel stockModel,TUser currentUser,TUserStock userStock);

    /**
     * 新增用户收藏股票
     *
     * @param userId
     * @param symbol
     */
    void saveUserStock(int userId, String symbol);

    /**
     * 删除收藏的股票
     * @param userId
     * @param symbol
     */
    void deleteUserStock(int userId, String symbol);

    /**
     *  身份证唯一性判断
     */
    int idCardNumCheck(String idCardNumber, Integer userId);

    /**
     * 发送验证码
     * @param userTel 手机号
     * @return
     */
    int sendIdentityCode(String userTel);

    /**
     * 准备提交首信易支付订单数据
     * @return
     */
    StandardPaymentRequestEntity getStandardPaymentInfo(StandardPaymentRequestEntity RequestEntity,TUser currentUser);

    /**
     * 接收首信易返回参数
     * @param retuenEntity
     * @return
     */
    int getStandardPaymentReturnData(StandardPaymentRetuenEntity retuenEntity);

    /**
     * 首信易发送订单支付结果数据
     * @param orderParmentResult
     * @return
     */
    int getOrderPaymentResult(OrderParmentResultReturnEntity orderParmentResult);


    int checkBankNumberAndBankId (TUserInfo userInfo, Integer currentUserId);

    TDictionary updateWithdrawFee(TDictionary dictionary);
}
