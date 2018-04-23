package com.pay.yinshengpay.utils;

import com.pay.ipspay.utils.IPSConstants;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class YinshengPayConstants {

    private static Logger log = LoggerFactory.getLogger(YinshengPayConstants.class);

    private String version;         //接口版本号
    private String merchantId;      //商户ID
    private String merchantKey;     //key(密钥)
    private String merchantUrl;     //回调地址
    private String frontURL;        //前台地址
    private String requestUrl;      //提交跳转地址

    public YinshengPayConstants() {
        Properties properties = new Properties();

        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in ;
        // 使用properties对象加载输入流
        try {
            in = IPSConstants.class.getClassLoader().getResourceAsStream("/yinshengpay.properties");
            properties.load(in);
            log.warn("properties:"+ JSONObject.fromObject(properties).toString());

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        //获取key对应的value值
        version = properties.getProperty("yinshengPay.version");
        merchantId = properties.getProperty("yinshengPay.merchantId");
        merchantKey = properties.getProperty("yinshengPay.merchantKey");
        merchantUrl = properties.getProperty("yinshengPay.merchantUrl");
        frontURL = properties.getProperty("yinshengPay.frontURL");
        requestUrl = properties.getProperty("yinshengPay.requestUrl");
    }

    public String getVersion() {
        return version;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public String getMerchantUrl() {
        return merchantUrl;
    }

    public String getFrontURL() {
        return frontURL;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
