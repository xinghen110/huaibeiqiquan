package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.web.StockDao;
import com.ruanyun.web.model.TStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService extends BaseServiceImpl<TStock> {

    @Autowired
    StockDao stockDao;

    @Override
    public Page<TStock> queryPage(Page<TStock> page, TStock stock) {
        return stockDao.queryPage(page, stock);
    }

    /**
     * 批量替换股票代码前缀
     * @param stock
     * @return
     */
    public TStock updateBatchStockPrefix(TStock stock) {
        TStock tStock = stockDao.get(TStock.class,stock.getSymbol());
        if("60".equals(stock.getSymbol().substring(0,2))){
            tStock.setSymbolPrefix("sh");
        }else {
            tStock.setSymbolPrefix("sz");
        }
        return super.update(tStock);
    }
}
