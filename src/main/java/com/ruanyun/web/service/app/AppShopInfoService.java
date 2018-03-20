package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.service.mall.ShopInfoService;

@Service
public class AppShopInfoService {
	
	@Autowired
	private ShopInfoService shopInfoService;
	
	/**
	 * 功能描述:搜索技师信息
	 * @author cqm  2017-8-8 下午02:59:09
	 * @param page
	 * @param shopInfo
	 * @param type
	 * @param age
	 * @return
	 */
	public AppCommonModel getShopList(Page<TShopInfo> page,TShopInfo shopInfo,Integer type,String age,Integer memberLevel){
		return new AppCommonModel(1,"查询成功",shopInfoService.getShopList(page, shopInfo, type, age,memberLevel));
	}

}
