<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="CONSUM_TYPE,PAY_TYPE" bingdingName="consumType,payTypes"></ry:binding>

<form id="pagerForm" method="post" action="userrecord/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="consumType" value="${param.consumType }" />
     <input type="hidden" name="payType" value="${param.payType }" />
      <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
    	<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="userrecord/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	  <li>
	 <label>消费类型：</label>
	 <select name="consumType">
		 <option value="">请选择</option>
		 <c:forEach items="${consumType}" var="itmes">
		  <option value="${itmes.itemCode}" ${bean.consumType == itmes.itemCode?'selected':''}>${itmes.itemName}</option>
		 </c:forEach>
	 </select>
	 </li>
	  <li>
	 <label>支付类型：</label>
	 <select name="payType">
		 <option value="">请选择</option>
		 <c:forEach items="${payTypes}" var="itmes">
		  <option value="${itmes.itemCode}" ${bean.payType == itmes.itemCode?'selected':''}>${itmes.itemName}</option>
		 </c:forEach>
	 </select>
	 </li>
	 <li style="width: 370px">
		<label>交易时间：</label>
		<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
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
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!-- <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">用户类型</th>
			<th width="center" align="center">用户名</th>
			<th width="center" align="center">支付类型</th>
			<th width="center" align="center">交易金额</th>
			<th width="center" align="center">交易时间</th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!-- <td><input name="ids" value="${item.userRecordId }" type="checkbox"></td>-->
				<td>
				<input type="hidden" name="userRecordId" value="${item.userRecordId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td><ry:show parentCode="USER_TYPE" itemCode="${item.userType}"></ry:show></td>
				<td>${item.user.nickName}</td>
				<td><ry:show parentCode="PAY_TYPE" itemCode="${item.payType}"></ry:show></td>
				<td><fmt:formatNumber value="${item.consumPrice}" /></td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

