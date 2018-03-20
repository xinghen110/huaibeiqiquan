<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<style>
    .w-e-text p{
        width: 100%;
    }
    .input_red{
        border: 1px solid red;
    }
</style>
<div class="pageContent">
    <form method="post" action="user/account/operation/add" id="forms" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)"  enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <dl>
                <dt>申请账户登录名：<span style="color: red">*</span></dt>
                <dd>
                    <input type="text" id="loginName" name="loginName"   maxlength="40" size="40" class="mustFill " title="申请账户登录名" alt="请输入申请账户登录名" />
                    <label id="loginNameAlertValue" style="display: none">无此用户</label>
                </dd>
            </dl>
            <input type="hidden" id="userId" name="userId"   maxlength="40" size="40" class="mustFill" title="用户实名信息" />
            <dl>
                <dt>申请操作的金额：</dt>
                <dd>
                    <input type="text" id="money" name="money"  value="${userAccountOperation.money}" maxlength="40" size="40" class="mustFill" title="申请操作的金额" alt="请输入申请操作的金额" />
                </dd>
            </dl>
            <dl>
                <dt>真实姓名：<span style="color: red">*</span></dt>
                <dd>
                    <input type="text" id="userName" maxlength="40" size="40" class="mustFill" title="真实姓名" readonly/>
                </dd>
            </dl>
            <dl>
                <dt>身份证号：<span style="color: red">*</span></dt>
                <dd>
                    <input type="text" id="idNumber" name="idNumber" value="" maxlength="40" size="40" class="mustFill" title="身份证号" readonly/>
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

    $("#loginName").change(function () {
        $.ajax({
            type: 'get',
            url:"admin/user/info?loginName="+$("#loginName").val(),
            data:"",
            dataType:"json",
            cache: false,
            success: function (data) {
                console.log(data);
                if(data.userName == null || data.userName == ""){
                    $("#userName").val("该用户还没有实名验证");
                    $("#idNumber").val("");
                    $("#userId").val("");
                }else{
                    $("#userName").val(data.userName);
                    $("#idNumber").val(data.idNumber);
                    $("#userId").val(data.userId);
                }
//                Console.log(this);
//                alert(data);
//                if(data==null){
//                    $("#loginName").addClass("input_red");
//                    isVal = false;
//                    $("#loginNameAlertValue").css("display","");
//                    $("#loginNameAlertValue").css("color","red");
//                }else {
//                    $("#userId").val(data.userId);
//                    $("#loginName").removeClass("input_red");
//                    isVal = true;
//                    $("#loginNameAlertValue").css("display","none");
//                    $("#loginNameAlertValue").css("color","");
//                }
            },
            error: DWZ.ajaxError
        });

//        $.ajax({
//            type: 'get',
//            url:"admin/user/detail?loginName="+$("#loginName").val(),
//            data:"",
//            dataType:"json",
//            cache: false,
//            success: function (data) {
//                if(data==null){
//                    $("#loginName").addClass("input_red");
//                    isVal = false;
//                    $("#loginNameAlertValue").css("display","");
//                    $("#loginNameAlertValue").css("color","red");
//                }else {
//                    $("#userId").val(data.userId);
//                    $("#loginName").removeClass("input_red");
//                    isVal = true;
//                    $("#loginNameAlertValue").css("display","none");
//                    $("#loginNameAlertValue").css("color","");
//                }
//            },
//            error: DWZ.ajaxError
//        });
    });
</script>
