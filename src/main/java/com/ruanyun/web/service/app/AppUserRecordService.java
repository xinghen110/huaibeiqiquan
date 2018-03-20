package com.ruanyun.web.service.app;

import com.ruanyun.web.model.TUserAccountFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.UserRecordService;

@Service
public class AppUserRecordService {
	
	@Autowired
	private UserRecordService userRecordService;

	
	/**
	 * 功能描述:查询流水记录
	 * @author cqm  2016-11-5 下午01:38:52
	 * @param page
	 * @param userRecord
	 * @return
	 */
	public AppCommonModel getUserRecordList(Page<TUserRecord> page,TUserRecord userRecord){
		return new AppCommonModel(1,"",userRecordService.getUserRecordList(page,userRecord));
	}

	public AppCommonModel getList(TUserAccountFlow userAccountFlow, TUser curUser){
		return new AppCommonModel(1,"获取流水记录列表成功",userRecordService.getList(userAccountFlow,curUser));
	}

}
