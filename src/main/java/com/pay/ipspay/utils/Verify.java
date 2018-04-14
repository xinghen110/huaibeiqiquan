package com.pay.ipspay.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.openssl.PEMReader;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Verify {

    private static boolean verifyMD5withRSA(String publicKey, String data, String sign)
    {
        return verifyMD5withRSA(publicKey, data, sign, "UTF-8");
    }

    private static boolean verifyMD5withRSA(String publicKey, String data, String sign, String charset)
    {
        try
        {
            PublicKey publicKeyObj = loadPublicKey(publicKey);
            return verify(publicKeyObj, getBytes(data, charset), Hex.decodeHex(sign.toCharArray()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static PublicKey loadPublicKey(String publicKey)
    {
        try
        {
            PEMReader reader = new PEMReader(new StringReader(publicKey));
            return (PublicKey)reader.readObject();
        } catch (Exception ex) {
            throw new RuntimeException("load publicKey error:", ex);
        }
    }

    private static boolean verify(PublicKey publicKey, byte[] data, byte[] sign)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] getBytes(String content, String charset) {
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNULL(String str) {
        return str == null;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static
    {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 获取报文中<Amount></Amount>部分
     */
    public static String getAmount(String xml) {
        int s_index = xml.indexOf("<Amount>");
        int e_index = xml.indexOf("</Amount>");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index + 8, e_index);
        }
        return sign;
    }

    /**
     * 获取报文中<Date></Date>部分
     */
    public static String getDate(String xml) {
        int s_index = xml.indexOf("<Date>");
        int e_index = xml.indexOf("</Date>");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index + 6, e_index);
        }
        return sign;
    }

    /**
     * 获取报文中<Signature></Signature>部分
     */
    private static String getSign(String xml) {
        int s_index = xml.indexOf("<Signature>");
        int e_index = xml.indexOf("</Signature>");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index + 11, e_index);
        }
        return sign;
    }

    /**
     * 获取body部分
     */
    private static String getBodyXml(String xml) {
        int s_index = xml.indexOf("<body>");
        int e_index = xml.indexOf("</body>");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index, e_index + 7);
        }
        return sign;
    }

    /**
     * 获取报文中<RspCode></RspCode>部分
     */
    public static String getRspCode(String xml) {
        int s_index = xml.indexOf("<RspCode>");
        int e_index = xml.indexOf("</RspCode>");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index + 9, e_index);
        }
        return sign;
    }

    /**
     * 获取报文中<Status></Status>部分
     */
    public static String getStatus(String xml) {
        int s_index = xml.indexOf("<Status>");
        int e_index = xml.indexOf("</Status>");
        String sign = "";
        if (s_index > 0) {
            sign = xml.substring(s_index + 8, e_index);
        }

        if (sign.equals("Y")) {
            System.out.println("交易成功");
        } else if (sign.equals("N")) {
            System.out.println("交易失败");
        } else if (sign.equals("P")) {
            System.out.println("交易处理中");
        }

        return sign;
    }

    /**
     * 获取报文中<RetEncodeType></RetEncodeType>部分
     */
    private static String getRetEncodeType(String xml) {
        int s_index = xml.indexOf("<RetEncodeType>");
        int e_index = xml.indexOf("</RetEncodeType>");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index + 15, e_index);
        }
        return sign;
    }

    /**
     * 获取报文中<RetEncodeType></RetEncodeType>部分
     */
    public static String getValueByTag(String xml, String tag) {
        int s_index = xml.indexOf("<"+tag+">");
        int e_index = xml.indexOf("</"+tag+">");
        String sign = null;
        if (s_index > 0) {
            sign = xml.substring(s_index + tag.length() + 2, e_index);
        }
        return sign;
    }

    /**
     * 验签
     *
     */
    public static boolean checkSign(String xml, String merCode, String directStr, String ipsRsaPub) {

        if (xml == null){
            return false;
        }
        String OldSign = Verify.getSign(xml); // 返回签名
        String text = Verify.getBodyXml(xml); // body
        System.out.println("MD5验签，验签文：" + text + "\n待比较签名值:" + OldSign);
        String retEncodeType =  Verify.getRetEncodeType(xml); //加密方式
        System.out.println("加密方式 ：" + retEncodeType);
        String result;
        if (OldSign == null || retEncodeType == null) {
            return false;
        }
        // 根据验签方式进行验签
        if (retEncodeType.equals("16")){
            return Verify.verifyMD5withRSA(ipsRsaPub, text + merCode, OldSign);
        } else if (retEncodeType.equals("17")){
            result = DigestUtils
                    .md5Hex(Verify.getBytes(text + merCode + directStr,
                            "UTF-8"));
        } else {
            return false;
        }
        if (result == null || !OldSign.equals(result)) {
            return false;
        }
        return true;
    }
}
