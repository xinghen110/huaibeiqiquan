package com.ruanyun.web.service.mall;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.BankBindDao;
import com.ruanyun.web.model.mall.TBankBind;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;
@Service("bankBindService")
public class BankBindService extends BaseServiceImpl<TBankBind> {
	@Autowired
	private BankBindDao bankBindDao;
	@Autowired
	private ShopInfoService shopInfoService;
	@Autowired
	private UserService userService;
	public Page<TBankBind> getList(Page<TBankBind> page,TBankBind bankBind){
		return bankBindDao.queryPage(page, bankBind);
	}
	
	/**
	 * 功能描述:app绑定银行卡
	 * @author cqm  2016-12-3 下午03:41:45
	 * @param bankBind
	 * @return
	 */
	@Transactional
	public void saveApp(TBankBind bankBind) {
		TBankBind oldBankBind = getBankBind(bankBind.getCommonNum(), false);
		if(EmptyUtils.isNotEmpty(oldBankBind)){
			throw new RuanYunException("只能绑定一张银行卡！！");
		}
		if(EmptyUtils.isEmpty(bankBind.getCommonNum())||//商家/用户编号
		   EmptyUtils.isEmpty(bankBind.getCardNo())||//卡号
		   EmptyUtils.isEmpty(bankBind.getUserName())||//真实姓名
		   EmptyUtils.isEmpty(bankBind.getBankName())){//开户行
			throw new RuanYunException("参数不全");
		}
		bankBind.setCreateTime(new Date());
		save(bankBind);
		bankBind.setBankBindNum(NumUtils.getCommondNum(NumUtils.PIX_BANK_BIND, bankBind.getBankBindId()));
        update(bankBind);
        TUser user = userService.getUserByUserNum(bankBind.getCommonNum(), true);
        user.setBindStatus(1);
        userService.update(user);
	}
	
	/**
	 * 功能描述:app修改绑定银行卡
	 * @author cqm  2016-12-3 下午04:23:09
	 * @param bankBind
	 * @return
	 */
	public Integer updateBankBind(TBankBind bankBind){
		TBankBind oldBankBind = getBankBind(bankBind.getCommonNum(), true);
		if(EmptyUtils.isNotEmpty(bankBind.getBankBranch())){
			oldBankBind.setBankBranch(bankBind.getBankBranch());
		}
		if(EmptyUtils.isNotEmpty(bankBind.getBankName())){
			oldBankBind.setBankName(bankBind.getBankName());
		}
		if(EmptyUtils.isNotEmpty(bankBind.getCardNo())){
			oldBankBind.setCardNo(bankBind.getCardNo());
		}
		if(EmptyUtils.isNotEmpty(bankBind.getUserName())){
			oldBankBind.setUserName(bankBind.getUserName());
		}
		update(oldBankBind);
		return 1;
	}
   
	/**
	 * 功能描述:查询绑定银行卡详情
	 * @author cqm  2016-12-3 下午04:22:36
	 * @param shopNum
	 * @param isRequired
	 * @return
	 */
	public TBankBind getBankBind(String commonNum,boolean isRequired){
		TBankBind bean = get(TBankBind.class,"commonNum", commonNum);
		if(isRequired && bean==null)
			throw new RuanYunException("当前店铺还没有银行卡,请先绑定银行卡");
		return bean;
	}
	
	/**
	 * 功能描述:修改和添加
	 * @author zhujingbo  
	 * @param bean
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public Integer saveOrUpdate(TBankBind bean,HttpServletRequest request,TUser user) {
		
		if (EmptyUtils.isNotEmpty(bean.getBankBindNum())) {
			TBankBind bankBind = get(TBankBind.class,"bankBindNum", bean.getBankBindNum());
			BeanUtils.copyProperties(bean, bankBind, new String[]{ "bankBindId","bankBindNum","city","area","commonNum","nickName"});
			update(bankBind);
			
		} else {
			if(EmptyUtils.isNotEmpty(user.getCity()))
				bean.setCity(user.getCity());
			if(EmptyUtils.isNotEmpty(user.getArea()))
				bean.setArea(user.getArea());
			save(bean);
			String bankBindNum=NumUtils.getCommondNum(NumUtils.PIX_BANK_BIND, bean.getBankBindId());
			bean.setBankBindNum(bankBindNum);
			TUser user2 = userService.get(TUser.class, "shopNum", bean.getCommonNum());
			user2.setBindStatus(1);
			userService.update(user2);
		}
	return 1;
 }
	
	/**
	 * 功能描述:ajax判断店铺是否重复
	 * 
	 * zhujingbo
	 * 
	 * @param shopNum
	 *           
	 * @return
	 */
	public TBankBind getAjaxshopNum(String shopNum) {
		return bankBindDao.getAjaxshopNum(shopNum);
	}
}
