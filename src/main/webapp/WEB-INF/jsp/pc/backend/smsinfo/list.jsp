<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="smsInfo/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="startTime" value='<fmt:formatDate value="${param.startTime}" pattern="yyyy-MM-dd"/>' /> 
	 <input type="hidden" name="createTime" value='<fmt:formatDate value="${param.createTime}" pattern="yyyy-MM-dd"/>' />
     
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="smsInfo/list" method="post">
<div class="searchBar">
<ul class="searchContent">
<li style="width: 370px">
<label>发送时间：</label>
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
<table class="table" width="100%" layoutH="105">
	<thead>
		<tr>
		  
			<th width="center" align="center">序号</th>
			<th width="center" align="center">标题</th>
			<th width="center" align="center">类型</th>
			<th width="center" align="center">接收人</th>
			
			<th width="center" align="center">状态</th> 
			<th width="center" align="center">创建时间</th>
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<td>
				<input type="hidden" name="smsInfoNum" value="${item.smsInfoNum }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.title}</td>
				<td>${item.smsType }</td>
				<td>${item.user.nickName}</td>
				 <td>${item.status }</td> 
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
				<a title="短信详情"   onclick="add('smsInfo/toDetailsEdit?smsInfoNum=${item.smsInfoNum}','短信详情',900,550,'main_')"  rel="users_saveedit" class="btnLook">查看详情</a>				
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
