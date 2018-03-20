package com.ruanyun.web.dao.mall;

import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TShopVedio;

@Repository("shopVedioDao")
public class ShopVideoDao extends BaseDaoImpl<TShopVedio>{
	
	/**
	 * 功能描述:视频列表
	 * @author cqm  2017-8-21 上午11:49:43
	 * @param page
	 * @param shopVedio
	 * @return
	 */
	public Page<TShopVedio> getShopVedioList(Page<TShopVedio> page,TShopVedio shopVedio){
		StringBuffer hql = new StringBuffer(" from TShopVedio where 1=1 ");
		if(EmptyUtils.isNotEmpty(shopVedio)){
			hql.append(SQLUtils.popuHqlEq("shopNum", shopVedio.getShopNum()));
		}
		hql.append(" order by createTime desc ");
		return hqlDao.queryPage(page, hql.toString());
		
	}
	

	/**
	 * 获取当前店铺下视频数量
	 */
	public int getCount(String shopNum){
		StringBuffer hql = new StringBuffer(" select COUNT(1) from t_shop_vedio where shop_num='"+shopNum+"' ");
		return sqlDao.getCount(hql.toString());
		
	}



}
