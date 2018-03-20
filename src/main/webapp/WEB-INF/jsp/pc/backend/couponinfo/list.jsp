<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="couponinfo/list?type=${type }">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="couponName" value="${param.couponName}" /> 
     <input type="hidden" name="shopName" value="${param.shopName}" /> 
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="couponinfo/list?type=${type }" method="post">
<input type="hidden" name="type" value="${type }"/>
<div class="searchBar">
<ul class="searchContent">
<li><label>优惠券名称：</label>
	<input type="text" name="couponName" value="${bean.couponName }">
</li>

<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" >
	<li><label>店铺名称：</label>
		<input type="text" name="shopName" value="${bean.shopName }">
	</li>
	
	 
</ry:authorize>

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
<ry:authorize ifAnyGranted ="${authMap.SHOP_AUTH}" >	
	<li><a class="add" onclick="add('couponinfo/toEdit','添加优惠券',700,450,'main_')" ><span>添加</span></a></li>
	
</ry:authorize>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr><!--
		   <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			-->
			
			<th width="center" align="center">序号</th>
			<th width="center" align="center">优惠券名称</th>
			<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" ><th width="center" align="center">店铺名称</th></ry:authorize>
			<th width="center" align="center">满额</th>
			<th width="center" align="center">有效期</th>
		
			<ry:authorize ifAnyGranted ="${authMap.SHOP_AUTH}" >	
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				<input type="hidden" name="couponId" value="${item.couponId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.couponName }</td>
				<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" ><td>${item.shopName }</td></ry:authorize>
				<td>满${item.fullMoney }减${item.lessMoney }</td>
				<td><ry:formatDate date="${item.startTime }" toFmt="yyyy-MM-dd" />~<ry:formatDate date="${item.endTime }" toFmt="yyyy-MM-dd" /></td>
				<ry:authorize ifAnyGranted ="${authMap.SHOP_AUTH}" >	
				<td>
				<c:if test="${item.isUse==2}">
					<a title="修改优惠券信息" onclick="add('couponinfo/toEdit?couponNum=${item.couponNum}','修改优惠券信息',700,450,'main_')"  rel="users_saveedit" class="btnEdit" >修改</a>		
				</c:if>
					<a title="您确定删除优惠券信息吗？" target="ajaxTodo" href="couponinfo/del?couponNum=${item.couponNum}" class="btnDel" >删除</a>
					
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

