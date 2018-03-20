<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>实名绑卡</title>
</head>
<%@include file="base.jsp" %>
<body>
<div data-role="page" id="capital_details">
    <div data-role="header" style="border-width: 0; background: #28292E;">
        <a data-role="button" data-rel="back"><img src="img/mobile/icon_nav_back.png"/></a>
        <h4 class="color-w">资金明细</h4>
    </div>
    <div data-role="content">
        <%--<div>
            <h4 class="title">
                <span onclick="title_select2(this)" class="title_select allCaptial">全部</span>
                <span onclick="title_select2(this,1)">充值</span>
                <span onclick="title_select2(this,2)">提现</span>

            </h4>
        </div>--%>
        <ul data-role="listview" id="recordList">

        </ul>



    </div>
</div>
<script>
    getUserRecord();


    //获取流水记录，并生成DOM,
    function getUserRecord() {


        $("#capital_details #recordList").empty();



        $.ajax({
            url: "mobile/getUserRecord",
            dataType: "json",
            type: "post",
            success: function (data) {
                var result = data.obj;
                var str = '';
                for (var i = 0; i < result.length; i++) {
                    str += '<li data-icon = "false"><h4 class="size-9">';

                    str += '<span class="color-w f1">' + (result[i].flowRemark) + '</span>';

                    str += '</h4> <h5 class="size-9"><span class="color-w">';
                    str += '<img src="img/mobile/sign_money.png" width="15px" />';
                    str += '<span class="color-orange">' + (result[i].money) + '</span>';
                    str += '</span> <span class="color-hs fr">' + (result[i].createTime) + '</span></h5></li>';
                }

                $("#capital_details #recordList").append(str).listview().listview("refresh");

            }
        });
    }

</script>

</body>
</html>
