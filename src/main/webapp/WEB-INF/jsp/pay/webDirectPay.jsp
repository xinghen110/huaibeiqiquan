<%@ page import="com.pay.yspay.utils.*" %>
<%@ page import="com.pay.yspay.bean.PayOrder" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//---------------------------------------------------------
	//银盛支付接口请求示例，商户按照此文档进行开发即可
	//---------------------------------------------------------

	//---------------生成支付订单号 结束------------------------

	//创建wap手机直连对象

	PayOrder bean = (PayOrder)request.getAttribute("payModel");

	String html = APIUtils.buildRequest(request, bean);

	out.println(html);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银盛支付即时到帐程序演示</title>
</head>
<body>
	<br />
</body>
</html>
