package com.ruanyun.web.service.app.sys;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.model.AppCommonModel;
import com.ruanyun.web.model.daowei.TUserMember;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserRecord;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.daowei.UserMemberService;
import com.ruanyun.web.service.mall.ShopInfoService;
import com.ruanyun.web.service.mall.UserCenterService;
import com.ruanyun.web.service.mall.UserLoginService;
import com.ruanyun.web.service.mall.UserRecordService;
import com.ruanyun.web.service.sys.UserService;

@Service
public class AppUserService {
	
	@Autowired
	UserService userService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private UserRecordService userRecordService;
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserMemberService userMemberService;
	@Autowired
	private ShopInfoService shopInfoService;
	
	/**
	 * 功能描述: 获取用户信息
	 *
	 * @author yangliu  2016年8月24日 上午9:43:51
	 * 
	 * @param userNum
	 * @return
	 */
	public AppCommonModel useUserInfoByUserNum(String userNum){
		TUser user = userService.getUserByUserNum(userNum,true);
		if(user.getUserType()==2){
			TShopInfo shopInfo = shopInfoService.getShopInfo(user.getShopNum(), true);
			user.setTypeNum(shopInfo.getTypeNum());
			user.setShopType(shopInfo.getShopType());
			if(shopInfo.getShopType()==1){//如果为商家 查询该商家下的技师数量
				user.setJishiCount(shopInfo.getJishiCount());
			}
		}
		
		TUserMember userMember = userMemberService.get(TUserMember.class, new String[]{"userNum","status"}, new Object[]{userNum,2});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		if(EmptyUtils.isNotEmpty(userMember)){
			user.setValidityDate(sdf.format(userMember.getEndTime()));//会员有效期
			if (userMember.getEndTime().getTime() < (new Date().getTime())) {
				user.setMemberLevel(1);
				userMemberService.updateMemberStatus(userMember.getUserMemberNum());
				userService.updateQuery(String.valueOf(1), "member_level", userNum, "t_user", "user_num");
			}
		}else{
			user.setValidityDate("");
		}
		return new AppCommonModel(1,"获取用户详情成功",user);
	}
	
	/**
	 * 功能描述:修改登入密码 /支付密码 /工作状态
	 * @author cqm  2016-11-3 下午04:21:29
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public AppCommonModel updateUser(HttpServletRequest request,TUser user,String oldLoginPass,String oldPayPassword) throws Exception{
   		return new AppCommonModel(1,"", userService.updateUserPassword(request,user,oldLoginPass,oldPayPassword));
	}
	
	/**
	 * 功能描述:设置支付密码
	 * @author cqm  2016-11-5 上午10:34:45
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AppCommonModel addPayPassword(TUser user) throws Exception{
		return new AppCommonModel(1,"设置成功", userService.savePayPassword(user));
	}
	
	/**
	 * 功能描述:更新设备号
	 * @author cqm  2017-8-22 下午03:23:47
	 * @param userNum
	 * @param registrationId
	 * @return
	 */
	public AppCommonModel updateRegistrationId(String userNum,String registrationId){
		if(EmptyUtils.isEmpty(registrationId)){
			throw new RuanYunException("设备号不能为空");
		}
		userService.updateRegistrationId(userNum, registrationId);
		return new AppCommonModel(1,"更新设备号成功");
	}
	
	/**
	 * 功能描述: 修改用户信息
	 *
	 * @author yangliu  2016年8月26日 上午9:00:31
	 *
	 * @param newUser 新用户
	 * @param request  
	 * @return
	 */
	public AppCommonModel update(String userNum,TUser newUser,HttpServletRequest request){
		TUser oldUser=userService.getUserByUserNum(userNum,true);
		userService.update(oldUser, newUser, request);
		return new AppCommonModel(1,"",oldUser);
	}
	
	/**
	 * 功能描述: 获取用户中心
	 *
	 * @author yangliu  2016年10月10日 上午9:00:56
	 * 
	 * @param userNum 用户编号
	 * @return
	 */
	public AppCommonModel getUserCenter(String userNum){
		return new AppCommonModel(1,"",userCenterService.getUserCenter(userNum,true));
		
	}
	
	/**
	 * 功能描述: 获取记录
	 *
	 * @author yangliu  2016年10月10日 上午9:17:51
	 * 
	 * @param page 分页
	 * @param record 对象  
	 * @return
	 */
	public AppCommonModel getAccountRecordPage(Page<TUserRecord> page,TUserRecord record){
		return new AppCommonModel(1,"",userRecordService.queryPage(page, record));
	}
	
	/**
	 * 功能描述: 删除第三方登陆
	 *
	 * @author yangliu  2016年10月11日 上午10:37:32
	 * 
	 * @param userNum 用户编号
	 * @param thirdType 第三方类型
	 * @return
	 */
	public AppCommonModel deleteUserLogin(String userNum,Integer thirdType){
		userLoginService.deleteUserLogin(userNum, thirdType);
		return new AppCommonModel(1,"解绑成功");
	}
	
	/**
	 * 功能描述: 获取已绑定的第三方登陆
	 *
	 * @author yangliu  2016年10月11日 上午10:38:50
	 * 
	 * @param userNum 用户编号
	 * @return
	 */
	public AppCommonModel getUserLoginByUserNum(String userNum){
		return new AppCommonModel(1,"",userLoginService.getUserLoginByUserNum(userNum));
	}
	
	/**
	 * 功能描述:修改是否推送消息状态
	 * @author cqm  2017-1-7 下午07:45:52
	 * @param userNum
	 * @param sendMsgStatus
	 */
	public AppCommonModel updateSendMsgStatus(String userNum,Integer sendMsgStatus){
		userService.updateSendMsgStatus(userNum, sendMsgStatus);
		return new AppCommonModel(1,"修改成功");
	}
	
}
