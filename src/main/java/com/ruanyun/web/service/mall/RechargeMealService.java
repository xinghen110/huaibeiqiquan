package com.ruanyun.web.service.mall;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.RechargeMealDao;
import com.ruanyun.web.model.mall.TRechargeMeal;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;
@Service("recharheMealService")
public class RechargeMealService extends BaseServiceImpl<TRechargeMeal> {

	@Autowired
	private RechargeMealDao rechargeMealDao;
	@Autowired
	private UserService userService;
	
	/**
	 * 功能描述:查询
	 * @author zhujingbo 2016-10-29 
	 * @param rechargeMeal
	 * @param user
	 * @return
	 */
	public Page<TRechargeMeal> getList(Page<TRechargeMeal> page,TRechargeMeal rechargeMeal,TUser user){
		Page<TRechargeMeal>_page=rechargeMealDao.queryPage(page,rechargeMeal,user);
		 
		String userNum=CommonUtils.getAttributeValue(TRechargeMeal.class, _page.getResult(), "userNum");
		Map<String,TUser> userMap=userService.getUserByUserNums(userNum);
		CommonUtils.setAttributeValue(TRechargeMeal.class,  _page.getResult(), userMap, "userNum", "user");
		
		return _page;
		
	}
	
	/**
	 * 功能描述:获取套餐列表
	 * @author cqm  2017-8-16 上午11:02:42
	 * @param trechargeMeal
	 * @return
	 */
	public List<TRechargeMeal> getRechargeMealList(TRechargeMeal trechargeMeal){
		return rechargeMealDao.getList(trechargeMeal);
	}
	
	/**
	 * 功能描述:修改和添加
	 * @author zhujingbo 2016-10-29 
	 * @param rechargeMeal
	 * @param user
	 * @return
	 */
	public Integer saveOrUpdate(TRechargeMeal rechargeMeal,TUser user){
			if (EmptyUtils.isNotEmpty(rechargeMeal.getRechargeMealNum())) {
			
			TRechargeMeal bean = get(TRechargeMeal.class,"rechargeMealNum", rechargeMeal.getRechargeMealNum());
			BeanUtils.copyProperties(rechargeMeal, bean, new String[]{ "rechargeMealNum","createTime","rechargeMealId"});
			update(bean);
			
		} else {
			/*String loginName=rechargeMeal.getUserNum();//获取用户用户填写的登陆账号
			TUser user2=userService.get(TUser.class,"loginName",loginName);//通过登陆账号查询用户信息
			String userNum=user2.getUserNum();//然后再获取用户的userNum
			rechargeMeal.setUserNum(userNum);//存储userNum
			 */	
			rechargeMeal.setCreateTime(new Date());
			save(rechargeMeal);
			String rechargeMealNum=NumUtils.getCommondNum(NumUtils.PIX_RECHARGEMEAL_RCE, rechargeMeal.getRechargeMealId());
			rechargeMeal.setRechargeMealNum(rechargeMealNum);
		}
	return 1;
	}
	
	
	 
	
	
}
