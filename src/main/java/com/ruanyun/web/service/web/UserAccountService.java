package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.huanxin.Main;
import com.ruanyun.web.dao.web.UserAccountDao;
import com.ruanyun.web.model.TUserAccount;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.sys.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserAccountService extends BaseServiceImpl<TUserAccount> {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private UserAccountFlowService userAccountFlowService;

    /**
     * 在用户注册的同时将账户也注册掉
     * @param userId
     * @return
     */
    public TUserAccount save(int userId) {
        Date now = new Date();
        TUserAccount userAccount = new TUserAccount(
                userId,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                now,
                now
        );
        return super.save(userAccount);
    }

    public Page queryUserAccountList(Page page, TUserAccount userAccount, TUser user,TUser currentUser, String startTime, String endTime, TUserInfo userInfo, Integer belongUserId, Integer parentCodeIndex ){
        return userAccountDao.queryPage(page,userAccount,user,currentUser,startTime,endTime,userInfo,belongUserId ,parentCodeIndex );
    }


    public TUserAccount updateAccount(Integer userId,BigDecimal money,String orderNum){

        TUserAccount userAccount=super.get(TUserAccount.class,"userId",userId);

        BigDecimal afterMoney=userAccount.getMoney().add(money);

        userAccount.setMoney(afterMoney);

        //增加流水
        userAccountFlowService.save(userAccount,userId,money,"depositOrder",orderNum,"扫描充值");

        return super.update(userAccount);

    }



}
