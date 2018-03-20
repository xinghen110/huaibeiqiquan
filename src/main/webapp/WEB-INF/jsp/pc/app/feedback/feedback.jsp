<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>意见反馈</title>
</head>
	<body>
		<div data-role="page" id="feedback">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="viewBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>意见反馈</h4>
			</div>
			<div data-role = "content">
				<textarea placeholder="填写您的意见和建议~" name="content"></textarea>
				<div>
					<h4 class="lxfs">联系方式</h4>
					<input type="number" value="${user.userPhone}" placeholder="请输入你的联系方式" name="linkTel" />
				</div>
				<h5>客服电话 : <a href="tel:055176416350">0551-76416350</a></h5>
				<h5>工作地址 : <span>09:00</span>~<span>12:00</span></h5>
				<a data-role="button" class="submit" onclick="feedBack()">提交</a>
			</div>
		</div>
	<script type="text/javascript">
        function viewBack(num){
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
        function feedBack() {
			var userNum = '${userNum}';
			var content = $('textarea[name=content]').val().trim();
			var linkTel = $('input[name=linkTel]').val().trim();
			if (content == '' || linkTel == '') {
			    alert("请补全信息！");
			    return false;
            }
            if (linkTel.length != 11) {
			    alert("请填写正确的手机号！")
				return false;
            }
			var data = {
			    'userNum' : userNum,
				'content' : content,
				'linkTel' : linkTel
            }
            $.post("app/feebBack/add", data, function (data) {
				viewBack("反馈成功！");
            })
        }
	</script>
	</body>
</html>