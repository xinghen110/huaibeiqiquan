<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
    <title>广告位</title>
</head>
<body>
<style>
	.img{margin-bottom: -3px;}
</style>
<div data-role="page" id="ad_position">
    <div data-role="header" style="background: white;color: black;border: none;">
        <a data-role="button" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
        <h4 class="text-shadow">${title}会员</h4>
    </div>
    <div data-role = "content" style="padding: 0">
        <c:forEach items="${adverInfos}" var="item">
            <c:if test="${empty item.videoPath}">
                <img class="img" src="${constants.QINIU_USER_IMGURL}${item.mainPhoto}" width="100%">
            </c:if>
            <c:if test="${not empty item.videoPath}">
                <video style="width: 99.5%;" src="${constants.QINIU_USER_IMGURL}${item.videoPath}" preload="auto" poster="${constants.QINIU_USER_IMGURL}${item.mainPhoto}" class="img"></video>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
<script type="text/javascript">
	var windowH = $(window).height();
	var content = windowH -44.55;
	var imgH = content/3;
	$(".img").height(imgH);
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
</script>
</html>