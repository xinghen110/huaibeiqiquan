<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title></title>
    <script type="text/javascript" src="../../../app/js/md5.js"></script>
    <script type="text/javascript" src="../../../app/js/jquery.min.js"></script>
    <script type="text/javascript">

        document.addEventListener('plusready', function(){
            //console.log("所有plus api都应该在此事件发生后调用，否则会出现plus is undefined。"

        });

    </script>
</head>
<style>
    body{background: url(../../img/bg_download.jpg) no-repeat;background-size:cover ;}
    input::-ms-input-placeholder{color: white;}
    input::-moz-placeholder{color: white;}
    input::-webkit-input-placeholder{color: white;}
    input{color:white;width: 80%; margin: 1rem 10%;height:3rem ;line-height: 3rem;
        font-size: 1rem;text-align: center;border-radius: 5px;border: none;position: fixed;}
    #dl1{background: rgba(0,0,0,0.5);bottom: 7rem;}
    #dl2{background: #f57b0b;bottom: 3rem;}
    h3{color: #646464;position: fixed;bottom: 0;width: 100%; text-align: center;font-weight: normal;font-size: 0.9rem;}
    h3 a{color: #F57B0B;}
</style>
<body>
<input type="tel" name="" id="dl1" value="" placeholder="输入手机号码"/>
<input type="button" name="" id="dl2" value="立即注册" onclick="ljzc()" />
<h3>我已阅读并同意<a href="../../pageshow/agreement?commonProblemNum=CP94380000000010">《91到位用户协议》</a></h3>
</body>
<script type="text/javascript">
    function ljzc() {
        var loginName = $("#dl1").val();
        var partten = /^1[3,5,8]\d{9}$/;
        if (loginName == '') {
            alert("请输入手机号！");
            return false;
        }
        if (!partten.test(loginName)) {
            alert("请输入正确的手机号码！");
            return false;
        }
        var data = {
            "parentCode" : '${user.userCode}',
            "loginName" : loginName,
            "userType" : 3,
            "loginPass" : hex_md5("123456")
        }
        $.post("../../external/"+loginName+"/user/add", data, function (data) {
            if (data.result == 1) {
                $("#dl1").hide();
                $("#dl2").removeAttr("onclick");
                $("#dl2").val("立即下载！");
            }
        })
    }
</script>
</html>