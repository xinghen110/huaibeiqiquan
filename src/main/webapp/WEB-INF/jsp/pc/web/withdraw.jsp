<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="APPLICATION_STATUS" bingdingName="applicationstatus"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="userapplication/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }"/>
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="accountNumber" value="${bean.accountNumber}"/>
    <input type="hidden" name="userName" value="${bean.userName}"/>
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>'/>
    <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>'/>
    <input type="hidden" name="handleTime" value='<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd"/>'/>
    <input type="hidden" name="handlestartTime" value='<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd"/>'/>
    <input type="hidden" name="status" value="${bean.status}"/>

</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="admin/withdraw/handle" method="post">
        <div class="searchBar">
            <ul class="searchContent">

                <li><label>申请人：</label>
                    <input type="text" name="accountNumber" value="${userAccountOrder.userName}">
                </li>

                <li><label>价格：</label>
                    <input type="text" name="userName" value="${userAccountOrder.money}">
                </li>

                <li>
                    <label>状态：</label>
                    <select name="orderStatus">
                        <option value="">请选择</option>
                        <option value="1">通过</option>
                        <option value="2">拒绝</option>
                    </select>
                </li>
            </ul>

            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">确认</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <input type="hidden" name="orderId" value="${userAccountOrder.orderId}">
    </form>
</div>


<%--<table class="table" width="100%" layoutH="108">--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>--%>
         <%---->--%>
        <%--<th width="center" align="center">序号</th>--%>
        <%--<th width="center" align="center">申请人帐号</th>--%>
        <%--<th width="center" align="center">申请人姓名</th>--%>
        <%--<th width="center" align="center">申请金额</th>--%>
        <%--<th width="center" align="center">状态</th>--%>
        <%--<th width="center" align="center">申请时间</th>--%>
        <%--<th width="center" align="center">处理人</th>--%>
        <%--<th width="center" align="center">处理时间</th>--%>

        <%--<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}">--%>
            <%--<th width="center" align="center">操作</th>--%>
        <%--</ry:authorize>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<c:forEach var="item" items="${pageList.result}" varStatus="row">--%>
        <%--<tr>--%>
            <%--<!--<td><input name="ids" value="${item.userApplicationId }" type="checkbox"></td>--%>
				<%---->--%>
            <%--<td>--%>
                <%--<input type="hidden" name="userApplicationId" value="${item.userApplicationId }">--%>
                    <%--${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>--%>

            <%--<td><a style="cursor: pointer;color: blue" onclick="add('userapplication/toDetailsEdit?userApplicationNum=${item.userApplicationNum}','查看提现信息',900,550,'main_')">${item.accountNumber}</a>--%>
            <%--</td>--%>
            <%--<td>${item.userName}</td>--%>
            <%--<td>${item.money}</td>--%>
            <%--<td>--%>
                <%--<c:if test="${item.status!=2}">--%>
                    <%--<ry:show parentCode="APPLICATION_STATUS" itemCode="${item.status}"></ry:show>--%>
                <%--</c:if>--%>
                <%--<c:if test="${item.status==2}">已提交</c:if>--%>
            <%--</td>--%>
            <%--<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss"/></td>--%>
            <%--<td>${item.user.nickName}</td>--%>
            <%--<td><ry:formatDate date="${item.handleTime}" toFmt="yyyy-MM-dd HH:mm:ss"/></td>--%>


            <%--<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}">--%>
                <%--<td>--%>
                    <%--<c:if test="${item.status==2}">--%>
                        <%--<a title="修改提现信息" style="cursor: pointer;" onclick="add('userapplication/toEdit?userApplicationNum=${item.userApplicationNum}','修改提现信息',900,550,'main_')"--%>
                           <%--rel="users_saveedit"><span style="color: blue;">提现处理</span> </a>--%>
                    <%--</c:if>--%>
                <%--</td>--%>
            <%--</ry:authorize>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--</tbody>--%>
<%--</table>--%>
<!-- 分页 -->
<%--<%@include file="/WEB-INF/jsp/inc/page.jsp" %>--%>

