<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="usermember/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="userNum" value="${bean.userNum}" />
    <input type="hidden" name="status" value="${bean.status}" />
    <input type="hidden" name="nickName" value="${nickName }">

</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="usermember/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>用户姓名：</label>
                    <input type="text" name="nickName" value="${nickName }">
                </li>

                <li>
                    <label>会员状态：</label>
                    <select name="status">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${bean.status == 1}"> selected </c:if>>已过期</option>
                        <option value="2" <c:if test="${bean.status == 2}"> selected </c:if>>未过期</option>
                    </select>
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
        <th width="center" align="center">序号</th>
        <th width="center" align="center">用户姓名</th>
        <th width="center" align="center">会员等级</th>
        <th width="center" align="center">开始时间</th>
        <th width="center" align="center">结束时间</th>
        <th width="center" align="center">会员状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
            <td>
                <input type="hidden" name="userMemberId" value="${item.userMemberId}">
                    ${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td>${item.user.nickName}</td>
            <td><ry:show parentCode="MEMBER_LEVAL" itemCode="${item.userMember}"></ry:show></td>
            <td><ry:formatDate date="${item.beginTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
            <td><ry:formatDate date="${item.endTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
            <td>${item.status == 1 ? '已过期' : '未过期'}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>