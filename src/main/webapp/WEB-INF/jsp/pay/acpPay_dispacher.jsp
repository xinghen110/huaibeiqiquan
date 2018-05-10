<%@ page contentType="text/html; charset=utf-8"%>
<%
    request.setCharacterEncoding("UTF-8");
/*    String version = request.getParameter("version");
    String encoding = request.getParameter("encoding");
    String certId = request.getParameter("certId");
    String signMethod = request.getParameter("signMethod");
    String signature = request.getParameter("signature");
    String txnType = request.getParameter("txnType");
    String txnSubType = request.getParameter("txnSubType");
    String bizType = request.getParameter("bizType");
    String channelType = request.getParameter("channelType");
    String merId = request.getParameter("merId");
    String accessType = request.getParameter("accessType");
    String orderId = request.getParameter("orderId");
    String txnTime = request.getParameter("txnTime");
    String currencyCode = request.getParameter("currencyCode");
    String txnAmt = request.getParameter("txnAmt");
    String frontUrl = request.getParameter("frontUrl");
    String backUrl = request.getParameter("backUrl");
    String payTimeout = request.getParameter("payTimeout");*/
%>
<HTML>
<HEAD>
    <TITLE>银联支付</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</HEAD>
<BODY>
    <form name="pay_form" id="pay_form" method="post" action="https://gateway.95516.com/gateway/api/frontTransReq.do">
        <%--***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***--%>
        <input type="hidden" name="version" value="${param.version}" />
        <input type="hidden" name="encoding" value="${param.encoding}" />
        <input type="hidden" name="certId" value="${param.certId}" />
        <input type="hidden" name="signMethod" value="${param.signMethod}" />
        <input type="hidden" name="signature" value="${param.signature}" />
        <input type="hidden" name="txnType" value="${param.txnType}" />
        <input type="hidden" name="txnSubType" value="${param.txnSubType}" />
        <input type="hidden" name="bizType" value="${param.bizType}" />
        <input type="hidden" name="channelType" value="${param.channelType}" />
        <%--/***商户接入参数***/--%>
        <input type="hidden" name="merId" value="${param.merId}" />
        <input type="hidden" name="accessType" value="${param.accessType}" />
        <input type="hidden" name="orderId" value="${param.orderId}" />
        <input type="hidden" name="txnTime" value="${param.txnTime}" />
        <input type="hidden" name="currencyCode" value="${param.currencyCode}" />
        <input type="hidden" name="txnAmt" value="${param.txnAmt}" />
        <input type="hidden" name="frontUrl" value="${param.frontUrl}" />
        <input type="hidden" name="backUrl" value="${param.backUrl}" />
        <input type="hidden" name="payTimeout" value="${param.payTimeout}" />
    </form>
</BODY>
<script type="text/javascript">
    document.all.pay_form.submit();
</script>
</HTML>
