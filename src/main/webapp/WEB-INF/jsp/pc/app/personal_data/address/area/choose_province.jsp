<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<style>
	#choose—province ul a{background: white;border: none;}
	#choose—province ul a h4{color:black;}
</style>
<div data-role="page" id="choose—province">
	<div data-role="header" style="background: white;">
		<a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
		<h4 class="text-shadow">选择省</h4>
	</div>
	<div data-role = "content">
		<ul data-role="listview">
			<c:forEach  items="${listProvince}" var="item">
				<li data-icon="false">
					<a data-ajax="false" href="app/page/choose_city?provinceId=${item.provinceCode}&userAddressNum=${userAddressNum}&linkMan=${linkMan}&linkTel=${linkTel}&address=${address}&userNum=${userNum}&longitude=${longitude}&latitude=${latitude}">
						<h4 class="font-weight">${item.provinceName}<img class="right" src="app/img/icon_right.png"></h4>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
        function goBack(num){
            var arrayString=num;
            var u = navigator.userAgent;
            window.history.go( -1 );
        }
	</script>
</div>
