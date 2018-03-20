package com.ruanyun.web.service.app;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.AccountRechargeDao;
import com.ruanyun.web.model.mall.TAccountRecharge;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TOrderTrain;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;
 
/**
 * 
 *  #(c) IFlytek czy <br/>
 *
 *  版本说明: $id:$ <br/>
 *
 *  功能说明: 账户充值类
 * 
 *  <br/>创建说明: 2016-10-21 下午04:16:57 wsp  创建文件<br/>
 * 
 *  修改历史:<br/>
 *
 */
@Service
public class AccountRechargeService extends BaseServiceImpl<TAccountRecharge>{
	
	@Autowired
	private OrderInfoService orderInfoService; //订单类
	
	@Autowired
	private UserCenterService userCenterService; //用户个人中心类
	
	@Autowired
	private AccountRechargeDao accountRechargeDao; //账户充值类
	
	@Autowired
	private UserService userService; //用户类
	
 
	
	/**
	 * 功能描述: 账户充值 page
	 * @author wsp  2016-10-21 下午07:27:15
	 * @param page
	 * @param accountRecharge
	 * @param user
	 * @return
	 */
	public Page<TAccountRecharge> getList(Page<TAccountRecharge> page,TAccountRecharge accountRecharge,TUser user){
		Page<TAccountRecharge> _page = accountRechargeDao.queryPage(page, accountRecharge, user);
		
		String userNums=CommonUtils.getAttributeValue(TAccountRecharge.class, _page.getResult(), "userNum");
		Map<String,TUser> userMap=userService.getUserByUserNums(userNums);
		CommonUtils.setAttributeValue(TAccountRecharge.class,  _page.getResult(), userMap, "userNum", "user");
		return _page;
	}
	
	
	/**
	 * 功能描述:账户充值
	 *
	 * @author yanzy  2016-10-9 下午08:55:05
	 * 
	 * @param bean 充值对象
	 * @param user 充值用户
	 * @param rechargeType 充值类型 1手机端充值 2管理后台充值
	 * @param createUserNum 如果是手机端充值 则传入手机端登录人userNum；如果是管理后台充值 则传入后台登录人userNum
	 * @return
	 */
	public TAccountRecharge saveAccountRecharge(TAccountRecharge bean,Integer rechargeType,TUser user){
		bean.setUserNum(user.getUserNum());
		bean.setCreateTime(new Date());
		save(bean);
		bean.setRechargeNum(NumUtils.getCommondNum(NumUtils.PIX_ACCOUNTRECHARGE, bean.getRechargeId()));
		TOrderInfo orderInfo =	orderInfoService.saveOrderInfo(user, bean.getAmount());
		bean.setRechargeMealNum(orderInfo.getOrderNum());  //存订单号
		update(bean);
		return bean;
	}
	
 

	/**
	 * 功能描述:保存充值金额
	 * @author wsp  2016-10-21 下午06:04:50
	 * @param accountRecharge
	 * @param user 要充值的客户
	 * @param createUser 创建人
	 * @return
	 */
	public int saveOrUpdate(TAccountRecharge accountRecharge, TUser user,TUser createUser) {
		if(EmptyUtils.isEmpty(user))
			return 0;//用户为空
		if(EmptyUtils.isEmpty(accountRecharge.getAmount()))
			return -1;//充值金额为空
		
		//saveAccountRecharge(accountRecharge, user, 2, createUser.getUserNum());
		return 1;
	}

	 

}










