package com.ruanyun.web.controller.sys;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.IBaseService;
import com.ruanyun.common.utils.EmptyUtils;

import com.ruanyun.web.model.sys.TRole;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.OperationLogService;
import com.ruanyun.web.service.sys.RoleService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.CommonUtils;
import com.ruanyun.web.util.ConstantAuth;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.HttpSessionUtils;
import com.ruanyun.web.util.SecurityUtils;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	

	@Autowired
	@Qualifier("userService")
	UserService userService;
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Autowired
	private OperationLogService operationLogService;
	
	/**
	 * 功能描述:绑定时间
	 */
	@InitBinder
	public void initBinders(WebDataBinder binder) {
		super.initBinder(binder, "yyyy-MM-dd", true);
	}
	
	/**
	 * 功能描述:用户列表
	 *
	 * @author yangliu  2013-9-16 上午09:17:34
	 * 
	 * @param tuser 用户信息
	 * @return
	 */
	@RequestMapping("/users")
	public String users(HttpSession session,TUser tuser,Page<TUser> page,Model model,Integer userType){
		TUser currentUser=HttpSessionUtils.getCurrentUser(session);
		if(SecurityUtils.isGranted(ConstantAuth.SYSATEM_AUTH, currentUser)&& userType!=3){  //区域管理员
			tuser.setCity(CommonUtils.getUserCity(currentUser));
			}
		addModel(model, "pageList",userService.queryPage(page, tuser,currentUser));
		addModel(model, "tuser", tuser);
		addModel(model, "userType", userType);
		
		if(userType==1)  //系统用户
		return "pc/sys/user/list";
		if(userType==2)  //店铺用户/技师
			return "pc/sys/user/dianpulist";
		if(userType==3)  //会员用户
			return "pc/sys/user/huiyuanlist";

		return "";
	}

	/**
	 * 功能描述:导出数据
	 * @author wangyf
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse response, HttpSession session, Integer userType) throws Exception{
		TUser user = HttpSessionUtils.getCurrentUser(session);
		userService.exportExcel(response, userType);
	}

	
	/**
	 * 功能描述:跳转到用户的添加和修改页面 
	 *
	 * @author L H T  2013-11-26 下午04:05:04
	 * 
	 * @param user 用户实体
	 * @param
	 * @return
	 */
	@RequestMapping("/toUserEdit")
	public String userAddOrEditJsp(TUser user,Model model,Integer userType){
		
		if (EmptyUtils.isNotEmpty(user.getUserId())) {
			//通过id查询用户信息
			user=userService.getUserById(user.getUserId());
			
			//通过用户id查询用户拥有的角色
			TRole  userRole =roleService.getRoleListByUserId(user.getUserId());
			addModel(model, "userRole", userRole);
		}
		addModel(model, "user",user );
		//查询所有角色
		List<TRole> allRoles=roleService.getAll(TRole.class,"orderby",IBaseService.ORDER_DESC);
		addModel(model, "allRoles", allRoles);
		addModel(model,"userType",userType);
		if(userType==4)  //物流人员
			return "pc/sys/user/wuliuEdit";
		else
			return "pc/sys/user/userEdit";
	}
	
	/**
	 * 功能描述://保存和修改用户
	 *
	 * @author L H T  2013-10-11 下午12:02:17
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(TUser user,HttpSession session,Integer roleId, HttpServletResponse response,Model model,HttpServletRequest request) throws Exception{
		//获取当前登录人的session信息
		TUser currentUser=HttpSessionUtils.getCurrentUser(session);
		if(SecurityUtils.isGranted(ConstantAuth.SYSATEM_AUTH, currentUser)){ //区域管理员
			user.setProvince(currentUser.getProvince());
			user.setCity(currentUser.getCity());
			user.setArea(currentUser.getArea());
		}
		int result=userService.saveOrUpdate(currentUser, roleId, user,  request);
		if (result==1) {
			super.writeJsonData(response,CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "user/users?userType="+user.getUserType(), "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}
	

	
	/**
	 * 功能删除：用户
	 * 
	 * zhujingbo 
	 */
	@RequestMapping("/del")
	public void userDel(HttpServletResponse response,String loginName,Integer userStatus,Integer userType,HttpSession session){
		TUser currentUser =HttpSessionUtils.getCurrentUser(session);
		if(userStatus!=1){
			int result = userService.delete(TUser.class, new String[]{"loginName","userType"}, new Object[]{loginName,userType});
			//操作日志
			operationLogService.addOperationLogThead(currentUser, "用户管理", "删除用户："
					+ currentUser.getLoginName() + "", "删除用户");
			if(result == 1){
				super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","user/users", "redirect"));
			}else{
				super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
			}
			
		}else {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE,"此用户非禁用状态，不可删除！！", "main_","user/users", "forward"));
		}
	}
	
	/***
	 * 
	 * ajax判断登录名是否存在
	 * 
	 */
	@RequestMapping("searchAjaxName")
	public void getUser(HttpServletResponse response,String loginName,Integer userType){
		String result = null;
		TUser info = userService.getAjaxLoginName(loginName,userType);
		if(info == null){
			result = "success";
		}else{
			result = "fail";
		}
		super.writeText(response,result);
	}
	/**
	 * 功能描述:跳转到修改个人信息页面
	 *
	 * @author L H T  2013-12-2 下午05:10:11
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("editPersion")
    public String showPersonalInfo(Model model,Integer userId){
    	//通过id查询用户信息
		TUser user=userService.getUserById(userId);
		addModel(model, "user", user);
		return "pc/sys/user/personlEdit";
	}
	/**
	 * 功能描述: 更新个人信息修改
	 *
	 * @author L H T  2013-12-2 下午05:26:27
	 * 
	 * @param model
	 * @param session
	 * @param response
	 * @param user
	 */
	@RequestMapping("updatePersonalInfo")
	public void updatePersonalInfo(Model model,HttpSession session,HttpServletResponse response,TUser user){
		TUser currentUser=HttpSessionUtils.getCurrentUser(session);
		int result=userService.updatePersonalInfo(user, currentUser);
		if (result==1) {
			String url="user/editPersion?userId="+user.getUserId();
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "persion_edit", url, ""));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}
	/**
	 * 功能描述:跳转到密码修改页面
	 *
	 * @author L H T  2013-12-3 上午09:21:02
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("editpass")
	public String editPassword(Model model,Integer userId){
		//通过id查询用户信息
		TUser user=userService.getUserById(userId);
		addModel(model, "user", user);
		return "pc/sys/user/editPassword";
	}
	/**
	 * 功能描述:修改密码
	 *
	 * @author L H T  2013-12-3 上午09:57:41
	 * 
	 * @param user
	 * @param response
	 * @param session
	 */
	@RequestMapping("updatePass")
	public String updatePass(TUser user,HttpServletResponse response,HttpSession session){
		TUser currentUser=HttpSessionUtils.getCurrentUser(session);
		int result=userService.updatePass(user, currentUser);
		if (result==1) {
			return REDIRECT +"/loginout";
//			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,"密码修改成功！请重新登录...", "", "loginout", "forward"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
			return null;
		}
	}
	

	/**
	 * 功能描述:重置用户的密码为默认密码
	 *
	 * @author L H T  2013-12-24 下午02:18:19
	 * 
	 * @param response
	 * @param session
	 */
	@RequestMapping("resetPassword")
	public void resetUserPassword(HttpServletResponse response,HttpSession session,Integer userId){
		TUser currentUser=HttpSessionUtils.getCurrentUser(session);
		TUser user = userService.getUserById(userId);
		int result=userService.resetUserPassword(currentUser, userId);
		if (result==1) {
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "user/users?userType="+user.getUserType(), "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "", "", ""));
		}
	}


	/**
	 * 功能描述:更新条件
	 * @param response
	 * @param filed
	 * @param filedValue
	 * @param tableName
	 * @param queryFiled
	 * @param queryFiledValue
	 */
	@RequestMapping("updateQuery")
	public void updateQuery(HttpServletResponse response,String filed,String filedValue,String tableName,String queryFiled,String queryFiledValue){
		int result=userService.updateQuery(filedValue, filed, queryFiledValue, tableName, queryFiled);
		TUser user = userService.get(TUser.class,Integer.parseInt(queryFiledValue));
		if(result == 1 ){
			if(filedValue.equals("0")){
//				user.setWorkStatus(0);
				user.setUserStatus(0);
				userService.update(user);
			}
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","admin/user/list?userType="+user.getUserType(), "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}
	
	@RequestMapping("updatework")
	public void updatework(HttpServletResponse response,String filed,String filedValue,String tableName,String queryFiled,String queryFiledValue){
		int result=userService.updateQuery(filedValue, filed, queryFiledValue, tableName, queryFiled);
		TUser user = userService.get(TUser.class,"userNum",queryFiledValue);
		if(result == 1 ){
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_","user/users?userType="+user.getUserType(), "redirect"));
		}else{
			super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED, "","", ""));
		}
	}

	/**
	 * 功能描述：获取分销提成用户列表
	 * @param page
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("fxlist")
	public String fxlist(Page<?> page, TUser user, Model model) {
		addModel(model, "pageList", userService.fxlist(page, user));
		addModel(model, "bean", user);
		return "pc/backend/fxuser/list";
	}

	/**
	 * 功能描述：获取分销详情
	 */
	@RequestMapping("fxdetail")
	public String fxDetail(Page<TUser> page, TUser user, Integer type, Model model) {
		addModel(model, "pageList", userService.fxDetail(page, user, type));
		addModel(model, "totalPrice", userService.getTotalPrice(user, type));
		addModel(model, "user", user);
		addModel(model, "type", type);
		return "pc/backend/fxuser/fxdetail";
	}
}
