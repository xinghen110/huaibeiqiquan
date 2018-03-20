package com.ruanyun.web.dao.mall;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.service.mall.UserRecordService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;

/**
 * 
 *  #(c) IFlytek czy <br/>
 *
 *  版本说明: $id:$ <br/>
 *
 *  功能说明: 用户账户、积分类
 * 
 *  <br/>创建说明: 2016-10-9 上午09:59:25 yanzy  创建文件<br/>
 * 
 *  修改历史:<br/>
 *
 */
@Repository("userCenterDao")
public class UserCenterDao extends BaseDaoImpl<TUserCenter>{
	
	@Autowired
	UserRecordService  userRecordService;
	/**
	 * 功能描述:根据当前用户查询积分余额或账户余额
	 * @author cqm  2016-11-3 上午11:44:24
	 * @param userNum
	 * @return
	 */
	public Integer getUserCenter(String userNum){
		String sqlString = "select * from t_user_center where user_num='"+userNum+"'";
		return sqlDao.get(sqlString);
		
	}
	
	/**
	 * 功能描述:更新用户账户余额
	 *
	 * @author yanzy  2016-10-9 上午09:59:47
	 * 
	 * @param userCenter
	 * @return
	 */
//	public TUserCenter updateAccountBalance(TUserCenter userCenter){
//		TUserCenter center = super.get(TUserCenter.class, "userNum", userCenter.getUserNum());
//		center.setAccountBalance(center.getAccountBalance().add(userCenter.getAccountBalance()));
//		super.save(center);
//		return center;
//	}
	
//	/**
//	 * 功能描述:更新积分
//	 *
//	 * @author yanzy  2016-10-9 上午10:00:46
//	 * 
//	 * @param userCenter
//	 * @return
//	 */
//	public TUserCenter updateScoreBalance(TUserCenter userCenter){
//		TUserCenter center = super.get(TUserCenter.class, "userNum", userCenter.getUserNum());
//		center.setScoreBalance(center.getScoreBalance());
//		super.save(center);
//		return center;
//	}
	
	
	
	/**
	 * 功能描述:更新用户积分余额
	 *
	 * @author chenqian  2016-10-9 上午09:59:47
	 * 
	 * @param userCenter
	 * @return
	 */
	public TUserCenter updateScoreBalance(String userNum,Double consumPrice,Integer consumType,Integer payType,String orderNum,Integer userType,String title){
		//更新账户余额
		TUserCenter center = super.get(TUserCenter.class, "userNum", userNum);
		center.setScoreBalance(center.getScoreBalance()+consumPrice);
		super.save(center);
		//生成流水记录
		String[] orderNums = orderNum.split(",");
		for (int i = 0; i < orderNums.length; i++) {
			if(EmptyUtils.isNotEmpty(center)){
				TUserRecord userRecord=new TUserRecord();//创建流水账对象
				userRecord.setUserNum(userNum);//用户账号
				userRecord.setConsumPrice(new BigDecimal(consumPrice));
				userRecord.setAccountBancle(new BigDecimal(center.getScoreBalance()));
				userRecord.setOrderNum(orderNums[i]);
				userRecord.setCreateTime(new Date());//创建时间
				userRecord.setConsumType(consumType);  //消费类型
				userRecord.setTitle(title);
				userRecord.setPayType(payType);//支付类型
				userRecord.setUserType(userType);
				userRecordService.save(userRecord);//保存到用户流水表
				userRecord.setUserRecordNum(NumUtils.getCommondNum(NumUtils.PIX_ACCOUNTRECORD, userRecord.getUserRecordId()));
			}
		}
		
		return center;
	}
	
	/**
	 * 功能描述:更新用户账户余额
	 *
	 * @author chenqian  2016-10-9 上午09:59:47
	 * 
	 * @param userCenter
	 * @return
	 */
	@Transactional
	public TUserCenter updateAccountBalance(String userNum,BigDecimal consumPrice,Integer consumType,Integer payType,String orderNum,Integer userType,String title,Integer payMethod){
		TUserCenter center = super.get(TUserCenter.class, "userNum", userNum);
		System.out.println("==consumPrice="+consumPrice+"==="+payMethod+"==="+center.getAccountBalance());
		if(payMethod==Constants.PAY_TYPE_1 || payMethod==0 ){
			System.out.println("==consumPrice="+consumPrice+"==="+payMethod);
			//更新账户余额
			center.setAccountBalance(center.getAccountBalance().add(consumPrice));
			save(center);
		}
		System.out.println("==="+center.getAccountBalance());
		//生成流水记录
		String[] orderNums = orderNum.split(",");
		for (int i = 0; i < orderNums.length; i++) {
			if(EmptyUtils.isNotEmpty(center)){
				TUserRecord userRecord=new TUserRecord();//创建流水账对象
				userRecord.setUserNum(userNum);//用户账号
				userRecord.setConsumPrice(consumPrice);
				userRecord.setAccountBancle(center.getAccountBalance());
				userRecord.setOrderNum(orderNums[i]);
				userRecord.setCreateTime(new Date());//创建时间
				userRecord.setConsumType(consumType);  //消费类型
				userRecord.setTitle(title);
				userRecord.setPayType(payType);//支付类型
				userRecord.setUserType(userType);
				userRecordService.save(userRecord);//保存到用户流水表
				userRecord.setUserRecordNum(NumUtils.getCommondNum(NumUtils.PIX_ACCOUNTRECORD, userRecord.getUserRecordId()));
			}
		}
		
		return center;
	}
}

