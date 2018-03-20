<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="REFUND_STATUS" bingdingName="refundStatus"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="userrefundapplication/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="userNum" value="${bean.userNum}" /> 
       <input type="hidden" name="orderNum" value="${bean.orderNum}" /> 
       <input type="hidden" name="status" value="${bean.status}" /> 
       <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="handlestartTime" value='<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="handleTime" value='<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd"/>' /> 
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="userrefundapplication/list?refundType=${bean.refundType}" method="post">
<div class="searchBar">
<ul class="searchContent">
    
    <li ><label>用户编号：</label>
		<input type="text" name="userNum" value="${bean.userNum }">
	</li>
	
	<li ><label>订单编号：</label>
		<input type="text" name="orderNum" value="${bean.orderNum}">
	</li>
	
	
	
	<li style="width: 370px">
		<label>退货时间：</label>
		<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
		~
		<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
	</li>
	
	<li style="width: 370px">
		<label>处理时间：</label>
		<input id="handlestartTime" style="width: 110px" type="text" name="handlestartTime" value="<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'handleTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'handleTime\')}'})"/>
		~
		<input id="handleTime" style="width: 110px" type="text" name="handleTime" value="<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'handlestartTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'handlestartTime\')}'})"/>
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
			<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" ><th width="center" align="center">店铺</th></ry:authorize>
			<th width="center" align="center">订单号</th>
			<th width="center" align="center">用户编号</th>
			<th width="center" align="center">用户名称</th>
			<!--<th width="center" align="center">退款金额</th>
			<th width="center" align="center">退款状态</th>
			-->
			<th width="center" align="center">退货时间</th>
			<th width="center" align="center">处理时间</th>
			<!--<th width="center" align="center">操作</th>
		--></tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
				<input type="hidden" name="userRefundApplicationId" value="${item.userRefundApplicationId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}" ><td>${item.shopInfo.shopName}</td></ry:authorize>
				<td><a href="javascript:void(0)" onclick="openNav('userrefundapplication/toDetailsEdit?userRefundApplicationNum=${item.userRefundApplicationNum }&refundType=${refundType }','换货申请详情','main_index3')"><span style="color: blue;">${item.orderNum }</span></a></td>
				<td>${item.userNum }</td>
				<td>${item.user.nickName}</td>
				<!--<td>${item.refundMoney}</td>
				<td><ry:show parentCode="REFUND_STATUS" itemCode="${item.status}"></ry:show>
				</td>
				-->
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td><ry:formatDate date="${item.handleTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<!--<td>
				<a title="退货申请详情" onclick="openNav('userrefundapplication/toDetailsEdit?userRefundApplicationNum=${item.userRefundApplicationNum }','退货申请详情','main_index3')"  rel="users_saveedit" class="btnLook">退款申请详情</a>
				</td>
				--></tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>