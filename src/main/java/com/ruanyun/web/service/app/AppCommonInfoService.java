package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.mall.CommonInfoService;

@Service
public class AppCommonInfoService {
	
	@Autowired
	private CommonInfoService commonInfoService;
	
	/**
	 * 功能描述:获取首页信息
	 * @author cqm  2017-6-28 下午04:04:34
	 * @param page
	 * @param infoYiyuangou
	 * @param type
	 * @return
	 */
	public AppCommonModel getCommon(String longitude,String latitude,Integer memberLevel){
		return new AppCommonModel(1,"获取首页列表成功",commonInfoService.getCommon(longitude, latitude,memberLevel));
	}


}
