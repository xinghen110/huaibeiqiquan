<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="FW_TYPE" bingdingName="fwTypes"></ry:binding>
<ry:binding parentCode="MEMBER_LEVAL" bingdingName="memberLevel"></ry:binding>
<form id="pagerForm" method="post" action="mealinfo/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="mealType" value="${bean.mealType}">
    <input type="hidden" name="mealInfoNum" value="${bean.mealInfoNum}" />
    <input type="hidden" name="title" value="${bean.title }">
    <input type="hidden" name="typeNum" value="${bean.typeNum }">
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' />
    <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' />
    <input type="hidden" name="isRecommend" value="${bean.isRecommend }">
    <input type="hidden" name="makeMethod" value="${bean.makeMethod}">
    <input type="hidden" name="flag1" value="${bean.flag1}">
</form>


<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="mealinfo/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <input type="hidden" name="mealType" value="${bean.mealType}">

                <li ><label>名称：</label>
                    <input type="text" name="title" value="${bean.title }">
                </li>

                <li>
                    <label>会员等级：</label>
                    <select name="flag1"  >
                        <option value="">全部等级</option>
                        <c:forEach items="${memberLevel}" var="items">
                            <option value="${items.itemCode}" <c:if test="${items.itemCode==bean.flag1}">selected</c:if>>${items.itemName}</option>
                        </c:forEach>
                    </select>
                </li>

                <li ><label>会员等级：</label>
                    <select name="typeNum">
                        <option value="">请选择</option>
                        <c:forEach items="${typeInfo}" var="item">
                            <option value="${item.typeNum }" <c:if test="${item.typeNum == bean.typeNum}">selected="selected"</c:if>>${item.typeInfoName }</option>
                        </c:forEach>

                    </select>
                </li>

                <li style="width: 370px">
                    <label>创建时间：</label>
                    <input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
                    ~
                    <input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
                </li>

                <li>
                    <label>是否推荐：</label>
                    <select name="isRecommend">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${bean.isRecommend == 1}">selected="selected"</c:if>>推荐</option>
                        <option value="2" <c:if test="${bean.isRecommend == 2}">selected="selected"</c:if>>未推荐</option>
                    </select>
                </li>
                <li>
                    <label>预约方式：</label>
                    <select name="makeMethod">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${bean.makeMethod == 1}">selected="selected"</c:if>>定金预约</option>
                        <option value="2" <c:if test="${bean.makeMethod == 2}">selected="selected"</c:if>>全套预约</option>
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
            <li><a title="确实要批量变更推荐状态吗？" target="selectedTodo" rel="ids" postType="string" href="mealinfo/update" class="add"><span>批量变更</span></a></li>
        </ul>
    </div>
</div>
<table class="table" width="100%" layoutH="131">
    <thead>
    <tr>
    <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
        <th width="center" align="center">序号</th>
        <c:if test="${bean.mealType == 1}">
            <th width="center" align="center">商家店名</th>
        </c:if>
        <c:if test="${bean.mealType == 2}">
            <th width="center" align="center">技师姓名</th>
        </c:if>
        <th width="center" align="center">类别</th>
        <th width="center" align="center">名称</th>
        <th width="center" align="center">会员等级</th>
        <th width="center" align="center">价格</th>
        <th width="center" align="center">预约方式</th>
        <th width="center" align="center">定金</th>
        <th width="center" align="center">时长（分钟）</th>
        <th width="center" align="center">创建时间</th>
        <th width="center" align="center">推荐状态</th>
        
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
        	<td><input name="ids" value="${item.meal_info_num}" type="checkbox"></td>
            <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td>${item.shop_name}</td>
            <td>${item.type_info_name}</td>
            <td><a style="cursor: pointer;color: blue" onclick="window.open('app/page/mealinfo_details?mealInfoNum=${item.meal_info_num}','详情','resizable=no,location=no,status=no,width=400,height=500,top=200,left=500')" >${item.title}</a></td>
            <td><ry:show parentCode="MEMBER_LEVAL" itemCode="${item.member_level}"></ry:show></td>
            <td>${item.meal_price}</td>
            <td>${item.make_method==1?'定金预约':'全套预约'}</td>
            <td><fmt:formatNumber value="${item.make_price }" pattern="0.00"/></td>
            <td>${item.meal_log}</td>
            <td><fmt:formatDate value="${item.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <c:if test="${item.is_recommend == 1}">
                    <%--<a style="cursor: pointer;color: blue" title="你确定要取消推荐该套餐吗？" target="ajaxTodo" href="mealinfo/update?mealInfoNum=${item.mealInfoNum}">
                       		 推荐
                    </a>--%>
                    已推荐
                </c:if>
                <c:if test="${item.is_recommend == 2}">
                    <%--<a style="cursor: pointer;color: blue" title="你确定要推荐该套餐吗？" target="ajaxTodo" href="mealinfo/update?mealInfoNum=${item.mealInfoNum}">
                      		  未推荐
                    </a>--%>
                    未推荐
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>