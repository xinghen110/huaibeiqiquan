<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<div class="pageContent">
<form method="post" action="goodsattribute/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">
<dl style="width: 750px;float: none;" >
<dt ><span style="color: red;">*</span>商品一级分类：</dt>
	<dd style="width: 320px">
	<select name="goodsTypeNum" title="商品一级分类" class="mustFill"   id="goodsTypenums" onchange="querygoodsTypeNumEdit(this.value)" style="width: 180px" <c:if test="${not empty bean.goodsAttributeId}">disabled</c:if>>
	</select>
	<input type="hidden"  id="goodsTypeNum_edit"   value="${bean.goodsTypeNum}" />
	</dd>
</dl>
<dl style="width: 750px;float: none;" >
<dt ><span style="color: red;">*</span>上级类目：</dt>
	<dd style="width: 320px">

		<select name="parentNum"   id="parentnums"    style="width: 180px" <c:if test="${not empty bean.goodsAttributeId}">disabled</c:if> >
			<option value="">无</option>
		</select>
	<input type="hidden"  id="parentNum_edit"  value="${bean.parentNum}" />
		
&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${ empty bean.goodsAttributeId}">顶级类目请选择"无"</c:if>
	</dd>
</dl>
<dl style="width: 750px;float: none;" >
	<dt><span style="color: red;">*</span>名称：</dt>
	<dd>
		<input type="text" name="attributeName" value="${bean.attributeName }" maxlength="20" class="mustFill" title="名称">
	</dd>
</dl>



<script>
var goodsTypeNum_edit=document.getElementById("goodsTypeNum_edit").value;
var parentNum_edit=document.getElementById("parentNum_edit").value;
querygoodTypeEdit();
function querygoodTypeEdit(){
	 
	$.ajax({
		type:'post',
		url:'goodstype/getgoodsType',
		dataType:'json',
		success:function(data){
			if(data!=null){
				var goodsType="<option value=''>请选择</option>";
				for(var i=0;i<data.length;i++){
					var status="";
					if(goodsTypeNum_edit==data[i].goodsTypeNum){
						status="selected";
						querygoodsTypeNumEdit(goodsTypeNum_edit,parentNum_edit);
					}
					goodsType+="<option "+status+" value="+data[i].goodsTypeNum+">"+data[i].goodsTypeName+"</option>";
					}
				$("#goodsTypenums").html(goodsType);
			}
			},
			error:function(){
			alert("没有信息");
			}
		});
}

function querygoodsTypeNumEdit(goodsTypeNum,parentNum){
	
	$.ajax({
		type:'post',
		url:'goodsattribute/getparentnum',
		data:'goodsTypeNum='+goodsTypeNum,
		dataType:'json',
		success:function(data){
		var parentNum="<option value=''>无</option>";
		for(var i=0;i<data.length;i++){
			var status="";
			if(parentNum_edit==data[i].goodsAttributeNum){
				status="selected";
				
			}
			parentNum+="<option "+status+" value=" +data[i].goodsAttributeNum +">"+data[i].attributeName+"</option>";
			
			}
			$("#parentnums").html(parentNum);
		
		},error:function(){
			alert("没有信息");
			}
		});
}



</script>

<dl style="width: 750px;" >
	<dt><span style="color: red;">*</span>排序：</dt>
	<dd>
		<input type="text" name="sortNum" value="${not empty bean.sortNum? bean.sortNum :(MaxSort+1) }" class="mustFill" title="等级">
	</dd>
</dl>
<!--隐藏值-->
<input type="hidden" name="goodsAttributeId" value="${bean.goodsAttributeId }"  >
<input type="hidden" name="attributeLevel" value="${bean.attributeLevel }"  >
<input type="hidden" name="goodsAttributeNum" value="${bean.goodsAttributeNum }"  >
</div>
<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">保存</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
</ul>
</div>
</form>
</div>
<script type="text/javascript">
function todo(){
	if(check()){
		$("#forms").submit();
	}
}
</script>
