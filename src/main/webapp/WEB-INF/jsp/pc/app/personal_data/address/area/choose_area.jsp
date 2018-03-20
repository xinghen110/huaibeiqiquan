<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<style>
	#choose—area ul a{background: white;border: none;}
	#choose—area ul a h4{color:black;}
</style>
<div data-role="page" id="choose—area">
	<div data-role="header" style="background: white;">
		<a data-role="button" href="app/page/add_delivery_address"><img src="app/img/icon_nav_back.png"/></a>
		<h4 class="text-shadow">选择区</h4>
	</div>
	<div data-role = "content">
		<ul data-role="listview">
			<c:forEach  items="${listAreas}" var="item">
				<li data-icon="false">
					<a data-ajax="false" href="app/page/add_delivery_address?areaid=${item.areaid}&userAddressNum=${userAddressNum}&linkMan=${linkMan}&linkTel=${linkTel}&address=${address}&userNum=${userNum}&longitude=${longitude}&latitude=${latitude}">
						<h4 class="font-weight">${item.area}<img class="right" src="app/img/icon_right.png"></h4>
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
