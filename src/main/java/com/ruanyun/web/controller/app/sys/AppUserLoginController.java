package com.ruanyun.web.controller.app.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.app.sys.AppUserLoginService;
import com.ruanyun.web.util.MD5;
@Controller
@RequestMapping("app/external/{loginName}/user")
public class AppUserLoginController extends BaseController {
	@Autowired
	private AppUserLoginService appUserLoginService;
	
	
	/**
	 * 功能描述:发送短信(获取验证码)
	 *
	 * @author yangliu  2016年9月19日 上午8:18:49
	 * 
	 * @param response
	 * @param userNum
	 * @param user
	 * @param request
	 */
	@RequestMapping("send_msg")
	public void sendMsg(HttpServletResponse response,@PathVariable String loginName,Integer userType,String type) {
		AppCommonModel model=null;
		try {
			model=appUserLoginService.sendMsg(loginName, userType, type);
		} catch (Exception e) {
			logger.error("send_msg:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:分享获取验证码
	 * @author cqm  2017-8-26 下午06:00:01
	 * @param response
	 * @param loginName
	 * @param userType
	 * @param type
	 */
	@RequestMapping("share")
	public void shareSendMsg(HttpServletResponse response,@PathVariable String loginName,Integer userType,String type) {
		AppCommonModel model=null;
		try {
			
			model=appUserLoginService.sendMsg(loginName, userType, type);
		} catch (Exception e) {
			logger.error("send_msg:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		System.out.println("验证码："+model.getObj());
		super.writeText(response,model.getObj());
//		super.writeAppDataEncryption(response, model.getResult());
	}
	/**
	 * 功能描述: 用户登陆
	 *
	 * @author yangliu  2016年8月1日 下午2:27:42
	 * 
	 * @param loginName 登陆名
	 * @param loginPass 密码【MD5后】
	 * @param userType 1系统用户 2店铺用户  3会员账户
	 * @param registrationId 极光推送设备标识号 
	 * @return
	 */
	@RequestMapping("login")
	public void login(HttpServletResponse response,@PathVariable String loginName, String loginPass, Integer userType) {
		AppCommonModel model=null;
		try {
			model=appUserLoginService.login(loginName, loginPass, userType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:更换手机
	 * @author cqm  2017-1-4 下午04:33:43
	 * @param response
	 * @param loginName 新账户
	 * @param userType 用户类型
	 * @param oldLoginName 原账户
	 * @param oldLoginPass 原密码
	 */
	@RequestMapping("updateUserPhone")
	public void updateUserPhone(HttpServletResponse response,@PathVariable String loginName,String userNum,String oldLoginName,String oldLoginPass) {
		AppCommonModel model=null;
		try {
			model=appUserLoginService.updateUserPhone(loginName,userNum,oldLoginName, oldLoginPass);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述: 用户第三方登陆
	 *
	 * @author yangliu  2016年8月1日 下午2:27:42
	 * 
	 * @param loginName 登陆名
	 * @param loginPass 密码【MD5后】
	 * @param userType 【2 技师用户 3 客户端用户】
	 * @return
	 */
	@RequestMapping("login_third")
	public void login_third(HttpServletResponse response,@PathVariable String loginName,Integer thirdType) {
		AppCommonModel model=null;
		try {
			model=appUserLoginService.login_third(thirdType, loginName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:第三方绑定或注册用户
	 *
	 * @author yangliu  2016年10月9日 下午1:46:09
	 * 
	 * @param response
	 * @param request
	 * @param loginName 手机号
	 * @param user 用户对象
	 * @param thirdType 第三方类型 
	 * @param thirdNum 第三方唯一编号
	 */
	@RequestMapping("add_third")
	public void add_third(HttpServletResponse response,HttpServletRequest request,@PathVariable String loginName,TUser user,Integer thirdType,String thirdNum,String userPhotoPic,String nickName){
		AppCommonModel model=null;
		try {
			user.setLoginName(loginName);
			user.setParentCode("000000");
			model=appUserLoginService.add_third(user, thirdType, thirdNum, userPhotoPic, nickName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:注册用户
	 *
	 * @author yangliu  2016年8月1日 下午2:56:48
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("add")
	public void add(HttpServletResponse response,@PathVariable String loginName,TUser user){
		AppCommonModel model=null;
		try {
			user.setLoginName(loginName);
			if(EmptyUtils.isNotEmpty(user.getParentCode())){
				user.setParentCode(user.getParentCode());
			}else{
				user.setParentCode("000000");
			}
			model=appUserLoginService.add(user);
			
		} catch (Exception e) {
			logger.error("login:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	

	
	/**
	 * 功能描述: 忘记密码/支付密码
	 *
	 * @author yangliu  2016年8月2日 上午9:33:15
	 * 
	 * @param loginName 登录账户
	 * @param password 登录密码
	 * @param patPassword 支付密码
	 * @param userType 用户类型
	 * @return
	 */
	@RequestMapping("forgetPassword")
	public void forgetPassword(HttpServletResponse response,@PathVariable String loginName,TUser user){
		AppCommonModel model=null;
		try {
			model=appUserLoginService.forgetPassword(user);
		} catch (Exception e) {
			logger.error("app/external/{loginName}/user/forgetPassword:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
}
