<%--
  Created by IntelliJ IDEA.
  User: hx-pc
  Date: 2018/4/20
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
    /*request.setCharacterEncoding("UTF-8");
    //接口版本
    String version = request.getParameter("version");
    //提交地址
    String requestUrl = request.getParameter("requestUrl");
    if(requestUrl == null || requestUrl.equals("")){
        //如果接收参数为空，则说明是跳转地址
        requestUrl = "https://www.unspay.com/unspay/page/linkbank/payRequest.do";
    }
    //注册商户在银生的客户编号
    String merchantId = request.getParameter("merchantId");
    //注册商户在银生设置的密钥
    String merchantKey = request.getParameter("merchantKey");
    //商户反馈url
    String merchantUrl = request.getParameter("merchantUrl");
    //响应方式， 1-页面响应，2-后台响应， 3-两者都需
    String responseMode = request.getParameter("responseMode");
    //是否担保支付
    String assuredPay = request.getParameter("assuredPay");
    //是否允许商联卡担保
    String cardAssured = request.getParameter("cardAssured");
    //订单的生成
    String time = request.getParameter("date"); //订单创建时间
    //订单id[商户网站]
    String orderId = request.getParameter("orderId");
    //货币种类，暂时只支持人民币： CNY
    String currencyType = "CNY";
    //支付金额
    String amount = request.getParameter("amount");
    //备注，附加信息
    String remark = request.getParameter("remark");
    //备注，商户前台响应地址
    String frontURL = request.getParameter("frontURL");
    //银行代码或者商联卡支付或者银生账户支付
    //String bankCode = request.getParameter("bankCode");
    //是否B2B支付
    //boolean b2b = false;
    //商品名称
    //String commodity = request.getParameter("commodity");
    //订单url
    //String orderUrl = request.getParameter("orderUrl");


    //md5加密
    String mac = request.getParameter("mac");*/
%>
<HTML>
<HEAD>
    <TITLE>银生支付</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#form").submit();
        });
    </script>
</HEAD>
<BODY>
    <form name="form" id="form" method="post" action="${requestUrl}">
        <input type="hidden" name="version" value="${version}">
        <input type="hidden" name="merchantId" value="${merchantId}">
        <input type="hidden" name="merchantUrl" value="${merchantUrl}">
        <input type="hidden" name="responseMode" value="${responseMode}">
        <input type="hidden" name="orderId" value="${orderId}">
        <input type="hidden" name="amount" value="${amount}">
        <input type="hidden" name="currencyType" value="${currencyType}">
        <input type="hidden" name="assuredPay" value="${assuredPay}">
        <input type="hidden" name="date" value="${time}">
        <input type="hidden" name="remark" value="${remark}">
        <input type="hidden" name="mac" value="${mac}">
        <input type="hidden" name="b2b" value="false">
        <input type="hidden" name="cardAssured" value="${cardAssured}">
        <input type="hidden" name="frontURL" value="${frontURL}">
        <input type="hidden" name="merchantKey" value="${merchantKey}">
    </form>
</BODY>
</HTML>
