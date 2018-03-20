package com.ruanyun.web.service.mall;

import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.common.utils.CommonUtils;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.Exception.RuanYunException;
import com.ruanyun.web.dao.mall.SmsInfoDao;
import com.ruanyun.web.dao.sys.UserDao;
import com.ruanyun.web.model.mall.TOrderMeal;
import com.ruanyun.web.model.mall.TSmsInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.util.NumUtils;

@Service("smsInfoService")
public class SmsInfoService extends BaseServiceImpl<TSmsInfo> {
	 @Autowired
	 private SmsInfoDao smsInfoDao;
	 @Autowired
	 private UserDao userDao;
 
	 @Autowired
	 private UserService userService;
	 
	 public Page<TSmsInfo> getList(Page<TSmsInfo> page,TSmsInfo smsInfo,TUser user){
		 Page<TSmsInfo> _page = smsInfoDao.queryPage(page,smsInfo,user);
			String userNums=CommonUtils.getAttributeValue(TSmsInfo.class, _page.getResult(), "userNum");
			Map<String,TUser> userMap=userDao.getUserByUserNums(userNums);
			CommonUtils.setAttributeValue(TSmsInfo.class,  _page.getResult(), userMap, "userNum", "user");
			return _page;
	 }
	 
	 /**
	  * 功能描述:查询消息列表App
	  * @author cqm  2016-12-2 下午04:40:23
	  * @param page
	  * @param smsInfo
	  * @param user
	  * @return
	  */
	 public Page<TSmsInfo> getListApp(Page<TSmsInfo> page,TSmsInfo smsInfo,TUser user){
		 Page<TSmsInfo> _page = smsInfoDao.queryPage(page,smsInfo,user);
		 return _page;
	 }
	 
 
	 
	 
	/* *//**
	  * 功能描述:修改
	  * @author cqm  2016-12-6 下午03:12:40
	  * @param smsInfo
	  * @param orderNum
	  * @return
	  *//*
	 public Integer updateSmsInfo(TSmsInfo smsInfo){
		 TSmsInfo oldSmsInfo = getSmsInfo(smsInfo.getOrderExpressNum(), smsInfo.getUserNum(), true);
		 if(EmptyUtils.isNotEmpty(smsInfo.getTitle())){
			 oldSmsInfo.setTitle(smsInfo.getTitle());
		 }
		 oldSmsInfo.setCreateTime(new Date());
		 update(oldSmsInfo);
		 return 1;
	 }*/
	 
	 /**
	  * 功能描述:查询
	  * @author cqm  2016-12-6 下午03:15:56
	  * @param orderExpressNum
	  * @param userNum
	  * @param isRequired
	  * @return
	  */
	public TSmsInfo getSmsInfo(String orderExpressNum,String userNum,boolean isRequired){
		TSmsInfo smsInfo=this.get(TSmsInfo.class, new String[] { "orderExpressNum","userNum" }, new Object[] { orderExpressNum,userNum });
		if(isRequired && smsInfo==null){
			throw new RuanYunException("物流消息不存在");
		}
		return smsInfo;
		}
	



	
	/**
	 * 功能描述:更新消息为已送达
	 * @author cqm  2017-4-26 上午09:09:05
	 * @param userNum
	 * @param orderExpressNum
	 */
	public void updateSmsInfo(String userNum,String orderExpressNum,String title,String content){
		smsInfoDao.updateSmsInfo(userNum, orderExpressNum,title,content);
	}
	/**
	 * 功能描述:添加消息
	 * @author cqm  2017-8-30 下午03:06:39
	 * @param userNum 接受人
	 * @param title 
	 * @param sendUserNum 发送人
	 * @param smsType
	 */
	public void saveSmsInfo(String userNum,String title,String sendUserNum,String smsType){
		TSmsInfo smsInfo =new TSmsInfo();
		smsInfo.setUserNum(userNum);
		smsInfo.setSendUserNum(sendUserNum);
		smsInfo.setSmsType(smsType);
		smsInfo.setTitle(title);
		smsInfo.setContent(title);
		smsInfo.setCreateTime(new Date());
		save(smsInfo);
		smsInfo.setSmsInfoNum(NumUtils.getCommondNum(NumUtils.PIX_SMS_INFO, smsInfo.getSmsInfoId()));
		update(smsInfo);
	}

	public List getAll(TSmsInfo smsInfo) {
		return smsInfoDao.getAll(smsInfo);
	}
}
