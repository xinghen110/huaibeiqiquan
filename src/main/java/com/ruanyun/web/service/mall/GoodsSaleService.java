package com.ruanyun.web.service.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.mall.GoodsSaleDao;
import com.ruanyun.web.model.mall.TGoodsSale;
//商品销量
@Service("goodsSaleService")
public class GoodsSaleService extends BaseServiceImpl<TGoodsSale>{
	
	@Autowired
	private GoodsSaleDao goodsSaleDao;
	
	/**
	 * 功能描述:查询
	 * @author wsp  2016-9-7 下午05:31:09
	 * @param page
	 * @param adverInfo
	 * @param user
	 * @return
	 */
//	public Page<TGoodsSale> getList(Page<TGoodsSale> page,TGoodsSale adverInfo,TUser user){
//		return adverInfoDao.queryPage(page,adverInfo,user);
//	}
	
}










