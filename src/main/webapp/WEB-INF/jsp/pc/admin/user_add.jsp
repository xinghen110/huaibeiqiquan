<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USER_TYPE_NEW" bingdingName="USER_TYPE_NEW"></ry:binding>
<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<%--<script type="text/javascript">
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
        $("#menuArea").css({left:130 + "px", top:36 + "px"}).slideDown("fast");
        console.log($("#menuArea"));
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuArea").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuArea" || $(event.target).parents("#menuArea").length>0)) {
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
</style>--%>
<div class="pageContent">
    <form method="post" action="admin/user/add" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <dl>
                <dt>
                    <%--<c:if test="${empty user.loginName}"><span style="color: red;">*</span></c:if>--%>
                    <span style="color: red;">*</span>登录名称：</dt>
                <dd>
                    <%--<c:if test="${not empty user.loginName}">${user.loginName}</c:if>--%>
                    <c:if test="${not empty user.loginName}">
                        <input type="text" value="${user.loginName}" size="20" readonly style="width: 50px;" />
                    </c:if>
                        <input type="text" id="loginName" name="loginName"  value="" size="20" class="mustFill" title="登录名称" alt="请输入登录名称" onblur="searchAjaxName();" style="width: 120px;"/>
                        <span id="fali_show" class="info" style="color: red;display:none;">登录名已被使用，请重新输入</span>
                </dd>
            </dl>
            <dl>
                <dt><span style="color: red;">*</span>电话号码：</dt>
                <dd>
                    <input type="text" name="userPhone" value="${user.userPhone}" size="30" class="mustFill phone " maxlength="11" title="电话号码" alt="请输入电话号码" />
                </dd>
            </dl>
            <c:if test="${param.userType<5}">
            <dl>
                <dt><span style="color: red;">*</span>用户昵称：</dt>
                <dd>
                    <input type="text" name="nickName" value="${user.nickName}" size="30" class="mustFill" title="真实姓名" alt="请输入真实姓名"/>
                </dd>
            </dl>
            </c:if>

            <dl>
                <dt>用户性别：</dt>
                <dd>
                    <c:forEach items="${usersexs}" var="item">
                        <input type="radio" value="${item.itemCode }" name="userSex" ${item.itemCode==user.userSex ? 'checked':(empty user.userSex ? 'checked':'') } >${item.itemName }
                    </c:forEach>
                </dd>
            </dl>
            <c:if test="${param.userType==5}">
                <dl style="width:750px;" >
                    <dt ><span style="color: red;">*</span>归属代理商：</dt>
                    <dd style="width: 300px">
                        <select name="parentUserId">
                            <option value="">请选择代理商</option>
                            <c:if test="${systemUser.userType==4}">
                                <option value="${systemUser.userId}">${systemUser.nickName}/${systemUser.loginName}</option>
                            </c:if>
                            <c:forEach items="${allUserList}" var="item">
                                <option value="${item.userId}">${item.nickName}/${item.loginName}</option>
                            </c:forEach>
                        </select>
                    </dd>
                </dl>
            </c:if>


            <%--<dl style="width:750px;" >--%>
                <%--<dt ><span style="color: red;">*</span>区域：</dt>--%>
                <%--<dd style="width: 300px">--%>
                    <%--<%@include file="/WEB-INF/jsp/inc/auserRegionalCascadeEdit.jsp" %>--%>
                    <%--<input type="hidden" id="provinceid_edit"  value="${user.province}" />--%>
                    <%--<input type="hidden" id="citiesid_edit"   value="${user.city}" />--%>
                    <%--<input type="hidden" id="areaid_edit"   value="${user.area}" />--%>
                <%--</dd>--%>
            <%--</dl>--%>
            <c:if test="${param.userType==1}">
            <dl style="width:750px;" >
            <dt ><span style="color: red;">*</span>用户角色：</dt>
            <dd style="width: 300px">
                <select name="roleId">
                    <option value="">请选择用户角色</option>
                    <c:forEach items="${roleList}" var="item">
                        <c:if test="${item.roleId > 5 ||item.roleId ==1}">
                            <option value="${item.roleId}">${item.roleName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </dd>
            </dl>
            </c:if>
            <dl style="display: none;">
                <dt><span style="color: red;">*</span>用户类型：</dt>
                <dd>
                    <input id="userType" type="hidden" name="userType" value="${param.userType}">
                   <%--<select id="userType" name="userType">--%>
                       <%--<option value="">请选择用户类型</option>--%>
                    <%--<c:forEach items="${USER_TYPE_NEW}" var="item">--%>
                        <%--<c:if test="${param.userType==1 && item.itemCode==2  || item.itemCode ==3 }">--%>
                            <%--<option value="${item.itemCode}">${item.itemName}</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${param.userType==3 && item.itemCode==4}">--%>
                            <%--<option value="${item.itemCode}">${item.itemName}</option>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>
                   <%--</select>--%>
                </dd>
            </dl>
            <dl id="parentCodeDl" style="display: none">
                <dt><span style="color: red;">*</span>选择所属上级：</dt>
                <dd>
                    <%--<input  id="userAddBelongName" type="text" readonly value="${param.belongName}" style="width:120px;"/>
                    <a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
                    <input type="text" name="parentCode" id="userAddBelongValue" value="${param.belongValue}"/>
                    <div id="menuArea" class="menuContent" style="display:none; position: absolute;left: 73px!important;    top: 36px!important;    border: 1px solid #000000;">
                        <div  style="width: 160px" class="accordionContent" id="menu_left"></div>
                    </div>--%>
                    <select id="parentCode" name="parentCode">
                        <option value="">请选择所属上级</option>
                        <c:choose>
                            <c:when test="${param.userType==3}">
                                <c:forEach items="${childUserList.result}" var="item">
                                    <option value="${item.userId}">${item.loginName}/${item.nickName}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${systemUser.userType==3}">
                                    <option value="${systemUser.userId }">${systemUser.loginName}/${item.nickName}</option>
                                </c:if>
                                <c:forEach items="${userList.result}" var="item">
                                    <option value="${item.userId}">${item.loginName}/${item.nickName}</option>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </dd>
            </dl>

        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="formSubmit();">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
        <!-- 隐藏的值 -->
        <input type="hidden" name="userId" value="${user.userId}"/>
    </form>
</div>

<%--<script>
    function getTree(data,pId){
        var tree;
        if(pId==0){
            tree = '<ul class="tree treeFolder">';
        }else{
            tree = '<ul>';
        }
        for(var i in data){

            if(data[i].pId == pId) {
                tree += "<li><a onclick='addInfo(this)' data-belongValue='" + data[i].id + "'>" + data[i].name + "</a>";
                tree += getTree(data, data[i].id);
                tree += "</li>";
            }
        }
        return tree+"</ul>";
    }
    jQuery(function($) {
        var data = ${allUserList}
           for(var i = 0 ;i<data.length;i++){
                console.log(data[i].id);
           }
            $("#menu_left").html(getTree(data,0).replaceAll("<ul></ul>",""));
    });

    function addInfo(obj) {
        var belongValue = $(obj).attr("data-belongValue");
        $("#userAddBelongValue").val(belongValue);
        $("#userAddBelongName").val($(obj).html());
    }
</script>--%>
<script>
    var isVal=true;
    function searchAjaxName(){
        var loginName=$('#loginName').val();
        if(loginName!='${user.loginName}'){
            $.ajax({
                type:'post',//可选get
                url:'user/searchAjaxName',//这里是接收数据的PHP程序
                data:{loginName:loginName,userType:${param.userType}},//传给PHP的数据，多个参数用&连接
                dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
                success:function(msg){
                    if(msg=='fail'){
                        isVal=false;
                        $('#fali_show').show();
                    }else{
                        isVal=true;
                        $('#fali_show').hide();

                    }
                },
                error:function(){
                }
            });
        }else{
            isVal=true;
            $('#fali_show').hide();
        }

    }

    function formSubmit(){
        if(isVal==false){
            $('#fali_show').show();
            return false;
        }else{
            if(check())
                $('#forms').submit();
        }

    }

    $("#userType").change(function () {
        showChildInfo();
    });

    $("#userType").val(${param.userType});

    showChildInfo();

    function showChildInfo() {
        var userType = $("#userType").val();
        if(userType==3|userType==4){
            $("#parentCodeDl").css("display","");
        }else {
            $("#parentCodeDl").css("display","none");
        }
    }
</script>