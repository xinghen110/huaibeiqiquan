<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>全部商家</title>
</head>
<body>
<style>
	.all-sj{margin: 0 1em!important; margin-top: 3.5em!important;}
	.all-sj li{margin-bottom: 1em!important;}
	.all-sj a{border-radius: 5px!important; padding:.51em 1em!important;padding-left: 8em!important; border: none!important;box-shadow: 0px 5px 7PX #EBEBEB;}
	.all-sj a img{max-height: none!important; max-width: none!important; width: 100px; height: 65px;border-radius: 5px; margin-top: .8em; margin-left: 0.8em;margin-bottom: .8em;}
	.name{font-weight: normal!important;}
	.name span:last-child{font-size: .8em;font-weight: normal; float: right;color: #999;}
	.all-sj  .bq{font-weight: normal!important;font-size: .8em; color: #F39E63; border: 1px solid; padding: .1em .3em;float: left;margin-right: .3em;border-radius: 5px;margin-bottom: .5em;}
	.bq_wc{margin-bottom: 0!important; margin-top: 1.3em!important;}
</style>
<div data-role="page" id="all_technician">
    <div data-role="header" style="background: white; position: fixed; width: 100%;z-index: 99999;">
        <a style="margin-top: .1em;" data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
        <img src="app/img/icon_ss.png" class="ss_img" />
        <input type="text" placeholder="搜索商家名称" name="search"  value="${shopName}" />
        <a class="ui-btn ss" data-ajax="false" onclick="sousuo()">搜索</a>
    </div>
    <div data-role = "content">
        <div class="header">
            <h4 class="fl types">类型筛选<img src="app/img/icon_x.png" class="x" /><img style="display: none" src="app/img/icon_s.png" class="y" /><span style="display: none" class="start">0</span></h4>
            <h4 class="fl choose_px"><span class="px_name">排序</span><img src="app/img/icon_x.png" class="x" /><img style="display: none" src="app/img/icon_s.png" class="y" /><span class="start" style="display: none">0</span></h4>
        </div>
        <div class="all_type" style="display: none;">
            <div class="type"><!-- 类型筛选 -->
                <c:forEach items="${typeInfo}" var="item">
                    <a class="ui-btn">${item.typeInfoName}<span class="typeNum" style="display: none;">${item.typeNum}</span><span style="display: none" class="start">0</span></a>
                </c:forEach>
            </div>
            <div class="cz">
                <button class="button_cz" type="button">重置</button>
                <button class="button_qr" type="button">确认</button>
            </div>
        </div>
        <ul data-role="listview" class="px" style="display: none"><!-- 排序 -->
            <li data-icon="false">
                <h4>销量最高<img src="app/img/icon_g.png" style="display: none;" class="fr" width="20px"></h4>
				<span class="pxtype" style="display: none">0</span>
				<span class="type" style="display: none">1</span>
            </li>
            <li data-icon="false">
                <h4>评分最高<img src="app/img/icon_g.png" style="display: none;" class="fr" width="20px"></h4>
				<span class="pxtype" style="display: none">0</span>
				<span class="type" style="display: none">2</span>
            </li>
        </ul>
        <div class="query-c"></div><!-- 灰色背景 -->

        <ul data-role="listview" class="all-sj">
			<c:forEach items="${shopInfos}" var="item">
				<li data-icon="false">
					<a data-ajax="false" onclick="ckxq('${item.shopNum}')" style="position: relative;">
						<img <c:if test="${not empty item.mainPhoto}">src="${constants.QINIU_USER_IMGURL}${item.mainPhoto}"</c:if><c:if test="${empty item.mainPhoto}">src="app/img/default_merchant.png"</c:if> />
                        <c:if test="${item.isHezuo == 2}">
                            <img src="app/img/bg_sign_fhzsh.png"  style="position:absolute;top: 40px;left: -3px;width: 75px;height: 20px;border-radius: 0"/>
                        </c:if>
                        <h4 class="name"><span>${item.shopName}</span><span class="fr"><c:if test="${item.distance > 1000}">${item.distance/1000}km</c:if><c:if test="${item.distance < 1000}">${item.distance}m</c:if></span></h4>
						<h4 class="bq_wc">
							<c:forEach items="${fn:split(item.flag1, ',')}" var="type">
								<span class="bq">${type}</span>
							</c:forEach>
						</h4>
					</a>
				</li>
			</c:forEach>
        </ul>
        <div class="more" style="text-align: center; margin-top: -10px; height: 30px;">
            <a id="PicID" style="display: none;line-height: 30px;font-weight: normal;color: black;font-size: .95em;" >没有更多数据了</a>
            <a id="PicID2" onclick="loadMore()" style="line-height: 30px;font-weight: normal;color: black;font-size: .95em;">加载更多</a>
        </div>

    </div>
</div>
<script type="text/javascript">
    $(".px li").click(function(){
        $(".px li h4 img").hide();
        $(this).find("h4 img").show();
        $(".px li .pxtype").html("0");
        $(this).find(".pxtype").html("1");
        var h4Val =  $(this).find("h4").text();
        $(".choose_px .px_name").text(h4Val);
        $(".px").hide();
        $(".header .choose_px").find(".start").html("0");
        $(".header .choose_px").find(".y").hide();
        $(".header .choose_px").find(".x").show();
        $(".header .choose_px").removeClass("choose");
        $(".query-c").hide();
        var typeNums = getTypeNums();
        var shopName = getTitle();
        var type = getType();
        window.location.href="app/pageshow/all_merchant?userNum=${userNum}&typeNums="+typeNums+"&shopName="+shopName+"&type="+type+"&longitude=${longitude}&latitude=${latitude}";
    });
    $(".header .choose_px").click(function(){
        $(".query-c").show();
        $(".all_type").hide();
        $(".header .types").removeClass("choose");
        $(".header .types").find(".start").html("0");
        $(".header .types").find(".y").hide();
        $(".header .types").find(".x").show();

        var spanVal = $(this).find(".start").html();
        if(spanVal==0){
            $(this).find(".x").hide();
            $(this).find(".y").show();
            $(this).addClass("choose");
            $(this).find(".start").html("1");
            $(".px").show();

        }else{
            $(this).find(".y").hide();
            $(this).find(".x").show();
            $(this).removeClass("choose");
            $(this).find(".start").html("0");
            $(".px").hide();
            $(".query-c").hide();
        }
    });

    $(".header .types").click(function(){
        $(".query-c").show();
        $(".px").hide();
        $(".choose_px").removeClass("choose");
        $(".choose_px").find(".start").html("0");
        $(".choose_px").find(".y").hide();
        $(".choose_px").find(".x").show();

        var spanVal = $(this).find(".start").html();
        if(spanVal==0){
            $(this).find(".x").hide();
            $(this).find(".y").show();
            $(this).addClass("choose");
            $(this).find(".start").html("1");
            $(".all_type").show();

        }else{
            $(this).find(".y").hide();
            $(this).find(".x").show();
            $(this).removeClass("choose");
            $(this).find(".start").html("0");
            $(".all_type").hide();
            $(".query-c").hide();
        }
    });

    $(".type .ui-btn").click(function(){
        var spanVal = $(this).find(".start").html();
        if(spanVal==0){
            $(this).addClass("choose_type");
            $(this).find(".start").html("1");
        }else{
            $(this).removeClass("choose_type");
            $(this).find(".start").html("0");
        }
    });

    $(".button_cz").click(function(){
        $(".type .ui-btn").removeClass("choose_type");
        $(".type .ui-btn").find(".start").html("0");
    })
    $(".button_qr").click(function(){
        $(".header .types").find(".y").hide();
        $(".header .types").find(".x").show();
        $(".header .types").removeClass("choose");
        $(".header .types").find(".start").html("0");
        $(".all_type").hide();
        $(".query-c").hide();
        var typeNums = getTypeNums();
        var shopName = getTitle();
        var type = getType();
        window.location.href="app/pageshow/all_merchant?userNum=${userNum}&typeNums="+typeNums+"&shopName="+shopName+"&type="+type+"&longitude=${longitude}&latitude=${latitude}";
    })

    $(".query-c").click(function(){
        $(".query-c").hide();
        $(".px").hide();
        $(".all_type").hide();

        $(".choose_px").find(".y").hide();
        $(".choose_px").find(".x").show();
        $(".choose_px").removeClass("choose");
        $(".choose_px").find(".start").html("0");

        $(".header .types").removeClass("choose");
        $(".header .types").find(".start").html("0");
        $(".header .types").find(".y").hide();
        $(".header .types").find(".x").show();
    });
    //获取typeNums
    function getTypeNums() {
        var typeNums = "";
        $(".all_type .type .ui-btn").each(function () {
            var spanVal = $(this).find(".start").html();
            if (spanVal == 1) {
                var typeNum = $(this).find(".typeNum").html();
                typeNums = typeNums + typeNum + ",";
            }
        });
        typeNums = typeNums.substring(0, typeNums.length-1);
        return typeNums;
    }
    //获取搜索框的值
    function getTitle() {
        var title = $("input[name=search]").val();
        return title;
    }
    //获取排序类型
    function getType() {
        var type = "";
        $(".px li").each(function () {
            var pxtype = $(this).find(".pxtype").html();
            if (pxtype == 1) {
                type = $(this).find(".type").html();
            }
        })
        return type;
    }
    $(function () {
        var type = '${type}';
        if (type != '') {
            $(".px li").each(function () {
                var pxtype = $(this).find(".type").html();
                if (pxtype == type) {
                    $(".px li h4 img").hide();
                    $(".px li .pxtype").html("0");
                    $(this).find(".pxtype").html("1");
                    $(this).find("h4 img").show();
                    var h4Val =  $(this).find("h4").text();
                    $(".choose_px .px_name").text(h4Val);
                    $(".px").hide();
                    $(".header .choose_px").find(".start").html("0");
                    $(".header .choose_px").find(".y").hide();
                    $(".header .choose_px").find(".x").show();
                    $(".header .choose_px").removeClass("choose");
                    $(".query-c").hide();
                }
            });
        }
        var typeNums = '${typeNums}';
        if (typeNums != '') {
            var typeNum = typeNums.split(",");
            for (var i = 0; i < typeNum.length; i++){
                var a = typeNum[i];
                $(".type .ui-btn").each(function () {
                    var b = $(this).find(".typeNum").html();
                    if (a == b) {
                        $(this).addClass("choose_type");
                        $(this).find(".start").html("1");
                    }
                });
            }
        }
        var number = $(".shop-technician").find("li").length;
        if ((number % 10) != 0 || number < 10) {
            $("#PicID").show();
            $("#PicID2").hide();
        }
    })
	//搜索
    function sousuo() {
        var shopName = getTitle();
        window.location.href="app/pageshow/all_merchant?userNum=${userNum}&shopName="+shopName+"&longitude=${longitude}&latitude=${latitude}";
    }
    function goBack(num){
        var arrayString=num;
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if(isAndroid){
            window.ruanyun.viewBack(arrayString);
        }else if(isiOS){
            window.webkit.messageHandlers.viewBack.postMessage(arrayString);
        }
    }
    function ckxq(shopNum) {
        window.location.href="app/page/merchant_details?userNum=${userNum}&shopNum="+shopNum;
    }
    //分页
    var startNum = 10;
    function loadMore() {
        var typeNums = getTypeNums();
        var type = getType();
        var shopName = getTitle();
        var url = "app/pageshow/ajaxAll_merchant?&typeNums="+typeNums+"&type="+type+"&startNum="+startNum+"&shopName="+shopName+"&memberLevel=${memberLevel}&longitude=${longitude}&latitude=${latitude}";
        var result = new MyJqueryAjax(url,null,null,'json').request();
        if (result.length < 10) {
            $("#PicID").show();
            $("#PicID2").hide();
        }
        for(var i=0;i<result.length;i++){
            var item = result[i];
            if (item.mainPhoto == '') {
                main_photo = '<img src="app/img/default_merchant.png" />';
            } else {
                main_photo = '<img src="${constants.QINIU_USER_IMGURL}' + item.mainPhoto + '" />'
            }
            if (Number(item.distance) > 1000) {
                var distance = Number(item.distance)/1000 + "km";
            } else {
                var distance = Number(item.distance) + "m";
            }
            var types = (item.flag1).split(",");
            var typess = '';
            for (var j = 0; j < types.length; j++) {
                if (types[j] != '')
                    typess = typess + '<span class="bq">' + types[j] + '</span>';
            }
            var isHezuo = '';
            if (Number(item.isHezuo) == 2) {
                isHezuo = '<img src="app/img/bg_sign_fhzsh.png"  style="position:absolute;top: 40px;left: -3px;width: 75px;height: 20px;border-radius: 0"/>'
            }
            var html =
                '<li data-icon="false">' +
                '<a data-ajax="false" onclick="ckxq(\''+item.shopNum+'\')">' + main_photo + isHezuo +
                '<h4 class="name"><span>' + item.shopName + '</span><span class="fr">' + distance + '</span></h4>' +
                '<h4 class="bq_wc">' + typess +
                '</h4>' +
                '</a>' +
                '</li>';
            $(".all-sj").append(html);
            $(".all-sj").listview("refresh");
        }
        startNum = Number(startNum) + 10;
    }
</script>
</body>
</html>