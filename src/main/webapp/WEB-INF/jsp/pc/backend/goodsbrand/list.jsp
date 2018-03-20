<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="goodsbrand/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="brandName" value="${param.brandName}" />
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="goodsbrand/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>品牌名称：</label>
		<input type="text" name="brandName" value="${bean.brandName }"> 
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
	<li><a class="add" onclick="add('goodsbrand/toEdit','添加品牌',600,350,'main_')" ><span>添加</span></a></li>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!-- <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">品牌名称</th>
		 
			<th width="center" align="center">备注</th>
			 
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!-- <td><input name="ids" value="${item.goodsBrandId }" type="checkbox"></td>-->
				<td>
				<input type="hidden" name="goodsBrandId" value="${item.goodsBrandId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.brandName }</td>
				
				<td>${item.remark}</td>
				 
				<td>
				<a title="修改品牌"   onclick="add('goodsbrand/toEdit?goodsBrandNun=${item.goodsBrandNun}','修改品牌信息',600,400,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				<a title="您确定删除商标信息吗？" target="ajaxTodo" href="goodsbrand/del?goodsBrandNun=${item.goodsBrandNun}" class="btnDel">删除</a>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

