<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<form id="pagerForm" method="post" action="admin/stockinfo/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<form id="fileForm" action="admin/stockinfo/import" method="post" enctype="multipart/form-data" target="_blank">
    <input type="file" name="stockPlanFile" id="stockPlanFile" style="display: none;" onchange="$('#fileForm').submit()">
</form>
<script type="text/javascript">
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
</script>
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

    <form id="pagerForm" onsubmit="return navTabSearch(this);" action="admin/stockinfo/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
            </ul>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <a href="javascript:;" onclick="$('#stockPlanFile').click()">导入</a>
                            </div>
                        </div>
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
    <table class="table" width="100%" layoutH="166">
        <thead>
        <tr>

            <th width="40"  align="center">id</th>
            <th width="120" align="center">编码</th>
            <th width="80" align="center">名称</th>
            <th width="80" align="center">hv14</th>
            <th width="80" align="center">hv30</th>
            <th width="50" align="center">hv60</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${pageList.result}" varStatus="row">
            <tr target="sid_user" rel="1">
                <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
                <td>${item.code}</td>
                <td>${item.name}</td>
                <td>${item.hv14}</td>
                <td>${item.hv30}</td>
                <td>${item.hv60}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>