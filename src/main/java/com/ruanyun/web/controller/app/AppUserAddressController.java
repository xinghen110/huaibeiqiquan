package com.ruanyun.web.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.app.AppUserAddressService;

@Controller
@RequestMapping("/app/{userNum}/address")
public class AppUserAddressController extends BaseController{
	
	@Autowired
	private AppUserAddressService appUserAddressService ;
	/**
	 * 功能描述:查询
	 * @author cqm 2016-10-20 下午02:46:49
	 * @param response
	 * @param userNum
	 * @param page
	 * @param userAddress
	 * @param user
	 */
	@RequestMapping("list")
	public void getShopList(HttpServletResponse response,@PathVariable String userNum,Page<TUserAddress> page,TUserAddress userAddress,TUser user){
		AppCommonModel model=null;
		try {
			model=appUserAddressService.getUserAddressPage(page, userAddress,user);
		} catch (Exception e) {
			logger.error("/app/{userNum}/address/list:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:获取默认地址
	 * @author cqm  2016-10-31 下午06:49:43
	 * @param response
	 * @param userNum
	 */
	@RequestMapping("get/isDeafult")
	public void getIsDeafult(HttpServletResponse response,@PathVariable String userNum){
		AppCommonModel model=null;
		try {
			model=appUserAddressService.getIsDeafult(userNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/{userNum}/address/get/isDeafult:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}


}


