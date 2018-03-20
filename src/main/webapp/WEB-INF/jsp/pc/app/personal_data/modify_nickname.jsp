<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>修改昵称</title>
</head>
	<body>
		<div data-role="page" id="modify-nickname">
			<div data-role="header" style="background: white;">
			 <a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
			 	<h4 class="text-shadow">修改昵称</h4>
			</div>
			<div data-role = "content">
				<div>
					<input type="text" name="nickName" id="nickName" placeholder="修改昵称">
					<a data-role="button" onclick="update()">确认修改</a>
				</div>
		    </div>
		</div>
	</body>
<script type="text/javascript">
    function update() {
        var nickName = $("#nickName").val();
        if (nickName.trim() != '') {
            window.location.href="app/page/updateUser?userNum=${userNum}&nickName=" + nickName;
        } else {
            alert("请输入昵称！");
        }
    }
</script>
</html>