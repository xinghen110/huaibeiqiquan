<%@ page contentType="text/html; charset=utf-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    //接口版本
    String version = request.getParameter("version");
%>
<HTML>
<HEAD>
    <TITLE>银联支付</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</HEAD>
<BODY>
    <form name="pay_form" id="pay_form" method="post" action="${requestUrl}">
        <%--***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***--%>
        <input type="hidden" name="version" value="${version}" />
        <input type="hidden" name="encoding" value="${encoding}" />
        <input type="hidden" name="certId" value="${certId}" />
        <input type="hidden" name="signMethod" value="${signMethod}" />
        <input type="hidden" name="signature" value="${signature}" />
        <input type="hidden" name="txnType" value="${txnType}" />
        <input type="hidden" name="txnSubType" value="${txnSubType}" />
        <input type="hidden" name="bizType" value="${bizType}" />
        <input type="hidden" name="channelType" value="${channelType}" />
        <%--/***商户接入参数***/--%>
        <input type="hidden" name="merId" value="${merId}" />
        <input type="hidden" name="accessType" value="${accessType}" />
        <input type="hidden" name="orderId" value="${orderId}" />
        <input type="hidden" name="txnTime" value="${txnTime}" />
        <input type="hidden" name="currencyCode" value="${currencyCode}" />
        <input type="hidden" name="txnAmt" value="${txnAmt}" />
        <input type="hidden" name="frontUrl" value="${frontUrl}" />
        <input type="hidden" name="backUrl" value="${backUrl}" />
        <input type="hidden" name="payTimeout" value="${payTimeout}" />
    </form>
</BODY>
<script type="text/javascript">
    document.all.pay_form.submit();
</script>
</HTML>
