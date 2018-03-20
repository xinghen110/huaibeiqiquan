<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<form id="pagerForm" method="post" action="auditshop/list?auditShopStatus=${auditShopStatus}">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="userType" value="${bean.userType}">
    <input type="hidden" name="userNum" value="${bean.userNum}" />
    <input type="hidden" name="userType" value="${bean.userType}" />
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' />
    <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />

</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="auditshop/list?auditShopStatus=${auditShopStatus}" method="post">
    	<input type="hidden" name="auditShopStatus" value="${auditShopStatus}" />
        <div class="searchBar">
            <ul class="searchContent">
                <li><label>名称：</label>
                    <input type="text" name="nickName" value="${bean.nickName}">
                </li>

                <li style="width: 370px">
                    <label>申请时间：</label>
                    <input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
                    ~
                    <input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
                </li>
                <li>
					<label>所属类型：</label>
					<select name="userType">
						<option value="" >请选择</option>
						<option value="4" <c:if test="${bean.userType == 4}">selected="selected"</c:if>>技师</option>
						<option value="2" <c:if test="${bean.userType == 2}">selected="selected"</c:if>>店铺</option>
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



</div>
<table class="table" width="100%" layoutH="108">
    <thead>
    <tr>
        <th width="center" align="center">序号</th>
        <th width="center" align="center">申请<c:if test="${bean.userType == 4}">技师</c:if><c:if test="${bean.userType == 2}">商家</c:if>编号</th>
        <th width="center" align="center">名称</th>
        <th width="center" align="center">服务类型</th>
        <th width="center" align="center">申请时间</th>
		<c:if test="${auditShopStatus == 2}">
      	  <th width="center" align="center">操作</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
            <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td>${item.userNum}</td>
            <td>${item.nickName}</td>
            <td>${item.createTime}</td>
          	<c:if test="${auditShopStatus == 2}">
	            <td>
	                <a style="cursor: pointer; color: blue;" onclick="add('auditshop/details?shopNum=${item.shopNum}','进入审核页面',900,600,'main_')">审核处理</a>
	            </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

