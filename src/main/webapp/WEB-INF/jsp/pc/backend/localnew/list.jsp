<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="localnew/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="title" value="${bean.title}" /> 
      <input type="hidden" name="source" value="${bean.source}" /> 
      <input type="hidden" name="shopTypeNum"  value="${param.shopTypeNum}"/>
      <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
	<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
       
</form>

<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="localnew/list" method="post">
<div class="searchBar">
<ul class="searchContent">
    <li ><label>标题：</label>
		<input type="text" name="title" value="${bean.title}">
	</li>
	<li ><label>来源：</label>
		<input type="text" name="source" value="${bean.source}">
	</li>
	<li>
		<label>新闻类型：</label>
		<select name="shopTypeNum">
			<option value="">--请选择--</option>
				<c:forEach items="${shopTypeList}"  var="hy">
					<option value="${hy.shopTypeNum }" ${hy.shopTypeNum==bean.shopTypeNum?'selected':'' }>${hy.shopTypeName }</option>
				</c:forEach>
					</select>
	</li>
	
	<li style="width: 370px">
	<label>创建时间：</label>
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
	<ry:authorize ifAnyGranted="${authMap.SYSATEM_AUTH}" >
		<li><a class="add" onclick="add('localnew/toEdit','添加本地新闻信息',900,600,'main_')" ><span>添加</span></a></li>
		</ry:authorize>

</ul>
</div>
</div>
<!--<table class="table" width="100%" layoutH="133">
--><table class="table" width="100%" layoutH="135">
	<thead>
		<tr>
		  
			<th width="center" align="center">序号</th>
			<th width="center" align="center">编号</th>
			<th width="center" align="center">标题</th>
			<th width="center" align="center">新闻类型</th>
			<th width="center" align="center">来源</th>
			<th width="center" align="center">评论数</th>
			<th width="center" align="center">浏览量</th>
			<th width="center" align="center">推荐</th>
			<th width="center" align="center">置顶</th>
			<th width="center" align="center">更新时间</th>
			<th width="center" align="center">创建时间</th>
			<ry:authorize ifAnyGranted="${authMap.SYSATEM_AUTH}" >
			<th width="center" align="center">操作</th>
			</ry:authorize>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				
				<td>
				<input type="hidden" name="localNewNum" value="${item.localNewNum}">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.localNewNum}</td>
				<td >
				<a href="javascript:void(0)" onclick="window.open('app/external/localNew/${item.localNewNum}','详情','resizable=no,location=no,status=no,width=400,height=500,top=200,left=500')"><span style="color: blue;">${item.title}</span></a>
				</td>
				<td>${item.shopType.shopTypeName }</td>
				<td >${item.source}</td>
				<td >${item.commentCount}</td>
				<td >${item.browseCount}</td>
				
				<td>
				<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}" >
				${item.isRecommend ==1? '是' : '否'}
				</ry:authorize>
					<ry:authorize ifAnyGranted="${authMap.SYSATEM_AUTH}" >
					<c:if test="${item.isRecommend==1}">
					<a href="javascript:void(0)" onclick="makeTop(2,'${item.localNewNum}','is_recommend','localnew/isRecommend','t_local_new','local_new_num')"><span style="color: green">是</span></a>
					</c:if>
					<c:if test="${item.isRecommend==2}">
					<a href="javascript:void(0)" onclick="makeTop(1,'${item.localNewNum}','is_recommend','localnew/isRecommend','t_local_new','local_new_num')"><span style="color: red">否</span></a>
					</c:if>
				</ry:authorize>
				</td>
				<td>
				<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}" >
				${item.isTop ==1? '是' : '否'}
				</ry:authorize>
				<ry:authorize ifAnyGranted="${authMap.SYSATEM_AUTH}" >
				<c:if test="${item.isTop==1}">
					<a href="javascript:void(0)" onclick="makeTop(2,'${item.localNewNum}','is_top','localnew/isTop','t_local_new','local_new_num')"><span style="color: green">是</span></a>
				</c:if>
				<c:if test="${item.isTop==2}">
					<a href="javascript:void(0)" onclick="makeTop(1,'${item.localNewNum}','is_top','localnew/isTop','t_local_new','local_new_num')"><span style="color: red">否</span></a>
				</c:if>
				</ry:authorize>
				</td>
				<td ><ry:formatDate date="${item.pubTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<ry:authorize ifAnyGranted="${authMap.SYSATEM_AUTH}" >
				<td>
					<a title="修改本地新闻信息"   onclick="add('localnew/toEdit?localNewNum=${item.localNewNum}','修改本地新闻信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
					<a title="您确定删除本地新闻信息吗？" target="ajaxTodo" href="localnew/del?localNewNum=${item.localNewNum}" class="btnDel">删除</a>
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>