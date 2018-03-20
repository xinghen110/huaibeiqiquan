<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<style>
    .w-e-text p{
        width: 100%;
    }
</style>
<div class="pageContent">
    <form method="post" action="${url}" id="forms" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="57">
            <div class="index1-div-div" id="add_photo" style="margin-top: 100px;">
                <div class="fl" style="width: 250px; height: 50px; margin-left: 140px; margin-top: 20px; ">
                    <span class="fl color-white" style="font-size: 16px; ">客户账号：</span>
                    <div ><span style="font-size: 16px; ">${appllyedUser.loginName}</span></div>
                </div>
                <div class="fl" style="width: 250px; height: 50px; margin-left: 20px; margin-top: 20px;">
                    <span class="fl color-white" style="font-size: 16px; ">冲正金额：</span>
                    <div><span style="font-size: 16px; color: red; ">${userAccountOperation.money}</span>&nbsp;&nbsp;<span style="font-size: 16px;">元</span></div>
                </div>
                <div class="fl" style="width: 250px; height: 50px; margin-left: 140px; ">
                    <span class="fl color-white" style="font-size: 16px; ">客户真实姓名：</span>
                    <div ><span style="font-size: 16px; ">${appllyedUserInfo.userName}</span></div>
                </div>
                <div class="fl" style="width: 290px; height: 50px; margin-left: 20px; ">
                    <span class="fl color-white" style="font-size: 16px; ">客户身份证号：</span>
                    <div><span style="font-size: 16px; ">${appllyedUserInfo.idNumber}</span></div>
                </div>
                <div class="fl" style="width:250px; height: 50px; margin-left: 140px;">
                    <span class="fl color-white" style="font-size: 16px; ">申请人：</span>
                    <div><span style="font-size: 16px; ">${appllyUser.loginName}</span></div>
                </div>
                <div class="fl" style="width: 250px; height: 50px; margin-left: 20px; ">
                    <span class="fl color-white" style="font-size: 16px; ">申请时间：</span>
                    <div>
                        <span style="font-size: 16px; ">
                            <%--<ry:formatDate date="${userAccountOperation.createDateTime}" fromFmt="yyyy-MM-dd"></ry:formatDate>--%>
                            <fmt:formatDate value="${userAccountOperation.createDateTime}" pattern="yyyy年MM月dd日" ></fmt:formatDate>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="button" onclick="formSubmit(1);">审核通过</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="button" onclick="formSubmit(2);">审核不通过</button>
                        </div>
                    </div>
                </li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
        <!-- 隐藏的值 -->
        <input type="hidden" name="id" value="${userAccountOperation.id}"/>
        <input type="hidden" id="status" name="status" value="">
    </form>
</div>
<script>
    var isVal=true;
    function formSubmit(str){
        $("#status").val(str);
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

<%--<script>--%>
    <%--var ue=UE.getEditor("myeditor",{--%>
        <%--autoHeightEnabled: false});--%>
    <%--ue.addListener("blur",function(){--%>
        <%--var html=UE.getEditor('myeditor').getContent();--%>
        <%--$("#content").val(html);--%>
    <%--});--%>
    <%--ue.addListener("ready", function () {--%>
        <%--var Basecontent = $("#content").val();--%>
        <%--ue.setContent(Basecontent,false);--%>
    <%--});--%>
    <%--$("#articleType").val(${article.articleType});--%>
<%--</script>--%>

