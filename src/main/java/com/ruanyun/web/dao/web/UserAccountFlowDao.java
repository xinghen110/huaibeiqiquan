package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TUserAccountFlow;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserAccountFlowDao extends BaseDaoImpl<TUserAccountFlow> {

    public Page queryPage(Page page, TUserAccountFlow userAccountFlow, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  uaf.flow_id              AS flowId,");
        sql.append("  uaf.user_id              AS userId,");
        sql.append("  uaf.money                AS money,");
        sql.append("  uaf.freezing_money       AS freezingMoney,");
        sql.append("  uaf.integral             AS integral,");
        sql.append("  uaf.before_money         AS beforeMoney,");
        sql.append("  uaf.after_money          AS afterMoney,");
        sql.append("  uaf.after_freezing_money AS afterFreezingMoney,");
        sql.append("  uaf.after_integral       AS afterIntegral,");
        sql.append("  uaf.flow_type            AS flowType,");
        sql.append("  uaf.create_type          AS createType,");
        sql.append("  uaf.flow_source          AS flowSource,");
        sql.append("  uaf.flow_source_type     AS flowSourceType,");
        sql.append("  uaf.flow_remark          AS flowRemark,");
        sql.append("  uaf.create_time          AS createTime,");
        sql.append("  tu.login_name		       AS parentLoginName,");
        sql.append("  tui.user_name		       AS userName,");
        sql.append("  u.login_name             AS loginName ");
        sql.append(" from ");
        sql.append("  t_user_account_flow uaf");
        sql.append("  LEFT JOIN t_user u");
        sql.append("    ON u.user_id = uaf.user_id");
        sql.append(" LEFT JOIN t_user tu ON tu.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) ");
        sql.append(" LEFT JOIN t_user_info tui ON tui.user_id = uaf.user_id ");
        sql.append(" WHERE 1 = 1");
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
            sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
        if (StringUtils.isNotEmpty(user.getLoginName())) {
            sql.append("      AND u.login_name like :loginName ");
            map.put("loginName", "%" + user.getLoginName() + "%");
        }
        if (StringUtils.isNotEmpty(userAccountFlow.getFlowType())) {
            sql.append("      AND uaf.flow_type = :flowType ");
            map.put("flowType", userAccountFlow.getFlowType());
        }
        if (EmptyUtils.isNotEmpty(user.getUserType())) {
            sql.append("      AND u.user_type = :userType ");
            map.put("userType", user.getUserType());
        }
        if (EmptyUtils.isNotEmpty(userInfo)&&StringUtils.isNotEmpty(userInfo.getUserName())) {
            sql.append("      AND tui.user_name like :userName ");
            map.put("userName", "%" + userInfo.getUserName() + "%");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND uaf.create_time >= :startTime ");
            map.put("startTime", startTime  );
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND uaf.create_time <= :endTime ");
            map.put("endTime", endTime  );
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if (index==3) {
                sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
            }
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
        }
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }

    public Page queryPage(Page page, TUserAccountFlow userAccountFlow,String selectType,TUser user,String loginName, String startTime, String endTime,Integer belongUserId,String userName,Integer parentCodeIndex) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * from (");
        sql.append(" SELECT");
        sql.append("   tuaf.user_id    AS  userId,");
        sql.append("   SUM(tuaf.money) as money,");
        sql.append("   u.login_name   as loginName,");
        sql.append("   tui.user_name   as userName,");
        sql.append("   SUM(CASE WHEN tuaf.flow_remark = '用户充值' THEN tuaf.money ELSE 0 END) AS rujinzonghe,");
        sql.append("   ABS(SUM(CASE WHEN tuaf.flow_remark = '用户提现' THEN tuaf.money ELSE 0 END)) AS chujinzonghe,");
        sql.append("   ABS(SUM(CASE WHEN tuaf.flow_type = 'userAccountOperation' THEN CASE WHEN tuaf.money<0 THEN tuaf.money ELSE 0 END  ELSE 0 END)) AS hongchong, ");
        sql.append("   ABS(SUM(CASE WHEN tuaf.flow_type = 'userAccountOperation' THEN CASE WHEN tuaf.money>0 THEN tuaf.money ELSE 0 END  ELSE 0 END)) AS lanbu,");
        sql.append("   ABS(SUM(CASE WHEN tuaf.flow_remark = '方案申请' THEN tuaf.money ELSE 0 END))-ABS(SUM(CASE WHEN tuaf.flow_remark = '买入申请未成交,退回管理费' THEN tuaf.money ELSE 0 END)) AS sumServiceFee,");
        sql.append("   SUM(CASE WHEN tuaf.flow_remark = '行权收益' THEN tuaf.money ELSE 0 END) AS sumPrifit ");
        sql.append(" from ");
        sql.append("   t_user_account_flow tuaf ");
        sql.append("   LEFT JOIN t_user u ON u.user_id = tuaf.user_id ");
        sql.append("   LEFT JOIN t_user_info tui ON tui.user_id = u.user_id ");
        sql.append(" LEFT JOIN t_user parentUser  ON parentUser.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) ");
        sql.append(" WHERE 1=1");
        if(EmptyUtils.isNotEmpty(userAccountFlow.getFlowType())){
            sql.append("       AND tuaf.flow_type = :flowType");
            map.put("flowType",userAccountFlow.getFlowType());
        }
        if(EmptyUtils.isNotEmpty(loginName)){
            sql.append("       AND u.login_name like :loginName");
            map.put("loginName","%"+loginName+"%");
        }
        if(EmptyUtils.isNotEmpty(userName)){
            sql.append("       AND tui.user_name like :userName");
            map.put("userName","%"+userName+"%");
        }
        if(EmptyUtils.isNotEmpty(user) &&  EmptyUtils.isNotEmpty(user.getUserId())){
            if(user.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+user.getUserId()+", u.parent_code) > 0");
            }
//			sql.append(" and u.user_type >"+IPSConstants.USER_TYPE_MOTHER_01);
        }
        //查询包括下属的信息
        if(EmptyUtils.isNotEmpty(selectType)&&"child".equals(selectType)&&user.getUserType()!= Constants.USER_TYPE_MOTHER_01){
            sql.append("       AND SUBSTRING_INDEX(u.parent_code, ',', - 1)="+userAccountFlow.getUserId());
        }
        if(EmptyUtils.isNotEmpty(selectType)&&"all".equals(selectType)&&user.getUserType()!= Constants.USER_TYPE_MOTHER_01){
            sql.append("       AND FIND_IN_SET("+userAccountFlow.getUserId()+", u.parent_code) > 0");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND u.create_time >= :startTime ");
            map.put("startTime", startTime  );
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND u.create_time <= :endTime ");
            map.put("endTime", endTime  );
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if (index==3) {
                sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
            }
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
        }
        sql.append(" GROUP BY tuaf.user_id ) t");
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }

    public List groupQueryPage(TUserAccountFlow userAccountFlow, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  SUM(uaf.money)                  AS money ");
        sql.append(" from ");
        sql.append("  t_user_account_flow uaf");
        sql.append("  LEFT JOIN t_user u");
        sql.append("    ON u.user_id = uaf.user_id");
        sql.append(" LEFT JOIN t_user tu ON tu.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) ");
        sql.append(" LEFT JOIN t_user_info tui ON tui.user_id = uaf.user_id ");
        sql.append(" WHERE 1 = 1");
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
            sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
        if (StringUtils.isNotEmpty(user.getLoginName())) {
            sql.append("      AND u.login_name like  "+"'%" + user.getLoginName() + "%'");
            map.put("loginName", "%" + user.getLoginName() + "%");
        }
        if (StringUtils.isNotEmpty(userAccountFlow.getFlowType())) {
            sql.append("      AND uaf.flow_type = '"+userAccountFlow.getFlowType()+"'");
            map.put("flowType", userAccountFlow.getFlowType());
        }
        if (EmptyUtils.isNotEmpty(user.getUserType())) {
            sql.append("      AND u.user_type ="+user.getUserType());
            map.put("userType", user.getUserType());
        }
        if (EmptyUtils.isNotEmpty(userInfo)&&StringUtils.isNotEmpty(userInfo.getUserName())) {
            sql.append("      AND tui.user_name like "+"'%" + userInfo.getUserName() + "%'");
            map.put("userName", "%" + userInfo.getUserName() + "%");
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND uaf.create_time >= '"+startTime+"'");
            map.put("startTime", startTime  );
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND uaf.create_time <='"+endTime+"'");
            map.put("endTime", endTime  );
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if (index==3) {
                sql.append("   AND SUBSTRING_INDEX(u.parent_code,',',-1) = " + belongUserId);
            }
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(u.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
        }
        return sqlDao.getAll(sql.toString());
    }
}
