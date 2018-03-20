<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
<link href="app/css/style.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="REFUND_STATUS" bingdingName="refundstatus"></ry:binding>

<title></title>
	</head>
	<body align="center">
	<c:if test="${not empty microamoy.title}">
		<section class="od_box">
			<article class="details">
				<div class="details_list">
					<p>微淘名称：</p>
					<p>${microamoy.title }</p>
				</div>
				<div class="details_list">
					<p>会员名：</p>
					<p >${user.nickName }</p>
				</div>
				<div class="details_list">
					<p>评论时间：</p>
					<p><ry:formatDate date="${bean.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></p>
				</div>
			</article>
			<article class="seller_inf">
				<h3><b>内容：</b></h3>
				<p class="seller_inf_content">
				${bean.content }
				</p>
				<tr>
				<c:forEach items="${pic}" var="item">
				<td><img class="seller_inf_content"  src="${item.filePath }" width="200" height="200"></td>
				</c:forEach>
				</tr>
			</article>
		</section>
		</c:if>
		<c:if test="${empty microamoy.title}">
			<h1 style="text-align:center;"><span style="color: red; font-size: 30px;" >该微淘信息已被删除</span></h1>
		</c:if>
		
	</body>
</html>
