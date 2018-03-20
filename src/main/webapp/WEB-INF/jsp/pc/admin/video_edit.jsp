<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<style>
    .w-e-text p{
        width: 100%;
    }
</style>
<div class="pageContent">
    <form method="post" action="${url}" id="forms" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)"  enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <dl>
                <dt>文章标题：</dt>
                <dd>
                    <input type="text" id="loginName" name="title"  value="${article.title}" maxlength="40" size="40" class="mustFill" title="文章标题" alt="请输入文章标题" />
                </dd>
            </dl>
            <input type="hidden" name="mediaType"  value="2"/>
            <dl style="width: 750px;height: 150px;" >
                <dt>配图链接：</dt>
                <dd>
                    <textarea style="width: 600px;height: 100px;resize: none;" name="fileUrl">${article.fileUrl}</textarea>
                </dd>
            </dl>
              <dl style="width: 750px;height: 150px;" >
            <dt>视频链接：</dt>
            <dd>
                <textarea style="width: 600px;height: 100px;resize: none;" name="linkUrl">${article.linkUrl}</textarea>
            </dd>
        </dl>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="formSubmit();">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
        <!-- 隐藏的值 -->
        <input type="hidden" name="articleId" value="${article.articleId}"/>
    </form>
</div>
<script>
    var isVal=true;
    function formSubmit(){
        if(isVal==false){
            $('#fali_show').show();
            return false;
        }else{
            if(check())
                $('#forms').submit();
        }

    }

    function validateCallback(form, callback, confirmMsg) {
        var $form = $(form);
        if (!$form.valid()) {
            return false;
        }

        var _submitFn = function(){
            $.ajax({
                type: form.method || 'POST',
                url:$form.attr("action"),
                data:$form.serializeArray(),
                dataType:"json",
                cache: false,
                success: callback || DWZ.ajaxDone,
                error: DWZ.ajaxError
            });
        }

        if (confirmMsg) {
            alertMsg.confirm(confirmMsg, {okCall: _submitFn});
        } else {
            _submitFn();
        }

        return false;
    }
</script>
