package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.TStock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StockDao extends BaseDaoImpl<TStock> {

    @Override
    public Page queryPage(Page page, TStock stock) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  symbol_name      AS symbolName,");
        sql.append("  insure_date      AS insureDate,");
        sql.append("  option_type      AS optionType,");
        sql.append("  price_mode       AS priceMode,");
        sql.append("  offer_price_date AS offerPriceDate,");
        sql.append("  symbol_prefix    AS symbolPrefix,");
        sql.append("  symbol           AS symbol,");
        sql.append("  option_code      AS optionCode,");
        sql.append("  cycle            AS cycle,");
        sql.append("  offer_price      AS offerPrice,");
        sql.append("  expire_date      AS expireDate,");
        sql.append("  exercise_mode    AS exerciseMode");
        sql.append("  from t_stock WHERE 1=1");
        if (StringUtils.isNotEmpty(stock.getSymbol())) {
            sql.append("      AND symbol like :symbol ");
            map.put("symbol", "%" + stock.getSymbol() + "%");
        }
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }
}
