package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserInfoDao extends BaseDaoImpl<TUserInfo> {


    public Page queryPage(Page page, TUserInfo userInfo, TUser user) {
        Map map = new HashMap();
        StringBuffer  sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("  tui.user_id            AS userId,");
        sql.append("  tui.user_name          AS userName,");
        sql.append("  tui.id_number          AS idNumber,");
        sql.append("  tui.id_card_front_view AS idCardFrontView,");
        sql.append("  tui.id_card_back_view  AS idCardBackView,");
        sql.append("  tui.bank_id            AS bankId,");
        sql.append("  tui.bank_card_number   AS bankCardNumber,");
        sql.append("  tui.back_card_photo    AS backCardPhoto,");
        sql.append("  tui.status             AS status,");
        sql.append("  u.login_name            AS loginName");
        sql.append("  from t_user_info tui LEFT JOIN t_user u ON u.user_id = tui.user_id  where 1=1");

        if(EmptyUtils.isNotEmpty(userInfo)&& EmptyUtils.isNotEmpty(userInfo.getUserName())){
            sql.append("      AND tui.user_name like :userName ");
            map.put("userName","%"+userInfo.getUserName()+"%");
        }
        if(EmptyUtils.isNotEmpty(userInfo)&& EmptyUtils.isNotEmpty(userInfo.getStatus())){
            sql.append("      AND tui.status = :status ");
            map.put("status",userInfo.getStatus());
        }
        if(EmptyUtils.isNotEmpty(user)&& EmptyUtils.isNotEmpty(user.getUserType())){
            sql.append("      AND u.user_type = :userType ");
            map.put("userType",user.getUserType());
        }

        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }
}
