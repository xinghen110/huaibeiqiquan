package com.ruanyun.web.controller.web;

import com.ruanyun.common.controller.BaseController;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.IBaseService;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.MD5Util;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TRole;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.service.sys.DictionaryService;
import com.ruanyun.web.service.sys.RoleService;
import com.ruanyun.web.service.sys.UserService;
import com.ruanyun.web.service.web.*;
import com.ruanyun.web.util.CallbackAjaxDone;
import com.ruanyun.web.util.Constants;
import com.ruanyun.web.util.DateUtils;
import com.ruanyun.web.util.HttpSessionUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    @Qualifier("webService")
    private WebInterface webService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private StockPlanService stockPlanService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private BusinessOrderService businessOrderService;


    @InitBinder
    public void initBinders(WebDataBinder binder) {
        super.initBinder(binder, "yyyy-MM-dd HH:mm:ss", true);
    }


    @RequestMapping("/admin/withdraw/list")
    public String toWithdrawList(Model model, Page page, TUserAccountOrder userAccountOrder, String loginName) {
        List<HashMap> userAccountOrderList = adminService.queryWithdrawList(page, userAccountOrder, loginName);
        addModel(model, "pageList", page);
        return "pc/web/withdraw_list";
    }

    @RequestMapping("/admin/withdraw")
    public String toWithdraw(Model model, TUserAccountOrder userAccountOrder) {
        HashMap hashMap = adminService.queryWithdraw(userAccountOrder.getOrderId());
        addModel(model, "userAccountOrder", hashMap);
        return "pc/web/withdraw";
    }

    @RequestMapping("/admin/withdraw/handle")
    public void doWithdrawHandle(Model model, HttpServletResponse response, HttpSession session, TUserAccountOrder userAccountOrder) {
        try {
            if (userAccountOrder.getOrderStatus() == null || !(userAccountOrder.getOrderStatus().equals("1") || userAccountOrder.getOrderStatus().equals("2"))) {
                throw new RuntimeException("请选择操作结果");
            }

            TUser currentUser = HttpSessionUtils.getCurrentUser(session);
            userAccountOrder.setHandleUserId(currentUser.getUserId());

            adminService.handleWithdraw(userAccountOrder);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/withdraw/list", "redirect"));
        } catch (Exception e) {
            logger.error(e);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, e.getMessage(), "", "", ""));
        }
    }

    @RequestMapping("/admin/stock/plan/list")
    public String toAdminStockPlanList(HttpSession session,Model model, Page page, TStockPlan stockPlan,
                                       String loginName,String startTime,String endTime,Integer yunYingUserId,
                                       Integer huiYuanUserId,Integer daiLiUserId,String userName,String belongValue,
                                       String buyConfirmDatetartTime,String buyConfirmDateEndTime,
                                       String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,
                                       Integer isProfit,TUser user) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        List<HashMap> allFeeList = adminService.groupQueryStockPlanListServerFee(stockPlan, loginName,startTime,
                endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,
                buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime,isProfit);

        model.addAttribute("allFee",EmptyUtils.isEmpty(allFeeList.get(0).get("manageFee"))?"":allFeeList.get(0));

        List<HashMap> list = adminService.queryStockPlanList(page, stockPlan, loginName,startTime,endTime,yunYingUserId,
                huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,
                buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime,isProfit);

        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            model.addAttribute("buyConfirmDatetartTime", DateUtils.doFormatDate(buyConfirmDatetartTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            model.addAttribute("buyConfirmDateEndTime", DateUtils.doFormatDate(buyConfirmDateEndTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            model.addAttribute("sellConfirmTimeDatetartTime", DateUtils.doFormatDate(sellConfirmTimeDatetartTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            model.addAttribute("sellConfirmTimeDateEndTime", DateUtils.doFormatDate(sellConfirmTimeDateEndTime, "yyyy-MM-dd HH:mm:ss"));
        }
//        model.addAttribute("yunYingUserList",userService.getAllByCondition(TUser.class,"userType",IPSConstants.USER_TYPE_CHILD_02));
//        model.addAttribute("huiYuanUserList",userService.getAllByCondition(TUser.class,"userType",IPSConstants.USER_TYPE_THIRD_03));
//        model.addAttribute("daiLiUserList",userService.getAllByCondition(TUser.class,"userType",IPSConstants.USER_TYPE_SPREAD_04));
        model.addAttribute("userList", JSONArray.fromObject(userService.list(currentUser)));
        addModel(model, "pageList", page);
        addModel(model, "stockPlan", stockPlan);
        model.addAttribute("url","admin/stock/plan/list");
        return "pc/admin/admin_stock_plan_list";
    }

    @RequestMapping("/admin/stock/plan")
    public String toAdminStockPlan(Model model, TStockPlan stockPlan,String startTime,String endTime) {
        HashMap hashMap = adminService.queryStockPlan(stockPlan,startTime,endTime);
        addModel(model, "object", hashMap);
        return "pc/admin/admin_stock_plan";
    }

    @RequestMapping(value = "/admin/stock/plan/export", params = "direction=buy")
    public void doAdminStockPlanExport(Model model, HttpServletResponse response, HttpSession session, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,String belongValue,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit) {
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        try {
            adminService.exportBuyStockPlanList(response, stockPlan, loginName,startTime,endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime,isProfit);
        } catch (Exception e) {
//            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_FAILD_CODE, e.getMessage(), "", "", ""));
            super.writeText(response, "导出失败.原因：" + e.getMessage());
        }
    }

    @RequestMapping(value = "/admin/stock/plan/import", params = "direction=buy")
    public void doAdminStockPlanImport(Model model, HttpServletResponse response, HttpSession session, HttpServletRequest request
            , @RequestParam("stockPlanFile") MultipartFile stockPlanFile
    ) {
        List<Map> list = adminService.importBuyStockPlanList(stockPlanFile);
        super.writeJsonData(response, list);
    }

    @RequestMapping(value = "/admin/stock/plan/export", params = "direction=sell")
    public void doAdminStockPlanExportSell(Model model, HttpServletResponse response, HttpSession session, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,String belongValue,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit) {
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        try {
            adminService.exportSellStockPlanList(response, stockPlan, loginName,startTime,endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime,isProfit);
        } catch (Exception e) {
//            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_FAILD_CODE, e.getMessage(), "", "", ""));
            super.writeText(response, "导出失败.原因：" + e.getMessage());
        }
    }

    @RequestMapping(value = "/admin/stock/plan/import", params = "direction=sell")
    public void doAdminStockPlanImportSell(HttpServletResponse response, @RequestParam("stockPlanFile") MultipartFile stockPlanFile) {
        List<Map> list = adminService.importSellStockPlanList(stockPlanFile);
        super.writeJsonData(response, list);
    }

    /**
     * 查询用户账户信息列表
     */
    @RequestMapping("admin/user/account/list")
    public String queryUserAccountList(Page page, TUserAccount userAccount,Model model,TUser user,HttpSession session, String startTime, String endTime, TUserInfo userInfo,String belongValue){
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        model.addAttribute("userList", JSONArray.fromObject(userService.list(currentUser)));
        user.setUserType(5);
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        model.addAttribute("pageList",adminService.queryUserAccountList(page,userAccount,user,currentUser,startTime,endTime,userInfo,belongUserId,parentCodeIndex ));
        model.addAttribute("userAccount",userAccount);
        model.addAttribute("url","admin/user/account/list");
        model.addAttribute("user",user);
        return "pc/admin/user_account_list";
    }

    /**
     * 查询账户信息流水列表
     */
    @RequestMapping("admin/user/account/flow/list")
    public String queryUserAccountFlowList(Page page,HttpSession session,TUserAccountFlow userAccountFlow,Model model,TUser user, String startTime, String endTime, TUserInfo userInfo,String belongValue){
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        List<HashMap> allFeeList = adminService.groupQueryUserAccountFlowList(userAccountFlow,user,currentUser,startTime, endTime, userInfo, belongUserId, parentCodeIndex);
        model.addAttribute("allFee",EmptyUtils.isEmpty(allFeeList.get(0).get("money"))?"":allFeeList.get(0));
        model.addAttribute("userList", JSONArray.fromObject(userService.list(currentUser)));
        user.setUserType(5);
        model.addAttribute("pageList",adminService.queryUserAccountFlowList(page,userAccountFlow,user,currentUser,startTime, endTime, userInfo, belongUserId, parentCodeIndex));
        model.addAttribute("userAccountFlow",userAccountFlow);
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return "pc/admin/user_account_flow_list";
    }

    /**
     * 查询视频信息的列表
     */
    @RequestMapping("admin/video/list")
    public String queryVideoList(TArticle video,Page page,Model model){
        video.setMediaType(2);
        model.addAttribute("pageList",webService.queryArticleList(page,video));
        return "pc/admin/video_list";
    }

    /**
     * 跳转到添加视频的界面
     */
    @RequestMapping(value = "admin/video/add",method = RequestMethod.GET)
    public String toAdd(Model model){
        model.addAttribute("url","admin/video/add");
        return "pc/admin/video_add";
    }

    /**
     * 执行添加视频信息的操作
     */
    @RequestMapping(value = "admin/video/add",method = RequestMethod.POST)
    public void doAdd(HttpServletResponse response,HttpSession session, TArticle video){
        articleService.save(session,video);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "admin/video/list", "forward"));
    }

    /**
     * 跳转到修改视频信息的界面
     */
    @RequestMapping(value = "admin/video/update",method = RequestMethod.GET)
    public String toUpdate(TArticle article,Model model){
        model.addAttribute("url","admin/video/update");
        model.addAttribute("article",articleService.get(TArticle.class,article.getArticleId()));
        return "pc/admin/video_edit";
    }

    /**
     * 执行修改视频信息的操作
     */
    @RequestMapping(value = "admin/video/update",method = RequestMethod.POST)
    public void doUpdate(HttpServletResponse response,TArticle video,Model model){
        articleService.updateVideoInfo(video);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "admin/video/list", "forward"));
    }

    /**
     * 当前登录者查询自己邀请的用户列表
     */
    @RequestMapping("admin/query/by/invite")
    public String queryUserByInvite(HttpSession session,Page page,Model model,TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime,String belongValue,String parentLoginName){
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        //运营中心列表
        model.addAttribute("childUserList",userService.getAllByCondition(TUser.class,"userType",Constants.USER_TYPE_CHILD_02));
        model.addAttribute("userList", JSONArray.fromObject(userService.list(HttpSessionUtils.getCurrentUser(session))));
        model.addAttribute("pageList",adminService.queryUserByInvite(page,session,selectInviteUser, userInfo, startTime, endTime,belongUserId,parentCodeIndex,parentLoginName));
//        model.addAttribute("user",user);
        model.addAttribute("userInfo",userInfo);
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return "pc/admin/invite_user_list";
    }

    /**
     * 查询用户列表(账户管理)
     */
    @RequestMapping("admin/user/list")
    public String getUserList(HttpSession session,TUser user,Model model,Page page, TUserInfo userInfo, String startTime, String endTime,String belongValue){
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        model.addAttribute("userList", JSONArray.fromObject(userService.list(HttpSessionUtils.getCurrentUser(session))));
        model.addAttribute("pageList",adminService.queryUserByInvite(page,session,user, userInfo, startTime, endTime,belongUserId, parentCodeIndex,null));
        model.addAttribute("user",user);
        model.addAttribute("userInfo",userInfo);
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return "pc/admin/user_list";
    }

    /**
     * 跳往添加用户的界面
     */
    @RequestMapping(value = "admin/user/add",method = RequestMethod.GET)
    public String toAddUser(Model model,TUser user,Page page,HttpSession session,String belongValue){

        TUser currentUser = HttpSessionUtils.getCurrentUser(session);

        //当创建userType=4的代理商用户时,用户名需要使用当前用户名为前缀
        if (user.getUserType() != null && user.getUserType() == 4) {
            model.addAttribute("user", HttpSessionUtils.getCurrentUser(session));
        }

        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }
        model.addAttribute("userList",adminService.queryUserByInvite(page,session,user, null, null, null,belongUserId,parentCodeIndex,null));
        model.addAttribute("roleList",adminService.queryRoleList(new TRole()));
        //查询分公司的用户列表
        Page newpage = new Page();
        user.setUserType(2);
        model.addAttribute("childUserList",adminService.queryUserList(newpage,user));
        currentUser.setFlag3(String.valueOf(Constants.USER_TYPE_SPREAD_04));
        user.setUserType(Constants.USER_TYPE_SPREAD_04);
        model.addAttribute("allUserList", JSONArray.fromObject(userService.queryUserByInviteList(currentUser,user,new TUserInfo(),"","")));

        return "pc/admin/user_add";
    }

    /**
     * 执行添加用户的操作
     */
    @RequestMapping(value = "admin/user/add",method = RequestMethod.POST)
    public void doAddUser(HttpServletResponse response,HttpSession session,TUser user,TRole role,Integer parentUserId){
        adminService.addUser(session,user,role,parentUserId);
        if(EmptyUtils.isNotEmpty(user.getUserType()) && user.getUserType()==3){
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/query/by/invite?userType=" + user.getUserType(), "forward"));
        }else if(EmptyUtils.isNotEmpty(user.getUserType()) && user.getUserType()==4) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/query/user/parent/list?userType=" + user.getUserType(), "forward"));
        }else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/user/list?userType=" + user.getUserType(), "forward"));
        }
//        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(IPSConstants.STATUS_SUCCESS_CODE,IPSConstants.MESSAGE_SUCCESS, "main_", "admin/user/list?userType="+user.getUserType(), "forward"));
    }

    /**
     * 跳往修改用户界面
     * @param user
     * @param model
     * @param userType
     * @return
     */
    @RequestMapping(value = "admin/user/update",method = RequestMethod.GET)
    public String toUpdateUser(TUser user,Model model,Integer userType,String urlType){
        model.addAttribute("urlType",urlType);
        if (EmptyUtils.isNotEmpty(user.getUserId())) {
            //通过id查询用户信息
            user=userService.getUserById(user.getUserId());

            //通过用户id查询用户拥有的角色
            TRole  userRole =roleService.getRoleListByUserId(user.getUserId());
            addModel(model, "userRole", userRole);
        }
        addModel(model, "user",user );
        //查询所有角色
        List<TRole> allRoles=roleService.getAll(TRole.class,"orderby", IBaseService.ORDER_DESC);
        addModel(model, "allRoles", allRoles);
        addModel(model,"userType",userType);
        model.addAttribute("roleList",adminService.queryRoleList(new TRole()));
//		if(userType==4)  //物流人员
//			return "pc/sys/user/wuliuEdit";
//		else
        return "pc/sys/user/userEdit";
    }

    /**
     * 执行修改用户信息
     * @param response
     * @param session
     * @param user
     * @param role
     */
    @RequestMapping(value = "admin/user/update",method = RequestMethod.POST)
    public void doUpdateUser(HttpServletResponse response,HttpSession session,TUser user,TRole role,String urlType){
        adminService.updateUser(session,user,role);
        if(EmptyUtils.isNotEmpty(user.getUserType()) && user.getUserType()==3){
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/query/by/invite?userType=" + user.getUserType(), "forward"));
        }else if(EmptyUtils.isNotEmpty(user.getUserType()) && user.getUserType()==4) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/query/user/parent/list?userType=" + user.getUserType(), "forward"));
        }else {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS, "main_", "admin/user/list?userType=" + user.getUserType(), "forward"));
        }
    }

    /**
     * 查询
     */
    @RequestMapping("/admin/stock/plan/list/bysth")
    public String queryAdminStockPlanList(HttpSession session,Model model, Page page, TStockPlan stockPlan, String loginName,String belong,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,String belongValue,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,TUser user) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0]);
            parentCodeIndex = Integer.parseInt(belongInfo[1]);
        }

        List<HashMap> allFeeList = adminService.groupQueryStockPlanListBySth(session,stockPlan, loginName,belong,startTime,endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime);
        model.addAttribute("allFee",EmptyUtils.isEmpty(allFeeList.get(0).get("manageFee"))?"":allFeeList.get(0));
        List<HashMap> list = adminService.queryStockPlanListBySth(session,page, stockPlan, loginName,belong,startTime,endTime,yunYingUserId,huiYuanUserId,daiLiUserId,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime);
        addModel(model, "pageList", page);
        model.addAttribute("url","admin/stock/plan/list/bysth");
//        model.addAttribute("yunYingUserList",userService.getAllByCondition(TUser.class,"userType",IPSConstants.USER_TYPE_CHILD_02));
//        model.addAttribute("huiYuanUserList",userService.getAllByCondition(TUser.class,"userType",IPSConstants.USER_TYPE_THIRD_03));
//        model.addAttribute("daiLiUserList",userService.getAllByCondition(TUser.class,"userType",IPSConstants.USER_TYPE_SPREAD_04));
        model.addAttribute("yunYingUserId",EmptyUtils.isEmpty(yunYingUserId)?"":yunYingUserId);
        model.addAttribute("huiYuanUserId",EmptyUtils.isEmpty(huiYuanUserId)?"":huiYuanUserId);
        model.addAttribute("daiLiUserId",EmptyUtils.isEmpty(daiLiUserId)?"":daiLiUserId);
        model.addAttribute("userList", JSONArray.fromObject(userService.list(currentUser)));
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            model.addAttribute("buyConfirmDatetartTime", DateUtils.doFormatDate(buyConfirmDatetartTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            model.addAttribute("buyConfirmDateEndTime", DateUtils.doFormatDate(buyConfirmDateEndTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            model.addAttribute("sellConfirmTimeDatetartTime", DateUtils.doFormatDate(sellConfirmTimeDatetartTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            model.addAttribute("sellConfirmTimeDateEndTime", DateUtils.doFormatDate(sellConfirmTimeDateEndTime, "yyyy-MM-dd HH:mm:ss"));
        }
        model.addAttribute("belong",belong);
        return "pc/admin/stock_plan_list";
    }

    /**
     * 查询股票信息列表
     */
    @RequestMapping("admin/stock/list")
    public String toQueryStockList(Model model,TStock stock,Page page){
        model.addAttribute("url","admin/stock/list");
        model.addAttribute("pageList",adminService.queryStockList(page,stock));
        return "pc/admin/stock_list";
    }

    /**
     * 跳转编辑股票信息的界面
     */
    @RequestMapping(value = "admin/stock/update",method = RequestMethod.GET)
    public String toUpdateStock(Model model,TStock stock){
        model.addAttribute("url","admin/stock/update");
        model.addAttribute("stock",adminService.getStock(stock));
        return "pc/admin/stock_update";
    }

    /**
     * 执行修改股票信息操作
     */
    @RequestMapping(value = "admin/stock/update",method = RequestMethod.POST)
    public void doUpdateStock(HttpServletResponse response,TStock stock){
        adminService.updateStock(stock);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "admin/stock/list", "forward"));
    }

    /**
     * 批量处理股票代码所属交易所
     */
    @RequestMapping(value = "admin/batch/stock/prefix/update")
    public void updateBatchStockPrefix(HttpServletResponse response,TStock stock){
        adminService.updateBatchStockPrefix(stock);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "", "closeCurrent"));
    }

    /**
     * 功能描述:跳转到报价模式管理list页面
     * @author zhujingbo
     */
    @RequestMapping("market/quotemode/list")
    public String getQuoteModeList(Model model){
        String parentCode = "STOCK_PRICE_TYPE";
        addModel(model, "authList", adminService.getQuoteModeList(parentCode));
        return "pc/admin/quote_newlist";
    }

    /**
     * 进入字典表查询
     */
    @RequestMapping("market/quotemode/toList")
    public String toList(Model model,TDictionary dictionary){
        addModel(model, "dictionaryList", dictionaryService.getDictionaryList(dictionary));
        addModel(model, "bean", dictionaryService.getAllByCondition(TDictionary.class,"parentCode",dictionary.getParentCode()).get(0));
        return "pc/admin/quote_mode_list";
    }

    /**
     * 功能描述:跳转到字典list页面
     *
     */
    @RequestMapping("admin/dictionary/list")
    public String getAdminDictionaryList(Model model) {
        Page page = new Page();
        page.setNumPerPage(Integer.MAX_VALUE);
        TDictionary dictionary = new TDictionary();
        //多个字典菜单
        StringBuilder sb = new StringBuilder();
        sb.append("'USER_TRANSACTION_SWITCH',");
        sb.append("'IMPORT_STOCK_INFO_NUMBER',");
        sb.delete(sb.toString().length() - 1, sb.toString().length());
        //赋值
        dictionary.setParentCode(sb.toString());
        addModel(model, "authList", adminService.queryDictionaryList(page, dictionary));
        return "pc/admin/admin_dictionary_list";
    }

    @RequestMapping("market/quotemode/toEdit")
    public String toEdit(Model model,TDictionary dictionary){
        TDictionary tDictionary = dictionaryService.get(TDictionary.class,dictionary.getId());
        tDictionary.setStatus(dictionary.getStatus());
        addModel(model, "bean", tDictionary);
        return "pc/admin/quote_mode_edit";
    }

    @RequestMapping("market/quotemode/saveOrUpdate")
    public void saveOrUpdate(Model model,TDictionary dictionary,HttpServletResponse response,Integer type){
        TDictionary tDictionary = dictionaryService.get(TDictionary.class,dictionary.getId());
        tDictionary.setStatus(dictionary.getStatus());
        int result = dictionaryService.updateQueto(tDictionary);

        if(result == 1){
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS,"","jbsxBox","","closeCurrent"));
        }else{
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED,"","jbsxBox","","closeCurrent"));
        }
    }


    /**
     * 功能描述:跳转到服务费设置页面
     * @author zhujingbo
     */
    @RequestMapping("market/servicecharge/list")
    public String getServiceChargeList(Model model){
        String parentCode = "MANAGE_FEE";
        addModel(model, "authList", adminService.getQuoteModeList(parentCode));
        return "pc/admin/service_charge_newlist";
    }

    /**
     * 进入服务费查询-字典表查询
     */
    @RequestMapping("market/servicecharge/toList")
    public String toServiceChargeList(Model model,TDictionary dictionary){
        addModel(model, "dictionaryList", adminService.queryDictionaryList(null, dictionary));
        addModel(model, "bean", dictionaryService.getAllByCondition(TDictionary.class,"parentCode",dictionary.getParentCode()).get(0));
        return "pc/admin/service_charge_list";
    }


    @RequestMapping("market/servicecharge/toEdit")
    public String toServiceChargeEdit(Model model,TDictionary dictionary){
        TDictionary tDictionary = dictionaryService.get(TDictionary.class,dictionary.getId());
        tDictionary.setStatus(dictionary.getStatus());
        addModel(model, "bean", tDictionary);
        return "pc/admin/service_charge_edit";
    }

    @RequestMapping("market/servicecharge/saveOrUpdate")
    public void serviceChargeSaveOrUpdate(Model model,TDictionary dictionary,HttpServletResponse response,Integer type){
        //TDictionary tDictionary = dictionaryService.get(TDictionary.class,dictionary.getId());
        //tDictionary.setStatus(dictionary.getStatus());
        //int result = dictionaryService.updateQueto(tDictionary);
        int result = dictionaryService.updateQueto(dictionary);

        if(result == 1){
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, Constants.MESSAGE_SUCCESS,"","jbsxBox","","closeCurrent"));
        }else{
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, Constants.MESSAGE_FAILED,"","jbsxBox","","closeCurrent"));
        }
    }



//    @RequestMapping("admin/user/info/list")
    public String queryUserinfoList(Page page,TUserInfo userInfo,TUser user,Model model){
        model.addAttribute("pageList",adminService.queryUserInfoList(page,userInfo,user));
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("user",user);
        return "pc/admin/user_info_list";
    }

    /**
     * 审核银行卡信息以及实名制信息
     * 多写的，弃用
     * @return
     */
//    @RequestMapping(value = "admin/user/info/check",method = RequestMethod.GET)
    public String toDealUserInfo(TUserInfo userInfo,Model model){
        model.addAttribute("userInfo",userInfoService.get(TUserInfo.class,userInfo.getUserId()));
        return "pc/admin/user_info_check";
    }

    /**
     * 执行提交的审核结果
     * 多写的，弃用
     */
//    @RequestMapping(value = "admin/user/info/check",method = RequestMethod.POST)
    public void doDealUserInfo(HttpServletResponse response,TUserInfo userInfo){
       adminService.updateUserInfo(userInfo);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "admin/user/info/list", "forward"));
    }

    /**
     * 查询代理商列表
     * @param model
     * @param page
     * @param user
     * @param session
     * @param userInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping("admin/query/user/parent/list")
    public String doqueryUserInfoList(Model model,Page page,TUser user,HttpSession session, TUserInfo userInfo, String startTime, String endTime,String belongValue){
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0].toString());
            parentCodeIndex = Integer.parseInt(belongInfo[1].toString());
        }
        model.addAttribute("tuser",user);
        model.addAttribute("pageList",adminService.queryUserAndParentInfoList(page,user,session, userInfo, startTime, endTime,belongUserId,parentCodeIndex));
        model.addAttribute("userInfo",userInfo);
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        model.addAttribute("userList", JSONArray.fromObject(userService.list(HttpSessionUtils.getCurrentUser(session))));
        return "pc/admin/user_and_parent_list";
    }

    /**
     * 跳往出入金设置界面
     * @param model
     * @return
     */
    @RequestMapping(value = "admin/income/expenditure/setting",method = RequestMethod.GET)
    public String toUpdateIncomeExpenditureSetting(Model model){
        return "pc/admin/income_expenditure_setting";
    }

    /**
     * 执行修改出入金设置写入字典表
     * @param response
     * @param time
     * @param count
     * @param limit
     */
    @RequestMapping(value = "admin/income/expenditure/setting",method = RequestMethod.POST)
    public void doUpdateIncomeExpenditureSetting(HttpServletResponse response,String time,String count,String limit){
        adminService.updateSetting(time,count,limit);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "cacheOperate/toUpdate", "redirect"));
    }

    /**
     * 这个是提供给查询个人信息的json
     * @param response
     * @param user
     */
    @RequestMapping("admin/user/detail")
    public void getUserDetail(HttpServletResponse response,TUser user){
        TUser tUser = userService.get(TUser.class,"loginName",user.getLoginName());
        super.writeJsonData(response,tUser);
    }

    /**
     * 这个是提供给查询个人信息的json
     * @param response
     * @param user
     */
    @RequestMapping("admin/user/info")
    public void getUserInfo(HttpServletResponse response, TUser user) {
        TUser tUser = userService.get(TUser.class, "loginName", user.getLoginName());
        if (tUser == null) {
            return;
        }
        TUserInfo tUserInfo = userInfoService.get(TUserInfo.class, tUser.getUserId());
        super.writeJsonData(response, tUserInfo);
    }

    /**
     * 查询自己的邀请码等信息
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("admin/query/self/detail")
    public String getSelfDetail(Model model,HttpSession session){
        model.addAttribute("userDetail",HttpSessionUtils.getCurrentUser(session));
        return "pc/admin/user_detail";
    }

    /**
     * 数据统计
     * @param page
     * @param stockPlan
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("admin/stock/plan/data/handle")
    public String queryData(Page page,TStockPlan stockPlan,Model model,TUser user,HttpSession session){
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        model.addAttribute("pageList",adminService.queryFeeAndProfitInfo(currentUser,page,stockPlan,user));
        return "pc/admin/stock_plan_data_handle";
    }


    @RequestMapping("admin/stock/plan/fee/list")
    public String queryStockPlanFeeList(HttpSession session,Page page,TStockPlan stockPlan,TUser user,Model model,String startTime,String endTime,String userName,String belongValue,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime){
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0].toString());
            parentCodeIndex = Integer.parseInt(belongInfo[1].toString());
        }
        List<HashMap> allFeeList = adminService.groupQueryStockPlanFeeInfo(currentUser,stockPlan,user,startTime,endTime,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime);
        model.addAttribute("allFee",EmptyUtils.isEmpty(allFeeList.get(0).get("manageFee"))?"":allFeeList.get(0));
        model.addAttribute("pageList",adminService.queryStockPlanFeeInfo(currentUser,page,stockPlan,user,startTime,endTime,userName,belongUserId,parentCodeIndex,buyConfirmDatetartTime,buyConfirmDateEndTime,sellConfirmTimeDatetartTime,sellConfirmTimeDateEndTime));
        model.addAttribute("userList", JSONArray.fromObject(userService.list(currentUser)));
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(EmptyUtils.isNotEmpty(buyConfirmDatetartTime)) {
            model.addAttribute("buyConfirmDatetartTime", DateUtils.doFormatDate(buyConfirmDatetartTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(buyConfirmDateEndTime)) {
            model.addAttribute("buyConfirmDateEndTime", DateUtils.doFormatDate(buyConfirmDateEndTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if(EmptyUtils.isNotEmpty(sellConfirmTimeDatetartTime)) {
            model.addAttribute("sellConfirmTimeDatetartTime", DateUtils.doFormatDate(sellConfirmTimeDatetartTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(sellConfirmTimeDateEndTime)) {
            model.addAttribute("sellConfirmTimeDateEndTime", DateUtils.doFormatDate(sellConfirmTimeDateEndTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return "pc/admin/stock_plan_fee_list";
    }

    /**
     * 查询资金流水总和列表
     * @param session
     * @param model
     * @param page
     * @param userAccountFlow
     * @param type
     * @return
     */
    @RequestMapping("admin/user/account/flow/sum/list")
    public String queryCapitalflow(HttpSession session,Model model,Page page,TUserAccountFlow userAccountFlow,String type,String loginName, String startTime, String endTime,String belongValue,String userName){
        String[] belongInfo = new String[]{};
        Integer belongUserId=-1;
        Integer parentCodeIndex=-1;
        if(EmptyUtils.isNotEmpty(belongValue)) {
            belongInfo = belongValue.split("-");
            belongUserId = Integer.parseInt(belongInfo[0].toString());
            parentCodeIndex = Integer.parseInt(belongInfo[1].toString());
        }
        TUser user = HttpSessionUtils.getCurrentUser(session);
        userAccountFlow.setUserId(user.getUserId());
        model.addAttribute("userList", JSONArray.fromObject(userService.list(HttpSessionUtils.getCurrentUser(session))));
        model.addAttribute("pageList",adminService.queryCapitalflow(page,userAccountFlow,type,user,loginName,startTime,endTime,belongUserId,userName,parentCodeIndex));
        if(EmptyUtils.isNotEmpty(startTime)) {
            model.addAttribute("startTime", DateUtils.doFormatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            model.addAttribute("endTime", DateUtils.doFormatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }
        return "pc/admin/user_account_flow_sum_list";
    }

    @RequestMapping(value = "admin/manualSell", method = RequestMethod.GET)
    public String manualExerciseGet(HttpSession session, TStockPlan stockPlan, HttpServletResponse response, HttpServletRequest request) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        if (user.getUserType() != 1) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, "非管理员无法使用", "main_", "index", "forward"));
            return "";
        }
        TStockPlan tStockPlan = stockPlanService.get(TStockPlan.class, stockPlan.getPlanId());
        request.setAttribute("obj", tStockPlan);
        return "pc/admin/manual_sell";
    }

    @RequestMapping(value = "admin/manualSell", method = RequestMethod.POST)
    public void manualExercisePost(HttpSession session, TStockPlan stockPlan, Model model, HttpServletResponse response, HttpServletRequest request) {
        TUser user = HttpSessionUtils.getCurrentUser(session);
        if (user.getUserType() != 1) {
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, "非管理员无法使用", "main_", "index", "redirect"));
        }
        try {
            if(stockPlan.getSellPriceType().equals("0")){
                stockPlan.setSellLimitPrice(BigDecimal.ZERO);
            }
            webService.exercise(stockPlan);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE, "行权申请成功", "main_", "admin/stock/plan/list", "forward"));
        } catch (Exception e) {
            logger.error(e);
            super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_FAILD_CODE, "行权申请失败,原因:" + e.getMessage(), "main_", "admin/stock/plan/list", "forward"));
        }
    }

    /**
     * 跳往设置提现手续费的界面
     * @return
     */
    @RequestMapping(value ="admin/withdrawfee/edit",method = RequestMethod.GET)
    public String toEditWithdrawFee(){
        return "pc/admin/withdraw_fee_edit";
    }

    /**
     * 修改提现手续费
     * @param dictionary
     * @return
     */
    @RequestMapping(value ="admin/withdrawfee/edit",method = RequestMethod.POST)
    public void doEditWithdrawFee(HttpServletResponse response,TDictionary dictionary){
        webService.updateWithdrawFee(dictionary);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "cacheOperate/toUpdate", "redirect"));
    }

    @RequestMapping("admin/article/list")
    public String queryArticleList(Page page, TArticle article, Model model){
        model.addAttribute("pageList",articleService.queryArticleList(page,article));
        return "pc/sys/article/list";
    }

    @RequestMapping(value = "admin/article/add",method = RequestMethod.GET)
    public String toAddArticle(Model model){
        model.addAttribute("url","article/add");
        return "pc/sys/article/add";
    }

    @RequestMapping(value = "admin/article/add",method = RequestMethod.POST)
    public void doAddArticle(HttpServletResponse response,HttpSession session, TArticle article){
        articleService.save(session,article);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "article/list", "forward"));
    }

    @RequestMapping(value = "admin/article/update",method = RequestMethod.GET)
    public String toUpdateArticle(TArticle article,Model model){
        model.addAttribute("url","article/update");
        model.addAttribute("article",articleService.get(TArticle.class,article.getArticleId()));
        return "pc/sys/article/edit";
    }

    @RequestMapping(value = "admin/article/update",method = RequestMethod.POST)
    public void doUpdateArticle(HttpServletResponse response,TArticle article,Model model){
        articleService.update(article);
        super.writeJsonData(response, CallbackAjaxDone.AjaxDone(Constants.STATUS_SUCCESS_CODE,Constants.MESSAGE_SUCCESS, "main_", "article/list", "forward"));
    }

    @RequestMapping(value = "admin/businessorder/list", method = RequestMethod.GET)
    public String businessOrderGet(HttpSession session, Page page, TBusinessOrder businessOrder, Model model) {
        TUser currentUser = HttpSessionUtils.getCurrentUser(session);
        if (currentUser.getUserType() != 1) {
            return redirect("/admin/login");
        }
        page = businessOrderService.page(page, businessOrder);
        model.addAttribute("pageList", page);
        return "pc/admin/admin_business_order_list";
    }


    /**
     * 股票数据导入功能
     */
    @RequestMapping(value = "admin/stockinfo/list")
    public String toAdminStockInfoList(Model model, Page page, TStockInfo stockInfo) {
        page = stockInfoService.list(page, stockInfo);
        addModel(model, "pageList", page);
        return "pc/admin/admin_stock_list";
    }

    /**
     * 股票数据导入功能
     *
     * @param session
     * @param stockPlan
     * @param model
     * @param response
     * @param request
     */
    @RequestMapping(value = "admin/stockinfo/import", method = RequestMethod.POST)
    public void doAdminStockInfoImport(HttpSession session, TStockPlan stockPlan, Model model, HttpServletResponse response, HttpServletRequest request,
                                       @RequestParam("stockPlanFile") MultipartFile stockPlanFile
    ) {
        List<Map> list = adminService.importStockInfoList(stockPlanFile);
        super.writeJsonData(response, list);
    }
}
