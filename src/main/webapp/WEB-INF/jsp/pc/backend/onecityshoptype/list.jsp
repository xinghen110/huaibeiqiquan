<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
 <ry:binding type="2"></ry:binding>
<form id="pagerForm" method="post" action="oneCityShopType/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="shopTypeName" value="${bean.shopTypeName}" /> 
       <input type="hidden" name="status" value="${bean.status}" /> 

       
</form>
<style type="text/css">
	.grid .gridTbody td div{height: auto;}
</style>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="oneCityShopType/list" method="post">
<div class="searchBar">
<ul class="searchContent">
    
    <li ><label>类型名称：</label>
		<input type="text" name="shopTypeName" value="${bean.shopTypeName}">
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

	<!--<li><a class="add" onclick="add('yellowshoptype/toEdit','添加同城分类信息',900,600,'main_')" ><span>添加</span></a></li>
	--><!-- <li><a title="确实要开启显示吗?" target="selectedTodo" rel="ids" postType="string" href="adverinfo/ishomeshow?type=1" class="edit"><span>开启显示</span></a></li>	
	<li><a title="确实要关闭显示吗?" target="selectedTodo" rel="ids" postType="string" href="adverinfo/ishomeshow?type=2" class="icon"><span>关闭显示</span></a></li>	 -->	
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!-- <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
			 
			<th width="center" align="center">同城店铺图片</th>
			<th width="center" align="center">同城店铺名称</th>
			 
			<th width="center" align="center">排序</th>
			<th width="center" align="center">操作</th>
			<th width="center" align="center">子类</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<input type="hidden" name="shopTypeNum" value="${item.shopTypeNum}" />
				<td style="height: auto;"><img src="${constants.QINIU_USER_IMGURL}${item.shopTypeIcon}" width="80" height="80"/></td>
				<td style="text-align: center;">${item.shopTypeName}</td>
				 
				<td >${item.sortNum}</td>
				<td>
					<a title="修改黄页分类信息"   onclick="add('oneCityShopType/toEdit?shopTypeNum=${item.shopTypeNum}','修改黄页分类信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
					<a title="您确定删除黄页分类信息吗？" target="ajaxTodo" href="oneCityShopType/del?shopTypeNum=${item.shopTypeNum}" class="btnDel">删除</a>
				</td>
				<td>
					<a style="cursor: pointer;" onclick="openNav('shoptype/childlist?parentNum=${item.shopTypeNum}','下级管理','main_index')"><span style="color: blue;">下级管理</span></a>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
