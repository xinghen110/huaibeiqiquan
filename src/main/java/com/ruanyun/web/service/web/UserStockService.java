package com.ruanyun.web.service.web;

import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.web.StockInfoDao;
import com.ruanyun.web.dao.web.UserStockDao;
import com.ruanyun.web.model.TStockInfo;
import com.ruanyun.web.model.TUserStock;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStockService extends BaseServiceImpl<TUserStock> {

    @Autowired
    private UserStockDao userStockDao;

    @Autowired
    private StockInfoDao stockInfoDao;

    @Override
    public TUserStock save(TUserStock x) {
        TUserStock tUserStock = get(
                TUserStock.class,
                new String[]{"userId", "symbol"},
                new Object[]{x.getUserId(), x.getSymbol()}
        );
        if (tUserStock == null)
            tUserStock = new TUserStock(x.getUserId(), x.getSymbol());
        tUserStock = super.saveOrUpdate(tUserStock);
        return tUserStock;
    }

    public List<TUserStock> list(int userId,String symbol) {
        if(userId<0){
            return super.getAll(new TUserStock());
        }
        return super.getAll(TUserStock.class, new String[]{"userId","symbol"}, new Object[]{userId,symbol}, null, null);
    }

    public int delete(int userId, String symbol) {
        TUserStock tUserStock = get(
                TUserStock.class,
                new String[]{"userId", "symbol"},
                new Object[]{userId, symbol}
        );
        if (tUserStock == null)
            return 0;
        return super.delete(tUserStock);
    }




    /**
     * 功能描述: 从新浪获取指定股票代码行情
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 14:59
     * @param data
     * @return
     */
    public String[] getMarketForSina(String data) {
        String url = "http://hq.sinajs.cn/?list=" + data;
        String result = HttpRequestUtil.stringHttpRequest(url);
        if (EmptyUtils.isEmpty(result)) {
            return null;
        }
        String[] strs = result.split("\"");
        String[] dataArray = strs[1].split(",");

        return dataArray;
    }



    /**
     * 功能描述:获取用户自选股列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 14:18
     * @param userStock
     * @return
     */
    public List<TUserStock> getList(TUserStock userStock, TUser curUser){
        return userStockDao.getList(userStock,curUser);
    }


    /**
     * 功能描述: 不分页，获取股票基本信息列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 16:36
     * @param stockInfo 股票基本信息对象
     * @return
     */
    public List<TStockInfo> getList2(TStockInfo stockInfo){
        return userStockDao.getList2(stockInfo);
    }



    /**
     * 功能描述:加入自选股
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 16:53
     * @param userStock
     * @return 返回自选股的对象，是关联其他表的完整对象信息
     */
    public TUserStock createUserStock(TUserStock userStock,TUser curUser){

        //判断主要属性是否为空
        if(EmptyUtils.isEmpty(curUser.getUserId())){
            throw new RuanYunException("获取用户信息失败");
        }
        if(EmptyUtils.isEmpty(userStock.getSymbol())){
            throw new RuanYunException("股票编号不能为空");
        }

        //判断是否重复添加
        TUserStock oldUserStock=get(TUserStock.class,new String[]{"userId","symbol"},new Object[]{curUser.getUserId(),userStock.getSymbol()});
        if(EmptyUtils.isNotEmpty(oldUserStock)){
            throw new RuanYunException("该股票已在自选股中");
        }

        userStock.setUserId(curUser.getUserId());

        save(userStock);


        return userStockDao.getUserStock(userStock,curUser);
    }


    /**
     * 功能描述: 删除自选股
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 18:31
     * @param userStock
     * @return
     */
    public Integer deleteUserStock(TUserStock userStock,TUser curUser){

        //判断主要属性是否为空
        if(EmptyUtils.isEmpty(curUser.getUserId())){
            throw new RuanYunException("获取用户信息失败");
        }
        if(EmptyUtils.isEmpty(userStock.getSymbol())){
            throw new RuanYunException("股票编号不能为空");
        }

        //获取原始数据
        TUserStock oldUserStock=get(TUserStock.class,new String[]{"userId","symbol"},new Object[]{curUser.getUserId(),userStock.getSymbol()});

        delete(oldUserStock);

        return 1;
    }




    /**
     * 功能描述: 根据编码找到对应的股票名称
     * 创建者: zhangwei
     * 创建时间: 2018/01/17 11:52
     * @param code
     * @return
     */
    public TStockInfo getStockInfo(String  code){

        TStockInfo stockInfo= get(TStockInfo.class,"code",code);

        if(EmptyUtils.isEmpty(stockInfo)){
            throw  new RuanYunException("股票编号有误");
        }

        return stockInfo;
    }
}
