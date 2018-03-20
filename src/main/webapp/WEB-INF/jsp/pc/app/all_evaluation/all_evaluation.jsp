<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>全部评论</title>
</head>
	<body>
		<div data-role="page" id="all_evaluation">
			<div data-role="header" style="background: white;">
				<a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
				<h4>全部评论</h4>
			</div>
			<div data-role = "content">
				<ul data-role="listview" class="service-evaluation">
					<c:forEach items="${commentInfos}" var="item">
						<li data-icon="false">
							<img src="${constants.QINIU_USER_IMGURL}${item.user.userPhoto}" />
							<h4 style="margin-bottom: -.3em;">${item.user.nickName}<span class="fr size-8" style="color: #999"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></span></h4>
							<h4 class="xj size-10"><input type="hidden" value="${item.score}"><span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span></h4>
							<p class="content">
									${item.content}
							</p>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			//显示星级
            $('#all_evaluation .xj input').each(function () {
                var score = $(this).val();
                $(this).parent().find("span:lt("+score+")").addClass('active');
            });
            function goBack(num){
                var arrayString=num;
                var u = navigator.userAgent;
                window.history.go( -1 );
            }
		</script>
	</body>
</html>