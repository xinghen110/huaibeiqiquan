package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.web.UserInfoCheckDao;
import com.ruanyun.web.dao.web.UserInfoDao;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.TUserInfoCheck;
import com.ruanyun.web.model.sys.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoCheckService extends BaseServiceImpl<TUserInfoCheck> {

    @Autowired
    private UserInfoCheckDao userInfoCheckDao;

    /**
     * 根据用户id和审核状态查询用户信息审核表
     * @param userId
     * @param checkResult
     * @return
     */
    public TUserInfoCheck queryUserInfoCheck(int userId, String checkResult) {
        TUserInfoCheck userInfoCheck = new TUserInfoCheck();
        userInfoCheck.setUserId(userId);
        userInfoCheck.setCheckResult(checkResult);
        return userInfoCheckDao.queryUserInfoCheck(userInfoCheck);
    }


    /**
     * 功能描述：查询用户信息审核表
     * @param page
     * @param userInfoCheck
     * @return
     */
    public Page<TUserInfoCheck> queryUserInfoCheckList(Page<TUserInfoCheck> page, TUserInfoCheck userInfoCheck,TUser user,TUser currentUser,Integer belongUserId,Integer parentCodeIndex){
//        return userInfoCheckDao.queryUserInfoCheckList(page, userInfoCheck);
        return userInfoCheckDao.queryPage(page,userInfoCheck,user,currentUser,belongUserId,parentCodeIndex);
    }
}
