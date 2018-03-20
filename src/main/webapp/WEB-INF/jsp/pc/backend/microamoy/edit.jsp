<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>

 
<div class="pageContent">
<form method="post" action="microamoy/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 800px;">
	<dt><span style="color: red;">*</span>标题 ：</dt>
	<dd>
		<input type="text" name="title" value="${bean.title }" style="width: 310px" title="广告名称" class="mustFill" maxlength="100" >
	</dd>
</dl>


<dl style="width: 810px;height: auto;">
			<dt >&nbsp;</dt>
			<dd style="width: 600px;height: auto;">
				<table class="list nowrap itemDetail" addButton="添加商品" style="width:500px;height: auto;"  >
					<thead>
						<tr>
							<th type="lookup" name="items[0].org.goodsName"  lookupGroup="items[0].org" drawable="true" lookupUrl="microamoy/getFindBack" title="ddd" fieldclass="required">商品名称</th>
							 <th type="text" name="items[0].org.goodsNum" >商品编号</th>
							<th type="del" >操作</th>
						</tr>
					</thead>
					<tbody id="goodsTGTbody">
						<c:forEach var="item" items="${bean.goodsMicroAmoyList }" varStatus="row">
							<tr class='unitBox'>
									 
								<td><input type="text"  disabled="disabled" class="mustFill required" title='商品名称' value="${item.goodsInfo.goodsName }"></td>
								<td><input type="text" disabled="disabled" class="mustFill" title="商品编号" name="${row.count}.goodsNum" value="${item.goodsNum }"></td>
								 
								<td><a onclick="del(this)" style="cursor:pointer;background: transparent url('dwz/themes/purple/images/button/imgX.gif') no-repeat scroll -23px 0px;width:22px;height:21px;display:inline-block;vertical-align: middle;margin-top:2px"></a></td>
							</tr>
						</c:forEach>
							
							<tr class='unitBox'>
								<td > 
									<input name="items[0].org.id" type="hidden" value=""/>
									<input type="text" disabled="disabled"  name="items[0].org.goodsName"  value="" size="30" class="mustFill required" title='商品名称'/>  
									<a class="btnLook" href="microamoy/getFindBack" lookupgroup="items[0].org" drawable="true" >选择商品</a>
									</td> 
								<td><input type="text" disabled="disabled" name="items[0].org.goodsNum"  class="mustFill" title="商品编号"   /> </td>
								 
								<td><a onclick="del(this)" style="cursor:pointer;background: transparent url('dwz/themes/purple/images/button/imgX.gif') no-repeat scroll -23px 0px;width:22px;height:21px;display:inline-block;vertical-align: middle;margin-top:2px"></a></td> 
							</tr>
					</tbody>
				</table>
				<input type="hidden" id="goodsNums" name="goodsNums">
			</dd>
		</dl>

<dl style="width: 800px;height: 530px;">
	<dt>内容：</dt>
	<dd><div id="myeditor"  style="width:600px;height: 400px;"></div>
	<input type="hidden" id="detailsInfo" name="detailsInfo" value='${bean.detailsInfo}' />
	</dd>
</dl>

</div>
		<input type="hidden" name="microAmoyId" value="${bean.microAmoyId }">
		<input type="hidden" name="microAmoyNum" value="${bean.microAmoyNum }">

<div class="formBar">
<ul>
	<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>

<script type="text/javascript">
	function del(obj){
		  var tr=obj.parentNode.parentNode;
		  tr.parentNode.removeChild(tr);
	};
	var ue=UE.getEditor("myeditor",{
		autoHeightEnabled: false
	});
	ue.ready(function() {
		ue.setContent($("#detailsInfo").val());
	});

	
function todo(){
	if($("tr[class='unitBox']").length > 0){//$('#goodsTGTbody').find('.unitBox').size()
		var flag = false;
		var flag2 = false;
		var goodsNums = [];
		var amountNum=new Array();
		$('#goodsTGTbody').find('.unitBox').each(function(i){
			for(var j in amountNum){
				if(amountNum[j]==$(this).find("input[name$='.goodsNum']").val())
					flag2= true;
			}
			if(i >= 8) flag = true;
			amountNum[i]=$(this).find("input[name$='.goodsNum']").val();
			goodsNums[i] = $(this).find("input[name$='.goodsNum']").val();
		});
		$('#goodsNums').val(goodsNums);
	}
	$("#detailsInfo").val(ue.getContent());
	if(flag)
		return alertMsg.info('最多添加8个商品！');
	if(flag2)
		return alertMsg.info('商品重复，请检查后重新提交');
	
	if(check())
		$("#forms").submit();
}
</script>
