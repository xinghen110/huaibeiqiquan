<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="admin/user/list?userType=${tuser.userType}">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <input type="hidden" name="orderDirection" value="${param.orderDirection}" />

    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="userName" value="${param.userName}"/>
    <%--<input type="hidden" name="nickName" value="${param.nickName}"/>--%>
    <%--<input type="hidden" name="userSex" value="${param.userSex}"/>--%>
    <%--<input type="hidden" name="createTime" value="${param.createTime}" />--%>
    <%--<input type="hidden" name="endDate" value="${param.endDate}" />--%>

</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="admin/user/info/list?userType=${user.userType}" method="post">
        <div class="searchBar">

            <ul class="searchContent">
                <li>
                    <label>用户名：</label>
                    <input type="text" name="userName" value="${userInfo.userName }" />
                </li>
                <%--<li>--%>
                <%--<label>用户昵称：</label>--%>
                <%--<input type="text" name="nickName" value="${tuser.nickName }"/></td>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<label>性别：</label>--%>
                <%--<select name="userSex" >--%>
                <%--<option value="">---请选择性别---</option>--%>
                <%--<c:forEach items="${usersexs}" var="item">--%>
                <%--<option value="${item.itemCode }" ${item.itemCode==tuser.userSex ? 'selected':'' } >${item.itemName }</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</li>--%>


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
            <%--<li><a class="add" onclick="add('admin/user/add','添加信息',900,500,'main_')"><span>添加</span></a></li>--%>

            <!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="user/batchDelete" class="delete"><span>批量删除</span></a></li>
--><!--			<li class="line">line</li>-->
            <!--			<li><a class="icon" href="user/exportExcel" target="dwzExport" targetType="navTab" title="您确定导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
        </ul>
    </div>
    <table class="table" width="100%" layoutH="132">
        <thead>
        <tr>

            <th width="40"  align="center">序号</th>
            <th width="180" align="center">用户名</th>
            <th width="180" align="center">身份证号码</th>
            <th width="180" align="center">银行卡号</th>
            <th width="50" align="center">状态</th>
            <th width="80" >操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${pageList.result}" varStatus="row">
            <tr target="sid_user" rel="1">

                <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
                <td>${item.userName}</td>
                <td>${item.idNumber}</td>
                <td>${item.bankCardNumber}</td>
                <td>
                    <c:choose>
                        <c:when test="${item.status==0}">未审核</c:when>
                        <c:when test="${item.status==1}">审核通过</c:when>
                        <c:otherwise>审核未通过</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a class="btnEdit" onclick="add('admin/user/info/check?userId=${item.userId }','审核信息',950,500,'main_')"><span>审核信息</span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
<script>
    function updateUserStatus() {
        $.ajax({
            type: 'POST',
            url: "" ,
            data: data ,
            success: success ,
            dataType: dataType
        });
    }
</script>
