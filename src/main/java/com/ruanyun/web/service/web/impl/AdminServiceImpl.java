package com.ruanyun.web.service.web.impl;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.orm.ICommonSqlDao;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SysCode;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TRole;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.model.sys.TUserRole;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.sys.RoleService;
import com.ruanyun.web.service.sys.UserRoleService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.*;
import com.ruanyun.web.util.*;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {


    @Autowired
    @Qualifier("commonSqlExpandDao")
    private ICommonSqlDao sqlDao;

    @Autowired
    private UserAccountOrderService userAccountOrderService;
    @Autowired
    private StockPlanService stockPlanService;
    @Autowired
    private WebInterface webService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountFlowService userAccountFlowService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StockService stockService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private StockInfoService stockInfoService;

    @Override
    public List<HashMap> queryWithdrawList(Page page, TUserAccountOrder userAccountOrder, String loginName) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  tuao.order_id         AS orderId, ");
        sql.append("  tuao.user_id          AS userId, ");
        sql.append("  tuao.money            AS money, ");
        sql.append("  tuao.remark           AS remark, ");
        sql.append("  tuao.order_type       AS orderType, ");
        sql.append("  tuao.order_status     AS orderStatus, ");
        sql.append("  tuao.bank_id          AS bankId, ");
        sql.append("  tuao.bank_card_number AS bankCardNumber, ");
        sql.append("  tuao.create_time      AS createTime, ");
        sql.append("  tuao.handle_user_id   AS handleUserId, ");
        sql.append("  tuao.handle_result    AS handleResult, ");
        sql.append("  tuao.update_time      AS updateTime, ");
        sql.append("  user.login_name       AS loginName, ");
        sql.append("  user.nick_name        AS nickName, ");
        sql.append("  userInfo.user_name    AS userName, ");
        sql.append("  handleUser.login_name AS handleLoginName, ");
        sql.append("  handleUser.nick_name  AS handleNickName ");
        sql.append("from ");
        sql.append("  t_user_account_order tuao ");
        sql.append("  LEFT JOIN t_user user ON tuao.user_id = user.user_id ");
        sql.append("  LEFT JOIN t_user_info userInfo ON tuao.user_id = userInfo.user_id ");
        sql.append("  LEFT JOIN t_user handleUser ON tuao.handle_user_id = handleUser.user_id ");
        sql.append("WHERE 1 = 1 ");
        if (userAccountOrder.getUserId() != null) {
            sql.append("      AND tuao.user_id = :userId ");
            map.put("userId", userAccountOrder.getUserId());
        }
        if (StringUtils.isNotEmpty(loginName)) {
            sql.append("      AND user.login_name = :loginName ");
            map.put("loginName", loginName);
        }
        if (userAccountOrder.getOrderId() != null) {
            sql.append("      AND tuao.order_id = :orderId ");
            map.put("orderId", userAccountOrder.getOrderId());
        }
        if (StringUtils.isNotEmpty(userAccountOrder.getOrderStatus())) {
            sql.append("      AND tuao.order_status = :orderStatus ");
            map.put("orderStatus", userAccountOrder.getOrderStatus());
        }

        Page queryPage = sqlDao.queryPage(sql.toString(), page, map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return queryPage.getResult();
    }

    @Override
    public HashMap queryWithdraw(int orderId) {
        TUserAccountOrder userAccountOrder = new TUserAccountOrder();
        userAccountOrder.setOrderId(orderId);
        List<HashMap> hashMapList = queryWithdrawList(new Page(), userAccountOrder, null);
        if (hashMapList.size() == 0) {
            throw new RuntimeException("没有查询到该申请");
        }
        return hashMapList.get(0);
    }

    @Transactional
    @Override
    public void handleWithdraw(TUserAccountOrder userAccountOrder) {
        userAccountOrderService.handleWithdraw(userAccountOrder);
    }

    @Override
    public List<HashMap> queryStockPlanList(Page page, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  stp.plan_id                 AS planId, ");
        sql.append("  stp.user_id                 AS userId, ");
        sql.append("  stp.symbol                  AS symbol, ");
        sql.append("  stp.symbol_name             AS symbolName, ");
        sql.append("  stp.cycle                   AS cycle, ");
        sql.append("  stp.buy_recommend_date      AS buyRecommendDate, ");
        sql.append("  stp.buy_confirm_date        AS buyConfirmDate, ");
        sql.append("  stp.buy_end_date            AS buyEndDate, ");
        sql.append("  stp.buy_market_price        AS buyMarketPrice, ");
        sql.append("  stp.buy_price_type          AS buyPriceType, ");
        sql.append("  stp.buy_limit_price         AS buyLimitPrice, ");
        sql.append("  stp.buy_price               AS buyPrice, ");
        sql.append("  stp.sell_market_price       AS sellMarketPrice, ");
        sql.append("  stp.sell_price_type         AS sellPriceType, ");
        sql.append("  stp.sell_limit_price        AS sellLimitPrice, ");
        sql.append("  stp.sell_create_time        AS sellCreateTime, ");
        sql.append("  stp.sell_price              AS sellPrice, ");
        sql.append("  stp.sell_confirm_time       AS sellConfirmTime, ");
        sql.append("  stp.service_fee             AS serviceFee, ");
        sql.append("  stp.manage_fee              AS manageFee, ");
        sql.append("  stp.order_status            AS orderStatus, ");
        sql.append("  stp.create_time             AS createTime, ");
        sql.append("  stp.profit                  AS profit, ");
        sql.append("  stp.net_profit              AS netProfit, ");
        sql.append("  user.login_name   AS loginName, ");
        sql.append("  user.nick_name    AS nickName, ");
        sql.append("  tui.user_name     AS userName ");
        sql.append("from t_stock_plan stp ");
        sql.append("  LEFT JOIN t_user user ON stp.user_id = user.user_id ");
        sql.append("  LEFT JOIN t_user_info tui ON tui.user_id = stp.user_id ");
        sql.append("WHERE 1 = 1 ");
        if (stockPlan.getPlanId() != null) {
            sql.append("      AND stp.plan_id = :id ");
            map.put("id", stockPlan.getPlanId());
        }
        if (StringUtils.isNotEmpty(stockPlan.getSymbol())) {
            sql.append("      AND stp.symbol = :symbol ");
            map.put("symbol", stockPlan.getSymbol());
        }
        if (StringUtils.isNotEmpty(stockPlan.getCycle())) {
            sql.append("      AND stp.cycle = :cycle ");
            map.put("cycle", stockPlan.getCycle());
        }
        if (StringUtils.isNotEmpty(stockPlan.getOrderStatus())) {
            sql.append("      AND stp.order_status in :orderStatus ");
            map.put("orderStatus", stockPlan.getOrderStatus().split(","));
        }
        if (StringUtils.isNotEmpty(loginName)) {
            sql.append("      AND user.login_name like :loginName ");
            map.put("loginName", "%" + loginName + "%");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND stp.create_time >= :startTime ");
            map.put("startTime", startTime );
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND stp.create_time <= :endTime ");
            map.put("endTime", endTime);
        }
        //生效时间
        if (EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            sql.append("      AND stp.buy_confirm_date >= :buyConfirmDatetartTime ");
            map.put("buyConfirmDatetartTime", buyConfirmDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            sql.append("      AND stp.buy_confirm_date <= :buyConfirmDateEndTime ");
            map.put("buyConfirmDateEndTime", buyConfirmDateEndTime);
        }

        //结算时间
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            sql.append("      AND stp.sell_confirm_time >= :sellConfirmTimeDatetartTime ");
            map.put("sellConfirmTimeDatetartTime", sellConfirmTimeDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            sql.append("      AND stp.sell_confirm_time <= :sellConfirmTimeDateEndTime ");
            map.put("sellConfirmTimeDateEndTime", sellConfirmTimeDateEndTime );
        }

        if(EmptyUtils.isNotEmpty(isProfit)&&isProfit==0){
            sql.append(" AND stp.net_profit < 0 ");
        }
        if(EmptyUtils.isNotEmpty(isProfit)&&isProfit==1){
            sql.append(" AND stp.net_profit >= 0 ");
        }

        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
            if (index==3) {
                //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
                sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = " + belongUserId);
            }
        }
        if (StringUtils.isNotEmpty(userName)) {
            sql.append("      AND tui.user_name like :userName ");
            map.put("userName","%"+userName+"%");
        }

        Page queryPage = sqlDao.queryPage(sql.toString(), page, map);
        page.setTotalCount(queryPage.getTotalCount());
        page.setResult(queryPage.getResult());
        return queryPage.getResult();
    }

    public List groupQueryStockPlanListServerFee(TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit) {
        Map map = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT");
        sql.append("  SUM(stp.manage_fee)                  AS manageFee,");
        sql.append("  SUM(stp.service_fee)                 AS serviceFee,");
        sql.append("  SUM(stp.manage_fee+stp.service_fee)  AS fee ");
        sql.append(" from t_stock_plan stp ");
        sql.append("  LEFT JOIN t_user user ON stp.user_id = user.user_id ");
        sql.append("  LEFT JOIN t_user_info tui ON tui.user_id = stp.user_id ");
        sql.append("WHERE 1 = 1 ");
        if (stockPlan.getPlanId() != null) {
            sql.append("      AND stp.plan_id =  "+stockPlan.getPlanId());
//            map.put("id", stockPlan.getPlanId());
        }
        if (StringUtils.isNotEmpty(stockPlan.getSymbol())) {
            sql.append("      AND stp.symbol =  "+ stockPlan.getSymbol());
//            map.put("symbol", stockPlan.getSymbol());
        }
        if (StringUtils.isNotEmpty(stockPlan.getCycle())) {
            sql.append("      AND stp.cycle = '"+stockPlan.getCycle()+"'");
            map.put("cycle", stockPlan.getCycle());
        }
        if (StringUtils.isNotEmpty(stockPlan.getOrderStatus())) {
            sql.append("      AND stp.order_status =  "+stockPlan.getOrderStatus());
            map.put("orderStatus", stockPlan.getOrderStatus());
        }
        if (StringUtils.isNotEmpty(loginName)) {
            sql.append("      AND user.login_name like  "+"'%" + loginName + "%'");
            map.put("loginName", "%" + loginName + "%");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND stp.create_time >= '"+startTime+"'");
            map.put("startTime", startTime );
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND stp.create_time <= '"+endTime+"'");
            map.put("endTime", endTime);
        }
        //生效时间
        if (EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            sql.append("      AND stp.buy_confirm_date >=  '"+buyConfirmDatetartTime+"'");
            map.put("buyConfirmDatetartTime", buyConfirmDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            sql.append("      AND stp.buy_confirm_date <='"+buyConfirmDateEndTime+"'");
            map.put("buyConfirmDateEndTime", buyConfirmDateEndTime);
        }

        //结算时间
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            sql.append("      AND stp.sell_confirm_time >= '"+sellConfirmTimeDatetartTime+"'");
            map.put("sellConfirmTimeDatetartTime", sellConfirmTimeDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            sql.append("      AND stp.sell_confirm_time <= '"+sellConfirmTimeDateEndTime+"'");
            map.put("sellConfirmTimeDateEndTime", sellConfirmTimeDateEndTime );
        }

        if(EmptyUtils.isNotEmpty(isProfit)&&isProfit==0){
            sql.append(" AND stp.net_profit < 0 ");
        }
        if(EmptyUtils.isNotEmpty(isProfit)&&isProfit==1){
            sql.append(" AND stp.net_profit >= 0 ");
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
            if (index==3) {
                //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
                sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = " + belongUserId);
            }
        }
        if (StringUtils.isNotEmpty(userName)) {
            sql.append("      AND tui.user_name like  "+"'%"+userName+"%'");
            map.put("userName","%"+userName+"%");
        }
        return sqlDao.getAll(sql.toString());
    }

    @Override
    public HashMap queryStockPlan(TStockPlan stockPlan,String startTime,String endTime) {
        List<HashMap> hashMaps = queryStockPlanList(new Page(), stockPlan, null,startTime,endTime,null,null,null,null,null,null,null,null,null,null,null);
        if (hashMaps.size() == 0)
            throw new RuntimeException("没有查询到方案");
        return hashMaps.get(0);
    }

    @Override
    public void exportBuyStockPlanList(HttpServletResponse response, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit) {
        Page page = new Page();
        page.setNumPerPage(Integer.MAX_VALUE);
        stockPlan.setOrderStatus("1");//固定为只有申请买入的单子才能导出
        List<HashMap> planList = queryStockPlanList(page, stockPlan, loginName,startTime,endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime,isProfit);
        List<TDictionary> cycleList = dictionaryService.queryPlanCycleList(null);
//        Date now = new Date();
        //导出的时间需要多加上一天
        //又TM不要了.变成T+0了
        Calendar nowC = Calendar.getInstance();
//        nowC.add(Calendar.DATE, 1);

        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));

        List<Map> planMapList = new LinkedList<Map>();
        for (Map plan : planList) {
            TStockPlan tStockPlan = stockPlanService.get(TStockPlan.class, Integer.parseInt(plan.get("planId").toString()));
            tStockPlan.setBuyRecommendDate(nowC.getTime());
//            tStockPlan.setBuyConfirmDate(now);
//            tStockPlan.setBuyEndDate(getBuyEndDate(now, tStockPlan.getCycle()));

            Map<String, Object> map = JSONObject.fromObject(tStockPlan, config);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() == null || entry.getValue() instanceof JSONNull) {
                    entry.setValue("");
                }
            }
            String kaicangzhiling = map.get("buyRecommendDate").toString()
                    + (tStockPlan.getBuyLimitPrice().compareTo(BigDecimal.ZERO) > 0 ?
                    ("当天限价" + map.get("buyLimitPrice")) : "当天市价");
            map.put("cycle", getCycleString(cycleList, tStockPlan.getCycle()));
            map.put("qiquanleixing", "平值看涨");
            map.put("buyPrice", "");
            map.put("direction", "买入");
            map.put("kaicangzhiling", kaicangzhiling);
            map.put("shifouchengjiao", "");
            planMapList.add(map);
        }

        String date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String fileName = "申请方案" + date;
//        客户经理	客户名称	股票名称	股票代码	期限（月）	方向	报价	期权类型	行权价	名义本金	开仓指令	平仓指令
        String[] headers = {
                "订单号", "建议日期", "股票编码", "股票名称", "周期", "期初市值", "期权类型", "买入限价", "买入价格", "方向", "开仓指令", "是否成交"
        };
        String[] columns = {
                "planId", "buyRecommendDate", "symbol", "symbolName", "cycle", "buyMarketPrice", "qiquanleixing", "buyLimitPrice", "buyPrice", "direction", "kaicangzhiling", "shifouchengjiao"
        };
        try {
            ExcelUtils.exportExcel(response, fileName, planMapList, columns, headers, SysCode.DATE_FORMAT_STR_S);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void exportSellStockPlanList(HttpServletResponse response, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit) {
        Page page = new Page();
        page.setNumPerPage(Integer.MAX_VALUE);
        stockPlan.setOrderStatus("3");//固定为只有申请行权的单子才能导出
//        List<HashMap> planListMap = queryStockPlanList(page, stockPlan, loginName,startTime,endTime,null,null,null,null,null,null);
        List<HashMap> planListMap = queryStockPlanList(page, stockPlan, loginName,startTime,endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime,isProfit);
        List<TDictionary> cycleList = dictionaryService.queryPlanCycleList(null);

        //导出的时间需要多加上一天
        //又TM不要了,变T+0了
        Calendar nowC = Calendar.getInstance();
//        nowC.add(Calendar.DATE, 1);

        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));

        List<Map> planMapList = new LinkedList<Map>();
        String nowDate=DateUtils.doFormatDate(new Date(),"yyyy-MM-dd");
        for (Map planMap : planListMap) {
            TStockPlan plan;
            plan = stockPlanService.get(TStockPlan.class, Integer.valueOf(planMap.get("planId").toString()));

            /* plan.setSellCreateTime(nowC.getTime());*/
            //stockPlanService.update(plan);

           // planMap.put("sellCreateTime", nowC.getTime());

            Map<String, Object> map = JSONObject.fromObject(planMap, config);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() == null || entry.getValue() instanceof JSONNull) {
                    entry.setValue("");
                }
            }
            String kaicangzhiling = map.get("sellCreateTime").toString()
                    + (BigDecimal.ZERO.compareTo(EmptyUtils.isEmpty(plan.getSellLimitPrice())?BigDecimal.ZERO:plan.getSellLimitPrice()) < 0 ?
                    ("当天限价" + map.get("sellLimitPrice")) : "当天市价");
            map.put("cycle", getCycleString(cycleList, plan.getCycle()));
            map.put("qiquanleixing", "平值看涨");
            map.put("sellPrice", "");
            map.put("direction", "卖出");
            map.put("pingcangzhiling", kaicangzhiling);
            map.put("shifouchengjiao", "");
            map.put("sellCreateTime", nowDate);
            planMapList.add(map);
        }

        String date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String fileName = "行权方案" + date;
//        客户经理	客户名称	股票名称	股票代码	期限（月）	方向	报价	期权类型	行权价	名义本金	开仓指令	平仓指令
        String[] headers = {
                "订单号", "建议日期", "股票编码", "股票名称", "周期", "期初市值", "期权类型", "卖出限价", "卖出价格", "方向", "平仓指令", "是否成交"
        };
        String[] columns = {
                "planId", "sellCreateTime", "symbol", "symbolName", "cycle", "sellMarketPrice", "qiquanleixing", "sellLimitPrice", "sellPrice", "direction", "pingcangzhiling", "shifouchengjiao"
        };
        try {
            ExcelUtils.exportExcel(response, fileName, planMapList, columns, headers, SysCode.DATE_FORMAT_STR_S);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }


    private String getCycleString(List<TDictionary> cycleList, String cycle) {
        for (TDictionary dictionary : cycleList) {
            if (dictionary.getItemCode().equals(cycle)) {
                return dictionary.getItemName();
            }
        }
        return "未知周期";
    }

    @Transactional
    @Override
    public List<Map> importBuyStockPlanList(MultipartFile stockPlanFile) {
        try {
            List<Map> list = ExcelUtils.readImportBuyWorkbook(stockPlanFile.getInputStream());
            //根据之前的表格更新建议日期,生效日期并计算结束日期
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);
                if (i == 0 || map.get("planId") == null || map.get("planId").toString().isEmpty()) {
                    continue;
                }

                String shifouchengjiao = map.get("shifouchengjiao").toString();
                String planId = map.get("planId").toString();
                String buyRecommendDateString = map.get("buyRecommendDate").toString();

                boolean isDone = shifouchengjiao.equals("1");
                Date buyRecommendDate = DateUtils.doFormatDate(buyRecommendDateString, "yyyy-MM-dd");
                BigDecimal buyPrice = isDone ? new BigDecimal(map.get("buyPrice").toString()) : BigDecimal.ZERO;

                stockPlanService.buyHandle(isDone, Integer.parseInt(planId), buyRecommendDate, buyPrice);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public List<Map> importSellStockPlanList(MultipartFile stockPlanFile) {
        try {
            List<Map> list = ExcelUtils.readImportSellWorkbook(stockPlanFile.getInputStream());
            Date now = new Date();
            //根据之前的表格更新建议日期,生效日期并计算结束日期
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);
                if (i == 0 || map.get("planId") == null || map.get("planId").toString().isEmpty()) {
                    continue;
                }

                String shifouchengjiao = map.get("shifouchengjiao").toString();
                boolean isDone = shifouchengjiao.equals("1");
                Integer planId = Integer.parseInt(map.get("planId").toString());
                BigDecimal sellPrice = isDone ? new BigDecimal(map.get("sellPrice").toString()) : BigDecimal.ZERO;
                String buyRecommendDate=map.get("buyRecommendDate").toString();
                stockPlanService.sellHandle(isDone, planId, sellPrice,buyRecommendDate);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *  查询账户信息列表
     * @param page
     * @param userAccount
     * @return
     */
    @Override
    public Page queryUserAccountList(Page page, TUserAccount userAccount, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo, Integer belongUserId, Integer parentCodeIndex ) {

        return userAccountService.queryUserAccountList(page,userAccount,user,currentUser,startTime,endTime,userInfo,belongUserId,parentCodeIndex);
    }

    /**
     * 查询账户流水信息列表
     * @param page
     * @param userAccountFlow
     * @return
     */
    @Override
    public Page<TUserAccountFlow> queryUserAccountFlowList(Page page, TUserAccountFlow userAccountFlow,TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex) {
        return userAccountFlowService.queryUserAccountFlowList(page,userAccountFlow,user,currentUser,startTime,endTime,userInfo,belongUserId,parentCodeIndex);
    }

    /**
     * 查询聚合数据与查询列表配套
     * @param userAccountFlow
     * @param user
     * @param currentUser
     * @param startTime
     * @param endTime
     * @param userInfo
     * @param belongUserId
     * @param parentCodeIndex
     * @return
     */
    @Override
    public List groupQueryUserAccountFlowList(TUserAccountFlow userAccountFlow, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex ) {
        return userAccountFlowService.groupQueryUserAccountFlowList(userAccountFlow,user,currentUser,startTime,endTime,userInfo,belongUserId,parentCodeIndex);
    }


    @Override
    public Page<TStockPlan> queryStockPlanList(Page page, TStockPlan stockPlan) {
        return stockPlanService.queryStockPlanList(page,stockPlan);
    }

    @Override
    public Page queryUserByInvite(Page page, HttpSession session,TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex,String parentLoginName) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        return userService.queryUserByInvite(page,currentUser,selectInviteUser, userInfo, startTime, endTime,belongUserId, parentCodeIndex,parentLoginName);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public TUser addUser(HttpSession session,TUser user,TRole role,Integer parentUserId) {
        TUser willAddUser = userService.get(TUser.class,"loginName",user.getLoginName());
        if(EmptyUtils.isNotEmpty(willAddUser)){
            throw new RuntimeException("登录名已存在");
        }
        TUser doAddUser = HttpSessionUtils.getCurrentUser(session);
        if(EmptyUtils.isNotEmpty(user.getParentCode())) {
            //判断选择推广员是不是选了自己
            if (!(user.getParentCode().equals(doAddUser.getUserId().toString()))) {
                //选择旗下某人，根据传上来的parentCode(他的)查询此人的parentCodeuserId
                TUser parentUser = userService.get(TUser.class, Integer.valueOf(user.getParentCode()));
                if(parentUser.getUserType()== Constants.USER_TYPE_CHILD_02){
                    user.setParentCode(parentUser.getUserId().toString());
                }else {
                    user.setParentCode(parentUser.getParentCode() + "," + parentUser.getUserId());
                }
            }else {
                user.setParentCode(doAddUser.getParentCode() + "," + doAddUser.getUserId());
            }
        }else {
            if(EmptyUtils.isNotEmpty(parentUserId)){//后台注册userType=5的客户
                TUser parentUser = userService.get(TUser.class,parentUserId);
                user.setParentCode(parentUser.getParentCode()+","+parentUser.getUserId());
            }else {
                //没选择以及选择的类型不是推广员或者第三方机构，直接记录添加者的parentCode+其userId
                if (user.getUserType() == Constants.USER_TYPE_CHILD_02) {//添加一级不留parentCode
                    user.setParentCode("");
                } else if (user.getUserType() == Constants.USER_TYPE_THIRD_03) {
                    user.setParentCode(doAddUser.getUserId().toString());
                } else {
                    user.setParentCode(doAddUser.getParentCode() + "," + doAddUser.getUserId());
                }
            }
        }
        user.setLoginPass(Constants.USER_DEFULT_PASSWORD);
        //如果是添加代理商的时候,把登录的用户的账户附加在添加的用户登录号前.代理商userType=4
        if (user.getUserType().intValue() == 4) {
            user.setLoginName(doAddUser.getLoginName() + user.getLoginName());
        }
        TUser tUser = userService.save(user,role);
        //添加用户信息表
        TUserInfo userInfo = new TUserInfo();
        userInfo.setUserId(tUser.getUserId());
        if(tUser.getUserType()>5){
            userInfo.setUserName(tUser.getLoginName());
        }
        userInfoService.save(userInfo);
        //保存完用户表信息后，新增账户信息
        TUserAccount userAccount = new TUserAccount(
                tUser.getUserId(),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new Date(),
                new Date()
        );
        userAccountService.save(userAccount);
        return tUser;
    }

    @Override
    public TUser updateUser(HttpSession session, TUser user, TRole role) {
        TUser targetUser = userService.get(TUser.class,user.getUserId());
        targetUser.setNickName(user.getNickName());
        targetUser.setUserSex(user.getUserSex());
        targetUser.setUserPhone(user.getUserPhone());
        userService.update(targetUser);
        TUserRole userRole = userRoleService.get(TUserRole.class,"userId",user.getUserId());
        if(EmptyUtils.isNotEmpty(role.getRoleId())){
            userRole.setRoleId(role.getRoleId());
            userRoleService.update(userRole);
        }
        return null;
    }

    @Override
    public Page queryUserList(Page page, TUser user) {
        return userService.queryPage(page,user);
    }

    @Override
    public List<HashMap> queryStockPlanListBySth(HttpSession session,Page page, TStockPlan stockPlan, String loginName,String belong,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  stp.plan_id                 AS planId, ");
        sql.append("  stp.user_id                 AS userId, ");
        sql.append("  stp.symbol                  AS symbol, ");
        sql.append("  stp.symbol_name             AS symbolName, ");
        sql.append("  stp.cycle                   AS cycle, ");
        sql.append("  stp.buy_recommend_date      AS buyRecommendDate, ");
        sql.append("  stp.buy_confirm_date        AS buyConfirmDate, ");
        sql.append("  stp.buy_end_date            AS buyEndDate, ");
        sql.append("  stp.buy_market_price        AS buyMarketPrice, ");
        sql.append("  stp.buy_price_type          AS buyPriceType, ");
        sql.append("  stp.buy_limit_price         AS buyLimitPrice, ");
        sql.append("  stp.buy_price               AS buyPrice, ");
        sql.append("  stp.sell_market_price       AS sellMarketPrice, ");
        sql.append("  stp.sell_price_type         AS sellPriceType, ");
        sql.append("  stp.sell_limit_price        AS sellLimitPrice, ");
        sql.append("  stp.sell_create_time        AS sellCreateTime, ");
        sql.append("  stp.sell_price              AS sellPrice, ");
        sql.append("  stp.sell_confirm_time       AS sellConfirmTime, ");
        sql.append("  stp.service_fee             AS serviceFee, ");
        sql.append("  stp.manage_fee              AS manageFee, ");
        sql.append("  stp.order_status            AS orderStatus, ");
        sql.append("  stp.create_time             AS createTime, ");
        sql.append("  user.login_name             AS loginName, ");
        sql.append("  user.nick_name              AS nickName, ");
        sql.append("  tui.user_name               AS userName ");
        sql.append("from t_stock_plan stp ");
        sql.append("  LEFT JOIN t_user user ON stp.user_id = user.user_id ");
        sql.append("  LEFT JOIN t_user_info tui ON tui.user_id = stp.user_id ");
        sql.append("WHERE 1 = 1 ");
        if (stockPlan.getPlanId() != null) {
            sql.append("      AND stp.plan_id = :id ");
            map.put("id", stockPlan.getPlanId());
        }
        if (StringUtils.isNotEmpty(stockPlan.getSymbol())) {
            sql.append("      AND stp.symbol = :symbol ");
            map.put("symbol", stockPlan.getSymbol());
        }
        if (StringUtils.isNotEmpty(userName)) {
            sql.append("      AND tui.user_name = :userName ");
            map.put("userName","%"+userName+"%");
        }
        if (StringUtils.isNotEmpty(stockPlan.getCycle())) {
            sql.append("      AND stp.cycle = :cycle ");
            map.put("cycle", stockPlan.getCycle());
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
            if (index==3) {
                //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
                sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = " + belongUserId);
            }
        }

//        if (EmptyUtils.isNotEmpty(yunYingUserId)) {
//            int index = 1;//所属运营中心
//            sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+yunYingUserId);
//        }
//        if (EmptyUtils.isNotEmpty(huiYuanUserId)) {
//            int index = 2;//所属会员
//            sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+huiYuanUserId);
//        }
//        if (EmptyUtils.isNotEmpty(daiLiUserId)) {
//            //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
//            sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = "+daiLiUserId);
//        }

        if (StringUtils.isNotEmpty(stockPlan.getOrderStatus())) {
            sql.append("      AND stp.order_status in :orderStatus ");
            map.put("orderStatus", stockPlan.getOrderStatus().split(","));
        }
        if (StringUtils.isNotEmpty(loginName)) {
            sql.append("      AND user.login_name like :loginName ");
            map.put("loginName", "%" + loginName + "%");
        }
        //查询
        if(Constants.USER_TYPE_MOTHER_01!=user.getUserType() && EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(belong)&&"self".equals(belong)){
            sql.append("   AND   SUBSTRING_INDEX(user.parent_code,',',-1) = :userId");
            map.put("userId",user.getUserId());
        }
        if(Constants.USER_TYPE_MOTHER_01!=user.getUserType() && EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(belong)&&"child".equals(belong)){
//            sql.append(" AND FIND_IN_SET("+user.getUserId()+", user.parent_code) > 0");
            sql.append(" AND SUBSTRING_INDEX(user.parent_code,',',-1) = "+user.getUserId());
        }
        if(Constants.USER_TYPE_MOTHER_01!=user.getUserType() && EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(belong)&&"all".equals(belong)){
            sql.append(" AND FIND_IN_SET("+user.getUserId()+", user.parent_code) > 0");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND stp.create_time >= :startTime ");
            map.put("startTime", startTime);
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND stp.create_time <= :endTime ");
            map.put("endTime", endTime);
        }
        //生效时间
        if (EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            sql.append("      AND stp.buy_confirm_date >= :buyConfirmDatetartTime ");
            map.put("buyConfirmDatetartTime", buyConfirmDatetartTime);
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            sql.append("      AND stp.buy_confirm_date <= :buyConfirmDateEndTime ");
            map.put("buyConfirmDateEndTime", buyConfirmDateEndTime);
        }

        //结算时间
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            sql.append("      AND stp.sell_confirm_time >= :sellConfirmTimeDatetartTime ");
            map.put("sellConfirmTimeDatetartTime", sellConfirmTimeDatetartTime);
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            sql.append("      AND stp.sell_confirm_time <= :sellConfirmTimeDateEndTime ");
            map.put("sellConfirmTimeDateEndTime", sellConfirmTimeDateEndTime );
        }
        Page queryPage = sqlDao.queryPage(sql.toString(), page, map);
        page.setTotalCount(queryPage.getTotalCount());
        page.setResult(queryPage.getResult());
        return queryPage.getResult();
    }

    @Override
    public List<HashMap> groupQueryStockPlanListBySth(HttpSession session,TStockPlan stockPlan, String loginName,String belong,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  SUM(stp.manage_fee)                  AS manageFee,");
        sql.append("  SUM(stp.service_fee)                 AS serviceFee,");
        sql.append("  SUM(stp.manage_fee+stp.service_fee)  AS fee ");
        sql.append("from t_stock_plan stp ");
        sql.append("  LEFT JOIN t_user user ON stp.user_id = user.user_id ");
        sql.append("  LEFT JOIN t_user_info tui ON tui.user_id = stp.user_id ");
        sql.append("WHERE 1 = 1 ");
        if (stockPlan.getPlanId() != null) {
            sql.append("      AND stp.plan_id = "+stockPlan.getPlanId());
            map.put("id", stockPlan.getPlanId());
        }
        if (StringUtils.isNotEmpty(stockPlan.getSymbol())) {
            sql.append("      AND stp.symbol ="+stockPlan.getSymbol());
            map.put("symbol", stockPlan.getSymbol());
        }
        if (StringUtils.isNotEmpty(userName)) {
            sql.append("      AND tui.user_name like "+"'%"+userName+"%'");
            map.put("userName","'%"+userName+"%'");
        }
        if (StringUtils.isNotEmpty(stockPlan.getCycle())) {
            sql.append("      AND stp.cycle = '"+stockPlan.getCycle()+"'");
            map.put("cycle", stockPlan.getCycle());
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
            if (index==3) {
                //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
                sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = " + belongUserId);
            }
        }

//        if (EmptyUtils.isNotEmpty(yunYingUserId)) {
//            int index = 1;//所属运营中心
//            sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+yunYingUserId);
//        }
//        if (EmptyUtils.isNotEmpty(huiYuanUserId)) {
//            int index = 2;//所属会员
//            sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+huiYuanUserId);
//        }
//        if (EmptyUtils.isNotEmpty(daiLiUserId)) {
//            //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
//            sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = "+daiLiUserId);
//        }

        if (StringUtils.isNotEmpty(stockPlan.getOrderStatus())) {
            sql.append("      AND stp.order_status ="+stockPlan.getOrderStatus());
            map.put("orderStatus", stockPlan.getOrderStatus().split(","));
        }
        if (StringUtils.isNotEmpty(loginName)) {
            sql.append("      AND user.login_name like "+"'%" + loginName + "%'");
            map.put("loginName", "%" + loginName + "%");
        }
        if(Constants.USER_TYPE_MOTHER_01!=user.getUserType() && EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(belong)&&"self".equals(belong)){
            sql.append("   AND   SUBSTRING_INDEX(user.parent_code,',',-1) = "+user.getUserId());
            map.put("userId",user.getUserId());
        }
        if(Constants.USER_TYPE_MOTHER_01!=user.getUserType() && EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(belong)&&"child".equals(belong)){
//            sql.append(" AND FIND_IN_SET("+user.getUserId()+", user.parent_code) > 0");
            sql.append(" AND SUBSTRING_INDEX(user.parent_code,',',-1) = "+user.getUserId());
        }
        if(Constants.USER_TYPE_MOTHER_01!=user.getUserType() && EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(belong)&&"all".equals(belong)){
            sql.append(" AND FIND_IN_SET("+user.getUserId()+", user.parent_code) > 0");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND stp.create_time >=  '"+startTime+"'");
            map.put("startTime", startTime);
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND stp.create_time <=  '"+endTime+"'");
            map.put("endTime", endTime);
        }
        //生效时间
        if (EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            sql.append("      AND stp.buy_confirm_date >= '"+buyConfirmDatetartTime+"'");
            map.put("buyConfirmDatetartTime", buyConfirmDatetartTime);
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            sql.append("      AND stp.buy_confirm_date <= '"+buyConfirmDateEndTime+"'");
            map.put("buyConfirmDateEndTime", buyConfirmDateEndTime);
        }

        //结算时间
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            sql.append("      AND stp.sell_confirm_time >=  '"+sellConfirmTimeDatetartTime+"'");
            map.put("sellConfirmTimeDatetartTime", sellConfirmTimeDatetartTime);
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            sql.append("      AND stp.sell_confirm_time <=  '"+sellConfirmTimeDateEndTime+"'");
            map.put("sellConfirmTimeDateEndTime", sellConfirmTimeDateEndTime );
        }
        return sqlDao.getAll(sql.toString());
    }

    @Override
    public TStock getStock(TStock stock) {
        return stockService.get(TStock.class,stock.getSymbol());
    }

    @Override
    public TStock updateStock(TStock stock) {
        TStock tStock = stockService.get(TStock.class,stock.getSymbol());
        tStock.setSymbolPrefix(stock.getSymbolPrefix());
        return stockService.update(tStock);
    }

    @Override
    public Page queryStockList(Page page,TStock stock) {
        return stockService.queryPage(page,stock);
    }

    /**
     * 批量替换股票代码前缀
     * @return
     */
    @Override
    public int updateBatchStockPrefix(TStock stock){
        List<TStock> list = stockService.getAll(stock);
        for (int i = 0;i<list.size();i++){
            TStock tStock = list.get(i);
            stockService.updateBatchStockPrefix(tStock);
        }
        return 1;
    }

    /**
     * 查询字典表
     * @param page
     * @param dictionary
     * @return
     */
    @Override
    public List queryDictionaryList(Page page, TDictionary dictionary) {
        List queryList = dictionaryService.getDictionaryList(dictionary);
        return queryList;
    }

    /**
     * 功能描述:查询字典表 父级
     * @author zhujingbo
     * @return
     */
    public List<TDictionary> getQuoteModeList(String parentCode){
        return dictionaryService.getQuoteModeList(parentCode);
    }


    @Deprecated
    @Override
    public TUserInfo updateUserInfo(TUserInfo userInfo){
       TUserInfo tUserInfo = userInfoService.get(TUserInfo.class,userInfo.getUserId());
        tUserInfo.setStatus(userInfo.getStatus());
        if(userInfo.getStatus()==1){
            logger.info("审核通过，调用阿里云短信通知");
        }else if (userInfo.getStatus()==2){
            logger.info("驳回，调用阿里云短信通知");
        }else {
            logger.info("状态不对");
        }
       return  userInfoService.update(tUserInfo);
    }

    @Override
    public Page queryUserInfoList(Page page,TUserInfo userInfo,TUser user){
        return userInfoService.queryPage(page,userInfo,user);
    }

    @Override
    public Page queryUserAndParentInfoList(Page page, TUser user,HttpSession session, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        return userService.queryUserAndParentInfoList(page,user,currentUser, userInfo, startTime, endTime,belongUserId,parentCodeIndex);
    }

    public int updateSetting(String time,String count,String limit){
        TDictionary timeObject = dictionaryService.get(TDictionary.class,"parentCode","ALLOW_WITHDRAW_TIME");
        TDictionary countObject = dictionaryService.get(TDictionary.class,"parentCode","MAX_WITHDRAW_COUNT");
        TDictionary limitObject = dictionaryService.get(TDictionary.class,"parentCode","MAX_WITHDRAW_LIMIT");

        timeObject.setItemCode(time);
        countObject.setItemCode(count);
        limitObject.setItemCode(limit);

        dictionaryService.update(timeObject);
        dictionaryService.update(countObject);
        dictionaryService.update(limitObject);
        return 1;
    }

    /**
     *
     * @param page
     * @param stockPlan
     * @return
     */
    @Override
    public Page queryFeeAndProfitInfo(TUser currentUser,Page page,TStockPlan stockPlan,TUser user){
        return stockPlanService.queryFeeAndProfitInfo(currentUser,page,stockPlan,user);
    }

    @Override
    public Page queryCapitalflow(Page page, TUserAccountFlow userAccountFlow,String type,TUser user,String loginName, String startTime, String endTime,Integer belongUserId,String userName,Integer parentCodeIndex) {
        return userAccountFlowService.queryCapitalflow(page,userAccountFlow,type,user,loginName,startTime,endTime,belongUserId,userName,parentCodeIndex);
    }

    @Override
    public Page queryStockPlanFeeInfo(TUser currentUser,Page page, TStockPlan stockPlan,TUser user,String startTime,String endTime,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime){
        return  stockPlanService.queryStockPlanFeeInfo(currentUser,page,stockPlan,user,startTime,endTime,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime);
    }

    @Override
    public List groupQueryStockPlanFeeInfo(TUser currentUser, TStockPlan stockPlan,TUser user,String startTime,String endTime,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime){
        return  stockPlanService.groupQueryStockPlanFeeInfo(currentUser,stockPlan,user,startTime,endTime,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime);
    }

    @Override
    public List queryRoleList(TRole role) {
        return roleService.getAll(role);
    }

    @Override
    public List groupQueryAllServerFee(TUser currentUser,String startTime,String endTime){
        return  stockPlanService.groupQueryAllServerFee(currentUser,startTime,endTime);
    }


    @Transactional
    @Override
    public List<Map> importStockInfoList(MultipartFile stockPlanFile) {
        //导入股票excel
        try {
            //excel数据
            String[] columns = new String[]{
                    "code",
                    "name",
                    "hv30",
                    "hv60",
                    "hv14"
            };
            List<Map> list = ExcelUtils.readWorkbook(stockPlanFile.getInputStream(), columns);

            if (list.size() == 0) return null;

            stockInfoService.truncate();

            //导入股票
            //这里有一个步骤,需要去字典表里找需要带入运算的值
            TDictionary dictionary = dictionaryService.getDictionary("IMPORT_STOCK_INFO_NUMBER", true);
            BigDecimal number = new BigDecimal(dictionary.getItemCode());

            //循环导入
            for (int i = 0; i < list.size(); i++) {
                //首先要跳过第一行 题头
                if(i == 0) continue;
                Map map = list.get(i);

                //有一个要注意的重点,由于表格中的数字有百分号,这里获取值的时候会得到原来的数/100后的结果,存在数据库之前需要调整
                //手续费加上字典的值
                BigDecimal hv30 = new BigDecimal(map.get("hv30").toString()).multiply(new BigDecimal(100)).add(number);
                BigDecimal hv60 = new BigDecimal(map.get("hv60").toString()).multiply(new BigDecimal(100)).add(number);
                BigDecimal hv14 = new BigDecimal(map.get("hv14").toString()).multiply(new BigDecimal(100)).add(number);

                TStockInfo stockInfo = new TStockInfo(
                        map.get("code").toString(),
                        map.get("name").toString(),
                        hv30,
                        hv60,
                        hv14
                );
                stockInfoService.save(stockInfo);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
