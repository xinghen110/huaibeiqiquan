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
        <dl>
            <dt>页面名称：</dt>
            <dd>
                <select id="selectAdverDetail" name="flag1">
                    <option>请选择需要编辑的页面</option>
                    <option value="1">关于我们</option>
                    <option value="2">集团介绍</option>
                    <option value="3">投资保障</option>
                </select>
            </dd>
        </dl>
        <dl style="width: 750px;height: 520px;" >
            <dt>页面内容：</dt>
            <dd><div id="myeditor"  style="width:600px;height: 400px;" title="页面内容"></div>
                <input type="hidden" id="adverCount" name="adverCount" title="页面内容" value='${bean.graphicDetail}' />
            </dd>
        </dl>
        <%--<!--隐藏值-->--%>
        <%--<input type="hidden" name="goodsId" value="${bean.goodsId }"  >--%>
        <input id="adverInfoId" type="hidden" name="adverInfoId"  >


    </div>

    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button id="submit" type="button" class="submit">提交</button></div></div></li>

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


    //window.onload = getTypeNum('${bean.parentNum}');

    var ue=UE.getEditor("myeditor",{
        autoHeightEnabled: false});
    ue.addListener("blur",function(){
        var html=UE.getEditor('myeditor').getContent();
       $("#adverCount").val(html);
    })



    function todo(){
        if(!checkPhoto()){
            alertMsg.warn("至少需要上传一张图片");
            return;
        }

        $("#graphicDetail").val(ue.getContent());

        if(check())
            $("#forms").submit();
    }
    
    function selectAdverDetail() {
        var checkValue=$("#select_id").val();
        alert(checkValue);
    }
    $("#selectAdverDetail").change(function(){
        var checkValue=$("#selectAdverDetail").val();
        $.ajax({
            type:'post',
            url:'adverinfo/get/page/info',
            data:'flag1='+checkValue,
            dataType:'json',
            success:function(data){
                var contentHtml = data== null?"":data.adverContent;
                var adverInfoId = data== null?"":data.adverInfoId;
                ue.setContent(contentHtml);
                $("#adverInfoId").val(adverInfoId);
            },
            error:function(){
                console.log("没有信息");
            }
        });
    });

    $("#submit").click(function () {
        $.ajax({
            type:'post',
            url:'adverinfo/edit/page',
            data:{
                adverInfoId:$("#adverInfoId").val(),
                adverContent:$("#adverCount").val()
            },
            dataType:'json',
            success:function(data){
                console.log(data)
                var message = data.message;
                alert(message);
                window.location.href = data.forwardUrl;
            },
            error:function(){
                console.log("没有信息");
            }
        });
    });
</script>
