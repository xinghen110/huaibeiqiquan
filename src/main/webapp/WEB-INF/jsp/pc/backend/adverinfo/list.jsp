<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
 <ry:binding type="2"></ry:binding>
<form id="pagerForm" method="post" action="adverinfo/list?position=${position}">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="title" value="${bean.title}" /> 
       <input type="hidden" name="status" value="${bean.status}" /> 
       <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' /> 
       
</form>
<style type="text/css">
	.grid .gridTbody td div{height: auto;}
</style>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="adverinfo/list?position=${position }" method="post">
<div class="searchBar">
<ul class="searchContent">
    
    <li ><label>广告名称：</label>
		<input type="text" name="title" value="${bean.title }">
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
	<li><a class="add" onclick="add('adverinfo/toEdit?adverType=${adverType}','添加广告信息',900,600,'main_')" ><span>添加</span></a></li>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
			 
			<th width="center" align="center">广告图片</th>
			<th width="center" align="center">广告名称</th>
			 
			<th width="center" align="center">排序</th>
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<!-- <td><input name="ids" value="${item.adverInfoId }" type="checkbox"></td> -->
				<input type="hidden" name="adverInfoId" value="${item.adverInfoId }" />
				<td style="height: auto;"><img src="${constants.QINIU_USER_IMGURL}${item.mainPhoto}" width="150" height="80"/></td>
				<td style="text-align: left;">${item.title}</td>
				 
				<td >${item.sortNum}</td>
				<td>
					<a title="修改广告信息"   onclick="add('adverinfo/toEdit?adverInfoId=${item.adverInfoId}&adverType=${item.adverType}','修改广告信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>
					<a title="您确定删除广告信息吗？" target="ajaxTodo" href="adverinfo/del?adverInfoId=${item.adverInfoId}" class="btnDel">删除</a>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

