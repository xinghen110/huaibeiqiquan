<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>

<form id="pagerForm" method="post" action="goodsattribute/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="attributeName" value="${param.attributeName}" /> 
     <input type="hidden" name="attributeLevel" value="${param.attributeLevel}" /> 
     
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="goodsattribute/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>类目名称：</label>
		<input type="text" name="attributeName" value="${bean.attributeName }"> 
	</li>
	<!--  <li>
	 <label>一级类别：</label>
	 <select name="parentNum">
		 <option value="">请选择一级类别</option>
		 <c:forEach items="${goodsTypeList}" var="itmes">
		  <option value="${itmes.goodsTypeNum}" ${bean.parentNum == itmes.goodsTypeNum? 'selected' : ''}>${itmes.goodsTypeName}</option>
		 </c:forEach>
	 </select>
	 </li> -->
	 
	   <li>
		 <label>类别：</label>
		 <select name="attributeLevel">
			 <option value="">请选择</option>
			 <option value="1" <c:if test="${bean.attributeLevel == 1}">selected="selected"</c:if>>一级类别</option>
			 <option value="2" <c:if test="${bean.attributeLevel == 2}">selected="selected"</c:if>>二级类别</option>
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
	<li><a class="add" onclick="add('goodsattribute/toEdit','添加商品属性分类',900,550,'main_')" ><span>添加</span></a></li>
</ry:authorize>

	 </ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">商品类别名称</th>
			<th width="center" align="center">名称</th>
			<th width="center" align="center">父名称</th>
			<th width="center" align="center">等级</th>
			<th width="center" align="center">状态</th>
			<th width="center" align="center">排序</th>
		
			<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" >
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				<input type="hidden" name="goodsAttributeId" value="${item.goodsAttributeId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}
				</td>
				<td>${item.goodsType.goodsTypeName }</td>
				<td>${item.attributeName }</td>
				<td>${item.parentName }</td>
				<td><c:if test="${item.attributeLevel==1}">一</c:if> <c:if test="${item.attributeLevel==2}">二</c:if>级类别</td>
				<td>
				<ry:authorize ifAllGranted="${authMap.SHOP_AUTH}">
				<c:if test="${item.status==1}">
					<a href="javascript:void(0)" onclick="makeTop(2,'${item.goodsAttributeNum}','status','goodsattribute/ishomeshow','t_goods_attribute','goods_attribute_num')"><span style="color: green">启用</span></a>
				</c:if>
				<c:if test="${item.status==2}">
					<a href="javascript:void(0)" onclick="makeTop(1,'${item.goodsAttributeNum}','status','goodsattribute/ishomeshow','t_goods_attribute','goods_attribute_num')"><span style="color: red">禁用</span></a>
				</c:if>
				</ry:authorize>
				<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH},${authMap.SYSATEM_AUTH }">
				${item.status== 1?'启用' : '禁用'}
				 </ry:authorize>
				<td>${item.sortNum}</td>
				<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" >
				<td>
				<a title="修改商品属性分类" onclick="add('goodsattribute/toEdit?goodsAttributeNum=${item.goodsAttributeNum}','修改商品属性分类',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				<a title="您确定删除商品属性分类信息吗？" target="ajaxTodo" href="goodsattribute/del?goodsAttributeNum=${item.goodsAttributeNum}" class="btnDel">删除</a>
				
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

