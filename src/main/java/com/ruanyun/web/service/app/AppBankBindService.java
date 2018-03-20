package com.ruanyun.web.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TBankBind;
import com.ruanyun.web.service.mall.BankBindService;

@Service
public class AppBankBindService {
	
	@Autowired
	private BankBindService bankBindService;
	
	/**
	 * 功能描述:绑定银行卡
	 * @author cqm  2016-12-3 下午04:10:24
	 * @param bankBind
	 * @return
	 */
	public AppCommonModel saveBankBind(TBankBind bankBind){
		bankBindService.saveApp(bankBind);
		return new AppCommonModel(1,"绑定成功");
	}
	
	/**
	 * 功能描述:修改绑定银行卡
	 * @author cqm  2016-12-3 下午04:24:07
	 * @param bankBind
	 * @return
	 */
	public AppCommonModel updateBankBind(TBankBind bankBind){
		return new AppCommonModel(1,"修改成功",bankBindService.updateBankBind(bankBind));
	}
	
	/**
	 * 功能描述:获取当前店铺银行卡
	 * @author cqm  2016-12-7 下午02:31:47
	 * @param bankBind
	 * @return
	 */
	public AppCommonModel getBankBind(TBankBind bankBind){
		return new AppCommonModel(1,"查询成功",bankBindService.getBankBind(bankBind.getCommonNum(), true));
	}

}
