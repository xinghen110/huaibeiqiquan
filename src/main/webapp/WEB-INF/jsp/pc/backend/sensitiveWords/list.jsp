<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="sensitiveWords/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
      <input type="hidden" name="wordsName" value="${bean.wordsName}" /> 
       
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="sensitiveWords/list" method="post">
<div class="searchBar">
<ul class="searchContent">
    <li ><label>敏感词：</label>
		<input type="text" name="wordsName" value="${bean.wordsName}">
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
	<li><a class="add" onclick="add('sensitiveWords/toEdit','添加敏感词信息',900,600,'main_')" ><span>添加</span></a></li>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="135">
	<thead>
		<tr>
		  
			<th width="center" align="center">序号</th>
			<th width="center" align="center">敏感词</th>
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				
				<td>
				<input type="hidden" name="sensitiveWordsNum" value="${item.sensitiveWordsNum}">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td >${item.wordsName}</td>
				
				<td>
					<a title="修改敏感词信息"   onclick="add('sensitiveWords/toEdit?sensitiveWordsNum=${item.sensitiveWordsNum}','修改敏感词信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
					<a title="您确定删除敏感词信息吗？" target="ajaxTodo" href="sensitiveWords/del?sensitiveWordsNum=${item.sensitiveWordsNum}" class="btnDel">删除</a>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>