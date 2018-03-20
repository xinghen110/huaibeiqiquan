package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TRechargeMeal;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.RechargeMealService;

@Service
public class AppRechargeMealService {
	@Autowired
	private RechargeMealService rechargeMealService;
	
	/**
	 * 功能描述:优惠套餐查询
	 * @author cqm  2016-11-5 下午05:36:43
	 * @param page
	 * @param rechargeMeal
	 * @param user
	 * @return
	 */
	public AppCommonModel getRechargeInfo(TRechargeMeal rechargeMeal){
		return new AppCommonModel(1,"获取充值套餐列表成功",rechargeMealService.getRechargeMealList(rechargeMeal));
	}

}
