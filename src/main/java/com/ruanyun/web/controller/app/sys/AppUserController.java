package com.ruanyun.web.controller.app.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.app.sys.AppUserService;
@Controller
@RequestMapping("/app/{userNum}/user/")
public class AppUserController extends BaseController{
	@Autowired
	private AppUserService appUserService;
	
	/**
	 * 功能描述: 获取用户信息
	 *
	 * @author yangliu  2016年7月27日 下午3:14:51
	 * 
	 * @param userNum 用户编号
	 * @return
	 */
	@RequestMapping("info")
	public void useUserFriendList(HttpServletResponse response,@PathVariable String userNum,String  friendNum){
		AppCommonModel model=null;
		try {
			if(EmptyUtils.isEmpty(friendNum))
				friendNum=userNum;
			model=appUserService.useUserInfoByUserNum(friendNum);
		} catch (Exception e) {
			logger.error("getUserFriendList:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述: 获取用户中心
	 *
	 * @author yangliu  2016年10月10日 上午9:13:55
	 * 
	 * @param response
	 * @param userNum
	 */
	@RequestMapping("center")
	public void getUserFriendList(HttpServletResponse response,@PathVariable String userNum){
		AppCommonModel model=null;
		try {
			model=appUserService.getUserCenter(userNum);
		} catch (Exception e) {
			logger.error("getUserFriendList:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	
	/**
	 * 功能描述: 获取用户中心记录
	 *
	 * @author yangliu  2016年10月10日 上午9:19:42
	 * 
	 * @param response
	 * @param userNum 用户编号
	 * @param page 分页 
	 * @param record 记录
	 */
	@RequestMapping("center_record")
	public void getUserFriendList(HttpServletResponse response,@PathVariable String userNum,Page<TUserRecord> page,TUserRecord record){
		AppCommonModel model=null;
		try {
			record.setUserNum(userNum);
			model=appUserService.getAccountRecordPage(page, record);
		} catch (Exception e) {
			logger.error("getUserFriendList:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	
	/**
	 * 功能描述: 修改用户信息 
	 *
	 * @author yangliu  2016年7月27日 下午3:14:51
	 * 
	 * @param userNum 用户编号
	 * @return
	 */
	@RequestMapping("update")
	public void update(HttpServletResponse response,@PathVariable String userNum,TUser user,HttpServletRequest request){
		AppCommonModel model=null;
		try {
			model=appUserService.update(userNum, user, request);
		} catch (Exception e) {
			logger.error("update:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:修改是否推送消息状态
	 * @author cqm  2017-1-7 下午07:45:52
	 * @param userNum
	 * @param sendMsgStatus
	 */
	@RequestMapping("updateUserStatus")
	public void updateSendMsgStatus(HttpServletResponse response,@PathVariable String userNum,Integer sendMsgStatus){
		AppCommonModel model=null;
		try {
			model=appUserService.updateSendMsgStatus(userNum, sendMsgStatus);
		} catch (Exception e) {
			logger.error("updateUserStatus:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述: 第三方登陆解绑
	 *
	 * @author yangliu  2016年10月11日 上午10:41:07
	 * 
	 * @param response
	 * @param userNum 用户编号
	 * @param thirdType 第三方登陆
	 */
	@RequestMapping("third_delete")
	public void third_delete(HttpServletResponse response,@PathVariable String userNum,Integer thirdType){
		AppCommonModel model=null;
		try {
			model=appUserService.deleteUserLogin(userNum, thirdType);
		} catch (Exception e) {
			logger.error("update:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:
	 *
	 * @author yangliu  2016年10月11日 上午10:41:00
	 * 
	 * @param response
	 * @param userNum
	 */
	@RequestMapping("third_list")
	public void third_list(HttpServletResponse response,@PathVariable String userNum){
		AppCommonModel model=null;
		try {
			model=appUserService.getUserLoginByUserNum(userNum);
		} catch (Exception e) {
			logger.error("update:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	/**
	 * 功能描述:修改登入密码 /支付密码 /工作状态
	 * @author cqm  2016-11-3 下午04:27:28
	 * @param response
	 * @param userNum
	 * @param request
	 * @param user
	 */
	@RequestMapping("update/login_pay_status")
	public void Update(HttpServletResponse response,@PathVariable String userNum,HttpServletRequest request,TUser user,String oldLoginPass,String oldPayPassword){
		AppCommonModel model = null;
		try {
			model=appUserService.updateUser(request,user,oldLoginPass,oldPayPassword);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/{userNum}/user/update/login_pay_status:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:更新设备号
	 * @author cqm  2017-8-22 下午03:25:30
	 * @param response
	 * @param userNum
	 * @param registrationId
	 */
	@RequestMapping("update/registration_id")
	public void updateRegistrationId(HttpServletResponse response,@PathVariable String userNum,String registrationId){
		AppCommonModel model = null;
		try {
			model=appUserService.updateRegistrationId(userNum, registrationId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/{userNum}/user/update/registration_id:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	
	/**
	 * 功能描述:设置支付密码
	 * @author cqm  2016-11-5 上午10:36:56
	 * @param response
	 * @param userNum
	 * @param request
	 * @param user
	 */
	@RequestMapping("add/payPassword")
	public void appPassword(HttpServletResponse response,@PathVariable String userNum,TUser user){
		AppCommonModel model = null;
		try {
			model=appUserService.addPayPassword(user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("/app/{userNum}/user/add/payPassword:"+e.getMessage());
			model = new AppCommonModel(-1,e.getMessage());
		}
		super.writeAppDataEncryption(response, model);
	}
	

}
