<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>新增视频</title>
</head>
	<body>
		<div data-role="page" id="add_video">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" data-rel="back"><img src="app/img/icon_nav_back.png"/></a>
				<h4>新增视频</h4>
			</div>
			<div data-role = "content">
				<div>
					<h4 class="size-9">视频名称</h4>
					<input class="size-9" type="text" placeholder="请输入视频名称">
				</div>
				<div class="headeait">
					<img src="" class="tctp" />
					<h4 class="size-9">选择视频</h4>
					<input class="size-9 choose-video" type="text" readonly="readonly" placeholder="请选择视频">
					<img class="right right-video" src="app/img/icon_right.png">
				</div>
				 <a data-role="button" class="save_video">保存</a> 
				 <input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile3(this)"   />
				 <input type="hidden" style="display: none;" name="userPhoto" id="UserPhoto" value="" />
			</div>
		</div>
		<script type="text/javascript">
			$(".del").click(function(){
				$(this).parent().remove();
			});
			
			//新增套餐图片
			$("#add_video").find(".headeait").click(function(){
				filebtn();				
			});
		
			function filebtn(){
				$("#add_video").find(".filebtn").click();
			}
			function readFile3(obj){
				$(".choose-video").attr("placeholder","")
				var file = obj.files[0]; 
				var reader = new FileReader();
				reader.readAsDataURL(file);
					reader.onload = function(e){
						var imgBase64Data =encodeURIComponent(e.target.result);
						$("#add_video").find(".tctp").show()
						$("#add_video").find(".tctp")[0].src=this.result;
						var res = (this.result);
						var pos = imgBase64Data.indexOf("4")+4;
						imgBase64Data = imgBase64Data.substring(pos);
						$('#UserPhoto').val(imgBase64Data);
				}
			}
		</script>
	</body>
</html>