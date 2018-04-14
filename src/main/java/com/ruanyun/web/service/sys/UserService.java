package com.ruanyun.web.service.sys;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aliyuncs.exceptions.ClientException;
import com.ruanyun.web.dao.mall.OrderInfoDao;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.sys.*;
import com.ruanyun.web.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.common.utils.RequestUtils;
import com.ruanyun.common.utils.SysCode;
import com.ruanyun.huanxin.HuanXinUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.sys.UserDao;
import com.ruanyun.web.model.UploadVo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.sms.SendMessage;


@Service("userService")
public class UserService extends BaseServiceImpl<TUser> {

	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	@Autowired
	@Qualifier("loginLogService")
	private LoginLogService loginLogService;

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	@Autowired
	@Qualifier("shopInfoService")
	private ShopInfoService shopInfoService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private ProvincService provincService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreasService areasService;
	@Autowired
	private OrderInfoDao orderInfoDao;
	/**
	 * 功能描述: 登陆用户
	 * 
	 * @author yangliu 2013-9-11 下午04:20:03
	 * 
	 * @param loginName
	 *            登陆号
	 * @param password
	 *            密码
	 * @return 1-- 正常登陆 -1 用户不存在 -2 密码错误
	 */
	public int login(String loginName, String password,
			HttpServletRequest request,String type) {
		HttpSession session = request.getSession();
		if (EmptyUtils.isEmpty(loginName.trim()))
			return -3;// 登录名为空
		 
//		TUser user = userDao.getUserInfo(loginName, type);// this.get(TUser.class, new String[] { "loginName","userStatus","userType" }, new Object[] { loginName.trim(),IPSConstants.GLOBAL_STATUS,type });
		TUser user = userDao.getUserInfomation(loginName, type);
		if (EmptyUtils.isNotEmpty(user)) {
/*			if("2".equals(type)){
				if(user.getUserType()==2 && user.getAuditShopStatus()==2)   // 店铺审核中
					return -5;
				if(user.getUserType()!=2)
					return -4;
				}
					
			if("1".equals(type)){
				if(user.getUserType()!=1&&user.getUserType()!=5)
					return -4;
			}
*/			if (user.getLoginPass().equals(MD5Util.encoderByMd5(password.trim()))) {

					Integer userid = user.getUserId();
					boolean isMobileRequest = RequestUtils.isMobileRequest(request); // true
					// 手机访问查询url不等于手机的 pc访问查询url 不等于手机
					String notRequestType = isMobileRequest ? SysCode.REQUEST_TYPE_PC: SysCode.REQUEST_TYPE_MOBILE;
					// 判断用户的客户端类型 是否为 手机端 还是电脑端
					user.setRequestType(isMobileRequest ? SysCode.REQUEST_TYPE_MOBILE: SysCode.REQUEST_TYPE_PC);
					// 获取用户权限
					user.setAuths(authorityService.getListTAuthByUser(userid,
							Constants.AUTHORITY_TYPE_AUTH, notRequestType));
					List<TAuthority> leftUrls = authorityService.getListTAuthByUser(userid,Constants.AUTHORITY_TYPE_URL, notRequestType);
					// 获取用户url
					user.setUrls(leftUrls);
					// 获取用户角色
					user.setRole(roleService.getRoleListByUserId(userid));
					HttpSessionUtils.setUserToSession(session, user);
					// 把左边菜单放入session中
					HttpSessionUtils.setObjectToSession(session,Constants.SEESION_KEY_LEFTURLS, authorityService.getAuths(leftUrls));
					loginLogService.addLoginLogThead(user, request.getRemoteAddr());
					return 1;
				 
			}
			return -2;// 密码错误
		}

		return -1;// 用户名错误
	}

	/**
	 * 功能描述: 根据账户查询当前用户信息
	 *
	 * @author yangliu  2016年8月1日 下午2:19:29
	 * 
	 * @param loginName 登陆名
	 * @param isRequired 是否判断为空 true 如果为空 抛出异常
	 * @return 
	 */
	public TUser getUserByLoginName(String loginName,Integer userType,boolean isRequired){
		TUser user=this.get(TUser.class, new String[] { "loginName","userType" }, new Object[] { loginName.trim(),userType });
		if(isRequired && user==null){
			throw new RuanYunException("用户不存在");
		}
		return user;
	}
	
	/**
	 * 功能描述: 
	 *
	 * @author yangliu  2016年8月1日 下午2:19:29
	 * 
	 * @param userNum 登陆名
	 * @param isRequired 是否判断为空 true 如果为空 抛出异常
	 * @return 
	 */
	public TUser getUserByUserNum(String userNum,boolean isRequired){
		TUser user=this.get(TUser.class, new String[] { "userNum" }, new Object[] { userNum.trim() });
		if(isRequired && user==null){
			throw new RuanYunException("用户不存在");
		}
		return user;
	}
	
	/**
	 * 功能描述:查询店铺用户详情
	 * @author cqm  2017-4-27 下午01:47:46
	 * @param shopNum
	 * @param isRequired
	 * @return
	 */
	public TUser getUserByShopNum(String shopNum,boolean isRequired){
		TUser user=this.get(TUser.class, new String[] { "shopNum" }, new Object[] { shopNum.trim() });
		if(isRequired && user==null){
			throw new RuanYunException("店铺用户不存在");
		}
		return user;
	}
	
	public TUser getUserByLoginname(String loginName,boolean isRequired){//getLoginName
		TUser user=this.get(TUser.class, new String[] { "loginName" }, new Object[] { loginName.trim() });
		if(isRequired && user==null){
			throw new RuanYunException("用户不存在");
		}
		return user;
	}
	
	/**
	 * 功能描述:更新设备号
	 * @author cqm  2017-8-22 下午03:15:26
	 * @param userNum
	 * @param registrationId
	 */
	public int updateRegistrationId(String userNum,String registrationId){
		if(EmptyUtils.isEmpty(registrationId)){
			throw new RuanYunException("设备号不能为空");
		}
		TUser user = getUserByUserNum(userNum, true);
		userDao.updateRegistrationId(registrationId, user.getUserType());
		return userDao.updateRegistrationId(registrationId, user.getUserType(),userNum);
	}
	
	 
	
	/**
	 * 功能描述:根据账户查询信息
	 * @author cqm  2016-12-29 上午10:01:28
	 * @param loginName
	 * @param
	 * @return
	 */
	public TUser getUserLoginName(String loginName){
		return   userDao.getLoginName(loginName);
	}
	
	/**
	 * 功能描述:注册
	 * @author cqm  2016-11-18 下午07:04:08
	 * @param user
	 * @return
	 * @throws ClientException 
	 */
	public TUser saveUserApp(TUser user) throws ClientException{
		if(EmptyUtils.isEmpty(user.getNickName()))
			user.setNickName(user.getLoginName());
		user.setUserStatus(Constants.GLOBAL_STATUS);
		user.setCreateTime(new Date());
		user.setIsMember(2);//默认为非会员用户
		user.setIsDefaultAddress(2);
		//user.setParentCode("000000");
		user.setBindStatus(2);
		user.setUserPhone(user.getLoginName());
		user.setSendMsgStatus(1);
		user.setMemberLevel(1);//默认为游客
		if(user.getUserType()==2)  //商家技师用户
			user.setAuditShopStatus(2);
		save(user);
		if(!user.getParentCode().equals("000000")){    //分享注册，发送短信验证码
			SendMessage.doAliyunSendSuccess(user.getLoginName(),"123456");
		}
		String userNum=null;
		if(user.getUserType()==2){
			userNum=NumUtils.getCommondNum(NumUtils.PIX_SP_USER, user.getUserId());//商家技师用户
			user.setBindStatus(2);  //新注册的商家默认未绑定银行卡
			userRoleService.save(String.valueOf(Constants.USER_ROLE_SHOP), String.valueOf(user.getUserId()), user);
		}else{
			userNum=NumUtils.getCommondNum(NumUtils.PIX_HY_USER, user.getUserId());//会员用户
		}
		user.setUserNum(userNum);
		user.setUserCode(userNum);
		userCenterService.saveUserCenter(userNum);
		return user;
	}
	
	/**
	 * 功能描述:保存和修改用户
	 * @author cqm  2016-11-18 下午07:04:40
	 * @param currentUser
	 * @param roleId
	 * @param user
	 * @param request
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int saveOrUpdate(TUser currentUser,Integer roleId, TUser user,HttpServletRequest request) {
		 
		if(user.getUserType()==3)
			roleId=Constants.USER_ROLE_MEMBER;
		if(user.getUserType()==2)
			roleId=Constants.USER_ROLE_SHOP;
		if(user.getUserType()==1)
			roleId =Constants.USER_ROLE_SYS;
		if(user.getUserType()==5)//区域管理
			roleId =Constants.USER_ROLE_AREA_MANAGER;
		// 用户id不为空时 ，修改用户信息
		if (user != null) {
			if (EmptyUtils.isNotEmpty(user.getUserId())) {
				TUser  oldUser=getUserById(user.getUserId());
				return update(oldUser, user, request);
			} else {
				user.setCreateTime(new java.util.Date());
				user.setUserStatus(Constants.GLOBAL_STATUS);
				if(user.getUserType()==4)
				user.setWorkStatus(1);
				user.setMemberLevel(1);
				user.setBindStatus(2);
				user.setSendMsgStatus(1);
				user.setLoginPass(MD5Util.encoderByMd5(Constants.USER_DEFULT_PASSWORD));
				save(user);
				String userNum=null;
				 
				if(user.getUserType()==1)
					userNum=NumUtils.getCommondNum(NumUtils.PIX_USER, user.getUserId());
				
				user.setUserNum(userNum);
				if(user.getUserType()==5)//区域管理员
					userNum=NumUtils.getCommondNum(NumUtils.PIX_USER_AREA, user.getUserId());
				user.setUserNum(userNum);
				//更新用户中心
				TUserCenter userCenter = new TUserCenter(userNum,new BigDecimal(0),0.0);
				userCenterService.save(userCenter);
				String userCenterNum=NumUtils.getCommondNum(NumUtils.PIX_USER_CENTER, userCenter.getUserCenterId());
				userCenter.setUserCenterNum(userCenterNum);
				//添加环信用户
				HuanXinUtils.createUser(user.getUserNum(),Constants.HUANXIN_USER_DEFULT_PASSWORD,user.getLoginName()); 
				// 保存用户角色
				if(SecurityUtils.isGranted(ConstantAuth.SYSTEM_AUTH,  currentUser))
					userRoleService.save(String.valueOf(roleId), String.valueOf(user.getUserId()), currentUser);
				else{
					update(user);
					userRoleService.save(String.valueOf(currentUser.getRole().getRoleId()), String.valueOf(user.getUserId()), currentUser);
				}
				return 1;
			}

		}
		return 0;
	}
	
	/**
	 * 功能描述: 修改用户信息
	 *
	 * @author yangliu  2016年8月26日 上午9:00:31
	 * 
	 * @param
	 * @param newUser 新用户
	 * @param request  
	 * @return
	 */
	public int update(TUser oldUser,TUser newUser,HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		UploadVo userphoto =QiniuUploadCommon.uploadPic(multipartRequest.getFile("userPhotoPic"),Constants.QINIU_COMMONT_BUCKET); //主图

		if(EmptyUtils.isNotEmpty(newUser.getUserBirth())){
			oldUser.setUserBirth(newUser.getUserBirth());
		}
		if(EmptyUtils.isNotEmpty(newUser.getUserPhoto())){
			oldUser.setUserPhoto(newUser.getUserPhoto());
		}
		if(EmptyUtils.isNotEmpty(newUser.getUserSex())){
			oldUser.setUserSex(newUser.getUserSex());
		}
		if(EmptyUtils.isNotEmpty(newUser.getNickName())){
			oldUser.setNickName(newUser.getNickName());
		}
		if(EmptyUtils.isNotEmpty(newUser.getWorkStatus())){
			oldUser.setWorkStatus(newUser.getWorkStatus());
		}
		if(EmptyUtils.isNotEmpty(userphoto) && userphoto.getResult()==1){
			oldUser.setUserPhoto(userphoto.getFilename());
	    }
		if(EmptyUtils.isNotEmpty(newUser.getProvince())){
			oldUser.setProvince(newUser.getProvince());
		}
		
		if(EmptyUtils.isNotEmpty(newUser.getCity())){
			oldUser.setCity(newUser.getCity());
		}
		
		if(EmptyUtils.isNotEmpty(newUser.getSendMsgStatus())){
			oldUser.setSendMsgStatus(newUser.getSendMsgStatus());
		}
		update(oldUser);
		return 1;
	}
	/**
	 * 功能描述:查询用户列表
	 * 
	 * @author L H T 2013-11-22 下午04:20:37
	 * 
	 * @param page
	 * @param t
	 * @param currentUser
	 * @return
	 */
	public Page<TUser> queryPage(Page<TUser> page, TUser t, TUser currentUser) {
		Page<TUser> _page = userDao.queryPage(page, t);
		if(EmptyUtils.isNotEmpty(t.getUserType()) && t.getUserType() == 2){//店铺显示店铺名
			String shopNums=CommonUtils.getAttributeValue(TUser.class, _page.getResult(), "shopNum");
			Map<String,TShopInfo> userMap=shopInfoService.getShopInfoByShopNum(shopNums);
			CommonUtils.setAttributeValue(TUser.class,  _page.getResult(), userMap, "shopNum", "shopInfo");
		}
		if(EmptyUtils.isNotEmpty(t.getUserType()) && t.getUserType() == 3){//会员显示邀请人员
			String userNums=CommonUtils.getAttributeValue(TUser.class, _page.getResult(), "parentCode");
			Map<String,TUser> userMap=getUserByUserNums(userNums);
			CommonUtils.setAttributeValue(TUser.class,  _page.getResult(), userMap, "parentCode", "user");

			String userNums2=CommonUtils.getAttributeValue(TUser.class, _page.getResult(), "userNum");
			Map<String, Integer> orderMap = orderInfoDao.getCFOrderCount(userNums2);
			CommonUtils.setAttributeValue(TUser.class,  _page.getResult(), orderMap, "userNum", "cfOrderCount");
		}
		return _page;
	}

	/**
	 * 功能描述: 通过用户id查询用户详细信息
	 * 
	 * @author L H T 2013-11-26 下午03:59:58
	 * 
	 * @param
	 *
	 * @return
	 */
	public TUser getUserById(Integer userId) {
		TUser user = null;
		if (EmptyUtils.isNotEmpty(userId)) {
			user = super.get(TUser.class, userId);
		}
		return user;
	}


	/**
	 * 功能描述:ajax判断用户名是否重复
	 * 
	 * @author L H T 2013-11-26 下午06:25:18
	 * 
	 * @param loginName
	 *            登录名称
	 * @return
	 */
	public TUser getAjaxLoginName(String loginName,Integer userType) {
		return userDao.getAjaxLoginName(loginName,userType);
	}

	/**
	 * 功能描述:删除用户
	 * 
	 * @author L H T 2013-10-11 下午04:25:20
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int userDel(TUser user, TUser tuser) {
		if (user != null) {
			// 通过id查询用户信息
			System.out.println(user.getUserId());
			TUser newUser = super.get(TUser.class, user.getUserId());
			newUser.setUserStatus(Constants.GLOBAL_DEL);// 将用户信息置为删除状态
			update(newUser);
			return 1;
		}
		return 0;
	}
	/**
	 * 功能描述:通过用户id查询用户的名称
	 *
	 * @author L H T  2013-12-2 下午02:30:11
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserNameById(Integer userId) {
		return userDao.getUserNameById(userId);

	}
	/**
	 * 功能描述: 更新个人信息修改
	 *
	 * @author L H T  2013-12-2 下午05:26:27
	 *
	 * @param user
	 */
	@Transactional(rollbackFor = Exception.class)
	public int updatePersonalInfo(TUser user,TUser currentUser){
		if (EmptyUtils.isNotEmpty(user.getUserId())) {
			TUser oldUser=super.get(TUser.class, user.getUserId());
			BeanUtils.copyProperties(user, oldUser, new String[]{"userId","loginName","loginPass","orgId","createCode","createDate","userStatus","checkPass","userPhoto"});
			update(oldUser);
			//操作日志
			return 1;
		}
		return -1;
	}
	/**
	 * 功能描述:修改密码
	 *
	 * @author L H T  2013-12-3 上午09:57:41
	 * 
	 * @param user
	 */
	@Transactional(rollbackFor = Exception.class)
	public int updatePass(TUser user,TUser currentUser){
		if (EmptyUtils.isNotEmpty(user.getUserId())) {
			TUser oldUser=super.get(TUser.class, user.getUserId());
			oldUser.setLoginPass(MD5Util.encoderByMd5(user.getLoginPass()));
			update(oldUser);
			return 1;
		}
		return -1;
	}
	/**
	 * 功能描述:重置用户密码为默认密码
	 *
	 * @author L H T  2013-12-24 下午02:20:57
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(noRollbackFor=Exception.class)
	public int resetUserPassword(TUser currentUser,Integer userId){
		if (EmptyUtils.isNotEmpty(userId)) {
			TUser users=super.get(TUser.class, userId);
			users.setLoginPass(MD5Util.encoderByMd5(Constants.USER_DEFULT_PASSWORD));
			update(users);
			//操作日志
//			operationLogService.addOperationLogThead(currentUser, "用户管理", "重置用户："
//					+ users.getLoginName() + " 的密码", IPSConstants.OPERA_TYPE_USER);
			return 1;
		}
		return 0;
	}
	

	
	
	/**
	 * 功能描述:通过组织org_Code查询用户
	 *
	 * @author L H T  2013-12-18 下午03:34:31
	 * 
	 * @param orgId
	 * @return
	 */
	/*public List<TUser> getUserByOrgId(Integer orgId){
		return userDao.getUserByOrgId(orgId);
	}*/
	/**
	 * 功能描述:手机端上传图片
	 *
	 * @author L H T  2013-12-12 下午04:30:20
	 * 
	 * @param files
	 * @param userId
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@Transactional
	public String uploadUserPhoto(MultipartFile files, Integer userId,
			HttpServletRequest request) throws IOException {
		// 返回结果
		String name = files.getOriginalFilename();
		if (EmptyUtils.isNotEmpty(name)) {
			// 保存文件上传结果
			UploadVo vo = null;
			TUser user = new TUser();
			user = super.get(TUser.class, userId);
			if (EmptyUtils.isNotEmpty(user.getUserPhoto())) {
				// 删除原来的头像
				String contextPath = CommonMethod.getProjectPath(request)
						+ Constants.FILE_USER_PHOTO + user.getUserPhoto();
				FileUtils.deleteFile(contextPath);// 删除上传的文件
			}
			// 上传图片，返回图片路劲FileUtils.FILE_LOGO
			vo = QiniuUploadCommon.uploadPic(files, request,
					Constants.FILE_USER_PHOTO, "");
            //判断是否上传成功
			if (vo.getResult() == Constants.FILE_SUCCESS) {
				user.setUserPhoto(vo.getFilename());
				super.update(user);
				return user.getUserPhoto();
			}
		}
		return null;

	}
	
	 

	 /**
		 * 功能描述: 根据用户编号获取用户信息 返回值为map key为userNum value 为 user对象
		 *
		 * @author yangliu  2016年9月5日 上午10:48:16
		 * 
		 * @param userNums 用户编号格式为 A,B,C,D
		 * @return
		 */
		public Map<String,TUser> getUserByUserNums(String userNums){
			if(EmptyUtils.isEmpty(userNums))
				return null;
			return userDao.getUserByUserNums(userNums);
		}

	/**
	 * 功能描述:启用禁用
	 * @author wsp  2016-10-17 上午11:22:56
	 * @param type
	 * @param ids
	 * @return
	 */
	public int saveIsHomeShow(Integer type, String ids) {
			String[] id=ids.split(Constants.FILE_COMMA);
			for (String adverId:id) {
				TUser bean = super.get(TUser.class, Integer.valueOf(adverId));
					bean.setUserStatus(type);
				super.update(bean);
			}
			return 1;
	}
	/**
	 * 功能描述:设置用户密码,支付密码
	 * @author cqm  2016-11-5 上午10:30:03
	 * @param user
	 * @return
	 */
	public TUser savePayPassword(TUser user){
		TUser oldUser=getUserByUserNum(user.getUserNum(),true);
		if(EmptyUtils.isNotEmpty(user.getPayPassword())){//设置支付密码
			oldUser.setPayPassword(user.getPayPassword());
		}
		if(EmptyUtils.isNotEmpty(user.getLoginPass())){//设置用户密码
			oldUser.setLoginPass(user.getLoginPass());
		}
		return oldUser;
	}
	
	/**
	 * 功能描述:修改登入密码 /支付密码 /工作状态
	 * @author cqm  2016-11-5 下午02:09:53
	 * @param request
	 * @param user 当前用户
	 * @param oldLoginPass 旧登录密码
	 * @param oldPayPassword 旧支付密码
	 * @return
	 * @throws Exception
	 */
	public Integer updateUserPassword(HttpServletRequest request,TUser user,String oldLoginPass,String oldPayPassword)throws Exception{
		TUser oldUser=getUserByUserNum(user.getUserNum(),true);
		if(EmptyUtils.isNotEmpty(user.getLoginPass()) &&  EmptyUtils.isNotEmpty(oldLoginPass)){//修改登录密码
			   if(oldUser.getLoginPass().equals(oldLoginPass)){
				   if(!oldUser.getLoginPass().equals(user.getLoginPass())){
				       oldUser.setLoginPass(user.getLoginPass());
				   }else{
					   throw new  RuanYunException("原密码与新密码一致！");
				   }
			   }else{
				   throw new  RuanYunException("原密码不正确");
			   }
		  }
		if(EmptyUtils.isNotEmpty(user.getPayPassword()) && EmptyUtils.isNotEmpty(oldPayPassword)){//修改支付密码
			if(oldUser.getPayPassword().equals(oldPayPassword)){
				if(!oldUser.getPayPassword().equals(user.getPayPassword())){
					   oldUser.setPayPassword(user.getPayPassword());
					}else{
						throw new  RuanYunException("原支付密码与新支付密码一致！");
					}
			}else{
				throw  new  RuanYunException("原支付密码不正确");
			}
		}
		if(EmptyUtils.isNotEmpty(user.getWorkStatus())){//修改工作状态1,正常   2,下线
			oldUser.setWorkStatus(user.getWorkStatus());
		}
		update(oldUser);
		return 1;
	}
	
 
	
	/**
	 * 修改状态
	 * @param filedValue
	 * @param filed
	 * @param queryFiledValue
	 * @param tableName
	 * @param queryFiled
	 * @return
	 */
	public int updateQuery(String filedValue,String filed,String queryFiledValue,String tableName,String queryFiled){
		userDao.updateQuery(filedValue, filed, queryFiledValue, tableName, queryFiled);
		return 1;
	}
	
    /**
     * 功能描述:更换手机号码
     * @author cqm  2017-1-4 下午04:46:25
     * @param loginName
     * @param oldLoginName
     * @param oldLoginPass
     * @param
     */
	public void updateUserPhone(TUser oldUser,String loginName,String oldLoginName,String oldLoginPass){
		if(EmptyUtils.isNotEmpty(oldUser)){
			if(!oldLoginPass.equals(oldUser.getLoginPass()))
				throw new RuanYunException("原密码不正确");
			oldUser.setLoginName(loginName);
			update(oldUser);
		}
	}
	
	/**
	 * 功能描述:是否推送消息状态
	 * @author cqm  2017-1-7 下午07:45:52
	 * @param userNum
	 * @param sendMsgStatus
	 */
	public void updateSendMsgStatus(String userNum,Integer sendMsgStatus){
		TUser oldTUser = getUserByUserNum(userNum, true);
		if(EmptyUtils.isNotEmpty(sendMsgStatus)){
			oldTUser.setSendMsgStatus(sendMsgStatus);
		}
		update(oldTUser);
	}


	/**
	 * 功能描述：获取未审核的即使用户和店铺用户
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<TUser> getList(Page<TUser> page, TUser user){
		return userDao.getList(page, user);
	}

	/**
	 * 功能描述：通过客户端userNum查询如果他是技师或商家的信息，否则返回空
	 * @param userNum
	 * @return
	 */
	public TUser getShopUser(String userNum) {
		return userDao.getShopUser(userNum);
	}

	/**
	 * 功能描述:导出列表
	 * @author wangyf
	 */
	public void exportExcel(HttpServletResponse response, Integer userType) throws Exception{
		List<TUser> users = userDao.getAllByCondition(TUser.class, "userType", userType);
		if (users.size() > 0 && userType == 2) {
			//注入shopInfo
			String shopNums=CommonUtils.getAttributeValue(TUser.class, users, "shopNum");
			Map<String,TShopInfo> userMap=shopInfoService.getShopInfoByShopNum(shopNums);
			CommonUtils.setAttributeValue(TUser.class,  users, userMap, "shopNum", "shopInfo");

			//注入省市区
			for (TUser user : users) {
				if (EmptyUtils.isNotEmpty(user.getShopInfo())) {
					if (EmptyUtils.isNotEmpty(user.getShopInfo().getProvince()) && EmptyUtils.isNotEmpty(user.getShopInfo().getCity()) && EmptyUtils.isNotEmpty(user.getShopInfo().getArea())) {
						user.setFlag1(provincService.get(TProvince.class, "provinceCode", user.getShopInfo().getProvince()).getProvinceName());
						user.setFlag2(cityService.get(TCity.class, "cityCode", user.getShopInfo().getCity()).getCityName());
						user.setFlag3(areasService.get(TAreas.class, "areaid", user.getShopInfo().getArea()).getArea());
					}
				}
			}
		}
		if (users.size() > 0 && userType == 3) {
			//注入省市区
			for (TUser user : users) {
				if (EmptyUtils.isNotEmpty(user.getAreaid())) {
					user.setFlag1(provincService.get(TProvince.class, "provinceCode", user.getAreaid().substring(0,2)+"0000").getProvinceName());
					user.setFlag2(cityService.get(TCity.class, "cityCode", user.getAreaid().substring(0,4)+"00").getCityName());
					user.setFlag3(areasService.get(TAreas.class, "areaid", user.getAreaid()).getArea());
				}
			}
		}

		if (userType == 3) {
			String fileName = "会员用户信息";
			String[] columns = {"loginName","nickName","isMember","memberLevel","createTime","flag1","flag2","flag3"};
			String[] headers = {"登录名","昵称","是否是会员","会员等级","创建时间","省","市","区"};
			ExcelUtils.exportExcel(response, fileName, users, columns, headers, SysCode.DATE_FORMAT_STR_L);
		} else if (userType == 2) {
			String fileName = "商家用户信息";
			String[] columns = {"loginName","shopName","createTime","flag1","flag2","flag3"};
			String[] headers = {"登录名","店铺名称","创建时间","省","市","区"};
			ExcelUtils.exportExcel(response, fileName, users, columns, headers, SysCode.DATE_FORMAT_STR_L);
		}
	}

	/**
	 * 功能描述：分销提成列表
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<?> fxlist(Page<?> page, TUser user) {
		return userDao.fxlist(page, user);
	}

	/**
	 * 功能描述：获取分销详情
	 */
	public Page<TUser> fxDetail(Page<TUser> page, TUser user, Integer type) {
		Page<TUser> _page = userDao.fxDetail(page, user, type);

		StringBuilder userNums = new StringBuilder();
		for(int i = 0; i < _page.getResult().size(); i++) {
			userNums.append(_page.getResult().get(i).getUserNum());
			if ((i + 1) < _page.getResult().size()) {
				userNums.append(",");
			}
		}

		List<TOrderInfo> orderInfos = orderInfoDao.getFirstCardFeeOrderListByUserNums(userNums.toString());

		for(int i = 0; i < _page.getResult().size(); i++) {
			for (int j = 0; j < orderInfos.size(); j++) {
				if (orderInfos.get(j).getUserNum().equals(_page.getResult().get(i).getUserNum())) {
					_page.getResult().get(i).setFlag1(orderInfos.get(j).getActualPrice().toString());
				}
			}
		}

		return _page;
	}

	/**
	 * 功能描述：获取总金额
	 */
	public Integer getTotalPrice(TUser user, Integer type) {
		List<TUser> users = userDao.getAllFXUserNum(user, type);
		StringBuilder userNums = new StringBuilder();
		for(int i = 0; i < users.size(); i++) {
			userNums.append(users.get(i).getUserNum());
			if ((i + 1) < users.size()) {
				userNums.append(",");
			}
		}
		return orderInfoDao.getFirstCardFeeTotalPriceByUserNums(userNums.toString());
	}


	/**
	 * 查询当前登录用户的邀请的用户列表
	 * @param page
	 * @param currentUser
	 * @return
	 */
	public Page queryUserByInvite(Page page, TUser currentUser, TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime, Integer belongUserId,Integer parentCodeIndex,String parentLoginName){
		return userDao.queryUserByInvite(page,currentUser,selectInviteUser, userInfo, startTime, endTime, belongUserId, parentCodeIndex,parentLoginName);
	}

	public List<HashMap> queryUserByInviteList(TUser currentUser, TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime){
		return userDao.list(currentUser,selectInviteUser, userInfo, startTime, endTime);
	}

	@Override
	public Page<TUser> queryPage(Page<TUser> page, TUser user) {
		return userDao.queryPage(page, user);
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public TUser save(TUser user,TRole role) {
		Integer roleId = 5;
		if(user.getUserType()==1) {
			//TODO 这里需要添加添加时候自选的用户角色
			roleId = role.getRoleId();
//			roleId = IPSConstants.USER_TYPE_MOTHER_01;
		}
		if(user.getUserType()==2)
			roleId=Constants.USER_TYPE_CHILD_02;
		if(user.getUserType()==3)
			roleId =Constants.USER_TYPE_THIRD_03;
		if(user.getUserType()==4)//经销商
			roleId =Constants.USER_TYPE_SPREAD_04;
		if(user.getUserType()==5)//普通用户
			roleId =Constants.USER_TYPE_CUSTOM_05;
		// 用户id不为空时 ，修改用户信息
		if (user != null) {
				user.setCreateTime(new java.util.Date());
				user.setUserStatus(Constants.GLOBAL_STATUS);
				if(user.getUserType()==4)
					user.setWorkStatus(1);
				user.setMemberLevel(1);
				user.setBindStatus(2);
				user.setSendMsgStatus(1);
				user.setLoginPass(MD5Util.encoderByMd5(user.getLoginPass()));
				baseDao.save(user);
				// 保存用户角色
				userRoleService.save(String.valueOf(roleId), String.valueOf(user.getUserId()), new TUser());
		}
		return user;
	}

	public Page queryUserAndParentInfoList(Page page ,TUser user,TUser currentUser, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex){
		return userDao.queryUserAndParentInfoList(page,user,currentUser, userInfo, startTime, endTime,belongUserId,parentCodeIndex);
	}

	public List list(TUser currentUser){
		return userDao.list(currentUser);
	}
}
