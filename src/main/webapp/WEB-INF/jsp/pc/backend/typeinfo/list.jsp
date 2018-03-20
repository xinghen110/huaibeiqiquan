<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding type="2"></ry:binding>
<ry:binding parentCode="CAR_COLOR" bingdingName="carColor"></ry:binding>
<style type="text/css">
    .new_tab {border-collapse:collapse;}
    .new_tab td{height:29px;line-height:29px;border:1px solid #e0e0e0;text-indent:10px;font-size: 12px;text-align: center;}
    .new_tab td a{ margin:6px -3px 0 10px}
    .new_tab tbody tr:hover{ background:#f5f5f5}
    .list_all{position: absolute;top:0px;left:0px;background:#fff;border-radius:3px}
    .list_all p{height:25px;line-height:25px; cursor:pointer;}
    .list_all p:hover{color:#2ea7ec}
    .list_all ul li{float:left}
    p {height: none;width: 625px;line-height: 160%;}
    .pageFormContent p {width: 630px;height: auto;}
    .new_tab tbody tr:hover{background:#f5f5f5}
    .new_tab thead tr td{background:#f0eff0 url(dwz/themes/default/images/grid/tableth.png) repeat-x}
    .app_tb{width:100%;overflow:hidden;}
    .app_tb li{float:left;width:22%;margin:0.5rem 1%;border:1px solid #ccc;background:#fff;}
    .app_tb_img{width:80%;margin:1rem 10%;border:1px solid #ccc;background:#fff;}
    .app_tb_img img{width:50%;margin:1rem 25%;}
    .app_tb_btnl{width:30%;height:30px;line-height:30px;color:#fff;margin-left:15%;float:left;border-radius:3px;;margin-bottom:1rem;}
    .app_tb_btnr{width:30%;height:30px;line-height:30px;color:#fff;margin-right:15%;float:right;border-radius:3px;margin-bottom:1rem;}
</style>
<div class="pageContent">
    <div class="pageFormContentUM pageFormContent nowrap" layoutH="60">


        <div class="tabsContent" layoutH="75">
            <!--	-------------------------------	基本信息 	 star -------------------------------    -->
            <div style="display: block;" inited="1000">


                <ul class="app_tb">
                    <c:forEach var="bean1" items="${List}">
                        <li>
                            <div class="app_tb_img"><img src="${constants.QINIU_USER_IMGURL}${not empty bean1.imgUrl ? bean1.imgUrl :'default2.png'}"/></div>
                            <div style="width:100%;overflow:hidden;margin-bottom:1rem;">
                                <h3 style="float:left;margin-left:1rem;">${bean1.typeInfoName}</h3>
                                <h3 style="float:right;margin-right:1rem;">

                                </h3>
                            </div>
                            <!-- <button class="app_tb_btnl" type="button">前移</button>
                            <button class="app_tb_btnr" type="button">后移</button> -->
                            <button class="app_tb_btnl" type="button" onclick="add('typeinfo/toEdit?typeNum=${bean1.typeNum}','修改分类',900,550,'main_')">
                                <a title="修改店铺分类"  href="javascript:void(0)"  rel="users_saveedit" >修改</a>
                            </button>
                        </li>
                    </c:forEach>
                </ul>



            </div>
            <!--	-------------------------------	基本信息 	 end-------------------------------    -->




            <!--	-------------------------------	账户  信息 star	 -------------------------------    -->
            <div inited="1000" style="display: none;" id="jbsxBox">
                <div id="jbsxBox" class="unitBox" style="margin-left:246px;"></div><!--   div局部刷新 	-->
            </div>
            <!--	-------------------------------	账户  信息	end -------------------------------    -->

            <!--	-------------------------------	积分 信息 star	 -------------------------------    -->
            <div inited="1000" style="display: none;" id="jbsxBox2">
                <div id="jbsxBox2" class="unitBox" style="margin-left:246px;"></div><!--   div局部刷新 	-->
            </div>
            <!--	-------------------------------	积分 信息		end -------------------------------    -->


        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>

</div>
</div>