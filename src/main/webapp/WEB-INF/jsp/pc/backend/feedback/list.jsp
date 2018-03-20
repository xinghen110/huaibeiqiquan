<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>

<form id="pagerForm" method="post" action="feedback/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" value="${bean.linkTel }" name="linkTel">
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' />
    <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />


</form>



<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="feedback/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">

                <li>
                    <label>联系方式：</label>
                    <input type="text" value="${bean.linkTel }" name="linkTel">
                </li>

                <li style="width: 370px">
                    <label>反馈时间：</label>
                    <input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
                    ~
                    <input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
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
        <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>-->
        <th width="70px" align="center">序号</th>
        <th width="200px" align="center">联系方式 </th>
        <th width="center" align="center">反馈内容</th>
        <!--<th width="center" align="center">备注</th>
        --><th width="200px" align="center">反馈时间</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
            <td><input type="hidden" name="feedBackId" value="${item.feedBackId }">
                    ${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td>${item.linkTel }</td>
            <td><a href="javascript:void(0)" onclick="add('feedback/detil?feedBackNum=${item.feedBackNum}','查看反馈内容',500,200,'main_')"><span style=" color: blue;">${fn:substring(item.content,0,15)}<c:if test="${fn:length(item.content)>15}">...</c:if></span></a></td>
            <td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>