<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="useraddress/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="linkMan" value="${param.linkMan}" /> 
     <input type="hidden" name="linkTel" value="${param.linkTel}" /> 

</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="useraddress/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>联系人：</label>
		<input type="text" name="linkMan" value="${bean.linkMan }"> 
	</li>
	
	<li>
		<label>联系方式：</label>
		<input type="text" name="linkTel" value="${bean.linkTel }"> 
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
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
	</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">联系人</th>
			<th width="center" align="center">联系方式 </th>
			<th width="center" align="center">是否默认地址</th>
			<th width="center" align="center">详细地址信息</th>
			</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				<input type="hidden" name="userAddressId" value="${item.userAddressId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.linkMan}</td>
				<td>${item.linkTel}</td>
				<td>${item.isDeafult == 1?'是' : '否'}</td>
				<td>${item.address }</td></tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

