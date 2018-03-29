<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>

<form id="pagerForm" method="post" targetType="dialog" action="bankBind/getFindBack">
	<input type="hidden" name="pageNum" value="${pageList.pageNum }" />
	<input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="shopName" value="${param.shopName}" />
	<input type="hidden" name="province" value="${param.province}" />
	<input type="hidden" name="city" value="${param.city}" />
	<input type="hidden" name="areas" value="${param.areas}" />
	
</form>


<div class="pageHeader">

	<form onsubmit="return dwzSearch(this, 'dialog');" action="bankBind/getFindBack" method="post" >
	<div class="searchBar">
		<ul class="searchContent" >
   <li>
		<label>店铺名称：</label>
		<input type="text" name="shopName" value="${bean.shopName }"> 
	</li>
	
	<!--<li style="width: 490px">
		<label style="width: 45px;">区域：</label>
		<%@include file="/WEB-INF/jsp/inc/regionalCascadeList.jsp" %>
		
		<input type="hidden"  id="provinceid_list" value="${bean.province }">
		<input type="hidden" id="citiesid_list" value="${bean.city }">
		<input type="hidden"  id="areaid_list" value="${bean.areas }">
	</li>
  
    --><li style="width: 70px;">
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
		        <th>编号</th>
		        <th width="center" align="center">店铺名称</th>
			    <th width="center" align="center">联系人 </th>
				<th width="center" align="center">联系手机</th>
				<th width="center" align="center">区域 </th>
				
			</tr>
		</thead>
		<tbody>
			 <c:forEach var="item" items="${pageList.result}" varStatus="row" >
	            <tr onclick="javascript:$.bringBack({shopNum:'${item.shopNum }', shopName:'${item.shopName}'})" style="cursor:pointer;">
			        <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td> 
			        <td>${item.shopName}</td>
					<td>${item.linkMan}</td>
					<td>${item.linkTel}</td>
					<td><ry:show parentCode="${item.province}" itemCode="${item.province}" type="2"></ry:show>
					 <ry:show parentCode="${item.province}" itemCode="${item.city}" type="3"></ry:show>
					<ry:show parentCode="${item.city}" itemCode="${item.areas}" type="4"></ry:show>
					</td>
					
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