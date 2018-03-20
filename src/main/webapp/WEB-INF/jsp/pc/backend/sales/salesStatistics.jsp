<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<table class="table" width="100%" layoutH="105">
	<thead>
		<tr>
		 	
			<th width="center" align="center">序号</th>
			<th width="center" align="center">商品名称</th>
			<th width="center" align="center">数量</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<td>
				<input type="hidden" name="orderGoodsNum" value="${item.orderGoodsNum}">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.goodsName}</td>
				<td>${item.counts}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
