<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="header.jsp"%>
<body>
<%@include file="header_title.jsp"%>
<div>
    <img src="img/web/banner_ssjp.jpg" width="100%" />
</div>
<style>
    .ul-listview{padding-bottom: 35px;}
    .ul-listview li{padding-top: 35px;}
    .ui-listview-mr{background: red;color: white; }
    .ul-listview li:hover h5{background: red;color: white; }
    .page_btn{
        background: #fff;
        width: 45px;
        border: solid 1px #decccc;
        height: 25px;
        text-align: center;
        line-height: 25px;
        cursor: pointer;
    }
    .page_btn:hover{
        background: #ff0000;
        color: #ffffff;
    }


    .page_div {
        margin-top: 20px;
        margin-bottom: 20px;
        font-size: 15px;
        font-family: "microsoft yahei";
        color: #666666;
        margin-right: 10px;
        padding-left: 20px;
        box-sizing: border-box;
    }
    /*
     * 页数按钮样式
     */
    .page_div a {
        min-width: 30px;
        height: 28px;
        border: 1px solid #dce0e0!important;
        text-align: center;
        margin: 0 4px;
        cursor: pointer;
        line-height: 28px;
        color: #666666;
        font-size: 13px;
        display: inline-block;
    }
    #firstPage,
    #lastPage {
        width: 50px;
        color: #0073A9;
        border: 1px solid #0073A9!important;
    }
    #prePage,
    #nextPage {
        width: 70px;
        color: #0073A9;
        border: 1px solid #0073A9!important;
    }
    .page_div .current {
        background-color: #0073A9;
        border-color: #0073A9;
        color: #FFFFFF;
    }
    .totalPages {
        margin: 0 10px;
    }
    .totalPages span,
    .totalSize span {
        color: #0073A9;
        margin: 0 5px;
    }
</style>
<ul id="t_body" class="ul-listview" style="margin: 0 auto;width: 1280px;">
    <c:forEach items="${pageList.result}" var="item">
    <li>
        <a href="web/news/detail?articleId=${item.articleId}">
            <h5 style="padding: 5px 8px;" class="size-9 fl">${item.createTime}</h5>
            <h4 class="size-11 clear" style="height: 50px;line-height: 50px;">${item.title}</h4>
            <hr style="width: 100px;" />
            <h6 style="margin-top: 15px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;" class="color-hs">${item.content}</h6>
        </a>
    </li>
    </c:forEach>
</ul>

<div class="text-align" style="width: 1280px;margin: 0 auto;margin-bottom:30px;height: 50px;">
    <input id="currentPage" type="hidden" value="${pageList.pageNum}" />
    <input id="totalPage" type="hidden" value="${pageList.totalPage}" />
    <input id="totalCount" type="hidden" value="${pageList.totalCount}" />
    <%--<h4 style="padding-top: 13px;" class="color-white">--%>
        <%--<button class="page_btn" id="firstPage">首页</button>--%>
        <%--<button class="page_btn" id="previous">上一页</button>--%>
        <%--<button class="page_btn" id="next">下一页</button>--%>
        <%--<button class="page_btn" id="last">尾页</button>--%>
    <%--</h4>--%>
    <div id="page" class="page_div"></div>
</div>
<%@include file="footer.jsp"%>
</div>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">
    $(".ul-listview li").click(function(){
        $(".ul-listview li h5").removeClass("ui-listview-mr");
        $(this).find("h5").addClass("ui-listview-mr");
    });
</script>
<script>
    var pageSize = "10";//每页行数
    var currentPage = $("#currentPage").val();//当前页
    var totalPage = $("#totalPage").val();//总页数
    var totalCount =$("#totalCount").val();//总条数
    var params="";//参数
    var url="web/latest/analysis/information/list";//action
    $(document).ready(function(){//jquery代码随着document加载完毕而加载
                                 //分页查询
        queryForPages(currentPage);
    });
    function queryForPages(currentPage)
    {
        $.ajax({
            url:url,
            type:'post',
            dataType:'json',
            data:"pageNum="+currentPage+"&pageSize="+pageSize+params,
            success:function callbackFun(data)
            {
                //解析json
                var info = eval(data.result);
                //清空数据
//                    clearDate();
                $("#t_body").empty();
                fillTable(info);
                $("#currentPage").val(data.pageNum);
                $("#totalPage").val(data.totalPage);
                $("#totalCount").val(data.totalCount);
            }
        });
    }
    //填充数据
    function fillTable(info)
    {
        if(info.length>1)
        {
//                totalPage=info[info.length-1].totalPage;
            var tbody_content="";//不初始化字符串"",会默认"undefined"
            for(var i=0;i<info.length;i++)
            {
                tbody_content +="<a href=\"web/news/detail?articleId="+info[i].articleId+"\">\n" +
                    "              \n" +
                    "            <h5 style=\"padding: 5px 8px;\" class=\"size-9 fl\">"+info[i].createTime+"</h5>\n" +
                    "            <h4 class=\"size-11 clear\" style=\"height: 50px;line-height: 50px;\">"+info[i].title+"</h4>\n" +
                    "            <hr style=\"width: 100px;\" />\n" +
                    "            <h6 style=\"margin-top: 15px;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;\" class=\"color-hs\">"+info[i].description+"</h6>\n" +
                    "        </a>"
                $("#t_body").html(tbody_content);
            }
        }
        else
        {
            $("#t_head").html("");
            $("#t_body").html("<div style='padding-top: 100px;' align='center'>"+"暂无数据"+"</div>");
        }
    }
    //清空数据
    function clearDate()
    {
        $("#t_body").html("");
    }
    //搜索活动
    $("#searchActivity").click(function(){
        queryForPages();
    });
    //首页
    $("#firstPage").click(function(){
        currentPage="1";
        queryForPages();
    });
    //上一页
    $("#previous").click(function(){
        currentPage = $("#currentPage").val();
        if(currentPage>1)
        {
            currentPage-- ;
            queryForPages();
        }else {
            console.log("没有上一页");
        }
    });
    //下一页
    $("#next").click(function(){
        currentPage = $("#currentPage").val();
        totalPage = $("#totalPage").val();
        if(currentPage<totalPage)
        {
            currentPage++ ;
            queryForPages();
        }else {
            console.log("没有下一页");
        }
    });
    //尾页
    $("#last").click(function(){
        currentPage = $("#currentPage").val();
        totalPage = $("#totalPage").val();
        currentPage = totalPage;
        queryForPages();
    });
</script>
<script type="text/javascript" src="js/web/paging.js"></script>
<script>
    //分页
    $("#page").paging({
        pageNo:$("#currentPage").val(),
        totalPage: $("#totalPage").val(),
        totalSize: $("#totalCount").val(),
        callback: function(num) {
            queryForPages(num);
        }
    })
</script>
</body>
</html>

