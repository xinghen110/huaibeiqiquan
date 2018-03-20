<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>忘记密码</title>
</head>
<style>
    #forgot-password .yzm {
        margin: 0;
        text-decoration: underline;
        position: absolute;
        right: 2em;
        bottom: 1em;
    }
    .alertWord{
        color: red;
    }
</style>
<%@include file="base.jsp"%>
<body>
<div data-role="page" id="forgot-password">
    <div data-role = "header" style="border-width: 0; background: #28292E;">
        <a href="mobile/stock/login" data-role="button" data-ajax="false"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">找回密码</h4>
    </div>
    <div data-role = "content">
        <form id="form" data-ajax="false" action="mobile/stock/password/forget" method="post">
        <div class="input-div" style="margin-top: 4em;">
            <input id="loginName" name="userTel" type="number" data-role = "none" placeholder="请输入登录账号"/>
            <br><span id="loginNameAlertWord" style="display: none;" class="alertWord">请输入手机号！</span>
        </div>
        <div class="input-div" style="margin-top: 1.5em;position: relative;">
            <input name="identityCode" type="number" data-role = "none" placeholder="请输入验证码" />
            <h4 onclick="sendMessage()" class="color-w size-9 yzm" style="margin: 0;text-decoration: underline;">获取验证码</h4>
            <br><span id="identityCodeAlertWord" style="display: none;" class="alertWord">请输入验证码！</span>
        </div>
        <div class="input-div" style="margin-top: 1.5em;">
            <input id="password" type="password" name="newPassword" data-role = "none" placeholder="请输入登录密码" />
            <br><span id="passwordAlertWord" style="display: none;" class="alertWord">请输入至少六位数密码！</span>
        </div>
        <div class="input-div" style="margin-top: 1.5em;">
            <input id="confirm_password" type="password" equalTo="#password" data-role = "none" placeholder="请输入确认密码" />
            <br><span id="confirm_passwordAlertWord" style="display: none;" class="alertWord">两次密码输入不同！</span>
        </div>
        <div class="input-div" style="margin-top: 2em;">
            <a id="findPasswordBtn" class="ui-btn register">确认找回</a>
        </div>
        </form>
    </div>
</div>
<script>

    var isOk = true;
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var code = ""; //验证码
    var codeLength = 6;//验证码长度
    function sendMessage() {
        if($("#loginName").val().length==0){
            isOk = false;
            $("#loginNameAlertWord").css("display","");
            return
        }else {
            isOk = true;
            $("#loginNameAlertWord").css("display","none");
        }
        sendIdentityCodeToTelphone("loginName");
        $("#register").find(".yzm").removeAttr("onclick");
        //设置button效果，开始计时
        $("#register").find(".yzm").html("(" + count + ")秒内输入");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    }
    //timer处理函数
    function SetRemainTime() {
        if (count == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#register").find(".yzm").removeAttr("disabled");//启用按钮
            $("#register").find(".yzm").html("请重新发送");
            count=60;
            $("#register").find(".yzm").attr("onclick","sendMessage2()");
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            count--;
            $("#register").find(".yzm").html("(" + count + ")秒内输入");
        }
    }
    
    $("#findPasswordBtn").click(function () {
        if(isOk){
            $("#form").submit();
        }
    });
        
    $("#loginName").change(function () {
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test($("#loginName").val())) {
            isOk = false;
            $("#loginNameAlertWord").css("display","");
        } else {
            isOk = true;
            $("#loginNameAlertWord").css("display","none");
        }
    });

    $("#password").change(function () {
        if ($("#password").val().length<6) {
            isOk = false;
            $("#passwordAlertWord").css("display","");
        } else {
            isOk = true;
            $("#passwordAlertWord").css("display","none");
        }
    });

    $("#confirm_password").change(function () {
        if($("#confirm_password").val()!=$("#password").val()){
            isOk = false;
            $("#confirm_passwordAlertWord").css("display","");
        }else {
            isOk = true;
            $("#confirm_passwordAlertWord").css("display","none");
        }
    });
    
    function sendIdentityCodeToTelphone(idName) {
        $.ajax({
            type: "post",
            url: "web/user/register/send/identity/code",
            data: {userTel:$("#"+idName).val()},
            dataType: "json",
            success: function(data){
                console.log(data);
            }
        });
    }
</script>
</body>
</html>

