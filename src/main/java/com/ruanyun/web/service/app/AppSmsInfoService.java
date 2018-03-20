package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TSmsInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.SmsInfoService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.Constants;


@Service("appsmsinfoservice")
public class AppSmsInfoService {
	@Autowired
	private SmsInfoService smsInfoService;
	@Autowired
	private UserService userService;
	
	/**
	 * 功能描述:查询消息列表
	 * @author cqm  2016-12-2 下午04:44:26
	 * @param page
	 * @param smsInfo
	 * @return
	 */
	public AppCommonModel getSmsInfoList(Page<TSmsInfo> page,TSmsInfo smsInfo){
			if(EmptyUtils.isNotEmpty(smsInfo.getUserNum())){
				TUser user = userService.getUserByUserNum(smsInfo.getUserNum(), false);
				smsInfo.setUserType(user.getUserType());
			}
			if(EmptyUtils.isNotEmpty(smsInfo.getShopNum())){
				smsInfo.setUserType(Constants.USER_TYPE_2);
			}
		return new AppCommonModel(1,"",smsInfoService.getListApp(page, smsInfo, null));
	}
	



}
