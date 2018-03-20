package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.web.UserInfoDao;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends BaseServiceImpl<TUserInfo> {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public TUserInfo save(TUserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }


    public Page queryPage(Page page, TUserInfo userInfo, TUser user) {
        return userInfoDao.queryPage(page, userInfo,user);
    }
}
