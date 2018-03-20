package com.ruanyun.web.service.web;

import com.aliyuncs.exceptions.ClientException;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.web.StockPlanDao;
import com.ruanyun.web.model.TStockInfo;
import com.ruanyun.web.model.TStockPlan;
import com.ruanyun.web.model.TUserAccountFlow;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.SmsInfoService;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.impl.WebServiceImpl;
import com.ruanyun.web.sms.SendMessage;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class StockPlanService extends BaseServiceImpl<TStockPlan> {

    @Autowired
    private UserAccountFlowService userAccountFlowService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private WebInterface webService;
    @Autowired
    private StockPlanDao stockPlanDao;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsInfoService smsInfoService;
    @Autowired
    private StockInfoService stockInfoService;


    @Override
    public TStockPlan save(TStockPlan plan) {
        plan.setCreateTime(new Date());
        super.save(plan);

        TUserAccountFlow flow = new TUserAccountFlow(
                plan.getUserId(),
                plan.getServiceFee().add(plan.getManageFee()).negate(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                "stockPlan",
                "",
                plan.getPlanId().toString(),
                0,
                "方案申请",
                new Date()
        );
        userAccountFlowService.save(flow, false);
        return plan;
    }

    @Override
    public List<TStockPlan> getAll(TStockPlan stockPlan) {
        Map map = new HashMap<String, Object>();
        map.put("planId", stockPlan.getPlanId());
        map.put("userId", stockPlan.getUserId());
        map.put("symbol", stockPlan.getSymbol());
        map.put("symbolName", stockPlan.getSymbolName());
        map.put("cycle", stockPlan.getCycle());
        map.put("buyMarketPrice", stockPlan.getBuyMarketPrice());
        map.put("buyPriceType", stockPlan.getBuyPriceType());
        map.put("buyLimitPrice", stockPlan.getBuyLimitPrice());
        map.put("buyRecommendDate", stockPlan.getBuyRecommendDate());
        map.put("buyPrice", stockPlan.getBuyPrice());
        map.put("buyConfirmDate", stockPlan.getBuyConfirmDate());
        map.put("buyEndDate", stockPlan.getBuyEndDate());
        map.put("sellMarketPrice", stockPlan.getSellMarketPrice());
        map.put("sellPriceType", stockPlan.getSellPriceType());
        map.put("sellLimitPrice", stockPlan.getSellLimitPrice());
        map.put("sellCreateTime", stockPlan.getSellCreateTime());
        map.put("sellPrice", stockPlan.getSellPrice());
        map.put("sellConfirmTime", stockPlan.getSellConfirmTime());
        map.put("serviceFee", stockPlan.getServiceFee());
        map.put("manageFee", stockPlan.getManageFee());
        map.put("orderStatus", stockPlan.getOrderStatus());
        map.put("createTime", stockPlan.getCreateTime());

        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        List<String> columnList = new ArrayList<String>();
        List<Object> valueList = new ArrayList<Object>();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            if (next.getValue() != null) {
                columnList.add(next.getKey());
                valueList.add(next.getValue());
                iterator.remove();
            }
        }

        return super.getAll(TStockPlan.class, columnList.toArray(new String[0]), valueList.toArray(), null, null);
    }

    public TStockPlan buyApply(TStockPlan stockPlan) {
        //在创建订单时新增一个判断,当 USER_TRANSACTION_SWITCH 用户交易开关为0时,不允许交易
        TDictionary d_USER_TRANSACTION_SWITCH = dictionaryService.getDictionary("USER_TRANSACTION_SWITCH", true);
        if (!d_USER_TRANSACTION_SWITCH.getItemCode().equals("1")) {
            throw new RuntimeException("管理费结算中，请稍后提交！");
        }

        TDictionary dictionary = dictionaryService.getDictionary("MANAGE_FEE", true);
        if (EmptyUtils.isEmpty(stockPlan.getSymbol())) {
            throw new RuntimeException("股票编号不能为空");
        }
        TStockInfo stockInfo = new TStockInfo();
        stockInfo.setCode(stockPlan.getSymbol());
        stockInfo = stockInfoService.get(stockInfo);
        if (EmptyUtils.isEmpty(stockInfo)) {
            throw new RuntimeException("没有查询到该股票的管理费率");
        }
        String offerPrice = "1";
        if (Constants.oneMonth.equals(stockPlan.getCycle())) {
            offerPrice = stockInfo.getHv30().toString();
        } else if (Constants.twoMonth.equals(stockPlan.getCycle())) {
            offerPrice = stockInfo.getHv60().toString();
        }else if(Constants.fortityDay.equals(stockPlan.getCycle())){
            offerPrice = stockInfo.getHv14().toString();
        } else {
            throw new RuntimeException("请选择正确的周期");
        }
        BigDecimal serviceFee = new BigDecimal(offerPrice),             //机构收取
                manageFee = new BigDecimal(dictionary.getItemCode()),   //平台收取
                oneHundred = new BigDecimal(100);                   //100

        //服务费和管理费
        serviceFee = stockPlan.getBuyMarketPrice().divide(oneHundred).multiply(serviceFee);
        manageFee = stockPlan.getBuyMarketPrice().divide(oneHundred).multiply(manageFee);

        TStockPlan tStockPlan = new TStockPlan(
                stockPlan.getUserId(),
                stockPlan.getSymbol(),
                stockPlan.getSymbolName(),
                stockPlan.getCycle(),
                stockPlan.getBuyMarketPrice(),
                stockPlan.getBuyPriceType(),
                stockPlan.getBuyPriceType().equals(0) ? BigDecimal.ZERO : stockPlan.getBuyLimitPrice(),
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
                serviceFee,
                manageFee,
                "1",
                new Date(),
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
        return save(tStockPlan);
    }

    public void sellApply(TStockPlan stockPlan) {
        TStockPlan tStockPlan = get(TStockPlan.class, stockPlan.getPlanId());
        if (!tStockPlan.getOrderStatus().equals("2")) {
            //如果订单不是在持仓中这个状态,是无法申请行权的
            throw new RuntimeException("订单当初前状态无法行权");
        }
        tStockPlan.setOrderStatus("3");
        tStockPlan.setSellMarketPrice(stockPlan.getSellMarketPrice());
        tStockPlan.setSellPriceType(stockPlan.getSellPriceType());
        tStockPlan.setSellLimitPrice(stockPlan.getSellPriceType().equals("0") ? BigDecimal.ZERO : stockPlan.getSellLimitPrice());
        tStockPlan.setSellCreateTime(new Date());

        update(tStockPlan);
    }

    /**
     * 买入处理
     */
    public void buyHandle(boolean isDone, Integer planId, Date buyRecommendDate, BigDecimal buyPrice) {
        //将导入的数据更新到表中
        TStockPlan plan = get(TStockPlan.class, planId);
        if (plan == null) {
            throw new RuntimeException("没有订单号为" + planId + "的订单");
        }
        if (!plan.getOrderStatus().equals("1")) {
            //已经处理过的申请买入方案就不做处理了
            return;
        }
        if (isDone) {
            //如果成交了
            Date buyConfirmDate = buyRecommendDate;
            Date buyEndDate = getBuyEndDate(buyConfirmDate, plan.getCycle());

            plan.setBuyRecommendDate(buyRecommendDate);
            plan.setBuyConfirmDate(buyConfirmDate);
            plan.setBuyPrice(buyPrice);
            plan.setBuyEndDate(buyEndDate);
            plan.setOrderStatus("2");   //成交,持仓中
        } else {
            plan.setOrderStatus("-1");  //未成交
            //还需要退回当初买入的钱
            TUserAccountFlow flow = new TUserAccountFlow(
                    plan.getUserId(),
                    plan.getServiceFee().add(plan.getManageFee()),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "stockPlan",
                    "",
                    plan.getPlanId().toString(),
                    0,
                    "买入申请未成交,退回管理费",
                    new Date()
            );
            userAccountFlowService.save(flow);
        }
        update(plan);
    }

    public void sellHandle(boolean isDone, Integer planId, BigDecimal sellPrice, String buyRecommendDate) {
        //将导入的数据更新到表中
        TStockPlan plan = get(TStockPlan.class, planId);
        if (plan == null) {
            throw new RuntimeException("没有订单号为" + planId + "的订单");
        }
        if (!plan.getOrderStatus().equals("3")) {
            //已经处理过的申请卖出方案就不做处理了
            return;
        }
        if (isDone) {
            //如果卖出成交了
            plan.setOrderStatus("4");   //状态改为行权成功
            plan.setSellPrice(sellPrice);
            plan.setSellConfirmTime(DateUtils.doFormatDate(buyRecommendDate, "yyyy-MM-dd"));

            //计算盈利,进行转账
            //(卖出价-买入价)/买入价 * 期初金额 = 用户盈利的钱
            //((卖出价/买入价)-1) * 期初金额 = 收益
            //((卖出价/买入价)-1) * 期初金额 - 管理费(两个相加的管理费) = 净收益

            BigDecimal profit = plan.getSellPrice()
                    .divide(plan.getBuyPrice(), 3, BigDecimal.ROUND_HALF_DOWN)
                    .subtract(BigDecimal.ONE)
                    .multiply(plan.getBuyMarketPrice());
            BigDecimal netProfit = profit.subtract(plan.getServiceFee().add(plan.getManageFee()));

            plan.setProfit(profit);
            plan.setNetProfit(netProfit);

            //如果收益大于0,则进行转账
            if (profit.compareTo(BigDecimal.ZERO) > 0) {
                TUserAccountFlow flow = new TUserAccountFlow(
                        plan.getUserId(),
                        profit,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        "stockPlan",
                        "",
                        plan.getPlanId().toString(),
                        0,
                        "行权收益",
                        new Date()
                );
                userAccountFlowService.save(flow);
            } else {
                //如果卖出价低于买入价,则没有盈利,不做处理
                //(卖出价-买入价) < 0 没有盈利则不给用户转账
            }
        } else {
            plan.setOrderStatus("2");   //状态改回,持仓中
        }
        update(plan);
    }

    private Date getBuyEndDate(Date now, String cycle) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        Integer number = Integer.valueOf(cycle.substring(0, cycle.length() - 1));
        String unitString = cycle.substring(cycle.length() - 1);
        int unit;
        if (unitString.equals("d")) {
            unit = Calendar.DATE;
        } else if (unitString.equals("m")) {
            unit = Calendar.MONTH;
        } else {
            throw new RuntimeException("未知的时间周期");
        }
        calendar.add(unit, number);
        return calendar.getTime();
    }

    public Page<TStockPlan> queryStockPlanList(Page page, TStockPlan stockPlan) {
        return stockPlanDao.queryPage(page, stockPlan);
    }

    /**
     * 查询费用和收益聚合信息
     *
     * @param page
     * @param stockPlan
     * @param user
     * @return
     */
    public Page queryFeeAndProfitInfo(TUser currentUser, Page page, TStockPlan stockPlan, TUser user) {
        return stockPlanDao.queryFeeAndProfitInfo(currentUser, page, stockPlan, user);
    }

    /**
     * 查询费用
     *
     * @param page
     * @param stockPlan
     * @param user
     * @return
     */
    public Page queryStockPlanFeeInfo(TUser currentUser, Page page, TStockPlan stockPlan, TUser user, String startTime, String endTime, String userName, Integer belongUserId, Integer parentCodeIndex, String buyConfirmDatetartTime, String buyConfirmDateEndTime, String sellConfirmTimeDatetartTime, String sellConfirmTimeDateEndTime) {
        return stockPlanDao.queryStockPlanFeeInfo(currentUser, page, stockPlan, user, startTime, endTime, userName, belongUserId, parentCodeIndex, buyConfirmDatetartTime, buyConfirmDateEndTime, sellConfirmTimeDatetartTime, sellConfirmTimeDateEndTime);
    }

    public List groupQueryStockPlanFeeInfo(TUser currentUser, TStockPlan stockPlan, TUser user, String startTime, String endTime, String userName, Integer belongUserId, Integer parentCodeIndex, String buyConfirmDatetartTime, String buyConfirmDateEndTime, String sellConfirmTimeDatetartTime, String sellConfirmTimeDateEndTime) {
        return stockPlanDao.groupQueryStockPlanFeeInfo(currentUser, stockPlan, user, startTime, endTime, userName, belongUserId, parentCodeIndex, buyConfirmDatetartTime, buyConfirmDateEndTime, sellConfirmTimeDatetartTime, sellConfirmTimeDateEndTime);
    }

    public List groupQueryAllServerFee(TUser currentUser, String startTime, String endTime) {
        return stockPlanDao.groupQueryAllServerFee(currentUser, startTime, endTime);
    }

    /**
     * 根据传的userId获取手机号码并请求发送短信
     *
     * @param random
     * @param userId
     * @return
     * @throws ClientException
     */
    public int sendMessageByAliyun(Integer random, Integer userId) throws ClientException {
        TUser user = userService.get(TUser.class, userId);
        SendMessage.doAliyunSend(user.getLoginName(), random);
        smsInfoService.saveSmsInfo("", random.toString(), user.getUserNum(), Constants.SMS_TYPE_1);
        return 1;
    }

    public List<Map> listMap(Page page, TStockPlan stockPlan, TUser user) {
        return stockPlanDao.listMap(page, stockPlan, user);
    }
}
