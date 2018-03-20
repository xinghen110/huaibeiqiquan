<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding type="3"></ry:binding>
<ry:binding parentCode="USERSEX" bingdingName="usersexs"></ry:binding>
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
	<input type="hidden" name="userType" value="${param.userType}" />
	  <input type="hidden" name="province" value="${param.province}" /> 
     <input type="hidden" name="city" value="${param.city}" /> 
    
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="user/users?userType=${tuser.userType}" method="post">
	<div class="searchBar">
		
		<ul class="searchContent">
			<li>
					<label>登录名称：</label>
					<input type="text" name="loginName" value="${tuser.loginName }" />
			</li>
			<li>
					<label>用户昵称：</label>
					<input type="text" name="nickName" value="${tuser.nickName }"/>
			</li>
			
			<li ><label>用户状态：</label>
		<select name="userStatus">
			<option value="">请选择</option>
			<option value="2" <c:if test="${tuser.userStatus == 2}">selected="selected"</c:if>>禁用</option>
			<option value="1" <c:if test="${tuser.userStatus == 1}">selected="selected"</c:if>>正常</option>
		</select>
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
		<ul class="toolBar">
		<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
			<li><a class="add" onclick="add('user/toUserEdit?userType=${userType}','添加信息',900,500,'main_')"><span>添加</span></a></li>
			</ry:authorize>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="135">

		<thead>
			<tr>
			
				<th   align="center">序号</th>
				<th  align="center">登录名称</th>
				<th  align="center">用户昵称</th>
				<th  align="center">性别</th>
				<th  align="center">区域 </th>
				<th  align="center">创建时间</th>
				<th  align="center" >用户状态</th>
				<th  align="center" >工作状态</th>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<th  >操作</th>
				</ry:authorize>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr target="sid_user" rel="1">
			
				<td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.loginName}</td>
				<td>${item.nickName}</td>
				<td>
				<c:if test="${item.userSex!=0}">
				<ry:show parentCode="USERSEX" itemCode="${item.userSex}"></ry:show>
				</c:if></td>
				<td><ry:show parentCode="${item.province}" itemCode="${item.province}" type="2"></ry:show>
				<ry:show parentCode="${item.province}" itemCode="${item.city}" type="3"></ry:show>
				<ry:show parentCode="${item.city}" itemCode="${item.area}" type="4"></ry:show>
				</td>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<c:if test="${item.userStatus==1}">
					<a href="javascript:void(0)" style="text-decoration: underline" onclick="makeTop(2,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: green">启用</span></a>
					</c:if>
					<c:if test="${item.userStatus==2}">
					<a href="javascript:void(0)" style="text-decoration: underline" onclick="makeTop(1,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: red">禁用</span></a>
					</c:if>
					</ry:authorize>
					<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
					${item.userStatus==1? '启用' : '禁用'}
					</ry:authorize>
					
					</td>
				<td>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
					<c:if test="${item.workStatus==1}">
					<a href="javascript:void(0)" style="text-decoration: underline" onclick="makeTop(2,'${item.userNum}','work_status','user/updateQuery','t_user','user_num')"><span style="color: green">上线</span></a>
					</c:if>
					<c:if test="${item.workStatus==2}">
					<a href="javascript:void(0)" style="text-decoration: underline" onclick="makeTop(1,'${item.userNum}','work_status','user/updateQuery','t_user','user_num')"><span style="color: red">下线</span></a>
					</c:if>
				</ry:authorize>
				<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">
				${item.workStatus==1? '上线' : '下线'}
				</ry:authorize>
				</td>
				<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">
				<td>
				<a title="您确定重置用户 “${item.loginName}”的密码吗？" target="ajaxTodo" href="user/resetPassword?userId=${item.userId }"  class="btnView">重置密码</a>
				<a class="btnEdit" onclick="add('user/toUserEdit?userId=${item.userId }&userType=${item.userType}','修改用户信息',950,500,'main_')"><span>修改用户</span></a>	
					<a title="您确定删除用户 ”${item.loginName}“ 吗？" target="ajaxTodo" href="user/del?userId=${item.userId}&loginName=${item.loginName}&userType=${item.userType}&userStatus=${item.userStatus}" class="btnDel">删除</a>
				</td>
				</ry:authorize>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
