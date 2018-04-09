<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		<title>实名绑卡</title>
	</head>
	<%@include file="base.jsp"%>
	<ry:binding parentCode="BANK_NAME" bingdingName="BANK_NAME"></ry:binding>
	<body>
		<div data-role="page" id="updata_yh_card">
			<div data-role = "header" style="border-width: 0; background: #28292E;">
				<a data-role="button" data-rel="back" ><img src="img/mobile/icon_nav_back.png"/></a>
				<h4 class="color-w">换绑银行卡</h4>
			</div>
			<div data-role = "content">
				<form id="form" action="mobile/update/bandingcard" data-ajax="false" method="post">
					<input type="hidden" name="userId" value="${userInfo.userId}">
				<div style="margin-top: 1.5em;">
					<h4 class="fl">银行卡卡号</h4>
					<input style="margin-left: 1em;" data-role = "none"  class="fl color-w size-9" type="number" oninput="if(value.length >19)value=value.slice(0,19)" placeholder="请输入您的银行卡号" name="bankCardNumber" value="${userInfo.bankCardNumber}" required>
					<%--<input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="number" placeholder="输入提现金额" />--%>
				</div>
				<%--<div>--%>
					<%--<h4 class="fl">银行卡卡号</h4>--%>
					<%--<input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="number" placeholder="输入提现金额" />--%>
				<%--</div>--%>
				<div>
					<h4 class="fl">所属银行</h4>
					<select name="bankId" data-role = "none">
						<option value="">请选择银行</option>
						<c:forEach var="item" items="${BANK_NAME}">
							<option value="${item.itemName}" <c:if test="${item.itemName == userInfo.bankId}">selected</c:if>>${item.itemName}</option>
						</c:forEach>
					</select>
					<%--<select data-role = "none">--%>
					<%--<option>请选择银行</option>--%>
					<%--<option>光大银行</option>--%>
					<%--<option>中国邮政储蓄银行</option>--%>
					<%--<option>建设银行</option>--%>
					<%--</select>--%>
				</div>
				<div id="back_card">
					<h4 class="fl">银行卡照片</h4>
					<span class="fr" style="margin-right: 22px">
						<img src="${constants.QINIU_USER_IMGURL}${userInfo.backCardPhoto}" class="headeait" width="200px" height="80px">
					</span>
					<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile1(this,'#back_card','backCardPhoto')" placeholder="请选择您的银行卡照片"  />
					<input type="hidden" style="display: none;" name="backCardPhoto" id="backCardPhoto" value="${userInfo.backCardPhoto}" />
				</div>
				<%--<div>--%>
					<%--<h4 class="fl">银行卡图片</h4>--%>
					<%--<h4 onclick="clickFile(this)"  class="color-w img-name">请选择图片</h4>--%>
					<%--<input data-role = "none" class="file" type="file" onchange="qrFile(this)" style="display: none;" />--%>
				<%--</div>--%>
				<a id="formsub" data-ajax = "false" class="ui-btn login">确定</a>
				<%--<a data-ajax = "false" href='real_name_binding_card_success.html' class="ui-btn">确定</a>--%>
				</form>
				<%--<input name="msg" value="${ceshe}">--%>
			</div>
		</div>
		<script type="text/javascript">

            $("#formsub").click(function () {
//                    if($("#loginName").val()==''||$("#password").val()==''){
//                        alert("请输入用户名和密码");
//                        return;
//                    }else {
//                        $("#form").submit();
//                    }
                $("#form").submit();
            });

//			function clickFile(obj){ //点击触发input file
//				$(obj).parent().find(".file").click();
//			}
//			function qrFile(obj){//选中图片时的判断
//				var file = obj.files[0];
//		   		//判断类型是不是图片
//			    if(!/image\/\w+/.test(file.type)){
//		            alert("请确保文件为图像类型");
//		            return false;
//			    }
//				var fileName="";
//	            if(typeof(fileName) != "undefined")
//	            {
//	                fileName = $(obj).val().split("\\").pop();
//	                fileName=fileName.substring(0, fileName.lastIndexOf("."));
//	            }
//            	$(obj).parent().find(".img-name").text(fileName);
//			}
		</script>
		<script type="text/javascript">
            //身份证正面照
//            $("#id_card_front .headeait").click(function(){
//                filebtn1();
//            });
//
//            function filebtn1(){
//                $("#id_card_front .filebtn").click();
//            }

            //身份证反面照
//            $("#id_card .headeait").click(function(){
//                filebtn2();
//            });
//
//            function filebtn2(){
//                $("#id_card .filebtn").click();
//            }

            //银行卡正面照
            $("#back_card .headeait").click(function(){
                filebtn3();
            });

            function filebtn3(){
                $("#back_card .filebtn").click();
            }

            function readFile1(obj,id,string){
                var file = obj.files[0];
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
		<%--<script>--%>
<%--//            (function($){--%>
<%--//                $.getUrlParam--%>
<%--//                    = function(name)--%>
<%--//                {--%>
<%--//                    var reg--%>
<%--//                        = new RegExp("(^|&)"+--%>
<%--//                        name +"=([^&]*)(&|$)");--%>
<%--//                    var r--%>
<%--//                        = window.location.search.substr(1).match(reg);--%>
<%--//                    if (r!=null) return unescape(r[2]); return null;--%>
<%--//                }--%>
<%--//            })(jQuery);--%>

            <%--&lt;%&ndash;var msg = $.getUrlParam('msg');&ndash;%&gt;--%>
            <%--&lt;%&ndash;alert(msg);&ndash;%&gt;--%>
			<%--&lt;%&ndash;var message = '<%=session.getAttribute("msg")%>';&ndash;%&gt;--%>
			<%--&lt;%&ndash;<%=session.getAttribute("msg")%>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<%=session.getAttribute("")%>&ndash;%&gt;--%>
			<%--&lt;%&ndash;alert(message);&ndash;%&gt;--%>
		<%--</script>--%>
	</body>
</html>
