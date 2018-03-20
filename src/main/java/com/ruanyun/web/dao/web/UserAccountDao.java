package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TUserAccount;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserAccountDao extends BaseDaoImpl<TUserAccount> {

    public Page queryPage(Page page, TUserAccount userAccount, TUser user, TUser currentUser, String startTime, String endTime, TUserInfo userInfo, Integer belongUserId, Integer parentCodeIndex ) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  ua.user_id               AS userId,");
        sql.append("  ua.money                 AS money,");
        sql.append("  ua.freezing_money        AS freezingMoney,");
        sql.append("  ua.integral              AS integral,");
        sql.append("  ua.create_time           AS createTime,");
        sql.append("  ua.update_time           AS updateTime,");
        sql.append("  parentUser.login_name    AS parentLoginName,");
        sql.append("  ui.user_name             AS userName,");
        sql.append("  ui.status                AS status,");
        sql.append("  u.login_name             AS loginName,");
        sql.append("  u.user_status            AS userStatus ");
        sql.append(" from ");//###这个一定得小写，不然后面hql查询分页信息会报错###
        sql.append("  t_user_account ua");
        sql.append("  LEFT JOIN t_user u");
        sql.append("    ON u.user_id = ua.user_id ");
        sql.append(" LEFT JOIN t_user parentUser  ON parentUser.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) ");
        sql.append(" LEFT JOIN t_user_info ui  ON ui.user_id = ua.user_id ");
        sql.append(" WHERE 1 = 1 ");
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!= Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
            sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
        if (StringUtils.isNotEmpty(user.getLoginName())) {
            sql.append("      AND u.login_name like :loginName ");
            map.put("loginName", "%" + user.getLoginName() + "%");
        }
        if (EmptyUtils.isNotEmpty(user.getUserType())) {
            sql.append("      AND u.user_type = :userType ");
            map.put("userType",user.getUserType());
        }
        if (EmptyUtils.isNotEmpty(user.getUserStatus())) {
            sql.append("      AND u.user_status = :userStatus ");
            map.put("userStatus",user.getUserStatus());
        }
        if (EmptyUtils.isNotEmpty(userInfo)&&EmptyUtils.isNotEmpty(userInfo.getUserName())) {
            sql.append("      AND ui.user_name like :userName ");
            map.put("userName","%"+userInfo.getUserName()+"%");
        }
        if (EmptyUtils.isNotEmpty(userInfo)&&EmptyUtils.isNotEmpty(userInfo.getStatus())) {
            sql.append("      AND ui.status = :status ");
            map.put("status",userInfo.getStatus());
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
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }
}
