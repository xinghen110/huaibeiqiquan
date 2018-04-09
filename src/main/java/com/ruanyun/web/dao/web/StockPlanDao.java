package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TStockPlan;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockPlanDao extends BaseDaoImpl<TStockPlan> {
    @Override
    public Page<TStockPlan> queryPage(Page<TStockPlan> page, TStockPlan stockPlan) {
        StringBuilder sql = new StringBuilder("select * from t_stock_plan where 1=1 ");
        if (EmptyUtils.isNotEmpty(stockPlan)) {
            sql.append(SQLUtils.popuHqlEq("order_status", stockPlan.getOrderStatus()));
        }
        return sqlDao.queryPage(page,TStockPlan.class,sql.toString());
    }

    public Page queryFeeAndProfitInfo(TUser currentUser,Page page, TStockPlan stockPlan, TUser user) {
        Map map = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT * from (");
        sql.append("  SELECT");
        sql.append("  u.login_name                         AS loginName,");
        sql.append("  tsp.user_id                          AS userId,");
        sql.append("  SUM(tsp.manage_fee+tsp.service_fee)  AS fee,");
        sql.append("  SUM(tsp.profit)		               AS profit,");
        sql.append("  SUM(tsp.profit)/SUM(tsp.service_fee)  AS yieldRate,");
        sql.append("  SUM(tsp.net_profit)                   AS netProfit,");
        sql.append("  SUM(CASE WHEN tsp.profit>0 THEN 1 ELSE 0 END)/COUNT(*) AS accuracyRate ");
        sql.append("  from t_stock_plan tsp LEFT JOIN t_user u ON u.user_id = tsp.user_id");
        sql.append("  WHERE 1 = 1 ");
        if(EmptyUtils.isNotEmpty(user.getLoginName())){
            sql.append("  and u.login_name like :loginName");
            map.put("loginName","%"+user.getLoginName()+"%");
        }
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
//			sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
        sql.append("  GROUP BY tsp.user_id ");
        sql.append("   ) t  ");
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }

    public List groupQueryAllServerFee(TUser currentUser,String startTime,String endTime) {
        Map map = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT * from (");
        sql.append("  SELECT");
        sql.append("  u.login_name                         AS loginName,");
        sql.append("  tsp.user_id                          AS userId,");
        sql.append("  SUM(tsp.service_fee)                 AS serviceFee,");
        sql.append("  SUM(tsp.manage_fee+tsp.service_fee)  AS fee,");
        sql.append("  SUM(tsp.profit)		               AS profit,");
        sql.append("  SUM(tsp.profit)/SUM(tsp.service_fee)  AS yieldRate,");
        sql.append("  SUM(tsp.net_profit)                   AS netProfit,");
        sql.append("  SUM(CASE WHEN tsp.profit>0 THEN 1 ELSE 0 END)/COUNT(*) AS accuracyRate ");
        sql.append("  from t_stock_plan tsp LEFT JOIN t_user u ON u.user_id = tsp.user_id");
        sql.append("  WHERE 1 = 1 ");
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
//			sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
//        sql.append("  GROUP BY tsp.user_id ");
        sql.append("   ) t  ");
//        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
//        page.setResult(queryPage.getResult());
//        page.setTotalCount(queryPage.getTotalCount());
        return sqlDao.getAll(sql.toString());
    }

    public List<Map> listMap(Page page, TStockPlan stockPlan, TUser user) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  tsp.plan_id                      AS planId, ");
        sql.append("  tsp.user_id                      AS userId, ");
        sql.append("  tsp.symbol                       AS symbol, ");
        sql.append("  tsp.cur_price                    AS curPrice, ");
        sql.append("  tsp.symbol_name                  AS symbolName, ");
        sql.append("  tsp.cycle                        AS cycle, ");
        sql.append("  tsp.buy_market_price             AS buyMarketPrice, ");
        sql.append("  tsp.buy_price_type               AS buyPriceType, ");
        sql.append("  tsp.buy_limit_price              AS buyLimitPrice, ");
        sql.append("  tsp.buy_recommend_date           AS buyRecommendDate, ");
        sql.append("  tsp.buy_price                    AS buyPrice, ");
        sql.append("  tsp.buy_confirm_date             AS buyConfirmDate, ");
        sql.append("  tsp.buy_end_date                 AS buyEndDate, ");
        sql.append("  tsp.sell_market_price            AS sellMarketPrice, ");
        sql.append("  tsp.sell_price_type              AS sellPriceType, ");
        sql.append("  tsp.sell_limit_price             AS sellLimitPrice, ");
        sql.append("  tsp.sell_create_time             AS sellCreateTime, ");
        sql.append("  tsp.sell_price                   AS sellPrice, ");
        sql.append("  tsp.sell_confirm_time            AS sellConfirmTime, ");
        sql.append("  tsp.service_fee                  AS serviceFee, ");
        sql.append("  tsp.manage_fee                   AS manageFee, ");
        sql.append("  tsp.service_fee + tsp.manage_fee AS fee, ");
        sql.append("  tsp.order_status                 AS orderStatus, ");
        sql.append("  tsp.create_time                  AS createTime, ");
        sql.append("  tsp.profit                       AS profit, ");
        sql.append("  tsp.net_profit                   AS netProfit, ");
        sql.append("  tu.login_name                    AS loginName, ");
        sql.append("  cycleD.item_name                 AS cycleName, ");
        sql.append("  buyPriceTypeD.item_name          AS buyPriceTypeName, ");
        sql.append("  sellPriceTypeD.item_name         AS sellPriceTypeName, ");
        sql.append("  orderStatusD.item_name           AS orderStatusName ");
        sql.append("FROM t_stock_plan tsp LEFT JOIN t_user tu ON tu.user_id = tsp.user_id ");
        sql.append("  LEFT JOIN t_dictionary cycleD ON cycleD.parent_code = 'STOCK_PLAN_CYCLE' AND cycleD.item_code = tsp.cycle ");
        sql.append("  LEFT JOIN t_dictionary buyPriceTypeD ON buyPriceTypeD.parent_code = 'STOCK_PRICE_TYPE' AND buyPriceTypeD.item_code = tsp.buy_price_type ");
        sql.append("  LEFT JOIN t_dictionary sellPriceTypeD ON sellPriceTypeD.parent_code = 'STOCK_PRICE_TYPE' AND sellPriceTypeD.item_code = tsp.sell_price_type ");
        sql.append("  LEFT JOIN t_dictionary orderStatusD ON orderStatusD.parent_code = 'STOCK_PLAN_STATUS' AND orderStatusD.item_code = tsp.order_status ");
        sql.append("WHERE 1 = 1 ");
        Map<String, Object> param = new HashMap();
        if (EmptyUtils.isNotEmpty(user.getUserId())) {
            sql.append(" and tu.user_id = :userId");
            param.put("userId", user.getUserId());
        }
        if (EmptyUtils.isNotEmpty(stockPlan.getOrderStatus())) {
            sql.append(" and tsp.order_status = :orderStatus");
            param.put("orderStatus",stockPlan.getOrderStatus());
        }
        if (EmptyUtils.isNotEmpty(user.getLoginName())) {
            sql.append(" and tu.login_name like :loginName");
            param.put("loginName", "%" + user.getLoginName() + "%");
        }
        SQLQuery sqlQuery = sqlDao.createQuery(sql.toString(), param);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return sqlQuery.list();
    }

    public Page queryStockPlanFeeInfo(TUser currentUser,Page page, TStockPlan stockPlan, TUser user,String startTime,String endTime,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime) {
        Map map = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT");
        sql.append("  u.login_name                             AS loginName,");
        sql.append("  tsp.user_id                              AS userId,");
        sql.append("  tsp.symbol                               AS symbol,");
        sql.append("  tsp.symbol_name                          AS symbolName,");
        sql.append("  tsp.cur_price                            AS curPrice, ");
        sql.append("  tsp.cycle                                AS cycle,");
        sql.append("  tsp.buy_market_price                     AS buyMarketPrice,");
        sql.append("  tsp.buy_price_type                       AS buyPriceType,");
        sql.append("  tsp.buy_limit_price                      AS buyLimitPrice,");
        sql.append("  tsp.buy_recommend_date                   AS buyRecommendDate,");
        sql.append("  tsp.buy_price                            AS buyPrice,");
        sql.append("  tsp.buy_confirm_date                     AS buyConfirmDate,");
        sql.append("  tsp.buy_end_date                         AS buyEndDate,");
        sql.append("  tsp.sell_market_price                    AS sellMarketPrice,");
        sql.append("  tsp.sell_price_type                      AS sellPriceType,");
        sql.append("  tsp.sell_limit_price                     AS sellLimitPrice,");
        sql.append("  tsp.sell_create_time                     AS sellCreateTime,");
        sql.append("  tsp.sell_price                           AS sellPrice,");
        sql.append("  tsp.order_status                         AS orderStatus,");
        sql.append("  tsp.create_time                          AS createTime,");
        sql.append("  tsp.manage_fee+tsp.service_fee           AS fee,");
        sql.append("  tsp.manage_fee                           AS manageFee,");
        sql.append("  tsp.profit                               AS profit,");
        sql.append("  tsp.net_profit                           AS netProfit,");
        sql.append("  tsp.service_fee                          AS serviceFee ");
        sql.append("  from t_stock_plan tsp LEFT JOIN t_user u ON u.user_id = tsp.user_id ");
        sql.append("  LEFT JOIN t_user_info tui ON tui.user_id = tsp.user_id ");
        sql.append("  where 1=1 ");
//        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
//            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
//                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
//            }
////			sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
//        }
        if (stockPlan.getPlanId() != null) {
            sql.append("      AND tsp.plan_id = :id ");
            map.put("id", stockPlan.getPlanId());
        }
        if (StringUtils.isNotEmpty(stockPlan.getSymbol())) {
            sql.append("      AND tsp.symbol = :symbol ");
            map.put("symbol", stockPlan.getSymbol());
        }
        if(EmptyUtils.isNotEmpty(user.getLoginName())){
            sql.append(" and u.login_name like :loginName");
            map.put("loginName","%"+user.getLoginName()+"%");
        }
        if(EmptyUtils.isNotEmpty(stockPlan.getOrderStatus())){
            sql.append("  and tsp.order_status = :orderStatus");
            map.put("orderStatus",stockPlan.getOrderStatus());
        }
        if (StringUtils.isNotEmpty(userName)) {
            sql.append("      AND tui.user_name = :userName ");
            map.put("userName","%"+userName+"%");
        }
        if (StringUtils.isNotEmpty(stockPlan.getCycle())) {
            sql.append("      AND tsp.cycle = :cycle ");
            map.put("cycle", stockPlan.getCycle());
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if (index==3) {
                sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
            }
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
        }
        if (StringUtils.isNotEmpty(stockPlan.getOrderStatus())) {
            sql.append("      AND tsp.order_status in :orderStatus ");
            map.put("orderStatus", stockPlan.getOrderStatus().split(","));
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND tsp.create_time >= :startTime ");
            map.put("startTime", startTime);
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND tsp.create_time <= :endTime ");
            map.put("endTime", endTime);
        }
        //生效时间
        if (EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            sql.append(" AND tsp.buy_confirm_date >= :buyConfirmDatetartTime");
            map.put("buyConfirmDatetartTime", buyConfirmDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            sql.append(" AND tsp.buy_confirm_date <= :buyConfirmDateEndTime");
            map.put("buyConfirmDateEndTime", buyConfirmDateEndTime);
        }
        //结算时间
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            sql.append("      AND tsp.sell_confirm_time >= :sellConfirmTimeDatetartTime");
            map.put("sellConfirmTimeDatetartTime", sellConfirmTimeDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            sql.append("      AND tsp.sell_confirm_time <= :sellConfirmTimeDateEndTime");
            map.put("sellConfirmTimeDateEndTime", sellConfirmTimeDateEndTime );
        }
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }

    public List groupQueryStockPlanFeeInfo(TUser currentUser,TStockPlan stockPlan, TUser user,String startTime,String endTime,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime) {
        Map map = new HashMap();
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT");
        sql.append("  SUM(tsp.manage_fee)                  AS manageFee,");
        sql.append("  SUM(tsp.service_fee)                 AS serviceFee,");
        sql.append("  SUM(tsp.manage_fee+tsp.service_fee)  AS fee ");
        sql.append("  from t_stock_plan tsp LEFT JOIN t_user u ON u.user_id = tsp.user_id ");
        sql.append("  LEFT JOIN t_user_info tui ON tui.user_id = tsp.user_id ");
        sql.append("  where 1=1 ");
//        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
//            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
//                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
//            }
////			sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
//        }
        if (stockPlan.getPlanId() != null) {
            sql.append("      AND tsp.plan_id = "+stockPlan.getPlanId());
            map.put("id", stockPlan.getPlanId());
        }
        if (StringUtils.isNotEmpty(stockPlan.getSymbol())) {
            sql.append("      AND tsp.symbol = "+stockPlan.getSymbol());
            map.put("symbol", stockPlan.getSymbol());
        }
        if(EmptyUtils.isNotEmpty(user.getLoginName())){
            sql.append(" and u.login_name like "+"'%"+user.getLoginName()+"%'");
            map.put("loginName","%"+user.getLoginName()+"%");
        }
        if(EmptyUtils.isNotEmpty(stockPlan.getOrderStatus())){
            sql.append("  and tsp.order_status = "+stockPlan.getOrderStatus());
            map.put("orderStatus",stockPlan.getOrderStatus());
        }
        if (StringUtils.isNotEmpty(userName)) {
            sql.append("      AND tui.user_name like "+"'%"+userName+"%'");
            map.put("userName","%"+userName+"%");
        }
        if (StringUtils.isNotEmpty(stockPlan.getCycle())) {
            sql.append("      AND tsp.cycle = '"+stockPlan.getCycle()+"'");
            map.put("cycle", stockPlan.getCycle());
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if (index==3) {
                sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
            }
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
        }
//        if (StringUtils.isNotEmpty(stockPlan.getOrderStatus())) {
//            sql.append("      AND tsp.order_status in :orderStatus ");
//            map.put("orderStatus", stockPlan.getOrderStatus().split(","));
//        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND tsp.create_time >=  '"+startTime+"'");
            map.put("startTime", startTime);
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND tsp.create_time <= '"+endTime+"'");
            map.put("endTime", endTime);
        }
        //生效时间
        if (EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            sql.append("      AND tsp.buy_confirm_date >= '"+buyConfirmDatetartTime+"'");
            map.put("buyConfirmDatetartTime", buyConfirmDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            sql.append("      AND tsp.buy_confirm_date <='"+buyConfirmDateEndTime+"'");
            map.put("buyConfirmDateEndTime", buyConfirmDateEndTime);
        }

        //结算时间
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            sql.append("      AND tsp.sell_confirm_time >='"+sellConfirmTimeDatetartTime+"'");
            map.put("sellConfirmTimeDatetartTime", sellConfirmTimeDatetartTime );
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            sql.append("      AND tsp.sell_confirm_time <='"+sellConfirmTimeDateEndTime+"'");
            map.put("sellConfirmTimeDateEndTime", sellConfirmTimeDateEndTime );
        }
        return sqlDao.getAll(sql.toString());
    }
}
