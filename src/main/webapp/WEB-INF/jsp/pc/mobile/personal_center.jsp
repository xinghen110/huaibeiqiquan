<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人中心</title>
</head>
<%@include file="base.jsp"%>
<body>
<div data-role="page" id="personal-center">
    <div data-role = "content">
        <ul data-role = "listview" class="personal_center_list">
            <li data-icon="false">
                <c:if test="${(empty userInfo or empty userInfo.userName)}">
                    <a href="#pagetwo" data-rel="dialog" data-transition="pop" style="padding-left: 5.9em;margin-bottom: 0;border-bottom: 1px solid rgba(255,255,255,.3);">
                        <img class="user-tx" src="img/mobile/img_ph_default.png">
                        <h4 style="margin-top: .5em;" class="color-w size-11">
                            <span>未实名</span>
                            <img class="fr" style="position: absolute;right: 1em;top:1.8em;" width="18px" src="img/mobile/icon_arrow_right.png" />
                        </h4>
                        <h4 style="margin-top: .3em;" class="color-hs size-10">
                            <span>${sessionScope.systemUser.loginName}</span>
                        </h4>
                    </a>
                </c:if>
                <c:if test="${!(empty userInfo or empty userInfo.userName)}">
                    <a data-ajax="false" href="mobile/realname/bandingcard/success"style="padding-left: 5.9em;margin-bottom: 0;border-bottom: 1px solid rgba(255,255,255,.3);">
                        <img class="user-tx" src="img/mobile/img_ph_default.png">
                        <h4 style="margin-top: .5em;" class="color-w">
                            <span class="fl size-13">${userInfo.userName}</span>
                            <span class="fl" style="color: #FF0000;border: 1px solid;border-radius:20px;font-size: .8em; padding: 1px 5px; margin-left: 5px; margin-top: 2px;">已认证</span>
                            <img class="fr" style="position: absolute;right: 1em;top:1.8em;" width="18px" src="img/mobile/icon_arrow_right.png" />
                        </h4>
                        <h4 style="margin-top: .3em;" class="color-hs size-10">
                            <span>${sessionScope.systemUser.loginName}</span>
                        </h4>
                    </a>
                </c:if>

            </li>
            <li data-icon="false" style="margin-top: 0em;border: none;background: #222222;">
                <h4 class="color-w size-9" style="">账号余额：<span class="color-red">${userAccount.money}</span>元</h4>

            </li>

            <li data-icon="false" style="margin-top: .5em;">
                <a data-ajax="false" href="mobile/toPay">
                    <img src="img/mobile/icon_user_rules.png" class="ui-li-icon"><h4 class="color-w">扫码支付<img class="fr" width="18px" src="img/mobile/icon_arrow_right.png" /></h4>
                </a>
            </li>

            <li data-icon="false" style="margin-top: .5em;">
                <a data-ajax="false" href="mobile/rule/center">
                    <img src="img/mobile/icon_user_rules.png" class="ui-li-icon"><h4 class="color-w">资金明细<img class="fr" width="18px" src="img/mobile/icon_arrow_right.png" /></h4>
                </a>
            </li>

            <li data-icon="false" style="margin-top: .5em;">
                <a data-ajax="false" href="mobile/realname/bindingcard">
                    <img src="img/mobile/icon_user_realname.png" class="ui-li-icon"><h4 class="color-w">实名绑卡<img class="fr" width="18px" src="img/mobile/icon_arrow_right.png" /></h4>
                </a>
            </li>
            <li data-icon="false">
                <a data-ajax="false" href="mobile/stock/password/update">
                    <img src="img/mobile/icon_user_edit.png" class="ui-li-icon"><h4 class="color-w">修改密码<img class="fr" width="18px" src="img/mobile/icon_arrow_right.png" /></h4>
                </a>
            </li>
            <li data-icon="false" style="margin-top: .5em;">
                <a data-ajax="false" href="mobile/stock/logout">
                    <img src="img/mobile/icon_user_logout.png" class="ui-li-icon"><h4 class="color-w">退出登录<img class="fr" width="18px" src="img/mobile/icon_arrow_right.png" /></h4>
                </a>
            </li>
        </ul>

        <!-- 底部 -->
        <div class="footer">
            <div class="fl">
                <a href="mobile/option/labels" data-ajax = "false">
                    <img src="img/mobile/tab_select_white.png" />
                    <h4 class="color-w size-9">自选股</h4>
                </a>
            </div>
            <div class="fl">
                <a href="mobile/stock/plan/list?orderStatus=1" data-ajax = "false">
                    <img src="img/mobile/tab_select_white.png" />
                    <h4 class="color-w size-9">持仓</h4>
                </a>
            </div>
            <div class="fl">
                <a href="mobile/stock/userinfo" data-ajax = "false">
                    <img src="img/mobile/tab_my_red.png" />
                    <h4 class="color-red size-9">我的</h4>
                </a>
            </div>
        </div>
    </div>
</div>
<div data-role="page" id="pagetwo" data-overlay-theme="b" style="margin-top: 100px">
    <div data-role="header" data-theme="a">
        <h1>提示对话框</h1>
    </div>

    <div data-role="content" data-theme="a">
        <p style="margin: 30px 18%; color: red">身份未实名，请先实名认证</p>
        <a href="#personal-center">返回个人中心</a>
    </div>

    <div data-role="footer" data-theme="a">
        <h1></h1>
    </div>
</div>
</body>
</html>

