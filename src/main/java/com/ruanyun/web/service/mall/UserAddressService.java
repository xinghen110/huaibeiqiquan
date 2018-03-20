package com.ruanyun.web.service.mall;

import java.util.Date;
import com.ruanyun.web.model.sys.TAreas;
import com.ruanyun.web.service.sys.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.UserAddressDao;
import com.ruanyun.web.model.mall.TUserAddress;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.NumUtils;

//收货地址
@Service("userAddressService")
public class UserAddressService extends BaseServiceImpl<TUserAddress>{

	@Autowired
	private UserAddressDao userAddressDao;
	@Autowired
	private AreasService areasService;

	/**
	 * 功能描述:查询
	 * @author wsp  2016-9-29 上午09:32:51
	 * @param page
	 * @param userAddress
	 * @param user
	 * @return
	 */
	public Page<TUserAddress> getList(Page<TUserAddress> page,TUserAddress userAddress,TUser user){
		return userAddressDao.queryPage(page,userAddress,user);
	}
	/**
	 * 功能描述:根据主键查询
	 * @author cqm 2016-10-20 下午02:54:53
	 * @param userNum
	 * @param address
	 * @param isRequeried
	 * @return
	 */
	public TUserAddress getAddressInfo(String userAddressNum,boolean isRequeried){
		TUserAddress userAddress = this.get(TUserAddress.class, "userAddressNum" ,userAddressNum);
		if(isRequeried && userAddress==null)
			throw new RuanYunException("地址不存在");
		return userAddress;
	}

	/**
	 * 功能描述:删除地址
	 * @author cqm 2016-10-20 下午02:51:34
	 * @param address
	 * @param userNum
	 */
	public void delAddressInfo(String userAddressNum){
		TUserAddress userAddress=getAddressInfo(userAddressNum, true);
		super.delete(userAddress);
	}
	/**
	 * 功能描述:新增地址
	 * @author cqm  2016-10-20 下午05:24:21
	 * @param userAddress
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer save(TUserAddress userAddress,TUser user)throws Exception{
		userAddress.setCreateTime(new Date());
		userAddress.setUserNum(user.getUserNum());

		if(userAddress.getIsDeafult()==1){
			userAddressDao.updateAddressNoDefault(user.getUserNum());
		}
		save(userAddress);
		userAddress.setUserAddressNum(NumUtils.getCommondNum(NumUtils.PIX_USER_ADDRESS, userAddress.getUserAddressId()));
		return 1;
	}
	/**
	 * 功能描述:用户默认获取用户地址
	 * @author cqm  2016-10-31 下午06:21:13
	 * @param isDeafule
	 * @return
	 */
	public TUserAddress getIsDeafule(String userNum){
		TUserAddress userAddress = userAddressDao.getIsDeafult(userNum);
		return userAddress;

	}



	/**
	 * 功能描述：修改地址
	 * @param userAddress
	 * @param userNum
	 * @param areaid
	 * @return
	 * @throws Exception
	 */
	public Integer update(TUserAddress userAddress,String userNum,String areaid)throws Exception{
		TUserAddress userAddressOldAddress=getAddressInfo(userAddress.getUserAddressNum(),true);
		if(EmptyUtils.isNotEmpty(userAddress.getAddress())){
			userAddressOldAddress.setAddress(userAddress.getAddress());
		}
		if(EmptyUtils.isNotEmpty(userAddress.getLinkMan())){
			userAddressOldAddress.setLinkMan(userAddress.getLinkMan());
		}
		if(EmptyUtils.isNotEmpty(userAddress.getLinkTel())){
			userAddressOldAddress.setLinkTel(userAddress.getLinkTel());
		}
		if(EmptyUtils.isNotEmpty(userAddress.getLongitude()) && EmptyUtils.isNotEmpty(userAddress.getLatitude())){
			userAddressOldAddress.setLongitude(userAddress.getLongitude());
			userAddressOldAddress.setLatitude(userAddress.getLatitude());
		}
		if(EmptyUtils.isNotEmpty(areaid)){
			TAreas areas = areasService.get(TAreas.class,"areaid", areaid);
			userAddressOldAddress.setAreas(areaid);
			userAddressOldAddress.setCity(areas.getCityCode());
			userAddressOldAddress.setProvince(areaid.substring(0,2)+"0000");
		}

		if(EmptyUtils.isNotEmpty(userAddress.getIsDeafult())){
			if(userAddressOldAddress.getIsDeafult()==1)
				userAddressDao.updateAddressNoDefault(userNum);
			userAddressOldAddress.setIsDeafult(userAddress.getIsDeafult());
		}
		super.update(userAddressOldAddress);
		return 1;
	}

	/**
	 * 功能描述：保存地址
	 * @param userAddress
	 * @param userNum
	 * @param areaid
	 * @return
	 * @throws Exception
	 */
	public Integer save(TUserAddress userAddress,String userNum,String areaid)throws Exception{
		userAddress.setCreateTime(new Date());
		userAddress.setUserNum(userNum);

		//获取此用户有多少个地址
		int addressCount = getAllByCondition(TUserAddress.class, "userNum", userNum).size();
		//第一次增加
		if(addressCount < 1){
			//设置为默认地址
			userAddress.setIsDeafult(1);
		}else{
			userAddress.setIsDeafult(2);
		}
		if(EmptyUtils.isNotEmpty(areaid)){
			TAreas areas = areasService.get(TAreas.class,"areaid", areaid);
			userAddress.setAreas(areaid);
			userAddress.setCity(areas.getCityCode());
			userAddress.setProvince(areaid.substring(0,2)+"0000");
		}
		super.save(userAddress);
		userAddress.setUserAddressNum(NumUtils.getCommondNum(NumUtils.PIX_USER_ADDRESS, userAddress.getUserAddressId()));
		super.update(userAddress);
		return 1;
	}

}










