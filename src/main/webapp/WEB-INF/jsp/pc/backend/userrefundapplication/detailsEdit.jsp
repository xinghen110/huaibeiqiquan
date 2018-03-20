<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
<link href="app/css/style.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="REFUND_STATUS,REFUND_REASON" bingdingName="refundstatus,refundreason"></ry:binding>
<title></title>
	</head>
	<body>
		<section class="od_box">
			<article class="details">
				<div class="details_list">
					<p>订单编号：</p>
					<p style="font-weight: bold;font-size: large">${bean.orderNum }</p>
				</div>
				
				<div class="details_list">
					<p>店铺：</p>
					<p>${shopinfo.shopName }</p>
				</div>
				<div class="details_list">
					<p>用户名称：</p>
					<p>${user.nickName }</p>
				</div>
				<div class="details_list">
					<p>
					<c:if test="${refundType ==1}">退款</c:if>
					<c:if test="${refundType ==2}">退货</c:if>
					<c:if test="${refundType ==3}">换货</c:if>
					时间：</p>
					<p><ry:formatDate date="${bean.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></p>
				</div>
				<c:if test="${refundType ==1 || refundType ==2}">
				<div class="details_list">
					<p>金额：</p>
					<p>${bean.refundMoney}</p>
				</div>
				
				<div class="details_list">
					<p>原因：</p>
					<p ><ry:show parentCode="REFUND_REASON" itemCode="${bean.refundReason}"></ry:show></p>
				</div>
				</c:if>
				
			</article>
			<article class="seller_inf">
				<h3><b>备注信息</b></h3>
				<p class="seller_inf_content">
				${bean.refundRemark}
				</p>
			</article>
			<article class="seller_inf">
				<div style="width:100%;overflow:hidden;">
					<p style="float:left;line-height:30px">审核状态：</p>
					 <ry:show parentCode="REFUND_STATUS" itemCode="${bean.status}"></ry:show>
					 
				</div>
				<div style="width:100%;overflow:hidden;margin-top:10px;">
					<p style="float:left;line-height:30px">审核说明：</p>
						${bean.refusalReason }
				</div>
			</article>
 

 
			<div style="text-align: center;">
			<!--<c:if test="${bean.status==2}">
			<input type="button" value="保存" style="margin-top: 6px;width:6rem;border:none;background:#2D89BE;color:white;height:30px;line-height:30px;text-align:center;border-radius:5px;cursor:pointer;"/>
			</c:if>-->
			<input type="button" value="关闭" onclick="closeCur()" style="margin-top: 6px;width:6rem;border:none;background:#2D89BE;color:white;height:30px;line-height:30px;text-align:center;border-radius:5px;cursor:pointer;"/>
			</div>
		</section>
		<script type="text/javascript">
			function closeCur(){
				navTab.closeCurrentTab(); 
			}
		</script>
	</body>
</html>
