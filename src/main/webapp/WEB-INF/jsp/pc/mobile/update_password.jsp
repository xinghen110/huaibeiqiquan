<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
</head>
<style>
    #update_password .ui-content div input {
        width: 45%!important;
        outline: none;
        border: none;
        margin-top: .5em;
        background: none;
    }
</style>
<%@include file="base.jsp"%>
<body>
<div data-role="page" id="update_password">
    <div data-role = "header" style="border-width: 0; background: #28292E;">
        <a href="mobile/stock/userinfo" data-role="button" data-ajax="false" ><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">修改密码</h4>
    </div>
    <div data-role = "content">
        <form id="form" action="mobile/stock/password/update" method="post" data-ajax="false">
        <div>
            <h4 class="fl">手机号</h4>
            <input id="loginName" name="loginName" style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="text" placeholder="输入手机号码" />
        </div>
        <div>
            <h4 class="fl">验证码</h4>
            <input name="identityCode" type="number"   style="margin-left: 1em;"class="fl color-w size-9" data-role = "none" placeholder="输入验证码" />
            <h4 onclick="sendMessage()" class="color-w size-9 yzm" style="    float: right;margin: 0;text-decoration: underline;">获取验证码</h4>
        </div>
        <div>
            <h4 class="fl">新密码</h4>
            <input name="password" style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="password" placeholder="输入新密码" />
        </div>
        <div>
            <h4 class="fl">确认密码</h4>
            <input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="password" placeholder="输入确认密码" />
        </div>
        <a id="updatePasswordBtn" data-ajax = "false" href="" class="ui-btn">确定</a>
        </form>
    </div>
</div>
</body>
<script>
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var code = ""; //验证码
    var codeLength = 6;//验证码长度
    function sendMessage() {
        sendIdentityCodeToTelphone("loginName");
        $("#update_password").find(".yzm").removeAttr("onclick");
        //设置button效果，开始计时
        $("#update_password").find(".yzm").html("(" + count + ")秒内输入");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    }
    //timer处理函数
    function SetRemainTime() {
        if (count == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#update_password").find(".yzm").removeAttr("disabled");//启用按钮
            $("#update_password").find(".yzm").html("请重新发送");
            count=60;
            $("#update_password").find(".yzm").attr("onclick","sendMessage2()");
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            count--;
            $("#update_password").find(".yzm").html("(" + count + ")秒内输入");
        }
    }

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


    $("#updatePasswordBtn").click(function () {
        $("#form").submit();
    });

</script>
</html>

