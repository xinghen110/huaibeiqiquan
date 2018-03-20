<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp"%>
<body>
<%@include file="header_title.jsp"%>
<div>
    <img src="img/web/banner_ssjp.jpg" style="width: 100%;"/>
    <div style="width: 1280px;margin: 0 auto;">
        <div style="line-height: 25px;padding: 15px 0;border-bottom: 1px solid #ebebeb;margin-bottom: 20px;">
            <h4 class="size-11">${article.title}</h4>
            <h5 class="color-hs size-8">${article.createTime}</h5>
        </div>
        <p style="line-height: 35px;margin-bottom: 30px;text-indent: 2em;color: #646464;">${article.content}
        </p>
        <h3 class="choose size-9">上一篇：
            <c:choose>
            <c:when test="${empty preAndNext.pre[0].title}">没有上一篇</c:when>
                <c:otherwise><a href="web/news/detail?articleId=${preAndNext.pre[0].articleId}">${preAndNext.pre[0].title}</a></c:otherwise>
            </c:choose>

        </h3>
        <h3 class="choose size-9" style="margin-bottom: 100px;">下一篇：
            <c:choose>
                <c:when test="${empty preAndNext.next[0].title}">没有下一篇</c:when>
                <c:otherwise>
                    <a href="web/news/detail?articleId=${preAndNext.next[0].articleId}">${preAndNext.next[0].title}</a>
                </c:otherwise>
            </c:choose>
        </h3>
    </div>
</div>
<%@include file="footer.jsp" %>
<%--<div class="footer">--%>
    <%--<h4>版权所有 2017 金点护航 ICP备********号</h4>--%>
    <%--<h4>风险提示：保证金类交易时存在较大的亏损风险，这些产品不一定适合每一位投资者，请确保您完全了解所涉及的风险</h4>--%>
    <%--<h4>本站内容仅供参考，非操作建议，请您自觉决策交易。</h4>--%>
    <%--<img style="margin-top: 20px;" src="img/logo.png" />--%>
<%--</div>--%>
</div>
</body>
</html>


