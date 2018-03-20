<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="flowinfo/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="shopNum" value="${param.shopNum}" />
       <input type="hidden" name="statrTime" value='<fmt:formatDate value="${param.statrTime}" pattern="yyyy-MM-dd"/>' /> 
		<input type="hidden" name="createTime" value='<fmt:formatDate value="${param.createTime}" pattern="yyyy-MM-dd"/>' />
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="flowinfo/list"  method="post">
<div class="searchBar">
<ul class="searchContent">
	<li style="width: 370px">
		<label>关注时间：</label>
		<input id="statrTime" style="width: 110px" type="text" name="statrTime" value="<fmt:formatDate value="${bean.statrTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
		~
		<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
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
<table class="table" width="100%" layoutH="106">
	<thead>
		<tr>
			<th width="center" align="center">序号</th>
			<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" >
			<th width="center" align="center">店铺名称</th>
			</ry:authorize>
			<th width="center" align="center">姓名</th>
			<th width="center" align="center">性别</th>	
			<th width="center" align="center">关注时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				<input type="hidden" name="flowInfoId" value="item.flowInfoId">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" >
				<td>${item.tShopInfo.shopName}</td>
				</ry:authorize>
				<td>${item.tUser.nickName}</td>
				<td>
				<c:if test="${item.tUser.userSex!=0}">
				<ry:show parentCode="USERSEX" itemCode="${item.tUser.userSex}"></ry:show></c:if></td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
