<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="PROBLEM_TYPE" bingdingName="problemtype"></ry:binding>
<form id="pagerForm" method="post" action="commonProblen/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
</form>




<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">

	<!-- <li><a class="add" onclick="add('commonProblen/toEdit','添加问题信息',900,550,'main_')" ><span>添加</span></a></li> -->

</ul>
</div>
</div>

<table class="table" width="100%" layoutH="90">
	<thead>
		<tr>
		  
			<th width="center" align="center">序号</th>
			
			
			<th width="center" align="center">协议名称 </th>
			<th width="center" align="center">创建时间 </th>
		
		
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	 <tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<td><input type="hidden" name="goodsId" value="${item.commonProblemId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
			
				<td>${item.title}</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>	
				
				
				<td>
				
						<a title="修改协议"   onclick="add('commonproblem/toEdit?commonProblemNum=${item.commonProblemNum}','修改协议',900,650,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>
						
				</td>
				</tr>
		</c:forEach>
	 </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
