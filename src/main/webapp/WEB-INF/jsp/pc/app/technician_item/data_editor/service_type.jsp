<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>服务类别</title>
</head>
	<body>
		<div data-role="page" id="service_type">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4>服务类别</h4>
				<button type="button" onclick="save()" style="float: right;width: 20%; box-shadow: none; border: none; font-weight: normal; background: white;color: #31B16C; font-size: 1em; margin-top: -.2em;">确定</button>
			</div>
			<div data-role = "content">
				<ul data-role="listview">
					<c:forEach items="${typeInfos}" var="item">
						<c:if test="${item.typeNum != 'TI43490000000012'}">
							<li data-icon="false">
								<span style="display: none" class="typeNum">${item.typeNum}</span>
								<h4>${item.typeInfoName}<span class="type" style="display: none">0</span>
									<img class="wxz fr" src="app/img/icon_wxz.png">
									<img class="yxz fr" src="app/img/icon_yxz.png" style="display: none">
								</h4>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			$("#service_type ul li").click(function(){
				var spanVal = $(this).find(".type").html();
				if(spanVal==0){
					$(this).find(".wxz").hide();	
					$(this).find(".yxz").show();	
					$(this).find(".type").html(spanVal+1);
				}else{
					$(this).find(".wxz").show();	
					$(this).find(".yxz").hide();	
					$(this).find(".type").html(spanVal-1);
				}
			});
			//设置默认值
			$(function () {
				var typeNums = '${typeNums}';
				var typeNum = typeNums.split(",");
				for (var i = 0; i< typeNum.length; i++) {
                    $("#service_type ul li").each(function () {
						var val = $(this).find(".typeNum").html();
						if (val == typeNum[i]) {
                            $(this).click();
						}
                    });
                }
            })
			//确定
			function save() {
				var array = '${array}';
				var typeNums = "";
                $("#service_type ul li").each(function () {
                    var spanVal = $(this).find(".type").html();
                    if (spanVal == 1) {
                        typeNums = typeNums + "," + $(this).find(".typeNum").html();
					}
                });
                typeNums = typeNums.substring(1);
                var array = setJson(array, 'typeNums', typeNums);
                window.location.href='app/pageshow/technician_data?userNum=${userNum}&array='+array;
            }
            //添加或修改json数据
            function setJson(jsonStr, name, value) {
                if(!jsonStr)jsonStr="{}";
                var jsonObj = JSON.parse(jsonStr);
                jsonObj[name] = value;
                return JSON.stringify(jsonObj);
            }
            function goBack(num){
                window.history.go(-1);
            }
		</script>
	</body>
</html>