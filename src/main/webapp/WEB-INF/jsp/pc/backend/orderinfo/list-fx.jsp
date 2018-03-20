<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="ORDER_STATUS,PAY_METHOD" bingdingName="orderInfoStatus,payMethod"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="orderinfo/list-fx">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="orderNum" value="${bean.orderNum}" />
    <input type="hidden" name="orderLinkMan" value="${bean.orderLinkMan}" />
    <input type="hidden" name="paystartTime" value='<fmt:formatDate value="${bean.paystartTime}" pattern="yyyy-MM-dd"/>' />
    <input type="hidden" name="payTime" value='<fmt:formatDate value="${bean.payTime}" pattern="yyyy-MM-dd"/>' />
    <input type="hidden" name="payMethod" value="${bean.payMethod}" />
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="orderinfo/list-fx" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li ><label>订单编号：</label>
                    <input type="text" name="orderNum" value="${bean.orderNum }">
                </li>

                <li ><label>创建人：</label>
                    <input type="text" name="orderUserName" value="${bean.orderUserName}">
                </li>

                <li ><label>付款方式：</label>
                    <select name="payMethod"  >
                        <option value="">请选择</option>
                        <c:forEach items="${payMethod}" var="items">
                            <option value="${items.itemCode}" <c:if test="${items.itemCode==bean.payMethod}">selected</c:if>>${items.itemName}</option>
                        </c:forEach>
                    </select>
                </li>
                <li style="width: 370px">
                    <label>付款时间：</label>
                    <input id="paystartTime" style="width: 110px" type="text" name="paystartTime" value="<fmt:formatDate value="${bean.paystartTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'payTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'payTime\')}'})"/>
                    ~
                    <input id="payTime" style="width: 110px" type="text" name="payTime" value="<fmt:formatDate value="${bean.payTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'paystartTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'paystartTime\')}'})"/>
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
<table class="table" width="100%" layoutH="131">
    <thead>
    <tr>
        <th width="center" align="center">序号</th>
        <th width="center" align="center">订单编号</th>
        <th width="center" align="center">订单创建人</th>
        <th width="center" align="center">付款时间</th>
        <th width="center" align="center">付款方式</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
            <td>
                <input type="hidden" name="orderId" value="${item.orderId }">
                    ${(pageList.pageNum-1)*pageList.numPerPage+row.count}
            </td>
            <td>
                <a style="cursor: pointer; color: blue;" onclick="add('orderinfo/detail-fx?orderNum=${item.orderNum}','进入分销详情页面',900,600,'main_')">${item.orderNum}</a>
            </td>
            <td>${item.user.nickName}</td>
            <td><ry:formatDate date="${item.payTime}" toFmt="yyyy-MM-dd HH:mm:ss" /></td>
            <td><ry:show parentCode="PAY_METHOD" itemCode="${item.payMethod}"></ry:show></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>