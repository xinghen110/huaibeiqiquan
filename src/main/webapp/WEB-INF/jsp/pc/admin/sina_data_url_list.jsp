<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="goodsinfo/list">
    <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
    <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
    <input type="hidden" name="orderField" value="${param.orderField}" />
    <!-- 分页时 带模糊查询的值 -->
    <input type="hidden" name="goodsName" value="${param.goodsName}" />
    <input type="hidden" name="isHome" value="${param.isHome}" />
    <input type="hidden" name="goodsStatus" value="${param.goodsStatus}" />

</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="goodsinfo/list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>商品名称：</label>
                    <input type="text" name="goodsName" value="${bean.goodsName }">
                </li>
                <li>
                    <label>上架下架：</label>
                    <select name="goodsStatus">
                        <option value="" >请选择</option>
                        <option value="1" <c:if test="${bean.goodsStatus == 1}">selected="selected"</c:if>>上架</option>
                        <option value="2" <c:if test="${bean.goodsStatus == 2}">selected="selected"</c:if>>下架</option>
                    </select>
                </li>
                <li style="width: 245px;">
                    <label style="width: 100px;">是否首页展示：</label>
                    <select name="isHome">
                        <option value="" >请选择</option>
                        <option value="1" <c:if test="${bean.isHome == 1}">selected="selected"</c:if>>是</option>
                        <option value="2" <c:if test="${bean.isHome == 2}">selected="selected"</c:if>>否</option>
                    </select>
                </li>
            </ul>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <ry:authorize ifAnyGranted ="${authMap.SHOP_AUTH}" >
                <li><a class="add" onclick="add('goodsinfo/toEdit','添加商品信息',900,550,'main_')" ><span>添加</span></a></li>
                <li><a title="确定要上架吗?" target="selectedTodo" rel="ids" postType="string" href="goodsinfo/isStatus?type=1" class="edit"><span>上架</span></a></li>
                <li><a title="确定要下架吗?" target="selectedTodo" rel="ids" postType="string" href="goodsinfo/isStatus?type=2" class="icon"><span>下架</span></a></li>
                <li><a title="确定首页展示吗?" target="selectedTodo" rel="ids" postType="string" href="goodsinfo/ishomeshow?type=1" class="edit"><span>首页展示</span></a></li>
                <li><a title="确定首页关闭吗?" target="selectedTodo" rel="ids" postType="string" href="goodsinfo/ishomeshow?type=2" class="icon"><span>首页关闭</span></a></li>
                <li><a title="确定新品展示吗?" target="selectedTodo" rel="ids" postType="string" href="goodsinfo/isnewhow?type=1" class="edit"><span>新品展示</span></a></li>
                <li><a title="确定新品关闭吗?" target="selectedTodo" rel="ids" postType="string" href="goodsinfo/isnewhow?type=2" class="icon"><span>新品关闭</span></a></li>
            </ry:authorize>
        </ul>
    </div>
</div>
<table class="table" width="100%" layoutH="133">
    <thead>
    <tr>
        <th width="center" align="center">序号</th>
        <th width="center" align="center">URL</th>
        <th width="center" align="center">备注 </th>
        <th width="center" align="center">操作 </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${dataList.result}" varStatus="row">
        <tr>
            <td>${item.id}</td>
            <td>${item.url}</td>
            <td>${item.remark}</td>
            <td>
                <a href="web/sina/data/request/params">查看参数信息</a>
                <a href="web/sina/data/return/params">查看返回参数</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

