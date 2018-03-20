package com.ruanyun.web.service.app.sys;

import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.TStockInfo;
import com.ruanyun.web.model.TUserStock;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.web.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * @description：
 * @create 2018-02-06 13:52
 **/
@Service
public class AppUserStockService {



    @Autowired
    private UserStockService userStockService;



    /**
     * 功能描述:从新浪获取行情
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 15:07
     * @param symbol
     * @return
     */
    public AppCommonModel getMarket(String symbol) {
        return new AppCommonModel(1, "获取行情成功", userStockService.getMarketForSina(symbol));
    }


    /**
     * 功能描述: 获取自选股列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 14:33
     * @param userStock 自选股对象
     * @return
     */
    public AppCommonModel getList(TUserStock userStock, TUser curUser) {
        return new AppCommonModel(1, "获取自选股列表成功", userStockService.getList(userStock,curUser));
    }



    /**
     * 功能描述:获取股票基本信息列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 16:37
     * @param stockInfo 股票基本信息对象
     * @return
     */
    public AppCommonModel getList2(TStockInfo stockInfo) {
        return new AppCommonModel(1, "获取股票基本信息列表成功", userStockService.getList2(stockInfo));
    }


    /**
     * 功能描述: 加入自选股
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 17:03
     * @param userStock
     * @return
     */
    public AppCommonModel createUserStock(TUserStock userStock,TUser curUser){
        return new AppCommonModel(1,"加入自选股成功",userStockService.createUserStock(userStock,curUser));
    }



    /**
     * 功能描述: 删除自选股
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 18:33
     * @param userStock
     * @return
     */
    public AppCommonModel deleteUserStock(TUserStock userStock,TUser curUser){
        return new AppCommonModel(1,"删除自选股成功",userStockService.deleteUserStock(userStock,curUser));
    }


    /**
     * 功能描述: 根据股票代码，获取指定的股票名称
     * 创建者: zhangwei
     * 创建时间: 2018/01/17 13:49
     * @param code
     * @return
     */
    public AppCommonModel getStockInfo(String code){
        return new AppCommonModel(1,"获取成功",userStockService.getStockInfo(code));
    }





}
