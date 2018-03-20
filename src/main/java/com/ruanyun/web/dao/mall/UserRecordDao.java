package com.ruanyun.web.dao.mall;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruanyun.web.model.TUserAccountFlow;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;
import com.ruanyun.web.util.SecurityUtils;

@Repository("userRecordDao")
public class UserRecordDao extends BaseDaoImpl<TUserRecord>{
	@Autowired
	UserCenterService  userCenterService;
	/**
	 * 功能描述:查询会员流水
	 * @author wsp  2016-11-4 下午03:15:26
	 * @param page
	 * @param userRecord
	 * @param user
	 * @return
	 */
	public Page<TUserRecord> queryPage(Page<TUserRecord> page,TUserRecord userRecord,TUser user){
		StringBuffer hql = new StringBuffer("from TUserRecord where 1=1");
		if(EmptyUtils.isNotEmpty(userRecord)){
			hql.append(SQLUtils.popuHqlEq("payType", userRecord.getPayType()));
			hql.append(SQLUtils.popuHqlEq("consumType", userRecord.getConsumType()));
			hql.append(SQLUtils.popuHqlEq("user_type", userRecord.getUserType()));
			hql.append(SQLUtils.popuHqlMinDate("createTime", userRecord.getStartTime()));
			hql.append(SQLUtils.popuHqlMaxDate("createTime", userRecord.getCreateTime()));
		}
		if(user!=null && SecurityUtils.isGranted(ConstantAuth.SHOP_AUTH, user)){   //店铺角色
			hql.append(SQLUtils.popuHqlEq("userNum", user.getUserNum()));
		}
		
		hql.append(" order by createTime DESC");
		return hqlDao.queryPage(page, hql.toString());
	}
	
	/**
	 * 功能描述:根据账户类型查询流水记录
	 * @author cqm  2016-11-9 下午02:55:20
	 * @param page
	 * @param userRecord
	 * @return
	 */
	public Page<TUserRecord> getUserRecord(Page<TUserRecord> page,TUserRecord userRecord){
		StringBuffer hql = new StringBuffer("select * from t_user_record where 1=1");
		if(EmptyUtils.isNotEmpty(userRecord)){
			hql.append(SQLUtils.popuHqlEq("consum_type", userRecord.getConsumType()));
			hql.append(SQLUtils.popuHqlIn("consum_type", userRecord.getConsumTypeString()));
			hql.append(SQLUtils.popuHqlEq("user_type", userRecord.getUserType()));
			hql.append(SQLUtils.popuHqlEq("user_num", userRecord.getUserNum()));
			
		}
		hql.append(" order by create_time DESC");
		return sqlDao.queryPage(page,TUserRecord.class,hql.toString());
	}
	
	/**
	 * 功能描述:保存账户流水
	 *
	 * @author yanzy  2016-10-8 上午10:11:16
	 * 
	 * @param userCenter 账户类
	 * @param payType 账户类型 1账户余额 2支付宝 3微信
	 * @param consumType 消费类型 1下单消费  2积分兑换 3充值 4提现 5充值送积分
	 * @param commonNum 公共num
	 * @param accountBlance 账户余额 if (accountType == 1 || accountType == 4) accountBlance传入值为null
	 */
//	public void saveUserRecord(TUserCenter userCenter,Integer consumType,Integer payType,String orderNum,BigDecimal accountBlance,Integer userType,String title){
//		String[] orderNums = orderNum.split(",");
//		for (int i = 0; i < orderNums.length; i++) {
//			if(EmptyUtils.isNotEmpty(userCenter)){
//				TUserRecord userRecord=new TUserRecord();//创建流水账对象
//				userRecord.setUserNum(userCenter.getUserNum());//用户账号
//				userRecord.setConsumPrice(userCenter.getAccountBalance());
//				if (payType == 1) {
//					userRecord.setAccountBancle(accountBlance);
//				}
//				userRecord.setOrderNum(orderNums[i]);
//				userRecord.setCreateTime(new Date());//创建时间
//				userRecord.setConsumType(consumType);  //消费类型
//				userRecord.setTitle(title);
//				userRecord.setPayType(payType);//支付类型
//				userRecord.setUserType(userType);
//				super.save(userRecord);//保存到用户流水表
//				userRecord.setUserRecordNum(NumUtils.getCommondNum(NumUtils.PIX_ACCOUNTRECORD, userRecord.getUserRecordId()));
//			}
//		}
//	}


	public List<TUserAccountFlow> getList(TUserAccountFlow userAccountFlow , TUser curUser){
		StringBuffer sql=new StringBuffer("select *  from t_user_account_flow  where 1=1" );

		if(EmptyUtils.isNotEmpty(userAccountFlow)){

			sql.append(SQLUtils.popuHqlEq("user_id",curUser.getUserId()));
		}

		sql.append(" order by create_time desc ");

		return sqlDao.getAll(TUserAccountFlow.class,sql.toString());
	}

}

