package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserApplication;
import com.ruanyun.web.service.mall.UserApplicationService;

@Service
public class AppUserApplicationService {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	/**
	 * 功能描述:申请提现
	 * @author cqm  2017-2-23 下午03:37:50
	 * @param userApplication
	 * @return
	 */
	public AppCommonModel saveOrUpdateUserApplication(TUserApplication userApplication,String payPassword){
		return new AppCommonModel(1,"申请提现成功",userApplicationService.saveOrUpdateUserApplication(userApplication,payPassword));
	}
	

}
