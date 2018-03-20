<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>

<form id="pagerForm" method="post" action="rechargemeal/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       
      
       <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
    	<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
       
       
</form>



<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="rechargemeal/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	
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
	<li><a class="add" onclick="add('rechargemeal/toEdit','充值信息',900,500,'main_')"><span>充值</span></a></li>	
</ul>
</div>
</div>

<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>-->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">充值金额 </th>
			 
			<th width="center" align="center">赠送金额 </th>
			<!--<th width="center" align="center">备注</th>
			--><th width="center" align="center">创建时间 </th>
			
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	 <tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!--<td><input name="ids" value="${item.rechargeMealId }" type="checkbox"></td>
			-->
			<td><input type="hidden" name="rechargeMealId" value="${item.rechargeMealId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				
				<td>${item.amount}</td>
				<td>${item.presentPrice }</td>
				<!--<td>${item.remark}</td>
				--><td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
						<a title="修改充值"   onclick="add('rechargemeal/toEdit?rechargeMealNum=${item.rechargeMealNum }','修改充值',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>			
						<a title="您确定删除信息吗？" target="ajaxTodo" href="rechargemeal/del?rechargeMealNum=${item.rechargeMealNum }" class="btnDel">删除</a>
				</td>
				</tr>
		</c:forEach>
	 </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>