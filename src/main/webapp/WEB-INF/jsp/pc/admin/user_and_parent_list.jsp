<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="admin/query/user/parent/list?userType=${tuser.userType}">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <input type="hidden" name="orderDirection" value="${param.orderDirection}" />

    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="loginName" value="${param.loginName}"/>
    <input type="hidden" name="nickName" value="${param.nickName}"/>
    <input type="hidden" name="userSex" value="${param.userSex}"/>
    <input type="hidden" name="userName" value="${userInfo.userName}"/>
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
    <input type="hidden" name="endDate" value='<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' />

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
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="admin/query/user/parent/list?userType=${tuser.userType}" method="post">
        <div class="searchBar">

            <ul class="searchContent">
                <li style="width: 250px"><label>上级归属：</label>
                    <input name="belongName" id="belongName" type="text" readonly value="${param.belongName}" style="width:120px;"/>
                    <a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
                    <input type="hidden" name="belongValue" id="belongValue" value="${param.belongValue}"/>
                </li>
                <li>
                    <label>登录名称：</label>
                    <input type="text" name="loginName" value="${tuser.loginName }" />
                </li>
                <li>
                <label>代理商姓名：</label>
                <input type="text" name="userName" value="${param.userName }"/></td>
                </li>
                <li>
                    <label>账号状态：</label>
                    <select name="userStatus" >
                        <option value="">---请选择用户状态---</option>
                        <option value="0" ${tuser.userStatus == 0 ? 'selected':'' } >禁用</option>
                        <option value="1" ${tuser.userStatus == 1 ? 'selected':'' } >启用</option>
                    </select>
                </li>
            </ul>
            <ul class="searchContent">
                <li style="width: 350px">
                    <label>创建时间：</label>
                    <input id="startTime" style="width: 90px" type="text" name="startTime" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
                    ~
                    <input id="endTime" style="width: 90px" type="text" name="endTime" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
                </li>
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
            <li><a class="add" onclick="add('admin/user/add?userType=${param.userType}','添加信息',900,500,'main_')"><span>添加</span></a></li>

            <!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="user/batchDelete" class="delete"><span>批量删除</span></a></li>
--><!--			<li class="line">line</li>-->
            <!--			<li><a class="icon" href="user/exportExcel" target="dwzExport" targetType="navTab" title="您确定导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
        </ul>
    </div>
    <table class="table" width="100%" layoutH="166">
        <thead>
        <tr>

            <th  align="center">序号</th>
            <th  align="center">登录名称</th>
            <th  align="center">姓名</th>
            <th  align="center">创建时间</th>
            <th align="center">所属上级</th>
            <th  align="center">邀请链接</th>
            <th  align="center">二维码</th>
            <th align="center">状态</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${pageList.result}" varStatus="row">
            <tr target="sid_user" rel="1">

                <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
                <td>${item.loginName}</td>
                <td>${item.nickName}</td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.parentLoginName}</td>
                <td>http://www.jdhuhang.com/mobile/stock/register?parentCode=${item.userId}</td>
                <td><a class="button" href="static/erweima.html?parentCode=${item.userId}" target="_blank"><span>查看二维码</span></a></td>
                <td>
                    <c:if test="${item.userStatus==1}">
                        <a href="javascript:void(0)" onclick="makeTop(0,'${item.userId}','user_status','user/updateQuery','t_user','user_id')"><span style="color: green">启用</span></a>
                    </c:if>
                    <c:if test="${item.userStatus==0}">
                        <a href="javascript:void(0)" onclick="makeTop(1,'${item.userId}','user_status','user/updateQuery','t_user','user_id')"><span style="color: red">禁用</span></a>
                    </c:if>
                </td>
                <td>
                    <a title="您确定重置用户 “${item.loginName}”的密码吗？" target="ajaxTodo" href="user/resetPassword?userId=${item.userId}"  class="btnView">重置密码</a>
                        <%--<c:if test="${item.userStatus==1}">--%>
                        <%--<a title="禁用" target="navTab" href="admin/update/user/status?userId=${item.userId}" rel="users_saveedit" class="btnInfo">禁用</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.userStatus==0}">--%>
                        <%--<a title="启用" target="navTab" href="admin/update/user/status?userId=${item.userId}" rel="users_saveedit" class="btnSelect">启用</a>--%>
                        <%--</c:if>--%>
                    <a class="btnEdit" onclick="add('admin/user/update?userId=${item.userId }&userType=${item.userType}&urlType=parent','修改用户信息',950,500,'main_')"><span>修改用户</span></a>
                    <a title="您确定删除用户 ”${item.loginName}“ 吗？" target="ajaxTodo" href="user/del?userId=${item.userId}&loginName=${item.loginName}&userType=${item.userType}&userStatus=${item.userStatus}" class="btnDel">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
<div id="menuContent" class="menuContent" style="height: 300px;overflow: auto;display:none; position: absolute;left: 73px!important;    top: 36px!important;">
    <div  style="background: #ffffff;width: 160px" class="accordionContent" id="left"></div>
</div>
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
