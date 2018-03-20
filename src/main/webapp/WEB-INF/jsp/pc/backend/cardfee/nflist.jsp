<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>

<table class="table" width="100%" layoutH="135">
	<thead>
		<tr>
		   
			<th width="center" align="center">序号</th>
			
			<c:if test="${bean.cardType==1}">
			<th width="center" align="center">会员卡等级</th>
			<th width="center" align="center">年费金额</th>
			</c:if>
			<c:if test="${bean.cardType==2}">
			<th width="center" align="center">分销等级</th>
			<th width="center" align="center">分销比例</th>
			</c:if>
			<th width="center" align="center">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}" varStatus="row">
			<tr>
				
				<td>
				<input type="hidden" name="cardFeeNum" value="${item.cardFeeNum}">
				${row.count}</td>
				<td >
				<c:if test="${item.cardType==1}"><ry:show parentCode="MEMBER_LEVAL" itemCode="${item.memberLevel}"></ry:show></c:if>
				<c:if test="${item.cardType==2}"><ry:show parentCode="FENXIAO" itemCode="${item.memberLevel}"></ry:show>级</c:if>
				</td>
				<td>${item.cardFee}</td>
				<td>
					<a title="修改"   onclick="add('cardfee/edit?cardFeeNum=${item.cardFeeNum}','修改',500,200,'main_')"  rel="users_saveedit" class="btnEdit">修改</a>				
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
