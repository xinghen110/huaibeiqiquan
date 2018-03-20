<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>技师详情</title>
</head>
	<body>
		<div data-role="page" id="shop_technician_details">
			<div data-role = "content">
				<img  src="app/img/icon_return.png" onclick="goBack(1)" style="width: 35px; position: absolute; left: 1.5em; top: 1.5em; z-index: 9999;">
				<div class="bannder">
					<img src="${constants.QINIU_USER_IMGURL}${jishiInfo.mainPhoto}" width="100%" height="180px"  />
				</div>
				<div class="sjxm" style="text-align: center;">
					<img src="${constants.QINIU_USER_IMGURL}${jishiInfo.mainPhoto}" style="width: 100px; height: 100px; border-radius: 50%; margin-top: -3em;box-shadow: 0px 5px 7PX #EBEBEB;" />
					<h4 class="name size-11">${jishiInfo.userName}<span><ry:show parentCode="USERSEX" itemCode="${jishiInfo.userSex}"></ry:show> | ${nianling}岁</span></h4>
					<h4 class="size-6 bq">
						<c:forEach items="${fn:split(jishiInfo.flag1, ',')}" var="type">
							<span>${type}</span>
						</c:forEach>
					</h4>
				</div>
				<p class="details">
					${jishiInfo.description}
				</p>
			</div>
		</div>

		<script>
            function goBack(num){
                var arrayString=num;
                var u = navigator.userAgent;
                window.history.go( -1 );
            }
		</script>
	</body>
</html>