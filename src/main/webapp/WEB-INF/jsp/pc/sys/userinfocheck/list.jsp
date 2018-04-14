<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="USER_ACCOUNT_ORDER_STATUS" bingdingName="orderStatus"></ry:binding>
<ry:binding type="3"></ry:binding>
<form id="pagerForm"  method="post" action="userInfoCheck/list">
	<input type="hidden" name="pageNum" value="${pageList.pageNum }" />
	<input type="hidden" name="numPerPage" value="${pageList.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />

	<!-- 分页时 带模糊查询的值 -->
	<input type="hidden" name="loginName" value="${param.loginName}"/>
	<input type="hidden" name="userName" value="${param.userName}"/>
	<%--<input type="hidden" name="startTime" value='<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd"/>' />--%>
	<%--<input type="hidden" name="endDate" value='<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>' />--%>

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
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="userInfoCheck/list" method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li style="width: 250px"><label>上级归属：</label>
					<input name="belongName" id="belongName" type="text" readonly value="${param.belongName}" style="width:120px;"/>
					<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
					<input type="hidden" name="belongValue" id="belongValue" value="${param.belongValue}"/>
				</li>
				<li style="width: 200px">
					<label>登录名称：${params.userType}</label>
					<input style="width: 100px" type="text" name="loginName" value="${user.loginName }" />
				</li>
				<li style="width: 200px">
					<label>用户名：</label>
					<input style="width: 100px" type="text" name="userName" value="${param.userName}">
				</li>
				<li style="width: 280px;"><label>银行卡号：</label>
					<input type="number" style="width: 150px" name="bankCardNumber" value="${param.bankCardNumber}">
				</li>
			</ul>
			<ul class="searchContent">
				<li style="width: 220px">
					<label>审核状态：</label>
					<select id="checkResult" name="checkResult" style="width: 120px" >
						<option value="">--选择审核状态--</option>
						<option value="0">审核中</option>
						<option value="1">审核通过</option>
						<option value="2">审核未通过</option>
					</select>
				</li>
				<script>
                    $("#checkResult").val(${param.checkResult});
				</script>
			</ul>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
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
	<table class="table" width="100%" layoutH="166">
		<thead>
		<tr>
			<!--<th width="center" align="center"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
             -->
			<th width="center" align="center">序号</th>
			<th width="center" align="center">用户名</th>
			<th width="center" align="center">登录账号</th>
			<th width="center" align="center">身份证号码</th>
			<th width="center" align="center">上级归属</th>
			<th width="center" align="center">银行卡所属银行</th>
			<th width="center" align="center">银行卡号码</th>
			<th width="center" align="center">开户行</th>
			<%--<th width="center" align="center">银行卡照片</th>--%>
			<th width="center" align="center">银行卡审核状态</th>
			<th width="center" align="center">操作</th></tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${pageList.result}" varStatus="row">
			<tr>
				<td>
					<input type="hidden" name="id" value="${item.id}">
						${(pageList.pageNum-1)*pageList.numPerPage+row.count}</td>
				<td>${item.userName}</td>
				<td>${item.loginName}</td>
				<td>
						${item.idNumber}
				</td>
				<td>
						${item.parentUserLoginName}
				</td>
					<%--<td>--%>
					<%--<img src="${constants.QINIU_USER_IMGURL}${item.idCardFrontView}" class="headeait" width="20px" height="10px">--%>
					<%--</td>--%>
					<%--<td>${item.userName}</td>--%>
				<td>${item.bankId}</td>
				<td>${item.bankCardNumber}</td>
				<td>${item.depositBank}</td>
					<%--<td>${item.userName}</td>--%>
				<td>
					<c:choose>
						<c:when test="${item.checkResult == 0}">未审核</c:when>
						<c:when test="${item.checkResult == 1}">审核通过</c:when>
						<c:otherwise>审核不通过</c:otherwise>
					</c:choose>
						<%--${item.checkResult}--%>
				</td>
				<td>
					<a onclick="add('userInfoCheck/check/detail?id=${item.id}','详情',900,600,'main_')" rel="" class="btnLook">操作</a>
					<c:choose>
					<c:when test="${item.checkResult == 0}">
					<a onclick="add('userInfoCheck/check?id=${item.id}','详情',900,600,'main_')" rel="users_saveedit" class="btnEdit">操作</a></td>
				</c:when>
				<c:when test="${item.checkResult == 1}">
					<a href="javascript:void(0)" onclick="alert('已审核，不能重复审核')" rel="users_saveedit" class="btnEdit"></a></td>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0)" onclick="alert('已审核，不能重复审核')" rel="users_saveedit" class="btnEdit">操作</a></td>
				</c:otherwise>
				</c:choose>
				</td>
					<%--<ry:authorize ifAnyGranted="${authMap.SYSTEM_AUTH}">--%>
					<%--<td>--%>
					<%--<c:if test="${item.status==2}">--%>
					<%--<a title="修改提现信息" style="cursor: pointer;" onclick="add('userapplication/toEdit?userApplicationNum=${item.userApplicationNum}','修改提现信息',900,550,'main_')"--%>
					<%--rel="users_saveedit"><span style="color: blue;">提现处理</span> </a>--%>
					<%--</c:if>--%>
					<%--</td>--%>
					<%--</ry:authorize>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<%@include file="/WEB-INF/jsp/inc/page.jsp" %>
</div>
<div id="menuContent" class="menuContent" style="height:200px;overflow:auto;display:none; position: absolute;left: 73px!important;    top: 36px!important;">
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
//            console.log(data[i]+"--"+pId+"--"+(data[i].pId == pId));
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


