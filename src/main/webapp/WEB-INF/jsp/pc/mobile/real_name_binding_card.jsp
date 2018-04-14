<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>实名绑卡</title>
</head>
<%@include file="base.jsp" %>
<ry:binding parentCode="BANK_NAME" bingdingName="BANK_NAME"></ry:binding>
<body>
<div data-role="page" id="real_name_binding_card">
    <div data-role="header" style="border-width: 0; background: #28292E;">
        <a data-role="button" data-rel="back" href="/mobile/stock/center"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">实名绑卡</h4>
    </div>
    <div data-role="content">
        <p style="margin: 1em;" class="color-w size-9">实名信息提交后不可修改，请务必填写真实资料，并保证与银行卡预留个人信息一致</p>
        <form id="form" action="mobile/realname/bindingcard" data-ajax="false" method="post">
            <input type="hidden" name="userId" value="${userInfo.userId}">
            <div>
                <h4 class="fl">姓名</h4>
                <input style="margin-left: 1em;" data-role="none" class="fl color-w size-9" type="text"
                       <c:if test="${userInfo.userName != null}">readonly="readonly"</c:if> placeholder="请输入您的真实姓名"
                       name="userName" id="userName" value="${userInfo.userName}" required maxlength="6"/>
                <%--<input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="number" placeholder="输入提现金额" />--%>
            </div>

            <div>
                <h4 class="fl">身份证号码</h4>
                <c:if test="${userInfo.idNumber == null}">
                    <input style="margin-left: 1em;" data-role="none" class="fl color-w size-9" type="text"
                           placeholder="请输入您的身份证号" name="idNumber" id="idNumber" value="${userInfo.idNumber}" required maxlength="18"
                           minlength="18">
                </c:if>
                <c:if test="${userInfo.idNumber != null}">
                    <input style="margin-left: 1em;" readonly="readonly" data-role="none" class="fl color-w size-9"
                           type="text" placeholder="请输入您的身份证号" name="idNumber" value="${userInfo.idNumber}" required
                           maxlength="18" minlength="18">
                </c:if>
                <%--<input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="text" placeholder="输入提现金额" />--%>
            </div>
            <c:if test="${userInfo.idNumber == null}">
                <div id="id_card_front">
                    <h4 class="fl">身份证正面照片：</h4>
                    <span class="fr" style="margin-right: 22px">
							<img src="${constants.QINIU_USER_IMGURL}${userInfo.idCardFrontView}" class="headeait"
                                 width="200px" height="80px">
						</span>
                    <input type="file" class="filebtn" data-role="none" style="display: none;"
                           onchange="readFile1(this,'#id_card_front','idCardFrontView')" placeholder="请选择身份证正面照片"
                           required/>
                    <input type="hidden" style="display: none;" name="idCardFrontView" id="idCardFrontView"
                           value="${userInfo.idCardFrontView}"/>
                </div>
                <div id="id_card">
                    <h4 class="fl">身份证反面照片：</h4>
                    <span class="fr" style="margin-right: 22px">
						<img src="${constants.QINIU_USER_IMGURL}${userInfo.idCardBackView}" class="headeait"
                             width="200px" height="80px">
				    </span>
                    <input type="file" class="filebtn" data-role="none" style="display: none;"
                           onchange="readFile1(this,'#id_card','idCardBackView')" placeholder="请选择身份证正面照片"/>
                    <input type="hidden" style="display: none;" name="idCardBackView" id="idCardBackView"
                           value="${userInfo.idCardBackView}"/>
                </div>
            </c:if>
            <c:if test="${userInfo.idNumber != null}">
                <div>
                    <h4 class="fl">是否实名</h4>
                    <input style="margin-left: 1em;" data-role="none" class="fl color-w size-9" type="text"
                           readonly="readonly" name="realName" value="已实名，不能再实名"/>
                </div>
                <input type="hidden" name="idCardFrontView"  value="${userInfo.idCardFrontView}" />
                <input type="hidden" name="idCardBackView" value="${userInfo.idCardBackView}" />
            </c:if>

            <div style="margin-top: 1.5em;">
                <h4 class="fl">银行卡卡号</h4>
                <input style="margin-left: 1.2em;" data-role="none" class="fl color-w size-9" type="number"
                       oninput="if(value.length >19)value=value.slice(0,19)" placeholder="请输入您的银行卡号"
                       name="bankCardNumber" value="${userInfo.bankCardNumber}" required>
                <%--<input style="margin-left: 1em;" data-role = "none" class="fl color-w size-9" type="number" placeholder="输入提现金额" />--%>
            </div>
            <div>
                <h4 class="fl">所属银行</h4>
                <select name="bankId" data-role="none">
                    <option value="">请选择银行</option>
                    <c:forEach var="item" items="${BANK_NAME}">
                        <option value="${item.itemName}" <c:if test="${item.itemName == userInfo.bankId}">selected</c:if>>${item.itemName}</option>
                    </c:forEach>

                </select>

            </div>
            <div style="margin-top: 1.5em;">
                <h4 class="fl">开户行</h4>
                <input style="margin-left: 1.2em;" data-role="none" class="fl color-w size-9" type="text" placeholder="请输入开户行"
                       name="depositBank" value="${userInfo.depositBank}" required>
            </div>

            <div id="back_card" style="margin-bottom: 50px;">
                <h4 class="fl">银行卡照片</h4>
                <span class="fr" style="margin-right: 22px">
						<img src="${constants.QINIU_USER_IMGURL}${userInfo.backCardPhoto}" class="headeait"
                             width="200px" height="80px">
					</span>
                <input type="file" class="filebtn" data-role="none" style="display: none;"
                       onchange="readFile1(this,'#back_card','backCardPhoto')" placeholder="请选择您的银行卡照片"/>
                <input type="hidden" style="display: none;" name="backCardPhoto" id="backCardPhoto"
                       value="${userInfo.backCardPhoto}"/>
            </div>
            <a onclick="subAuthencation(this)" data-ajax="false" class="ui-btn login" style="background-color: #c33f00!important;">确定</a>
            <input id="idCardFontDefaultImgPath" type="hidden" name="path" value="img/id_card_font_default.png">
            <input id="idCardBackDefaultImgPath" type="hidden" name="path" value="img/id_card_back_default.png">
            <input id="bankCardDefaultImgPath" type="hidden" name="path" value="img/bank_card_default.png">
        </form>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            var idCardFontDefaultImgPath = $("#idCardFontDefaultImgPath").val();
            var imgSrc1 = $("#id_card_front .headeait").attr("src");
            if(imgSrc1 == "${constants.QINIU_USER_IMGURL}") {
                $("#id_card_front .headeait").attr("src",idCardFontDefaultImgPath);
            }

            var idCardBackDefaultImgPath = $("#idCardBackDefaultImgPath").val();
            var imgSrc2 = $("#id_card .headeait").attr("src");
            if(imgSrc2 == "${constants.QINIU_USER_IMGURL}") {
                $("#id_card .headeait").attr("src",idCardBackDefaultImgPath);
            }

            var bankCardDefaultImgPath = $("#bankCardDefaultImgPath").val();
            var imgSrc3 = $("#back_card .headeait").attr("src");
            if(imgSrc3 == "${constants.QINIU_USER_IMGURL}") {
                $("#back_card .headeait").attr("src",bankCardDefaultImgPath);
            }
        });

        function subAuthencation(obj) {
            $("#form").submit();

        }


        function clickFile(obj) { //点击触发input file
            $(obj).parent().find(".file").click();
        }

        function qrFile(obj) {//选中图片时的判断
            var file = obj.files[0];
            //判断类型是不是图片
            if (!/image\/\w+/.test(file.type)) {
                alert("请确保文件为图像类型");
                return false;
            }
            var fileName = "";
            if (typeof(fileName) != "undefined") {
                fileName = $(obj).val().split("\\").pop();
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
            $(obj).parent().find(".img-name").text(fileName);
        }
    </script>
    <script type="text/javascript">
        //身份证正面照
        $("#id_card_front .headeait").click(function () {
            filebtn1();
        });

        function filebtn1() {
            $("#id_card_front .filebtn").click();
        }

        //身份证反面照
        $("#id_card .headeait").click(function () {
            filebtn2();
        });

        function filebtn2() {
            $("#id_card .filebtn").click();
        }

        //银行卡正面照
        $("#back_card .headeait").click(function () {
            filebtn3();
        });

        function filebtn3() {
            $("#back_card .filebtn").click();
        }

        function readFile1(obj, id, string) {
            var file = obj.files[0];
            if(!/image\/\w+/.test(file.type)){
                alert("请确保文件为图像类型");
                return false;
            }
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                var imgBase64Data = encodeURIComponent(e.target.result);
                $(id + " .headeait").attr("src", this.result);
                var res = (this.result);
                var pos = imgBase64Data.indexOf("4") + 4;
                imgBase64Data = imgBase64Data.substring(pos);
                $('#' + string).val(imgBase64Data);
                //			var formData = $("imgBase64Data").serialize();
                //			formData =decodeURIComponent(formData,true);
                var result = new MyJqueryAjax('mobile/stock/update/authentication', 'imgBase64Data=' + imgBase64Data, null, 'text').request();
                $('#' + string).val(result);
                // alert("上传成功");
            }
        }

        //换头像end
    </script>
</div>
</body>
</html>
