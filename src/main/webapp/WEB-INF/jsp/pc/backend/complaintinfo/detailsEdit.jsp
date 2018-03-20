<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
<link href="app/css/style.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="REFUND_STATUS" bingdingName="refundstatus"></ry:binding>
<ry:binding type="2"></ry:binding>
<title></title>
	</head>
	<body>
		<section class="od_box">
			<article class="details">
				<div class="details_list">
					<p>投诉订单号：</p>
					<p style="font-weight: bold;font-size: large">${bean.orderNum }</p>
				</div>
				<div class="details_list">
					<p>投诉人：</p>
					<p>${user.nickName}</p>
				</div>
				<c:if test="${bean.type==1}">
				<div class="details_list">
					<p>店铺名：</p>
					<p>${shop.shopName }</p>
				</div>
				</c:if>
				<c:if test="${bean.type==2}">
				<div class="details_list">
					<p>物流人员：</p>
					<p>${wluser.nickName }</p>
				</div>
				</c:if>
				<div class="details_list">
					<p>被投诉者：</p>
					<p>${bean.type == 1 ? '商家' : '物流'}</p>
				</div>
				<div class="details_list">
					<p>投诉时间：</p>
					<p><ry:formatDate date="${bean.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></p>
				</div>
				
				
				
			</article>
			<article class="seller_inf">
				<h3><b>备注信息</b></h3>
				<p class="seller_inf_content">
				${bean.content }
				</p>
				<tr>
				<c:forEach items="${pic}" var="item">
				
				<td><img class="seller_inf_content"  src="${constants.QINIU_USER_IMGURL}${item.filePath }" width="300" height="300"></td>
				
				</c:forEach>
				</tr>
			</article>
			
		</section>
	</body>
</html>
