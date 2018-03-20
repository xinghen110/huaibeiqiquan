package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.TStockInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockInfoDao extends BaseDaoImpl<TStockInfo> {

    public TStockInfo get(TStockInfo stockInfo){
        return super.get(TStockInfo.class,stockInfo.getCode());
    }

    public Page list(Page page,TStockInfo stockInfo){
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append("  tsi.code, ");
        sql.append("  tsi.name, ");
        sql.append("  tsi.hv30, ");
        sql.append("  tsi.hv60, ");
        sql.append("  tsi.hv14 ");
        sql.append(" from t_stock_info tsi ");
        sql.append(" where 1=1  ");
        if(EmptyUtils.isNotEmpty(stockInfo.getCode())) {
            sql.append("  and tsi.code ="+stockInfo.getCode());
            map.put("code",stockInfo.getCode());
        }
        sql.append(" order by tsi.hv30,tsi.code");
//        return sqlDao.queryPage(page,TStockInfo.class,sql.toString()).getResult();
        return sqlDao.queryPage(page,sql.toString());
//        return sqlDao.queryPage(sql.toString(),page,map).getResult();
//        return sqlDao.queryPage(sql.toString(),page,map).getResult();
    }

}
