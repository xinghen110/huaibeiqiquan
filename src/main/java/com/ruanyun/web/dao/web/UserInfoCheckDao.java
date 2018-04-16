package com.ruanyun.web.dao.web;

import com.ruanyun.common.dao.impl.BaseDaoImpl;
import com.ruanyun.common.model.Page;
import com.ruanyun.common.utils.EmptyUtils;
import com.ruanyun.common.utils.SQLUtils;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.TUserInfoCheck;
import com.ruanyun.web.model.sys.TUser;
import com.ruanyun.web.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserInfoCheckDao extends BaseDaoImpl<TUserInfoCheck> {

    @Override
    protected String queryPageSql(TUserInfoCheck tUserInfoCheck, Map<String, Object> params) {
        StringBuffer  sql = new StringBuffer("from TUserInfoCheck where 1=1 ");//and userStatus=1
        if(tUserInfoCheck!=null){
            sql.append(SQLUtils.popuHqlEq("userId", tUserInfoCheck.getUserId(),params));
            sql.append(SQLUtils.popuHqlLike("userName", tUserInfoCheck.getUserName(),params));
            sql.append(SQLUtils.popuHqlLike("idNumber",tUserInfoCheck.getIdNumber(),params));
            sql.append(SQLUtils.popuHqlEq("idCardFrontView",tUserInfoCheck.getIdCardFrontView(),params));
            sql.append(SQLUtils.popuHqlEq("idCardBackView",tUserInfoCheck.getIdCardBackView(),params));
            sql.append(SQLUtils.popuHqlEq("bankId",tUserInfoCheck.getBankId(),params));
            sql.append(SQLUtils.popuHqlEq("bankCardNumber",tUserInfoCheck.getBankCardNumber(),params));
            sql.append(SQLUtils.popuHqlEq("backCardPhoto",tUserInfoCheck.getBackCardPhoto(),params));
            sql.append(SQLUtils.popuHqlEq("checkResult",tUserInfoCheck.getCheckResult(),params));
        }
        //sql.append(" ORDER BY createTime DESC");
        return sql.toString();
    }

    /**
     * 功能描述：获取未审核的银行卡绑定
     * @param page
     * @param userInfoCheck
     * @return
     */
    public Page<TUserInfoCheck> queryUserInfoCheckList(Page<TUserInfoCheck> page, TUserInfoCheck userInfoCheck) {
        StringBuffer hql = new StringBuffer("from TUserInfoCheck where 1=1 ");
        if (EmptyUtils.isNotEmpty(userInfoCheck)) {
            hql.append(SQLUtils.popuHqlEq("id", userInfoCheck.getId()));
            hql.append(SQLUtils.popuHqlEq("userId", userInfoCheck.getUserId()));
            hql.append(SQLUtils.popuHqlLike("userName", userInfoCheck.getUserName()));
            hql.append(SQLUtils.popuHqlLike("idNumber",userInfoCheck.getIdNumber()));
            hql.append(SQLUtils.popuHqlEq("idCardFrontView",userInfoCheck.getIdCardFrontView()));
            hql.append(SQLUtils.popuHqlEq("idCardBackView",userInfoCheck.getIdCardBackView()));
            hql.append(SQLUtils.popuHqlEq("bankId",userInfoCheck.getBankId()));
            hql.append(SQLUtils.popuHqlEq("bankCardNumber",userInfoCheck.getBankCardNumber()));
            hql.append(SQLUtils.popuHqlEq("backCardPhoto",userInfoCheck.getBackCardPhoto()));
            hql.append(SQLUtils.popuHqlEq("checkResult",userInfoCheck.getCheckResult()));
        }
        return hqlDao.queryPage(page, hql.toString());
    }

    public TUserInfoCheck queryUserInfoCheck(TUserInfoCheck userInfoCheck) {
        String sql = "from TUserInfoCheck where userId = '"+userInfoCheck.getUserId()+"' and checkResult="+userInfoCheck.getCheckResult()+"";
        return hqlDao.get(sql.toString());
    }


    public Page queryPage(Page page, TUserInfoCheck userInfoCheck,TUser user,TUser currentUser,Integer belongUserId,Integer parentCodeIndex) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("  tuic.id                 AS id,");
        sql.append("  tuic.user_id            AS userId,");
        sql.append("  tuic.user_name          AS userName,");
        sql.append("  tuic.id_number          AS idNumber,");
        sql.append("  tuic.id_card_front_view AS idCardFrontView,");
        sql.append("  tuic.id_card_back_view  AS idCardBackView,");
        sql.append("  tuic.bank_id            AS bankId,");
        sql.append("  tuic.bank_card_number   AS bankCardNumber,");
        sql.append("  tuic.deposit_bank       AS depositBank,");
        sql.append("  tuic.back_card_photo    AS backCardPhoto,");
        sql.append("  u.login_name            AS loginName,");
        sql.append("  parentUser.login_name   AS parentUserLoginName,");
        sql.append("  tuic.check_result       AS checkResult");
        sql.append(" from t_user_info_check tuic LEFT JOIN t_user u on u.user_id = tuic.user_id ");
        sql.append(" LEFT JOIN t_user parentUser  ON parentUser.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) ");
        sql.append(" where 1=1 ");
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
            sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
        if (StringUtils.isNotEmpty(userInfoCheck.getUserName())) {
            sql.append("      AND tuic.user_name like :userName ");
            map.put("userName", "%" + userInfoCheck.getUserName() + "%");
        }
        if (EmptyUtils.isNotEmpty(user.getUserType())) {
            sql.append("      AND u.user_type = :userType ");
            map.put("userType", user.getUserType());
        }
        if (EmptyUtils.isNotEmpty(userInfoCheck.getCheckResult())) {
            sql.append("      AND tuic.check_result = :checkResult ");
            map.put("checkResult", userInfoCheck.getCheckResult());
        }
        if (EmptyUtils.isNotEmpty(userInfoCheck.getBankCardNumber())) {
            sql.append("      AND tuic.bank_card_number = :bankCardNumber ");
            map.put("bankCardNumber", userInfoCheck.getBankCardNumber());
        }
        if (EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(user.getLoginName())) {
            sql.append("      AND u.login_name like :loginName ");
            map.put("loginName", "%" + user.getLoginName() + "%");
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


    public Page queryPageList(Page page, TUserInfoCheck userInfoCheck,TUser user,TUser currentUser) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("  tuic.id                 AS id,");
        sql.append("  u.login_name            AS loginName,");
        sql.append("  tu.user_id              AS parentUserId,");
        sql.append("  tu.login_name		     AS parentLoginName,");
        sql.append("  tuic.user_id            AS userId,");
        sql.append("  tuic.user_name          AS userName,");
        sql.append("  tuic.id_number          AS idNumber,");
        sql.append("  tuic.id_card_front_view AS idCardFrontView,");
        sql.append("  tuic.id_card_back_view  AS idCardBackView,");
        sql.append("  tuic.bank_id            AS bankId,");
        sql.append("  tuic.bank_card_number   AS bankCardNumber,");
        sql.append("  tuic.back_card_photo    AS backCardPhoto,");
        sql.append("  tuic.check_result       AS checkResult");
        sql.append(" from t_user_info_check tuic LEFT JOIN t_user u on u.user_id = tuic.user_id ");
        sql.append(" LEFT JOIN t_user tu ON tu.user_id = SUBSTRING_INDEX(u.parent_code, ',', - 1) ");
        sql.append(" where 1=1 ");
        if(EmptyUtils.isNotEmpty(currentUser) &&  EmptyUtils.isNotEmpty(currentUser.getUserId())){
            if(currentUser.getUserType()!=Constants.USER_TYPE_MOTHER_01){
                sql.append(" and FIND_IN_SET("+currentUser.getUserId()+", u.parent_code) > 0");
            }
            sql.append(" and u.user_type >"+Constants.USER_TYPE_MOTHER_01);
        }
        if (StringUtils.isNotEmpty(userInfoCheck.getUserName())) {
            sql.append("      AND tuic.user_name like :userName ");
            map.put("userName", "%" + userInfoCheck.getUserName() + "%");
        }
        if (EmptyUtils.isNotEmpty(user.getUserType())) {
            sql.append("      AND u.user_type = :userType ");
            map.put("userType", user.getUserType());
        }
        Page queryPage = sqlDao.queryPage(sql.toString(),page,map);
        page.setResult(queryPage.getResult());
        page.setTotalCount(queryPage.getTotalCount());
        return page;
    }
//    SELECT
//    tuic.id                 AS id,
//    tuic.user_id            AS userId,
//    u.login_name            AS loginName,
//    u.parent_code           AS parentCode,
//    tu.`user_id`		AS parentUserId,
//    tu.login_name		AS parentLoginName,
//    tuic.user_name          AS userName,
//    tuic.id_number          AS idNumber,
//    tuic.id_card_front_view AS idCardFrontView,
//    tuic.id_card_back_view  AS idCardBackView,
//    tuic.bank_id            AS bankId,
//    tuic.bank_card_number   AS bankCardNumber,
//    tuic.back_card_photo    AS backCardPhoto,
//    tuic.check_result       AS checkResult
//    FROM t_user_info_check tuic
// #LEFT JOIN t_user u ON  tuic.user_id = u.user_id
//    LEFT JOIN t_user u ON  u.user_id = tuic.user_id
//

}
