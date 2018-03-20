<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .updata_password_div{text-align: center;margin-top: 165.5px;}
    .updata_password_div h4{font-size: 16px;}
    .updata_password_div h4 .price{font-size: 18px; font-weight: bold!important; padding-right: 10px;}
    .updata_password_div .account_recharge{margin-top: 30px;font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .updata_password_div .account_recharge:hover{background: red;}

    .updata_password_div{display: none;box-shadow:0px 5px 48PX #EBEBEB!important;background: white;width: 570px;height: 648px;margin: 0 auto; position: absolute;left: 50%;margin-left: -285px;top: 50%;margin-top: -324px;}

    .updata_password_div .title_div{float: left;width: 100%;height: 50px;background: #4997CF;}
    .updata_password_div .title_div i{float: left;padding-left: 18px;color: #fff;font-size: 18px;line-height: 50px;}
    .updata_password_div .title_div .close{color: white;float: right;margin-right: 18px;margin-top: 13px;cursor: pointer;width: 25px;height: 25px;}
    .updata_password_div .ts_info{margin-top: 10px;width: 100%;float: left;text-align: center;font-size: 12px;color: #333333;padding-bottom: 10px;}
    .updata_password_div .submit{margin-top: 11px;cursor: pointer;font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;    margin-left: 184px;}
    .updata_password_div .submit:hover{background:red;color:white}
    .confirm_updata {font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .confirm_updata:hover{background: red;}
</style>
<div class="updata_password_div">
    <form id="update_form"action="web/stock/password/update" method="post">
        <div class="title_div" style="margin-bottom: 40px;">
            <i>修改密码</i>
            <span onclick="close_updata_password_div()" class="close">x</span>
        </div>
        <div class="index1-div-div" style="margin-bottom: 20px;">
            <div style="width: 100%;">
                <h4 style="width: 75px;" class="size-9 clear fl">手机号</h4>
                <input name="loginName" id="loginName" style="margin-left: -25px;" type="text" placeholder="请输入手机号" />
            </div>
            <div>
                <h4 style="width: 75px;" class="size-9 fl">验证码</h4>
                <input id="identityCode" name="identityCode" style="margin-left: -23px;" type="text" placeholder="请输入收到的验证码" />
                <a style="font-size: 14px;position: absolute; margin-left: -91px; margin-top: 11px;" class="zhmm-yzm" onclick="sendMessage2()">获取验证码</a>
            </div>
            <div>
                <h4 style="width: 75px;" class="size-9 fl">登录密码</h4>
                <input id="password" name="password" style="margin-left: -23px;" type="password" placeholder="请输入登录密码" />
            </div>
            <div>
                <h4 style="width: 75px;" class="size-9 fl">确认密码</h4>
                <input id="repassword" style="margin-left: -23px;" type="password" placeholder="请确认登录密码" />
            </div>
            <button class="confirm_updata" onclick="checkUpdatePassworForm()"  type="button">确定修改</button>
        </div>
    </form>
</div>
<script>

    //修改密码的验证码获取
    var InterValObj2; //timer变量，控制时间
    var count2 = 60; //间隔函数，1秒执行
    var code2 = ""; //验证码
    function sendMessage2() {
        sendIdentityCodeToTelphone("loginName");
        $(".zhmm-yzm").removeAttr("onclick");
        //设置button效果，开始计时
        $(".zhmm-yzm").html("(" + count2 + ")秒内输入");
        InterValObj2 = window.setInterval(SetRemainTime2, 1000); //启动计时器，1秒执行一次
    }

    //timer处理函数
    function SetRemainTime2() {
        if (count2 == 0) {
            window.clearInterval(InterValObj2);//停止计时器
            $(".zhmm-yzm").removeAttr("disabled");//启用按钮
            $(".zhmm-yzm").html("请重新发送");
            count2=60;
            $(".zhmm-yzm").attr("onclick","sendMessage2()");
            code2 = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            count2--;
            $(".zhmm-yzm").html("(" + count2 + ")秒内输入");
        }
    }
    /**
     * 请求服务器发送验证码到手机
     * @param idName
     */
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

    function updata_password(){
        $('.updata_password_div').show()//显示实名认证弹窗
    }
    function close_updata_password_div(){
        $('.updata_password_div').hide()//显示实名认证弹窗
    }

    /**
     * 检验表单
     */
    function checkUpdatePassworForm() {
        var isOk = false;
        //手机号不能为空
        if(!$("#loginName").val()){
            isOk = false;
            alert("手机号不能为空");
            return false;
        }else {
            isOk = true;
        }

        //验证码不能为空
        if(!$("#identityCode").val()){
            isOk = false;
            alert("验证码不能为空");
            return false;

        }else {
            isOk = true;
        }

        //登录密码不能为空
        if(!$("#password").val()){
            isOk = false;
            alert("登录密码不能为空");
            return false;
        }else {
            isOk = true;
        }
        //确认密码是否一致
        if($("#repassword").val()!=$("#password").val()){
            isOk = false;
            alert("两次输入密码不一致");
            return false;
        }else {
            isOk = true;
        }
        if(isOk){
            $("#update_form").submit();
        }
    }
</script>