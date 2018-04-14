package com.pay.yspay.utils;


import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API主入口根据bean传值,取值,遍历签名,验签 最终生成返回
 * @author chang
 *
 */
public class APIUtils {
    public static Constants constants = new Constants();
    private static Logger log = LoggerFactory.getLogger(APIUtils.class);
    /**
     * API验证签名工具，把签名值，请求字符编码，返回结果(json body)传递过来进行验证签名 公钥验证签名
     * 用于验证银盛同步响应回来的参数
     */
    public static boolean verifyJsonSign(HttpServletRequest request, String sign, String responseBody, String charset) {
        ServletContext servletContext = request.getServletContext();
        InputStream publicCertFileInputStream = servletContext
                .getResourceAsStream(constants.getPATH_YSEPAY_PUBLIC_CERT());


        boolean isSign = false;
        try {
            isSign = SignUtils.rsaCheckContent(publicCertFileInputStream, responseBody, sign, charset);
        } catch (Exception e) {
            throw new RuntimeException("验证签名失败，请检查银盛公钥证书文件是否存在");
        }
        return isSign;
    }


    /**
     * 拼接请求网关参数
     */
    public static String buildRequest(HttpServletRequest request, Object obj) {
        Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
        System.out.println("--打印所有参数--"+sPara.toString());
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuilder sbHtml = new StringBuilder();

        sbHtml.append("正在跳转。。。<br/>" + "<form id=\"ysepaysubmit\" name=\"ysepaysubmit\" action=\"")
                .append(constants.getYSEPAY_GATEWAY_URL()).append("\" method = \"").append("post").append("\">");

        for (String name : keys) {
            String value = sPara.get(name);

            sbHtml.append("<input type=\"text\" name=\"").append(name).append("\" value=\"")
                    .append(StringEscapeUtils.escapeHtml4(value)).append("\"/><br/>");
        }

        sbHtml.append("<input type=\"submit\" value=\"" + "确定" + "\" style=\"display;\"></form>");
        sbHtml.append("<script>document.forms['ysepaysubmit'].submit();</script>");

        return sbHtml.toString();
    }

    /**
     * 拼接请求代付参数
     */
/*    public static String buildRequestdf(HttpServletRequest request, Object obj) {

        Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
        List<String> keys = new ArrayList<String>(sPara.keySet());
        System.out.println("--打印所有参数--"+sPara.toString());
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("正在跳转。。。<br/>" + "<form id=\"ysepaysubmit\" name=\"ysepaysubmit\" action=\"")
                .append(IPSConstants.YSEPAY_GATEWAY_URL_DF).append("\" method = \"").append("post").append("\">");

        for (String key : keys) {
            String name = (String) key;
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"text\" name=\"").append(name).append("\" value=\"")
                    .append(StringEscapeUtils.escapeHtml4(value)).append("\"/><br/>");
        }

        sbHtml.append("<input type=\"submit\" value=\"" + "确定" + "\" style=\"display;\"></form>");
        sbHtml.append("<script>document.forms['ysepaysubmit'].submit();</script>");

        return sbHtml.toString();

    }*/


    /**
     * 拼接请求代收参数
     */
/*    public static String buildRequestds(HttpServletRequest request, Object obj) {

        Map<String, String> sPara = buildRequestPara(request, getProperty(obj));
        List<String> keys = new ArrayList<String>(sPara.keySet());
        System.out.println("--打印所有参数--"+sPara.toString());
        StringBuilder sbHtml = new StringBuilder();

        sbHtml.append("正在跳转。。。<br/>" + "<form id=\"ysepaysubmit\" name=\"ysepaysubmit\" action=\"").append(IPSConstants.YSEPAY_GATEWAY_URL_DS).append("\" method = \"").append("post").append("\">");

        for (String key : keys) {
            String name = (String) key;
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"text\" name=\"").append(name).append("\" value=\"")
                    .append(StringEscapeUtils.escapeHtml4(value)).append("\"/><br/>");
        }

        sbHtml.append("<input type=\"submit\" value=\"" + "确定" + "\" style=\"display;\"></form>");
        sbHtml.append("<script>document.forms['ysepaysubmit'].submit();</script>");

        return sbHtml.toString();
    }*/


    /**
     * 获取证书路径并且签名
     */
    private static Map<String, String> buildRequestPara(HttpServletRequest request, Map<String, String> sParaTemp) {

        //除去数组中的空值和签名参数
        Map<String, String> sPara = SignUtils.paraFilter(sParaTemp);

        ServletContext servletContext = request.getServletContext();

        //私钥证书路径
        String partnerCert = constants.getPATH_PARTER_PKCS12();





/*		通过file读取证书
		File file = new File("F:\\apache-tomcat-7.0.75\\wtpwebapps\\yspay_merchant_official\\WEB-INF\\cert\\smy_520.pfx");
		InputStream pfxCertFileInputStream = new FileInputStream(file);*/
        try {
            partnerCert = servletContext.getResource("/").toString().replace("file:","")+partnerCert;

            log.warn("servletContext:" + servletContext.getResource("/").toString());
            log.warn("partnerCert:" + partnerCert);
            //读取证书
            InputStream pfxCertFileInputStream = new FileInputStream(new File(partnerCert));
            log.warn("pfxCertFileInputStream:" + pfxCertFileInputStream.available());

            //遍历以及根据重新排序
            String signContent = SignUtils.getSignContent(sPara);

            String mysign = SignUtils.rsaSign(signContent, sParaTemp.get("charset"), pfxCertFileInputStream);

            sPara.put("sign", mysign);
            return sPara;
        } catch (Exception e) {
            log.error(e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for(StackTraceElement s : stackTrace){
                log.error(s.toString());
            }
            throw new RuntimeException("签名失败，请检查证书文件是否存在，密码是否正确" + e.getMessage() + "@@@" + partnerCert);
        }
    }

    /**
     * 异步验证签名
     */
    public static boolean verifySign(HttpServletRequest request, Map<String,String> params) {
        ServletContext servletContext = request.getServletContext();
        InputStream publicCertFileInputStream = servletContext
                .getResourceAsStream(constants.getPATH_YSEPAY_PUBLIC_CERT());

        String sign = "";
        if (params.get("sign") != null) {
            sign = params.get("sign");
        }
        boolean isSign = false;
        try {
            System.out.println("获取公钥成功，公钥路径："
                    + constants.getPATH_YSEPAY_PUBLIC_CERT()
                    + ", 长度：" + publicCertFileInputStream.available());
            isSign = SignUtils.rsaCheckContent(publicCertFileInputStream, params, sign,"utf-8");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("验证签名失败，请检查银盛公钥证书文件是否存在");
        }

        return isSign;
    }


    /**
     * send发送
     */
/*    public static String backgroundURL(HttpServletRequest request, Object obj) {

        Map<String, String> sPara = buildRequestPara(request, getProperty(obj));

        try {

            return Https.httpsSend("https://openapi.ysepay.com/gateway.do", sPara);
        } catch (Exception e) {
            throw new RuntimeException("支付发送网络异常" + e.getMessage());
        }
    }*/

    /**
     * 取对应的key和value
     */
    private static Map<String, String> getProperty(Object obj) {

        Map<String, String> map = null;
        try {
            Field fields[] = obj.getClass().getDeclaredFields();
            String[] name = new String[fields.length];
            Object[] value = new Object[fields.length];
            Field.setAccessible(fields, true);
            map = new HashMap<String, String>();
            for (int i = 0; i < name.length; i++) {
                name[i] = fields[i].getName();
                value[i] = fields[i].get(obj);

                if (value[i] == null ||name[i] == null || "serialVersionUID".equals(name[i])) {
                    continue;
                }

                map.put(name[i], String.valueOf(value[i]));
            }
        } catch (Exception e) {
            throw new RuntimeException("获取bean属性值异常:" + e.getMessage());
        }
        return map;
    }
}
