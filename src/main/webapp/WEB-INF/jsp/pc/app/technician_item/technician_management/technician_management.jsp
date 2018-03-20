<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>商家详情</title>
</head>
	<body>
		<div data-role="page" id="technician_management">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>技师管理</h4>
			</div>
			<div data-role = "content">
				<ul data-role="listview" class="shop-technician">
					<c:forEach items="${jishiInfos}" var="item">
						<li data-icon="false">
							<div class="tx" >
								<img src="${constants.QINIU_USER_IMGURL}${item.mainPhoto}" />
							</div>
							<a class="a" data-ajax="false" href="app/page/add_technician?shopNum=${shopNum}&jishiInfoNum=${item.jishiInfoNum}&userNum=${userNum}">
								<h4 class="name">${item.userName}</h4>
								<h4 class="size-7 bq">
									<c:forEach items="${fn:split(item.flag1, ',')}" var="type">
										<span class="fl">${type}</span>
									</c:forEach>
								</h4>
							</a>
							<div class="del">
								<a><span style="display: none" class="jishiInfoNum">${item.jishiInfoNum}</span><img style="width: 20px; height: 20px;" src="app/img/icon_delete.png" /></a>
							</div>
						</li>
					</c:forEach>
				</ul>
				<a class="add-technician" data-role="button" href="app/page/add_technician?shopNum=${shopNum}&userNum=${userNum}" data-ajax="false">新增技师</a>
			</div>
		</div>
		<script type="text/javascript">
			$(".choose h4").click(function(){
				$(".choose h4").removeClass("active");
				$(this).addClass("active");
				$(".choose h4").find("hr").hide();
				$(this).find("hr").show();
			});
			$("ul li .del img").click(function(){
			    var parentsli = $(this).parents("li");
			    var jishiInfoNum = parentsli.find(".jishiInfoNum").html();
                var data = {
                    "jishiInfoNum" : jishiInfoNum,
					"shopNum" : '${shopNum}'
                }
                if (confirm("确定要删除该技师吗？")) {
                    $.post("app/page/delete_technician", data, function (data) {
                        if (data >= 1) {
                            updateTechnician();
                            parentsli.remove();
                            alert("删除技师成功！");
                        } else {
                            alert("删除技师失败！");
                        }
                    });
                }
			});
            function goBack(num){
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
            function updateTechnician() {
                var arrayString='';
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if(isAndroid){
                    window.ruanyun.updateTechnician(arrayString);
                }else if(isiOS){
                    window.webkit.messageHandlers.updateTechnician.postMessage(arrayString);
                }
            }
		</script>
	</body>
</html>