package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TBankBind;
import com.ruanyun.web.service.app.AppBankBindService;

@Controller
@RequestMapping("/app/bankBind")
public class AppBankBindController extends BaseController{
	
	@Autowired
	private AppBankBindService appBankBindService;
	
	/**
	 * 功能描述:绑定银行卡
	 * @author cqm  2016-12-3 下午04:59:29
	 * @param response
	 * @param bankBind
	 */
	@RequestMapping("add")
	public void addBankBind(HttpServletResponse response,TBankBind bankBind){
		AppCommonModel model=null;
		try {
			model=appBankBindService.saveBankBind(bankBind);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/bankBind/add:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:修改绑定银行卡
	 * @author cqm  2016-12-3 下午05:00:51
	 * @param response
	 * @param bankBind
	 */
	@RequestMapping("update")
	public void updateBankBind(HttpServletResponse response,TBankBind bankBind){
		AppCommonModel model=null;
		try {
			model=appBankBindService.updateBankBind(bankBind);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/bankBind/update:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:获取银行卡
	 * @author cqm  2016-12-7 下午02:32:37
	 * @param response
	 * @param bankBind
	 */
	@RequestMapping("get")
	public void getBankBind(HttpServletResponse response,TBankBind bankBind){
		AppCommonModel model=null;
		try {
			model=appBankBindService.getBankBind(bankBind);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/bankBind/get:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}

}
