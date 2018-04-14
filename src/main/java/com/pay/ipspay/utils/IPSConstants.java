package com.pay.ipspay.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class IPSConstants {

    private static Logger log = LoggerFactory.getLogger(IPSConstants.class);
    
    private String MERCODE;
    private String ACCOUNT;
    private String GATEWAY_URL;
    private String Merchanturl;
    private String FailUrl;
    private String ServerUrl;
    private String DIRECT_STR;
    private String RSA_PUB;

    public IPSConstants() {
        Properties properties = new Properties();

        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in ;
        // 使用properties对象加载输入流
        try {
            in = IPSConstants.class.getClassLoader().getResourceAsStream("/ipspay.properties");
            properties.load(in);
            log.warn("properties:"+ JSONObject.fromObject(properties).toString());

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        //获取key对应的value值
        MERCODE = properties.getProperty("ipspay.MERCODE");
        ACCOUNT = properties.getProperty("ipspay.ACCOUNT");
        GATEWAY_URL = properties.getProperty("ipspay.GATEWAY_URL");
        Merchanturl = properties.getProperty("ipspay.Merchanturl");
        FailUrl = properties.getProperty("ipspay.FailUrl");
        ServerUrl = properties.getProperty("ipspay.ServerUrl");
        DIRECT_STR = properties.getProperty("ipspay.DIRECT_STR");
        RSA_PUB = properties.getProperty("ipspay.RSA_PUB");
    }

    public static void main(String args[]){
        IPSConstants ip = new IPSConstants();
        System.out.println("success!");
    }

    public String getMERCODE() {
        return MERCODE;
    }

    public String getACCOUNT() {
        return ACCOUNT;
    }

    public String getGATEWAY_URL() {
        return GATEWAY_URL;
    }

    public String getMerchanturl() {
        return Merchanturl;
    }

    public String getFailUrl() {
        return FailUrl;
    }

    public String getServerUrl() {
        return ServerUrl;
    }

    public String getDIRECT_STR() {
        return DIRECT_STR;
    }

    public String getRSA_PUB() {
        return RSA_PUB;
    }
}
