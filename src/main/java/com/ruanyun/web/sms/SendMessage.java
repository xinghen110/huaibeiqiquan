package com.ruanyun.web.sms;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ruanyun.common.utils.EmptyUtils;
/**
 * 
 *  #(c) IFlytek shouhou <br/>
 *
 *  版本说明: $id:$ <br/>
 *
 *  功能说明: 发送短息
 * 
 *  <br/>创建说明: 2013-11-21 上午11:20:18 yangliu  创建文件<br/>
 * 
 *  修改历史:<br/>
 *
 */
@Service("sendMessage")
public class SendMessage {
	
	
	
	/**
	 * 功能描述:短信验证码
	 * @author cqm  2017-7-18 下午06:10:23
	 * @param linkTel
	 * @return
	 * @throws ClientException 
	 */
    @RequestMapping("doAliyunSendSms")
	public static int doAliyunSend(String linkTel,Integer random) throws ClientException{
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "600000");
		System.setProperty("user.timezone","GMT +08");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
		//替换成你的AK
		final String accessKeyId = "LTAIQkrgYLl6aCsb";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "KriijbUNN36JAXmGpFxQK7rz0TBzG1";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		
		 SendSmsRequest request = new SendSmsRequest();
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request.setPhoneNumbers(linkTel);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("金点");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode("SMS_127161548");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 request.setTemplateParam("{\"code\":\""+random+"\"}");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//执行成功
			System.out.println("====");
		}
		return 1;
		
	}
    /**
	 * 功能描述:短信验证码
	 * @author cqm  2017-7-18 下午06:10:23
	 * @param linkTel
	 * @return
	 * @throws ClientException 
	 */
    @RequestMapping("doAliyunSendSuccess")
	public static int doAliyunSendSuccess(String linkTel,String random) throws ClientException{
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "600000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
		//替换成你的AK
		final String accessKeyId = "LTAIQkrgYLl6aCsb";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "KriijbUNN36JAXmGpFxQK7rz0TBzG1";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		
		 SendSmsRequest request = new SendSmsRequest();
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request.setPhoneNumbers(linkTel);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("金点");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode("SMS_127161548");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 request.setTemplateParam("{\"code\":\""+random+"\"}");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//执行成功
			System.out.println("====");
		}
		return 1;
		
	}

	/**
	 * 功能描述:用户申请方案审批通过短信
	 * @author cqm  2017-7-18 下午06:10:23
	 * @param linkTel
	 * @return
	 * @throws ClientException
	 */
	@RequestMapping("doApprovedSendSms")
	public static int doApprovedSend(String linkTel,String mtname, String submittime) throws ClientException{
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "600000");
		System.setProperty("user.timezone","GMT +08");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
		//替换成你的AK
		final String accessKeyId = "LTAIG7v2dsyCOv7q";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "ioGvbsAkDJA1NbXmUTm11l6ZX8BdXl";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
				accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象

		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(linkTel);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName("鼎越护航");
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_130215210");
		//可选:模板中的变量替换JSON串,如模板内容为"您的${mtname}申请已于${submittime}审批通过，特此通知。"
		request.setTemplateParam("{\"mtname\":\""+mtname+"\",\"submittime\":\""+submittime+"\"}");
		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//执行成功
			System.out.println("====");
		}
		return 1;

	}
	/**
	 * 功能描述:用户申请方案审批驳回短信
	 * @author cqm  2017-7-18 下午06:10:23
	 * @param linkTel
	 * @return
	 * @throws ClientException
	 */
	@RequestMapping("doRejectSendSms")
	public static int doRejectSend(String linkTel,String mtname) throws ClientException{
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "600000");
		System.setProperty("user.timezone","GMT +08");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
		//替换成你的AK
		final String accessKeyId = "LTAIQkrgYLl6aCsb";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "KriijbUNN36JAXmGpFxQK7rz0TBzG1";//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
				accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		//组装请求对象

		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(linkTel);
		//必填:短信签名-可在短信控制台中找到
		request.setSignName("金点");
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_129757714");
		//可选:模板中的变量替换JSON串,模板内容为"您的${mtname}申请未通过，请您及时处理。"
		request.setTemplateParam("{\"mtname\":\""+mtname+"\"}");
		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			//执行成功
			System.out.println("====");
		}
		return 1;

	}
	
	 
	
	public static void main(String[] args) throws IOException, ClientException {
		//System.out.println(doAliyunSend("13966005078", 2221));
		System.out.println(doRejectSend("18931008313", "股票1"));
		System.out.println(doApprovedSend("18931008313", "股票1", "2018-04-01"));
		//System.out.println(batchSend("13705604031", "您好，您的验证码是123456"));
	}
	
	 
}
