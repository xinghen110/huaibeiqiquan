<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<ry:binding parentCode="MEMBER_LEVAL" bingdingName="memberLevel"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="shopinfo/list?shopStatus=${bean.shopStatus}&type=${type}">
     <input type="hidden" name="pageNum" value="${pageList.pageNum }" />
     <input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
     <input type="hidden" name="orderField" value="${param.orderField}" /> 
     <!-- 分页时 带模糊查询的值 -->
     <input type="hidden" name="shopName" value="${param.shopName}" />
     <input type="hidden" name="shopType" value="${param.Type}" />
	<input type="hidden" name="memberLevel" value="${param.memberLevel}">
      <input type="hidden" name="startTime" value='<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>' /> 
       <input type="hidden" name="createTime" value='<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>' /> 
     
</form>


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" action="shopinfo/list?shopStatus=${bean.shopStatus}&type=${type}" method="post">
<div class="searchBar">
<ul class="searchContent">
	<li>
		<label>名称：</label>
		<input type="text" name="shopName" value="${bean.shopName }"> 
	</li>
	<li>
		<label>类型：</label>
		<select name="shopType">
				<option value="" selected="selected">请选择</option>
				<option value="1" <c:if test="${bean.shopType == '1'}">selected="selected"</c:if>>商家</option>
				<option value="2" <c:if test="${bean.shopType == '2'}">selected="selected"</c:if>>技师</option>
		</select>
	</li>
	<c:if test="${bean.shopStatus==1}">
		<li>
			<label>会员等级：</label>
			<select name="memberLevel"  >
				<option value="">全部等级</option>
				<c:forEach items="${memberLevel}" var="items">
					<option value="${items.itemCode}" <c:if test="${items.itemCode==bean.memberLevel}">selected</c:if>>${items.itemName}</option>
				</c:forEach>
			</select>
		</li>
	</c:if>
	<li style="width: 370px">
		<label>申请时间：</label>
			<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${bean.startTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createTime\')}'})"/>
			~
			<input id="createTime" style="width: 110px" type="text" name="createTime" value="<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
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
			<c:if test="${bean.shopStatus==1}">
				<li><a class="add" onclick="window.open('shopinfo/registered','newwindow','height=700,width=470')" title="添加商家或技师" href="javascript:void(0)"><span>添加</span></a></li>
				<li><a title="确实要批量变更推荐状态吗？" target="selectedTodo" rel="ids" postType="string" href="shopinfo/update" class="edit"><span>批量变更</span></a></li>
			</c:if>
		</ul>
	</div>
	</div>
<table class="table" width="100%" layoutH="131">
	<thead>
		<tr>
			<c:if test="${bean.shopStatus==1}">
				<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
			</c:if>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">名称<c:if test="${bean.shopStatus==1}">（点击查看技师）</c:if></th>
			<th width="center" align="center">类型</th>
			<th width="center" align="center">服务类型</th>
			<c:if test="${bean.shopStatus==1}">
				<th width="center" align="center">推荐状态</th>
				<th width="center" align="center">合作状态</th>
				<th width="center" align="center">会员等级</th>
			</c:if>
			<th width="center" align="center">申请时间</th>
			<th width="center" align="center">操作 </th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<c:if test="${bean.shopStatus==1}">
					<td><input name="ids" value="${item.shopNum}" type="checkbox"></td>
				</c:if>
				<td>
				<input type="hidden" name="shopId" value="${item.shopInfoId}">
				${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>
					<c:if test="${item.shopStatus==2}">
						${item.shopName}
					</c:if>
					<c:if test="${item.shopStatus==1}">
						<c:if test="${item.shopType==1}">
							<a title="技师查看" href="javascript:void(0)"  onclick="add('shopinfo/jishi?shopInfoNum=${item.shopNum}','查看技师',900,600,'main_')"  rel="users_saveedit" ><span style="color: blue;">${item.shopName}</span></a>
						</c:if>
						<c:if test="${item.shopType==2}">
							${item.shopName}
						</c:if>
					</c:if>
				</td>
				<td>${item.shopType==1?'商家':'技师'}</td>
				<td>${item.typName}</td>
				<c:if test="${bean.shopStatus==1}">
					<td>
						<c:if test="${item.isRecommond == 1}">已推荐</c:if>
						<c:if test="${item.isRecommond == 2}">未推荐</c:if>
					</td>
					<td>
						<c:if test="${item.isHezuo == 1}"><a title="您确定要修改“${item.shopName}”的合作状态吗？" target="ajaxTodo" href="shopinfo/updateIsHezuo?shopNum=${item.shopNum}&isHezuo=2"><span style="color: blue;">已合作</span></a></c:if>
						<c:if test="${item.isHezuo == 2}"><a title="您确定要修改“${item.shopName}”的合作状态吗？" target="ajaxTodo" href="shopinfo/updateIsHezuo?shopNum=${item.shopNum}&isHezuo=1"><span style="color: blue;">未合作</span></a></c:if>
					</td>
					<td><ry:show parentCode="MEMBER_LEVAL" itemCode="${item.memberLevel}"></ry:show></td>
				</c:if>
				<td><ry:formatDate date="${item.createTime}" toFmt="yyyy-MM-dd"></ry:formatDate></td>
				<td>
				<c:if test="${item.shopStatus==2}">
					<a title="审核"  href="javascript:void(0)" onclick="add('shopinfo/show?shopNum=${item.shopNum}','审核',900,600,'main_')"  rel="users_saveedit" ><span style="color: blue;">审核</span></a>
				</c:if>
				<c:if test="${item.shopStatus==1}">
					<c:if test="${item.status==1}">
						<a title="您确定要禁用“${item.shopName}”吗？" target="ajaxTodo" href="shopinfo/updateStatus?shopNum=${item.shopNum}&status=2"><span style="color: green">启用</span></a>
					</c:if>
					<c:if test="${item.status==2}">
						<a title="您确定要启用“${item.shopName}”吗？" target="ajaxTodo" href="shopinfo/updateStatus?shopNum=${item.shopNum}&status=1"><span style="color: red">禁用</span></a>
					</c:if>
					&nbsp;
					<a title="添加套餐" href="javascript:void(0)" onclick="window.open('shopinfo/add_packages?shopNum=${item.shopNum}','newwindow','height=700,width=470')" rel="users_saveedit"><span style="color: blue;">添加套餐</span></a>
				</c:if>
				</td>
				
				</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 分页 -->
<%@include file="/WEB-INF/jsp/inc/page.jsp"%>

