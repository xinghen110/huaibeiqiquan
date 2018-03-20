package com.ruanyun.zf;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author zhangwei
 * @description：
 * @create 2018-02-24 14:56
 **/
public class PayUtil {


    public static String signByPrivateKey(String data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateK);
        signature.update(data.getBytes("utf-8"));
        return Base64.encode(signature.sign()).replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\r", "");
    }


    public static boolean validateSignByPublicKey(String paramStr, String publicKey, String signedData) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicK);
        signature.update(paramStr.getBytes("utf-8"));
        return signature.verify(Base64.decode(signedData));
    }
}
