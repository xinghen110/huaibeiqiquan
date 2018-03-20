<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="ALLOW_WITHDRAW_TIME" bingdingName="ALLOW_WITHDRAW_TIME"></ry:binding>
<ry:binding parentCode="MAX_WITHDRAW_COUNT" bingdingName="MAX_WITHDRAW_COUNT"></ry:binding>
<ry:binding parentCode="MAX_WITHDRAW_LIMIT" bingdingName="MAX_WITHDRAW_LIMIT"></ry:binding>

<style>
    .w-e-text p{
        width: 100%;
    }
    .red_border{
        border: 1px solid red;
    }
</style>
<div class="pageContent">
    <form method="post" action="admin/income/expenditure/setting" id="forms" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)"  enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <dl>
                <dt>允许提现时间段：</dt>
                <dd>
                    <input id="time" type="text" name="time"  onblur="checkTime();"  value="${ALLOW_WITHDRAW_TIME[0].itemCode}" alt="正确的格式为 0000|1700" />
                </dd>
            </dl>
            <dl>
                <dt>最多提现次数：</dt>
                <dd>
                    <input type="text" id="max_withdraw_count" name="count"  value="${MAX_WITHDRAW_COUNT[0].itemCode}" maxlength="40" size="40" class="mustFill" title="最多提现次数" alt="请输入最多提现次数" />
                </dd>
            </dl>
            <dl>
                <dt>最大提现金额：</dt>
                <dd>
                    <input type="text" id="max_withdraw_limit" name="limit"  value="${MAX_WITHDRAW_LIMIT[0].itemCode}" maxlength="40" size="40" class="mustFill" title="最大提现金额" alt="请输入最大提现金额" />
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="formSubmit();">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
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


    function checkTime() {
        var timeValue = $("#time").val();
        time = /^([0-1]?[0-9]|2[0-3])([0-5][0-9])\|([0-1]?[0-9]|2[0-3])([0-5][0-9])$/;
        if(!time.test(timeValue)){
            alert("正确的时间格式为0000|2359");
            $("#time").addClass("red_border");
            isVal=false;
        }else {
            $("#time").removeClass("red_border");
            isVal=true;
        }
    }
</script>
