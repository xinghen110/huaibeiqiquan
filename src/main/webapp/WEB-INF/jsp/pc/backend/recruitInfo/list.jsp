<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="recruitInfo/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
      <input type="hidden" name="title" value="${bean.title}" /> 
        <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
		<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="recruitInfo/list" method="post">
<div class="searchBar">
<ul class="searchContent">
    <li ><label>招聘标题：</label>
		<input type="text" name="title" value="${bean.title}">
	</li>
	
	<li style="width: 370px">
	<label>发布时间：</label>
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
	<!-- <li><a class="add" onclick="add('recruitInfo/toEdit','添加招聘信息信息',900,600,'main_')" ><span>添加</span></a></li> -->
</ul>
</div>
</div>
<table class="table" width="100%" layoutH="135">
	<thead>
		<tr>
		  
			<th width="center" align="center">序号</th>
			<th width="center" align="center">招聘标题</th>
			<th width="center" align="center">联系电话</th>
			<th width="center" align="center">单位名称</th>
			 
			<th width="center" align="center">置顶</th>
			
			<th width="center" align="center">发布人</th>
			<th width="center" align="center">发布时间</th>
			<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
			<th width="center" align="center">操作</th>
			</ry:authorize>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				
				<td>
				<input type="hidden" name="recruitInfoNum" value="${item.recruitInfoNum}">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td >
				<a href="javascript:void(0)" onclick="window.open('app/external/inforecruit/${item.recruitInfoNum}','详情','resizable=no,location=no,status=no,width=400,height=500,top=200,left=500')"><span style="color: blue;">${fn:substring(item.title,0,14)}<c:if test="${fn:length(item.title)>=15}">...</c:if></span></a>
				</td>
				<td >${item.linkTel}</td>
				<td >${fn:substring(item.unitName,0,14)}<c:if test="${fn:length(item.unitName)>=15}">...</c:if></td>
				<td>
				<ry:authorize ifAnyGranted="${authMap.SYSATEM_AUTH}" >
					<c:if test="${item.isTop==1}">
						<a href="javascript:void(0)" onclick="makeTop(2,'${item.recruitInfoNum}','is_top','recruitInfo/isTop','t_info_recruit','recruit_info_num')"><span style="color: green">是</span></a>
					</c:if>
					<c:if test="${item.isTop==2}">
						<a href="javascript:void(0)" onclick="makeTop(1,'${item.recruitInfoNum}','is_top','recruitInfo/isTop','t_info_recruit','recruit_info_num')"><span style="color: red">否</span></a>
					</c:if>
				</ry:authorize>
				
					<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}" >
						${item.isTop ==1? '是' : '否'}
					</ry:authorize>
				</td>
				<td >${item.user.nickName}</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<td>
					<!-- <a title="修改招聘词信息"   onclick="add('recruitInfo/toEdit?recruitInfoNum=${item.recruitInfoNum}','修改招聘信息',900,600,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>	 -->			
					<a title="您确定删除招聘信息吗？" target="ajaxTodo" href="recruitInfo/del?recruitInfoNum=${item.recruitInfoNum}" class="btnDel">删除</a>
				</td>
				</ry:authorize>
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>