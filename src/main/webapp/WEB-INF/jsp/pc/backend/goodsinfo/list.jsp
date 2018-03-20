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
			<ry:authorize ifAnyGranted ="${authMap.SHOP_AUTH}" >
		  	 <th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
		   </ry:authorize>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">商品名称</th>
			<th width="center" align="center">商城价 </th>
			<th width="center" align="center">月销量 </th>
			<th width="center" align="center">状态 </th>
			<th width="center" align="center">是否首页展示</th>
			<th width="center" align="center">是否最新商品</th>
			<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH},${authMap.SYSATEM_AUTH}" ><th width="center" align="center">店铺名称</th></ry:authorize>
			<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" ><th width="center" align="center">操作</th></ry:authorize>
		
		</tr>
	</thead>
	 <tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
			
				<ry:authorize ifAnyGranted ="${authMap.SHOP_AUTH}" >
				<td><input name="ids" value="${item.goodsId }" type="checkbox"></td></ry:authorize>
				<input type="hidden" name="goodsId" value="${item.goodsId }"/>
				 <td>${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td> 
				<td style="text-align: left;"><a href="javascript:void(0)" onclick="add('goodsinfo/toDetailsEdit?goodsNum=${item.goodsNum }&shopNum=${item.shopNum }','商品详情',900,600,'main_')" ><span style="color: blue;">${item.goodsName }</span></a></td>
				<td>${item.salePrice }</td>
				<td>${item.monthSaleCount }</td>
				<td>
				
					<ry:authorize ifAllGranted="${authMap.SHOP_AUTH}">
					<c:if test="${item.goodsStatus ==1}">
						<a href="javascript:void(0)" onclick="makeTop(2,'${item.goodsNum}','goods_status','goodsinfo/isStatus1','t_goods_info','goods_num')"><span style="color: green">上架</span></a>
					</c:if>
					<c:if test="${item.goodsStatus ==2}">
						<a href="javascript:void(0)" onclick="makeTop(1,'${item.goodsNum}','goods_status','goodsinfo/isStatus1','t_goods_info','goods_num')"><span style="color: red">下架</span></a>
					</c:if>
					</ry:authorize>
					<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH},${authMap.SYSATEM_AUTH}">
					${item.goodsStatus ==1? '上架' : '下架' }
					</ry:authorize>
				
				</td>
				<td>
				
					<ry:authorize ifAllGranted="${authMap.SHOP_AUTH}">
					<c:if test="${item.isHome ==1}">
						<a href="javascript:void(0)" onclick="makeTop(2,'${item.goodsNum}','is_home','goodsinfo/ishomeshow1','t_goods_info','goods_num')"><span style="color: green">是</span></a>
					</c:if>
					<c:if test="${item.isHome ==2}">
						<a href="javascript:void(0)" onclick="makeTop(1,'${item.goodsNum}','is_home','goodsinfo/ishomeshow1','t_goods_info','goods_num')"><span style="color: red">否</span></a>
					</c:if>
					</ry:authorize>
					<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH},${authMap.SYSATEM_AUTH}">
					${item.isHome ==1? '是' : '否'}
					</ry:authorize>
				
				</td>
				<td>
					<ry:authorize ifAllGranted="${authMap.SHOP_AUTH}">
					<c:if test="${item.isNew ==1}">
						<a href="javascript:void(0)" onclick="makeTop(2,'${item.goodsNum}','is_new','goodsinfo/isnewhow1','t_goods_info','goods_num')"><span style="color: green">是</span></a>
					</c:if>
					<c:if test="${item.isNew ==2}">
						<a href="javascript:void(0)" onclick="makeTop(1,'${item.goodsNum}','is_new','goodsinfo/isnewhow1','t_goods_info','goods_num')"><span style="color: red">否</span></a>
					</c:if>
					</ry:authorize>
					<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH},${authMap.SYSATEM_AUTH}">
						${item.isNew ==1? '是' : '否'}
					</ry:authorize>
				
				</td>
				<ry:authorize ifNotGranted="${authMap.SHOP_AUTH}">
					<td>${item.shopInfo.shopName}</td>
				</ry:authorize>
				
				<ry:authorize ifAnyGranted="${authMap.SHOP_AUTH}" >
				<td>
					<c:if test="${item.goodsStatus !=1}">
						<a title="修改商品"   onclick="add('goodsinfo/toEdit?goodsNum=${item.goodsNum}','修改商品',900,550,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
						<a title="您确定删除商品吗？" target="ajaxTodo" href="goodsinfo/del?goodsNum=${item.goodsNum}" class="btnDel">删除</a>
					</c:if>
				</td></ry:authorize>
				
				</tr>
		</c:forEach>
	 </tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

