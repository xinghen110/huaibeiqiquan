<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>添加收货地址</title>
</head>
	<body>
		<div data-role = "page" id="add_delivery_address">
				<div data-role = "header" class="ui-header" style="background: white;">
					<a data-role="button" data-ajax="false" onclick="goBack(-1)"><img src="app/img/icon_nav_back.png"/></a>
					<h4><c:if test="${empty bean.userAddressNum}">添加</c:if><c:if test="${not empty bean.userAddressNum}">修改</c:if><c:if test="${user.userType == 3}">常用</c:if><c:if test="${user.userType == 2}">常用</c:if>地址</h4>
				</div>
				<div data-role="content">
					<form>
						<input type="hidden" name="userAddressNum" id="userAddressNum" value="${bean.userAddressNum}">
						<input type="hidden" id="areaid" value="${areaid}"/>
						<div data-role="fieldcontain">
							<h4>姓名</h4>
							<input type="text" placeholder="请输入收货人姓名" required="required" name="linkMan" id="linkMan" value="${bean.linkMan}"/>
						</div>
						<div data-role="fieldcontain">
							<h4>联系电话</h4>
							<input type="number" placeholder="请输入联系电话" required="required" name="linkTel" id="linkTel" value="${bean.linkTel}"/>
						</div>
						 <div data-role="fieldcontain">
							<h4>所在地区</h4>
							<input type="text" placeholder="请选择" class="choose" required="required" readonly="readonly" value="${szqy}"/>
						</div>
						<div data-role="fieldcontain">
							<h4>详细地址</h4>
							<input placeholder="请选择详细地址" type="text" required="required" name="address" id="address" value="${bean.address}" />
						</div>

						<div style="margin: 0 1em;">
							<button class="save" type="button" onclick="todo()">保存</button>
							<c:if test="${not empty bean.userAddressNum}">
								<button class="del" type="button" onclick="deleteAddress()">删除</button>
							</c:if>
						</div>
						<input type="hidden" id="longitude" value="${bean.longitude}">
						<input type="hidden" id="latitude" value="${bean.latitude}">
					</form>
				</div>
			</div>
			<div id="searchResultPanel" style="display: none;"></div>
			<div style="display: none;" id="l-map"></div>
			<input type="hidden" class="home_address" name="address">
			<script type="text/javascript">
				$(".choose").click(function(){
                    var userAddressNum = $('#userAddressNum').val();
                    var linkMan = $('#linkMan').val();
                    var linkTel = $('#linkTel').val();
                    var address = $('#address').val();
                    var longitude = $('#longitude').val();
					var latitude = $('#latitude').val();
					window.location.href='app/page/choose_province?linkMan='+linkMan+'&linkTel='+linkTel+'&address='+address+'&userNum=${userNum}&userAddressNum='+userAddressNum+'&longitude='+longitude+'&latitude='+latitude;
				});
                function todo(){
                    var userAddressNum = $('#userAddressNum').val();
                    var linkMan = $('#linkMan').val();
                    var linkTel = $('#linkTel').val();
                    var address = $('#address').val();
                    var areaid = $('#areaid').val();
                    var longitude = $('#longitude').val();
                    var latitude = $('#latitude').val();
                    if(linkMan.trim()!="" && linkTel.trim()!="" && address.trim()!="" && areaid.trim()!="") {
                        //window.location.href='app/page/saveAddress?linkMan='+linkMan+'&linkTel='+linkTel+'&address='+address+'&userAddressNum='+userAddressNum+'&userNum=${userNum}&areaid='+areaid+'&longitude='+longitude+'&latitude='+latitude;
                        $.ajax({
                            type : "POST",
                            url : 'app/page/saveAddress?linkMan='+linkMan+'&linkTel='+linkTel+'&address='+address+'&userAddressNum='+userAddressNum+'&userNum=${userNum}&areaid='+areaid+'&longitude='+longitude+'&latitude='+latitude,
                            datatype:"text",
                            success : function(data){
                                if(data == "true"){
                                    alert("设置地址成功！");
                                    viewBack(1);
                                }
                            },
                            error : function() {
                                alert("失败！");
                            }
                        });
                    } else {
                        alert("请补全收货地址信息！");
                    }
                }
                function deleteAddress() {
                    var userAddressNum = $('#userAddressNum').val();
                    if (userAddressNum.trim() != "") {
                        //window.location.href='app/page/deleteAddress?userAddressNum='+userAddressNum+'&userNum=${userNum}';
                        $.ajax({
                            type : "POST",
                            url : 'app/page/deleteAddress?userAddressNum='+userAddressNum+'&userNum=${userNum}',
                            datatype:"text",
                            success : function(data){
                                if(data == "true"){
                                    alert("删除地址成功！");
                                    viewBack(1);
                                }
                            },
                            error : function() {
                                alert("失败！");
                            }
                        });
					} else {
                        alert("出错！");
                    }
                }

                <c:if test="${empty bean.userAddressNum}">
					function goBack(num){
						var arrayString=num;
						var u = navigator.userAgent;
						window.history.go( -1 );
					}
				</c:if>
				<c:if test="${not empty bean.userAddressNum}">
					function goBack(num){
						var arrayString=num;
						var u = navigator.userAgent;
						var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
						var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
						if (isAndroid||isiOS) {
							if($.mobile.navigate.history.activeIndex == 0){
								if(isAndroid){
									window.ruanyun.viewBack(arrayString);
								}else if(isiOS){
									window.webkit.messageHandlers.viewBack.postMessage(arrayString);
								}
							}
						}
					}
				</c:if>

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

             	// 百度地图API功能
        		function G(id) {
        			return document.getElementById(id);
        		}
        		var map = new BMap.Map("l-map");
        		map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

        		$("#add_delivery_address").find("#address").on("input propertychange",function(){  //当
        	    	$("#add_delivery_address").find("#longitude").val("");
        	        $("#add_delivery_address").find("#latitude").val("");
        	        G("searchResultPanel").innerHTML="";
        	        $("#add_delivery_address").find(".home_address").val("");
        		});
        		$("#add_delivery_address").find("#businessAddress").on("input propertychange",function(){  //当
        	    	$("#add_delivery_address").find("#longitude").val("");
        	        $("#add_delivery_address").find("#latitude").val("");
        	        G("searchResultPanel").innerHTML="";
        	        $("#add_delivery_address").find(".home_address").val("");
        		});
        	  
        		function changeHouseNumber(){
        			var keyword =$("#add_delivery_address").find("#address").val();
        		 	$("#add_delivery_address").find(".home_address").val(keyword);
        		}
        		$(document).on("pageshow","#add_delivery_address",function(){

        			 $("#add_delivery_address").find("#address").click(function(){
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
        				         $("#add_delivery_address").find(".home_address").val(myValue);
        						setPlace();
        					});

        					function setPlace(){
        						map.clearOverlays();    //清除地图上所有覆盖物
        						function myFun(){
        							var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        							var lng = pp.lng;
        							var lat = pp.lat;
        							$("#add_delivery_address").find("#longitude").val(lng);
        					        $("#add_delivery_address").find("#latitude").val(lat);

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

        		$(document).on("pagebeforehide","#add_delivery_address",function(){
        			 $(".tangram-suggestion").hide();
        			 $(".gearDate").css("display","none");
        		});
			</script>
	</body>
</html>