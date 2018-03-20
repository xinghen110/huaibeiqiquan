package com.ruanyun.web.service.app.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.cache.impl.PublicCache;
import com.ruanyun.common.cache.impl.YttgCache;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.sys.TDictionary;

@Service
public class AppStaticDataService {
	@Autowired
	private PublicCache publicCache;
	@Autowired
	private YttgCache yttgCache;
	
	/**
	 * 功能描述:加载字典表
	 *
	 * @author yangliu  2016年8月2日 上午10:33:08
	 * 
	 * @param parentCode
	 * @return
	 */
	public AppCommonModel getDictionaryList(){
		return new AppCommonModel(1,"获取字典表成功",publicCache.getListAll());
	}
	
	/**
	 * 功能描述:加载类型表
	 * @author cqm  2017-8-11 上午09:28:13
	 * @return
	 */
	public AppCommonModel getTypeList(){
		return new AppCommonModel(1,"获类型表成功",publicCache.getListAllType());
	}
	
	/**
	 * 功能描述: 获取单个信息
	 *
	 * @author yangliu  2016年8月2日 上午10:49:35
	 * 
	 * @param parentCode
	 * @return
	 */
	public AppCommonModel getDictionaryList(String parentCode){
		List<TDictionary> dictionaries = PublicCache.getItemList(parentCode);
		Map<String, String> map = new HashMap<String, String>();
		for (TDictionary bean : dictionaries) {
			map.put(bean.getFlag1(), bean.getItemCode());
		}
		return new AppCommonModel(1,"",map);
	}
	

}
