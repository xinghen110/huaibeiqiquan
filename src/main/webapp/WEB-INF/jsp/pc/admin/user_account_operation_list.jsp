<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USER_ACCOUNT_ORDER_STATUS" bingdingName="orderStatus"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm" method="post" action="user/account/operation/list">
	<input type="hidden" name="pageNum" value="${pageList.pageNum }" />
	<input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />

	<!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="userName" value="${param.userName}"/>
	<input type="hidden" name="loginName" value="${param.loginName}"/>
	<input type="hidden" name="status" value="${param.status}"/>
	<%--<input type="hidden" name="startTime" value="${startTime}" />--%>
	<%--<input type="hidden" name="endDate" value="${endDate}" />--%>
    <input type="hidden" name="startTime" value='<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
    <input type="hidden" name="endDate" value='<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>' />

</form>

<script type="text/javascript">
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes(),
            v = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $("#citySel");
        cityObj.attr("value", v);
    }

    function showMenu() {
        var cityObj = $("#citySel");
        var cityOffset = $("#citySel").offset();
        $("#menuContent").css({left:76 + "px", top:36 + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    //    $(document).ready(function(){
    //        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    //    });
    //-->
</script>
<style>
	li {list-style: circle;font-size: 12px;}
	li.title {list-style: none;}
	ul.list {margin-left: 17px;}

	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
	ul.log {border: 1px solid #617775;background: #f0f6e4;width:300px;height:170px;overflow: hidden;}
	ul.log.small {height:45px;}
	ul.log li {color: #666666;list-style: none;padding-left: 10px;}
	ul.log li.dark {background-color: #E3E3E3;}


</style>
<style>
	.searchBar ul.searchContent li {
		float: left;
		display: block;
		overflow: hidden;
		width: 166px;
		height: 31px;
		padding: 2px 0;
	}
	select {
		height: 29px;
		border: 1px solid #A2BAC0;
		border-color: #A2BAC0 #B8D0D6 #B8D0D6 #A2BAC0;
		width: 90px;
	}
	.searchBar label {
		float: left;
		width:auto;
		padding: 0 5px;
		line-height: 23px;
		text-align: right;
	}
	.searchContent input{
		width: 65px;
	}
	.searchContent input[type='number']{
		padding: 2px;
		margin: 0;
		line-height: 20px;
		font-size: 12px;
		height: 22px;
		width: 125px;
		border-radius: 4px;
		border: 1px solid #A2BAC0;
		background-color: #FFF;
	}
</style>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="user/account/operation/list" method="post">
		<div class="searchBar">

			<ul class="searchContent">
				<li style="width: 250px"><label>上级归属：</label>
					<input name="belongName" id="belongName" type="text" readonly value="${param.belongName}" style="width:120px;"/>
					<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
					<input type="hidden" name="belongValue" id="belongValue" value="${param.belongValue}"/>
				</li>
				<li>
					<label>被申请人姓名：</label>
					<input type="text" name="userName" value="${userInfo.userName }" />
				</li>
				<li>
					<label>被申请人账户：</label>
					<input type="text" name="loginName" value="${param.loginName }" />
				</li>

			</ul>
			<ul class="searchContent">
				<li>
					<label>审核状态：</label>
					<select name="status" id="checkStatus">
						<option value="">请输入审核状态</option>
						<option value="0">申请中</option>
						<option value="1">申请通过</option>
						<option value="2">申请不通过</option>
					</select>
				</li>
				<script>
					$("#checkStatus").val(${param.status});
				</script>
				<li style="width: 370px">
					<label>冲正申请时间：</label>
					<input id="startTime" style="width: 110px" type="text" name="startTime" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
					~
					<input id="endTime" style="width: 110px" type="text" name="endTime" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
				</li>

			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>
</div>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<%--<li><a class="add" onclick="add('admin/user/add','添加信息',900,500,'main_')"><span>添加</span></a></li>--%>

			<!--<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="user/batchDelete" class="delete"><span>批量删除</span></a></li>
--><!--			<li class="line">line</li>-->
			<!--			<li><a class="icon" href="user/exportExcel" target="dwzExport" targetType="navTab" title="您确定导出这些记录吗?"><span>导出EXCEL</span></a></li>-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="168">
		<thead>
		<tr>
			<th width="center" align="center">序号</th>
			<th width="center" align="center">被申请人姓名</th>
			<th width="center" align="center">被申请人账号</th>
			<th width="center" align="center">申请操作的金额</th>
			<th width="center" align="center">申请人</th>
			<th width="center" align="center">申请创建时间</th>
			<th width="center" align="center">办理人</th>
			<th width="center" align="center">办理时间</th>
			<th width="center" align="center">状态</th>
			<th width="center" align="center">操作</th></tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
					<input type="hidden" name="id" value="${item.id}">
						${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>
						${item.applyedUserName}
				</td>
				<td>
						${item.loginName}
				</td>
				<td>${item.money}</td>
				<td>${item.applyUserName}</td>
				<td><fmt:formatDate value="${item.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>${item.handleUserName}</td>
				<td><fmt:formatDate value="${item.handleDateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>
					<c:choose>
						<c:when test="${item.status == 0}">申请中</c:when>
						<c:when test="${item.status == 1}">申请通过</c:when>
						<c:otherwise>申请不通过</c:otherwise>
					</c:choose>
						<%--${item.checkResult}--%>
				</td>
				<td>
					<c:choose>
					<c:when test="${item.status == 0}">
					<a onclick="add('user/account/operation/update?id=${item.id}','资金冲正申请',900,500,'main_')" rel="users_saveedit" class="btnEdit">操作</a></td>
				</c:when>
				<c:when test="${item.status == 1}">
					<a href="javascript:void(0)" onclick="alert('已审核，不能重复审核')" rel="users_saveedit" class="btnEdit">操作</a></td>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0)" onclick="alert('被拒绝，不能审核')" rel="users_saveedit" class="btnEdit">操作</a></td>
				</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
<div id="menuContent" class="menuContent" style="height: 300px;overflow: auto;display:none; position: absolute;left: 73px!important;    top: 36px!important;">
	<div  style="background: #ffffff;width: 160px" class="accordionContent" id="left"></div>
</div>
<script>
    function getTree(data,pId,isInit){
        var tree;
        if(isInit){
            var rootData = null;
            for(var i = 0;i<data.length; i++){
                if(data[i].id == pId){
                    rootData = data[i];
                    break;
                }
            }
            tree = '<ul class="tree treeFolder">';
            if(rootData != null)
                tree += "<a onclick='addInfo(this)' data-belongValue='"+rootData.id+"-"+rootData.userType+"'>"+rootData.name+"</a>";
        }else{
            tree = '<ul>';
        }
        for(var i in data){
            if(data[i].pId == pId){
                tree += "<li><a onclick='addInfo(this)' data-belongValue='"+data[i].id+"-"+data[i].userType+"'>"+data[i].name+"</a>";
                tree += getTree(data,data[i].id, false);
                tree += "</li>";
            }
        }
        return tree+"</ul>";
    }
    jQuery(function($) {
        var data = ${userList};
        var pId = 0;
        var systemUserType = ${systemUser.userType};
        if(systemUserType!=1){
            pId = ${systemUser.userId};
        }
        $("#left").html(getTree(data,pId,true).replaceAll("<ul></ul>",""));
    });

    function addInfo(obj) {
        var belongValue = $(obj).attr("data-belongValue");
        console.log($(obj).attr("data-belongValue"));
        $("#belongValue").val(belongValue);
        $("#belongName").val($(obj).html());
    }
</script>