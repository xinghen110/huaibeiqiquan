<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="admin/query/by/invite?userType=${param.userType}">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <input type="hidden" name="orderDirection" value="${param.orderDirection}" />

    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="loginName" value="${param.loginName}"/>
    <input type="hidden" name="nickName" value="${param.nickName}"/>
    <input type="hidden" name="userName" value="${userInfo.userName}"/>
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
    <input type="hidden" name="endTime" value='<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />

</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="admin/query/by/invite?userType=${param.userType}" method="post">
        <div class="searchBar">

            <ul class="searchContent">
                <li>
                    <label>登录名称：</label>
                    <input type="text" name="loginName" value="${param.loginName }" />
                </li>
                <li>
                    <label>用户昵称：</label>
                    <input type="text" name="nickName" value="${param.nickName}"/></td>
                </li>
                <li>
                    <label>账号状态：</label>
                    <select name="userStatus" >
                        <option value="">---请选择用户状态---</option>
                        <option value="0" ${param.userStatus == 0 ? 'selected':'' } >禁用</option>
                        <option value="1" ${param.userStatus == 1 ? 'selected':'' } >启用</option>
                    </select>
                </li>
                <c:if test="${param.userType==3}">
                <li>
                    <label>所属运营中心：</label>
                    <select name="parentLoginName" >
                        <option value="">---请选择运营中心---</option>
                        <c:forEach items="${childUserList}" var="item">
                            <option ${param.parentLoginName == item.loginName?'selected':''}  value="${item.loginName}">${item.loginName}/${item.nickName}</option>
                        </c:forEach>
                    </select>
                </li>
                </c:if>
            </ul>
            <ul class="searchContent">
                <li style="width: 350px">
                    <label>创建时间：</label>
                    <input id="startTime" style="width: 90px" type="text" name="startTime" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
                    ~
                    <input id="endTime" style="width: 90px" type="text" name="endTime" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
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
            <li><a class="add" onclick="add('admin/user/add?userType=${param.userType}','添加信息',900,500,'main_')"><span>添加</span></a></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="166">
        <thead>
        <tr>
            <th width="40"  align="center">序号</th>
            <th width="180" align="center">登录名称</th>
            <th width="180" align="center">用户昵称</th>
            <th width="180" align="center">所属运营中心</th>
            <th width="100" align="center">创建时间</th>
            <th width="100" align="center">用户状态</th>
            <th width="100" align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${pageList.result}" varStatus="row">
            <tr target="sid_user" rel="1">

                <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
                <td>${item.loginName}</td>
                <td>${item.nickName}</td>
                <td>${item.parentLoginName}</td>
                <%--<ry:authorize ifAllGranted="${authMap.SHOP_AUTH}">--%>
                    <%--<td>${item.shopName}</td>--%>
                <%--</ry:authorize>--%>
                <%--<td>--%>
                    <%--<ry:show parentCode="${item.province}" itemCode="${item.province}" type="2"></ry:show>--%>
                    <%--<ry:show parentCode="${item.province}" itemCode="${item.city}" type="3"></ry:show>--%>
                    <%--<ry:show parentCode="${item.city}" itemCode="${item.area}" type="4"></ry:show>--%>
                <%--</td>--%>

                <%--<td><ry:show parentCode="USERSEX" itemCode="${item.userSex}"></ry:show></td>--%>
                <%--<td>${item.userPhone}</td>--%>

                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                        <%--<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">--%>
                    <c:if test="${item.userStatus==1}">
                        <a href="javascript:void(0)" onclick="makeTop(0,'${item.userId}','user_status','user/updateQuery','t_user','user_id')"><span style="color: green">启用</span></a>
                    </c:if>
                    <c:if test="${item.userStatus==0}">
                        <a href="javascript:void(0)" onclick="makeTop(1,'${item.userId}','user_status','user/updateQuery','t_user','user_id')"><span style="color: red">禁用</span></a>
                    </c:if>
                        <%--</ry:authorize>--%>
                        <%--<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">--%>
                        <%--${item.userStatus ==1? '启用' : '禁用'}--%>
                        <%--</ry:authorize>--%>
                </td>
                <%--<td>--%>
                    <%--<ry:authorize ifAllGranted="${authMap.SYSATEM_AUTH}">--%>
                        <%--<c:if test="${item.userStatus==1}">--%>
                            <%--<a href="javascript:void(0)" onclick="makeTop(2,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: green">启用</span></a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.userStatus==2}">--%>
                            <%--<a href="javascript:void(0)" onclick="makeTop(1,'${item.userNum}','user_status','user/updateQuery','t_user','user_num')"><span style="color: red">禁用</span></a>--%>
                        <%--</c:if>--%>
                    <%--</ry:authorize>--%>
                    <%--<ry:authorize ifAllGranted="${authMap.SYSTEM_AUTH}">--%>
                        <%--${item.userStatus ==1? '启用' : '禁用'}--%>
                    <%--</ry:authorize>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<a title="您确定重置用户 “${item.loginName}”的密码吗？" target="ajaxTodo" href="user/resetPassword?userId=${item.userId }"  class="btnView">重置密码</a>--%>
                    <%--<!--<a title="修改用户" target="navTab" href="user/toUserEdit?userId=${item.userId }&userType=${item.userType}" rel="users_saveedit" class="btnEdit">编辑</a>--%>

					<%----><a class="btnEdit" onclick="add('user/toUserEdit?userId=${item.userId }&userType=${item.userType}','修改用户信息',950,500,'main_')"><span>修改用户</span></a>--%>

                    <%--<a title="您确定删除用户 ”${item.loginName}“ 吗？" target="ajaxTodo" href="user/del?userId=${item.userId}&loginName=${item.loginName}&userType=${item.userType}&userStatus=${item.userStatus}" class="btnDel">删除</a>--%>
                <%--</td>--%>
                <td>
                    <a title="您确定重置用户 “${item.loginName}”的密码吗？" target="ajaxTodo" href="user/resetPassword?userId=${item.userId}"  class="btnView">重置密码</a>
                        <%--<c:if test="${item.userStatus==1}">--%>
                        <%--<a title="禁用" target="navTab" href="admin/update/user/status?userId=${item.userId}" rel="users_saveedit" class="btnInfo">禁用</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.userStatus==0}">--%>
                        <%--<a title="启用" target="navTab" href="admin/update/user/status?userId=${item.userId}" rel="users_saveedit" class="btnSelect">启用</a>--%>
                        <%--</c:if>--%>
                    <a class="btnEdit" onclick="add('admin/user/update?userId=${item.userId }&userType=${item.userType}&urlType=invite','修改用户信息',950,500,'main_')"><span>修改用户</span></a>
                    <a title="您确定删除用户 ”${item.loginName}“ 吗？" target="ajaxTodo" href="user/del?userId=${item.userId}&loginName=${item.loginName}&userType=${item.userType}&userStatus=${item.userStatus}" class="btnDel">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
