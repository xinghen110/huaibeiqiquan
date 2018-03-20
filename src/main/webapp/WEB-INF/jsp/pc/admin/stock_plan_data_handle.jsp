<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USER_ACCOUNT_ORDER_STATUS" bingdingName="orderStatus"></ry:binding>
<ry:binding type="1" bingdingName="cycleList" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<ry:binding type="1" bingdingName="STOCK_PLAN_STATUS" parentCode="STOCK_PLAN_STATUS"></ry:binding>
<ry:binding type="3"></ry:binding>
<form  id="pagerForm" method="post" action="admin/stock/plan/data/handle">
<input type="hidden" name="pageNum" value="${pageList.pageNum }"/>
<input type="hidden" name="numPerPage" value="${pageList.numPerPage}"/>
<input type="hidden" name="orderField" value="${param.orderField}"/>
<!-- 分页时 带模糊查询的值 -->
<%--<input type="hidden" name="accountNumber" value="${bean.accountNumber}"/>--%>
<%--<input type="hidden" name="userName" value="${bean.userName}"/>--%>
<%--<input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>'/>--%>
<%--<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>'/>--%>
<%--<input type="hidden" name="handleTime" value='<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd"/>'/>--%>
<%--<input type="hidden" name="handlestartTime" value='<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd"/>'/>--%>
<%--<input type="hidden" name="status" value="${bean.status}"/>--%>

</form>

<form id="fileForm" action="admin/stock/plan/data/handle" method="post" enctype="multipart/form-data" target="_blank">
    <input type="file" name="stockPlanFile" id="stockPlanFile" style="display: none;" onchange="$('#fileForm').submit()">
    <input type="hidden" name="loginName" value="${param.loginName}">
    <%--<input type="hidden" name="symbol" value="${param.symbol}">--%>
    <%--<input type="hidden" name="orderStatus" value="${param.orderStatus}">--%>
    <%--<input type="hidden" name="direction" value="${param.direction}">--%>
</form>


<div class="pageHeader">
    <form id="form" rel="pagerForm" onsubmit="return navTabSearch(this);" action="admin/stock/plan/data/handle" method="post" enctype="multipart/form-data">
        <input id="orderBy" type="hidden" name="orderBy" value="${param.orderBy}"/>
        <input id="order" type="hidden" name="order" value="desc"/>
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>帐号：</label>
                    <input type="text" name="loginName" value="${param.loginName}">
                </li>
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
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar" style="float: right">
            <li><a class="add" onclick="searchOrderBy('yieldRate');"><span>按收益率排序</span></a></li>
            <li><a class="add" onclick="searchOrderBy('netProfit');"><span>按净收益排序</span></a></li>
            <li><a class="add" onclick="searchOrderBy('accuracyRate');"><span>按准确率排序</span></a></li>
        </ul>
    </div>
</div>

</div>
<table class="table" width="100%" layoutH="134">
    <thead>
    <tr>
        <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
         -->
        <th width="center" align="center">序号</th>
        <th width="center" align="center">申请人帐号</th>
        <th width="center" align="center">费用总计</th>
        <th width="center" align="center">收益总计</th>
        <th width="center" align="center">收益率</th>
        <th width="center" align="center">净收益</th>
        <th width="center" align="center">准确率</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
            <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td>${item.loginName}</td>
            <td>${item.fee}</td>
            <td>${item.profit}</td>
            <td >${item.yieldRate}</td>
            <td >${item.netProfit}</td>
            <td >${item.accuracyRate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
<script>
    function searchOrderBy(key) {
        $("#orderBy").val(key);
        $("#form").submit();
    }
</script>

