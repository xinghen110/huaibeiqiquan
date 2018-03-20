<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="user/users?userType=${tuser.userType}">
	<input type="hidden" name="pageNum" value="${pageList.pageNum }" />
	<input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	
	<!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="loginName" value="${param.loginName}"/>
	<input type="hidden" name="nickName" value="${param.nickName}"/>
	<input type="hidden" name="userSex" value="${param.userSex}"/>
	<input type="hidden" name="createTime" value="${param.createTime}" />
	<input type="hidden" name="endDate" value="${param.endDate}" />
	<input type="hidden" name="userStatus" value="${param.userStatus}" />
	<input type="hidden" name="userType" value="${param.userType}" />
	
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="user/users?userType=${tuser.userType}" method="post">
	<div class="searchBar">
		
	<ul class="searchContent">
			<li>
					<label>登录名称:</label>
					<input type="text" name="loginName" value="${tuser.loginName }" />
			</li>
		 
			
	<li ><label>状态：</label>
		<select name="userStatus">
			<option value="">请选择</option>
			<option value="2" <c:if test="${tuser.userStatus == 2}">selected="selected"</c:if>>禁用</option>
			<option value="1" <c:if test="${tuser.userStatus == 1}">selected="selected"</c:if>>启用</option>
		</select>
	</li>
	
	<li style="width: 370px">
		<label>创建时间:</label>
		<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${param.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"/>
		~
		<input id="endDate" style="width: 110px" type="text" name="endDate" value="<fmt:formatDate value="${param.endDate}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'createTime\')}'})"/>
	</li>
				
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">	
	<div class="panelBar">
		<a style="no-repeat; background-size:15px;background-position: 0 4px;color:blue;line-height:23px;"   class="icon" target="dwzExport" targetType="navTab" title="您确定导出这些记录吗?" href="user/exportExcel?userType=${tuser.userType}" >导出EXCEL</a>
		<div style="line-height:25px; float: right">重置密码为：123456&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr>
				 
				<th width="40"  align="center">序号</th>
				<th width="180" align="center">登录名称</th>
			
				<th width="180" align="center">所属店铺</th>
				<th width="70" align="center">归属地</th>
				<th width="100" align="center">创建时间</th>
				<th width="70" >操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr target="sid_user" rel="1">
				<td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.loginName }</td>
				 
				<td>${item.shopInfo.shopName }</td>
				<td>
					<ry:show parentCode="${item.shopInfo.province}" itemCode="${item.shopInfo.city}" type="3"></ry:show>
					<ry:show parentCode="${item.shopInfo.city}" itemCode="${item.shopInfo.area}" type="4"></ry:show>
				</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
				<c:if test="${item.userStatus==1}">
						<a href="javascript:void(0)" onclick="makeTop(2,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: green">启用</span></a>
					</c:if>
					<c:if test="${item.userStatus==2}">
						<a href="javascript:void(0)" onclick="makeTop(1,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: red">禁用</span></a>
					</c:if>
					&nbsp;&nbsp;
					<a title="您确定重置用户 “${item.loginName}”的密码吗？" target="ajaxTodo" href="user/resetPassword?userId=${item.userId }"><span style="color: green">重置密码</span></a>
									
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
