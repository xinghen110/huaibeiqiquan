package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TAdverInfo;
import com.ruanyun.web.service.mall.AdverInfoService;

@Service
public class AppAdverInfoService {
	@Autowired
	private AdverInfoService adverInfoService;
	
	/**
	 * 功能描述: 获取光该
	 *
	 * @author yangliu  2016年10月12日 下午6:25:22
	 * 
	 * @param page
	 * @return
	 */
	public AppCommonModel queryPage(Page<TAdverInfo> page,TAdverInfo adverInfo){
		adverInfo.setStatus(1);
		adverInfo.setAdverType(1);
		return new AppCommonModel(1,"获取广告位列表成功",adverInfoService.getList(page, adverInfo));
		
	}

}
