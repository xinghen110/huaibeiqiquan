<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="noticeInfo/list">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
       <input type="hidden" name="title" value="${bean.title}" /> 
       <input type="hidden" name="receiveType" value="${bean.receiveType}" /> 
       <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
       
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="noticeInfo/list" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>标题：</label>
		<input type="text" name="title" value="${bean.title }"> 
	</li>
	
	<li>
		<label>接受类型：</label>
		<select name="receiveType">
			<option value="" >请选择</option>
			<option value="99" <c:if test="${bean.receiveType == 99}">selected="selected"</c:if>>全系统</option>
			<option value="2" <c:if test="${bean.receiveType == 2}">selected="selected"</c:if>>店铺用户</option>
			<option value="4" <c:if test="${bean.receiveType == 4}">selected="selected"</c:if>>物流用户</option>
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

	<li><a class="add" onclick="add('noticeInfo/toEdit','添加公告信息',900,550,'main_')" ><span>添加</span></a></li>


	<!--<li><a title="确定要开启吗?" target="selectedTodo" rel="ids" postType="string" href="noticeInfo/isStatus?type=1" class="edit"><span>开启</span></a></li>	
	<li><a title="确定要关闭吗?" target="selectedTodo" rel="ids" postType="string" href="noticeInfo/isStatus?type=2" class="icon"><span>关闭</span></a></li>

--></ul>
</div>
</div>

<table class="table" width="100%" layoutH="133">
	<thead>
		<tr>
		 
			<th width="center" align="center">序号</th>
			
			<th width="center" align="center">标题</th>
			<th width="center" align="center">接受类型 </th>
			<th width="center" align="center">创建时间 </th>
			<th width="center" align="center">状态 </th>
			 
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	 <tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<td><input type="hidden" name="noticeInfoId" value="${item.noticeInfoId }">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td style="text-align: left;">${item.title }</td>
				<td><c:if test="${item.receiveType ==99}">全系统</c:if>
					<c:if test="${item.receiveType ==2}">店铺用户</c:if>
					<c:if test="${item.receiveType ==4}">物流用户</c:if></td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>	
				<td><!--<span class="${item.status ==1?'status-success' : 'status-danger'}">${item.status ==1? '开启' : '关闭'}</span></td>-->
					<c:if test="${item.status==1}">
					<a href="javascript:void(0)" onclick="makeTop(2,'${item.noticeInfoNum}','status','noticeInfo/isStatus','t_notice_info','notice_info_num')"><span style="color: green">开启</span></a>
				</c:if>
				<c:if test="${item.status==2}">
					<a href="javascript:void(0)" onclick="makeTop(1,'${item.noticeInfoNum}','status','noticeInfo/isStatus','t_notice_info','notice_info_num')"><span style="color: red">关闭</span></a>
				</c:if>
				</td>
				 
				<td>
				
						<a title="修改公告"   onclick="add('noticeInfo/toEdit?noticeInfoNum=${item.noticeInfoNum}','修改公告',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
						<a title="您确定删除公告吗？" target="ajaxTodo" href="noticeInfo/del?noticeInfoNum=${item.noticeInfoNum}" class="btnDel">删除</a>
			
						<a title="公告详情" onclick="add('noticeInfo/toDetailsEdit?noticeInfoNum=${item.noticeInfoNum }','公告详情',900,600,'main_')"  rel="users_saveedit" class="btnLook">商品详情</a>				
				</td>
				</tr>
		</c:forEach>
	 </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

