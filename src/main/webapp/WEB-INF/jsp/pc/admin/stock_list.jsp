<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="${url}">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <input type="hidden" name="orderDirection" value="${param.orderDirection}" />

    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="symbol" value="${param.symbol}"/>

</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${url}" method="post">
        <div class="searchBar">

            <ul class="searchContent">
                <li>
                    <label>股票代码：</label>
                    <input type="text" name="symbol" value="${stock.symbol}" />
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
        </ul>
    </div>
    <table class="table" width="100%" layoutH="132">
        <thead>
        <tr>

            <th width="40"  align="center">序号</th>
            <th width="180" align="center">股票代码前缀</th>
            <th width="180" align="center">股票代码</th>
            <th width="10" align="center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${pageList.result}" varStatus="row">
            <tr target="sid_user" rel="1">

                <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
                <td>${item.symbolPrefix}</td>
                <td>${item.symbol}</td>
                <td>
                    <a onclick="add('admin/stock/update?symbol=${item.symbol}','修改股票信息',900,600,'main_')" rel="users_saveedit" class="btnEdit">修改</a></td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
