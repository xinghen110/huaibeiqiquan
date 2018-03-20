<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="shopinfo/list?shopStatus=${bean.shopStatus}&isRecommend=${bean.isRecommend}&type=${type}">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
  <input type="hidden" name="shopName" value="${param.shopName}" />
     <input type="hidden" name="province" value="${param.province}" /> 
     <input type="hidden" name="city" value="${param.city}" /> 
     <input type="hidden" name="areas" value="${param.areas}" /> 
     <input type="hidden" name="shopStatus" value="${param.shopStatus}" /> 
     <input type="hidden" name="isRecommend" value="${param.isRecommend}" /> 
     <input type="hidden" name="shopTypeNum" value="${param.shopTypeNum}" /> 
     <input type="hidden" name="parentNum" value="${param.parentNum}" /> 
     <input type="hidden" name="isHot" value="${param.isHot}"/> 
     
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="shopinfo/list?shopStatus=${bean.shopStatus}&isRecommend=${bean.isRecommend}&type=${type}&isHot=${bean.isHot}" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>店铺名称：</label>
		<input type="text" name="shopName" value="${bean.shopName }"> 
		<input type="hidden" name="type" value="${type}">
	</li>
	<li>
		<label>是否推荐：</label>
		<select name="isRecommend">
			<option value="" selected="selected">请选择</option>
			<option value="1" <c:if test="${bean.isRecommend == 1}">selected="selected"</c:if>>是</option>
			<option value="2" <c:if test="${bean.isRecommend == 2}">selected="selected"</c:if>>否</option>
		</select>
	</li>
	<li>
		<label>店铺类型：</label>
		<select name="parentNum">
				<option value="" selected="selected">请选择</option>
				<option value="STI010000000000" <c:if test="${bean.parentNum == 'STI010000000000'}">selected="selected"</c:if>>同城商铺</option>
				<option value="STI020000000000" <c:if test="${bean.parentNum == 'STI020000000000'}">selected="selected"</c:if>>美食外卖</option>
				<option value="STI050000000000" <c:if test="${bean.parentNum == 'STI050000000000'}">selected="selected"</c:if>>生活超市</option>
		</select>
	</li>
	
	<li>
		<label>是否置顶：</label>
		<select name="isHot">
				<option value="" selected="selected">请选择</option>
				<option value="1" <c:if test="${bean.isHot == 1}">selected="selected"</c:if>>是</option>
				<option value="2" <c:if test="${bean.isHot == 2}">selected="selected"</c:if>>否</option>
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



</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		
			<th width="center" align="center">序号</th>
			<th width="center" align="center">店铺类型</th>
			<th width="center" align="center">店铺名称</th>
			<th width="center" align="center">起送价格</th>
			<th width="center" align="center">联系人 </th>
			<th width="center" align="center">联系手机</th>
			<th width="center" align="center">区域 </th>
			<!-- <th width="center" align="center">销量</th>
			<th width="center" align="center">评论数</th>
			<th width="center" align="center">在售商品数</th> -->
			<th width="center" align="center">是否推荐</th>
			<th width="center" align="center">是否热门</th>
			<th width="center" align="center">是否优惠卷</th>
			<th width="center" align="center">是否营业</th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<td>
				<input type="hidden" name="shopId" value="${item.shopId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.shopType.shopTypeName}</td>
				<td>
				<a href="javascript:void(0)" onclick="add('shopinfo/details?shopNum=${item.shopNum}','商铺详情',900,600,'main_')"><span style="color: blue;">${item.shopName}</span></a>
				</td>
				<td>${item.startPrice}</td>
				<td>${item.linkMan}</td>
				<td>${item.linkTel}</td>
				<td><ry:show parentCode="${item.province}" itemCode="${item.province}" type="2"></ry:show>
				<ry:show parentCode="${item.province}" itemCode="${item.city}" type="3"></ry:show>
				<ry:show parentCode="${item.city}" itemCode="${item.areas}" type="4"></ry:show>
				</td>
				<!-- <td>${item.saleCount}</td>
				<td>${item.commentCount}</td>
				<td>${item.goodsTotalCount}</td> -->
				<td>${item.isRecommend ==1?'是':'否' }</td>
				<td>${item.isHot ==1?'是':'否' }</td>
				<td>${item.isCoupon ==1?'是':'否' }</td>
				<td>${item.businessStatus ==1?'是':'否'}</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

