package com.ruanyun.web.service.mall;
import java.math.BigDecimal;
import java.util.*;

import com.ruanyun.web.model.TUserAccountFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.UserRecordDao;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;

@Service("userRecordService")
public class UserRecordService extends BaseServiceImpl<TUserRecord>{
/**
 * 会员流水
 */
	@Autowired
	private UserRecordDao userRecordDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopInfoService shopInfoService;
	
	public Page<TUserRecord> queryPage(Page<TUserRecord> page,TUserRecord userRecord,TUser user) {
		Page<TUserRecord> _page = userRecordDao.queryPage(page, userRecord, user);
		
		String userNums=CommonUtils.getAttributeValue(TUserRecord.class, _page.getResult(), "userNum");
		Map<String,TUser> userMap=userService.getUserByUserNums(userNums);
		CommonUtils.setAttributeValue(TUserRecord.class,  _page.getResult(), userMap, "userNum", "user");
		
		return _page;
		
	}
	
	/**
	 * 功能描述:app查询流水信息
	 * @author cqm  2016-11-9 下午02:57:59
	 * @param page
	 * @param userRecord
	 * @return
	 */
	public Page<TUserRecord> getUserRecordList(Page<TUserRecord> page,TUserRecord userRecord) {
		System.out.println("===userRecord.getUserNum()==="+userRecord.getUserNum());
		TShopInfo shopInfo = shopInfoService.get(TShopInfo.class, "userNum", userRecord.getUserNum());
		if(EmptyUtils.isNotEmpty(shopInfo)){
			userRecord.setConsumTypeString("1,4");
		}
    	Page<TUserRecord> _page = userRecordDao.getUserRecord(page, userRecord);
		return _page;
	}

	/**
	 * 功能描述：对数据进行排序并设置用户信息
	 * @param userRecords
	 * @return
	 */
	public List<TUserRecord> setUser(List<TUserRecord> userRecords) {
		if (EmptyUtils.isNotEmpty(userRecords)) {
			//排序
			Collections.sort(userRecords, new Comparator<TUserRecord>() {
				@Override
				public int compare(TUserRecord o1, TUserRecord o2) {
					return -o1.getConsumPrice().compareTo(o2.getConsumPrice());
				}
			});

			//设置用户信息
			String userNums=CommonUtils.getAttributeValue(TUserRecord.class, userRecords, "userNum");
			Map<String,TUser> userMap=userService.getUserByUserNums(userNums);
			CommonUtils.setAttributeValue(TUserRecord.class,  userRecords, userMap, "userNum", "user");
		}
		return userRecords;
	}
	
	/**
	 * 功能描述:增加流水
	 * @author cqm  2016-12-7 上午10:01:48
	 * @param userRecord
	 * @return
	 */
	public Integer saveUserRecordInteger(TUserRecord userRecord){
		if(EmptyUtils.isNotEmpty(userRecord)){
			userRecord.setCreateTime(new Date());
			save(userRecord);
			userRecord.setUserRecordNum(NumUtils.getCommondNum(NumUtils.PIX_ACCOUNTRECORD, userRecord.getUserRecordId()));
			update(userRecord);
		}
		
		return 1;
		
	}
	
	/**
	 * 功能描述:添加流水
	 * @author cqm  2017-5-19 上午11:18:01
	 * @param userNum
	 * @param consumPrice
	 * @param accountBalance
	 * @param orderNum
	 * @param consumType
	 * @param title
	 * @param payType
	 * @param userType
	 */
	public void addUserRecord(String userNum,BigDecimal consumPrice,BigDecimal accountBalance, String orderNum,Integer consumType,String title,Integer payType,Integer userType){
		TUserRecord userRecord=new TUserRecord();//创建流水账对象
		userRecord.setUserNum(userNum);//用户账号
		userRecord.setConsumPrice(consumPrice);
		userRecord.setAccountBancle(accountBalance);
		userRecord.setOrderNum(orderNum);
		userRecord.setCreateTime(new Date());//创建时间
		userRecord.setConsumType(consumType);  //消费类型
		userRecord.setTitle(title);
		userRecord.setPayType(payType);//支付类型
		userRecord.setUserType(userType);
		save(userRecord);//保存到用户流水表
		userRecord.setUserRecordNum(NumUtils.getCommondNum(NumUtils.PIX_ACCOUNTRECORD, userRecord.getUserRecordId()));
	}



	public List<TUserAccountFlow> getList(TUserAccountFlow userAccountFlow, TUser curUser) {
		return userRecordDao.getList(userAccountFlow,curUser);
	}

}










