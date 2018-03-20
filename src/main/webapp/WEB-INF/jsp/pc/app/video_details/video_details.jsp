<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>视频详情</title>
</head>
	<body>
	<style>
		#video_details .ui-content{padding: 0;}
		#video_details .ui-content video{width: 99.5%; }
		.title{background: white; padding: .8em; margin: 0; line-height: 1.8em;}
	</style>
		<div data-role="page" id="video_details">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>视频介绍</h4>
			</div>
			<div data-role = "content">
				<video src="${constants.QINIU_USER_IMGURL}${shopVedio.videoUrl}" poster="${constants.QINIU_USER_IMGURL}${shopVedio.mainPhotoUrl}" controls="controls" preload="none"></video>
				<h4 class="title">${shopVedio.videoTitle}</h4>
			</div>
		</div>

		<script>
			var windowH = $(window).width();
			$("video").height(windowH*0.562);
			function goBack(num) {
				window.history.go(-1);
            }
		</script>
	</body>
</html>