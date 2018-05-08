<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USER_ACCOUNT_ORDER_STATUS" bingdingName="orderStatus"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="admin/withdraw/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }"/>
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="loginName" value="${param.loginName}"/>
    <input type="hidden" name="orderStatus" value="${param.orderStatus}"/>
</form>


<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="admin/withdraw/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">

                <li><label>帐号：</label>
                    <input type="text" name="loginName" value="${param.loginName}">
                </li>

                <%--<li><label>姓名：</label>--%>
                    <%--<input type="text" name="userName" value="${bean.userName}">--%>
                <%--</li>--%>

                <li>
                    <label>提现状态：</label>
                    <select name="orderStatus">
                        <option value="">请选择</option>
                        <c:forEach items="${orderStatus}" var="item">
                            <option value="${item.itemCode}" ${item.itemCode==param.orderStatus? 'selected':'' } >${item.itemName}</option>
                        </c:forEach>
                    </select>
                </li>

                <%--<li style="width: 370px">--%>
                    <%--<label>申请时间：</label>--%>
                    <%--<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate"--%>
                           <%--onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>--%>
                    <%--~--%>
                    <%--<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate"--%>
                           <%--onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>--%>
                <%--</li>--%>

                <%--<li style="width: 370px">--%>
                    <%--<label>处理时间：</label>--%>
                    <%--<input id="handlestartTime" style="width: 110px" type="text" name="handlestartTime" value="<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd"/>" class="Wdate"--%>
                           <%--onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'handleTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'handleTime\')}'})"/>--%>
                    <%--~--%>
                    <%--<input id="handleTime" style="width: 110px" type="text" name="handleTime" value="<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd"/>" class="Wdate"--%>
                           <%--onFocus="WdatePicker({minDate:'#F{$dp.$D(\'handlestartTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'handlestartTime\')}'})"/>--%>
                <%--</li>--%>

            </ul>

            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">查询</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>


</div>
<table class="table" width="100%" layoutH="108">
    <thead>
    <tr>
        <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
         -->
        <th width="center" align="center">序号</th>
        <th width="center" align="center">申请人userId</th>
        <th width="center" align="center">申请人帐号</th>
        <th width="center" align="center">申请人姓名</th>
        <th width="center" align="center">申请金额</th>
        <th width="center" align="center">银行</th>
        <th width="center" align="center">银行卡号</th>
        <th width="center" align="center">备注</th>
        <th width="center" align="center">状态</th>
        <th width="center" align="center">申请时间</th>
        <th width="center" align="center">处理人</th>
        <th width="center" align="center">处理时间</th>
        <%--<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}">--%>
            <th width="center" align="center">操作</th>
        <%--</ry:authorize>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${page.result}" varStatus="row">
        <tr>
            <td>
                <input type="hidden" name="userApplicationId" value="${item.orderId}">
                    ${(page.pageNum-1)*page.numPerPage+row.count}</td>
            <td>${item.userId}</td>
            <td>${item.loginName}</td>
            <td>${item.userName}</td>
            <td>${item.money}</td>
            <td>${item.bankId}</td>
            <td>${item.bankCardNumber}</td>
            <td>${item.remark}</td>
            <td><c:choose>
                <c:when test="${item.orderStatus == '0'}">待审</c:when>
                <c:when test="${item.orderStatus == '1'}">通过</c:when>
                <c:when test="${item.orderStatus == '2'}">拒绝</c:when>
            </c:choose></td>
            <td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${item.handleLoginName}</td>
            <td><ry:formatDate date="${item.updateTime}" toFmt="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <a onclick="add('admin/withdraw?orderId=${item.orderId}','审批提现',900,600,'main_')" rel="users_saveedit" class="btnEdit">操作</a>
            <%--<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}">--%>
                <%--<td>--%>
                <%--<c:if test="${item.status==2}">--%>
                <%--<a title="修改提现信息" style="cursor: pointer;" onclick="add('userapplication/toEdit?userApplicationNum=${item.userApplicationNum}','修改提现信息',900,550,'main_')"--%>
                <%--rel="users_saveedit"><span style="color: blue;">提现处理</span> </a>--%>
                <%--</c:if>--%>
                <%--</td>--%>
                <%--</ry:authorize>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp" %>

