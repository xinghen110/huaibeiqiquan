<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>技师详情</title>
</head>
	<body>
		<div data-role="page" id="technician_details">
			<div data-role = "content">
				<img  src="app/img/icon_return.png" onclick="goBack(1)" style="width: 35px; position: absolute; left: 1.5em; top: 1.5em; z-index: 9999;">
				<div class="bannder">
					<img <c:if test="${not empty jishi.userPhoto}">src="${constants.QINIU_USER_IMGURL}${jishi.userPhoto}"</c:if><c:if test="${empty jishi.userPhoto}">src="app/img/default_technician.png"</c:if> width="100%" height="180px" />
				</div>
				<div class="sjxm" style="text-align: center;">
						<img <c:if test="${not empty jishi.userPhoto}">src="${constants.QINIU_USER_IMGURL}${jishi.userPhoto}"</c:if><c:if test="${empty jishi.userPhoto}">src="app/img/default_technician.png"</c:if> style="width: 100px; height: 100px; border-radius: 50%; margin-top: -3em;box-shadow: 0px 5px 7PX #EBEBEB;" />
					<h4 class="name size-11">${jishi.shopInfo.shopName}</h4>
					<h4 class="size-6 bq">
						<c:forEach items="${fn:split(jishi.shopInfo.flag1, ',')}" var="type">
							<span>${type}</span>
						</c:forEach>
					</h4>
					<hr />
					<h4 class="size-7 xx">
						<span class="fl"><img src="app/img/icon_details_tick.png" class="fl" width="15px" height="15px" />接单：${jishi.shopInfo.jiedanCount}</span>
						<span class="fl"><img src="app/img/icon_details_technician.png" class="fl" width="15px" height="15px" />性别：<ry:show parentCode="USERSEX" itemCode="${jishi.userSex}"></ry:show> | ${nianling}岁</span>
						<span class="fl"><img src="app/img/icon_details_core.png" class="fl" width="15px" height="15px" />评分：${jishi.shopInfo.score	}</span>
					</h4>
				</div>
				<div class="sjxm address-wc">
					<h4 class="size-9 address">
						<a onclick="viewMap('${shopInfo.longitude}','${shopInfo.latitude}')"><ry:show parentCode="${jishi.shopInfo.province}" itemCode="${jishi.shopInfo.province}" type="2"></ry:show>
						<ry:show parentCode="${jishi.shopInfo.province}" itemCode="${jishi.shopInfo.city}" type="3"></ry:show>
						<ry:show parentCode="${jishi.shopInfo.city}" itemCode="${jishi.shopInfo.area}" type="4"></ry:show>
						${shopInfo.address}</a>
						<a href="tel:${jishi.shopInfo.linkTel}"><img class="tel" src="app/img/icon_hospital_call.png" /></a></h4>
				</div>
				<div class="sjxm synopsis-wc">
					<h4 class="size-9 synopsis">简介：${jishi.shopInfo.description}
					</h4>
					<c:if test="${fn:length(jishi.shopInfo.description)>40}">
					<h4 class="zk">展开<span style="display: none;">0</span></h4>
					</c:if>
					
				</div>
				<p class="size-9 title" style="margin-top: 1em;">视频介绍</p>
				<div class="video-synopsis">
					<ul data-role="none">
						<c:forEach items="${shopVedios}" var="item">
							<li class="fl">
								<a data-ajax="false" href="app/pageshow/video_details?videoNum=${item.videoNum}">
									<img class="video-img" src="${constants.QINIU_USER_IMGURL}${item.mainPhotoUrl}"/>
									<div style="width: 100px; height: 100px; background: rgba(0,0,0,.5);text-align: center; margin-top: -6.5em; position: relative;">
										<img style="margin-top: 35px; width: 30px; height: 30px;" src="app/img/icon_video.png" />
									</div>
								</a><br>
								<span class="size-7">${item.videoTitle}</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<p class="size-9 title">所有套餐</p>
				<ul data-role="listview" class="all-tc">
					<c:forEach items="${mealInfos}" var="item">
						<li data-icon="false" class="fl">
							<a href="#cc" class="xq" onclick="ckxq('${item.meal_info_num}')">
								<h4 class="type size-7"><span class="fl">${item.type_info_name}</span><span class="fr">已售：${item.sold}</span></h4>
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
				<p class="size-9 title">服务评价</p>
				<ul data-role="listview" class="service-evaluation">
					<c:forEach begin="0" end="3" items="${commentInfos}" var="item">
						<li data-icon="false">
							<img src="${constants.QINIU_USER_IMGURL}${item.user.userPhoto}" />
							<h4 style="margin-bottom: 0;">${item.user.nickName}<span class="fr size-8" style="color: #999"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></span></h4>
							<h4 class="xj size-10"><input type="hidden" value="${item.score}"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></h4>
							<p class="content">${item.content}
							</p>
						</li>
					</c:forEach>
					<c:if test="${commentInfoCount > 3}">
						<li data-icon="false" style="padding-left: 0!important;">
							<a style="background: white;box-shadow:none; border: 1px solid #ebebeb;" href="app/page/all_evaluation?shopNum=${shopInfo.shopNum}&userNum=${userNum}" data-ajax="false">
								<h4 class="ckpl" >查看全部评价(${commentInfoCount}条)</h4>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			$(".zk").click(function(){
				var spanVal = $(".zk span").html();
				if(spanVal==0){
					$(".zk").html("收起<span style='display: none;'>1</span>");
					$(".synopsis").css("display","block")
				}else{
					$(".zk").html("展开<span style='display: none;'>0</span>");
					$(".synopsis").css("display","-webkit-box")
				}
			});
			var i = 0;
			$(".video-synopsis ul .video-img").each(function(){
				i++;
			});
			var marginVal = (i/2)*10; 
			var W = 100*i+marginVal;
			$(".video-synopsis ul").width(W);

            $('#technician_details .xj input').each(function () {
                var score = $(this).val();
                $(this).parent().find("span:lt("+score+")").addClass('active');
            })
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

            /*地图 导航*/
            function viewMap(longitude,latitude){
                var arrayString= '{"longitude":"'+longitude+'","latitude":"'+latitude+'"}';
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if(isAndroid){
                    window.ruanyun.viewMap(arrayString);
                }else if(isiOS){
                    window.webkit.messageHandlers.viewMap.postMessage(arrayString);
                }
            }
            function tv(num){
                alert(num);
                var num=num;
                var objVideo=$("#diveoID").val();
                //2、调用视频播放API
                objVideo.play();

            }
            function goBack(num){
                var arrayString=num;
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if (isAndroid||isiOS) {
                    if($.mobile.navigate.history.activeIndex == 0){
                        if(isAndroid){
                            window.ruanyun.historyBack(arrayString);
                        }else if(isiOS){
                            window.webkit.messageHandlers.historyBack.postMessage(arrayString);
                        }
                    } else {
                        window.history.go(-1);
                    }
                }
            }
		</script>
	</body>
</html>