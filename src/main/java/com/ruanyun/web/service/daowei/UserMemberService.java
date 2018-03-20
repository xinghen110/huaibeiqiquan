package com.ruanyun.web.service.daowei;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.TimeUtil;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.daowei.UserMemberDao;
import com.ruanyun.web.model.daowei.TCardFee;
import com.ruanyun.web.model.daowei.TUserMember;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.OrderInfoService;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.NumUtils;
import com.ruanyun.web.util.TimerUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userMemberService")
public class UserMemberService extends BaseServiceImpl<TUserMember>{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CardFeeService cardFeeService;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private UserMemberDao userMemberDao;

	@Autowired
	private UserCenterService userCenterService;

	/**
	 * 功能描述：获取办理过会员卡的用户
	 * @param page
	 * @param userMember
	 * @return
	 */
	public Page<TUserMember> getList(Page<TUserMember> page, TUserMember userMember, String nickName) {
		Page<TUserMember> _page = userMemberDao.getList(page, userMember, nickName);

		String userNumss = CommonUtils.getAttributeValue(TUserMember.class, _page.getResult(), "userNum");
		Map<String, TUser> userMaps = userService.getUserByUserNums(userNumss);
		CommonUtils.setAttributeValue(TUserMember.class, _page.getResult(), userMaps, "userNum", "user");

		return _page;
	}

	/**
	 * 功能描述:生成会员信息
	 * @author cqm  2017-8-19 上午09:14:17
	 * @param userNum
	 * @param memberLevel
	 * @throws ParseException
	 */
	public void saveUserMember(String userNum,Integer memberLevel,String cardFeeNum) throws ParseException{
		TUserMember userMember = new TUserMember();
		userMember.setUserMember(memberLevel);
		userMember.setUserNum(userNum);
		userMember.setCardFeeNum(cardFeeNum);
		userMember.setBeginTime(new Date());
		userMember.setStatus(2);
		userMember.setEndTime(TimerUtiles.getDateDays(365));
		save(userMember);
		String userMemberNum = NumUtils.getCommondNum(NumUtils.PIX_USER_MEMBER,userMember.getUserMemberId());
		userMember.setUserMemberNum(userMemberNum);
	}
	
	
    /**
     * 功能描述:开通会员或续费会员
     * @author cqm  2017-8-18 下午04:56:00
     * @param userNum
     * @param memberLevel
     * @throws ParseException
     */
	public void addUserMember(String userNum,Integer memberLevel,String cardFeeNum) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式
		TUserMember bean = getUserMember(userNum, false);
		if(EmptyUtils.isEmpty(bean)){//开通会员
			saveUserMember(userNum, memberLevel,cardFeeNum);
			List<TUserMember> userMembers = getAllByCondition(TUserMember.class, "userNum", userNum);
			if(userMembers.size() == 1){
				//获取订单详情
				TOrderInfo orderInfo = orderInfoService.get(TOrderInfo.class, new String[]{"orderType", "orderStatus", "userNum"}, new Object[]{2, 6, userNum});
				if (!orderInfo.getActualPrice().equals(BigDecimal.ZERO)) {
					//获取分销比例
					List<TCardFee> cardFees = cardFeeService.getAllByCondition(TCardFee.class, "cardType", 2);
					//首次充值邀请用户返现
					TUser user = userService.get(TUser.class, "userNum", userNum);
					Commission(1, cardFees, user, orderInfo);
				}
			}
		}else{//续费 或升级会员
			if(memberLevel==bean.getUserMember()){//续费 如果会员等级不变 在原来截止日期再加上一年
				bean.setEndTime(TimerUtiles.getDate(dateFormat.format(bean.getEndTime()),365));
				update(bean);
			}else if(memberLevel>bean.getUserMember()){
				saveUserMember(userNum, memberLevel,cardFeeNum);//重新添加一条
				userMemberDao.updateMemberStatus(bean.getUserMemberNum());//设置为已过期
			}
		}
		TUser user = userService.getUserByUserNum(userNum, true);
		if(user.getMemberLevel()!=memberLevel){//改变会员等级
			user.setMemberLevel(memberLevel);
			user.setIsMember(1);
			userService.update(user);
		}
	}

	/**
	 * 功能描述：分销提成
	 * @param i				分销等级
	 * @param cardFees		分销List
	 * @param user			当前用户
	 * @param orderInfo		订单
	 */
	public void Commission(Integer i, List<TCardFee> cardFees, TUser user, TOrderInfo orderInfo) {
		if (!user.getParentCode().equals("000000") && EmptyUtils.isNotEmpty(user.getParentCode()) && i <= cardFees.size()) {
			TUser parentUser = userService.get(TUser.class, "userCode", user.getParentCode());
			BigDecimal proportion = new BigDecimal(0);
			for (int j = 0; j < cardFees.size(); j++) {
				if (cardFees.get(j).getMemberLevel() == i) {
					proportion = (new BigDecimal(cardFees.get(j).getCardFee())).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
					break;
				}
			}
			userCenterService.updateAccountBalance(parentUser.getUserNum(), orderInfo.getActualPrice().multiply(proportion), Constants.CONSUM_TYPE_7, 1,  orderInfo.getOrderNum(), Constants.USER_TYPE_3,Constants.CONSUM_TYPE12_title,1);
			i++;
			Commission(i, cardFees, parentUser, orderInfo);
		}
	}
	/**
	 * 功能描述:充值会员生成订单
	 * @author cqm  2017-8-18 下午05:29:44
	 * @param userNum
	 * @param cardFeeNum
	 */
	public TOrderInfo saveOrderMemeber(String userNum,String cardFeeNum){
		TCardFee cardFee = cardFeeService.getCardFee(cardFeeNum, true);
		TUserMember bean = getUserMember(userNum, false);
		TOrderInfo orderInfo = new TOrderInfo();
		orderInfo.setOrderCreateTime(new Date());
		orderInfo.setOrderType(2);//会员订单
		orderInfo.setOrderStatus(1);//默认未支付
		orderInfo.setUserNum(userNum);
		orderInfo.setFalg1(cardFeeNum);
		orderInfo.setTotalCount(cardFee.getMemberLevel());//开通等级
		if(EmptyUtils.isEmpty(bean)){//如果为第一次开通
			orderInfo.setActualPrice(new BigDecimal(cardFee.getCardFee()));
		}else if(bean.getUserMember()==cardFee.getMemberLevel()){//续费
			orderInfo.setActualPrice(new BigDecimal(cardFee.getCardFee()));
		}else if(cardFee.getMemberLevel()>bean.getUserMember()){
			TCardFee cardFeeUser = cardFeeService.getCardFee(bean.getCardFeeNum(), true);
			String date = TimeUtil.getCurrentDay("yyyy-MM-dd hh:mm:ss");//当前系统时间
			//计算当前套餐每天多少钱
			BigDecimal dayPrice = (new BigDecimal(cardFeeUser.getCardFee())).divide(new BigDecimal(365),2,RoundingMode.HALF_UP);
			//计算俩日期相差的天数
			int days = TimeUtil.differentDays(TimeUtil.doFormatDate(date, "yyyy-MM-dd hh:mm:ss"), bean.getEndTime());
			//当前套餐剩余多少钱
			BigDecimal surplusPrice = dayPrice.multiply(new BigDecimal(days));
			//要开通的套餐费用减去当前剩余套餐费用 所得需要支付的价格
			BigDecimal actualPrice = new BigDecimal(cardFee.getCardFee()).subtract(surplusPrice);
			orderInfo.setActualPrice(actualPrice);
		
		}
		orderInfoService.save(orderInfo);
		String orderNum=NumUtils.getOrderNum(NumUtils.PIX_ORDER_INFO_UM,DateUtils.doFormatDate(new Date(), "yyyyMMddHHmmss"),10);
		orderInfo.setOrderNum(orderNum);
        return orderInfo;
	}

	/**
	 * 功能描述：赠送一年会员
	 * @param userNum
	 * @return
	 */
	public Integer saveGiveOrderMember(String userNum) {
		TOrderInfo orderInfo = new TOrderInfo();
		TCardFee cardFee = cardFeeService.get(TCardFee.class, "cardFeeNum", "CFM00000001");
		orderInfo.setOrderCreateTime(new Date());
		orderInfo.setOrderType(2);//会员订单
		orderInfo.setOrderStatus(6);//默认已支付
		orderInfo.setUserNum(userNum);
		orderInfo.setFalg1(cardFee.getCardFeeNum());
		orderInfo.setTotalCount(cardFee.getMemberLevel());//开通等级
		orderInfo.setActualPrice(BigDecimal.valueOf(0));
		try {
			orderInfoService.save(orderInfo);
			String orderNum=NumUtils.getOrderNum(NumUtils.PIX_ORDER_INFO_UM,DateUtils.doFormatDate(new Date(), "yyyyMMddHHmmss"),10);
			orderInfo.setOrderNum(orderNum);
			orderInfoService.update(orderInfo);
			addUserMember(userNum, cardFee.getMemberLevel(), cardFee.getCardFeeNum());
			userCenterService.updateAccountBalance(userNum, orderInfo.getActualPrice(), Constants.CONSUM_TYPE_8, 1,  orderInfo.getOrderNum(), Constants.USER_TYPE_3,Constants.CONSUM_TYPE13_title,1);
			return 1;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 功能描述:获取会员信息详情
	 * @author cqm  2017-8-18 下午04:24:22
	 * @param userNum
	 * @param isRequired
	 * @return
	 */
	public TUserMember getUserMember(String userNum,boolean isRequired){
		TUserMember userMember = get(TUserMember.class, new String[]{"userNum","status"}, new Object[]{userNum,2});
		if(isRequired && userMember==null){
			throw new RuanYunException("会员信息不存在");
		}
		return userMember;
	}
	
	/**
	 * 功能描述:定时器定时更新
	 * @author cqm  2017-8-19 下午03:30:15
	 */
	public void updateMember(){
		userMemberDao.updateMember();
	}

	/**
	 * 功能描述：根据编号更改Status
	 * @param userMemberNum
	 */
	public void updateMemberStatus(String userMemberNum) {
		userMemberDao.updateMemberStatus(userMemberNum);
	}

}
