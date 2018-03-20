<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>

<ry:binding parentCode="USERSEX,ORG_TYPE" bingdingName="usersexs,orgTypeList"></ry:binding>
<div class="pageContent">
    <form method="post" action="admin/user/info/check" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
        <div class="pageFormContent nowrap" layoutH="57">
            <dl>
                <dt><span style="color: red;">*</span>用户名：</dt>
                <dd>
                    <input readonly="readonly" type="text" name="userName" value="${userInfo.userName}" size="30" class="mustFill" title="用户名" alt="请输入用户名"/>
                </dd>
            </dl>

            <dl>
                <dt><span style="color: red;">*</span>身份证号码：</dt>
                <dd>
                    <input readonly="readonly" type="text" name="idNumber" value="${userInfo.idNumber}" size="30" class="mustFill" title="身份证号码" alt="请输入身份证号码"/>
                </dd>
            </dl>

            <%--<dl>--%>
                <%--<dt><span style="color: red;">*</span>身份证正面照：</dt>--%>
                <%--<dd>--%>
                    <%--&lt;%&ndash;<img src="${user.idCardFrontView}"> &ndash;%&gt;--%>
                    <%--<img src="img/1.jpg" height="50" width="200">--%>
                <%--</dd>--%>
            <%--</dl>--%>

            <dl>
                <dt><span style="color: red;">*</span>银行卡号：</dt>
                <dd>
                    <input readonly="readonly" type="text" name="bankCardNumber" value="${userInfo.bankCardNumber}" size="30" class="mustFill" title="银行卡号" alt="请输入银行卡号"/>
                </dd>
            </dl>

            <dl>
                <dt><span style="color: red;">*</span>审核结果：</dt>
                <dd>
                        <select name="status">
                            <option value="">请选择审核结果</option>
                            <option value="1">通过</option>
                            <option value="2">不通过</option>
                        </select>
                </dd>
            </dl>

            <dl>
                <dt><span style="color: red;">*</span>身份证正面照：</dt>
                <dd>
                    <img src="${user.idCardFrontView}">
                </dd>
            </dl>
            <dl>
                <dt><span style="color: red;">*</span>身份证反面照：</dt>
                <dd>
                    <%--<img src="${user.idCardFrontView}"> --%>
                    <img src="img/1.jpg" height="40" width="100">
                </dd>
            </dl>

            <dl>
                <dt><span style="color: red;">*</span>银行卡照片：</dt>
                <dd>
                    <%--<img src="${user.idCardFrontView}"> --%>
                    <img src="img/1.jpg" height="40" width="100">
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
        <input type="hidden" name="userId" value="${userInfo.userId}"/>
    </form>
</div>
<script>
    var isVal=true;
    function searchAjaxName(){
        var loginName=$('#loginName').val();
        if(loginName!='${user.loginName}'){
            $.ajax({
                type:'post',//可选get
                url:'user/searchAjaxName',//这里是接收数据的PHP程序
                data:'loginName='+loginName,//传给PHP的数据，多个参数用&连接
                dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
                success:function(msg){
                    if(msg=='fail'){
                        isVal=false;
                        $('#fali_show').show();
                    }else{
                        isVal=true;
                        $('#fali_show').hide();

                    }
                },
                error:function(){
                }
            });
        }else{
            isVal=true;
            $('#fali_show').hide();
        }

    }

    function formSubmit(){
        if(isVal==false){
            $('#fali_show').show();
            return false;
        }else{
            if(check())
                $('#forms').submit();
        }

    }

    $("#userType").change(function () {
        var userType = $("#userType").val();
        if(userType==3|userType==4){
            $("#parentCodeDl").css("display","");
        }else {
            $("#parentCodeDl").css("display","none");
        }
    });
</script>