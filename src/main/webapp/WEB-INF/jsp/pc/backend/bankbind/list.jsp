<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="bankBind/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
      <input type="hidden" name="userName" value="${bean.userName}" /> 
      <input type="hidden" name="cardNo" value="${bean.cardNo}" /> 
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="bankBind/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li ><label>持卡人姓名：</label>
		<input type="text" name="userName" value="${bean.userName }">
	</li>

	
    <li ><label>银行卡号：</label>
		<input type="text" name="cardNo" value="${bean.cardNo}">
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
<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
	<li><a class="add" onclick="add('bankBind/toEdit','添加银行卡',900,600,'main_')" ><span>添加</span></a></li>
	</ry:authorize>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="135">
	<thead>
		<tr>
		   
			<th width="center" align="center">序号</th>
			<th width="center" align="center">店铺名</th>
			<th width="center" align="center">持卡人姓名</th>
			<th width="center" align="center">银行卡号</th>
			<th width="center" align="center">银行开户行</th>
			<th width="center" align="center">创建时间</th>
			<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				
				<td>
				<input type="hidden" name="bankBindNum" value="${item.bankBindNum }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td >${item.shopName}</td>
				<td>${item.userName }</td>
				<td>${item.cardNo }</td>
				<td><ry:show parentCode="BANK_NAME" itemCode="${item.bankName }"></ry:show></td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<td>
					<a title="修改同城折扣"   onclick="add('bankBind/toEdit?bankBindNum=${item.bankBindNum }','修改银行卡信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
