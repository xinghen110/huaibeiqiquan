package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.mall.UserCenterService;

@Service
public class AppUserCenterService {
	
	@Autowired
	private UserCenterService userCenterService;
	
	/**
	 * 功能描述:根据当前账号获取积分余额或账户余额
	 * @author cqm  2016-11-3 上午11:54:56
	 * @param userNum
	 * @return
	 */
	public AppCommonModel getUserCenter(String userNum){
		return new AppCommonModel(1,"",userCenterService.getUserCenter(userNum,true));
	}

}
