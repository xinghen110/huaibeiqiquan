<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>常用地址</title>
</head>
	<body>
		<div data-role = "page" id="no_address">
				<div data-role = "header" class="ui-header" style="background: white;">
					<a data-role="button" data-rel="back" onclick="viewBack(1)"><img src="app/img/icon_nav_back.png"/></a>
	            		<h4>常用地址</h4>  
				</div>
				<div data-role="content" style="padding: 0">
					<div class="parent-div" style="text-align: center;">
						<img src="app/img/icon_no_address.png" width="120px" />
						<a data-ajax="false" href="app/page/add_delivery_address?userNum=${userNum}" style="box-shadow:none;margin: 0 5em;background: #31B16C;color:white;text-shadow: none;font-weight: normal;border: none; margin-top: 2em;" data-role="button">添加地址</a>
					</div>
				</div>
				<script type="text/javascript">
				$().ready(function(){
					var wH=document.documentElement.clientHeight||document.body.clientHeight;
					var dH = $(".parent-div").height();
					var mH = (wH-dH-100)/2+"px";
					$(".parent-div").css('margin-top',mH);
				});
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
			</script>
			</div>
	</body>
</html>