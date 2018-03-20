<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="shoptype/list?homeposition=${homeposition }">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="shopTypeName" value="${param.shopTypeNamel}" />
     <input type="hidden" name="shopLevel" value="${param.shopLevel}" />
</form>
<style type="text/css">
	.grid .gridTbody td div{height: auto;}
</style>
 <ry:binding type="2"></ry:binding>
 <ry:binding type="3"></ry:binding>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="shoptype/list?parentNum=${parentNum}" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>类目名称：</label>
		<input type="text" name="shopTypeName" value="${bean.shopTypeName }"> 
	</li>
	
	 <!--<li>
	 <label>一级类别：</label>
	 <select name="parentNum">
		 <option value="">请选择一级类别</option>
		 <c:forEach items="${shopTypeList}" var="itmes">
		  <option value="${itmes.shopTypeNum}" ${bean.parentNum == itmes.shopTypeNum?'selected':''}>${itmes.shopTypeName}</option>
		 </c:forEach>
	 </select>
	 </li>
	 
	  <li>
		 <label>类别：</label>
		 <select name="shopLevel">
			 <option value="">请选择</option>
			 <option value="1" <c:if test="${bean.shopLevel == 1}">selected="selected"</c:if>>一级类别</option>
			 <option value="2" <c:if test="${bean.shopLevel == 2}">selected="selected"</c:if>>二级类别</option>
		 </select>
	 </li>
--></ul>

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
<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
	<li><a class="add" onclick="add('shoptype/toEdit?homeposition=${homeposition}&parentNum=${parentNum}','添加店铺分类',900,550,'main_')" ><span>添加</span></a></li>
	  <!--<li><a title="确实要开启显示吗?" target="selectedTodo" rel="ids" postType="string" href="shoptype/ishomeshow?type=1" class="edit"><span>开启显示</span></a></li>	
	<li><a title="确实要关闭显示吗?" target="selectedTodo" rel="ids" postType="string" href="shoptype/ishomeshow?type=2" class="icon"><span>关闭显示</span></a></li>-->	 
</ry:authorize>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!-- <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">类目名称</th>
			<th width="center" align="center">排序</th>
			<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!-- <td><input name="ids" value="${item.shopTypeId }" type="checkbox"></td>-->
				<td>
				<input type="hidden" name="shopTypeId" value="${item.shopTypeId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.shopTypeName}</td>
				<td>${item.sortNum}</td>
				<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
				<td>
				<a title="修改新闻分类"   onclick="add('shoptype/toEdit?shopTypeNum=${item.shopTypeNum}&parentNum=${parentNum}','修改新闻分类',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				<a title="您确定删除新闻分类信息吗？" target="ajaxTodo" href="shoptype/del?shopTypeNum=${item.shopTypeNum}" class="btnDel">删除</a>
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
