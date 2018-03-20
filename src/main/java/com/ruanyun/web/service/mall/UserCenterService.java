package com.ruanyun.web.service.mall;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.UserCenterDao;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TOrderTrain;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;
 
@Service
public class UserCenterService extends BaseServiceImpl<TUserCenter>{
	
	@Autowired
	private UserCenterDao userCenterDao;
	
	/**
	 * 功能描述:添加账户
	 * @author cqm  2017-8-16 下午05:50:28
	 * @param userNum
	 */
	public void saveUserCenter(String userNum){
		TUserCenter userCenter= new TUserCenter();
		userCenter.setUserNum(userNum);
		userCenter.setScoreBalance(0.0);
		userCenter.setAccountBalance(new BigDecimal(0));
		super.save(userCenter);
		userCenter.setUserCenterNum(NumUtils.getCommondNum(NumUtils.PIX_USER_CENTER, userCenter.getUserCenterId()));
	}
	
 
	
	@Autowired
	private UserService userService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderTrainService orderTrainService;
	/**
	 * 功能描述:查询积分余额或账户余额
	 * @author cqm  2016-11-3 上午11:49:53
	 * @param userNum
	 * @return
	 */
	public Integer getUserCenter(String userNum){
		return userCenterDao.getUserCenter(userNum);
	}
	/**
	 * 功能描述:查询当前用户积分账户信息
	 * @author cqm  2016-11-3 下午02:15:19
	 * @param userNum
	 * @param isRequeired
	 * @return
	 */
	public TUserCenter getUserCenter(String userNum,boolean isRequeired){
         System.out.println("====userNum=="+userNum);
		TUserCenter userCenter=super.get(TUserCenter.class,"userNum",userNum);
		if(userCenter==null&& isRequeired)
			throw new RuanYunException("账号中心不存在");
		TUser user = userService.getUserByUserNum(userNum, true);
		if(EmptyUtils.isNotEmpty(user)){
			userCenter.setBindStatus(user.getBindStatus());
		}
		return userCenter;
	}
	
	/**
	 * 功能描述:更新账户余额
	 *
	 * @author chenqian  2016-11-8 上午10:18:35
	 * 
	 * @param userCenter 用户账户实体类 传入userNum、accountBalance 即可
	 * @param payType 操作类型 (1账号支付 2、支付宝支付 ，3微信支付4、积分账户)
	 * @param commonNum
	 * @return
	 */
	public AppCommonModel updateAccountBalance(TUserCenter userCenter,Integer payMethod,String orderNum,Integer orderType,Integer userType,BigDecimal orderPrice){
		TUserCenter uc = this.getUserCenter(userCenter.getUserNum(), true);
		TUser user = userService.getUserByUserNum(uc.getUserNum(), true);
		
		if(orderType==1 || orderType==2){//订单
			if(userCenter.getAccountBalance().compareTo(orderPrice)!=0)//判断传入金额与订单金额是否一致
				throw new RuanYunException("实付金额与订单价格不符！！");
		}
		
		if (EmptyUtils.isNotEmpty(user.getPayPassword()) && EmptyUtils.isNotEmpty(userCenter.getPayPassword())) {
			if (!user.getPayPassword().equals(userCenter.getPayPassword())) {
				throw new RuanYunException("支付密码错误");
			}
		}
		if (uc.getAccountBalance().add(userCenter.getAccountBalance()).compareTo(BigDecimal.ZERO) == -1) {
			throw new RuanYunException("账户余额不足");
		}
		return new AppCommonModel(1,"账户操作成功");
	}
	
 
	
	
	/**
	 * 更新账户余额
	 * @param userNum   用户编号
	 * @param consumPrice   消费价格
	 * @param consumType  消费类型
	 * @param payType  支付方式
	 * @param orderNum  订单编号
	 * @param userType  用户类型
	 * @param title  流水标题
	 * @return
	 */
	public TUserCenter updateAccountBalance(String userNum,BigDecimal consumPrice,Integer consumType,Integer payType,String orderNum,Integer userType,String title,Integer payMethod){
		return userCenterDao.updateAccountBalance(userNum,consumPrice, consumType, payType, orderNum, userType, title, payMethod);
	}
	
	
	/**
	 * 更新积分余额
	 * @param userNum
	 * @param consumPrice
	 * @param consumType
	 * @param payType
	 * @param orderNum
	 * @param userType
	 * @param title
	 * @return
	 */
	public TUserCenter updateScoreBalance(String userNum,Double consumPrice,Integer consumType,Integer payType,String orderNum,Integer userType,String title){
		return userCenterDao.updateScoreBalance(userNum, consumPrice, consumType, payType, orderNum, userType, title);
		
	}
}










