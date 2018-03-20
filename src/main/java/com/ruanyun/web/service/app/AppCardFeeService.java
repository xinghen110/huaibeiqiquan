package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.service.daowei.CardFeeService;

@Service
public class AppCardFeeService {
	
	@Autowired
	private CardFeeService cardFeeService;
	
	/**
	 * 功能描述:获取会员列表信息
	 * @author cqm  2017-8-18 下午05:09:16
	 * @param bankBind
	 * @return
	 */
	public AppCommonModel getCardFees(String userNum){
		return new AppCommonModel(1,"获取成功",cardFeeService.getCardFees(userNum));
	}

}
