<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="ordergoods/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
</form>

<table class="table" width="100%" layoutH="60">
	<thead>
		<tr>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">商品名称</th>
			<th width="center" align="center">数量</th>
			<th width="center" align="center">总金额</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				
				<input type="hidden" name="orderGoodsNum" value="${item.orderGoodsNum}">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.goodsName}</td>
				<td>${item.goodsCount}</td>
				<td>${item.actualPrice}</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

