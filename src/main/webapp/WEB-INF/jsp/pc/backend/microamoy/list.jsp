<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="microamoy/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
        <input type="hidden" name="title" value="${param.title}" /> 
       
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="microamoy/list" method="post">
<div class="searchBar">
<ul class="searchContent">
    
    <li ><label>标题：</label>
		<input type="text" name="title" value="${bean.title }">
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
<!--<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH }" >
	<li><a class="add" onclick="add('microamoy/toEdit','添加微淘信息',900,600,'main_')" ><span>添加</span></a></li>
	</ry:authorize>
-->
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		   <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>-->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">标题</th>
			<th width="center" align="center">店铺名称</th>
			<th width="center" align="center">创建时间</th>
			<th width="center" align="center">浏览次数</th>
			<th width="center" align="center">评论条数</th>
			<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH }" >
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<!--<td><input name="ids" value="${item.microAmoyId }" type="checkbox"></td>-->
				<td>
				<input type="hidden" name="microAmoyId" value="${item.microAmoyId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td style="text-align: left;"> <a href="javascript:void(0)" onclick="window.open('app/external/microamoy/${item.microAmoyNum}/detail','详情','resizable=no,location=no,status=no,width=400,height=500,top=200,left=500')"><span style="color: blue;">
				${fn:substring(item.title,0,14)}<c:if test="${fn:length(item.title)>=15}">...</c:if>
				</span>
				</a></td>
				<td style="text-align: left;">${item.shopName}</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${item.browseCount}</td>
				<td>${item.commentCount}</td>
				<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH }" >
				<td>
					<a title="修改微淘信息"   onclick="add('microamoy/toEdit?microAmoyNum=${item.microAmoyNum}','修改微淘信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
					<a title="您确定删除微淘信息吗？" target="ajaxTodo" href="microamoy/del?microAmoyNum=${item.microAmoyNum}" class="btnDel">删除</a>
				</td>
				</ry:authorize>	
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

