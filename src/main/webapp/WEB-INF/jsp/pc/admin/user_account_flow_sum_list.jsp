<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="ACCOUNT_FLOW_TYPE" bingdingName="ACCOUNT_FLOW_TYPE"></ry:binding>
<ry:binding parentCode="SELECT_RANGE" bingdingName="SELECT_RANGE"></ry:binding>
<form id="pagerForm" method="post" action="admin/user/account/flow/sum/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />

    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
    <input type="hidden" name="endTime" value='<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
    <input type="hidden" name="belongValue" value="${param.belongValue}"/>
    <input name="belongName"  type="hidden" value="${param.belongName}" />
    <input type="hidden" name="loginName" value="${param.loginName}"/>
    <input type="hidden" name="userName" value="${param.userName}"/>
    <input id="hiddenFlowType" type="hidden" name="flowType" value="${param.flowType}"/>
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
    .searchContent input{
        width: 65px;
    }
    .searchContent input[type='number']{
        padding: 2px;
        margin: 0;
        line-height: 20px;
        font-size: 12px;
        height: 22px;
        width: 125px;
        border-radius: 4px;
        border: 1px solid #A2BAC0;
        background-color: #FFF;
    }
</style>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="admin/user/account/flow/sum/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li style="width: 250px"><label>上级归属：</label>
                    <input name="belongName" id="belongName" type="text" readonly value="${param.belongName}" style="width:120px;"/>
                    <a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
                    <input type="hidden" name="belongValue" id="belongValue" value="${param.belongValue}"/>
                </li>
                <li>
                    <label>登录名：</label>
                    <input type="text" name="loginName" value="${param.loginName}"/>
                </li>
                <li>
                    <label>姓名：</label>
                    <input type="text" name="userName" value="${param.userName}"/>
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
                <li>
                    <label>流水类型：</label>
                    <select name="flowType">
                        <option value="">请选择流水类型</option>
                        <c:forEach var="item" items="${ACCOUNT_FLOW_TYPE}">
                            <option value="${item.itemCode}" ${item.itemCode==param.flowType? 'selected':'' }>${item.itemName}</option>
                        </c:forEach>
                    </select>
                </li>
                <c:if test="${systemUser.userType!=1}">
                    <li>
                        <label>查询范围：</label>
                        <select name="type">
                            <option value="">请选择查询范围</option>
                            <c:forEach var="item" items="${SELECT_RANGE}">
                                1<option value="${item.itemCode}" ${item.itemCode==param.type? 'selected':'' }>${item.itemName}</option>
                            </c:forEach>
                        </select>
                    </li>
                </c:if>
            </ul>
            <div class="subBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
                </ul>
            </div>
        </div>
    </form>
</div>
<table class="table" width="100%" layoutH="140">
    <thead>
    <tr>
        <th width="40" align="center">序号</th>
        <th width="40" align="center">登录名</th>
        <th width="40" align="center">姓名</th>
        <th width="50" align="center">流水总额</th>
        <th width="40" align="center">入金总和</th>
        <th width="40" align="center">出金总和</th>
        <th width="50" align="center">红冲</th>
        <th width="40" align="center">蓝补</th>
        <th width="40" align="center">服务费总和</th>
        <th width="50" align="center">盈利总和</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${pageList.result}" varStatus="row" >
        <tr>
            <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
            <td class="center">${item.loginName}</td>
            <td class="center">${item.userName}</td>
            <td class="center">${item.money}</td>
            <td class="center">${item.rujinzonghe}</td>
            <td class="center">${item.chujinzonghe}</td>
            <td class="center">${item.hongchong}</td>
            <td class="center">${item.lanbu}</td>
            <td class="center">${item.sumServiceFee}</td>
            <td class="center">${item.sumPrifit}</td>

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
    var flowType = $("#hiddenFlowType").val();
    $("#flowType").val(flowType);
</script>
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
