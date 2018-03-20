package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TUserAccount;
import com.ruanyun.web.model.TUserAccountOperation;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.TUserInfoCheck;
import com.ruanyun.web.model.sys.TUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserAccountOperationDao extends BaseDaoImpl<TUserAccountOperation> {

    /**
     * 功能描述：获取资金冲正申请列表
     * @param page
     * @param userAccountOperation
     * @return
     */
    public Page queryPage(Page page, TUserAccountOperation userAccountOperation, TUserInfo userInfo, String startTime, String endTime,Integer belongUserId,Integer parentCodeIndex,String loginName) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  uao.id                        AS id,");
        sql.append("  applyedUser.user_name        AS applyedUserName,");
        sql.append("  uao.money                     AS money,");
        sql.append("  uao.status                    AS status,");
        sql.append("  applyUser.user_name          AS applyUserName,");
        sql.append("  uao.create_date_time          AS createDateTime,");
        sql.append("  handleUser.user_name         AS handleUserName,");
        sql.append("  user.login_name         AS loginName,");
        sql.append("  uao.handle_date_time          AS handleDateTime");
        sql.append(" from ");//###这个一定得小写，不然后面hql查询分页信息会报错###
        sql.append("  t_user_account_operation uao ");
        sql.append("  left JOIN t_user_info applyedUser ");
        sql.append("    ON uao.user_id = applyedUser.user_id ");
        sql.append("  left JOIN t_user_info applyUser");
        sql.append("    ON uao.apply_user_id = applyUser.user_id");
        sql.append("  left JOIN t_user_info handleUser");
        sql.append("    ON uao.handle_user_id =handleUser.user_id");
        sql.append("  left JOIN t_user user");
        sql.append("    ON uao.user_id =user.user_id");
        sql.append(" WHERE 1 = 1 ");
        if (StringUtils.isNotEmpty(userInfo.getUserName())) {
            sql.append("      AND applyedUser.user_name like :applyedUserName ");
            map.put("applyedUserName", "%" + userInfo.getUserName()+ "%");
        }
        if (StringUtils.isNotEmpty(loginName)) {
            sql.append("      AND user.login_name like "+"'%" + loginName+ "%'");
//            map.put("loginName", "%" + loginName+ "%");
        }
        if (StringUtils.isNotEmpty(userAccountOperation.getStatus())) {
            sql.append("      AND uao.status = :status ");
            map.put("status", userAccountOperation.getStatus() );
        }
        if (EmptyUtils.isNotEmpty(startTime)) {
            sql.append("      AND uao.create_date_time >= :startTime ");
            map.put("startTime", startTime );
        }
        if (EmptyUtils.isNotEmpty(endTime)) {
            sql.append("      AND uao.create_date_time <= :endTime ");
            map.put("endTime", endTime );
        }
        if (belongUserId>0) {
            Integer index = parentCodeIndex-1;
            if (index==3) {
                //TODO 这一级比较纠结，到底是算一级代理商还是二级代理商,暂时算与客户直接联系的代理商
                sql.append("   AND SUBSTRING_INDEX(user.parent_code,',',-1) = " + belongUserId);
            }
            if(index==1||index==2){
                sql.append("   AND SUBSTRING_INDEX(SUBSTRING_INDEX(user.parent_code,',',"+index+"),',',-1) = "+belongUserId);
            }
        }
        Page queryPage = sqlDao.queryPage(sql.toString(), page, map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }

//    public TUserAccountOperation queryUserAccountOperation(TUserAccountOperation userAccountOperation) {
//        String sql = "from TUserAccountOperation where userId = '"+userAccountOperation.getUserId()+"' and checkResult="+userInfoCheck.getCheckResult()+"";
//        return hqlDao.get(sql.toString());
//    }



}
