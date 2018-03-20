package com.ruanyun.web.service.sms;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.sms.SmsDao;
import com.ruanyun.web.model.mall.TSmsCount;
import com.ruanyun.web.sms.SendMessage;

import net.sf.json.JSONObject;
@Repository
public class SmsService extends BaseServiceImpl<TSmsCount>{
	
	@Autowired
	@Qualifier("smsDao")
	private SmsDao smsDao;
	
	/**
	 * 功能描述: 保存发送短信记录
	 *
	 * @author yangliu  2013-11-21 上午11:01:35
	 * 
	 * @param orderNumber 订单编号
	 * @param content 内容
	 * @param phone 电话号码
	 * @param result 结果
	 */
	public void saveSmsThread(String userNum,String content,String phone,Integer sendStatus){
		TSmsCount sms =new TSmsCount();
		sms.setCreateTime(new Date());
		sms.setLinkTel(phone);
		sms.setSendCount(1);
		sms.setSendStatus(sendStatus);
		sms.setUserNum(userNum);
		smsDao.saveSms(sms);
	}
	
	public List<TSmsCount> queryList(){
		return smsDao.queryList();
	}
	
//	@Transactional
//	public void sendMsg(){
//		List<TSmsCount> list=queryList();
//		for(TSmsCount sms : list){
//			int result=0;
//			JSONObject object=JSONObject.fromObject(sms.getSendContent());
//			if(object.containsKey("msgType")&&"1".equals(object.getString("msgType"))){
//				result=SendMessage.doSendMessage(sms.getLinkTel(), object.getString("title"),object.getString("WINCODE"));
//			}
//			sms.setSendCount(sms.getSendCount()+1);
//			if(result>0){
//				sms.setSendStatus(1);
//			}else{
//				sms.setSendStatus(2);
//			}
//			update(sms);
//		}
//	}

}
