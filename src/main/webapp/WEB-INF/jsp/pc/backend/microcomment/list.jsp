<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="microcomment/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
    
     <input type="hidden" name="status" value="${param.status}" /> 
     <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
     <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
     
      
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="microcomment/list" method="post">
<div class="searchBar">
<ul class="searchContent"><!--
    <li>
    	<label>评论用户名</label>
    	<input type="text" value="${bean.user.nickName }" name="userNum"></input>
    </li>
    
    <li>
    	<label>是否开启：</label>
    	<select name="status">
    		<option value="">请选择</option>
    		<option value="1" <c:if test="${bean.status == 1}">selected="selected"</c:if>>开启</option>
    		<option value="2" <c:if test="${bean.status == 2}">selected="selected"</c:if>>关闭</option>
    	</select>
    </li>-->
    
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
<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
	<!-- <li><a title="确实要开启显示吗?" target="selectedTodo" rel="ids" postType="string" href="microcomment/ishomeshow?type=1" class="edit"><span>开启显示</span></a></li>	-->
	<li><a title="确实要批量删除吗?" target="selectedTodo" rel="ids" postType="string" href="microcomment/del" class="delete"><span>批量删除</span></a></li>		
	 </ry:authorize>
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
		   <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
		   </ry:authorize>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">微淘名称</th>
			<th width="center" align="center">会员名</th>
			<th width="center" align="center">评论时间</th>
			<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<td><input name="ids" value="${item.microCommentNum }" type="checkbox"></td>
				</ry:authorize>
				<td>
				<input type="hidden" name="commentId" value="${item.microCommentId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td style="text-align: left;"><a href="javascript:void(0)" onclick="openNav('microcomment/toDetailsEdit?microCommentNum=${item.microCommentNum }','评论详情','main_index3')" ><span style="color: blue;">
				${fn:substring(item.title,0,14)}<c:if test="${fn:length(item.title)>=15}">...</c:if>
				</span></a></td>
				<td>${item.user.nickName}</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<td>
					<a title="您确定删除商品吗？" target="ajaxTodo" href="microcomment/del?microCommentNum=${item.microCommentNum}" class="btnDel">删除</a>
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>
