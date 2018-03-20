<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX" bingdingName="usersexs"></ry:binding>
<form id="pagerForm" method="post" targetType="dialog" action="shopinfo/jishi">
	<input type="hidden" name="pageNum" value="${pageList.pageNum }" />
	<input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="userName" value="${param.userName}" />
	<input type="hidden" name="userSex" value="${param.userSex}"/>
</form>


<div class="pageHeader">

	<form onsubmit="return dwzSearch(this, 'dialog');" action="shopinfo/jishi" method="post" >
	<div class="searchBar">
		<ul class="searchContent" >
   	<li>
		<label>技师名称：</label>
		<input type="text" name="userName" value="${bean.userName}"> 
		<input type="hidden" name="shopInfoNum" value="${bean.shopInfoNum}">
	</li>
	<li>
		<label>技师性别：</label>
			<select name="userSex" >
				<option value="">请选择性别</option>
				<c:forEach items="${usersexs}" var="item">
				<option value="${item.itemCode }" ${item.itemCode==bean.userSex ? 'selected':'' } >${item.itemName }</option>
		        </c:forEach>
		   </select>
	</li>
	
	<li style="width: 70px;">
	   <div class="buttonActive"><div class="buttonContent"><button  TYPE="submit">查询</button></div></div>
	</li>
</ul>
 
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="128" targetType="dialog" style="width:900px;">
		<thead>
			<tr>
		        <th width="center" align="center">编号</th>
		        <th width="center" align="center">技师名称</th>
		     	<th width="center" align="center">性别</th>
				<th width="center" align="center">服务类型</th>
				
			</tr>
		</thead>
		<tbody>
			 <c:forEach var="item" items="${pageList.result}" varStatus="row" >
	            <tr >
			        <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td> 
			        <td>${item.userName}</td>
			        <td><ry:show itemCode="${item.userSex}" parentCode="USERSEX"></ry:show></td>
					<td>${item.typeName}</td>
					
	     		</tr>
         </c:forEach>
		</tbody>
	</table>
		<div class="panelBar" style="clear:both;">
		<div class="pages" style="width: 250px;height:25px">
			<span style="height:25px;display:inline-block">
			<p style="float:left;height:25px;line-height:20px"><c:if test="${pageList.totalCount!=0 }">
			      共 ${pageList.totalPage}页
			 
			</c:if>
			每页显示条数</p><select  style="width: 60px;float:left;height: 21px"  name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
				<option value="10" ${pageList.numPerPage==10?'selected':''}>10</option>
				<option value="50" ${pageList.numPerPage==50?'selected':''}>50</option>
				<option value="300" ${pageList.numPerPage==300?'selected':''}>300</option>
				<option value="500" ${pageList.numPerPage==500?'selected':''}>500</option>
			</select>共 ${pageList.totalCount }条数据
			</span>   
		</div>
		<div class="pagination"  targetType="dialog" totalCount="${pageList.totalCount}" numPerPage="${pageList.numPerPage }" pageNumShown="10" currentPage="${pageList.currentPage}"></div>
	</div> 
	<!-- 分页 -->
</div>