<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" href="css/web/mystyle.css" />
    <link rel="stylesheet" href="css/web/carousel.css" />
    <link rel="stylesheet" href="css/web/featureCarousel.css" />

    <script type="text/javascript" src="js/web/jquery.min.js" ></script>
    <script type="text/javascript" src="js/web/mj.js" ></script>
</head>
<body>
<!--<div class="header" style="width:100%;">
    <div style="width: 1280px; margin: 0 auto;">
        <img src="img/logo_jinniu.png" class="fl" />
        <ul>
            <li><a class="header-ul-select">首页</a></li>
            <li><a href="product_introduction.html">产品介绍</a></li>
            <li><a href="abouts_us.html">关于我们</a></li>
            <li><a>行业资讯</a></li>
            <li><a>实时解盘</a></li>
            <li><a>软件下载</a></li>
            <li><a href="realTimeOpening.html">实盘开户</a></li>
            <li><a>交易</a></li>
        </ul>
    </div>
</div>-->
<style>
    .user_login_header{background: url(img/web/img_user.jpg) no-repeat; width: 100%; background-size: 100% 100%; height: 221px; }
    .user_login_header_info{position: relative;width: 900px; margin: 0 auto; background: rgba(255,255,255,.8);height: 145px; top: 36px;}
    .user_login_header_info .img_user_tx{width: 155px; border-radius: 50%; position: absolute; left: -90px; margin-top: -5px;}
    .user_login_header_info .hr{position: relative; top: 77px; margin: 0 12%; border: none; border-top: 1px solid #999; margin-right: 5%;}
    .user_login_header_info .name_h4{position: absolute;left: 105px; top: 26px; font-size: 20px; color: #333333;}
    .user_login_header_info .exit_login{position: absolute; left: 385px; top: 19px; font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .user_login_header_info .exit_login:hover{background: red;}
    .user_login_header_info .tel_h4{position: absolute;left: 105px; top: 100px; font-size: 14px; color: #333333;}
    .user_login_header_info .update_password{position: absolute;left: 290px; top: 100px; font-size: 14px;color: #069;text-decoration: none;}
    .user_login_header_info .update_password:hover{color: #CE4972;}

    .personal_center .left_list .li_a_selected{background: #3984E1;color: white;}
    .personal_center{width: 1180px;margin: 0 auto;}
    .personal_center .left_list{float: left;width: 230px;border: 1px solid #EEEEEE;border-bottom: 0px; margin: 25px 0px;}
    .personal_center .left_list li{float: left; width: 100%; border-bottom: 1px solid #EEEEEE; position: relative;}
    .personal_center .left_list li h2{float: left;line-height: 50px; width: 205px; padding-left: 25px; background: #FAFAFA; color: #656565; font-size: 18px; font-weight: normal;}
    .personal_center .left_list li a{float: left; width: 130px;padding-left: 100px; line-height: 50px; color: #656565; font-size: 14px;}
    .personal_center .left_list li a:hover{background: #3984E1;color: white;}
    .personal_center .left_list li img{position: absolute; width: 21px; left: 69px; top: 14px}
    .personal_center_div{float: right; width: 915px; padding: 25px 0px;}
    .personal_center_div .member_title{width: 100%;height: 50px; border-bottom: 1px solid #DDDDDD;line-height: 50px; color: #333333; font-size: 14px; margin-bottom: 15px;}
    .personal_center_div .member_title h3{font-weight: bold!important;color: #333333; font-size: 16px;}
    table{float: left;margin-left: 20px;width: 830px; border: 1px solid #EEEEEE; border-left: 0px; border-bottom: 0px;margin-bottom: 20px;}
    table th,td{border-left: 1px solid #EEEEEE;border-bottom: 1px solid #EEEEEE;}
    table tr th{width: 20%; padding: 15px 0px; padding-right: 35px; text-align: right; color: #333333; font-size: 14px;line-height: 20px;vertical-align: top; font-weight: normal;}
    table tr td{padding: 12px 0px; padding-left: 25px; line-height: 25px; color: #333333;font-size: 14px;text-align: left;}
    table tr td span{color: #FF3F13; font-size: 16px; font-weight: bold!important;}
    table tr td input{width: 100px; height: 30px; line-height: 30px; border: 1px solid #999999;border-radius: 4px; margin-right: 10px; padding-left: 5px;}
    table tr td select{width: 105px; height: 35px; line-height: 35px; border: 1px solid #999999;border-radius: 4px; margin-right: 10px; padding-left: 5px;}
    /*去掉input[type=number]的默认样式*/
    input[type=number] {-moz-appearance:textfield; }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button { -webkit-appearance: none;margin: 0;}
    .next_btn{font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer; margin-left: 300px;}
    .next_btn:hover{background: red;}
</style>
<%@include file="user_login_header.jsp" %>
<div class="personal_center">
    <ul class="left_list">
        <li><h2>个人中心</h2></li>
        <li><img src="img/web/home_grey.png" /><a href="realName_authentication.html">个人资料</a></li>
        <li><img src="img/web/recharge_grey.png" /><a href="account_recharge.html">账户充值</a></li>
        <li><img src="img/web/withdrawal_grey.png" /><a href="account_presentation.html">账户提现</a></li>
        <li><img src="img/web/extension_white.png" /><a class="li_a_selected" href="promote_money.html">推广赚钱</a></li>
        <li><img src="img/web/data_grey.png" /><a href="my_plan.html">我的方案</a></li>
    </ul>
    <div class="personal_center_div">
        <div class="member_title">
            <h3>推广赚钱</h3>
        </div>
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td><input style="width: 98%; text-align: center; border: none; font-size: 20px;color: red; font-weight: bold!important;" id="copy" type="text" readonly="readonly" value="<%=basePath%>web/stock/register?parentCode=${sessionScope.systemUser.userId}" /></td>
            </tr>
            <tr>
                <td>
                    <button id="btn" class="next_btn">点击复制</button>
                </td>
            </tr>
        </table>
    </div>
</div>
<%@include file="footer.jsp" %>

<script type="text/javascript">
    function realName_authentication(){
        $('.realName_authentication_div').show()//显示实名认证弹窗
    }
    function close_realName_authentication(){
        $('.realName_authentication_div').hide()//显示实名认证弹窗
    }
    var btn = document.getElementById('btn');
    btn.addEventListener('click', function(){//点击复制链接地址
        document.getElementById("copy").focus();
        document.getElementById("copy").select();
        document.execCommand('copy', true);
        alert("复制成功！")
    });
</script>
</body>
</html>
