<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<ry:binding type="2"></ry:binding>
<div class="pageContent">
    <form method="post" action="typeinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">

            <dl style="width: 750px;float: none;" >
                <dt><span style="color: red;">*</span>名称：</dt>
                <dd>
                    <input type="text" name="typeInfoName" value="${bean.typeInfoName }" class="mustFill" title="名称">
                </dd>

            </dl>

            <dl style="width: 750px;" >
                <dt><span style="color: red;">*</span>排序：</dt>
                <dd>
                    <input type="text" name="sortNum" value="${not empty bean.sortNum? bean.sortNum :(MaxSort+1) }" class="mustFill" title="等级">
                </dd>
            </dl>
            
             <dl style="width: 750px;" >
                <dt><span style="color: red;">*</span>是否：</dt>
                <dd>
		            <select name="isVip" onchange="changeVIP()">
					 <option value="">请选择</option>
					 <option value="1" <c:if test="${bean.isVip == 1}">selected="selected"</c:if>>是</option>
					 <option value="2" <c:if test="${bean.isVip == 2}">selected="selected"</c:if>>否</option>
				   </select>
			 </dd>
		 </dl>

            <dl style="width: 750px; display: none;" id="vipCount" >
                <dt><span style="color: red;">*</span>VIP数量：</dt>
                <dd>
                    <input type="text" name="typeNumber" value="${bean.typeNumber}" class="mustFill" title="VIP数量">
                </dd>
            </dl>
            

            <!--隐藏值-->
            <input type="hidden" name="typeNum" value="${bean.typeNum }"  >
            <input type="hidden" name="typeId" value="${bean.typeId }"  >

            <dl id="icon" style="width: 800px;height: 140px;" >
                <dt>分类图标：</dt>
                <dd style="width: 170px;height: 170px;">
                    <c:if test="${not empty bean.imgUrl}"> <input type="hidden" name="imgUrl" readonly="readonly" value="${bean.imgUrl}" /></c:if>
                    <img id="imgUrlview" src="${constants.QINIU_USER_IMGURL}${not empty bean.imgUrl ? bean.imgUrl :'default2.png'}" width="100" height="100" />
                    <input id="imgUrlIE" style="display: none;">
                    <input name="mainPhoto" id="mainPhoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainPhoto','100px','100px','imgUrlview','imgUrlIE');" value="上传附件">
                    <span style="color: red; width: 100px">上传图片170px*170px最佳</span>
                </dd>
            </dl>
            <%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>

        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">保存</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
            </ul>
        </div>

    </form>
</div>
<script type="text/javascript">

    function todo(){
        var userId = $("#userId").val();
        if(userId != ''){
            flag = true;
        }else{
            if($('#loginName').val() == '')
                return alertMsg.warn("无登录名");
        }

        if(flag){
            if(check())
                $("#forms").submit();
        }else{
            alertMsg.warn("该登录名已经存在！");
        }
    }
    $(function () {
        var vip = "${bean.isVip}";
        if (vip == "2") {
            $("#vipCount").hide();
        } else if (vip == "1") {
            $("#vipCount").show();
        }
    })
    function changeVIP() {
        var num = $("select[name=isVip]").val();
        if (num == '2') {
            $("#vipCount").hide();
        } else if (num == '1') {
            $("#vipCount").show();
        }
    }
</script>
