<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
	<title>套餐详情</title>
</head>
<body>
<div data-role="page" id="package_details">
	<div data-role = "content">
		<img  src="app/img/icon_return.png" onclick="goBack(1)" style="width: 35px; position: absolute; left: 1.5em; top: 1.5em; z-index: 9999;">
		<div class="bannder">
			<img src="${constants.QINIU_USER_IMGURL}${mealInfo.mainPhoto}" width="100%" height="180px" />
		</div>
		<div class="sjxm">
			<h4 class="name size-10">${mealInfo.title}</h4>
			<h4 class="bq"><span style="color: red;" class="size-10 fl">￥${mealInfo.mealPrice}</span><span style="color: #ccc;margin-top: .2em" class="size-9 fr">${mealInfo.mealLog}分钟</span></h4>
			<hr />
			<h4 class="size-7 xx">
				<span class="fl"><img src="app/img/icon_details_pro.png" class="fl" width="15px" height="15px" />${mealInfo.flag1}</span>
				<span class="fl"><img src="app/img/icon_details_technician.png" class="fl" width="15px" height="15px" />${shopInfo.shopName}</span>
				<span class="fl"><img src="app/img/icon_details_tick.png" class="fl" width="15px" height="15px" />定金预约</span>
			</h4>
		</div>
		<p class="size-9 title" style="margin-bottom: -.5em; margin-top: 1em;">服务流程</p>
		<div class="sjxm address-wc">
			<h4 class="size-9 address" style="word-break:break-all;">
				${mealInfo.fwlc}
			</h4>
		</div>
		<p class="size-9 title">服务评价</p>
		<ul data-role="listview" class="service-evaluation">
			<c:forEach var="item" items="${commentInfoList}"  begin="0" end="3">
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
					<a style="background: white;box-shadow:none;border-color: #ebebeb;" href="app/page/all_evaluation?mealNum=${mealInfo.mealInfoNum}&userNum=${userNum}" data-ajax="false">
						<h4 class="ckpl" >查看全部评价(${commentInfoCount}条)</h4>
					</a>
				</li>
			</c:if>
		</ul>
		<h4 class="choose-title"><a class="xz">功效特色</a><a class="jjzt">禁忌状态</a><a>预约特色</a></h4>
		<div class="video-synopsis" style="margin-bottom: 4em;">
			<ul data-role="none" class="hdt">
				<li style="width: 200px" class="fl"><h4 style="word-break:break-all;">${mealInfo.gntx}</h4></li>
				<li style="width: 200px" class="fl"><h4 style="word-break:break-all;">${mealInfo.jjzz}</h4></li>
				<li style="width: 200px" class="fl"><h4 style="word-break:break-all;">${mealInfo.yyxz}</h4></li>
			</ul>
		</div>
		
	</div>
	<a data-role="button" data-ajax="false" class="ljyy">立即预约</a>
</div>
<script type="text/javascript">
    //给ul宽度赋值
    var W = 200*3+80;
    $(".video-synopsis ul").width(W);
    //显示星级
    var index=$(this).index()
    $('#package_details .xj span:lt(3)').addClass('active')
    //字体颜色切换
    $(".choose-title a").click(function(){
        $(".choose-title a").removeClass("xz");
        $(this).addClass("xz");
        $t = $(this).index();
        if($t==0){
        	$(".video-synopsis").scrollLeft(0)
        }
        if($t==1){
        	$(".video-synopsis").scrollLeft(160)
        }
        if($t==2){
        	$(".video-synopsis").scrollLeft(352);
        }
    });
    $('#package_details .xj input').each(function () {
        var score = $(this).val();
        $(this).parent().find("span:lt("+score+")").addClass('active');
    })
    $(".ljyy").click(function () {
		if ('${userNum}' != '' && ${not empty userNum}) {
            window.location.href = "app/page/meal_bespeak?userNum=${userNum}&mealInfoNum=${mealInfo.mealInfoNum}";
        } else {
            var arrayString='${mealInfo.mealInfoNum}';
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
        }
    })
    function goYuyue(mealInfoNum, userNum) {
        window.location.href="app/page/meal_bespeak?mealInfoNum="+mealInfoNum+"&userNum="+userNum;
    }
    function goBack(num){
        var arrayString=num;
        var u = navigator.userAgent;
        window.history.go( -1 );
    }
   	$(".video-synopsis").scroll(function(){
   		var leftVal =  $(".video-synopsis").scrollLeft()
   		if(leftVal<100){
		 	$(".choose-title a").removeClass("xz");
	        $(".choose-title a").eq(0).addClass("xz");
	}
   		if(leftVal>=100&&leftVal<=160){
			 	$(".choose-title a").removeClass("xz");
		        $(".choose-title a").eq(1).addClass("xz");
		}
   		if(leftVal>=280){
			 	$(".choose-title a").removeClass("xz");
		        $(".choose-title a").eq(2).addClass("xz");
		}
   	});
   	
   	$().ready(function(){
		var addressH =  $(".address").height();
		$(".tel").css("margin-top",(addressH-18)/2);
	});
    function goBack(num){
        var arrayString=num;
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if (isAndroid||isiOS) {
            if(isAndroid){
                window.ruanyun.historyBack(arrayString);
            }else if(isiOS){
                window.webkit.messageHandlers.historyBack.postMessage(arrayString);
            }
        }
    }
</script>
</body>
</html>