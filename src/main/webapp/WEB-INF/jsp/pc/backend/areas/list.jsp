<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="areas/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="level" value="${bean.level}" /> 
       <input type="hidden" name="isOpen" value="${bean.isOpen}" /> 
      <input type="hidden" name="area" value="${bean.area}" /> 
      <input type="hidden" name="cityName" value="${bean.cityName}" /> 
       
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="areas/list" method="post">
<div class="searchBar">
<ul class="searchContent">
   <li ><label>地区：</label>
		<input type="text" name="area" value="${bean.area }">
	</li>
	 <li ><label>城市：</label>
		<input type="text" name="cityName" value="${bean.cityName}">
	</li>
	
	<li >
	<label>开通状态</label>
	<select name="isOpen">
		<option value="">请选择</option>
		<option value="1" <c:if test="${bean.isOpen == 1}">selected="selected"</c:if>>已开通</option>
		<option value="2" <c:if test="${bean.isOpen == 2}">selected="selected"</c:if>>未开通</option>
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
<ul class="toolBar"><!--
	
	<li><a title="确实要开启吗?" target="selectedTodo" rel="ids" postType="string" href="areas/isOpen?type=1" class="edit"><span>开通</span></a></li>	
	<li><a title="确实要关闭吗?" target="selectedTodo" rel="ids" postType="string" href="areas/isOpen?type=2" class="icon"><span>关闭开通</span></a></li>		
--></ul>
</div>
</div>

<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!-- <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">地区</th>
			<th width="center" align="center">城市</th>
			<th width="center" align="center">级别</th>
			<th width="center" align="center">状态：</th>
			
			
		</tr>
	</thead>
	<tbody >
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr >
				<!--<td><input name="ids" value="${item.areaid }" type="checkbox"></td> -->
				<td class="unitBoxs">
				<input type="hidden" name="areaid" id="areaid" value="${item.areaid }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td >${item.area}</td>
				<td >${item.cityName}</td>
				<td>
				<select name="level" onchange="savelevel(this.options[this.options.selectedIndex].value,'${item.areaid }')" style="height: 21px">
					<option value="">请选择</option>
					<option value="1" <c:if test="${item.level == 1}">selected="selected"</c:if>>市级</option>
					<option value="2" <c:if test="${item.level == 2}">selected="selected"</c:if>>区县级</option>
				</select>
				</td>
				<td><c:if test="${item.isOpen==1}">
					<a href="javascript:void(0)" onclick="makeTop(2,'${item.areaid}','is_open','areas/isOpen','t_areas','areaid')"><span style="color: green">已开通</span></a>
				</c:if>
				<c:if test="${item.isOpen==2}">
					<a href="javascript:void(0)" onclick="makeTop(1,'${item.areaid}','is_open','areas/isOpen','t_areas','areaid')"><span style="color: red">未开通</span></a>
				</c:if>
				</td>
				</tr>
		</c:forEach>
		<script>
			function savelevel(level,areaid){
				$.ajax({
					url:'areas/savelevel',
					data:{"level":level,"areaid":areaid},

				});
			}
		</script>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>