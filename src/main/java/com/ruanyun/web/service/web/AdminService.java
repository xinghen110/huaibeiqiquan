package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.web.model.*;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.model.sys.TRole;
import com.ruanyun.web.model.sys.TUser;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminService {

    /**
     * 查询多个提现申请
     *
     * @param userAccountOrder
     * @param loginName
     * @return
     */
    List<HashMap> queryWithdrawList(Page page, TUserAccountOrder userAccountOrder, String loginName);

    /**
     * 查询单个提现申请
     *
     * @param orderId
     * @return
     */
    HashMap queryWithdraw(int orderId);

    /**
     * 处理提现申请
     *
     * @param userAccountOrder
     */
    void handleWithdraw(TUserAccountOrder userAccountOrder);

    /**
     * 查询方案列表
     * @param page
     * @param stockPlan
     * @param loginName
     * @param startTime
     * @param endTime
     * @return
     */
    List<HashMap> queryStockPlanList(Page page, TStockPlan stockPlan,String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit);

    /**
     * 与查询分页的queryStockPlanList配套的查询聚合数据
     * @param stockPlan
     * @param loginName
     * @param startTime
     * @param endTime
     * @param yunYingUserId
     * @param huiYuanUserId
     * @param daiLiUserId
     * @param userName
     * @param belongUserId
     * @param parentCodeIndex
     * @param buyConfirmDatetartTime
     * @param buyConfirmDateEndTime
     * @param sellConfirmTimeDatetartTime
     * @param sellConfirmTimeDateEndTime
     * @param isProfit
     * @return
     */
    List groupQueryStockPlanListServerFee(TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit);
    /**
     *
     * @param stockPlan
     * @param startTime
     * @param endTime
     * @return
     */
    HashMap queryStockPlan(TStockPlan stockPlan,String startTime,String endTime);

    /**
     * 导出买方案列表
     *
     * @param stockPlan
     * @param loginName
     */
    void exportBuyStockPlanList(HttpServletResponse response, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit);

    /**
     * 导出卖方案列表
     *
     * @param stockPlan
     * @param loginName
     */
    void exportSellStockPlanList(HttpServletResponse response, TStockPlan stockPlan, String loginName,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime,Integer isProfit);

    /**
     * 导入买方案列表
     *
     * @param stockPlanFile
     */
    List<Map> importBuyStockPlanList(MultipartFile stockPlanFile);

    /**
     * 导入卖方案列表
     *
     * @param stockPlanFile
     */
    List<Map> importSellStockPlanList(MultipartFile stockPlanFile);

    /**
     * 查询用户账户信息列表
     * @param page
     * @param userAccount
     * @return
     */
    Page queryUserAccountList(Page page, TUserAccount userAccount, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo, Integer belongUserId, Integer parentCodeIndex );

    /**
     * 查询用户账户流水信息列表
     * @param page
     * @param userAccountFlow
     * @return
     */
    Page<TUserAccountFlow> queryUserAccountFlowList(Page page,TUserAccountFlow userAccountFlow, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex);

    /**
     * 用户账户流水信息列表的所有结果的聚合
     * @param userAccountFlow
     * @param user
     * @param currentUser
     * @param startTime
     * @param endTime
     * @param userInfo
     * @param belongUserId
     * @param parentCodeIndex
     * @return
     */
    List groupQueryUserAccountFlowList(TUserAccountFlow userAccountFlow, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex );

    /**
     * 查询方案
     * @param page
     * @param stockPlan
     * @return
     */
    Page<TStockPlan> queryStockPlanList(Page page,TStockPlan stockPlan);

    /**
     * 查询当前登录用户的邀请的用户列表
     * @param page
     * @param session
     * @return
     */
    Page queryUserByInvite(Page page, HttpSession session,TUser selectInviteUser, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex,String parentLoginName );

    /**
     * 添加用户
     * @param user
     * @return
     */
    TUser addUser(HttpSession session,TUser user,TRole role,Integer parentUserId);


    /**
     * 修改用户信息
     * @param session
     * @param user
     * @param role
     * @return
     */
    TUser updateUser(HttpSession session,TUser user,TRole role);


    /**
     * 查询用户列表
     * @param page
     * @param user
     * @return
     */
    Page queryUserList(Page page,TUser user);

    List<HashMap> queryStockPlanListBySth(HttpSession session,Page page, TStockPlan stockPlan,String loginName,String belong,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime);

    List<HashMap> groupQueryStockPlanListBySth(HttpSession session, TStockPlan stockPlan,String loginName,String belong,String startTime,String endTime,Integer yunYingUserId,Integer huiYuanUserId,Integer daiLiUserId,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime);

    /**
     * 获取数据库中股票信息
     * @param stock
     * @return
     */
    TStock getStock(TStock stock);

    /**
     * 编辑股票信息
     * @param stock
     * @return
     */
    TStock updateStock(TStock stock);

    Page queryStockList(Page page,TStock stock);

    int updateBatchStockPrefix(TStock stock);

    @Deprecated
    TUserInfo updateUserInfo(TUserInfo userInfo);

    Page queryUserInfoList(Page page,TUserInfo userInfo,TUser user);

    Page queryUserAndParentInfoList(Page page,TUser user,HttpSession session, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex);

    /**
     * 查询字典表
     * @param page
     * @param dictionary
     * @return
     */
    List queryDictionaryList(Page page, TDictionary dictionary);

    /**
     * 功能描述:查询字典表 父级
     * @author zhujingbo
     * @return
     */
    List<TDictionary> getQuoteModeList(String parentCode);

    /**
     * 修改设置
     * @param
     * @param count
     * @param limit
     * @return
     */
    int updateSetting(String time,String count,String limit);

    /**
     * 查询费用以及收益的聚合
     * @param page
     * @param stockPlan
     * @param user
     * @return
     */
    Page queryFeeAndProfitInfo(TUser currentUser,Page page,TStockPlan stockPlan,TUser user);

    /**
     * 查询资金流水总和
     * @param page
     * @param userAccountFlow
     * @return
     */
    Page queryCapitalflow(Page page,TUserAccountFlow userAccountFlow,String type,TUser user,String loginName, String startTime, String endTime,Integer belongUserId,String userName,Integer parentCodeIndex);

    /**
     * 查询费用列表
     * @param page
     * @param stockPlan
     * @param user
     * @return
     */
    Page queryStockPlanFeeInfo(TUser currentUser,Page page, TStockPlan stockPlan,TUser user,String startTime,String endTime,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime);

    /**
     * 查询费用列表配套的聚合方法
     * @param currentUser
     * @param stockPlan
     * @param user
     * @param startTime
     * @param endTime
     * @param userName
     * @param belongUserId
     * @param parentCodeIndex
     * @param buyConfirmDatetartTime
     * @param buyConfirmDateEndTime
     * @param sellConfirmTimeDatetartTime
     * @param sellConfirmTimeDateEndTime
     * @return
     */
    List groupQueryStockPlanFeeInfo(TUser currentUser,TStockPlan stockPlan,TUser user,String startTime,String endTime,String userName,Integer belongUserId,Integer parentCodeIndex,String buyConfirmDatetartTime,String buyConfirmDateEndTime,String sellConfirmTimeDatetartTime,String sellConfirmTimeDateEndTime);

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    List queryRoleList(TRole role);

    List groupQueryAllServerFee(TUser currentUser,String startTime,String endTime);

    List<Map> importStockInfoList(MultipartFile stockPlanFile);
}
