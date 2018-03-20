<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>我是商家</title>
</head>
<script type="text/javascript" src="app/js/md5.js"></script>
<style>
    .ui-page-theme-a .ui-radio-on:after, html .ui-bar-a .ui-radio-on:after, html .ui-body-a .ui-radio-on:after, html body .ui-group-theme-a .ui-radio-on:after, .ui-btn.ui-radio-on.ui-btn-a:after{border:#31B16C}
</style>
<body>
<div data-role="page" id="technician">
    <div data-role = "header" style="background: white;border-width: 0px">
        <a data-role="button" onclick="goBack(-1);"><img src="app/img/icon_nav_back.png"/></a>
        <h4>我是商家</h4>
    </div>
    <div data-role = "content">
        <form id="forms"><!-- 商家  -->
            <div>
                <h4>登录名：</h4>
                <input type="text" id="loginName" placeholder="请输入手机号" />
            </div>
            <div>
                <h4>输入密码：</h4>
                <input type="text" id="password" placeholder="请输入密码" />
            </div>
            <div>
                <h4>再输密码：</h4>
                <input type="text" id="password2" placeholder="请再输入密码" />
            </div>
            <div>
                <h4>店铺名称：</h4>
                <input type="text" placeholder="请输入店铺名称" id="zsxm"/>
            </div>
            <div>
                <h4>联系方式：</h4>
                <input type="number" placeholder="请输入店铺联系方式" id="sjhm" />
            </div>
            <div>
                <h4>店铺地址：</h4>
                <input type="text" id="businessAddress" placeholder="请输入店铺详细地址" />
            </div>
            <div style="margin-top: 1em;">
                <label class="fl" style="margin-left: 1em;margin-top: 1em;" for="day">服务类别：</label>
                <select name="day" id="day" multiple="multiple" data-native-menu="false">
                    <option>选择店铺提供的服务类别</option>
                    <c:forEach items="${typeInfo}" var="item">
                        <option value="${item.typeNum}">${item.typeInfoName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="tjsh">
                <button type="button" id="submit" >确定添加</button>
            </div>
            <input type="hidden" id="longitude" value="">
            <input type="hidden" id="latitude" value="">
            <input type="hidden" id="city" value="">
            <input type="hidden" id="area" value="">
        </form>
    </div>
</div>
<div id="searchResultPanel" style="display: none;"></div>
<div style="display: none;" id="l-map"></div>
<input type="hidden" class="home_address" name="address">
<script type="text/javascript">

    //地址选择
    $("#submit").click(function(){
        var nickName = $('#zsxm').val().trim();
        var userPhone = $('#sjhm').val().trim();
        var city = $("#city").val();
        var area = $("#area").val();
        var typeNum = $('#day').val();
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();
        var loginName = $("#loginName").val().trim();
        var password1 = $("#password").val().trim();
        var password2 = $("#password2").val().trim();
        if(nickName == "" || userPhone == "" || $('#businessAddress').val().trim() == "" || typeNum == null || loginName == "" || password1 == '' || password2 == ''){
            alert("数据填写不完整！");
            return false;
        }
        if (password1 != password2) {
            alert("两次输入的密码不一致！");
            return false;
        }
        var address = $('#businessAddress').val().trim().substring(city.length + area.length);
        //非空判断
        var data = {
            "loginName" : loginName,
            "password1" : hex_md5(password1),
            "shopType" : '${shopType}',
            "nickName" : nickName,
            "userPhone" : userPhone,
            "address" : address,
            "typeNum" : typeNum.toString(),
            "longitude" : longitude,
            "latitude" : latitude,
            "city" : city,
            "area" : area
        }
        $.post("shopinfo/add_shopinfo", data, function (data) {
            if (data == "true") {
                alert("添加商家成功！");
                window.close();
            } else {
                alert("添加商家失败！");
            }
        })
    });

    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }
    var map = new BMap.Map("l-map");
    map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

    $("#technician").find("#businessAddress").on("input propertychange",function(){  //当
        $("#technician").find("#longitude").val("");
        $("#technician").find("#latitude").val("");
        G("searchResultPanel").innerHTML="";
        $("#technician").find(".home_address").val("");
    });
    function changeHouseNumber(){
        var keyword =$("#technician").find("#address").val();
        $("#technician").find(".home_address").val(keyword);
    }
    $(document).on("pageshow","#technician",function(){
        $("#technician").find("#businessAddress").click(function(){
            var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
                {"input" : "businessAddress"
                    ,"location" : map
                });
            $(".tangram-suggestion").show();
            var myValue;
            ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
                var _value = e.item.value;
                myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
                $("#city").val(_value.city);
                $("#area").val(_value.district);
                G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
                $("#technician").find(".home_address").val(myValue);
                setPlace();
            });

            function setPlace(){
                map.clearOverlays();    //清除地图上所有覆盖物
                function myFun(){
                    var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                    var lng = pp.lng;
                    var lat = pp.lat;
                    $("#technician").find("#longitude").val(lng);
                    $("#technician").find("#latitude").val(lat);

                    map.centerAndZoom(pp, 18);
                    map.addOverlay(new BMap.Marker(pp));    //添加标注
                }
                var local = new BMap.LocalSearch(map, { //智能搜索
                    onSearchComplete: myFun
                });
                local.search(myValue);
            }
        });
    });
    $(document).on("pagebeforehide","#technician",function(){
        $(".tangram-suggestion").hide();
        $(".gearDate").css("display","none");
    });

    function goBack(num) {
        window.history.back();
    }
</script>
</body>
</html>