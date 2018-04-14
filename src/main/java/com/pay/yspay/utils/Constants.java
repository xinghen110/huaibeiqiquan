package com.pay.yspay.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Constants {
    private String PATH_YSEPAY_PUBLIC_CERT;
    private String YSEPAY_GATEWAY_URL;
    private String PATH_PARTER_PKCS12;
    private String PASSWORD_PARTNER_PKCS12;
    private String RSA_ALGORITHM;
    private String PARTNER_ID;
    private String DEFAULT_CHARSET;
    private String SIGN_ALGORITHM;
    private String NOTIFY_URL;
    private String RETURN_URL;
    private String VERSION;
    private String SELLER_NAME = "河北臻致电子商务有限公司";
    private String CHARGE_RATE;

    private static Logger log = LoggerFactory.getLogger(Constants.class);

    public String msg;

    public Constants(){
        Properties properties = new Properties();

        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in ;
        // 使用properties对象加载输入流
        try {
            in = Constants.class.getClassLoader().getResourceAsStream("/yspay.properties");
            properties.load(in);
            log.warn("properties:"+JSONObject.fromObject(properties).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取key对应的value值
        PATH_YSEPAY_PUBLIC_CERT = properties.getProperty("yspay.PATH_YSEPAY_PUBLIC_CERT");
        YSEPAY_GATEWAY_URL = properties.getProperty("yspay.YSEPAY_GATEWAY_URL");
        PATH_PARTER_PKCS12 = properties.getProperty("yspay.PATH_PARTER_PKCS12");
        PASSWORD_PARTNER_PKCS12 = properties.getProperty("yspay.PASSWORD_PARTNER_PKCS12");
        RSA_ALGORITHM = properties.getProperty("yspay.RSA_ALGORITHM");
        PARTNER_ID = properties.getProperty("yspay.PARTNER_ID");
        DEFAULT_CHARSET = properties.getProperty("yspay.DEFAULT_CHARSET");
        SIGN_ALGORITHM = properties.getProperty("yspay.SIGN_ALGORITHM");
        NOTIFY_URL = properties.getProperty("yspay.NOTIFY_URL");
        RETURN_URL = properties.getProperty("yspay.RETURN_URL");
        VERSION = properties.getProperty("yspay.VERSION");
        CHARGE_RATE = properties.getProperty("yspay.CHARGE_RATE");

    }

    public static void main(String args[]){
        Properties properties = new Properties();

        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in ;
        // 使用properties对象加载输入流
        try {
            //in = IPSConstants.class.getClassLoader().getResourceAsStream("../../../../../../WEB-INF/classes/yspay.properties");
            URL resource = Constants.class.getClassLoader().getResource("/");
            System.out.println(resource.toString());
            //properties.load(in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getPATH_YSEPAY_PUBLIC_CERT() {
        return PATH_YSEPAY_PUBLIC_CERT;
    }

    public void setPATH_YSEPAY_PUBLIC_CERT(String PATH_YSEPAY_PUBLIC_CERT) {
        this.PATH_YSEPAY_PUBLIC_CERT = PATH_YSEPAY_PUBLIC_CERT;
    }

    public String getYSEPAY_GATEWAY_URL() {
        return YSEPAY_GATEWAY_URL;
    }

    public void setYSEPAY_GATEWAY_URL(String YSEPAY_GATEWAY_URL) {
        this.YSEPAY_GATEWAY_URL = YSEPAY_GATEWAY_URL;
    }

    public String getPATH_PARTER_PKCS12() {
        return PATH_PARTER_PKCS12;
    }

    public void setPATH_PARTER_PKCS12(String PATH_PARTER_PKCS12) {
        this.PATH_PARTER_PKCS12 = PATH_PARTER_PKCS12;
    }

    public String getPASSWORD_PARTNER_PKCS12() {
        return PASSWORD_PARTNER_PKCS12;
    }

    public void setPASSWORD_PARTNER_PKCS12(String PASSWORD_PARTNER_PKCS12) {
        this.PASSWORD_PARTNER_PKCS12 = PASSWORD_PARTNER_PKCS12;
    }

    public String getRSA_ALGORITHM() {
        return RSA_ALGORITHM;
    }

    public void setRSA_ALGORITHM(String RSA_ALGORITHM) {
        this.RSA_ALGORITHM = RSA_ALGORITHM;
    }

    public String getPARTNER_ID() {
        return PARTNER_ID;
    }

    public void setPARTNER_ID(String PARTNER_ID) {
        this.PARTNER_ID = PARTNER_ID;
    }

    public String getDEFAULT_CHARSET() {
        return DEFAULT_CHARSET;
    }

    public void setDEFAULT_CHARSET(String DEFAULT_CHARSET) {
        this.DEFAULT_CHARSET = DEFAULT_CHARSET;
    }

    public String getSIGN_ALGORITHM() {
        return SIGN_ALGORITHM;
    }

    public void setSIGN_ALGORITHM(String SIGN_ALGORITHM) {
        this.SIGN_ALGORITHM = SIGN_ALGORITHM;
    }

    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }

    public String getRETURN_URL() {
        return RETURN_URL;
    }

    public void setRETURN_URL(String RETURN_URL) {
        this.RETURN_URL = RETURN_URL;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getSELLER_NAME() {
        return SELLER_NAME;
    }

    public void setSELLER_NAME(String SELLER_NAME) {
        this.SELLER_NAME = SELLER_NAME;
    }

    public String getCHARGE_RATE() {
        return CHARGE_RATE;
    }

    public void setCHARGE_RATE(String CHARGE_RATE) {
        this.CHARGE_RATE = CHARGE_RATE;
    }
}

