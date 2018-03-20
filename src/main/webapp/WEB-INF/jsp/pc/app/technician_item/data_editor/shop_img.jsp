<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
<title>店铺图片</title>
</head>
	<body>
		<div data-role="page" id="shop_img">
			<div data-role = "header" style="background: white;border-width: 0px">
				<a data-role="button" onclick="goBack()"><img src="app/img/icon_nav_back.png"/></a>
				<h4>店铺图片</h4>
			</div>
			<div data-role = "content">
				<ul data-role="listview" id="img_list">
					<c:forEach items="${attachInfos}" var="item">
						<li data-icon="false" class="fl">
							<img src="${constants.QINIU_USER_IMGURL}${item.filePath}" />
							<h4 id="del" onclick="del(this)">删除</h4>
							<span style="display: none">${item.attachId}</span>
						</li>
					</c:forEach>
				</ul>
				<form id="forms" style="display: none">
					<input name="userNum" value="${userNum}">
					<input name="shopNum" value="${shopNum}">
					<input name="img" id="img" value="">
				</form>
				<input type="file" data-role="none" id="open_local" onchange="readFile(this)" style="display: none;">
				<a data-role="button" class="add_img">新增图片</a>
			</div>
		</div>
		<script type="text/javascript">
			function del(org){
                var attachId = $(org).next().html();
                $.post("app/pageshow/del_shop_img?attachId="+attachId+"&shopNum=${shopNum}", null, function (data) {
                    if (data == "true") {
                        alert("删除图片成功！");
                    } else {
                        alert("删除图片失败！");
                        return false;
                    }
                });
				$(org).parent().remove();
			}
			$(".add_img").click(function(){
				choose_img();
			})
			function choose_img(){
				$("#open_local").click();
			}
			var i =0;
			function readFile(obj){
			    var file = obj.files[0]; 
			    var imgType =  /image\/\w+/;
			    if(!imgType.test(file.type)){     
			           showAlert("请确保文件为图片类型",2);
			           return false;
			    }
			    var reader = new FileReader();
			    var imgList=document.getElementById('img_list'); 
			    reader.readAsDataURL(file);
			    reader.onload = function(e){

					var result = this.result;

					//新增图片
                    var attachId = '';
                    var imgBase64Data =encodeURIComponent(e.target.result);
                    var pos = imgBase64Data.indexOf("4")+4;
                    imgBase64Data = imgBase64Data.substring(pos);
                    $("#img").val(imgBase64Data)
                    var formData = $("#forms").serialize();
                    formData =decodeURIComponent(formData,true);
                    var back = new MyJqueryAjax('app/pageshow/add_shop_img',formData,null,'text').request();
                    if(back!="false" && back!='' && back!=null){
                        alert("新增图片成功！");
                        attachId = back;
                    } else {
                        alert("新增图片失败！");
                        return false;
					}

					var image = new Image();
					image.src = result;

					var Li = document.createElement('li');
					Li.setAttribute('data-icon',"false");
					Li.setAttribute('class',"fl");
					Li.setAttribute('id',"li"+i)
					imgList.insertBefore(Li,null);

					var img = document.createElement('img');
					img.setAttribute('src', this.result);
					var btn2 = document.getElementById('li'+i);
					btn2.insertBefore(img,null);


					var h4 = document.createElement('h4');
					h4.innerHTML = "删除";
					h4.setAttribute('id',"del")
					h4.setAttribute('onclick', "del(this)");
					btn2.insertBefore(h4,null);

					var span = document.createElement('span');
					span.setAttribute('style', "display: none");
					span.innerHTML = attachId;
					btn2.insertBefore(span, null);
					$('#img_list').listview('refresh');
		    	}
				i++;
			}
			function goBack() {
			    var array = '${array}';
                window.location.href="app/pageshow/technician_data?userNum=${userNum}&array="+array;
            }
		</script>
	</body>
</html>