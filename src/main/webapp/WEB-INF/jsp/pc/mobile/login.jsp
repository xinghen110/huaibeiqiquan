<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1,viewport-fit=cover">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, viewport-fit=cover">
    <title>用户登录</title>
</head>
<%@ include file="base.jsp"%>
<body>
<style>
    body {
    padding-top: constant(safe-area-inset-top);   //为导航栏+状态栏的高度 88px            
    padding-left: constant(safe-area-inset-left);   //如果未竖屏时为0                
    padding-right: constant(safe-area-inset-right); //如果未竖屏时为0                
    padding-bottom: constant(safe-area-inset-bottom);//为底下圆弧的高度 34px       
}
    #login input::-webkit-input-placeholder { color:#000000;}
    #login input:-moz-placeholder { color:#000000;}
    #login input::-moz-placeholder { color:#000000;}
    #login input:-ms-input-placeholder { color:#000000;}
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
        <div class="input-div" style="margin-top: 1em;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_password.png" />
            <input id="password" name="loginPass" type="password" data-role = "none" placeholder="请输入登录密码" />
        </div>
        <div class="input-div" style="margin-top: 1em;">
            <a id="loginBtn" data-ajax = "false" class="ui-btn login">登录</a>
            <%--<a href="mobile/stock/register?parentCode=${sessionScope.parentCode}" data-ajax = "false" class="ui-btn reg">注册</a>--%>
            <a href="mobile/stock/password/forget" data-ajax = "false" class="fl color-w size-9 forgot_password">忘记密码?</a>
           
            <a href="mobile/stock/register?parentCode=${sessionScope.parentCode}" data-ajax = "false" class="fr color-red size-9 forgot_password">没有账号?去注册.</a>
        </div>
		 <div style="display:none" class="input-div" style="margin-top: 3em;">
			<!--<input type="checkbox" id="rememberName" checked>记住账号
            <input type="checkbox" id="rememberPass" checked>记住密码-->
			<input type="hidden" id="rememberName" checked>记住账号</input>
			<input type="hidden" id="rememberPass" checked>记住密码</input>
			</div>
        </form>
    </div>
</div>
</body>
<script>
    $("#loginBtn").click(function () {

        var loginName = $('#loginName').val();
        var password = $('#password').val();
        if($('#rememberName').is(':checked')){
            localStorage.setItem('loginName',loginName);
        }else{
            localStorage.removeItem('loginName');
        }
        if($('#rememberPass').is(':checked')){
            localStorage.setItem('password',password);
        }else{
            localStorage.removeItem('password');
        }


        if($("#loginName").val()==''||$("#password").val()==''){
            alert("请输入用户名和密码");
            return;
        }else {
            $("#form").submit();
        }
    });

    $(document).ready(function(){

        var loginName = localStorage.getItem('loginName');
        var password = localStorage.getItem('password');
        if(loginName){
            $('#loginName').val(loginName);
        }
        if(password){
            $('#password').val(password);
        }
    });

</script>
</html>

