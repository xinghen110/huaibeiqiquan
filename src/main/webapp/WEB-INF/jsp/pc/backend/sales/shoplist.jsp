<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader" style="display: none;">
			<div class="tabsHeaderContent">
				<ul>
				
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
<div layoutH="46" style="float: left; display: block; overflow: auto; width: 240px; border: solid 1px #CCC; line-height: 21px; background: #fff">
<div style="float: left; display: block; margin: 10px; width: 200px; height: 100%; line-height: 21px; background: #FFF;">
<ul class="tree treeFolder ">
	<c:forEach var="firstIitem" items="${shoplist}" varStatus="firstStatus">
		<li><a href="sales/salesStatistics?shopNum=${firstIitem.shopNum }" target="ajax" rel="jbsxBox" title="${firstIitem.shopNum }" height="500" >${firstIitem.shopName }</a></li>
	</c:forEach>
</ul>
</div>
</div>
<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
				
				</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
