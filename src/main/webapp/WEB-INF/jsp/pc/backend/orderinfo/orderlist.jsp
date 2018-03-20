<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="orderinfo/listes" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li style="width: 370px">
		<label>付款时间：</label>
		<input id="paystartTime" style="width: 110px" type="text" name="paystartTime" value="<fmt:formatDate value="${bean.paystartTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'payTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'payTime\')}'})"/>
		~
		<input id="payTime" style="width: 110px" type="text" name="payTime" value="<fmt:formatDate value="${bean.payTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'paystartTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'paystartTime\')}'})"/>
	</li>
	<input type="hidden" name="orderType" value="${bean.orderType}"/>
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
<table class="table" width="100%" layoutH="167">
	<thead>
		<tr>
			<th width="center" align="center">姓名</th>
			<th width="center" align="center">次数</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList}" varStatus="row">
			<tr>
			<td>${item.logisticsName }</td>
			<td>${item.rs}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

