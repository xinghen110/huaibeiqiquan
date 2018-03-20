<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>

<form id="pagerForm" method="post" action="complaintInfo/list?type=${bean.type}">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
      <input type="hidden" name="orderNum" value="${param.orderNum}" /> 
      
       <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
    	<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
       
</form>

<!--模糊查询-->
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="complaintInfo/list?type=${bean.type }" method="post">
<div class="searchBar">
<ul class="searchContent">

	<!-- <li>
    	<label>订单号：</label>
    	<input type="text" value="${bean.orderNum }" name="orderNum"></input>
    </li> -->
	
	<li style="width: 370px">
		<label>投诉时间：</label>
		<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
		~
		<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
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
<!--操作按钮-->
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
	
</ul>
</div>
</div>


<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			-->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">会员名</th>
			<c:if test="${bean.type==1}">
			<th width="center" align="center">店铺名称</th>
			</c:if>
			<c:if test="${bean.type==2}">
			<th width="center" align="center">物流人员名称</th>
			</c:if>
			<th width="center" align="center">投诉时间 </th>
			</tr>
	</thead>
	 <tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<!--<td><input name="ids" value="${item.complaintInfoId }" type="checkbox"></td>
			-->
			<td><input type="hidden" name="complaintInfoNum" value="${item.complaintInfoNum }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.user.nickName }</td>
				
				<c:if test="${bean.type==1}">
				<td><a href="javascript:void(0)" onclick="openNav('complaintInfo/toDetailsEdit?complaintInfoNum=${item.complaintInfoNum}','投诉详情','main_index3')"><span style="color: blue;">${item.shopInfo.shopName }</span></a></td>
				</c:if>
				
				<c:if test="${bean.type==2}">
				<td><a href="javascript:void(0)" onclick="openNav('complaintInfo/toDetailsEdit?complaintInfoNum=${item.complaintInfoNum}','投诉详情','main_index3')" ><span style="color: blue;">${item.tUser.nickName }</span></a></td>
				</c:if>
				
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss " /></td>
				</tr>
				
		</c:forEach>
	 </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>