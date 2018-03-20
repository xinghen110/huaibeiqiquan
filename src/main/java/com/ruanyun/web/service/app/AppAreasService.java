package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.service.sys.AreasService;

@Service
public class AppAreasService {
	
	@Autowired
	private AreasService areasService;
	
	/**
	 * 功能描述:查询区域列表
	 * @author cqm  2016-11-8 上午10:51:14
	 * @param page
	 * @param areas
	 * @return
	 */
	public AppCommonModel getAreasList(TAreas areas){
		return new AppCommonModel(1,"",areasService.getAreasList(areas));
	}

}
