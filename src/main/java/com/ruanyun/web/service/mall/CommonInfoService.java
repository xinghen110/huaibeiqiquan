package com.ruanyun.web.service.mall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.daowei.MealInfoDao;
import com.ruanyun.web.dao.daowei.TypeInfoDao;
import com.ruanyun.web.dao.mall.AdverInfoDao;
import com.ruanyun.web.dao.mall.CommonInfoDao;
import com.ruanyun.web.dao.mall.ShopInfoDao;
import com.ruanyun.web.model.daowei.TMealInfo;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.model.mall.TCommonInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TShopInfo;

@Service("commonInfoService")
public class CommonInfoService extends BaseServiceImpl<TCommonInfo>{

	@Autowired
	private AdverInfoDao adverInfoDao;
	
	@Autowired
	private TypeInfoDao typeInfoDao;
	
	@Autowired
	private MealInfoDao mealInfoDao;
	
	@Autowired
	private ShopInfoDao shopInfoDao;
	
	@Autowired
	private ShopInfoService shopInfoService;
	
	/**
	 * 功能描述:首页信息
	 * @author cqm  2017-6-28 下午03:14:27
	 * @param page
	 */
	public Object getCommon(String longitude,String latitude,Integer memberLevel){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<TAdverInfo> adverInfos = adverInfoDao.getAdverList();//广告信息
//		if(EmptyUtils.isNotEmpty(adverInfos)){
//			//查询店铺信息
//			String shopNums=CommonUtils.getAttributeValue(TAdverInfo.class, adverInfos, "shopNum");
//			if(EmptyUtils.isNotEmpty(shopNums)){
//				Map<String,TShopInfo> shopInfoMap=shopInfoService.getShopInfoByShopNum(shopNums);
//				CommonUtils.setAttributeValue(TAdverInfo.class,  adverInfos, shopInfoMap, "shopNum", "shopInfo");
//			}
//		
//		}
		map.put("adverInfos", adverInfos);
		
		List<TTypeInfo> typeInfos = typeInfoDao.getTypeList();//类型
		map.put("typeInfos", typeInfos);
		
		List<TMealInfo> mealInfos = mealInfoDao.getMealInfos(memberLevel);//推荐套餐
		map.put("mealInfos", mealInfos);
		
		List<TShopInfo> technicians = shopInfoDao.getShopInfos(2, longitude, latitude,memberLevel);//查询推荐技师信息
		map.put("technicians", technicians);
		
		List<TShopInfo> shopInfos = shopInfoDao.getShopInfos(1, longitude, latitude,memberLevel);//查询推荐商家信息
		map.put("shopInfos", shopInfos);
		
		return map;
	}
	


}
