<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>全部套餐</title>
</head>
	<body>
		<div data-role="page" id="all_technician">
			<div data-role="header" style="background: white; position: fixed; width: 100%;z-index: 99999;">
				<a style="margin-top: .1em;" data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<img src="app/img/icon_ss.png" class="ss_img" />
				<input type="text" placeholder="搜索套餐名称" name="search" value="${title}" />
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
						<h4>价格从低到高<img src="app/img/icon_g.png" style="display: none;" class="fr" width="20px"></h4>
						<span class="pxtype" style="display: none">0</span>
						<span class="type" style="display: none">2</span>
					</li>
					<li data-icon="false">
						<h4>价格从高到低<img src="app/img/icon_g.png" style="display: none;" class="fr" width="20px"></h4>
						<span class="pxtype" style="display: none">0</span>
						<span class="type" style="display: none">3</span>
					</li>
				</ul>
				<div class="query-c"></div><!-- 灰色背景 -->
				
				<ul data-role="listview" class="all-tc">
					<c:forEach items="${mealInfos}" var="item">
				
						<li data-icon="false" class="fl">
							<a href="javascript:void(0)" class="xq"  data-ajax="false" onclick="ckxq('${item.meal_info_num}')">
								<h4 class="type_title size-7"><span class="fl">	${item.type_info_name }</span><span class="fr">已售：${item.sold}</span></h4>
								<img src="${constants.QINIU_USER_IMGURL}${item.main_photo}" /><br />
								<h4 class="size-8">${item.title}</h4>
								<h4 class="price"><span style="color: red;" class="size-9 fl">￥${item.meal_price}</span><span style="color: #ccc;margin-top: .2em" class="size-7 fr">${item.meal_log}分钟</span></h4>
							</a>
							<div>
								<a data-role="button" class="size-8" onclick="ljyy('${item.meal_info_num}')">预约</a>
							</div>
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
                var typeNums = getTypeNums();
                var title = getTitle();
                var type = getType();
                window.location.href="app/pageshow/all_package?userNum=${userNum}&typeNums="+typeNums+"&title="+title+"&type="+type;
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
				var title = getTitle();
				var type = getType();
				window.location.href="app/pageshow/all_package?userNum=${userNum}&typeNums="+typeNums+"&title="+title+"&type="+type;
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
            function ljyy(mealInfoNum) {
                if ('${userNum}' == '' || ${empty userNum}) {
                    var arrayString=mealInfoNum;
                    var u = navigator.userAgent;
                    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                    if (isAndroid||isiOS) {
                        if(isAndroid){
                            window.ruanyun.bespeak(arrayString);
                        }else if(isiOS){
                            window.webkit.messageHandlers.bespeak.postMessage(arrayString);
                        }
                    }
                } else {
                    window.location.href="app/page/meal_bespeak?mealInfoNum="+mealInfoNum+"&userNum=${userNum}";
                }
            }
            function goYuyue(mealInfoNum, userNum) {
                window.location.href="app/page/meal_bespeak?mealInfoNum="+mealInfoNum+"&userNum="+userNum;
            }
            function ckxq(mealInfoNum) {
                window.location.href="app/page/mealinfo_details?mealInfoNum="+mealInfoNum+"&userNum=${userNum}";
            }
            function sousuo() {
                var title = getTitle();
                window.location.href="app/pageshow/all_package?userNum=${userNum}&title="+title;
            }
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
            //带回默认值
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
                var number = $(".all-tc").find("li").length;
                if ((number % 10) != 0 || number < 10) {
                    $("#PicID").show();
                    $("#PicID2").hide();
                }
            })
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

            //分页
            var startNum = 10;
            function loadMore() {
                var typeNums = getTypeNums();
                var type = getType();
                var title = getTitle();
                var url = "app/pageshow/ajaxAll_package?&typeNums="+typeNums+"&type="+type+"&startNum="+startNum+"&title="+title+"&memberLevel=${memberLevel}";
                var result = new MyJqueryAjax(url,null,null,'json').request();
                if (result.length < 10) {
                    $("#PicID").show();
                    $("#PicID2").hide();
                }
                for(var i=0;i<result.length;i++){
                    var item = result[i];
					var main_photo='${constants.QINIU_USER_IMGURL}'+item.main_photo;
                    var html =
                        '<li data-icon="false" class="fl">' +
                        '<a href="javascript:void(0)" class="xq"  data-ajax="false" onclick="ckxq(\''+item.meal_info_num+'\')">' +
						'<h4 class="type_title size-7"><span class="fl">' + item.type_info_name + '</span><span class="fr">已售：'　+ item.sold + '</span></h4>' +
						'<img src="' + main_photo + '" /><br />' +
						'<h4 class="size-8">' + item.title + '</h4>' +
						'<h4 class="price"><span style="color: red;" class="size-9 fl">￥' + item.meal_price + '.00</span><span style="color: #ccc;margin-top: .2em" class="size-7 fr">' + item.meal_log + '分钟</span></h4>' +
						'</a>' +
						'<div>' +
						'<a data-role="button" class="size-8 ui-btn" onclick="ljyy(\'' + item.meal_info_num + '\')">预约</a>' +
						'</div>' +
						'</li>';
                    $(".all-tc").append(html);
                    $(".all-tc").listview("refresh");
                }
                startNum = Number(startNum) + 10;
            }
		</script>
	</body>
</html>