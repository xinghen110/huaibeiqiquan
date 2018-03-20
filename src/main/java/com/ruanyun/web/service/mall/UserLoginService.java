package com.ruanyun.web.service.mall;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.UserLoginDao;
import com.ruanyun.web.model.mall.TCommentInfo;
import com.ruanyun.web.model.mall.TOrderInfo;
import com.ruanyun.web.model.mall.TShopInfo;
import com.ruanyun.web.model.mall.TUserLogin;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.NumUtils;
@Service
public class UserLoginService extends BaseServiceImpl<TUserLogin> {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private ShopInfoService shopInfoService;
	/**
	 * 功能描述: 第三方登陆
	 *
	 * @author yangliu  2016年10月9日 上午11:38:29
	 * 
	 * @param thirdType 1-QQ 2-微信 3-新浪微博
	 * @param thirdNum
	 * @return
	 */
	public TUser doLogin(Integer thirdType,String thirdNum){
		TUserLogin userLogin=super.get(TUserLogin.class, new String[]{"thirdType","thirdNum"},new Object[]{thirdType,thirdNum});
		if(userLogin!=null){
			return userService.getUserByUserNum(userLogin.getUserNum(), true);
		}
		return new TUser();
	}
	
	/**
	 * 功能描述:第三方绑定或注册用户
	 *
	 * @author yangliu  2016年10月9日 上午11:49:35
	 * 
	 * @param user
	 * @param thirdNum
	 * @param thirdType
	 * @param request
	 * @return
	 * @throws ClientException 
	 */
	public TUser saveUser(TUser user,String thirdNum,Integer thirdType,String userPhotoPic,String nickName) throws ClientException{
		TUser oldUser=userService.getUserByLoginName(user.getLoginName(),user.getUserType(), false);
		if(oldUser==null){
			oldUser=userService.saveUserApp(user);//注册
		}
		TUserLogin userLogin = getUserLoginDetails(thirdNum,true);//查询是否绑定
		if(EmptyUtils.isEmpty(userLogin)){
			if(oldUser.getUserType()==3){//必须是会员用户才能绑定
		      saveUserLogin(oldUser.getUserNum(), thirdType, thirdNum);
			}else{
			  throw new RuanYunException("必须是会员用户才能绑定！！");
		    }
		}
		if(EmptyUtils.isNotEmpty(userPhotoPic)){
			oldUser.setUserPhoto(userPhotoPic);
		}
		if(EmptyUtils.isNotEmpty(nickName)){
			oldUser.setNickName(nickName);
		}
	    userService.update(oldUser);//修改用户信息
		return oldUser;
	}
	
	/**
	 * 功能描述:保存第三方登陆
	 *
	 * @author yangliu  2016年10月9日 下午1:21:35
	 * 
	 * @param userNum
	 * @param thirdType 1-QQ 2-微信 3-新浪微博
	 * @param thirdNum  第三方编号
	 * @return
	 */
	public TUserLogin saveUserLogin(String userNum,Integer thirdType,String thirdNum ){
		TUserLogin userLogin=new TUserLogin("", userNum, thirdNum, thirdType);
		save(userLogin);
		userLogin.setUserLoginNum(NumUtils.getCommondNum(NumUtils.PIX_USER_LOGIN, userLogin.getUserLoginId()));
		return userLogin;
	}
	
	/**
	 * 功能描述: 获取用户的第三方登陆信息
	 *
	 * @author yangliu  2016年10月11日 上午10:27:54
	 * 
	 * @param userNum
	 * @return
	 */
	public List<TUserLogin> getUserLoginByUserNum(String userNum){
		return super.getAllByCondition(TUserLogin.class, "userNum", userNum);
	}
	
	/**
	 * 功能描述: 删除第三方登陆
	 *
	 * @author yangliu  2016年10月11日 上午10:28:09
	 * 
	 * @param userNum 用户编号
	 * @param thirdType 类型  1-QQ 2-微信 3-新浪微博
	 */
	public void deleteUserLogin(String userNum,Integer thirdType){
		TUserLogin userLogin=super.get(TUserLogin.class, new String[]{"thirdType","userNum"}, new Object[]{thirdType,userNum});
		super.delete(userLogin);
	}
	/**
	 * 功能描述:查询是否绑定
	 * @author cqm  2016-12-1 上午11:53:35
	 * @param thirdNum
	 * @param userNum
	 * @return
	 */
    public TUserLogin getUserLoginDetails(String thirdNum,boolean isRequired){
    	TUserLogin bean = get(TUserLogin.class,"thirdNum", thirdNum);
		if(isRequired && bean!=null)
			throw new RuanYunException("已绑定其它账号");
		return bean;
	}
    

}
