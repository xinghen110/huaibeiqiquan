<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>table切换</title>

	<script language="javascript" src="js/table/common.js"></script>
	<link  type="text/css" rel="stylesheet" href="js/table/style.css" />
</head>

<style>
	.new_tab{border-collapse: collapse; margin-top: 25px;margin-left: 100px;margin-bottom: 20px}
	.new_tab h2{text-align:center;font-weight:10 }
	.new_tab tr td{height:30px;border:1px solid #e0e0e0; text-indent:10px}
	.investment div{background:#f5f5f5;border-top:2px soild #e0e0e0}
	.on{background:#f5f5f5;border-top:2px solid red}
	.cue{background:#f5f5f5;}
</style>
<div class="investment_f" style="width:850px;height:482px;">
	<div class="investment_con" style="border:none;width:830px;height:450px;overflow: auto;">
		<div class="investment_con_list" style="display:block;">
			<form action="auditshop/saveExamine?shopNum=${shopInfo.shopNum}&shopName=${shopInfo.shopName}&city=${shopInfo.city}&area=${shopInfo.area}"
				  method="post" id="form" onsubmit="return iframeCallback(this,dialogAjaxDone);">
				<table class="new_tab">
					<tr>
						<td colspan="4"  class="cue"><h2>订单详情</h2></td>
					</tr>

					<c:if test="${bean.orderType == 1}">
						<tr>
							<td width="112" align="right" class="cue">店铺名字：</td>
							<td  width="200">${bean.shopInfo.shopName}</td>

							<td  width="116" align="right" class="cue">店铺编号：</td>
							<td  width="200">${bean.shopNum}</td>
						</tr>
					</c:if>

					<tr>
						<td width="112" align="right" class="cue">订单类型：</td>
						<td  width="200">
							<c:if test="${bean.orderType == 1}">普通订单</c:if>
							<c:if test="${bean.orderType == 3}">充值订单</c:if>
							<c:if test="${bean.orderType == 2}">会员卡订单</c:if>
						</td>

						<td  width="116" align="right" class="cue">订单状态：</td>
						<td  width="200"><ry:show parentCode="ORDER_STATUS" itemCode="${bean.orderStatus}"></ry:show></td>
					</tr>

					<tr>
						<td width="112" align="right" class="cue">订单创建人：</td>
						<td  width="200">${bean.orderUserName}</td>

						<td  width="116" align="right" class="cue">订单收货人：</td>
						<td  width="200">${bean.orderLinkMan}</td>
					</tr>

					<tr>
						<td width="112" align="right" class="cue">订单总金额：</td>
						<td  width="200">${bean.totalPrice}</td>

						<td  width="116" align="right" class="cue">订单实付金额：</td>
						<td  width="200">${bean.actualPrice}</td>
					</tr>

					<tr>
						<td width="112" align="right" class="cue">付款方式：</td>
						<td  width="200"><ry:show parentCode="PAY_METHOD" itemCode="${bean.payMethod}"></ry:show></td>

						<td  width="116" align="right" class="cue">付款时间：</td>
						<td  width="200"><ry:formatDate date="${bean.payTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>

					<c:if test="${bean.orderStatus != -1}">
						<tr>
							<td width="112" align="right" class="cue">接单时间：</td>
							<td width="200"><ry:formatDate date="${bean.receiveTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
							<td width="112" align="right" class="cue">完成时间：</td>
							<td width="200"><ry:formatDate date="${bean.completeTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:if>

					<c:if test="${bean.orderStatus == -1}">
						<tr>
							<td width="112" align="right" class="cue">取消时间：</td>
							<td width="200"><ry:formatDate date="${bean.cancelTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
							<td width="112" align="right" class="cue">取消原因：</td>
							<td width="200">${bean.cancelReason}</td>
						</tr>

						<tr>
							<td width="112" align="right" class="cue">取消用户编号：</td>
							<td width="200">${bean.cancelUserNum}</td>
							<td width="112" align="right" class="cue">返回金额：</td>
							<td width="200">${bean.returnPrice}</td>
						</tr>

					</c:if>

					<tr>
						<td width="112" align="right" class="cue">备注：</td>
						<td width="200" colspan="3">${bean.orderRemark}</td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</div>

<div class="formBar" style="background-color: #e4ebf6;">
	<ul>
		<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
	</ul>
</div>
<script type="text/javascript">

</script>