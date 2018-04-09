package com.ruanyun.web.service.web.impl;

import com.aliyuncs.exceptions.ClientException;
import com.capinfo.crypt.Md5;
import com.capinfo.crypt.RSA_MD5;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.web.ArticleDao;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TSmsInfo;
import com.ruanyun.web.model.payeasy.OrderParmentResultReturnEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRequestEntity;
import com.ruanyun.web.model.payeasy.StandardPaymentRetuenEntity;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TRole;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.web.SearchPriceContentModel;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.SmsInfoService;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.sys.LoginLogService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.*;
import com.ruanyun.web.sms.SendMessage;
import com.ruanyun.web.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("webService")
public class WebServiceImpl extends BaseServiceImpl implements WebInterface {

    @Autowired
    StockService stockService;
    @Autowired
    UserService userService;
    @Autowired
    StockPlanService stockPlanService;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    LoginLogService loginLogService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserAccountFlowService userAccountFlowService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserInfoCheckService userInfoCheckService;
    @Autowired
    ArticleService articleService;
    @Autowired
    UserAccountOrderService userAccountOrderService;
    @Autowired
    private UserStockService userStockService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private SmsInfoService smsInfoService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private StockInfoService stockInfoService;


//    @Override
//    @Deprecated
//    public List queryStockListByApi(OptionModel optionModel, SearchPriceContentModel searchPriceContentModel) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("Cycle", "1m");
//        map.put("Exercise_mode", "A");
//        map.put("Market_type", "1");
//        map.put("Max_option_price", searchPriceContentModel.getMax_option_price());
//        map.put("Min_option_price", searchPriceContentModel.getMin_option_price());
//        map.put("Option_type", "C");
//        map.put("page", new Page().toString());
//        map.put("Price_mode", "100");
//        map.put("Symbols", searchPriceContentModel.getSymbols());
//        Map<String, String> pageMap = new HashMap<String, String>();
//        pageMap.put("Page_size", "50");
//        pageMap.put("Page_no", "1");
//        map.put("page", pageMap);
//        optionModel.setContent(map);
//        optionModel.setBizCode("QUERY_PRODUCT_INFO");
//        List list = FuturesUtil.getFuture(optionModel);
//        return list;
//    }

    public List queryStockListByApi(String[] symbols, String cycle, String Max_option_price, String Min_option_price, String pageSize, String pageNo) {
        TDictionary dictionary = dictionaryService.getDictionary("MANAGE_FEE", true);
        Page page = new Page();
        TStockInfo stockInfo = new TStockInfo();
        if (EmptyUtils.isNotEmpty(symbols)) {
            stockInfo.setCode(symbols[0]);
        }
        page.setNumPerPage(15);
//        List<TStockInfo> list = stockInfoService.list(page,stockInfo);
//        List returnList = new ArrayList();
//        for (TStockInfo jsonObject : list) {
//            Map map = new HashMap();
//            String servcieFeeData = jsonObject.getHv30().toString();
//           if(Constants.twoMonth.equals(cycle)){
//               servcieFeeData = jsonObject.getHv60().toString();
//            }
//            BigDecimal serviceFee = new BigDecimal(servcieFeeData);
//            BigDecimal manageFee = new BigDecimal(dictionary.getItemCode()).add(BigDecimal.ONE).multiply(serviceFee);
//            map.put("code",jsonObject.getCode());
//            map.put("name",jsonObject.getName());
//            map.put("hv30",jsonObject.getHv30());
//            map.put("hv60",jsonObject.getHv60());
//            map.put("manageFee", manageFee);
//            returnList.add(map);
//        }
        page = stockInfoService.list(page, stockInfo);
        List<Map> list = page.getResult();
        List returnList = new ArrayList();
        for (Map jsonObject : list) {
            Map map = new HashMap();
            String servcieFeeData = jsonObject.get("hv30").toString();
            if (Constants.twoMonth.equals(cycle)) {
                servcieFeeData = jsonObject.get("hv60").toString();
            }else if(Constants.fortityDay.equals(cycle)){
                servcieFeeData= jsonObject.get("hv14").toString();
            }else if(Constants.twoWeek.equals(cycle)){
                servcieFeeData= jsonObject.get("hv14").toString();
            }else if(Constants.oneWeek.equals(cycle)){
                servcieFeeData= jsonObject.get("hv7").toString();
            }
            BigDecimal serviceFee = new BigDecimal(servcieFeeData);
            //原来是原费率乘以(1 * 0.3). 现在改为原费率 * (字典表中的值)
            // by hexin 2018-4-5 如果后台费率为【0】或者没有维护（null），则比例为1
            String rate = dictionary.getItemCode();
            if(rate == null || rate.equals("0")){
                rate = "1";
            }

            BigDecimal manageFee = new BigDecimal(rate).multiply(serviceFee);

            map.put("code", jsonObject.get("code"));
            map.put("name", jsonObject.get("name"));
            map.put("hv30", jsonObject.get("hv30"));
            map.put("hv60", jsonObject.get("hv60"));
            map.put("hv14", jsonObject.get("hv14"));
            map.put("hv7", jsonObject.get("hv7"));
            map.put("manageFee", manageFee);
            returnList.add(map);
        }
        return returnList;
    }

    @Override
    public List<TStock> queryStockList(TStock stock) {
        return stockService.getAll(stock);
    }

    /**
     * 从新浪中获取股票数据
     *
     */
    @Override
    public String[] queryDataFromSina(String data) {
        String url = "http://hq.sinajs.cn/?list=" + data;
        String result = HttpRequestUtil.stringHttpRequest(url);
        if (EmptyUtils.isEmpty(result)) {
            return null;
        }
        String[] strs = result.split("\"");
        String[] dataArray = strs[1].split(",");
        dataArray[0] = data;//名称汉字填入股票代码
        return dataArray;
    }

    /**
     * 查询文章列表
     *
     */
    @Override
    public Page queryArticleList(Page page, TArticle article) {
        Page result = articleDao.queryArticleList(page, article, 2);
        return result;
    }

    /**
     * 查询文章详情
     *
     */
    @Override
    public TArticle queryArticle(Integer articleId) {
        return articleDao.getArticle(articleId);
    }

    /**
     * 上下一篇
     *
     */
    @Override
    public Map preAndNextArticle(TArticle article) {
        return articleService.getPreNextArticle(article);
    }

    /**
     * 登录的实现方法
     *
     */
    @Override
    public TUser webLogin(HttpServletRequest request, HttpSession session, String loginName, String password) {
        // type = null;
        int result = userService.login(loginName, password, request, "1");

        if (result == -1) {
            throw new RuntimeException("用户不存在");
        }
        if (result == -2) {
            throw new RuntimeException("密码错误");
        }
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        TUserInfo tUserInfo = userInfoService.get(TUserInfo.class, currentUser.getUserId());
        HttpSessionUtils.setObjectToSession(session, Constants.SESSION_KEY_USER_INFO, tUserInfo);
        return currentUser;
    }

    /**
     * 注册的实现方法
     *
     */
    @Override
    @Transactional
    public int webRegister(String nickName, String loginName, String loginPass, String parentCode, String identityCode) {
//        if (EmptyUtils.isEmpty(loginName)
//                || EmptyUtils.isEmpty(loginPass)) {
//            throw new RuntimeException("录入的信息有误");
////            return -1;//不能为空
//        }
        if (EmptyUtils.isEmpty(loginName)) {
            throw new RuntimeException("登录名不能为空");
        }
        if (EmptyUtils.isEmpty(loginPass)) {
            throw new RuntimeException("密码不能为空");
        }
        //手机验证码
        TSmsInfo smsInfo = new TSmsInfo();
        smsInfo.setSendUserNum(loginName);
        smsInfo.setSmsType(Constants.SMS_TYPE_0);
        List<TSmsInfo> smsList = smsInfoService.getAll(smsInfo);
        if (smsList.size() < 1) {
            throw new RuntimeException("验证码不正确");
        }
        if (!(EmptyUtils.isNotEmpty(smsList.get(0).getContent()) && smsList.get(0).getContent().equals(identityCode))) {
            throw new RuntimeException("验证码不正确");
        }
        //检查是否有重复
        List list = userService.getAllByCondition(TUser.class, new String[]{"loginName"}, new Object[]{loginName});
        if (list.size() > 0) {
            throw new RuntimeException("手机号已经被注册过");
//            return 0;
        }

        TUser user = new TUser();
        user.setNickName(nickName);
        user.setLoginName(loginName);
        user.setLoginPass(loginPass);
        user.setUserPhone(loginName);
        user.setParentCode(parentCode);
        user.setUserStatus(1);
        user.setUserType(Constants.USER_TYPE_CUSTOM_05);
        user.setCreateTime(new Date());
        if (EmptyUtils.isEmpty(user.getUserSex())) {
            user.setUserSex(1);
        }
        TRole role = new TRole();
        role.setRoleId(Constants.USER_TYPE_CUSTOM_05);
        userService.save(user, role);
        //保存完用户表信息后，新增账户信息
        TUserAccount userAccount = new TUserAccount(
                user.getUserId(),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new Date(),
                new Date()
        );
        userAccountService.save(userAccount);
        TUserInfo userInfo = new TUserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setStatus(0);
        userInfoService.save(userInfo);
        return 1;
    }

    /**
     * 登出的实现方法
     *
     */
    @Override
    public int webLoginout(HttpSession session) {
        HttpSessionUtils.removeSessionUser(session);
        session.removeAttribute(Constants.SEESION_KEY_LEFTURLS);//是清除菜单SESSION
        session.invalidate();//是让SESSION失效.
        return 1;
    }

    /**
     * 修改密码的实现方法
     */
    @Override
    @Transactional
    public int passwordUpdate(TUser user, String loginName, String password, String identityCode) {
        if (EmptyUtils.isEmpty(loginName) && EmptyUtils.isEmpty(password) && EmptyUtils.isEmpty(identityCode)) {
            return -1;
        }
        //手机验证码
        TSmsInfo smsInfo = new TSmsInfo();
        smsInfo. setSendUserNum(loginName);
        smsInfo.setSmsType(Constants.SMS_TYPE_0);
        List<TSmsInfo> smsList = smsInfoService.getAll(smsInfo);
        if (smsList.size() < 1) {
            throw new RuntimeException("验证码不正确");
        }
        if (!(EmptyUtils.isNotEmpty(smsList.get(0).getContent()) && smsList.get(0).getContent().equals(identityCode))) {
            throw new RuntimeException("验证码不正确");
        }
        if (loginName.equals(user.getLoginName())) {
            user.setLoginPass(MD5Util.encoderByMd5(password));
            userService.update(user);
            return 1;
        }
        return 0;
    }

    /**
     * 找回密码的实现方法
     */
    @Override
    @Transactional
    public int passwordForget(String telephoneNumber, String identifyingCode, String newPassword) {
        if (EmptyUtils.isNotEmpty(identifyingCode) && EmptyUtils.isNotEmpty(telephoneNumber) && EmptyUtils.isNotEmpty(newPassword)) {
            //手机验证码
            TSmsInfo smsInfo = new TSmsInfo();
            smsInfo.setSendUserNum(telephoneNumber);
            smsInfo.setSmsType(Constants.SMS_TYPE_0);
            List<TSmsInfo> smsList = smsInfoService.getAll(smsInfo);
            if (smsList.size() < 1) {
                throw new RuntimeException("验证码不正确");
            }
            if (!(EmptyUtils.isNotEmpty(smsList.get(0).getContent()) && smsList.get(0).getContent().equals(identifyingCode))) {
                throw new RuntimeException("验证码不正确");
            }
            TUser user = userService.get(TUser.class, "userPhone", telephoneNumber);
            user.setLoginPass(MD5Util.encoderByMd5(newPassword));
            userService.update(user);
            return 1;
        }
        return 0;
    }

    /**
     * 实名认证及银行卡绑定
     *
     */
    @Transactional
    @Override
    public void authentication(TUserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
    }

    /**
     * 实名认证及银行卡绑定
     *
     */
    @Transactional
    @Override
    public void authenticationCheck(TUserInfoCheck userInfoCheck) {
        userInfoCheckService.saveOrUpdate(userInfoCheck);
    }

    /**
     * 个人信息展示
     *
     */
    @Override
    public TUser queryUserSelfInfo(HttpSession session) {
        return HttpSessionUtils.getCurrentUser(session);
    }

    /**
     * 充值
     *
     */
    @Deprecated
    @Override
    public TUserAccount WebStockDeposit(HttpSession session, BigDecimal depositMoney, int depositType) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        TUserAccount userAccount = userAccountService.get(TUserAccount.class, user.getUserId());
        return userAccount;

    }

    /**
     * 提现
     *
     */
    @Deprecated
    @Override
    public void WebStockWithdraw(HttpSession session, BigDecimal withdrawMoney) {
    }

    /**
     * 创建推广链接
     *
     */
    @Override
    public String queryPromotion(HttpSession session) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        //TODO 应该在注册的时候给用户自动加一个分享的字段promotionKeyWords = MD5Util.encoderByMd5("##65533##"+user.getUserPhone())
        //todo by 方骢 ：这个推广似乎是一个类似分销一样需要计算的功能，似乎并不简单。暂时不要写
        String promotion = "http://www.baidu.com?" + MD5Util.encoderByMd5("##65533##" + user.getUserPhone());
        return promotion;
    }


    @Override
    public TStockPlan createStockPlan(TStockPlan stockPlan) {
        return stockPlanService.buyApply(stockPlan);
    }

    @Override
    public List<TStockPlan> queryStockPlanList(TStockPlan stockPlan) {
        List<TStockPlan> stockPlanList = stockPlanService.getAll(stockPlan);
        return stockPlanList;
    }

    @Override
    public List<Map> queryStockPlanListMap(TStockPlan stockPlan, TUser user) {
        List<Map> listMap = stockPlanService.listMap(null, stockPlan, user);
        return listMap;
    }

    @Override
    public TStockPlan queryStockPlan(TStockPlan stockPlan) {
        return stockPlanService.get(TStockPlan.class, stockPlan.getPlanId());
    }


    @Transactional
    @Override
    public void deposit(int userId, BigDecimal money, String payType) {
        //充值
        TUserAccountFlow flow = new TUserAccountFlow(
                userId,
                money,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "depositOrder",
                "",
                "",
                0,
                "用户充值",
                new Date()
        );
        userAccountFlowService.save(flow);
    }

    @Transactional
    @Override
    public void withdraw(int userId, BigDecimal money) {
        TUserInfo userInfo = userInfoService.get(TUserInfo.class, userId);

        if (userInfo == null) {
            throw new RuntimeException("请先进行银行卡绑定");
        }

        if (money.compareTo(BigDecimal.ZERO) < 1) {
            throw new RuntimeException("请输入正确的提现金额");
        }

        TDictionary tDictionary = dictionaryService.getDictionary("ALLOW_WITHDRAW_TIME", true);

        Calendar nowC = Calendar.getInstance();
        int i = nowC.get(Calendar.DAY_OF_WEEK);
        if (i == 7 || i == 1) {
            //7是星期六 1是星期日
            throw new RuntimeException("周末不允许提现");
        }

        String beginTimeStr = tDictionary.getItemCode().split("\\|")[0];
        String beginHour = beginTimeStr.substring(0, 2);
        String beginMinute = beginTimeStr.substring(2);
        Calendar beginC = (Calendar) nowC.clone();
        beginC.set(Calendar.HOUR_OF_DAY, Integer.parseInt(beginHour));
        beginC.set(Calendar.MINUTE, Integer.parseInt(beginMinute));

        String endTimeStr = tDictionary.getItemCode().split("\\|")[1];
        String endHour = endTimeStr.substring(0, 2);
        String endMinute = endTimeStr.substring(2);
        Calendar endC = (Calendar) nowC.clone();
        endC.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
        endC.set(Calendar.MINUTE, Integer.parseInt(endMinute));

        if (nowC.before(beginC) && nowC.after(endC)) {
            throw new RuntimeException("当前时间段无法提现");
        }

        TUserAccountOrder userAccountOrder = new TUserAccountOrder(
                userInfo.getUserId(),
                money,
                "用户提现",
                "withdrawOrder",
                "0",
                userInfo.getBankId(),
                userInfo.getBankCardNumber(),
                new Date(),
                0,
                "",
                null
        );
        userAccountOrderService.save(userAccountOrder);
    }

    @Transactional
    @Override
    public void exercise(TStockPlan stockPlan) {
        stockPlanService.sellApply(stockPlan);
    }

    @Override
    public TUserAccount queryUserAccount(int userId) {
        TUserAccount userAccount = userAccountService.get(TUserAccount.class, userId);
        return userAccount;
    }

    @Override
    public TUserInfo queryUserInfo(int userId) {
        return userInfoService.get(TUserInfo.class, userId);
    }

    /**
     * 查询是否有重复的审核请求
     *
     */
    @Override
    public TUserInfoCheck queryUserInfoCheck(int userId, String checkResult) {
        return userInfoCheckService.queryUserInfoCheck(userId, checkResult);
    }

    @Override
    public List queryUserStockList(int userId, TUserStock userStock) {
        return userStockService.list(userId, userStock.getSymbol());
    }

    @Override
    public List toMobileOptionalLabelsStockList(SearchPriceContentModel stockModel, TUser currentUser, TUserStock userStock) {
        String max = stockModel.getMax_option_price();
        String min = stockModel.getMin_option_price();
        String pageSize = "15";
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
        if (EmptyUtils.isEmpty(userStock.getSymbol())) {
            userStock.setSymbol(null);
        }
        List<TUserStock> list = userStockService.list(currentUser.getUserId(), userStock.getSymbol());
        List stockList = new ArrayList();
        TDictionary dictionary = dictionaryService.getDictionary("MANAGE_FEE", true);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map = new HashMap();
                TStockInfo stockInfo = new TStockInfo();
                stockInfo.setCode(list.get(i).getSymbol());
                stockInfo = stockInfoService.get(stockInfo);
                map.put("code", stockInfo.getCode());
                map.put("name", stockInfo.getName());
                map.put("hv60", stockInfo.getHv60());
                map.put("manageFee", new BigDecimal(dictionary.getItemCode()).add(stockInfo.getHv30()));
                stockList.add(map);
            }
        }

        return stockList;
    }

    @Override
    public void saveUserStock(int userId, String symbol) {
        TUserStock userStock = new TUserStock(userId, symbol);
        userStockService.save(userStock);
    }

    @Override
    public void deleteUserStock(int userId, String symbol) {
        userStockService.delete(userId, symbol);
    }


    /**
     * 身份证唯一性判断
     */
    @Override
    public int idCardNumCheck(String idCardNumber, Integer userId) {
        List list = userInfoService.getAllByCondition(TUserInfo.class, "idNumber", idCardNumber);
        if (list.size() != 0) {
            if (list.size() > 1) {
                return 1;
            } else {
                TUserInfo userInfo = (TUserInfo) list.get(0);
                if (userInfo.getUserId().equals(userId)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }

        //List list = userInfoService.getAll(userInfo);

        return 0;
    }

    @Transactional
    @Override
    public int sendIdentityCode(String userTel) {
        int result = 0;
        String sendMessage = getRandomString(4);
        try {
            result = SendMessage.doAliyunSend(userTel, Integer.parseInt(sendMessage));
            if (result == 1) {
                smsInfoService.saveSmsInfo("", sendMessage, userTel, Constants.SMS_TYPE_0);
            }
            System.out.println(result);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取n位随机数字
     *
     */
    private String getRandomString(int count) {
        String finalStr = "";
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        if ("0".equals(sb.toString().substring(0, 1))) {
            finalStr = "1" + sb.toString().substring(1, 4);
            sb = new StringBuffer(finalStr);
        }
        return sb.toString();
    }


    /**
     * 准备提交订单的数据
     *
     */
    @Override
    @Transactional
    public StandardPaymentRequestEntity getStandardPaymentInfo(StandardPaymentRequestEntity requestEntity, TUser currentUser) {
        //为了测试
        requestEntity.setV_amount(new BigDecimal(0.01));
        //需要先建立订单
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String date = DateUtils.doFormatDate(new Date(), "yyyyMMdd");
        String amount = decimalFormat.format(requestEntity.getV_amount().setScale(2, BigDecimal.ROUND_DOWN));
        String orderFlowCode = UUID.randomUUID().toString();
        TOrderInfo orderInfo = new TOrderInfo();
        orderInfo.setOrderStatus(0);
        orderInfo.setOrderNum(date + "-" + Constants.V_MID + "-" + orderFlowCode);
        orderInfo.setOrderCreateTime(new Date());
        orderInfo.setTotalPrice(requestEntity.getV_amount().setScale(2, BigDecimal.ROUND_DOWN));
        orderInfo.setOrderLoginName(currentUser.getLoginName());
        orderInfoService.save(orderInfo);

        //订单新建成功后准备需要的参数
        requestEntity.setV_oid(date + "-" + Constants.V_MID + "-" + orderFlowCode);
        StandardPaymentRequestEntity standardPaymentRequestEntity = new StandardPaymentRequestEntity(
                Constants.V_MID,//v_mid
                orderInfo.getOrderNum(),
                Constants.V_MID.toString(),
                Constants.V_MID.toString(),
                Constants.V_MID.toString(),
                Constants.V_MID.toString(),
                requestEntity.getV_amount(),
                date,//时间yyyyMMdd
                1,//状态：已配齐
                Constants.V_MID.toString(),
                0,
                "http://120.27.22.202/web/stock/payeasy/receive/data",
                "",
                currentUser.getUserId().toString(),
                "904"//固定传904
        );
        standardPaymentRequestEntity.setV_amount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN));
        String text = standardPaymentRequestEntity.getV_moneytype().toString()
                + standardPaymentRequestEntity.getV_ymd().toString()
                + amount
                + standardPaymentRequestEntity.getV_rcvname().toString()
                + standardPaymentRequestEntity.getV_oid().toString()
                + standardPaymentRequestEntity.getV_mid().toString()
                + standardPaymentRequestEntity.getV_url().toString();
        Md5 md = new Md5("");
        byte b[] = new byte[]{};
        try {
            md.hmac_Md5(text, Constants.V_KEY);
            b = md.getDigest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String digestString = md.stringify(b);
        standardPaymentRequestEntity.setV_md5info(digestString);
        return standardPaymentRequestEntity;
    }

    /**
     * 首易信支付操作完成后返回url所带参数的处理
     *
     */
    @Override
    @Transactional
    public int getStandardPaymentReturnData(StandardPaymentRetuenEntity standardPaymentRetuenEntity) {
        //v_oid，v_pstatus，v_pstring，v_pmode
        String source1 = standardPaymentRetuenEntity.getV_oid() + standardPaymentRetuenEntity.getV_pstatus() + standardPaymentRetuenEntity.getV_pstring() + standardPaymentRetuenEntity.getV_pmode();
        String source2 = standardPaymentRetuenEntity.getV_amount() + standardPaymentRetuenEntity.getV_moneytype();
        //v_md5info指纹结果
        Md5 md5 = new Md5("");
        String strSourceEncode = null;
        try {
            strSourceEncode = java.net.URLEncoder.encode(source1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte b[] = new byte[0];
        //v_md5money指纹结果
        Md5 m = new Md5("");
        byte a[] = new byte[0];
        String digestString = Md5.stringify(b);
        a = md5Digest(source2, md5, strSourceEncode, m, a);
        String digestString2 = Md5.stringify(a);
        //RSA验证
        String publicKey = Constants.KEY_PATH;   //public1024.key的路径
        String SignString = standardPaymentRetuenEntity.getV_sign();
        String strSource = standardPaymentRetuenEntity.getV_oid() + standardPaymentRetuenEntity.getV_pstatus() + standardPaymentRetuenEntity.getV_amount() + standardPaymentRetuenEntity.getV_moneytype();
        RSA_MD5 rsaMD5 = new RSA_MD5();
        int k = rsaMD5.PublicVerifyMD5(publicKey, SignString, strSource);
        if (digestString.equals(standardPaymentRetuenEntity.getV_md5info()) && digestString2.equals(standardPaymentRetuenEntity.getV_md5money()) && (k == 0)) {
            return 1;//成功
        } else {
            return 0;//失败
        }
    }

    private byte[] md5Digest(String source2, Md5 md5, String strSourceEncode, Md5 m, byte[] a) {
        byte[] b;
        try {
            md5.hmac_Md5(strSourceEncode, Constants.V_KEY);
            b = md5.getDigest();
            m.hmac_Md5(source2, Constants.V_KEY);
            a = m.getDigest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 获取订单支付结果
     *
     */
    @Override
    public int getOrderPaymentResult(OrderParmentResultReturnEntity orderParmentResult) {
        String source1 = orderParmentResult.getV_oid() + orderParmentResult.getV_pmode() + orderParmentResult.getV_pstatus() + orderParmentResult.getV_pstring() + orderParmentResult.getV_count();//中文加密前encode
        String source2 = orderParmentResult.getV_amount() + orderParmentResult.getV_moneytype();
        //md5加密1
        Md5 md5 = new Md5("");
        String strSourceEncode = null;
        byte b[] = new byte[0];
        String digestString = Md5.stringify(b);
        //md5加密2
        Md5 m = new Md5("");
        byte a[] = new byte[0];
        try {
            strSourceEncode = java.net.URLEncoder.encode(source1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        a = md5Digest(source2, md5, strSourceEncode, m, a);
        String digestString2 = Md5.stringify(a);
        //RSA验证
        String publicKey = Constants.KEY_PATH;
        String SignString = orderParmentResult.getV_sign();
        String strSource = orderParmentResult.getV_oid() + orderParmentResult.getV_pstatus() + orderParmentResult.getV_amount() + orderParmentResult.getV_moneytype() + orderParmentResult.getV_count();
        RSA_MD5 rsaMD5 = new RSA_MD5();
        if (EmptyUtils.isEmpty(publicKey) || EmptyUtils.isEmpty(SignString) || EmptyUtils.isEmpty(strSource)) {
            return 0;
        }
        int k = rsaMD5.PublicVerifyMD5(publicKey, SignString, strSource);
        if (digestString2.equals(orderParmentResult.getV_md5money()) && digestString.equals(orderParmentResult.getV_mac()) && (k == 0)) {
            System.out.println("sent");
            //拆分参数
            String[] resultoid = orderParmentResult.getV_oid().split("[|][_][|]");
//            String[] resultpmode = orderParmentResult.getV_pmode().split("[|][_][|]");
//            String[] resultstatus = orderParmentResult.getV_pstatus().split("[|][_][|]");
//            String[] resultpstring = orderParmentResult.getV_pstring().split("[|][_][|]");
//            String[] resultamount = orderParmentResult.getV_amount().split("[|][_][|]");
//            String[] resultmoneytype = orderParmentResult.getV_moneytype().split("[|][_][|]");
            for (int i = 0; i < resultoid.length; i++) {
                //将提交订单数据添加数据库中
                //需要注意一点：返回的订单金额要与数据库中存放的订单原金额比对，只有数据一致才可更新支付状态（预防数据在传输过程中被拦截、篡改）
                TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, "orderNum", orderParmentResult.getV_oid());
                //要求对金额做比对
                if (orderInfo.getTotalPrice().compareTo(new BigDecimal(orderParmentResult.getV_amount())) != 0) {
                    return 0;
                }
                System.out.println("success");
                orderInfoService.updateOrderInfo(orderInfo.getOrderNum());
                TUser user = userService.get(TUser.class, "loginName", orderInfo.getOrderLoginName());
                TUserAccountFlow flow = new TUserAccountFlow(
                        user.getUserId(),//userId
                        orderInfo.getTotalPrice(),//money
                        BigDecimal.ZERO,//freezingMoney
                        BigDecimal.ZERO,//integral
                        BigDecimal.ZERO,//beforeMoney
                        BigDecimal.ZERO,//afterMoney
                        BigDecimal.ZERO,//afterFreezingMoney
                        BigDecimal.ZERO,//afterIntegral
                        "depositOrder",
                        "",
                        "",
                        0,
                        "用户充值",
                        new Date()
                );
                userAccountFlowService.save(flow);
            }
            return 1;
        } else {
            System.out.println("error");
            return 0;
        }
    }

    @Override
    public int checkBankNumberAndBankId(TUserInfo userInfo, Integer currentUserId) {
        TUserInfo oldUserInfo = userInfoService.get(TUserInfo.class, currentUserId);
        if (userInfo.getBankId().equals(oldUserInfo.getBankId()) && userInfo.getBankCardNumber().equals(oldUserInfo.getBankCardNumber())) {
            return 0;
        }
        return 1;
    }

    @Override
    public TDictionary updateWithdrawFee(TDictionary dictionary) {
        TDictionary target = dictionaryService.get(TDictionary.class, dictionary.getId());
        target.setItemCode(dictionary.getItemCode());
        return dictionaryService.update(target);
    }


    /**
     * 功能描述: 创建支付订单
     * 创建者: zhangwei
     * 创建时间: 2018/02/11 16:04
     *
     */
    public TOrderInfo createOrder(TUser user, TOrderInfo orderInfo) {
        if (EmptyUtils.isEmpty(user)) {
            throw new RuanYunException("获取用户信息失败");
        }

        orderInfo.setOrderCreateTime(new Date());
        orderInfo.setOrderUserName(user.getUserNum());
        orderInfo.setOrderLoginName(user.getLoginName());

        save(orderInfo);

        orderInfo.setOrderNum(NumUtils.getCommondNum(NumUtils.PIX_ORDER_INFO, orderInfo.getOrderId()));

        return orderInfo;
    }


    //暂时没有用
    public Map<String,String> createPayArgs(TOrderInfo orderInfo) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> payArgs=new HashMap<String, String>();
        payArgs.put("merchant_code","1111110166");
        payArgs.put("service_type","direct_pay");
        payArgs.put("notify_url","http://15l0549c66.iask.in:45191/bankPay/offline_notify.jsp");
        payArgs.put("interface_version","V3.3");
        payArgs.put("input_charset","UTF-8");
        payArgs.put("sign_type","RSA-S");
        payArgs.put("return_url","http://15l0549c66.iask.in:45191/bankPay/page_notify.jsp");
        payArgs.put("order_no",orderInfo.getOrderNum());
        //payArgs.put("order_time",format.format(orderInfo.getOrderCreateTime()).toString());
        payArgs.put("order_amount",String.valueOf(orderInfo.getTotalPrice()));
        payArgs.put("product_name","充值");


        return payArgs;
    }
}
