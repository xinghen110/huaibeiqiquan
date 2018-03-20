<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
	<title>我是技师</title>
</head>
<style>
	.ui-page-theme-a .ui-radio-on:after, html .ui-bar-a .ui-radio-on:after, html .ui-body-a .ui-radio-on:after, html body .ui-group-theme-a .ui-radio-on:after, .ui-btn.ui-radio-on.ui-btn-a:after{border:#31B16C}
</style>
<body>
<div data-role="page" id="technician">
	<div data-role = "header" style="background: white;border-width: 0px">
		<a data-role="button" onclick="goBack(-1);"><img src="app/img/icon_nav_back.png"/></a>
		<h4>我是技师</h4>
	</div>
	<div data-role = "content">
		<div style="background: white;overflow: hidden;height: 4em;margin-top: 1em;">
			<div style="overflow: hidden;margin: 0 2em;margin-top: .5em">
				<progress  max="100"
						   <c:if test="${empty user.auditShopStatus || user.auditShopStatus == 3}">value="0"</c:if>
						   <c:if test="${user.auditShopStatus == 2}">value="50"</c:if>
						   <c:if test="${user.auditShopStatus == 1}">value="100"</c:if>></progress>
				<div class="fl" >
					<img src="app/img/icon_greey.png" />
					<h4>填写资料</h4>
				</div>
				<div class="fl center" >
					<c:if test="${empty user.auditShopStatus || user.auditShopStatus == 3}">
						<img src="app/img/icon_hs.png"  />
					</c:if>
					<c:if test="${user.auditShopStatus == 2 || user.auditShopStatus == 1}">
						<img src="app/img/icon_greey.png" />
					</c:if>
					<h4>提交审核</h4>
				</div>
				<div class="fl last">
					<c:if test="${user.auditShopStatus != 1}">
						<img src="app/img/icon_hs.png"  />
					</c:if>
					<c:if test="${user.auditShopStatus == 1}">
						<img src="app/img/icon_greey.png" />
					</c:if>
					<h4>注册成功</h4>
				</div>
			</div>
		</div>

		<c:if test="${empty user.auditShopStatus || user.auditShopStatus == 3}">
			<form id="forms"><!-- 技师  -->
				<div>
					<h4>真实姓名：</h4>
					<input type="text" id="zsxm" placeholder="请输入真实姓名" value="${shopInfo.shopName}" />
				</div>
				<div>
					<h4>出生日期：</h4>
					<input readonly="readonly" id="csrq" placeholder="请选择出生日期" value="${shopInfo.userBirth}"/>
				</div>
				<div style="border-bottom: 1px solid #ebebeb;">
					<legend class="sex">选择性别：</legend>
					<label for="male">男性</label>
					<input type="radio" name="gender" id="male" value="1" <c:if test="${shopInfo.userSex == 1}"> checked </c:if>>
					<label for="female">女性</label>
					<input type="radio" name="gender" id="female" value="2" <c:if test="${shopInfo.userSex == 2}"> checked </c:if>>
				</div>
				<div>
					<h4>手机号码：</h4>
					<input type="number" id="sjhm" placeholder="请输入手机号码" value="${shopInfo.linkTel}"/>
				</div>
				<div>
					<h4>常驻地址：</h4>
					<input type="text" id="address" placeholder="请输入详细地址" value="${shopInfo.address}"/>
				</div>
				<div style="margin-top: 1em;">
					<label class="fl" style="margin-left: 1em;margin-top: 1em;" for="day">服务类别：</label>
					<select name="day" id="day" multiple="multiple" data-native-menu="false">
						<option>请选择服务类型</option>
						<c:forEach items="${typeInfo}" var="item">
							<option value="${item.typeNum}" <c:if test="${fn:contains(shopInfo.typeNum, item.typeNum)}"> selected = "selected" </c:if>>${item.typeInfoName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="tjsh">
					<button type="button" id="submit" >提交审核</button>
				</div>
				<input type="hidden" id="longitude" value="${shopInfo.longitude}">
				<input type="hidden" id="latitude" value="${shopInfo.latitude}">
				<input type="hidden" id="city" value="<ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.city}" type="3"></ry:show>">
				<input type="hidden" id="area" value="<ry:show parentCode="${shopInfo.city}" itemCode="${shopInfo.area}" type="4"></ry:show>">
			</form>
		</c:if>

		<c:if test="${user.auditShopStatus == 2}">
			<div class="wait">
				<img src="app/img/icon_shz.png" width="100%"  />
				<p class="size-9">您的资料正在审核中~</p>
				<p class="size-9">请耐心等待</p>
			</div>
		</c:if>

		<c:if test="${user.auditShopStatus == 1}">
			<div class="wc">
				<img src="app/img/icon_shcg.png" width="100%"  />
				<p class="size-9">恭喜您！审核成功！</p>
				<p class="size-9">赶快下载技师端尽情接单把~</p>
				<a data-role="button" data-ajax="false" onclick="downloadSoft()">立即下载</a>
			</div>
		</c:if>
	</div>
</div>
<div id="searchResultPanel" style="display: none;"></div>
<div style="display: none;" id="l-map"></div>
<input type="hidden" class="home_address" name="address">
<script type="text/javascript">
    $("#csrq").focus(function(){
        document.activeElement.blur();
    });
    //地址选择
    $("#submit").click(function(){
        var nickName = $('#zsxm').val().trim();
        var userBirth = $('#csrq').val();
        var userSex = $("input[type='radio']:checked").val();
        var userPhone = $('#sjhm').val().trim();
        var city = $("#city").val();
        var area = $("#area").val();
        var typeNum = $('#day').val();
        var longitude = $("#longitude").val();
        var latitude = $("#latitude").val();
        var shopNum = "${shopInfo.shopNum}";
        var shopInfoId = "${shopInfo.shopInfoId}";
        if(nickName == "" || userBirth == "" || userSex == '' || userPhone == "" || $('#address').val().trim() == "" || typeNum == null ){
            alert("数据填写不完整！");
            return false;
        }
        var address = $('#address').val().substring(city.length + area.length);
        //非空判断
		var data = {
            "userNum" : '${userNum}',
			"shopType" : '${shopType}',
			"nickName" : nickName,
			"userBirth" : userBirth,
			"userSex" : userSex,
			"userPhone" : userPhone,
			"address" : address,
			"typeNum" : typeNum.toString(),
			"longitude" : longitude,
			"latitude" : latitude,
			"city" : city,
			"area" : area,
			"shopNum" : shopNum,
            "shopInfoId" : shopInfoId
		}
        $("#forms").html('<div class="no-data-page"><p class="color-hs size-9"><img alt="" src="app/img/ajax-loader.gif"><br><br>数据加载中...</p></div>');
        setTimeout(function(){
		$.post("app/page/audits", data, function () {
			window.location.reload();
        });}, 100);
    });
    var calendar = new datePicker();
    calendar.init({
        'trigger': '#csrq', /*按钮选择器，用于触发弹出插件*/
        'type': 'date',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate':'1900-1-1',/*最小日期*/
        'maxDate':(new Date()).Format("yyyy-MM-dd"),/*最大日期*/
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

    $("#technician").find("#address").on("input propertychange",function(){  //当
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
        $("#technician").find("#address").click(function(){
            var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
                {"input" : "address"
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

    <c:if test="${empty user.auditShopStatus}">
    function goBack(num){
        var arrayString=num;
        var u = navigator.userAgent;
        window.history.go( -1 );
    }
    </c:if>
    <c:if test="${not empty user.auditShopStatus}">
    function goBack(num){
        var arrayString=num;
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if (isAndroid||isiOS) {
            if($.mobile.navigate.history.activeIndex == 0){
                if(isAndroid){
                    window.ruanyun.viewBack(arrayString);
                }else if(isiOS){
                    window.webkit.messageHandlers.viewBack.postMessage(arrayString);
                }
            }
        }
    }
    </c:if>
	$(function () {
		var auditShopLog = '${auditShopLog.reason}';
		if (auditShopLog != '') {
		    alert("审核失败："+auditShopLog);
        }
    })
	//下载
    function downloadSoft(){
        var arrayString=null;
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if (isAndroid||isiOS) {
            if($.mobile.navigate.history.activeIndex == 0){
                if(isAndroid){
                    window.ruanyun.downloadSoft('https://itunes.apple.com/cn/app/id1253300505?mt=8');
                }else if(isiOS){
                    window.webkit.messageHandlers.downloadSoft.postMessage('https://itunes.apple.com/cn/app/id1253300505?mt=8');
                }
            }
        }
    }
</script>
</body>
</html>