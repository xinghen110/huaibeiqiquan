<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
</head>
<style>
    #register .yzm {
        margin: 0;
        text-decoration: underline;
        position: absolute;
        right: 2em;
        bottom: 1em;
    }

    .agreement img {
        width: 15px;
        float: left;
        margin-top: 1px;
        margin-left: 1em;
        margin-right: .5em;
    }
</style>
<%@include file="base.jsp"%>
<body>
<div data-role="page" id="register">
    <div data-role = "header" style="border-width: 0; background: #28292E;">
        <a href="mobile/stock/login" data-role="button" data-ajax="false"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">注册</h4>
    </div>
    <div data-role = "content">
        <h4 style="margin-top: .5em" class="title size-12 text-align"><img width="145px" src="img/mobile/login_logo.png" /></h4>
        <form id="form" action="mobile/stock/register" data-ajax="false" method="post">
        <div class="input-div" style="margin-top: 1em;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_user.png" />
            <input name="loginName" id="loginName" type="number" data-role = "none" placeholder="请输入手机号" />
        </div>
        <div class="input-div" style="margin-top: 1.5em;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_password.png" />
            <input name="loginPass" type="password" id="loginPass" data-role = "none" placeholder="请输入登录密码" />
        </div>
        <div class="input-div" style="margin-top: 1.5em;position: relative;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_password.png" />
            <input name="identityCode" id="identityCode" type="number" data-role = "none" placeholder="请输入验证码" />
            <h4 onclick="sendMessage()" class="color-red size-9 yzm" style="margin: 0;text-decoration: underline;">获取验证码</h4>
        </div>
        <div class="input-div" style="margin-top: 1.5em;">
            <img style="width: 22px; position: absolute; margin-top: 13px; margin-left: 15px;" src="img/mobile/login_password.png" />
            <input name="parentCode"  id="parentCode" type="text" value="${sessionScope.parentCode}" data-role = "none" placeholder="请输入邀请码" />
        </div>
            <div class="input-div" style="margin: 0 1em;  margin-top: 1.5em; position: absolute; bottom: 0;">
            <h4 class="agreement color-w size-9">
                <img class="btn_select" src="img/mobile/btn_select.png" onclick="btn_select()" />
                <img style="display: none;" onclick="btn_selected()" class="btn_selected" src="img/mobile/btn_selected.png" />
                我已阅读并同意<a data-ajax="false" href="mobile/network/service/protocol" class="color-red">《鼎越护航用户服务协议》</a>
            </h4>
            </div>
        <div class="input-div" style="margin-top: 2em;">
            <a id="registeBtn" class="ui-btn register">注册</a>
            <a href="mobile/stock/login}" data-ajax = "false" class="fr color-red size-9 forgot_password">已有账号?去登录.</a>
        </div>
        </form>
    </div>
</div>
<script>
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var code = ""; //验证码
    var codeLength = 6;//验证码长度
    function sendMessage() {

        var loginName = $("#loginName").val();

        if (loginName == "") {
            layer.msg('登录名不能为空');
            return false;
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
            $("#register").find(".yzm").attr("onclick","sendMessage()");
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            count--;
            $("#register").find(".yzm").html("(" + count + ")秒内输入");
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


    $("#registeBtn").click(function () {

        var loginName = $("#loginName").val();
        var loginPass = $("#loginPass").val();
        var parentCode = $("#parentCode").val();
        var regValidate = $("#identityCode").val();

        var flag = $(".btn_selected").is(":visible");

        if (!flag) {
            layer.msg('请先同意条款');
            return false;
        }

        if (loginName === "") {
            layer.msg('登录名不能为空');
            return false;
        }

        if (loginPass === "") {
            layer.msg('密码不能为空');
            return false;
        }

        if (parentCode === "") {
            layer.msg('邀请码不能为空');
            return false;
        }

        if (regValidate === "") {
            layer.msg('验证码不能为空');
            return false;
        }



        $("#form").submit();
    });

    function btn_select(){
        $('.agreement_state').val('1');//不同意协议为 0 同意协议 为1      我要申请时通过该状态判断协议是否勾选
        $('.btn_select').hide();
        $('.btn_selected').show();
    }
    function btn_selected(){
        $('.agreement_state').val('0');
        $('.btn_select').show();
        $('.btn_selected').hide();
    }
</script>
</body>
</html>

