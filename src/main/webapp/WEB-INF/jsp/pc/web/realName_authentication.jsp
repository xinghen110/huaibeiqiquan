<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ry" uri="http://www.ruanyun.com/taglib/ry" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>"/>
<ry:binding parentCode="BANK_NAME" bingdingName="BANK_NAME"></ry:binding>
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
<style>
</style>
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
    .personal_center_div .personal_info{height: 500px; width: 48%; float: left; border: 1px solid #DDDDDD;margin-bottom: 20px; border-top: 1px solid #3A85E2;}
    .personal_center_div .personal_data_title{width: 100%; padding: 15px 0px; border-bottom: 1px solid #DDDDDD;line-height: 24px; height: 24px;}
    .personal_center_div .personal_data_title h3{float: left;width: auto; padding-left: 10px; border-left: 3px solid #2B8AFE; font-size: 16px;color: #333333;margin-left: 22px;}
    .account_div{text-align: center;margin-top: 125.5px;}
    .account_div h4{font-size: 16px;}
    .account_div h4 .price{font-size: 18px; font-weight: bold!important; padding-right: 10px;}
    .account_div .account_recharge{margin-top: 30px;font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .account_div .account_recharge:hover{background: red;}

    .realName_authentication{text-align: center;margin-top: 165.5px;}
    .realName_authentication h4{font-size: 16px;}
    .realName_authentication h4 .price{font-size: 18px; font-weight: bold!important; padding-right: 10px;}
    .realName_authentication .account_recharge{margin-top: 30px;font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;outline: none;cursor: pointer;}
    .realName_authentication .account_recharge:hover{background: red;}

    .realName_authentication_div{display: none;box-shadow:0px 5px 48PX #EBEBEB!important;background: white;width: 570px;height: 740px;margin: 0 auto; position: absolute;left: 50%;margin-left: -285px;top: 50%;margin-top: -324px;}

    .realName_authentication_div .title_div{float: left;width: 100%;height: 50px;background: #4997CF;}
    .realName_authentication_div .title_div i{float: left;padding-left: 18px;color: #fff;font-size: 18px;line-height: 50px;}
    .realName_authentication_div .title_div .close{color: white;float: right;margin-right: 18px;margin-top: 13px;cursor: pointer;width: 25px;height: 25px;}
    .realName_authentication_div .ts_info{margin-top: 10px;width: 100%;float: left;text-align: center;font-size: 12px;color: #333333;padding-bottom: 10px;}
    .realName_authentication_div .submit{margin-top: 11px;cursor: pointer;font-size: 15px; background: #3984E1;color: white; border: none; width: 140px; height: 40px; border-radius: 5px;    margin-left: 184px;}
    .realName_authentication_div .submit:hover{background:red;color:white}
</style>
<%@include file="user_login_header.jsp" %>


<div class="personal_center">
    <ul class="left_list">
        <li><h2>个人中心</h2></li>
        <li><img src="img/web/home_white.png" /><a href="web/stock/userinfo" class="li_a_selected">个人资料</a></li>
        <li><img src="img/web/recharge_grey.png" /><a href="web/stock/deposit">账户充值</a></li>
        <li><img src="img/web/withdrawal_grey.png" /><a href="web/stock/withdraw">账户提现</a></li>
        <c:if test="${sessionScope.systemUser.userType == sessionScope.spreadUserType}">
            <li><img src="img/web/extension_grey.png" /><a href="web/stock/promotion">推广赚钱</a></li>
        </c:if>
        <li><img src="img/web/data_grey.png" /><a href="web/stock/plan/list">我的方案</a></li>
    </ul>
    <div class="personal_center_div">
        <div class="personal_info">
            <div class="personal_data_title">
                <h3>我的资产</h3>
            </div>
            <div class="account_div">
                <h4>账户总余额：<strong class="price">${userAccount.money}</strong>元</h4>
                <button class="account_recharge" onclick="account_recharge()">账户充值</button>
            </div>
        </div>
        <div class="personal_info" style="margin-left: 30px;">
            <div class="personal_data_title">
                <h3>实名认证</h3>
                <div class="realName_authentication">
                    <h4>实名认证及银行卡绑定</h4>
                    <button onclick="realName_authentication()" class="account_recharge">立即认证</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="realName_authentication_div">
    <div class="title_div">
        <i>实名认证</i>
        <span onclick="close_realName_authentication()" class="close">x</span>
    </div>
    <h5 class="ts_info">实名信息提交后不可修改，请务必填写真实资料，并保证与银行卡预留个人信息一致</h5>
    <div class="index1-div-div">
        <form action="web/stock/authentication" method="post" id="forms">
            <input type="hidden" name="userId" value="${userInfo.userId}">
        <div>
            <h4 class="fl ">姓名：</h4>
            <input class="fl" id="userName" type="text" <c:if test="${userInfo.userName != null}">readonly="readonly"</c:if> placeholder="请输入您的真实姓名" name="userName" value="${userInfo.userName}" maxlength="6" />
        </div>
        <div>
            <h4 class="fl ">身份证号：</h4>
            <c:if test="${userInfo.idNumber == null}">
                <input id="idNumber" class="fl" type="text" maxlength="18" placeholder="请输入您的身份证号" maxlength="18" name="idNumber" value="${userInfo.idNumber}" >
            </c:if>
            <c:if test="${userInfo.idNumber != null}">
                <input id="idNumber" class="fl" type="text" readonly="readonly" placeholder="请输入您的身份证号" name="idNumber" value="${userInfo.idNumber}" >
            </c:if>
        </div>
<c:if test="${userInfo.idNumber == null}">
        <div>
            <h4 class="fl ">身份证正面照片：</h4>
            <input  onclick="clickFile(this)" class="fl" readonly="readonly" value="${userInfo.idCardFrontView}" id="id_card_front" type="text" placeholder="请选择身份证正面照片" />
            <%--<input type="file" onchange="qrFile(this)" style="display: none;" />--%>
            <input id="uploadImage" type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#id_card_front','idCardFrontView','id_card_front')" placeholder="请选择身份证正面照片" />
            <input type="hidden" style="display: none;" name="idCardFrontView" id="idCardFrontView" value="${userInfo.idCardFrontView}" />

        </div>
        <div>
            <h4 class="fl ">身份证反面照片：</h4>
            <input onclick="clickFile(this)" class="fl" readonly="readonly" id="id_card_back" type="text" value="${userInfo.backCardPhoto}" placeholder="请选择身份证反面照片" />
            <%--<input type="file" onchange="qrFile(this)" style="display: none;" />--%>
            <input id="uploadImage" type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#id_card_back','idCardBackView','id_card_back')" placeholder="请选择身份证反面照片" />
            <input type="text" style="display: none;" name="idCardBackView" id="idCardBackView" value="${userInfo.idCardBackView}" />
        </div>
</c:if>
            <c:if test="${userInfo.idNumber != null}">
                <div>
                    <h4 class="fl ">是否实名：</h4>
                    <input class="fl" type="text" readonly="readonly" name="realName" value="已实名，不能再实名">
                        <%--<input class="fl" type="text"--%>
                        <%--readonly="readonly" name="realName" value="已实名，不能再实名"/>--%>
                </div>
                <input type="hidden" id="idCardFrontView" name="idCardFrontView"  value="${userInfo.idCardFrontView}" />
                <input type="hidden" id="idCardBackView" name="idCardBackView" value="${userInfo.idCardBackView}" />
            </c:if>
        <div>
            <h4 class="fl ">银行卡号：</h4>
            <input id="bankCardNumber" maxlength="19" class="fl" name="bankCardNumber" type="text" value="${userInfo.bankCardNumber}" placeholder="请输入您的银行卡号" />
        </div>
        <div>
            <h4 class="fl ">所属银行：</h4>
            <select id="bankId" name="bankId">
                <option value="">请选择银行</option>
                <c:forEach var="item" items="${BANK_NAME}">
                <option value="${item.itemName}" <c:if test="${item.itemName == userInfo.bankId}">selected</c:if>>${item.itemName}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <h4 class="fl ">银行卡照片：</h4>
            <input onclick="clickFile(this)" class="fl" readonly="readonly" value="${userInfo.backCardPhoto}" id="back_card_photo" type="text" placeholder="请选择您的银行卡照片" />
            <%--<input type="file" onchange="qrFile(this)" style="display: none;" />--%>
            <input id="uploadImage" type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#back_card_photo','backCardPhoto','back_card_photo')" placeholder="请选择您的银行卡照片" />
            <input type="hidden" style="display: none;" name="backCardPhoto" id="backCardPhoto" value="${userInfo.backCardPhoto}" />
        </div>
        <h5 class="ts_info">认证信息必须与公安部官方数据库一致</h5>
        <button class="submit" type="button" onclick="checkForms();">提交</button>
        </form>
    </div>
</div>

<%@include file="updatePassword.jsp" %>
<%@include file="footer.jsp" %>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/ajax2.js"></script>
<script type="text/javascript">
    var wH = $(window).height(); //获取屏幕高度

    function account_recharge(){
        window.location.href = 'web/stock/deposit' //账户充值
    }

    function realName_authentication(){
        $('.realName_authentication_div').show()//显示实名认证弹窗
    }
    function updata_password(){
        $('.updata_password_div').show()//显示实名认证弹窗
    }
    function close_realName_authentication(){
        $('.realName_authentication_div').hide()//显示实名认证弹窗
    }
    function clickFile(obj){ //点击触发input file
        $(obj).parent().find("input[type='file']").click();
    }
    function qrFile(obj){//选中图片时的判断
        var file = obj.files[0];
        //判断类型是不是图片
        if(!/image\/\w+/.test(file.type)){
            alert("请确保文件为图像类型");
            return false;
        }
        var fileName="";
        if(typeof(fileName) != "undefined")
        {
            fileName = $(obj).val().split("\\").pop();
            fileName=fileName.substring(0, fileName.lastIndexOf("."));
        }
        alert(fileName)
        $(obj).parent().find("input[type='text']").val(fileName);
    }



</script>
<script type="text/javascript">
    $(document).ready(function () {
        var imgPath = $("#imgPath").val();
        var imgSrc1 = $("#id_card_front .headeait").attr("src");
        if(imgSrc1 == "${constants.QINIU_USER_IMGURL}") {
            $("#id_card_front .headeait").attr("src",imgPath);
        }

        var imgSrc2 = $("#id_card .headeait").attr("src");
        if(imgSrc1 == "${constants.QINIU_USER_IMGURL}") {
            $("#id_card .headeait").attr("src",imgPath);
        }

        var imgSrc3 = $("#back_card .headeait").attr("src");
        if(imgSrc1 == "${constants.QINIU_USER_IMGURL}") {
            $("#back_card .headeait").attr("src",imgPath);
        }
    });

    //身份证正面照
    $("#id_card_front .headeait").click(function(){
        filebtn1();
    });

    function filebtn1(){
        $("#id_card_front .filebtn").click();
    }

    //身份证反面照
    $("#id_card .headeait").click(function(){
        filebtn2();
    });

    function filebtn2(){
        $("#id_card .filebtn").click();
    }

    //银行卡正面照
    $("#back_card .headeait").click(function(){
        filebtn3();
    });

    function filebtn3(){
        $("#back_card .filebtn").click();
    }

    function readFile1(obj,id,string,rstring){
        var file = obj.files[0];
        if(!/image\/\w+/.test(file.type)){
            alert("请确保文件为图像类型");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            var imgBase64Data =encodeURIComponent(e.target.result);
            $(id + " .headeait").attr("src",this.result);
            var res = (this.result);
            var pos = imgBase64Data.indexOf("4")+4;
            imgBase64Data = imgBase64Data.substring(pos);
            $('#'+string).val(imgBase64Data);
//			var formData = $("imgBase64Data").serialize();
//			formData =decodeURIComponent(formData,true);
            var result = new MyJqueryAjax('web/stock/update/authentication','imgBase64Data='+imgBase64Data,null,'text').request();
            $('#'+string).val(result);
            $('#'+rstring).val(result);
             alert("上传成功");
        }
    }
    //换头像end
    function checkForms() {
        var isOk = false;
        if(!$("#userName").val()){
            isOk = false;
            alert("姓名不能为空");
            return false;
        }else {
            isOk = true;
        }
        if(!$("#idNumber").val()){
            isOk = false;
            alert("身份证号码不能为空");
            return false;
        }else {
            isOk = true;
        }
        if(!$("#idCardFrontView").val()){
            alert("身份证正面照片信息不能为空");
            isOk = false;
            return false;
        }else {
            isOk = true;
        }
        if(!$("#idCardBackView").val()){
            alert("身份证背面照片信息不能为空");
            isOk = false;
            return false;
        }else {
            isOk = true;
        }
        if(!$("#bankCardNumber").val()){
            isOk = false;
            alert("银行卡卡号不能为空");
            return false;
        }else {
            isOk = true;
        }
        if(!$("#bankId").val()){
            isOk = false;
            alert("请先选择所属银行");
            return false;
        }else {
            isOk = true;
        }
        if(!$("#backCardPhoto").val()){
            isOk = false;
            alert("银行卡照片不能为空");
            return false;
        }else {
            isOk = true;
        }
        if(isOk){
            $("#forms").submit();
        }else {
            return false;
        }
    }
</script>
</body>
</html>

