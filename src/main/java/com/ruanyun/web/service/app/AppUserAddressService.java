package com.ruanyun.web.service.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.UserAddressService;

@Service
public class AppUserAddressService {
	@Autowired
	private UserAddressService userAddressService;
	/**
	 * 功能描述:查询我的地址
	 * @author cqm  2016-10-20 下午02:15:55
	 * @param page
	 * @param userAddress
	 * @param user
	 * @return
	 */
	public AppCommonModel getUserAddressPage(Page<TUserAddress> page,TUserAddress userAddress,TUser user){
		return new AppCommonModel(1,"",userAddressService.getList(page,userAddress,user));
	}


	/**
	 * 功能描述:默认获取用户地址
	 * @author cqm  2016-10-31 下午06:44:40
	 * @param userNum
	 * @return
	 */
	public AppCommonModel getIsDeafult(String userNum){
		return new AppCommonModel(1,"",userAddressService.getIsDeafule(userNum));
	}
}


