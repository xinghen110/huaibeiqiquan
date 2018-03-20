<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/jsp/inc/pression.jsp"%>
<%@include file="/WEB-INF/jsp/inc/ume.jsp"%>

<div class="pageContent">
<form method="post" action="sensitiveWords/saveOrUpdate" id="forms" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);" enctype="multipart/form-data">
<div class="pageFormContent nowrap" layoutH="57">

<dl style="width: 750px;" >

			<dt><span style="color: red;">*</span>敏感词：</dt>
				<dd>
				
					<input type="text" id="wordsName" name="wordsName"  value="${bean.wordsName}" maxlength="11" size="30" class="mustFill" title="敏感词输入有误"  onblur="searchAjaxwordsName();" />
					<span id="fali_show" class="info" style="color: red;display:none;">敏感词已被使用，请重新输入</span>
			
				</dd>
</dl>
</div>
<input type="hidden" name="sensitiveWordsNum" value="${bean.sensitiveWordsNum }"></input>
<div class="formBar">
<ul>
	<li>
	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
	<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
</ul>
</div>

</form>
</div>
<script>
var flag = false;
function searchAjaxwordsName(){
	 var wordsName=$('#wordsName').val();
		 if(wordsName!=""){
		     $.ajax({
		    	 type:'post',//可选get
		    	 url:'sensitiveWords/searchAjaxwordsName',//这里是接收数据的PHP程序
		    	 data:'wordsName='+wordsName,
		    	 dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
		    	 success:function(msg){
		    	    if(msg=='fail'){
		    	    	alertMsg.warn("该敏感词已经存在！");
			    	    return false;
			    	}else{
			    		flag = true;
			    	}
		    	 },
		    	 error:function(){
		    	 }
		    });
	     }
	}
function todo(){
	
	if(flag==true){
		if(check()){
				$("#forms").submit();
			}
		}else{
			alertMsg.warn("该敏感词已经存在！");
		}
	
	}


</script>