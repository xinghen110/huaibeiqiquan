<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/inc/pression.jsp" %>
<ry:binding parentCode="MEMBER_LEVAL" bingdingName="memberleval"></ry:binding>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>table切换</title>

    <script language="javascript" src="js/table/common.js"></script>
    <link  type="text/css" rel="stylesheet" href="js/table/style.css" />
</head>

<style>
    .new_tab{border-collapse: collapse; margin-top: 25px;margin-left: 100px;margin-bottom: 20px}
    .new_tab h2{text-align:center;font-weight:10 }
    .new_tab tr td{height:30px;border:1px solid #e0e0e0; text-indent:10px}
    .investment div{background:#f5f5f5;border-top:2px soild #e0e0e0}
    .on{background:#f5f5f5;border-top:2px solid red}
    .cue{background:#f5f5f5;}
</style>
<div class="investment_f" style="width:850px;height:482px;">
    <div class="investment_con" style="border:none;width:830px;height:450px;overflow: auto;">
        <div class="investment_con_list" style="display:block;">
            <form action="shopinfo/updateExamine" method="post" id="form" onsubmit="return iframeCallback(this,dialogAjaxDone);">
          	
            <table class="new_tab">
                <tr>
                    <td colspan="4"  class="cue"><h2>审核材料</h2></td>
                </tr>

                <tr>
                    <td width="150" align="right" class="cue">技师/店铺姓名：</td>
                    <td  width="200">${shopInfo.shopName}</td>

                    <td  width="150" align="right" class="cue">技师/店铺编号：</td>
                    <td  width="200">${shopInfo.shopNum}</td>
                </tr>
				<c:if test="${bean.shopType==2}">
	                <tr>
	                    <td width="150" align="right" class="cue">技师性别：</td>
	                    <td  width="200"><ry:show parentCode="USERSEX" itemCode="${shopInfo.userSex}"></ry:show></td>
	
	                    <td  width="150" align="right" class="cue">出生年月：</td>
	                    <td  width="200">${shopInfo.userBirth}</td>
	                </tr>
				</c:if>
                <tr>
                    <td width="150" align="right" class="cue">技师/店铺联系方式：</td>
                    <td  width="200">${shopInfo.linkTel}</td>

                    <td  width="150" align="right" class="cue">技师/店铺服务类别：</td>
                    <td  width="200">${typeName}</td>
                </tr>

                <tr>
                    <td width="150" align="right" class="cue">技师/店铺常驻地址：</td>
                    <td  colspan="3">
                        <ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.province}" type="2"></ry:show>
                        <ry:show parentCode="${shopInfo.province}" itemCode="${shopInfo.city}" type="3"></ry:show>
                        <ry:show parentCode="${shopInfo.city}" itemCode="${shopInfo.area}" type="4"></ry:show>
                        ${shopInfo.address}
                    </td>
                </tr>
                <tr>
                <td width="150" align="right" class="cue">会员等级：</td>
	                <td colspan="3">
					<select name="memberLevel" >
					   <option value="">---请选择问题---</option>
						  <c:forEach items="${memberleval}" var="item">
							 <option value="${item.itemCode }" <c:if test="${item.itemCode==shopInfo.memberLevel}">selected="selected"</c:if> >${item.itemName }</option>
					       </c:forEach>
					</select>
					</td>
				</tr>

                <tr>
                    <td width="150" align="right" class="cue">技师/店铺审核材料：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="150" align="right" class="cue">审批选择：</td>
                    <td colspan="3">
                        <label style="color: green">通过审核&nbsp;<input type="radio" name="shopStatus" class="auditStatus" onclick="checkshopStatus()" value="1"></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <label style="color: red;">未通过审核&nbsp;<input type="radio" name="shopStatus" class="auditStatus" onclick="checkshopStatus()" value="-1"></label>
                    </td>
                </tr>

                <tr id="reason" style="display: none">
                    <td width="150" align="right" class="cue">审核说明：</td>
                    <td colspan="3">
                        <textarea hidden="" type="text" id="auditReasons" style="width: 492px;height:100px" name="reason"  class="mustFill"  title="请填写内容"></textarea>
                    	<input type="hidden" name="shopNum" value="${shopInfo.shopNum}">
                    </td>
                    
                </tr>
            </table>
            </form>
        </div>
    </div>
</div>


<div class="formBar" style="background-color: #e4ebf6;">
    <ul>
    
        <li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="todo()">提交</button></div></div></li>
        <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
    </ul>
</div>
<script type="text/javascript">
    function checkshopStatus(){
        var auditStatus = $(".auditStatus:checked").val();
        var reason = $("#reason");
        var auditReasons = $("#auditReasons");
        if (auditStatus == 1) {
            reason.hide();
            auditReasons.hide();
        }
        if (auditStatus == -1) {
            auditReasons.val("");
            reason.show();
            auditReasons.show();
        }
    }
    function todo(){
        var auditStatus = $(".auditStatus:checked");
        var auditReasons = $("#auditReasons");
        if (auditStatus.val() == 1)
            auditReasons.val("通过审核！");
        if (auditStatus.val() == null) {
            alert("请选择审核状态！");
        } else if (auditReasons.val().trim() == "") {
            alert("请输入审核原因！");
        } else {
            $("#form").submit();
        }
    }
</script>