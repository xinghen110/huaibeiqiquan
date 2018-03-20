<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>
 <ry:binding type="2"></ry:binding>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
    
<div class="pageContent">

<div class="pageFormContent nowrap" layoutH="57">


<dl style="width: 750px;" >
	<dt>商品名称：</dt>
	<dd style="width: 550px;">${bean.goodsName }</dd>
</dl>

<dl style="width: 750px;" >	
	<dt>商品副标题：</dt>
	<dd style="width: 560px;">${bean.viteName }</dd>
</dl>

<!--<dl style="width: 750px;height: 40px;" >

<dt>商品类目：</dt>
	<dd style="width: 207px;">
		${goodsType.goodsTypeName }
	</dd>
	
 
</dl>


--><dl style="height: auto;width: 790px; min-height:0;padding:0;" id="goodsAttributes">

</dl>

<dl style="width: 750px;" >
	<dt>市场价：</dt>
	<dd>
		${bean.marketPrice}
	</dd>
	
	<dt style="width: 215px;">商城价：</dt>
	<dd>
		${bean.salePrice}
	</dd>
</dl>
 

<dl style="width: 750px;" >

	<dt>计量单位：</dt>
		<dd>
			${bean.unit }
		</dd>
		<dd style="margin-left: 167px;width: 240px">
		<input type="checkbox" name="isEssxsdh" value="1" <c:if test="${bean.isEssxsdh == 1}">checked="checked"</c:if> >是否24小时内到货
	 
		<input type="checkbox" style="margin-left: 24px" name="isQtkt" value="1" <c:if test="${bean.isQtkt == 1}">checked="checked"</c:if> >是否7天内可退
	</dd>
</dl>

<dl style="width: 810px;height: auto;">
		<dt>商品图片：</dt>
		<dd id="photoDD" style="width: 500px;height: auto;">
			<c:if test="${not empty attachInfoList}">
				<div style="height: 140px;">
					<ol >
						<c:forEach var="item" items="${attachInfoList}">
							<li style="float: left;margin-left: 5px;width: 100px; height: 140px;">
								<div><a href="${constants.QINIU_USER_IMGURL}${item.filePath }" target="_blank"><img id="mainphotoview" src="${constants.QINIU_USER_IMGURL}${item.filePath }" style="width: 100px; height: 100px;" /></a></div>
								<div style="width: 100%;text-align: center;padding-top: 3px;">
									<c:if test="${item.filePath == bean.mainPhoto}">
										<span>封面图片</span>
									</c:if>
									 
									
								</div>
							</li>
						</c:forEach>
					</ol>
				</div>
			</c:if>
			
			 
		</dd>
	</dl>
	 
	
<dl style="width: 750px;height: 200px;" >
	<dt>产品参数：</dt>
	<dd>
	
	<textarea  id="productDetail" name="productDetail"" title="产品参数" style="width: 400px;height: 200px;" >${bean.productDetail }</textarea>
		
	</dd>
</dl>
<dl style="width: 750px;height: 520px;" >
	<dt>产品详情：</dt>
	<dd><div id="myeditor"  style="width:600px;height: 400px;" title="产品详情"></div>
	<input type="hidden" id="graphicDetail" name="graphicDetail" title="产品详情" value='${bean.graphicDetail}' />
	</dd>
</dl>

<!--隐藏值-->
<input type="hidden" name="goodsId" value="${bean.goodsId }"  >
<input type="hidden" name="goodsNum" value="${bean.goodsNum }"  >
			

</div>

<div class="formBar">
<ul>
	<li>

	<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
</ul>
</div>

</form>
</div>
<script type="text/javascript">

var photoDiv = $('#photoDiv').html(); //获取上传图片模板，备用
//动态添加上传图片行
function addPhotoRows(){
	$('#photoDD').append('<div id="photoDiv">'+photoDiv+'</div>');
	setSelVal();
}
//动态删除上传图片行
function delPhotoRow(src){
	if(!checkPhoto()){
		alertMsg.warn("至少需要上传一张图片");
		return false;
	}
	$(src).parent().remove();
	setSelVal();
}
//校验商品上传上的图片至少有一张
function checkPhoto(){
	var photo1 = $('#photoDD').find('input:radio');
	var photo2 = $('#photoDD').find('li');
	var i=0;
	var j=0;
	if(photo1 != null){
		i = photo1.size();
	}
	if(photo2 != null){
		j = photo2.size();
	}
	//alert(i+'-'+j)
	if(i+j <= 0){
		return false;
	}
	return true;
}
//设置上传图片索引
function setSelVal(){
	$('#photoDD').find('input:radio').each(function(i){
		this.value=i;
	})
}
//设置封面图
function setMainPhoto(goodsNum,filePath){
	
	if(confirm('确认将此图片设置为封面图吗？')){
		var url = 'goodsinfo/updateMainPhoto?goodsNum='+goodsNum+'&mainPhoto='+filePath;
		var result = new MyJqueryAjax(url,null,null,'json').request();
		if(result == 1){
			alertMsg.correct('操作成功！');
			var dialog = $.pdialog.getCurrent();
			$.pdialog.reload(dialog.data("url"));
		}else{
			alertMsg.warn('操作失败！');
		}
	}
}
//删除图片
function delPhoto(attachId,src){
	if(confirm('确认将此图片删除吗？')){
		var url = 'goodsinfo/delPhoto?attachId='+attachId;
		var result = new MyJqueryAjax(url,null,null,'json').request();
		if(result == 1){
			alert('操作成功！');
			var dialog = $.pdialog.getCurrent();
			$.pdialog.reload(dialog.data("url"));
		}else{
			alert('操作失败！');
		}
	}
}

function getTypeNum(parentNum){
	getChild(parentNum);
	if(parentNum == ''){
		
		$("#typenum").html("<option value=''>请选择</option>");
		return;
	}
	$.ajax({
		type:'post',
		url:'goodsinfo/getGoodsTypeNum',
		data:'parentNum='+parentNum+'&shopNum=${user.shopNum}',
		dataType:'json',
		success:function(data){
		var html="<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			var status="";
			if('${bean.goodsTypeNum}'==data[i].goodsTypeNum){
				status="selected";
			}
			html+="<option "+status+" value="+data[i].goodsTypeNum+">"+data[i].goodsTypeName+"</option>";
			}
		$("#typenum").html(html);
			},
			error:function(){
			alert("没有信息");
			}
		});
}
//window.onload = getTypeNum('${bean.parentNum}');
$(document).ready(function() { 
	getTypeNum('${bean.parentNum}');
});

var ue=UE.getEditor("myeditor",{
	autoHeightEnabled: false});
	ue.ready(function() {
	ue.setContent($("#graphicDetail").val());
});


//商品属性
 function getChild(num){
	if(num == "" || num == null)
		num = $("#parentNum option:first").val();
	var goodsAttributeNums = '${goodsAttributeNums}';
     $.ajax({
    	 type:'post',//可选get
    	 url:'goodsinfo/getGoodsAttributeList',//这里是接收数据的PHP程序
    	 data:'goodsTypeNum='+num,//传给PHP的数据，多个参数用&连接
    	 dataType:'json',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
    	 success:function(result){
    	 var html = "";
    	$("#goodsAttributes").children().remove();//删除 子元素
		 for(var i =0;i<result.length;i++){
			 var mycheckedP = "";
				if(goodsAttributeNums.indexOf(result[i].goodsAttributeNum) != -1){
					mycheckedP = 'checked="checked"';
				}				
				html+='<dt><input id="parent['+i+']" type="checkbox" '+mycheckedP+' name="goodsAttributeNum" value="'+result[i].goodsAttributeNum+'">'+result[i].attributeName+'：</dt>'
					+'<dd style="width: 595px;height: auto;">'
				for(var j in result[i].childList){
					var mychecked = "";
					if(goodsAttributeNums.indexOf(result[i].childList[j].goodsAttributeNum) != -1){
						mychecked = 'checked="checked"';
					}
						html+='<input  type="checkbox" '+mychecked+' name="goodsAttributeNum" value="'+result[i].childList[j].goodsAttributeNum+'">'+result[i].childList[j].attributeName
				}
						html+='</dd>'
			}

    	 $('#goodsAttributes').append(html);
    	 },
    	 error:function(){
    	 }
    });
}//商品属性end

$(function (){
 	 	$("#goodsAttributes").on("click","dt :checkbox", function(){
 	 		$(this).parent().next().children(":checkbox").prop("checked",$(this).prop("checked"));
 	 	});
 	 	$("#goodsAttributes").on("click","dd :checkbox", function(){
 	 		var l=$(this).parent().children(":checked").size();
 	 		var pa=$(this).parent().prev().find(":checkbox");
 	 		pa.prop("checked",l>0);
 	 	 });
 });
	
function todo(){
	if(!checkPhoto()){
		alertMsg.warn("至少需要上传一张图片");
		return;
	}
	
	$("#graphicDetail").val(ue.getContent());

		if(check())
		$("#forms").submit();
}
</script>
