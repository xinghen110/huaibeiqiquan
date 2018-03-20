<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="orderinfo/lists/${orderStatus}">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="shopName" value="${bean.shopName}" /> 
       <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="payTime" value='<fmt:formatDate value="${bean.payTime}" pattern="yyyy-MM-dd"/>' /> 
</form>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="orderinfo/lists/${orderStatus}" method="post">
<div class="searchBar">
<ul class="searchContent">
    <li ><label>店铺名称：</label>
		<input type="text" name="shopName" value="${bean.shopName }">
	</li>

	
	<li style="width: 370px">
		<label>付款时间：</label>
		<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'payTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'payTime\')}'})"/>
		~
		<input id="payTime" style="width: 110px" type="text" name="payTime" value="<fmt:formatDate value="${bean.payTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
	</li>
</ul>
<div class="subBar">
<ul> 
    <li>
	<div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
	</li>
</ul>
</div>
</div>
</form>
</div>
<table class="table" width="100%" layoutH="105">
	<thead>
		<tr>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">店铺名</th>
			<th width="center" align="center">金额</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				<input type="hidden" name="orderId" value="${item.orderId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.shopName}</td>
				<td>${item.money}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

