<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="exchangeinfo/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="title" value="${param.title}" /> 
       
        <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' /> 
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="exchangeinfo/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>标题：</label>
		<input type="text" name="title" value="${bean.title }"> 
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
	<li><a class="add" onclick="add('exchangeinfo/toEdit','添加积分兑换商品信息',900,550,'main_')" ><span>添加</span></a></li>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr><!--
		   <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			--><th width="center" align="center">序号</th>
			<th width="center" align="center">标题</th>
			<th width="center" align="center">所需积分 </th>
			<th width="center" align="center">创建时间</th>
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!--<td><input name="ids" value="" type="checkbox"></td>
				--><td>
				<input type="hidden" name="exchangeInfoId" value="${item.exchangeInfoId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td style="text-align: left;">${item.title }</td>
				<td>${item.point }</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
				<a title="修改积分兑换商品"   onclick="add('exchangeinfo/toEdit?exchangeInfoNum=${item.exchangeInfoNum}','修改积分兑换商品',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				<a title="您确定删除积分兑换商品吗？" target="ajaxTodo" href="exchangeinfo/del?exchangeInfoNum=${item.exchangeInfoNum}" class="btnDel">删除</a>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

