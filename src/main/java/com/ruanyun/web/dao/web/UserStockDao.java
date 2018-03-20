package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TStockInfo;
import com.ruanyun.web.model.TUserStock;
import com.ruanyun.web.model.sys.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStockDao extends BaseDaoImpl<TUserStock> {


    /**
     * 功能描述:不分页，获取用户自选股列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 14:15
     * @param userStock
     * @return
     */
    public List<TUserStock> getList(TUserStock userStock, TUser curUser){
        StringBuffer sql=new StringBuffer("select tus.* ,tsi.name as stockName,tsi.hv30 from t_user_stock tus " +
                "left join t_stock_info tsi on tsi.code=tus.symbol where 1=1");

        if(EmptyUtils.isNotEmpty(userStock)){
            sql.append(SQLUtils.popuHqlEq("tus.user_id",curUser.getUserId()));

            sql.append(SQLUtils.popuHqlLike("tus.symbol",userStock.getSymbol()));


        }

        sql.append(" order by symbol asc limit 40");

        return sqlDao.getAll(TUserStock.class,sql.toString());
    }


    /**
     * 功能描述: 不分页，获取股票基本信息列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/18 16:35
     * @param stockInfo 股票基本信息对象
     * @return
     */
    public List<TStockInfo> getList2(TStockInfo stockInfo){
        StringBuffer sql=new StringBuffer("select * from t_stock_info where 1=1");
        if(EmptyUtils.isNotEmpty(stockInfo)){

            sql.append(" and code like '"+stockInfo.getCode()+"%'");
            sql.append(SQLUtils.popuHqlLike("name",stockInfo.getName()));
        }

        sql.append(" order by code asc ");

        return sqlDao.getAll(TStockInfo.class,sql.toString());
    }


    /**
     * 功能描述:不分页，获取用户自选股列表
     * 创建者: zhangwei
     * 创建时间: 2018/01/20 22:59
     * @param userStock
     * @return
     */
    public TUserStock getUserStock(TUserStock userStock,TUser curUser){
        StringBuffer sql=new StringBuffer("select tus.* ,tsi.name as stockName,tsi.hv30 from t_user_stock tus " +
                "left join t_stock_info tsi on tsi.code=tus.symbol where 1=1");

        if(EmptyUtils.isNotEmpty(userStock)){
            sql.append(SQLUtils.popuHqlEq("tus.user_id",curUser.getUserId()));

            sql.append(SQLUtils.popuHqlLike("tus.symbol",userStock.getSymbol()));


        }

        return sqlDao.get(TUserStock.class,sql.toString());
    }

}
