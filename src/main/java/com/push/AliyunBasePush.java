package com.push;


import org.junit.BeforeClass;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ruanyun.web.util.AliyunAdvancedPush;


/**
 * 推送的OpenAPI文档 https://help.aliyun.com/document_detail/mobilepush/api-reference/openapi.html
 */
public class AliyunBasePush {
    private static final String REGION_HANGZHOU = "cn-hangzhou";
    private static final String ACCESS_KEY_ID="aeXLQb3LBPpj6avE";  //# 用于鉴权的AK 阿里云官方控制台获取
    private static final String ACCESS_KEY_SECRET="ULgGvjEy2mxbE63AkRFOYubE8uDhK1";  //# 用于鉴权的AK 阿里云官方控制台获取
    public static final Long APPKEY_WULIU=23537634L; // 物流端
    
    private AliyunAdvancedPush  aliyunAdvancedPush;
    /**
     * 推送类型 设备
     */
    public static final String TARGET_DEVICE="device"; 
    /**
     * 推送类型 账号
     */
    public static final String TARGET_ACCOUNT="account";
    /**
     * 推送类型 自定义
     */
    public static final String TARGET_TAG="tag";
    /**
     * 推送类型 所有
     */
    public static final String TARGET_ALL="all";
    /**
     * 推送所有的填写的值
     */
    public static final String TARGET_VALUE_ALL="all";
    
    /**
     * 推送 设备类型 0 IOS
     */
    public static final Integer DEVICE_TYPE_IOS=0;
    /**
     * 推送 设备类型 1 安卓
     */
    public static final Integer DEVICE_TYPE_ANDROID=1;
    /**
     * 推送设置 3 所有
     */
    public static final Integer DEVICE_TYPE_ALL=3;
    /**
     * 推送类型 0 消息
     */
    public static final Integer TYPE_0=0;
    /**
     * 	推送类型 1 通知
     */
    public static final Integer TYPE_1=1;
    
    private static DefaultAcsClient client;

    /**
     * 从配置文件中读取配置值，初始化Client
     * <p>
     * 1. 如何获取 accessKeyId/accessKeySecret/appKey 照见README.md 中的说明<br/>
     * 2. 先在 push.properties 配置文件中 填入你的获取的值
     */
  public DefaultAcsClient getClient(){
	  if(client==null){
		  IClientProfile profile = DefaultProfile.getProfile(REGION_HANGZHOU, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		  client = new DefaultAcsClient(profile);
      }
	  return client;
  }

}
