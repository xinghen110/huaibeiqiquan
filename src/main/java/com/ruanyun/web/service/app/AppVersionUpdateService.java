package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.mall.VersionUpdateService;

@Service
public class AppVersionUpdateService {
	
	@Autowired
	private VersionUpdateService versionUpdateService;
	
	/**
	 * 功能描述:获取版本更新信息
	 * @author cqm  2017-4-5 下午01:53:34
	 * @param type
	 * @return
	 */
	public AppCommonModel getVersionUpdate(Integer type){
		return new AppCommonModel(1,"",versionUpdateService.getVersionUpdate(type, true));
	}

}
