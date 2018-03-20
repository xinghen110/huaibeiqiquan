<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>视频管理</title>
</head>
	<body>
		<div data-role="page" id="video_list">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
				<h4>技师资料</h4>
			</div>
			<div data-role = "content">
				<ul data-role="listview">
					<li data-icon="false">
						<img class="video" src="app/img/gd_goods.jpg" />
						<h4>汉方中药泡脚服务体验</h4>
						<img class="del" src="app/img/icon_delete.png">
					</li>
					<li data-icon="false">
						<img class="video" src="app/img/gd_goods.jpg" />
						<h4>汉方中药泡脚服务体验</h4>
						<img class="del" src="app/img/icon_delete.png">
					</li>
				</ul>
				
				 <a data-role="button" data-ajax="false" href="app/pageshow/add_video" class="add_video">新增视频</a> 
			</div>
		</div>
		<script type="text/javascript">
			$(".del").click(function(){
				$(this).parent().remove();
			});
		</script>
	</body>
</html>