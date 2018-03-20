<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>新增套餐</title>
</head>
	<body>
	<style>
		.ui-page-theme-a .ui-radio-on:after, html .ui-bar-a .ui-radio-on:after, html .ui-body-a .ui-radio-on:after, html body .ui-group-theme-a .ui-radio-on:after, .ui-btn.ui-radio-on.ui-btn-a:after{border-color: #31B16C /*{a-active-background-color}*/;}
	</style>
		<div data-role="page" id="add_packages">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back" onclick="goBack(1)"><img src="app/img/icon_nav_back.png"/></a>
				<h4><c:if test="${empty mealInfo}">新增</c:if><c:if test="${not empty mealInfo}">修改</c:if>套餐</h4>
				<button class="size-10" type="button" onclick="savedata()">保存</button>
			</div>
			<form id="forms">
			<div data-role = "content">
				<input type="hidden" value="${shopNum}" name="shopNum">
				<div class="wc">
					<h4 class="fl ">套餐名称</h4>
					<input type="text" name="title" placeholder="请输入套餐名称" value="${mealInfo.title}" />
				</div>
				<div class="wc tc_type">
					<h4 class="fl ">套餐类型</h4>
					<select name="day" id="day" data-native-menu="false">
						<option>请选择套餐类型</option>
						<c:forEach var="item" items="${typeInfo}">
							<c:if test="${item.typeNum != TI43490000000012}"><option value="${item.typeNum}" <c:if test="${item.typeNum == mealInfo.typeNum}"> selected = "selected" </c:if>>${item.typeInfoName}</option></c:if>
						</c:forEach>
					</select>
					<img src="app/img/icon_right.png">
				</div>
				<img class="tctp" src=""/>
				<div class="wc headeait">
					<h4 style="margin-top: 1.58em;" class="fl">套餐图片</h4>
					<input type="text" class="choose-img" readonly="readonly" disabled="disabled" placeholder="请选择套餐图片"  />
					<img src="app/img/icon_right.png" style="margin-top: -2.8em;"  >
				</div>

				<div class="wc pirce-wc">
					<h4 class="fl ">套餐价格</h4>
					<input type="number" name="mealPrice" placeholder="请输入套餐价格" value="${mealInfo.mealPrice}" />
					<h5 class="dw">元</h5>
				</div>


				<style>
					.fwfs div .ui-radio{float: right;width: 20%;}
					.fwfs div .ui-radio label{border: none!important;}
					.fwfs div .ui-radio:nth-child(2){clear: inherit!important;}
				</style>
				<div class="wc fwfs">
					<h4 class="fl">预约方式</h4>

					<h4 class="fl" style="margin: 0; position: absolute; margin-top: 3.8em;left: 1em;">是否允许技师上门</h4>
					<div style="clear: left;overflow: hidden;background: white;border-bottom: 1px solid #ebebeb;">
						<label for="N">否</label>
						<input type="radio" id="N" name="isMake" value="2">
						<label for="Y">是</label>
						<input type="radio" id="Y" name="isMake" value="1">
					</div>

					<label for="aa">可暂只支付定金预约，余款当面支付</label>
						<input type="radio" name="greader" id="aa" value="1" onclick="makeMethod(1)">
					<label for="bb">需全款支付预约</label>
						<input type="radio" name="greader" id="bb" value="2" onclick="makeMethod(2)">
				</div>

				<div class="wc dj">
					<h4 class="fl ">定金价格</h4>
					<input type="number" name="makePrice" placeholder="请输入定金价格" value="${mealInfo.makePrice}" />
					<h5 class="dw">元</h5>
				</div>

				<div class="wc pirce-wc">
					<h4 class="fl ">服务时间</h4>
					<input type="number" name="mealLog" placeholder="请输入服务时间" value="${mealInfo.mealLog}"/>
					<h5 class="dw">分钟</h5>
				</div>
				<div class="wc textarea-wc" style="margin-bottom: 1em;">
					<h4 class="fl">服务流程</h4>
					<textarea placeholder="请输入服务流程" name="fwlc" maxlength="200">${mealInfo.fwlc}</textarea>
					<h3>限200字</h3>
				</div>
				<div class="wc textarea-wc">
					<h4 class="fl">功效特色</h4>
					<textarea placeholder="请输入功效特色" name="gntx" maxlength="200">${mealInfo.gntx}</textarea>
					<h3>限200字</h3>
				</div>
				<div class="wc textarea-wc" style="margin-bottom: 1em;">
					<h4 class="fl">禁忌症状</h4>
					<textarea placeholder="请输入禁忌症状" name="jjzz" maxlength="200">${mealInfo.jjzz}</textarea>
					<h3>限200字</h3>
				</div>
				<div class="wc textarea-wc" style="margin-bottom: 1em;">
					<h4 class="fl">预约特色</h4>
					<textarea placeholder="请输入预约特色" name="yyxz" maxlength="200">${mealInfo.yyxz}</textarea>
					<h3>限200字</h3>
				</div>
			</div>
				<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile3(this)" />
				<input type="hidden" style="display: none;" name="userPhoto" id="UserPhoto" value="" />
			</form>
			<script type="text/javascript">
                makeMethod(${mealInfo.makeMethod});
                function makeMethod(num) {
                    if (num == 2) {
                        $("#bb").attr("checked", "checked");
                        $(".dj").hide(500);
                    } else {
                        $("#aa").attr("checked", "checked");
                        $(".dj").show(500);
                    }
                }

                isMake(${mealInfo.isMake});
                function isMake(num) {
                    if (num == 2) {
                        $("#N").attr("checked", "checked");
                    } else {
                        $("#Y").attr("checked", "checked");
                    }
                }

                //新增套餐图片
                $("#add_packages").find(".headeait").click(function(){
                    filebtn();
                });
                function filebtn(){
                    $("#add_packages").find(".filebtn").click();
                }
                function readFile3(obj){
                    var file = obj.files[0];
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function(e){
                        var imgBase64Data =encodeURIComponent(e.target.result);
                        $("#add_packages").find(".tctp").show();
                        $(".choose-img").attr('placeholder','');
                        $("#add_packages").find(".tctp")[0].src=this.result;
                        var res = (this.result);
                        var pos = imgBase64Data.indexOf("4")+4;
                        imgBase64Data = imgBase64Data.substring(pos);
                        $('#UserPhoto').val(imgBase64Data);
                    }
                }
                //进入页面设置已有图片
                $(function () {
                    var img = '${mealInfo.mainPhoto}';
                    if (img.trim() != '') {
                        $("#add_packages").find(".tctp").attr('src', '${constants.QINIU_USER_IMGURL}${mealInfo.mainPhoto}');
                        $(".choose-img").attr('placeholder','');
                        $("#add_packages").find(".tctp").show();
                    }
                })
                //保存
                function savedata(){
                    var title = $("input[name=title]").val().trim();
                    var typeNum = $("#day").val();
                    var userPhoto = $("#UserPhoto").val();
                    var mealPrice = $("input[name=mealPrice]").val().trim();
                    var isMake = $("input[name=isMake]:checked").val().trim();
                    var makeMethod = $('input[name=greader]:checked').val();
                    var makePrice = $("input[name=makePrice]").val().trim();
                    var mealLog = $("input[name=mealLog]").val().trim();
                    var fwlc = $("textarea[name=fwlc]").val().trim();
                    var gntx = $("textarea[name=gntx]").val().trim();
                    var jjzz = $("textarea[name=jjzz]").val().trim();
                    var yyxz = $("textarea[name=yyxz]").val().trim();
                    var img = '${mealInfo.mainPhoto}';
                    if (img == '' && userPhoto == '') {
                        alert("请补全套餐信息！");
                        return false;
                    }
                    if (title == "" || typeNum == "" || mealPrice == "" || mealLog == "" || fwlc == "" || gntx == "" || jjzz == "" || yyxz == '' || makeMethod == '') {
                        alert("请补全套餐信息！");
                        return false;
                    }
                    if (makeMethod == 1 && makePrice == '') {
                        alert("请补全套餐信息！");
                        return false;
                    }
                    var formData = $("#forms").serialize();
                    formData =decodeURIComponent(formData,true);
                    $("#forms").html('<div class="no-data-page"><p class="color-hs size-9"><img alt="" src="app/img/ajax-loader.gif"><br><br>数据加载中...</p></div>');
                    setTimeout(function(){
						var result = new MyJqueryAjax('app/page/save_packages?mealInfoNum=${mealInfo.mealInfoNum}',formData,null,'text').request();
						if(result == "true"){
							alert("设置套餐成功！");
                            addPackages(1);
                            goBack(1);
						} else {
							alert("设置套餐失败！");
						}}, 100);
                }
                function addPackages(num){
                    var arrayString=num;
                    var u = navigator.userAgent;
                    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                    if (isAndroid||isiOS) {
                        if($.mobile.navigate.history.activeIndex == 0){
                            if(isAndroid){
                                window.ruanyun.addPackages(arrayString);
                            }else if(isiOS){
                                window.webkit.messageHandlers.addPackages.postMessage(arrayString);
                            }
                        }
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

			</script>
		</div>
	</body>
</html>