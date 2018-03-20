<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html >
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/jqm.jsp"%>
	<title>新增技师</title>
</head>
<body>
<style>
	.ui-page-theme-a .ui-radio-on:after, html .ui-bar-a .ui-radio-on:after, html .ui-body-a .ui-radio-on:after, html body .ui-group-theme-a .ui-radio-on:after, .ui-btn.ui-radio-on.ui-btn-a:after{border:#31B16C}
</style>
<div data-role="page" id="add_technician">
	<div data-role = "header" style="background: white;border-width: 0px">
		<a data-role="button" onclick="goBack(-1)"><img src="app/img/icon_nav_back.png"/></a>
		<h4><c:if test="${empty jishiInfo}">新增</c:if><c:if test="${not empty jishiInfo}">修改</c:if>技师</h4>
	</div>
	<form id="forms">
		<input type="hidden" name="shopNum" value="${shopNum}">
		<input type="hidden" name="jishiInfoNum" value="${jishiInfo.jishiInfoNum}">
		<div data-role = "content">
			<ul data-role="listview">
				<li data-icon="false">
					<h4><span class="fl sztx size-9">设置头像</span><span class="fr"><img src="" class="headeait" width="40px" height="40px"><img class="right" src="app/img/icon_right.png"></span></h4>
					<input type="file" class="filebtn" data-role="none" style="display: none;" onchange="readFile3(this)"   />
					<input type="hidden" style="display: none;" name="userPhoto" id="UserPhoto" value="" />
				</li>
			</ul>
			<div class="wc">
				<h4 class="fl size-9">技师姓名</h4>
				<input type="text" placeholder="请输入技师姓名" value="${jishiInfo.userName}" name="userName" />
			</div>
			<div class="wc">
				<h4 class="fl size-9">出生年月</h4>
				<input readonly="readonly" id="userBirth"  placeholder="请选择出生年月" name="userBirth" value="<fmt:formatDate value="${jishiInfo.userBirth}" pattern="yyyy-MM-dd"/>" />
				<img src="app/img/icon_right.png">
			</div>
			<div class="wc sex">
				<h4 class="fl size-9">技师性别</h4>
				<label for="male">男性</label>
				<input type="radio" name="gender" id="male" value="1" <c:if test="${jishiInfo.userSex == 1}"> checked </c:if>>
				<label for="female">女性</label>
				<input type="radio" name="gender" id="female" value="2" <c:if test="${jishiInfo.userSex == 2}"> checked </c:if>>
			</div>
			<div class="wc tc_type">
				<h4 class="fl size-9" style="margin-top: 1.1em;">技师类别</h4>
				<select name="day" id="day" multiple="multiple" data-native-menu="false">
					<option>请选择套餐类型</option>
					<c:forEach var="item" items="${typeInfo}">
						<option value="${item.typeNum}" <c:if test="${fn:contains(jishiInfo.typeNum, item.typeNum)}"> selected = "selected" </c:if>>${item.typeInfoName}</option>
					</c:forEach>
				</select>
			</div>

			<div class="wc textarea-wc" style="margin-bottom: 1em;">
				<h4 class="fl size-9">个人简介</h4>
				<textarea placeholder="请输入个人简介" name="description"  maxlength="200">${jishiInfo.description}</textarea>
				<h3>限200字</h3>
			</div>
			<a data-role="button" class="baocun" onclick="save()">保存</a>
		</div>
	</form>
</div>
<script type="text/javascript">
    var calendar = new datePicker();
    calendar.init({
        'trigger': '#userBirth', /*按钮选择器，用于触发弹出插件*/
        'type': 'date',/*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate':'1900-1-1',/*最小日期*/
        'maxDate':(new Date()).Format("yyyy-MM-dd"),/*最大日期*/
        'onSubmit':function(){/*确认时触发事件*/
        },
        'onClose':function(){/*取消时触发事件*/
        }
    });
    $("#userBirth").focus(function(){
        document.activeElement.blur();
    });
    //换头像begin
    $("#add_technician").find(".headeait").click(function(){
        filebtn();
    });

    function filebtn(){
        $("#add_technician").find(".filebtn").click();
    }
    function readFile3(obj){
        var file = obj.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            var imgBase64Data =encodeURIComponent(e.target.result);
            $("#add_technician").find(".headeait")[0].src=this.result;
            var res = (this.result);
            var pos = imgBase64Data.indexOf("4")+4;
            imgBase64Data = imgBase64Data.substring(pos);
            $('#UserPhoto').val(imgBase64Data);
        }
    }
    //换头像end
    //进入页面设置已有图片
    $(function () {
        var img = '${jishiInfo.mainPhoto}';
        if (img.trim() != '') {
            $("#add_technician").find(".headeait").attr('src', '${constants.QINIU_USER_IMGURL}${jishiInfo.mainPhoto}');
        } else {
            $("#add_technician").find(".headeait").attr('src', 'app/img/default_technician.png');
        }
    })
    function goBack() {
        window.location.href="app/page/technician_management?userNum=${userNum}";
    }
    function save() {
        var userPhoto = $("#UserPhoto").val();
        var img = '${jishiInfo.mainPhoto}';
        if (img == '' && userPhoto == '') {
            alert("请设置头像！");
            return false;
        }
        var userName = $("input[name=userName]").val().trim();
        var userBirth = $("#userBirth").val().trim();
        var userSex = $('input[name=gender]:checked').val();
        var typeNum = $("#day").val();
        var description = $("textarea[name=description]").val().trim();
        if (userName == '' || userBirth == '' || userSex == '' || userSex == null || typeNum == null || description == '') {
            alert("请补全身份信息！");
            return false;
        }
        var formData = $("#forms").serialize();
        formData =decodeURIComponent(formData,true);
        $("#forms").html('<div class="no-data-page"><p class="color-hs size-9"><img alt="" src="app/img/ajax-loader.gif"><br><br>数据加载中...</p></div>');
        setTimeout(function(){
            var result = new MyJqueryAjax('app/page/save_technician',formData,null,'text').request();
            if(result == "true"){
                alert("设置技师成功！");
                updateTechnician();
                goBack(1);
            } else {
                alert("设置技师失败！");
            }}, 100);
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