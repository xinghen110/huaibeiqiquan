package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.orm.ICommonSqlDao;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.web.StockInfoDao;
import com.ruanyun.web.model.TStockInfo;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StockInfoService extends BaseServiceImpl<TStockInfo> {

    @Autowired
    private StockInfoDao stockInfoDao;
    @Autowired
    @Qualifier("commonSqlExpandDao")
    protected ICommonSqlDao sqlDao;

    public Page list(Page page, TStockInfo stockInfo) {
        return stockInfoDao.list(page,stockInfo);
    }

    public TStockInfo get(TStockInfo stockInfo) {
        return stockInfoDao.get(stockInfo);
    }

    @Transactional
    public void truncate(){
        SQLQuery query = sqlDao.createQuery("TRUNCATE TABLE t_stock_info; ");
        query.executeUpdate();
    }
}
