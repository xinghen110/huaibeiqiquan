package com.ruanyun.web.service.app.sys;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.aliyuncs.exceptions.ClientException;
import com.ruanyun.web.model.daowei.TUserMember;
import com.ruanyun.web.service.daowei.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.dao.mall.SmsCountDao;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TSmsCount;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.SmsCountService;
import com.ruanyun.web.service.mall.UserLoginService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.sms.SendMessage;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;


@Service
public class AppUserLoginService {
	
	@Autowired
	private	UserService userService;
	
	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private UserMemberService userMemberService;
	
	@Autowired
	private ShopInfoService shopInfoService;

	@Autowired
	private SmsCountDao smsCountDao;
	
	@Autowired
	private SmsCountService smsCountService;
	
	/**
	 * 功能描述: 用户登陆
	 *
	 * @author yangliu  2016年8月1日 下午2:27:42
	 * 
	 * @param loginName 登陆名
	 * @param loginPass 密码【MD5后】
	 * @param userType 1系统用户 2店铺用户  3会员账户 4物流账户
	 * @param registrationId 设备号
	 * @return
	 */
	@Transactional
	public AppCommonModel login(String loginName, String loginPass, Integer userType) {
  	   AppCommonModel model = new AppCommonModel(-1, "");
		TUser user = userService.getUserByLoginName(loginName,userType, true);
		
        //判断用户是否被禁用
		if(user.getUserStatus()==2){
			model.setMsg("此账号已被冻结");
			return model;
		}
		
		if (userType != user.getUserType()) {
			model.setMsg("用户不存在");
			return model;
		}
		if(EmptyUtils.isEmpty(user.getLoginPass())){
			model.setMsg("该账户未设置密码,请设置密码！！");	
			return model;
		}
		if (!user.getLoginPass().equals(loginPass)) {
			model.setMsg("密码错误");	
			return model;
		}
		if(user.getUserType()==2){//查询是否是商家还是技师
			TShopInfo shopInfo = shopInfoService.getShopInfo(user.getShopNum(), true);
			user.setShopType(shopInfo.getShopType());
			user.setTypeNum(shopInfo.getTypeNum());
		}

		TUserMember userMember = userMemberService.get(TUserMember.class, new String[]{"userNum","status"}, new Object[]{user.getUserNum(),2});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		if(EmptyUtils.isNotEmpty(userMember)){
			user.setValidityDate(sdf.format(userMember.getEndTime()));//会员有效期
			if (userMember.getEndTime().getTime() < (new Date().getTime())) {
				user.setMemberLevel(1);
				userMemberService.updateMemberStatus(userMember.getUserMemberNum());
				userService.updateQuery(String.valueOf(1), "member_level", user.getUserNum(), "t_user", "user_num");
			}
		}else{
			user.setValidityDate("");
		}

		model.setObj(user);
		model.setMsg("登陆成功");
		model.setResult(1);
		return model;
	}
	
	/**
	 * 功能描述:更换手机
	 * @author cqm  2017-1-4 下午04:31:15
	 * @param user
	 * @param oldLoginName
	 * @param oldLoginPass
	 * @return
	 */
	public AppCommonModel updateUserPhone(String loginName,String userNum,String oldLoginName,String oldLoginPass){
		AppCommonModel model = new AppCommonModel(-1, "");
		TUser oldUser = userService.getUserByUserNum(userNum, true);
		TUser user = userService.getUserByLoginName(loginName, oldUser.getUserType(), false);
		if(EmptyUtils.isNotEmpty(user)){
			model.setMsg("此账号已存在,不可修改");
		    return model;
		}
		userService.updateUserPhone(oldUser,loginName,oldLoginName, oldLoginPass);
		model.setResult(1);
		model.setMsg("修改成功");
		return model;
		
	}
	
	/**
	 * 功能描述:注册用户
	 *
	 * @author yangliu  2016年8月1日 下午2:56:48
	 *                          
	 * @param user
	 * @return
	 * @throws ClientException 
	 */
	public AppCommonModel add(TUser user) throws ClientException{
		AppCommonModel model = new AppCommonModel(-1, "");
		if(EmptyUtils.isEmpty(user.getLoginName())){
			model.setMsg("登陆名不能为空");
			return model;  
		}
		
		if(EmptyUtils.isEmpty(user.getLoginPass())){
			model.setMsg("密码不能为空");
			return model;
		}
		
		if(EmptyUtils.isEmpty(user.getUserType())){
			model.setMsg("用户类型为空");
			return model;
		}
		TUser oldUser = userService.getUserByLoginName(user.getLoginName(),user.getUserType(), false);
		if(oldUser !=null){
			model.setMsg("该手机号已被注册");
			return model;
		}
//	   if(user.getUserType()==2){//店铺用户  判断店铺用户昵称是否已存在
//			TUser user2 = userService.get(TUser.class,"nickName",user.getNickName());
//			if(EmptyUtils.isNotEmpty(user2)){
//				if(user.getNickName().equals(user2.getNickName())){
//					model.setMsg("该店铺昵称已存在");
//					return model;	
//				}
//			}
//		}
		userService.saveUserApp(user);
		model.setResult(1);
		model.setMsg("注册成功");
		return model;
	}
	
	/**
	 * 功能描述: 忘记密码/忘记支付密码
	 *
	 * @author yangliu  2016年8月2日 上午9:33:15
	 * 
	 * @param loginName
	 * @param password
	 * @param userType
	 * @return
	 */
	@Transactional
	public AppCommonModel forgetPassword(TUser user){
		AppCommonModel model = new AppCommonModel(-1, "");
		TUser oldUser = userService.getUserByLoginName(user.getLoginName(),user.getUserType(), true);
		if (user.getUserType() != oldUser.getUserType()) {
			model.setMsg("用户不存在");
			return model;
		}
		if(EmptyUtils.isNotEmpty(user.getLoginPass())){
			oldUser.setLoginPass(user.getLoginPass());
		}
		if(EmptyUtils.isNotEmpty(user.getPayPassword())){
			oldUser.setPayPassword(user.getPayPassword());
		}
		userService.update(oldUser);
		model.setResult(1);
		model.setMsg("修改成功");
		return model;
	}
	/**
	 * 功能描述:发送验证码
	 * @author cqm  2017-1-9 上午11:15:02
	 * @param loginName
	 * @param userType
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public AppCommonModel sendMsg(String loginName,Integer userType,String type) throws Exception{
		AppCommonModel model = new AppCommonModel(-1, "","");
		System.out.println("====loginName===="+loginName);
		System.out.println("====userType===="+userType);
		System.out.println("====type===="+type);
		TUser user=userService.getUserByLoginName(loginName,userType, false);
		if(user==null && "1".equals(type)){  //修改手机号
			model.setMsg("用户不存在");
			return model;
		}else if(user !=null && "2".equals(type)){ //注册
			model.setMsg("用户已存在");
			return model;
		}
		if(user !=null && "1".equals(type)&& 
		   EmptyUtils.isNotEmpty(userType) && 
		   userType!=user.getUserType()){
			model.setMsg("用户不存在");
			return model;
		}
		Integer random=(int) (1000+Math.random()*9000);
		SendMessage.doAliyunSend(loginName,random);
		
//		TSmsCount bean = smsCountDao.getSmsCount(loginName);
//		if(random>1){   //大于1记录当天发送次数
//							
//			if(EmptyUtils.isEmpty(bean)){//如果当天信息为空，生成新的短信记录
//				TSmsCount smsCount = new TSmsCount();
//				smsCount.setCreateTime(new Date());
//				smsCount.setLinkTel(loginName);
////				smsCount.setUserNum(user.getUserNum());
//				smsCount.setSendCount(1);
//				smsCount.setSendStatus(1);
//				smsCountService.save(smsCount);
//				String smsCountNum=NumUtils.getCommondNum(NumUtils.PIX_SMS_COUNT, smsCount.getSmsCountId());
//				smsCount.setSmsCountNum(smsCountNum);
//			}else{
//				if(bean.getSendCount()>Constants.SEND_COUNT){//短信次数
//					model.setMsg("今日短信次数已超过五次,请改日再进行操作！！");
//					return model;
//				}
//				bean.setSendCount(bean.getSendCount()+1);
//				smsCountService.update(bean);
//			}
//		}
		model.setResult(1);
		model.setMsg("发送成功");
		model.setObj(String.valueOf(random));
		return model;
	}
	
	/**
	 * 功能描述: 第三方登陆
	 *
	 * @author yangliu  2016年10月9日 下午1:28:34
	 * 
	 * @param thirdType 1-QQ 2-微信 3-新浪微博
	 * @param thirdNum 第三方唯一标识
	 * @return
	 */
	public AppCommonModel login_third(Integer thirdType,String thirdNum){
		return new AppCommonModel(1,"登陆成功",userLoginService.doLogin(thirdType, thirdNum));
	}
	
	/**
	 * 功能描述: 第三方注册
	 *
	 * @author yangliu  2016年10月9日 下午1:36:26
	 * 
	 * @param user 用户编号
	 * @param thirdType  1-QQ 2-微信 3-新浪微博
	 * @param thirdNum 第三方唯一标识
	 * @param request
	 * @return
	 * @throws ClientException 
	 */
	public AppCommonModel add_third(TUser user,Integer thirdType,String thirdNum,String userPhotoPic,String nickName) throws ClientException{
		return new AppCommonModel(1,"第三方绑定或注册成功",userLoginService.saveUser(user, thirdNum, thirdType, userPhotoPic, nickName));
	}
			

}
