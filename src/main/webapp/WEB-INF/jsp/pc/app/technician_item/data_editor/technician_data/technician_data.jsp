<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>技师资料</title>
</head>
	<body>
		<div data-role="page" id="technician_data">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4><c:if test="${shopInfo.shopType == 2}">技师</c:if><c:if test="${shopInfo.shopType == 1}">商家</c:if>资料</h4>
			</div>
			<div data-role = "content">
				<c:if test="${shopInfo.shopType == 2}">
					<ul data-role="listview">
						<li data-icon="false">
							<h4><span class="fl sztx size-9">设置头像</span><span class="fr"><img <c:if test="${not empty shopInfo.mainPhoto}">src="${constants.QINIU_USER_IMGURL}${shopInfo.mainPhoto}"</c:if><c:if test="${empty shopInfo.mainPhoto}">src="app/img/default_technician.png"</c:if> class="headeait" width="40px" height="40px"><img class="right" src="app/img/icon_right.png"></span></h4>
							<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile3(this)"   />
							<form id="forms">
								<input type="hidden" style="display: none;" name="userPhoto" id="UserPhoto" value="" />
							</form>
						</li>
						<li data-icon="false">
							<h4 class="size-9 fl title">姓名</h4>
							<img class="right" src="app/img/icon_right.png">
							<input type="text"  placeholder="请输入姓名" name="shopName" value="${shopInfo.shopName}" />
						</li>
						<li data-icon="false">
							<h4 class="size-9 fl title">出生年月</h4>
							<img class="right" src="app/img/icon_right.png">
							<input id="userBirth" readonly="readonly" placeholder="请输入出生年月" name="userBirth" value="${shopInfo.userBirth}" />
						</li>
						<li data-icon="false" class="sex">
							<h4 class="fl size-9 title">技师性别</h4>
							<label for="female">女性</label>
							<input type="radio" name="gender" id="female" value="2" <c:if test="${shopInfo.userSex == 2}"> checked </c:if>>
							<label for="male">男性</label>
							<input type="radio" name="gender" id="male" value="1" <c:if test="${shopInfo.userSex == 1}"> checked </c:if>>
						</li>
						<li data-icon="false" style="margin-top: 1em;">
							<h4 class="size-9 fl title">联系方式</h4>
							<img class="right" src="app/img/icon_right.png">
							<input type="number"  placeholder="请输入联系方式" name="linkTel" value="${shopInfo.linkTel}" />
						</li>
						<li data-icon="false" class="choose-address">
							<h4 class="size-9 fl title">选择地址</h4>
							<img class="right" src="app/img/icon_right.png">
							<input placeholder="请选择地址" type="text" id="address" class="fr address" width="100%" value="<ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.city}" type="3"></ry:show><ry:show parentCode="${shopInfo.city}" itemCode="${shopInfo.area}" type="4"></ry:show>${shopInfo.address}" />
						</li>
						<li data-icon="false" class="fwlb">
							<a data-ajax="false" style="border-bottom: none;" onclick="serviceType(1)">
								<h4  class="size-9 title">服务类别<img class="right" src="app/img/icon_right.png" style="margin: 0;">
									<span class="fr all">
										<c:forEach var="item" items="${typeInfos}">
											<span>${item.typeInfoName}</span><span class="typeNum" style="display: none;">${item.typeNum}</span>
										</c:forEach>
									</span>
								</h4>
							</a>
						</li>
						<li data-icon="false">
							<h4 class="fl title">个人简介</h4>
							<textarea  placeholder="请输入个人简历" maxlength="200" name="description">${shopInfo.description}</textarea>
						</li>
						<li data-icon="false">
							<a data-ajax="false" onclick="updateVideo(1)">
								<h4 class="size-9 title color-black">视频介绍<span class="fr videoCount"><span class="videoCount2">${videocount}</span>个视频<img class="right right-video" src="app/img/icon_right.png"></span></h4>
							</a>
						</li>
					</ul>
				</c:if>
				<c:if test="${shopInfo.shopType == 1}">
					<ul data-role="listview">
						<li data-icon="false">
							<h4><span class="fl sztx size-9">设置店铺主图</span><span class="fr"><img <c:if test="${not empty shopInfo.mainPhoto}">src="${constants.QINIU_USER_IMGURL}${shopInfo.mainPhoto}"</c:if><c:if test="${empty shopInfo.mainPhoto}">src="app/img/default_technician.png"</c:if> class="headeait" width="40px" height="40px"><img class="right" src="app/img/icon_right.png"></span></h4>
							<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile3(this)"   />
							<form id="forms">
								<input type="hidden" style="display: none;" name="userPhoto" id="UserPhoto" value="" />
							</form>
						</li>
						<li data-icon="false">
							<h4 class="size-9 fl title">店铺名称</h4>
							<img class="right" src="app/img/icon_right.png">
							<input type="text"  placeholder="请输入店铺名称" name="shopName" value="${shopInfo.shopName}" />
						</li>
						<li data-icon="false" class="business_time">
							<h4 class="size-9 fl title">营业时间</h4>
							<img class="right" src="app/img/icon_right.png">
							<input type="text" style="text-align: left;" placeholder="结束" name="flag2" value="${shopInfo.flag2}" />
							<h4 class="fr" style="position: relative; margin-top: .8em; margin-left: .5em;margin-right: 0em;">~</h4>
							<input type="text"  placeholder="开始" name="flag1" value="${shopInfo.flag1}"/>
						</li>
						<li data-icon="false" class="fwlb">
							<a data-ajax="false" style="border-bottom: none;" onclick="serviceType(1)">
								<h4  class="size-9 title">服务类别<img class="right" src="app/img/icon_right.png" style="margin: 0;">
									<span class="fr all">
										<c:forEach var="item" items="${typeInfos}">
											<span>${item.typeInfoName}</span><span class="typeNum" style="display: none;">${item.typeNum}</span>
										</c:forEach>
									</span>
								</h4>
							</a>
						</li>
						<li data-icon="false" style="margin-top: 1em;">
							<h4 class="size-9 fl title">联系方式</h4>
							<img class="right" src="app/img/icon_right.png">
							<input type="number"  placeholder="请输入联系方式" name="linkTel" value="${shopInfo.linkTel}"  />
						</li>
						<li data-icon="false" class="choose-address">
							<h4 class="size-9 fl title">店铺地址</h4>
							<img class="right" src="app/img/icon_right.png">
							<input placeholder="请选择地址" type="text" id="address" class="fr address" width="100%" value="<ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.city}" type="3"></ry:show><ry:show parentCode="${shopInfo.city}" itemCode="${shopInfo.area}" type="4"></ry:show>${shopInfo.address}" />
						</li>
						<li data-icon="false">
							<h4 class="fl title">店铺简介</h4>
							<textarea placeholder="请输入店铺简介" maxlength="200" name="description">${shopInfo.description}</textarea>
						</li>
						<li data-icon="false">
							<a style="border-bottom: none!important;" data-ajax="false" onclick="serviceType(2)">
								<h4 class="size-9 title color-black">店铺图片<span class="fr">${count}张图片<img class="right right-video" src="app/img/icon_right.png"></span></h4>
							</a>
						</li>
						<li data-icon="false">
							<a data-ajax="false" onclick="updateVideo(1)">
								<h4 class="size-9 title color-black">视频介绍<span class="fr"><span class="videoCount2">${videocount}</span>个视频<img class="right right-video" src="app/img/icon_right.png"></span></h4>
							</a>
						</li>
					</ul>
				</c:if>
				
				<a data-role="button" class="save" onclick="savedata()">保存</a>
			</div>
			<input type="hidden" id="longitude" value="${shopInfo.longitude}">
		    <input type="hidden" id="latitude" value="${shopInfo.latitude}">
		    <input type="hidden" id="province" value="<ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.province}" type="2"></ry:show>">
		    <input type="hidden" id="city" value="<ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.city}" type="3"></ry:show>">
		    <input type="hidden" id="area" value="<ry:show parentCode="${shopInfo.city}" itemCode="${shopInfo.area}" type="4"></ry:show>">
		    <div id="searchResultPanel" style="display: none;"></div>
		    <div style="display: none;" id="l-map"></div>
		    <input type="hidden" class="home_address" name="address">
		    <div id="result"></div>
		</div>
		<script type="text/javascript">
            <c:if test="${shopInfo.shopType == 2}">
				var calendar = new datePicker();
				calendar.init({
					'trigger': '#userBirth', /*按钮选择器，用于触发弹出插件*/
					'type': 'date',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
					'minDate':'1900-1-1',/*最小日期*/
					'maxDate':(new Date()).Format("yyyy-MM-dd"),/*最大日期*/
					'onSubmit':function(){/*确认时触发事件*/
						var theSelectData=calendar.value;
					},
					'onClose':function(){/*取消时触发事件*/
					}
				});
			</c:if>
		    
		  //换头像begin
			$("#technician_data").find(".headeait").click(function(){
				filebtn();				
			});
		
			function filebtn(){
				$("#technician_data").find(".filebtn").click();
			}
			function readFile3(obj){
				var file = obj.files[0]; 
				var reader = new FileReader();
				reader.readAsDataURL(file);
					reader.onload = function(e){
						var imgBase64Data =encodeURIComponent(e.target.result);
						$("#technician_data").find(".headeait")[0].src=this.result;
						var res = (this.result);
						var pos = imgBase64Data.indexOf("4")+4;
						imgBase64Data = imgBase64Data.substring(pos);
						$('#UserPhoto').val(imgBase64Data);
						changePicture();
					}
			}
		  	//换头像end
		  	
		  	// 百度地图API功能
			function G(id) {
				return document.getElementById(id);
			}
			var map = new BMap.Map("l-map");
			map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。
			map.enableScrollWheelZoom(true);
			var index = 0;
			var myGeo = new BMap.Geocoder();
			var adds = [];
			$("#technician_data").find("#address").on("input propertychange",function(){  //当
				$("#technician_data").find("#longitude").val("");
				$("#technician_data").find("#latitude").val("");
				G("searchResultPanel").innerHTML="";
				$("#technician_data").find(".home_address").val("");
			});
			function changeHouseNumber(){
				var keyword =$("#technician_data").find("#address").val();
				$("#technician_data").find(".home_address").val(keyword);
			}
			$(document).on("pageshow","#technician_data",function(){

				$("#technician_data").find("#address").click(function(){
					var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
						{"input" : "address"
							,"location" : map
						});
					$(".tangram-suggestion").show();
					var myValue;
					ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
						var _value = e.item.value;
						myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
						G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
						$("#technician_data").find(".home_address").val(myValue);
						setPlace();
					});

					function setPlace(){
						map.clearOverlays();    //清除地图上所有覆盖物
						function myFun(){
							var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
							var lng = pp.lng;
							var lat = pp.lat;
							$("#technician_data").find("#longitude").val(lng);
							$("#technician_data").find("#latitude").val(lat);
							adds = [new BMap.Point($("#technician_data").find("#longitude").val(), $("#technician_data").find("#latitude").val())];
							address();
							map.centerAndZoom(pp, 18);
							map.addOverlay(new BMap.Marker(pp));    //添加标注
						}
						var local = new BMap.LocalSearch(map, { //智能搜索
							onSearchComplete: myFun
						});
						local.search(myValue);
					}
				});
			});

			function address() {
				var pt = adds[index];
				geocodeSearch(pt);
			}

			function geocodeSearch(pt){
				if(index < adds.length-1){
					setTimeout(window.bdGEO,400);
				}
				myGeo.getLocation(pt, function(rs){
					var addComp = rs.addressComponents;
					$("#province").val(addComp.province);
					$("#city").val(addComp.city);
					$("#area").val(addComp.district);
				});
			}
			
			function serviceType(num) {
			    var userNum = '${userNum}';
			    var shopName = $("input[name=shopName]").val().trim();
                var userBirth = $("input[name=userBirth]").val();
                var userSex = $('input[name=gender]:checked').val();
                var linkTel = $("input[name=linkTel]").val().trim();
                var longitude = $("#longitude").val().trim();
                var latitude = $("#latitude").val().trim();
                var province = $("#province").val().trim();
                var city = $("#city").val().trim();
                var area = $("#area").val().trim();
				var flag1 = $("input[name=flag1]").val();
				var flag2 = $("input[name=flag2]").val();
				var address = $("#address").val().substring(city.length + area.length);
				var description = $("textarea[name=description]").val().trim();
				var typeNums = "";
				$("ul li a h4 span .typeNum").each(function () {
				    var typeNum = "," + $(this).html();
                    typeNums = typeNums + typeNum;
                })
				typeNums = typeNums.substring(1);
			    var data = {
					"shopName" : shopName,
					"userBirth" : userBirth,
					"userSex" : userSex,
					"linkTel" : linkTel,
					"longitude" : longitude,
					"latitude" : latitude,
					"province" : province,
					"city" : city,
					"area" : area,
					"flag1" : flag1,
					"flag2" : flag2,
					"address" : address,
					"description" : description,
					"typeNums" : typeNums
				}
				var array = JSON.stringify(data);
				if (num == 1) {
                    window.location.href='app/pageshow/service_type?array=' + array + '&userNum=' + userNum;
                } else if (num == 2) {
				    window.location.href='app/pageshow/shop_img?array=' + array + '&userNum=' + userNum + '&shopNum=${shopInfo.shopNum}';
                }
            }
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
            function updateVideo(num){
                var arrayString=num;
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if (isAndroid||isiOS) {
                    if($.mobile.navigate.history.activeIndex == 0){
                        if(isAndroid){
                            window.ruanyun.updateVideo(arrayString);
                        }else if(isiOS){
                            window.webkit.messageHandlers.updateVideo.postMessage(arrayString);
                        }
                    }
                }
            }
            function updateData(num){
                var arrayString=num;
                var u = navigator.userAgent;
                var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                if(isAndroid){
                    window.ruanyun.updateData(arrayString);
                }else if(isiOS){
                    window.webkit.messageHandlers.updateData.postMessage(arrayString);
                }
            }
            function savedata() {
                var userNum = '${userNum}';
                var shopName = $("input[name=shopName]").val().trim();
                var userBirth = $("input[name=userBirth]").val();
                var userSex = $('input[name=gender]:checked').val();
                var linkTel = $("input[name=linkTel]").val().trim();
                var longitude = $("#longitude").val().trim();
                var latitude = $("#latitude").val().trim();
                var province = $("#province").val().trim();
                var city = $("#city").val().trim();
                var area = $("#area").val().trim();
                var flag1 = $("input[name=flag1]").val();
                var flag2 = $("input[name=flag2]").val();
                var address = $("#address").val().substring(city.length + area.length);
                var description = $("textarea[name=description]").val().trim();
                var typeNums = "";
                $("ul li a h4 span .typeNum").each(function () {
                    var typeNum = "," + $(this).html();
                    typeNums = typeNums + typeNum;
                })
                typeNums = typeNums.substring(1);
                var shopType = '${shopInfo.shopType}';
                if (shopType == 1) {
					if (shopName==''||linkTel==''||area==''||flag1==''||flag2==''||description==''||typeNums=='') {
					    alert("请补全信息！");
					    return false;
                    }
                } else if (shopType == 2) {
					if (shopName==''||userBirth==''||userSex==''||linkTel==''||area==''||description==''||typeNums=='') {
                        alert("请补全信息！");
                        return false;
                    }
                } else {
                    return false;
                }
                var data = {
                    "shopName" : shopName,
                    "userBirth" : userBirth,
                    "userSex" : userSex,
                    "linkTel" : linkTel,
                    "longitude" : longitude,
                    "latitude" : latitude,
                    "province" : province,
                    "city" : city,
                    "area" : area,
                    "flag1" : flag1,
                    "flag2" : flag2,
                    "address" : address,
                    "description" : description,
                    "typeNums" : typeNums
                }
                $(".ui-content").html('<div class="no-data-page"><p class="color-hs size-9"><img alt="" src="app/img/ajax-loader.gif"><br><br>数据加载中...</p></div>');
                setTimeout(function(){
					$.post("app/pageshow/save_data?userNum=${userNum}&array="+JSON.stringify(data), null, function (data) {
						if (data == "true") {
							alert("修改信息成功！");
							updateData(1);
							goBack(1);
						} else {
							alert("修改信息失败");
						}
					});}, 100);
            }
            
            function updateVideoCount(num) {
				$(".videoCount2").html(num);
            }

            //更换头像
            function changePicture() {
                var formData = $("#forms").serialize();
                formData =decodeURIComponent(formData,true);
                var result = new MyJqueryAjax('app/pageshow/change_picture?shopNum=${shopInfo.shopNum}',formData,null,'text').request();
                if(result == "true"){
                    alert("修改头像成功！");
                } else {
                    alert("修改头像失败！");
                }
            }
            $("#userBirth").focus(function(){
                document.activeElement.blur();
            });
		</script>
	</body>
</html>