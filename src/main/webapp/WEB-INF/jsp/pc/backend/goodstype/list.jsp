<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>

<form id="pagerForm" method="post" action="goodstype/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="goodsTypeName" value="${param.goodsTypeName }" />
     <input type="hidden" name="parentNum" value="${param.parentNum }" />
     <input type="hidden" name="goodsLevel" value="${param.goodsLevel }" />
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="goodstype/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>类目名称：</label>
		<input type="text" name="goodsTypeName" value="${bean.goodsTypeName }"> 
	</li>
	 
	  <li>
		 <label>类别：</label>
		 <select name="goodsLevel">
			 <option value="">请选择</option>
			 <option value="1" <c:if test="${bean.goodsLevel == 1}">selected="selected"</c:if>>一级类别</option>
			 <option value="2" <c:if test="${bean.goodsLevel == 2}">selected="selected"</c:if>>二级类别</option>
		 </select>
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
<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" >
	<li><a class="add" onclick="add('goodstype/toEdit','添加商品分类',900,550,'main_')" ><span>添加</span></a></li>
</ry:authorize>
	  <!--<li><a title="确实要开启显示吗?" target="selectedTodo" rel="ids" postType="string" href="goodstype/ishomeshow?type=1" class="edit"><span>开启显示</span></a></li>	
	<li><a title="确实要关闭显示吗?" target="selectedTodo" rel="ids" postType="string" href="goodstype/ishomeshow?type=2" class="icon"><span>关闭显示</span></a></li>	 
--></ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!-- <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">类目名称</th>
			<th width="center" align="center">级别</th>
			<th width="center" align="center">排序</th>
			<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" ><th width="center" align="center">店铺名称</th></ry:authorize>
			
		
			<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" >
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<td>
				<input type="hidden" name="goodsTypeId" value="${item.goodsTypeId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td><a href="javascript:void(0)" onclick="add('goodstype/toDetailsEdit?goodsTypeNum=${item.goodsTypeNum }','商品类别详情',800,500,'main_')"><span style="color: blue;">${item.goodsTypeName }</span></a></td>
				<td><c:if test="${item.goodsLevel==1}">一</c:if> <c:if test="${item.goodsLevel==2}">二</c:if>级类别</td>
				<td>${item.sortNum}</td>
				<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" >
					<td>${item.shopInfo.shopName}</td>
				</ry:authorize>
				
			<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" >
				<td>
				<a title="修改商品分类"   onclick="add('goodstype/toEdit?goodsTypeNum=${item.goodsTypeNum}','修改商品分类',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				<a title="您确定删除商品分类信息吗？" target="ajaxTodo" href="goodstype/del?goodsTypeNum=${item.goodsTypeNum}" class="btnDel">删除</a>
				</td>
			</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

