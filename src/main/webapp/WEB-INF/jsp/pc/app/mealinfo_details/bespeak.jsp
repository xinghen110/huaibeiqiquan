<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>全款预约</title>
</head>
<style>
    .ui-page-theme-a .ui-radio-on:after, html .ui-bar-a .ui-radio-on:after, html .ui-body-a .ui-radio-on:after, html body .ui-group-theme-a .ui-radio-on:after, .ui-btn.ui-radio-on.ui-btn-a:after{border-color: #31B16C /*{a-active-background-color}*/;}
    .ui-page-theme-a .ui-btn.ui-btn-active, html .ui-bar-a .ui-btn.ui-btn-active, html .ui-body-a .ui-btn.ui-btn-active, html body .ui-group-theme-a .ui-btn.ui-btn-active, html head + body .ui-btn.ui-btn-a.ui-btn-active, .ui-page-theme-a .ui-checkbox-on:after, html .ui-bar-a .ui-checkbox-on:after, html .ui-body-a .ui-checkbox-on:after, html body .ui-group-theme-a .ui-checkbox-on:after, .ui-btn.ui-checkbox-on.ui-btn-a:after, .ui-page-theme-a .ui-flipswitch-active, html .ui-bar-a .ui-flipswitch-active, html .ui-body-a .ui-flipswitch-active, html body .ui-group-theme-a .ui-flipswitch-active, html body .ui-flipswitch.ui-bar-a.ui-flipswitch-active, .ui-page-theme-a .ui-slider-track .ui-btn-active, html .ui-bar-a .ui-slider-track .ui-btn-active, html .ui-body-a .ui-slider-track .ui-btn-active, html body .ui-group-theme-a .ui-slider-track .ui-btn-active, html body div.ui-slider-track.ui-body-a .ui-btn-active{background-color: #31B16C /*{a-active-background-color}*/;border-color: #31B16C /*{a-active-border}*/; color: #fff /*{a-active-color}*/;text-shadow: 0 /*{a-active-shadow-x}*/ 1px /*{a-active-shadow-y}*/ 0 /*{a-active-shadow-radius}*/ #005599 /*{a-active-shadow-color}*/;}
    .time .ui-input-text{margin: 0;float: right;border: none;}
    .time input{text-align: right;}
    .time span{margin-top: .5em;}
    .right{width: 10px; height: 15px;margin-left: .5em; float: right;margin-top: .1em;}
    .sj-right{margin-top: .5em;}
    .ui-input-text{margin: 0;float: right;border: none; width: 51%;}
</style>
<body>
<div data-role="page" id="bespeak">
    <div data-role = "content">
    	<a data-ajax="false" onclick="goBack(1)"><img src="app/img/icon_return.png" style="width: 35px; position: absolute; left: 1.5em; top: 1.5em; z-index: 9999;"></a>
        <div class="bannder">
            <img src="${constants.QINIU_USER_IMGURL}${mealInfo.mainPhoto}" width="100%" height="180px" />
        </div>
        <div class="sjxm">
            <h4 class="name size-10">${mealInfo.title}</h4>
            <h4 class="bq"><span style="color: #EB610E;" class="size-10 fl">￥${mealInfo.mealPrice}</span><c:if test="${mealInfo.makeMethod == 1}"><span style="color: #A9A9A9;" class="size-8">(定金:<span class="size-8" style="color: #EB610E">￥${mealInfo.makePrice}</span>)</span></c:if><span style="color: #ccc;margin-top: .2em" class="size-9 fr">${mealInfo.mealLog}分钟</span></h4>
            <hr />
            <h4 class="size-7 xx">
                <span class="fl"><img src="app/img/icon_details_pro.png" class="fl" width="15px" height="15px" />${mealInfo.flag1}</span>
                <span class="fl"><img src="app/img/icon_details_technician.png" class="fl" width="15px" height="15px" />${shopInfo.shopName}</span>
                <span class="fl"><img src="app/img/icon_details_tick.png" class="fl" width="15px" height="15px" />
                    <c:if test="${mealInfo.makeMethod == 2}">全款预约</c:if>
                    <c:if test="${mealInfo.makeMethod == 1}">可定金预约</c:if>
                </span>
            </h4>
        </div>
        <p class="size-9 title">选择数量</p>
        <div class="sl">
            <button class="fl reduce choose_num">-</button>
            <button class="fl num">1</button>
            <button class="fl add choose_num">+</button>
        </div>
        <p class="size-9 title">服务方式</p>
        <div class="fwfs">
            <c:if test="${mealInfo.isMake == 1}">
                <label class="size-9" for="aa">技师上门</label>
                <input type="radio" name="greader" id="aa" onclick="fwfs(1)" value="1">
            </c:if>
            <label class="size-9" for="bb">客户到店</label>
            <input type="radio" name="greader" id="bb" onclick="fwfs(2)" value="2">
        </div>
        <p class="size-9 title">个人信息</p>
        <div class="grxx">
            <a data-role="button"><span class="fl" style=" margin-top: .5em;">姓名</span><img style=" margin-top: .55em;" src="app/img/icon_right.png" class="right"/><input placeholder="请输入姓名" id="name" class="fr address" width="100%" value="${userAddress.linkMan}" /></a>
            <a data-role="button"><span class="fl" style=" margin-top: .5em;">联系方式</span><img style=" margin-top: .55em;" src="app/img/icon_right.png" class="right"/><input placeholder="请输入电话" type="number" id="userPhone" class="fr address" width="100%" value="${userAddress.linkTel}" /></a>
            <a data-role="button" class="wc-address" href="#CCC"><span style="margin-top: .6em;" class="fl">上门地址</span><img src="app/img/icon_right.png" class="right sj-right"/><input placeholder="请选择地址" id="address" class="fr address" width="100%" onchange="address()" />
            </a>
            <a data-role="button" class="time"><span class="fl">上门时间</span><img src="app/img/icon_right.png" class="right sj-right"/><input id="smsj" value="" placeholder="请选择上门时间" readonly="readonly" /></a>
        </div>
        <p class="size-9 title">备注信息</p>
        <div class="bzxx">
            <textarea id="ddbz" placeholder="请输入您想要特别备注的信息"></textarea>
            <h4 class="size-8">限200字</h4>
        </div>
        <div class="xy">
            <label for="ccc">预约即默认同意</label>
            <input type="checkbox" id="ccc" checked />
            <a class="xy-a" data-ajax="false" href="app/pageshow/agreement?commonProblemNum=CP22280000000008">《91到位预约协议》</a>
        </div>
        <a data-role="button" onclick="viewCanYu()" class="pay">确认支付(￥<span id="price"><c:if test="${mealInfo.makeMethod == 2}">${mealInfo.mealPrice}</c:if><c:if test="${mealInfo.makeMethod == 1}">${mealInfo.makePrice}</c:if></span>)</a>
        <c:if test="${mealInfo.makeMethod == 1}">
            <div class="xzye">
                <label for="ddd">暂只支付定金预约，余额当面支付</label><!-- 点了定金预约只付定金  没有就全额支付 （该选项只在可定金预约的套餐才显示）-->
                <input type="checkbox" id="ddd" checked />
            </div>
        </c:if>
    </div>
    <input type="hidden" id="longitude" value="${userAddress.longitude}">
    <input type="hidden" id="latitude" value="${userAddress.latitude}">
    <input type="hidden" id="province" value="<ry:show parentCode="${userAddress.province}" itemCode="${userAddress.province}" type="2"></ry:show>">
    <input type="hidden" id="city" value="<ry:show parentCode="${userAddress.province}" itemCode="${userAddress.city}" type="3"></ry:show>">
    <input type="hidden" id="area" value="<ry:show parentCode="${userAddress.city}" itemCode="${userAddress.areas}" type="4"></ry:show>">
    <div id="searchResultPanel" style="display: none;"></div>
    <div style="display: none;" id="l-map"></div>
    <input type="hidden" class="home_address" name="address">
    <div id="result"></div>
</div>
<script type="text/javascript">
    $("#smsj").focus(function(){
        document.activeElement.blur();
    });
    //数量加减
    $(".add").click(function(){
        var num = $(".num").html();
        var money = $("#price").html();
        money = parseInt(money)/parseInt(num);
        $(".num").html(parseInt(num)+1);
        $("#price").html(money * (parseInt(num)+1));
    });
    $(".reduce").click(function(){
        var num = $(".num").html();
        if(parseInt(num)>1){
            var money = $("#price").html();
            money = parseInt(money)/parseInt(num);
            $(".num").html(parseInt(num)-1);
            $("#price").html(money * (parseInt(num)-1));
        }
    });

    //上门时间插件
    var calendar = new datePicker();
    calendar.init({
        'trigger': '#smsj', /*按钮选择器，用于触发弹出插件*/
        'type': 'datetime',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate':(new Date()).Format("yyyy-MM-dd"),/*最小日期*/
        'maxDate':'2100-12-31',/*最大日期*/
        'onSubmit':function(){/*确认时触发事件*/
            var theSelectData=calendar.value;
        },
        'onClose':function(){/*取消时触发事件*/
        }
    });

    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }
    var map = new BMap.Map("l-map");
    map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。
    map.enableScrollWheelZoom(true);
    var index = 0;
    var myGeo = new BMap.Geocoder();
    var adds = [];
    $("#bespeak").find("#address").on("input propertychange",function(){  //当
        $("#bespeak").find("#longitude").val("");
        $("#bespeak").find("#latitude").val("");
        G("searchResultPanel").innerHTML="";
        $("#bespeak").find(".home_address").val("");
    });
    function changeHouseNumber(){
        var keyword =$("#bespeak").find("#address").val();
        $("#bespeak").find(".home_address").val(keyword);
    }
    $(document).on("pageshow","#bespeak",function(){

        $("#bespeak").find("#address").click(function(){
            var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
                {"input" : "address"
                    ,"location" : map
                });
            $(".tangram-suggestion").show();
            var myValue;
            ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
                var _value = e.item.value;
                myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
                G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
                $("#bespeak").find(".home_address").val(myValue);
                setPlace();
            });

            function setPlace(){
                map.clearOverlays();    //清除地图上所有覆盖物
                function myFun(){
                    var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                    var lng = pp.lng;
                    var lat = pp.lat;
                    $("#bespeak").find("#longitude").val(lng);
                    $("#bespeak").find("#latitude").val(lat);
                    adds = [new BMap.Point($("#bespeak").find("#longitude").val(), $("#bespeak").find("#latitude").val())];

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
    function geocodeSearch(pt){
        myGeo.getLocation(pt, function(rs){
            var addComp = rs.addressComponents;
            $("#province").val(addComp.province);
            $("#city").val(addComp.city);
            $("#area").val(addComp.district);
        });
    }

    //技师上门与客户上门选择
    function fwfs(number) {
        $(".tangram-suggestion").hide();
        $(".gearDate").css("display","none");
        if (number == 1) {
            $("#aa").attr("checked", "checked");
            //取消预约协议
            $(".xzye").hide();
            //修改实付金额
            var num = $(".num").html();
            var money = '${mealInfo.mealPrice}';
            $("#price").html(money * parseInt(num));
            $("#address").attr('disabled', false);
            $("#address").val('<ry:show parentCode="${userAddress.province}" itemCode="${userAddress.city}" type="3"></ry:show><ry:show parentCode="${userAddress.city}" itemCode="${userAddress.areas}" type="4"></ry:show>${userAddress.address}');
            $("#address").parent().prev().show();
        } else {
            $("#bb").attr("checked", "checked");
            //显示预约协议
            $(".xzye").show();
            //修改实付金额
            var num = $(".num").html();
            var money = <c:if test="${mealInfo.makeMethod == 1}">'${mealInfo.makePrice}'</c:if><c:if test="${mealInfo.makeMethod == 2}">'${mealInfo.mealPrice}'</c:if>;
            $("#price").html(money * parseInt(num));
            $("#address").attr('disabled',true);
            $("#address").val('<ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.city}" type="3"></ry:show><ry:show parentCode="${shopInfo.city}" itemCode="${shopInfo.area}" type="4"></ry:show>${shopInfo.address}');
            $("#address").parent().prev().hide();
        }
    }

    function address() {
        var pt = adds[index];
        geocodeSearch(pt);
    }

    /**
     * 立即参与
     */
    function viewCanYu(){
        //同意协议
        if ($("input[name='greader']:checked").val() == 1) {
            var payType = 2;
            if (!$("#ccc").is(":checked")) {
                alert("请同意协议");
                return false;
            }
        }
        if ($("input[name='greader']:checked").val() == 2) {
            <c:if test="${mealInfo.makeMethod == 1}">
                var payType = 1;
                if (!$("#ccc").is(":checked") || !$("#ddd").is(":checked")) {
                    alert("请同意协议");
                    return false;
                }
            </c:if>
            <c:if test="${mealInfo.makeMethod == 2}">
                var payType = 2;
                if (!$("#ccc").is(":checked")) {
                    alert("请同意协议");
                    return false;
                }
            </c:if>
        }
        var totalCount = $(".num").html();
        var orderLinkMan = $("#name").val().trim();
        var orderLinkTel = $("#userPhone").val().trim();
        var totalPrice = $("#price").text();
        var orderLongitude = $("#longitude").val();
        var orderLatitude = $("#latitude").val();
        var userNum = '${userNum}';
        var province = $('#province').val();
        var city = $('#city').val();
        var area = $('#area').val();
        var orderRemark = $('#ddbz').val();
        var arrivaTime = $('#smsj').val().trim();
        var orderAddress = $('#address').val().substring(city.length + area.length);
        var serviceType = $("input[name='greader']:checked").val();
        var mealInfoNum = '${mealInfo.mealInfoNum}';
        if (orderLinkMan == '' || orderLinkTel == '' || arrivaTime == '') {
            alert("请补全收货信息！");
            return false;
        }
        if (orderAddress == '' || area == "" || orderLongitude == '' || orderLatitude == '') {
            alert("请重新选择地址！");
            return false;
        }
        var data = {
            "shopNum" : '${shopInfo.shopNum}',
            "userNum" : userNum,
            "totalCount" : totalCount,
            "totalPrice" : totalPrice,
            "orderLinkMan" : orderLinkMan,
            "orderLinkTel" : orderLinkTel,
            "orderLongitude" : orderLongitude,
            "orderLatitude" : orderLatitude,
            "province" : province,
            "city" : city,
            "area" : area,
            "orderRemark" : orderRemark,
            "arrivaTime" : arrivaTime,
            "orderAddress" : orderAddress,
            "serviceType" : serviceType,
            "mealInfoNum" : mealInfoNum,
            "payType" : payType
        }
        var array = JSON.stringify(data);
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if(isAndroid){
            window.ruanyun.viewCanYu(array);
        }else if(isiOS){
            window.webkit.messageHandlers.viewCanYu.postMessage(array);
        }
    }

    function goBack(num){
        var arrayString=num;
        var u = navigator.userAgent;
        window.history.go( -1 );
    }

    $(function () {
        if ('${mealInfo.isMake}' == '1') {
            $("#aa").click();
        } else if ('${mealInfo.isMake}' == '2') {
            $("#bb").click();
        }
    })
    
</script>
</body>
</html>