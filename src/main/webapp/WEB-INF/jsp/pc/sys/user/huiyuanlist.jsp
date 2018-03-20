<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX,ORG_TYPE,MEMBER_LEVAL" bingdingName="usersexs,orgTypeList,memberLevel"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="user/users?userType=${tuser.userType}">
	<input type="hidden" name="pageNum" value="${pageList.pageNum }" />
	<input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	
	<!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="endDate" value="${tuser.endDate}">
	<input type="hidden" name="createTime" value="${tuser.createTime}">
	<input type="hidden" name="nickName" value="${tuser.nickName }">
	<input type="hidden" name="isMember" value="${tuser.isMember }">
	<input type="hidden" name="memberLevel" value="${tuser.memberLevel }">
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="user/users?userType=${tuser.userType}" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<label>昵称：</label>
					<input type="text" name="nickName" value="${tuser.nickName }"/>
				</td>
				<td>
					<label>是否是会员：</label>
					<select name="isMember">
						<option value="" selected="selected">请选择</option>
						<option value="1" <c:if test="${tuser.isMember == 1}">selected="selected"</c:if>>是</option>
						<option value="2" <c:if test="${tuser.isMember == 2}">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td>
					<label>会员等级：</label>
					<select name="memberLevel">
						<option value="" selected="selected">请选择</option>
						<c:forEach items="${memberLevel}" var="items">
							<option value="${items.itemCode}" <c:if test="${items.itemCode==tuser.memberLevel}">selected</c:if>>${items.itemName}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label>加入时间：</label>
					<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${tuser.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"/>
					~
					<input id="endDate" style="width: 110px" type="text" name="endDate" value="<fmt:formatDate value="${tuser.endDate}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'createTime\')}'})"/>
				</td>
			</tr>
		</table>
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
			<%--<li><a title="确实要启用吗?" target="selectedTodo" rel="ids" postType="string" href="user/isuserstatus?type=1" class="edit"><span>启用</span></a></li>
			<li><a title="确实要禁用吗?" target="selectedTodo" rel="ids" postType="string" href="user/isuserstatus?type=2" class="icon"><span>禁用</span></a></li>	--%>
				&nbsp;<a style="no-repeat;background-size:15px;background-position: 0 4px;color:blue;line-height:23px;"   class="icon" target="dwzExport" targetType="navTab" title="您确定导出这些记录吗?" href="user/exportExcel?userType=${tuser.userType}" >导出EXCEL</a>
				<div style="line-height:25px; float: right">重置密码为：123456&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="125">
		<thead>
			<tr>
				<th width="40"  align="center">序号</th>
				<th width="40"  align="center">登录名</th>
				<th width="40"  align="center">上级用户</th>
				<th width="180" align="center">昵称</th>
				<th width="70" align="center">归属地</th>
				<th width="70" align="center" >是否是会员</th>
				<th width="100" align="center">会员等级</th>
				<th width="100" align="center">加入时间</th>
				<th width="70" align="center" orderField="we">状态</th>
				<!--<th width="70" >操作</th>
			--></tr>
		</thead>
		<tbody>
		    <c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr target="sid_user" rel="1">
				<td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.loginName}</td>
				<td>${item.nickName}</td>
				<td>${item.user.nickName}</td>
				<td>
					<c:if test="${not empty item.areaid}">
						<ry:show parentCode="${fn:substring(item.areaid,0,2)}0000" itemCode="${fn:substring(item.areaid,0,4)}00" type="3"></ry:show>
						<ry:show parentCode="${fn:substring(item.areaid,0,4)}00" itemCode="${item.areaid}" type="4"></ry:show>
					</c:if>
				</td>
				<td>
				<c:if test="${item.isMember==1}">是</c:if>
				<c:if test="${item.isMember==2}">否</c:if>
				</td>
				<td>
				<ry:show parentCode="MEMBER_LEVAL" itemCode="${item.memberLevel}"></ry:show>
				</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><!--${item.userStatus ==1? '启用' : '禁用'}</td>-->
					<c:if test="${item.userStatus==1}">
						<a href="javascript:void(0)" onclick="makeTop(2,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: green">启用</span></a>
					</c:if>
					<c:if test="${item.userStatus==2}">
						<a href="javascript:void(0)" onclick="makeTop(1,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: red">禁用</span></a>
					</c:if>
					&nbsp;&nbsp;
					<a title="您确定重置用户 “${item.loginName}”的密码吗？" target="ajaxTodo" href="user/resetPassword?userId=${item.userId }"><span style="color: green">重置密码</span></a>
					<c:if test="${empty item.cfOrderCount}">
						&nbsp;&nbsp;
						<a title="您确定要赠送“${item.loginName}”一年会员吗？" target="ajaxTodo" href="usermember/giveMember?userNum=${item.userNum}"><span style="color: green">赠送会员</span></a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
