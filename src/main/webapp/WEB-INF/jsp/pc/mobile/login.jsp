<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
</head>
<%@ include file="base.jsp"%>
<body>
<style>
    #login input::-webkit-input-placeholder { color:white;}
    #login input:-moz-placeholder { color:white;}
    #login input::-moz-placeholder { color:white;}
    #login input:-ms-input-placeholder { color:white;}
</style>
<div data-role="page" id="login">
    <div data-role = "content" class="inquiry">
        <h4 style="margin-bottom: .5em" class="title size-15 text-align"><img width="145px" src="img/mobile/login_logo.png" /></h4>
        <%--<h6 class="size-8 text-align" style="color: #E1B080; margin: 0;">[演示版]</h6>--%>
        <form id="form" action="mobile/stock/login" data-ajax="false" method="post">
        <div class="input-div" style="margin-top: 4em;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_user.png" />
            <input id="loginName" name="loginName" type="number" data-role = "none" placeholder="请输入登录账号" />
        </div>
        <div class="input-div" style="margin-top: 1.5em;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_password.png" />
            <input id="password" name="loginPass" type="password" data-role = "none" placeholder="请输入登录密码" />
        </div>
        <div class="input-div" style="margin-top: 1.5em;">
            <a id="loginBtn" data-ajax = "false" class="ui-btn login">登录</a>
            <%--<a href="mobile/stock/register?parentCode=${sessionScope.parentCode}" data-ajax = "false" class="ui-btn reg">注册</a>--%>
            <a href="mobile/stock/password/forget" data-ajax = "false" class="fl color-w size-9 forgot_password">忘记密码?</a>
            <a href="mobile/stock/register?parentCode=${sessionScope.parentCode}" data-ajax = "false" class="fr color-red size-9 forgot_password">没有账号?去注册.</a>
        </div>
        </form>
    </div>
</div>
</body>
<script>
    $("#loginBtn").click(function () {
        if($("#loginName").val()==''||$("#password").val()==''){
            alert("请输入用户名和密码");
            return;
        }else {
            $("#form").submit();
        }
    });

</script>
</html>

