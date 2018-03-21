package com.pay.yspay.utils;


import com.google.common.io.BaseEncoding;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.*;

/**
 * 签名验签工具
 *
 * @author chang
 *
 */
public class SignUtils {

    public static Constants constants = new Constants();

    // 缓存公钥和私钥
    public static Map<String, Object> certMap = new java.util.concurrent.ConcurrentHashMap<String, Object>();

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || StringUtils.isEmpty(value) || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 遍历以及根据重新排序
     *
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 签名
     *
     */
    public static String rsaSign(String content, String charset, InputStream pfxCertFileInputStream) throws Exception {
        try {
            BaseEncoding baseEncoding = BaseEncoding.base64();

            System.out.println("进入签名方法：content[" + content + "], charset[" + charset + "]");

            PrivateKey priKey = getPrivateKeyFromPKCS12(constants.getPASSWORD_PARTNER_PKCS12(), pfxCertFileInputStream);

            java.security.Signature signature = java.security.Signature.getInstance(constants.getRSA_ALGORITHM());

            signature.initSign(priKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();

            String sign = new String(baseEncoding.encode(signed).getBytes(), charset);

            System.out.println("进入签名完：content[" + content + "], charset[" + charset + "], sign[" + sign + "]");

            return sign;
        } catch (Exception e) {
            System.out.println("签名失败：content[" + content + "], charset[" + charset + "]");
            throw new Exception("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    /**
     * 把参数签名
     */
    public static String rsaSign(Map<String, String> params, String charset, InputStream pfxCertFileInputStream)
            throws Exception {
        String signContent = getSignContent(params);

        return rsaSign(signContent, charset, pfxCertFileInputStream);
    }

    public static boolean rsaCheckContent(InputStream publicCertFileInputStream, Map<String, String> params,
                                          String sign, String charset) throws Exception {
        String content = StringUtils.createLinkString(SignUtils.paraFilter(params));

        return rsaCheckContent(publicCertFileInputStream, content, sign, charset);
    }

    /**
     * 验签
     */
    public static boolean rsaCheckContent(InputStream publicCertFileInputStream, String content, String sign,
                                          String charset) throws Exception {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println("进入验证签名方法: content[" + content + "], sign[" + sign + "], charset[" + charset + "]");
        boolean bFlag = false;
        try {
            java.security.Signature signetcheck = java.security.Signature.getInstance(constants.getRSA_ALGORITHM());
            System.out.println("获取签名方法成功"+signetcheck);
            signetcheck.initVerify(getPublicKeyFromCert(publicCertFileInputStream));
            System.out.println("获取证书公钥成功");
            signetcheck.update(content.getBytes(charset));
            System.out.println("更新待签名数据成功");
            if (signetcheck.verify(baseEncoding.decode(new String(sign.getBytes(charset))))) {
                // 跑不进条件语句里面
                bFlag = true;
                System.out.println("解密成功");
                System.out.println("sign:" + baseEncoding.decode(new String(sign.getBytes(charset))).toString());
            }
            System.out.println("进入验证签名方法: content[" + content + "], sign[" + sign + "], charset[" + charset
                    + "], result[" + bFlag + "]");
        } catch (Exception e) {
            System.out.println("验证签名异常" + ": content[" + content + "], sign[" + sign + "], charset[" + charset + "]");
            throw new Exception("验证签名异常");
        }

        return bFlag;
    }

    /**
     * 读取公钥，x509格式
     */
    public static PublicKey getPublicKeyFromCert(InputStream ins) throws Exception {
        PublicKey pubKey = (PublicKey) certMap.get("PublicKey");
        if (pubKey != null) {
            return pubKey;
        }

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cac = cf.generateCertificate(ins);
            pubKey = cac.getPublicKey();
            certMap.put("PublicKey", pubKey);
        } catch (Exception e) {
            if (ins != null)
                ins.close();
            throw e;
        } finally {
            if (ins != null) {
                ins.close();
            }
        }

        return pubKey;
    }

    /**
     * 读取PKCS12格式的key（私钥）pfx格式
     */
    public static PrivateKey getPrivateKeyFromPKCS12(String password, InputStream ins) throws Exception {
        PrivateKey priKey = (PrivateKey) certMap.get("PrivateKey");
        if (priKey != null) {
            return priKey;
        }

        KeyStore keystoreCA = KeyStore.getInstance("PKCS12");
        try {
            // 读取CA根证书
            keystoreCA.load(ins, password.toCharArray());

            Enumeration<?> aliases = keystoreCA.aliases();
            String keyAlias = null;
            if (aliases != null) {
                while (aliases.hasMoreElements()) {
                    keyAlias = (String) aliases.nextElement();
                    // 获取CA私钥
                    priKey = (PrivateKey) (keystoreCA.getKey(keyAlias, password.toCharArray()));
                    if (priKey != null) {
                        certMap.put("PrivateKey", priKey);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            if (ins != null)
                ins.close();
            throw e;
        } finally {
            if (ins != null) {
                ins.close();
            }
        }

        return priKey;
    }

    //验证异步结果通知
    public static boolean AsyncNotifyCheck(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        PrintWriter out = null;

        request.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            String valueStr = "";

            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }
        }
        System.out.println(params.toString());
        try {
            boolean verifyResult = APIUtils.verifySign(request, params);
            System.out.println(verifyResult);
            out = resp.getWriter();
            //状态
            String trade_status = params.get("trade_status");
            if (verifyResult) {// 验证成功
                //////////////////////////////////////////////////////////////////////////////////////////
                System.out.println("异步通知验证签名成功");
                // 如果状态为TRADE_FINISHED
                if (trade_status.equals("TRADE_FINISHED")) {
                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    // 如果有做过处理，不执行商户的业务程序
                    out.println("success");// 请不要修改或删除
                    // 注意：
                    // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知

                    // 如果状态为TRADE_SUCCESS
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    // 如果有做过处理，不执行商户的业务程序
                    out.println("success");// 请不要修改或删除
                    // 注意：
                    // 付款完成后，支付宝系统发送该交易状态通知
                } else {
                    out.println("fail");// 请不要修改或删除
                }
                //////////////////////////////////////////////////////////////////////////////////////////
            } else {// 验证失败
                out.println("fail");
                System.out.println("异步通知验证签名失败");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.flush();
                out.close();
            }
        }
        return true;
    }
    // 验证同步结果通知
    public static boolean SyncReturnCheck(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        //把字符串转为json
//		JSONObject jsonObj = JSONObject.fromObject(sb.toString());

        //判断接口名称
//		String method =  subMethod(jsonObj);

//		String result = StringUtils.isEmpty(method)==true?"":jsonObj.get(method).toString();
//		String result = jsonObj.get("ysepay_online_trade_query_response").toString();
//		String sign = jsonObj.get("sign")==null?"":jsonObj.get("sign").toString();
//		String content = "{\"code\":\"10000\",\"msg\":\"Success\",\"out_trade_no\":\"201708081716038084d5d1cd6\",\"trade_status\":\"TRADE_ACCEPT_SUCCESS\",\"trade_status_description\":\"受理成功，请耐心等待异步通知结果\",\"total_amount\":\"0.01\",\"trade_no\":\"102170808339690348\",\"fee\":\"2.00\"}";
        String content = "{\"code\":\"10000\",\"msg\":\"Success\",\"out_trade_no\":\"2017092119261419261443474347\",\"trade_status\":\"TRADE_ACCEPT_SUCCESS\",\"trade_status_description\":\"受理成功，请耐心等待异步通知结果\",\"total_amount\":\"100.00\",\"trade_no\":\"102170921196514898\",\"fee\":\"0.01\"}";

        String sign = "iXaTBzuX6Y8iVeK/JwGTGmOr6IPrQqJB/M0qCtoYQHjb0WvrI1jC+dO7eh5GzeVqcC9m/oxnw1LOBfoC9vq8rVts8xn82SjAt8tTKK0m/+H8/QQxQJ1VP+dc6bU+bH51h7QrHjUoMY0FjbbvM4hkmz3qk4LNyePr2+C+6wq6uRc=";

        //验签
        boolean verifyResult = APIUtils.verifyJsonSign(request,sign,content, "UTF-8");

        if (verifyResult==true) {
            System.out.println("验签成功");
        } else {
            System.out.println("验证失败");
        }
        return verifyResult;

    }
}