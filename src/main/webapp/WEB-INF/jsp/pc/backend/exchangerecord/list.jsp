<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="exchangerecord/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="title" value="${param.title}" /> 
       <input type="hidden" name="userName" value="${param.userName}" /> 
       <input type="hidden" name="linkTel" value="${param.linkTel}" /> 
        <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' /> 
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="exchangerecord/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>标题：</label>
		<input type="text" name="title" value="${bean.title }"> 
	</li>
	<li>
		<label>姓名：</label>
		<input type="text" name="userName" value="${bean.userName }"> 
	</li>
	<li>
		<label>联系方式：</label>
		<input type="text" name="linkTel" value="${bean.linkTel }"> 
	</li>
	
	<li style="width: 370px">
		<label>创建时间：</label>
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
	<!--<li><a class="add" onclick="add('exchangerecord/toEdit','添加积分兑换商品信息',900,550,'main_')" ><span>添加</span></a></li>
--></ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr><!--
		   <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			--><th width="center" align="center">序号</th>
			<th width="center" align="center">标题</th>
			<th width="center" align="center">所需积分 </th>
			<th width="center" align="center">姓名</th>
			<th width="center" align="center">联系方式 </th>
			<th width="center" align="center">创建时间</th>
			<th width="center" align="center">是否已货</th>
			

		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!--<td><input name="ids" value="" type="checkbox"></td>
				--><td>
				<input type="hidden" name="userExchangeRecordId" value="${item.userExchangeRecordId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.title }</td>
				<td>${item.point }</td>
				<td>${item.userName }</td>
				<td>${item.linkTel }</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
				<c:if test="${item.operateStatus==1}">
					<span style="color: green">已发货</span>
				</c:if>
				<c:if test="${item.operateStatus==2}">
					<a href="javascript:void(0)" onclick="add('exchangerecord/toEdit?userExchangeRecordNum=${item.userExchangeRecordNum}','发奖励',600,200,'main_')" rel="users_saveedit"><span style="color: red">未发货</span></a>
				</c:if>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

