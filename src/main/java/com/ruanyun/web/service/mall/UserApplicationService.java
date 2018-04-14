package com.ruanyun.web.service.mall;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.UserApplicationDao;
import com.ruanyun.web.dao.sys.DictionaryDao;
import com.ruanyun.web.model.TUserAccountFlow;
import com.ruanyun.web.model.mall.TBankBind;
import com.ruanyun.web.model.mall.TUserApplication;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.UserAccountFlowService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
//提现
@Service("userApplicationService")
public class UserApplicationService extends BaseServiceImpl<TUserApplication>{

    @Autowired
    private UserApplicationDao userApplicationDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    private SmsInfoService smsInfoService;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private BankBindService bankBindService;
	@Autowired
	UserAccountFlowService userAccountFlowService;

    /**
     * 功能描述:查询
     * @author wsp  2016-9-23 下午05:22:43
     * @param page
     * @param userApplication
     * @param user
     * @return
     */
    public Page<TUserApplication> getList(Page<TUserApplication> page,TUserApplication userApplication,TUser user){
        Page<TUserApplication>_page=userApplicationDao.queryPage(page,userApplication,user);

        String userNums=CommonUtils.getAttributeValue(TUserApplication.class, _page.getResult(), "handleNum");
        Map<String,TUser> usersMap=userService.getUserByUserNums(userNums);
        CommonUtils.setAttributeValue(TUserApplication.class,  _page.getResult(), usersMap, "handleNum", "user");

        return _page;
    }
    
	/**
	 * 功能描述:提现App
	 * @author cqm  2016-12-7 上午11:53:34
	 * @param userApplication
	 * @return
	 */
	public TUserApplication saveOrUpdateUserApplication(TUserApplication userApplication,String payPassword){
	 	if(EmptyUtils.isEmpty(userApplication.getUserNum()) || 
	 	   EmptyUtils.isEmpty(payPassword) || 
		   EmptyUtils.isEmpty(userApplication.getMoney())){
			  throw new RuanYunException("参数不全");
			}
	 	TUser user = userService.getUserByUserNum(userApplication.getUserNum(), true);
	 	TBankBind bankBind = bankBindService.getBankBind(userApplication.getUserNum(), true);
	 	if(user.getBindStatus()==2){//未绑定
	 		throw new RuanYunException("使用提现功能需要绑定一张支持提现的储存卡");
	 	}else if(EmptyUtils.isEmpty(user.getPayPassword())){
	 		throw new RuanYunException("请先设置支付密码！！");
	 	}else if(!payPassword.equals(user.getPayPassword()) || EmptyUtils.isEmpty(payPassword)){
	 		throw new RuanYunException("支付密码不正确！！");
	 	}
	 		 	
	 	TUserCenter accountUserCenter = userCenterService.getUserCenter(userApplication.getUserNum(), true);
	 	
//	 	Str1.compareTo(Str2);
//	 	其返回的是一个int类型值。若Str1等于参数字符串Str2字符串，则返回0；
//	 	若该Str1按字典顺序小于参数字符串Str2，则返回值小于0；
//	 	若Str1按字典顺序大于参数字符串Str2，则返回值大于0。
	 	if(userApplication.getMoney().compareTo(accountUserCenter.getAccountBalance())==1){
	 		throw new RuanYunException("余额不足");
	 	}else if(userApplication.getMoney().compareTo(new BigDecimal(0))==0){
	 		throw new RuanYunException("提现金额不能为0");
	 	}else {	
	 		TDictionary bean = dictionaryDao.get(TDictionary.class,new String[]{"parentCode"},new Object[]{"RATE"} );
	 		BigDecimal rate = new BigDecimal(bean.getItemCode()).divide(new BigDecimal(100));//费率
	 		//生成提现申请记录
		 	BigDecimal rateMoney = userApplication.getMoney().multiply(rate);//费率
		
//		 	userApplication.setRateMoney(rateMoney);//提现得到金额
		 	userApplication.setMoney(userApplication.getMoney());//提现原始金额
		 	userApplication.setUserNum(userApplication.getUserNum());
		 	userApplication.setAccountNumber(bankBind.getCardNo());//银行卡号
		 	userApplication.setBankName(bankBind.getBankName());
		 	userApplication.setBranchName(bankBind.getBankBranch());
		 	userApplication.setUserName(bankBind.getUserName());//持卡人姓名
			userApplication.setStatus(Constants.APPLICATION_STATUS_2);//已提交
			userApplication.setCreateTime(new Date());
		 	save(userApplication);
		 	String userApplicationNum = NumUtils.getCommondNum(NumUtils.PIX_USER_APPLICATION_UA, userApplication.getUserApplicationId());
		 	userApplication.setUserApplicationNum(userApplicationNum);
		 	BigDecimal actualPrice = userApplication.getMoney();//所得实际金额
		 	//生成流水信息
		 	userCenterService.updateAccountBalance(user.getUserNum(), actualPrice.multiply(new BigDecimal(-1)), Constants.CONSUM_TYPE_4, Constants.PAY_TYPE_1, userApplicationNum, user.getUserType(), Constants.CONSUM_TYPE4_title,0);
		 	
//			//提现费率流水
//			userCenterService.updateAccountBalance(user.getUserNum(),rateMoney.multiply(new BigDecimal(-1)),IPSConstants.CONSUM_TYPE_4, IPSConstants.PAY_TYPE_1,userApplicationNum,user.getUserType(),IPSConstants.CONSUM_TYPE9_title,0);

		}
		return userApplication; 
	}

	/**
	 * 功能描述:提现 金点的版本
	 * @param userApplication
	 * @return
	 */
	public TUserApplication saveJinNiuVersion(TUserApplication userApplication) {
		if (EmptyUtils.isEmpty(userApplication.getMoney())) {
			throw new RuanYunException("参数不全");
		}
		userApplication = super.save(userApplication);
		return userApplication;
	}

	/**
     * 功能描述:提现处理
     * @param bean
     * @param request
     * @param user
     * @return
     */
    public Integer saveOrUpdate(TUserApplication bean,HttpServletRequest request,TUser user ) {
        TUserApplication userApplication = get(TUserApplication.class, bean.getUserApplicationId());
        TUser shopUser = userService.get(TUser.class, "userNum", userApplication.getUserNum());
        userApplication.setHandleTime(new Date());
        userApplication.setHandleNum(user.getUserNum());
        userApplication.setHandleRemark(bean.getHandleRemark());
        userApplication.setStatus(bean.getStatus());
        update(userApplication);
        if(userApplication.getStatus() ==-1){  //提现失败，退换钱
            BigDecimal totalPrice = userApplication.getMoney(); //提现总金额
            //更新账户余额
            userCenterService.updateAccountBalance(shopUser.getUserNum(),totalPrice, Constants.CONSUM_TYPE_4, Constants.PAY_TYPE_1, userApplication.getUserApplicationNum(),Constants.USER_TYPE_2,Constants.CONSUM_TYPE4_1_title,0);
            //smsInfoService.saveSms("提现失败", bean.getHandleRemark(),userApplication.getUserApplicationNum() , shopUser.getUserNum(), "ZJLZ", IPSConstants.SYSTEM_CODE, IPSConstants.USER_TYPE_2, userApplication.getUserNum(), IPSConstants.SYS_TYPE_2, "");
        }
        return 1;
    }

	public void saveAgree(TUserApplication userApplication) {
    	//todo 后台提现审核通过时调用
		TUserAccountFlow userAccountFlow = new TUserAccountFlow(
				Integer.parseInt(userApplication.getUserNum()),
				userApplication.getMoney(),
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				"",
				"",
				"",
				0,
				"用户提现",
				new Date()
		);
		userAccountFlowService.save(userAccountFlow);
	}
}










