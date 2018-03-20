<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>

<form id="pagerForm" method="post" action="user/fxlist">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" value="${bean.nickName }" name="nickName">

</form>



<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="user/fxlist" method="post">
        <div class="searchBar">
            <ul class="searchContent">

                <li>
                    <label>用户昵称：</label>
                    <input type="text" name="nickName" value="${bean.nickName }"/></td>
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

        </ul>
    </div>
</div>

<table class="table" width="100%" layoutH="133">
    <thead>
    <tr>
        <th width="70px" align="center">序号</th>
        <th width="center" align="center">用户名</th>
        <th width="center" align="center">一级分享用户数</th>
        <th width="center" align="center">二级分享用户数</th>
        <th width="center" align="center">三级分享用户数</th>
        <th width="center" align="center">总获利</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
            <td><input type="hidden" name="feedBackId" value="${item.feedBackId }">
                    ${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td>${item.nick_name}</td>
            <td>
                <a title="技师查看" href="javascript:void(0)"  onclick="add('user/fxdetail?userCode=${item.user_code}&type=1','查看一级分享用户',900,600,'main_')"  rel="users_saveedit">
                        ${item.count1}
                </a>
            </td>
            <td>
                <a title="技师查看" href="javascript:void(0)"  onclick="add('user/fxdetail?userCode=${item.user_code}&type=2','查看二级分享用户',900,600,'main_')"  rel="users_saveedit">
                        ${item.count2}
                </a>
            </td>
            <td>
                <a title="技师查看" href="javascript:void(0)"  onclick="add('user/fxdetail?userCode=${item.user_code}&type=3','查看三级分享用户',900,600,'main_')"  rel="users_saveedit">
                        ${item.count3}
                </a>
            </td>
            <td>
                <c:if test="${empty item.consum_price}">0.00元</c:if>
                <c:if test="${not empty item.consum_price}">${item.consum_price}元</c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>