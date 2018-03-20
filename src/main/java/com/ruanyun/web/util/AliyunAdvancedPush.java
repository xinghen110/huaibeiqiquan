package com.ruanyun.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.push.model.v20150827.PushRequest;
import com.aliyuncs.push.model.v20150827.PushResponse;
import com.google.gson.JsonObject;
import com.push.AliyunBasePush;
import com.push.PushModel;
@Service
public class AliyunAdvancedPush extends AliyunBasePush {
		
	
	/**
	 * 功能描述: 发送 推送
	 *
	 * @author yangliu  2016年11月19日 下午4:12:41
	 * 
	 * @param pushModel 推送对象
	 */
	public void pushNotice(PushModel pushModel){
		 PushRequest pushRequest = new PushRequest();
		 pushRequest.setAppKey(pushModel.getAppKey());
	     pushRequest.setTarget(pushModel.getTarget()); //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
	     pushRequest.setTargetValue(pushModel.getTargetValue()); //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
	     pushRequest.setDeviceType(3); // 设备类型deviceType 取值范围为:0~3. iOS设备: 0; Android设备: 1; 全部: 3, 这是默认值.
	     pushRequest.setType(pushModel.getType()); // 0:表示消息(默认为0), 1:表示通知
	     pushRequest.setTitle(pushModel.getTitle()); // 消息的标题
	     pushRequest.setBody(pushModel.getBody()); // 消息的内容
	     pushRequest.setSummary(pushModel.getSummary()); // 通知的摘要
	     pushRequest.setStoreOffline(true);
	     // 推送配置: iOS
         pushRequest.setiOSBadge("5"); // iOS应用图标右上角角标
         pushRequest.setiOSMusic("sub.caf"); // iOS通知声音
         pushRequest.setApnsEnv("PRODUCT");
         pushRequest.setAndroidOpenType("1"); // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url 4 : 无跳转逻辑
         pushRequest.setAndroidOpenType("1"); // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url 4 : 无跳转逻辑
	     try {
			PushResponse pushResponse = getClient().getAcsResponse(pushRequest);
			System.out.println(JSONObject.fromObject(pushResponse).toString());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	     System.out.println("推送成功");
	}
	
	
	
	/**
	 * 功能描述:推送消息
	 * @author cqm  2016-12-14 下午04:09:18
	 * @param userNum
	 * @return
	 */
	public void push(String userNum){
		//推送消息
        PushModel model = new PushModel(AliyunBasePush.APPKEY_WULIU,AliyunBasePush.TARGET_ACCOUNT,AliyunBasePush.DEVICE_TYPE_ALL,AliyunBasePush.TYPE_1);
		model.setTargetValue(userNum);
		model.setTitle("你有一条运单消息！");
		model.setBody("你有一条新的物流消息,请注意查收！");
		model.setSummary("你有一条新的物流消息,请注意查收！");
		pushNotice(model);
	}
	
	@Test
	public  void test() {
		PushModel model = new PushModel(AliyunBasePush.APPKEY_WULIU,AliyunBasePush.TARGET_ACCOUNT,3,AliyunBasePush.TYPE_1);
			model.setTargetValue("wl67120000010864");
			model.setTitle("你有一条新消息！");
			model.setBody("你有一条新的物流消息,请注意查收！");
			model.setSummary("你有一条新的物流消息,请注意查收！");
			pushNotice(model);
	}
	
	
	 /**
     * 推送高级接口
     * <p>
     * 参见文档 https://help.aliyun.com/document_detail/mobilepush/api-reference/push-advanced.html
     */
    public void testPushNoticeToAllDeviceTypeAndAllDevice(Long appKey,String target,String targetValue) throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        final String date = dateFormat.format(new Date());
        PushRequest pushRequest = new PushRequest();

        // 推送目标
        pushRequest.setAppKey(appKey);
        pushRequest.setTarget(target); //推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
        pushRequest.setTargetValue("all"); //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
        pushRequest.setDeviceType(3); // 设备类型deviceType 取值范围为:0~3. iOS设备: 0; Android设备: 1; 全部: 3, 这是默认值.


        // 推送配置
        pushRequest.setType(1); // 0:表示消息(默认为0), 1:表示通知
        pushRequest.setTitle(date); // 消息的标题
        pushRequest.setBody("PushRequest body"); // 消息的内容
        pushRequest.setSummary("PushRequest summary"); // 通知的摘要
        // 推送配置: iOS
        pushRequest.setiOSBadge("5"); // iOS应用图标右上角角标
        pushRequest.setiOSMusic("default"); // iOS通知声音
		pushRequest.setiOSTitle("iOS 10 Title");//iOS通知标题(iOS 10+)
		pushRequest.setiOSSubtitle("iOS 10 Subtitle");//iOS通知副标题(iOS 10+)
		pushRequest.setiOSMutableContent(true);//使能通知扩展处理(iOS 10 +)
		pushRequest.setiOSNotificationCategory("test_category");//设定通知Catetory(iOS 10+)
        pushRequest.setiOSExtParameters("{\"attachment\":\"https://xxxx.xxx/notification_pic.png\",\"k2\":\"v2\"}"); //自定义的kv结构,开发者扩展用 针对iOS设备 iOS 10+用attachment可以指定富媒体推送通知的资源Url
        pushRequest.setApnsEnv("DEV");
        //pushRequest.setRemind(false); // 当APP不在线时候，是否通过通知提醒
        // 推送配置: Android
        //设置该参数后启动小米托管弹窗功能，此处指定通知点击后跳转的Activity（托管弹窗的前提条件：1. 继承小米辅助通道；2. storeOffLine设为true
        //pushRequest.setXiaomiActivity("_Your_XiaoMi_Activity_");
        pushRequest.setAndroidOpenType("3"); // 点击通知后动作,1:打开应用 2: 打开应用Activity 3:打开 url 4 : 无跳转逻辑
        pushRequest.setAndroidOpenUrl("http://www.baidu.com"); // Android收到推送后打开对应的url,仅仅当androidOpenType=3有效
        pushRequest.setAndroidExtParameters("{\"k1\":\"android\",\"k2\":\"v2\"}"); // 设定android类型设备通知的扩展属性


        // 推送控制
        //final Date pushDate = new Date(System.currentTimeMillis() + 30 * 1000); // 30秒之间的时间点, 也可以设置成你指定固定时间
        //final String pushTime = ParameterHelper.getISO8601Time(pushDate);
        // pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        //pushRequest.setStoreOffline(false); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        //final String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
        //pushRequest.setExpireTime(expireTime);
        //pushRequest.setBatchNumber("100010"); // 批次编号,用于活动效果统计. 设置成业务可以记录的字符串

        PushResponse pushResponse = getClient().getAcsResponse(pushRequest);
        System.out.printf("RequestId: %s, ResponseId: %s\n",
                pushResponse.getRequestId(), pushResponse.getResponseId());
    }
}


