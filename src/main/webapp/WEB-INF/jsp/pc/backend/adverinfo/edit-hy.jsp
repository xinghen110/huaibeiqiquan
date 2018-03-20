<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<%@include file="/WEB-INF/jsp/inc/autocomplete.jsp"%>
<ry:binding type="2"></ry:binding>
<ry:binding type="3"></ry:binding>
<script>
    $(function() {
        $('#selectmy').comboSelect();
    });
</script>

<ry:binding parentCode="JUMP_TYPE" bingdingName="jumpType"></ry:binding>
<div class="pageContent">
    <form method="post" action="adverinfo/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <input  type="hidden" name="adverType" value="${adverType}"/>

            <dl style="width: 800px;">
                <dt><span style="color: red;">*</span>排序 ：</dt>
                <dd>
                    <input type="number" name="sortNum" value="${bean.sortNum }" title="排序" class="mustFill" maxlength="100" >
                </dd>
            </dl>

            <dl id="icon" style="width: 800px;height: 180px;" >
                <dt>广告图片：</dt>
                <dd style="width: 130px;height: 125px;">
                    <c:if test="${not empty bean.mainPhoto}"> <input type="hidden" name="mainPhoto" readonly="readonly" value="${bean.mainPhoto}" /></c:if>
                    <img id="mainPhotoview" src="${constants.QINIU_USER_IMGURL}${not empty bean.mainPhoto ? bean.mainPhoto:'default.png'}" width="200" height="120" />
                    <input id="mainPhotoIE" style="display: none;">
                    <input name="mainphoto" id="mainphoto" type="file" style="width: 180px;padding-top: 4px;" multiple="multiple" onchange="javascript:setImagePreview('mainphoto','200px','120px','mainPhotoview','mainPhotoIE');" value="上传附件">
                    <span style="color: red; width: 150px">图片1242px*672px最佳</span>
                </dd>
                <%@include file="/WEB-INF/jsp/inc/display_picture_js.jsp"%>
            </dl>

            <dl style="width: 800px;">
                <dt>广告视频 ：</dt>
                <dd>
                    <input name="videopath" type="file" id="videopath">
                    <span style="color: red; width: 150px">视频不要超过15M，并且是mp4格式</span>
                </dd>
            </dl>


        </div>

        <div class="formBar">
            <ul>
                <li><input type="hidden" name="adverInfoId" value="${bean.adverInfoId }">
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>

    </form>
</div>
<script type="text/javascript">
    function todo(){
        if (${empty bean}) {
        }
        if(check())
            $("#forms").submit();
    }


</script>
