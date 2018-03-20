package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.sys.TCity;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.CityService;

@Service
public class AppCityService {
	
	@Autowired
	private CityService cityService;
	
	/**
	 * 功能描述:查询城市列表
	 * @author cqm  2016-11-8 上午10:51:14
	 * @param page
	 * @param city
	 * @param user
	 * @return
	 */
	public AppCommonModel getCityList(TCity city){
		return new AppCommonModel(1,"",cityService.getList(city));
	}

}
