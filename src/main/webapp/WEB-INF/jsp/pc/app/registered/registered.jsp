<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>成为技师</title>
</head>
<style>
	#registered_technician .ui-content div{overflow: hidden;}
	#registered_technician .ui-content div a{width: 50%;text-align: center;}
</style>
	<body>
	<style>
		#registered_technician{height: 100%}
		.ui-content{display: table;width: 100%;padding: 0px;height:84%;}
	</style>
		<div data-role="page" id="registered_technician">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="viewBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>成为技师</h4>
			</div>
			<div data-role = "content">
				<div class="div1" style="display: table-cell; vertical-align: middle!important;">
					<a href="app/page/registered2?userNum=${userNum}&shopType=2" data-ajax="false" class="fl js"><img src="app/img/icon_js.png" width="100px"/></a>
					<a href="app/page/registered2?userNum=${userNum}&shopType=1" data-ajax="false" class="fl sj"><img src="app/img/icon_sj.png" width="100px"/></a>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		/*$().ready(function(){
			var wH =
			alert(wH);
			var dH = $(".div1").height();
			var mH = (wH-dH-130)/2+"px";
			$(".div1").css('margin-top',mH);
		});*/
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
	</body>
</html>