<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USER_ACCOUNT_ORDER_STATUS" bingdingName="orderStatus"></ry:binding>
<ry:binding type="1" bingdingName="cycleList" parentCode="STOCK_PLAN_CYCLE"></ry:binding>
<ry:binding type="1" bingdingName="STOCK_PLAN_STATUS" parentCode="STOCK_PLAN_STATUS"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="${url}">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }"/>
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
    <input type="hidden" name="orderDirection" value="${param.orderDirection}" />

<%--<!-- 分页时 带模糊查询的值 -->--%>
    <%--&lt;%&ndash;<input type="hidden" name="accountNumber" value="${bean.accountNumber}"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<input type="hidden" name="userName" value="${bean.userName}"/>&ndash;%&gt;--%>
    <%--<input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>--%>
    <%--<input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>--%>
    <%--<input type="hidden" name="handleTime" value='<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>--%>
    <%--<input type="hidden" name="handlestartTime" value='<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>--%>
    <%--&lt;%&ndash;<input type="hidden" name="status" value="${bean.status}"/>&ndash;%&gt;--%>
    <input name="orderStatus" type="hidden" value="${param.orderStatus}"/>
    <input type="hidden" name="belongValue" value="${param.belongValue}"/>
    <input type="hidden" name="loginName" value="${param.loginName}">
    <input type="hidden" name="symbol" value="${param.symbol}">
    <input type="hidden" name="userName" value="${param.userName}">
    <input type="hidden" name="planId" value="${param.planId}">
    <input type="hidden" name="startTime" value="${param.startTime}">
    <input type="hidden" name="endTime" value="${param.endTime}">
    <input type="hidden" name="buyConfirmDatetartTime" value="${param.buyConfirmDatetartTime}">
    <input type="hidden" name="buyConfirmDateEndTime" value="${param.buyConfirmDateEndTime}">
    <input type="hidden" name="sellConfirmTimeDatetartTime" value="${param.sellConfirmTimeDatetartTime}">
    <input type="hidden" name="sellConfirmTimeDateEndTime" value="${param.sellConfirmTimeDateEndTime}">
    <input name="belongName"  type="hidden" value="${param.belongName}" />
    <input name="cycle"  type="hidden" value="${param.cycle}" />
    <input type="hidden" name="direction" value="${param.direction}">
    <input type="hidden" name="belong" value="${param.belong}">

</form>
<style>
    .searchBar ul.searchContent li {
        float: left;
        display: block;
        overflow: hidden;
        width: 166px;
        height: 31px;
        padding: 2px 0;
    }
    select {
        height: 29px;
        border: 1px solid #A2BAC0;
        border-color: #A2BAC0 #B8D0D6 #B8D0D6 #A2BAC0;
        width: 90px;
    }
    .searchBar label {
        float: left;
        width:auto;
        padding: 0 5px;
        line-height: 23px;
        text-align: right;
    }
</style>
<form id="fileForm" action="admin/stock/plan/import?direction=${param.direction}" method="post" enctype="multipart/form-data" target="_blank">
    <input type="file" name="stockPlanFile" id="stockPlanFile" style="display: none;" onchange="$('#fileForm').submit()">
    <%--<input type="hidden" name="loginName" value="${param.loginName}">--%>
    <%--<input type="hidden" name="symbol" value="${param.symbol}">--%>
    <%--<input type="hidden" name="orderStatus" value="${param.orderStatus}">--%>
    <%--<input type="hidden" name="direction" value="${param.direction}">--%>
</form>
<SCRIPT type="text/javascript">
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes(),
            v = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $("#citySel");
        cityObj.attr("value", v);
    }

    function showMenu() {
        var cityObj = $("#citySel");
        var cityOffset = $("#citySel").offset();
        $("#menuContent").css({left:76 + "px", top:36 + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

//    $(document).ready(function(){
//        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
//    });
    //-->
</SCRIPT>
<style>
    li {list-style: circle;font-size: 12px;}
    li.title {list-style: none;}
    ul.list {margin-left: 17px;}

    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
    ul.log {border: 1px solid #617775;background: #f0f6e4;width:300px;height:170px;overflow: hidden;}
    ul.log.small {height:45px;}
    ul.log li {color: #666666;list-style: none;padding-left: 10px;}
    ul.log li.dark {background-color: #E3E3E3;}


</style>

<div class="pageHeader">

    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="${url}" method="post" enctype="multipart/form-data">
        <div class="searchBar">

            <ul class="searchContent">

                <li style="width: 250px"><label>上级归属：</label>
                    <input name="belongName" id="belongName" type="text" readonly value="${param.belongName}" style="width:120px;"/>
                    <a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
                    <input type="hidden" name="belongValue" id="belongValue" value="${param.belongValue}"/>
                </li>

                <li><label>帐号：</label>
                    <input  style="width: 65px" type="text" name="loginName" value="${param.loginName}">
                </li>

                <li><label>股票编号：</label>
                    <input  style="width: 65px" type="number" name="symbol" value="${param.symbol}">
                </li>
                <li><label>申请人：</label>
                    <input  style="width: 65px" type="text" name="userName" value="${param.userName}">
                </li>

                <%--<li style="width: 221px"><label>订单状态：</label>--%>
                <%--<select name="orderStatus" id="orderStatus">--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach items="${STOCK_PLAN_STATUS}" var="d">--%>
                <%--<option value="${d.itemCode}" ${param.orderStatus==d.itemCode?"selected":""}>${d.itemName}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<script>--%>
                <%--$("#orderStatus").val("${param.orderStatus}");--%>
                <%--</script>--%>
                <%--</li>--%>



            </ul>
            <ul class="searchContent">

                <li id="orderStatusLi" style="width: 185px"><label>订单状态：</label>
                    <select name="orderStatus" id="orderStatus">
                        <option value="">请选择</option>
                        <c:forEach items="${STOCK_PLAN_STATUS}" var="d">
                            <option value="${d.itemCode}" ${param.orderStatus==d.itemCode?"selected":""}>${d.itemName}</option>
                        </c:forEach>
                    </select>
                    <%--<script>--%>
                    <%--$("#orderStatus").val("${param.orderStatus}");--%>
                    <%--</script>--%>
                </li>
                <%--<li style="width: 370px">--%>
                <%--<label>处理时间：</label>--%>
                <%--<input id="handlestartTime" style="width: 110px" type="text" name="handlestartTime" value="<fmt:formatDate value="${bean.handlestartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"--%>
                <%--onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'handleTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'handleTime\')}'})"/>--%>
                <%--~--%>
                <%--<input id="handleTime" style="width: 110px" type="text" name="handleTime" value="<fmt:formatDate value="${bean.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"--%>
                <%--onFocus="WdatePicker({minDate:'#F{$dp.$D(\'handlestartTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'handlestartTime\')}'})"/>--%>
                <%--</li>--%>
                <li style="width: 185px">
                    <label>周期：</label>
                    <select name="cycle" id="cycle">
                        <option value="">请选择</option>
                        <c:forEach var="item" items="${cycleList}">
                            <option value="${item.itemCode}">${item.itemName}</option>
                        </c:forEach>
                    </select>
                    <script>
                        $("#cycle").val("${param.cycle}");
                    </script>
                </li>
                <li><label>单号：</label>
                    <input  style="width: 65px" type="number" name="planId" value="${param.planId}">
                </li>
            </ul>
            <ul class="searchContent">

                <li style="width: 270px">
                    <label>申请时间：</label>
                    <input id="startTime" style="width: 70px" type="text" name="startTime" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
                    ~
                    <input id="createTime" style="width: 70px" type="text" name="endTime" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
                </li>
                <li style="width: 270px">
                    <label>生效时间：</label>
                    <input id="buyConfirmDatetartTime" style="width: 70px" type="text" name="buyConfirmDatetartTime" value="<fmt:formatDate value="${buyConfirmDatetartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
                    ~
                    <input id="buyConfirmDateEndTime" style="width: 70px" type="text" name="buyConfirmDateEndTime" value="<fmt:formatDate value="${buyConfirmDateEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
                </li>
                <li style="width: 270px">
                    <label>结算日期：</label>
                    <input id="sellConfirmTimeDatetartTime" style="width: 70px" type="text" name="sellConfirmTimeDatetartTime" value="<fmt:formatDate value="${sellConfirmTimeDatetartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
                    ~
                    <input id="sellConfirmTimeDateEndTime" style="width: 70px" type="text" name="sellConfirmTimeDateEndTime" value="<fmt:formatDate value="${sellConfirmTimeDateEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
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
                    <c:if test="${(param.orderStatus == 1 || param.orderStatus == 3) && systemUser.userType==1}">
                        <li>
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <a href="admin/stock/plan/export?loginName=${param.loginName}&symbol=${param.symbol}&direction=${param.orderStatus == 1 ? "buy":"sell"}" target="_blank">导出</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <a href="javascript:;" onclick="$('#stockPlanFile').click()">导入</a>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </form>
</div>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul>
            <c:if test="${sessionScope.systemUser.userType == 1}">
                <li style="line-height: 23px">服务费汇总：<c:if test="${empty allFee}">0</c:if><c:if test="${not empty allFee}">${allFee.serviceFee}</c:if></li>
                <li style="line-height: 23px">管理费汇总：<c:if test="${empty allFee}">0</c:if><c:if test="${not empty allFee}">${allFee.manageFee}</c:if></li>
            </c:if>
            <li style="line-height: 23px">管理费汇总：<c:if test="${empty allFee}">0</c:if><c:if test="${not empty allFee}">${allFee.fee}</c:if></li>
        </ul>

    </div>
</div>
<table class="table" width="340%" layoutH="230">
    <thead>
    <tr>
        <!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
         -->
        <%--<th align="center">序号</th>--%>
        <th>订单号</th>
        <th>申请人帐号</th>
        <th>申请人</th>
        <th>股票编码</th>
        <th>股票名称</th>
        <th>周期</th>
        <th>市值</th>
        <th>买入价格类型</th>
        <th>买入限价</th>
        <th>建议日期</th>
        <th>买入价格</th>
        <th>生效日期</th>
        <th>结束日期</th>
        <%--<th width="center" align="center">卖出初期价格</th>--%>
        <th>卖出价格类型</th>
        <th>卖出限价</th>
        <th>卖出申请时间</th>
        <th>卖出价格</th>
        <th>卖出生效时间</th>
        <c:if test="${sessionScope.systemUser.userType == 1}">
            <th>服务费</th>
            <th>管理费</th>
        </c:if>
        <th>管理费</th>
        <th>订单状态</th>
        <th>方案创建时间</th>
        <th>收益</th>
        <th>净收益</th>
        <%--<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}">--%>
        <%--<th width="center" align="center">操作</th>--%>
        <%--</ry:authorize>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row">
        <tr>
                <%--<td>${(page.pageNum-1)*page.numPerPage+row.count}</td>--%>
            <td>${item.planId}</td>
            <td>${item.loginName}</td>
            <td>${item.userName}</td>
            <td>${item.symbol}</td>
            <td>${item.symbolName}</td>
            <td><c:forEach items="${cycleList}" var="d">
                ${d.itemCode == item.cycle ? d.itemName : ""}
            </c:forEach>
            </td>
            <td>${item.buyMarketPrice}</td>
            <td><c:choose>
                <c:when test="${item.buyPriceType == '0'}">市价</c:when>
                <c:when test="${item.buyPriceType == '1'}">限价</c:when>
                <%--<c:otherwise></c:otherwise>--%>
            </c:choose></td>
            <td>${item.buyLimitPrice}</td>
            <td>${item.buyRecommendDate}</td>
            <td>${item.buyPrice}</td>
            <td>${item.buyConfirmDate}</td>
            <td>${item.buyEndDate}</td>
                <%--<td>${item.sellMarketPrice}</td>--%>
            <td>${item.sellPriceType}</td>
            <td>${item.sellLimitPrice}</td>
            <td>${item.sellCreateTime}</td>
            <td>${item.sellPrice}</td>
            <td>${item.sellConfirmTime}</td>
            <c:if test="${sessionScope.systemUser.userType == 1}">
                <td>${item.serviceFee}</td>
                <td>${item.manageFee}</td>
            </c:if>
            <td>${item.serviceFee + item.manageFee}</td>
            <td><c:choose>
                <c:when test="${item.orderStatus =='-1'}">未开仓成功</c:when>
                <c:when test="${item.orderStatus == '1'}">申请开仓</c:when>
                <c:when test="${item.orderStatus == '2'}">持仓中</c:when>
                <c:when test="${item.orderStatus == '3'}">申请行权中</c:when>
                <c:when test="${item.orderStatus == '4'}">行权成功</c:when>
            </c:choose></td>
            <td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${item.profit}</td>
            <td>${item.netProfit}</td>
                <%--<td>--%>
                <%--<a onclick="add('admin/stock/plan?planId=${item.planId}','审批方案',900,600,'main_')" rel="users_saveedit" class="btnEdit">操作</a>--%>
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
<div id="menuContent" class="menuContent" style="display:none; position: absolute;left: 73px!important;    top: 36px!important;height: 300px;overflow: auto;">
    <div  style="background: #ffffff;width: 160px" class="accordionContent" id="left"></div>
</div>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
<script>
    function getTree(data,pId,isInit){
        var tree;
        if(isInit){
            var rootData = null;
            for(var i = 0;i<data.length; i++){
                if(data[i].id == pId){
                    rootData = data[i];
                    break;
                }
            }
            tree = '<ul class="tree treeFolder">';
            if(rootData != null)
                tree += "<a onclick='addInfo(this)' data-belongValue='"+rootData.id+"-"+rootData.userType+"'>"+rootData.name+"</a>";
        }else{
            tree = '<ul>';
        }
        for(var i in data){
            if(data[i].pId == pId){
                tree += "<li><a onclick='addInfo(this)' data-belongValue='"+data[i].id+"-"+data[i].userType+"'>"+data[i].name+"</a>";
                tree += getTree(data,data[i].id, false);
                tree += "</li>";
            }
        }
        return tree+"</ul>";
    }
    jQuery(function($) {
        var data = ${userList};
        var pId = 0;
        var systemUserType = ${systemUser.userType};
        if(systemUserType!=1){
            pId = ${systemUser.userId};
        }
        $("#left").html(getTree(data,pId,true).replaceAll("<ul></ul>",""));
    });

    function addInfo(obj) {
        var belongValue = $(obj).attr("data-belongValue");
        console.log($(obj).attr("data-belongValue"));
        $("#belongValue").val(belongValue);
        $("#belongName").val($(obj).html());
    }
</script>

