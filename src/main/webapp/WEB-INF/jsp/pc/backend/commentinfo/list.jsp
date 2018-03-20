<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="commentinfo/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="flag1" value="${bean.flag1}">
	<input type="hidden" name="mealName" value="${bean.mealName}">
     <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
     <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="commentinfo/list" method="post">
<div class="searchBar">
<ul class="searchContent">

	<li>
		<label>评论用户</label>
		<input type="text" name="flag1" value="${bean.flag1}">
	</li>

	<li>
		<label>商品名称：</label>
		<input type="text" name="mealName" value="${bean.mealName}">
	</li>

    <li style="width: 370px">
		<label>评论时间：</label>
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
<div class="pageContent">
<div class="panelBar">
<ul class="toolBar">
	<li><a title="确实要开启显示吗?" target="selectedTodo" rel="ids" postType="string" href="commentinfo/ishomeshow?type=1" class="edit"><span>开启显示</span></a></li>
	<li><a title="确实要关闭显示吗?" target="selectedTodo" rel="ids" postType="string" href="commentinfo/ishomeshow?type=2" class="icon"><span>关闭显示</span></a></li>
	<li><a title="确实要删除吗?" target="selectedTodo" rel="ids" postType="string" href="commentinfo/del" class="delete"><span>批量删除</span></a></li>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
			<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">商品名称</th>
			<th width="center" align="center">评论用户</th>
			<th width="center" align="center">分值</th>
			<th width="center" align="center">内容</th>
			<th width="center" align="center">更新时间</th>
			<th width="center" align="center">评论时间</th>
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td><input name="ids" value="${item.commentId }" type="checkbox"></td>
				<td>
					<input type="hidden" name="commentId" value="${item.commentId }">
					${(pageList.pageNum-1)*pageList.numPerPage+row.count}
				</td>
				<td>${item.mealName}</td>
				<td>${item.user.nickName}</td>
				<td>${item.score}</td>
				<td>${fn:substring(item.content,0,15)}<c:if test="${fn:length(item.content)>15}">...</c:if></td>
				<td><ry:formatDate date="${item.updateTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${item.status == 1}">
						<a title="关闭该评论" target="ajaxTodo" href="commentinfo/ishomeshow?type=2&ids=${item.commentId}">
							<span style="color: green">已启用</span>
						</a>
					</c:if>
					<c:if test="${item.status == 2}">
						<a title="开启该评论" target="ajaxTodo" href="commentinfo/ishomeshow?type=1&ids=${item.commentId}">
							<span style="color: red">已关闭</span>
						</a>
					</c:if>
					&nbsp;
					<a title="修改该评论" href="javascript:void(0)" onclick="add('commentinfo/detail?commentNum=${item.commentNum}','查看评论内容',500,200,'main_')"><span style="color: green">修改</span></a>
					&nbsp;
					<a title="删除该评论" target="ajaxTodo" href="commentinfo/del?ids=${item.commentId}">
						<span style="color: green">删除</span>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

