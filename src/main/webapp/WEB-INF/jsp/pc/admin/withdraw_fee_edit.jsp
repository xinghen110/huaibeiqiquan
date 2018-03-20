<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="CHARGE_FEE" bingdingName="CHARGE_FEE"></ry:binding>

<div class="pageContent">
    <form method="post" action="admin/withdrawfee/edit" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="63">

            <p style="float: none;">
                <label>提现手续费：</label>
                <input name="itemCode" style="width: 180px;" class="required" type="text" size="30" value="${CHARGE_FEE[0].itemCode}" /><span style="line-height: 28px">元</span>
            </p>
            <!-- 隐藏值 -->
            <input type="hidden" value="${CHARGE_FEE[0].id}" name="id">

        </div>

        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>

                <%--<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>--%>
                <%--<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>--%>
            </ul>
        </div>

    </form>
</div>