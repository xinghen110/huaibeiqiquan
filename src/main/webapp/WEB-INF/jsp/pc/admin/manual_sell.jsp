<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
<ry:binding type="1" bingdingName="STOCK_PRICE_TYPE" parentCode="STOCK_PRICE_TYPE"></ry:binding>
<style>
    .w-e-text p{
        width: 100%;
    }
</style>
<div class="pageContent">
    <form method="post" action="admin/manualSell" id="forms" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)"  enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <dl>
                <dt>订单号：</dt>
                <dd>
                    <input type="text" name="planId"  value="${obj.planId}" maxlength="40" size="40" readonly />
                </dd>
            </dl>
            <dl style="width: 750px;" >
                <dt>价格类型：</dt>
                <dd>
                    <select name="sellPriceType" id="sellPriceType" onchange="change()">
                        <c:forEach items="${STOCK_PRICE_TYPE}" var="d">
                            <option value="${d.itemCode}">${d.itemName}</option>
                        </c:forEach>
                    </select>
                    <%--<textarea style="width: 600px;height: 100px;resize: none;" name="fileUrl">${article.fileUrl}</textarea>--%>
                </dd>
            </dl>
            <dl style="width: 750px;" id="xianjia">
                <dt>限价(元)：</dt>
                    <input type="text" name="sellLimitPrice">
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="formSubmit();">确认权行</button></div></div></li>
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
    $("#xianjia").hide();
    function change(){
        var sellPriceType = $("#sellPriceType").val();

        if(sellPriceType == "0"){
            $("#xianjia").hide();
        }else if(sellPriceType == "1"){
            $("#xianjia").show();
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
