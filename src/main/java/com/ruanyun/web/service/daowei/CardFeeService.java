package com.ruanyun.web.service.daowei;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.daowei.CardFeeDao;
import com.ruanyun.web.model.daowei.TCardFee;
import com.ruanyun.web.model.daowei.TTypeInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;


@Service("cardFeeService")
public class CardFeeService extends BaseServiceImpl<TCardFee>{
	
	@Autowired
	private CardFeeDao cardFeeDao;
	@Autowired
	private UserService userService;
	
	/**
	 * 功能描述:查询详情
	 * @author cqm  2017-8-18 下午05:03:17
	 * @param cardFeeNum
	 * @param isRequired
	 * @return
	 */
	public TCardFee getCardFee(String cardFeeNum,boolean isRequired){
		TCardFee ardFee = get(TCardFee.class, "cardFeeNum", cardFeeNum);
		if(isRequired && ardFee==null){
			throw new RuanYunException("会员等级信息不存在");
		}
		return ardFee;
	}
	
	/**
	 * 功能描述:获取会员列表
	 * @author cqm  2017-8-18 下午05:06:02
	 * @return
	 */
	public List<TCardFee> getCardFees(String userNum){
		TUser user = userService.getUserByUserNum(userNum, true);
		return cardFeeDao.getCardFees(user.getMemberLevel());
	}
	/**
	 * 修改 年费 /分销比例
	 * Zjb 2017 上午11:31:31
	 * @param cardFee
	 * @param request
	 * @param user
	 * @return
	 */
	public Integer saveCardFee(TCardFee cardFee, HttpServletRequest request, TUser user){
		TCardFee beanFee =get(TCardFee.class, "cardFeeNum",cardFee.getCardFeeNum());
		beanFee.setCardFee(cardFee.getCardFee());
		beanFee.setUserNum(user.getUserNum());
		update(beanFee);
		return 1;
	}

}
