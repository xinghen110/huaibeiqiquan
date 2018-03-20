<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>个人资料</title>
</head>
	<body>
		<div data-role="page" id="personal-data">
			<div data-role="header" style="background: white;">
			 <a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
			 	<h4 class="text-shadow">个人资料</h4>
			</div>
			<form id="forms">
				<input type="hidden" name="userNum" value="${user.userNum}">
				<div data-role = "content">
					<ul data-role="listview">
						<li data-icon="false">
							<h4><span class="fl sztx">设置头像</span><span class="fr"><img src="${constants.QINIU_USER_IMGURL}${user.userPhoto}" class="headeait" width="40px" height="40px"><img class="right" src="app/img/icon_right.png"></span></h4>
							<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile3(this)"   />
							<input type="hidden" style="display: none;" name="userPhoto" id="UserPhoto" value="" />
						</li>
						<li data-icon="false">
							<a data-ajax="false" href="app/page/modify_nickname?userNum=${user.userNum}">
								<h4><span class="fl">昵称</span><span class="fr">${user.nickName}<img class="right" src="app/img/icon_right.png"></span></h4>
							</a>
						</li>
						<li data-icon="false">
							<a data-ajax="false" href="app/page/modify_tel?userNum=${user.userNum}">
								<h4><span class="fl">手机号码</span><span class="fr">${user.userPhone}<img class="right" src="app/img/icon_right.png"></span></h4>
							</a>
						</li>
						<li data-icon="false" class="id-number">
							<h4><span class="fl">性别</span><span class="fr color-t"><ry:show parentCode="USERSEX" itemCode="${user.userSex}"></ry:show></span></h4>
						</li>
						<li data-icon="false">
							<a data-ajax="false" href="app/page/no_address?userNum=${user.userNum}">
								<h4><span class="fl">常用地址</span><span class="fr"><img class="right" src="app/img/icon_right.png"></span></h4>
							</a>
						</li>
					 </ul>
				</div>
			</form>
		</div>
		<script type="text/javascript">
				//换头像begin
				$("#personal-data").find(".headeait").click(function(){
					filebtn();				
				});
			
				function filebtn(){
					$("#personal-data").find(".filebtn").click();
				}
				function readFile3(obj){
					var file = obj.files[0]; 
					var reader = new FileReader();
					reader.readAsDataURL(file);
						reader.onload = function(e){
							var imgBase64Data =encodeURIComponent(e.target.result);
							$("#personal-data").find(".headeait")[0].src=this.result;
							var res = (this.result);
							var pos = imgBase64Data.indexOf("4")+4;
							imgBase64Data = imgBase64Data.substring(pos);
							$('#UserPhoto').val(imgBase64Data);
                            var formData = $("#forms").serialize();
                            formData =decodeURIComponent(formData,true);
                            var result = new MyJqueryAjax('app/page/save_my_data',formData,null,'text').request();
                            if(result==1){
                                window.location.href='app/page/personal_data?userNum=${user.userNum}';
                            }
						}
				}
			  	//换头像end
			</script>
	</body>
</html>