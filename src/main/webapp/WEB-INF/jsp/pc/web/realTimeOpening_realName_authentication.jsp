<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="BANK_NAME" bingdingName="BANK_NAME"></ry:binding>
<%
	String imgPath = Constants.DEFAULT_IMG;
	session.setAttribute("imgPath", imgPath);
%>
<!DOCTYPE html>
<html>
	<%@include file="header.jsp" %>
	<body>
		<%@include file="header_title.jsp" %>
		<div class="ydl"><!-- 已登录 -->
			<%@include file="header_personal.jsp" %>
			<div class="index1" style="height: 1100px;">
				<div class="index1-div" style="height: 1000px;"><!--实名认证-->
					<form action="web/stock/authentication" method="post" id="forms">
					<input type="hidden" name="userId" value="${userInfo.userId}">
					<div class="smrz">
						<h4 class="text-align color-white title">实名认证及银行卡绑定</h4>
						<h4 class="text-align color-white size-8 ts">实名信息提交后不可修改，请务必填写真实资料，并保证与银行卡预留个人信息一致</h4>
						<div class="index1-div-div" id="add_photo">
							<div>
								<h4 class="fl color-white">姓名：</h4>
								<%--<c:if test="${userInfo.userName == null}">--%>
									<%--<input class="fl" type="text" placeholder="请输入您的真实姓名" name="userName" value="${userInfo.userName}" >--%>
								<%--</c:if>--%>
								<%--<c:if test="${userInfo.userName != null}">--%>
									<%--<input class="fl" type="text" readonly="readonly" placeholder="请输入您的真实姓名" name="userName" value="${userInfo.userName}" >--%>
								<%--</c:if>--%>
								<input class="fl" type="text" <c:if test="${userInfo.userName != null}">readonly="readonly"</c:if> placeholder="请输入您的真实姓名" name="userName" value="${userInfo.userName}" maxlength="6">
							</div>
							<div>
								<h4 class="fl color-white">身份证号：</h4>
								<c:if test="${userInfo.idNumber == null}">
									<input class="fl" type="text" placeholder="请输入您的身份证号" maxlength="18" name="idNumber" value="${userInfo.idNumber}" >
								</c:if>
								<c:if test="${userInfo.idNumber != null}">
									<input class="fl" type="text" readonly="readonly" placeholder="请输入您的身份证号" name="idNumber" value="${userInfo.idNumber}" >
								</c:if>

							</div>
							<c:if test="${userInfo.idNumber == null}">
								<div id="id_card_front">
									<h4 class="fl color-white">身份证正面照片：</h4>
									<span class="fr" style="margin-right: 22px">
										<img src="${constants.QINIU_USER_IMGURL}${userInfo.idCardFrontView}" class="headeait" width="240px" height="140px">
										<%--<img class="right" src="app/img/icon_right.png">--%>
									</span>
									<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#id_card_front','idCardFrontView')" placeholder="请选择身份证正面照片" />
									<input type="text" style="display: none;" name="idCardFrontView" id="idCardFrontView" value="${userInfo.idCardFrontView}" />
									<%--<input onclick="clickFile(this)" class="fl" readonly="readonly" type="text" placeholder="请选择身份证正面照片" name="" />--%>
									<%--<input type="file" onchange="qrFile(this)" style="display: none;" />--%>
								</div>
								<div id="id_card">
									<h4 class="fl color-white">身份证反面照片：</h4>
									<span class="fr" style="margin-right: 22px">
										<img src="${constants.QINIU_USER_IMGURL}${userInfo.idCardBackView}" class="headeait" width="240px" height="140px">
									</span>
									<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#id_card','idCardBackView')" placeholder="请选择身份证正面照片"  />
									<input type="hidden" style="display: none;" name="idCardBackView" id="idCardBackView" value="${userInfo.idCardBackView}" />
								</div>
							</c:if>
							<c:if test="${userInfo.idNumber != null}">
								<div>
									<h4 class="fl color-white">是否实名：</h4>
									<input class="fl" type="text" readonly="readonly" name="realName" value="已实名，不能再实名">
									<%--<input class="fl" type="text"--%>
										   <%--readonly="readonly" name="realName" value="已实名，不能再实名"/>--%>
								</div>
								<input type="hidden" name="idCardFrontView"  value="${userInfo.idCardFrontView}" />
								<input type="hidden" name="idCardBackView" value="${userInfo.idCardBackView}" />
							</c:if>
                            <div>
                                <h4 class="fl color-white">所属银行：</h4>
                                <select name="bankId">
									<option value="">请选择银行</option>
									<c:forEach var="item" items="${BANK_NAME}">
										<option value="${item.itemName}" <c:if test="${item.itemName == userInfo.bankId}">selected</c:if>>${item.itemName}</option>
									</c:forEach>
									<%--<option value="中国银行" <c:if test="${userInfo.bankId == '中国银行'}">selected</c:if>>中国银行</option>--%>
                                    <%--<option value="工商银行" <c:if test="${userInfo.bankId == '工商银行'}">selected</c:if>>工商银行</option>--%>
                                    <%--<option value="交通银行" <c:if test="${userInfo.bankId == '交通银行'}">selected</c:if>>交通银行</option>--%>
                                    <%--<option value="农业银行" <c:if test="${userInfo.bankId == '农业银行'}">selected</c:if>>农业银行</option>--%>
                                    <%--<option value="光大银行" <c:if test="${userInfo.bankId== '光大银行' }">selected</c:if>>光大银行</option>--%>
                                </select>
                            </div>
                            <div>
								<h4 class="fl color-white">银行卡号：</h4>
								<input class="fl" type="text" placeholder="请输入您的银行卡号" maxlength="19" name="bankCardNumber" value="${userInfo.bankCardNumber}" >
							</div>
							<div id="back_card">
								<h4 class="fl color-white">银行卡照片：</h4>
								<span class="fr" style="margin-right: 22px">
									<img src="${constants.QINIU_USER_IMGURL}${userInfo.backCardPhoto}" class="headeait" width="240px" height="140px">
								</span>
								<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#back_card','backCardPhoto')" placeholder="请选择您的银行卡照片"  />
								<input type="hidden" style="display: none;" name="backCardPhoto" id="backCardPhoto" value="${userInfo.backCardPhoto}" />
							</div>
						</div>
						<div style="margin-top: -26px;" class="text-align">
							<h6 class="color-white size-9" style="margin-bottom: 10px;">认证信息必须与公安部官方数据库一致</h6>
							<button class="personalData" type="submit">确认</button>
						</div>
					</div>
						<input id="idCardFontDefaultImgPath" type="hidden" name="path" value="img/id_card_font_default.png">
						<input id="idCardBackDefaultImgPath" type="hidden" name="path" value="img/id_card_back_default.png">
						<input id="bankCardDefaultImgPath" type="hidden" name="path" value="img/bank_card_default.png">
					</form>
				</div>
			</div>
		</div>

		<%@include file="footer.jsp" %>

		<script type="text/javascript">
			var wH = $(window).height(); //获取屏幕高度
			hqkg();
			/**************************************登录后**************************************/
//			$(".grzx .exit-login").click(function(){//退出登录
//				window.location.href='realTimeOpening_login.html';
//			});
//			$(".personalData").click(function(){//个人资料页面
//				window.location.href='realTimeOpening_personalData.html';
//			});
			function hqkg(){
				var ydl_img_H = $(".ydl-img").height();
				var ydl_img_W = $(".ydl-img").width();
				$(".grzx").height(ydl_img_H);
				$(".grzx").css("margin-top",-(ydl_img_H));
			}

			//找回密码的验证码获取
			var InterValObj2; //timer变量，控制时间
			var count2 = 60; //间隔函数，1秒执行
			var code2 = ""; //验证码
			function sendMessage2() {
					$(".zhmm-yzm").removeAttr("onclick");
					//设置button效果，开始计时
					$(".zhmm-yzm").html("(" + count2 + ")秒内输入");
					InterValObj2 = window.setInterval(SetRemainTime2, 1000); //启动计时器，1秒执行一次
			}
			//timer处理函数
			function SetRemainTime2() {
				if (count2 == 0) {
					window.clearInterval(InterValObj2);//停止计时器
					$(".zhmm-yzm").removeAttr("disabled");//启用按钮
					$("zhmm-yzm").html("请重新发送");
					count2=60;
					$(".zhmm-yzm").attr("onclick","sendMessage2()");
					code2 = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
				}
				else {
					count2--;
					$(".zhmm-yzm").html("(" + count2 + ")秒内输入");
				}
			}

			function clickFile(obj){ //点击触发input file
				$(obj).parent().find("input[type='file']").click();
			}
			function qrFile(obj){//选中图片时的判断
				var file = obj.files[0];
		   		//判断类型是不是图片
			    if(!/image\/\w+/.test(file.type)){
		            alert("请确保文件为图像类型");
		            return false;
			    }
				var fileName="";
	            if(typeof(fileName) != "undefined")
	            {
	                fileName = $(obj).val().split("\\").pop();
	                fileName=fileName.substring(0, fileName.lastIndexOf("."));
	            }
            	$(obj).parent().find("input[type='text']").val(fileName);
			}

		    $('.update-password').click(function(){//修改密码
		    	window.location.href = 'realTimeOpening_update_password.html'
		    });
		     $('.break-data').click(function(){//修改密码-返回按钮
		    	$(".update-xgmm").hide();
		    	$(".smrz").show();
		    });
			$(function(){  /*调整窗口自动调整宽度*/
		    	$(window).resize(function(){//实时获取宽高
		    		var ydl_img_H = $(".ydl-img").height();
					var ydl_img_W = $(".ydl-img").width();
					$(".grzx").height(ydl_img_H);
					$(".grzx").css("margin-top",-(ydl_img_H));
	    		});
			});
		</script>
		<script>
			//			$.validator.setDefaults({
			//				submitHandler: function() {
			//					alert("submitted!");
			//				}
			//			});

			//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
			$.validator.setDefaults({
				errorElement:'span'
			});

			//配置通用的默认提示语
			$.extend($.validator.messages, {
				required: '必填',
				equalTo: "请再次输入相同的值"
			});

			$().ready(function() {
				// 验证表单提交时
//				$("#register").validate();

				// 在提交signup KeyUp和形式验证
				$("#register").validate({
					rules: {
						loginName: {
							required: true,
							minlength: 11,
							maxlength:11
						},
						password: {
							required: true,
							minlength: 6
						},
						confirm_password: {
							required: true,
							minlength: 6,
							equalTo: "#password"
						}
					},
					messages: {
						loginName: {
							required: "登录名不能为空！",
							minlength: "登录名必须为11位数字",
							maxlength: "登录名必须为11位数字"
						},
						password: {
							required: "密码不能为空！",
							minlength: "密码必须为6位以上"
						},
						confirm_password: {
							required: "密码不能为空！",
							minlength: "密码必须为6位以上",
							equalTo: "密码必须一致"
						},
					}
				});

				//code to hide topic selection, disable for demo
				var newsletter = $("#newsletter");
				// newsletter topics are optional, hide at first
				var inital = newsletter.is(":checked");
				var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
				var topicInputs = topics.find("input").attr("disabled", !inital);
				// show when newsletter is checked
				newsletter.click(function() {
					topics[this.checked ? "removeClass" : "addClass"]("gray");
					topicInputs.attr("disabled", !this.checked);
				});
			});
		</script>
	</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        var idCardFontDefaultImgPath = $("#idCardFontDefaultImgPath").val();
        var imgSrc1 = $("#id_card_front .headeait").attr("src");
        if(imgSrc1 == "${constants.QINIU_USER_IMGURL}") {
            $("#id_card_front .headeait").attr("src",idCardFontDefaultImgPath);
        }

        var idCardBackDefaultImgPath = $("#idCardBackDefaultImgPath").val();
        var imgSrc2 = $("#id_card .headeait").attr("src");
        if(imgSrc2 == "${constants.QINIU_USER_IMGURL}") {
            $("#id_card .headeait").attr("src",idCardBackDefaultImgPath);
        }

        var bankCardDefaultImgPath = $("#bankCardDefaultImgPath").val();
        var imgSrc3 = $("#back_card .headeait").attr("src");
        if(imgSrc3 == "${constants.QINIU_USER_IMGURL}") {
            $("#back_card .headeait").attr("src",bankCardDefaultImgPath);
        }
    });

	//身份证正面照
	$("#id_card_front .headeait").click(function(){
		filebtn1();
	});

	function filebtn1(){
		$("#id_card_front .filebtn").click();
	}

	//身份证反面照
	$("#id_card .headeait").click(function(){
		filebtn2();
	});

	function filebtn2(){
		$("#id_card .filebtn").click();
	}

	//银行卡正面照
	$("#back_card .headeait").click(function(){
		filebtn3();
	});

	function filebtn3(){
		$("#back_card .filebtn").click();
	}

	function readFile1(obj,id,string){
		var file = obj.files[0];
        if(!/image\/\w+/.test(file.type)){
            alert("请确保文件为图像类型");
            return false;
        }
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e){
			var imgBase64Data =encodeURIComponent(e.target.result);
			$(id + " .headeait").attr("src",this.result);
			var res = (this.result);
			var pos = imgBase64Data.indexOf("4")+4;
			imgBase64Data = imgBase64Data.substring(pos);
			$('#'+string).val(imgBase64Data);
//			var formData = $("imgBase64Data").serialize();
//			formData =decodeURIComponent(formData,true);
			var result = new MyJqueryAjax('web/stock/update/authentication','imgBase64Data='+imgBase64Data,null,'text').request();
			$('#'+string).val(result);
			// alert("上传成功");
		}
	}
	//换头像end
</script>
