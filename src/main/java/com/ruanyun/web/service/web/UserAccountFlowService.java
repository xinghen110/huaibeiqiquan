package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.dao.web.UserAccountFlowDao;
import com.ruanyun.web.model.TUserAccount;
import com.ruanyun.web.model.TUserAccountFlow;
import com.ruanyun.web.model.TUserInfo;
import com.ruanyun.web.model.mall.TUserCenter;
import com.ruanyun.web.model.sys.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class UserAccountFlowService extends BaseServiceImpl<TUserAccountFlow> {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    private UserAccountFlowDao userAccountFlowDao;

    /**
     * 存金钱流水
     * @param flow
     * @return
     */
    public TUserAccountFlow save(TUserAccountFlow flow) {
        return save(flow, true);
    }

    /**
     * 存金钱流水
     * @param flow
     * @param moneyCanBeNegative 计算后的钱是否可以为负数
     * @return
     */
    public TUserAccountFlow save(TUserAccountFlow flow, boolean moneyCanBeNegative) {
        Date now = new Date();

        TUserAccount userAccount = userAccountService.get(TUserAccount.class, flow.getUserId());
        flow.setBeforeMoney(userAccount.getMoney());
        userAccount.setMoney(userAccount.getMoney().add(flow.getMoney()));
        userAccount.setUpdateTime(now);

        if (!moneyCanBeNegative && userAccount.getMoney().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("账户资金不足");
        }
        flow.setAfterMoney(userAccount.getMoney());
        flow.setCreateTime(now);

        TUserAccountFlow tUserAccountFlow = super.save(flow);
        userAccountService.update(userAccount);
        return tUserAccountFlow;
    }

    public Page<TUserAccountFlow> queryUserAccountFlowList(Page page, TUserAccountFlow userAccountFlow, TUser user,TUser currentUser,String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex){
        return userAccountFlowDao.queryPage(page,userAccountFlow,user,currentUser, startTime, endTime, userInfo,belongUserId,parentCodeIndex);
    }

    public List groupQueryUserAccountFlowList(TUserAccountFlow userAccountFlow, TUser user,TUser currentUser,String startTime, String endTime, TUserInfo userInfo,Integer belongUserId,Integer parentCodeIndex){
        return userAccountFlowDao.groupQueryPage(userAccountFlow,user,currentUser, startTime, endTime, userInfo,belongUserId,parentCodeIndex );
    }

    /**
     * 查询资金流水总和
     * @param page
     * @param userAccountFlow
     * @param selectType
     * @return
     */
    public Page queryCapitalflow(Page page, TUserAccountFlow userAccountFlow,String selectType,TUser user,String loginName, String startTime, String endTime,Integer belongUserId,String userName,Integer parentCodeIndex) {
        return userAccountFlowDao.queryPage(page,userAccountFlow,selectType,user,loginName,startTime,endTime,belongUserId,userName,parentCodeIndex);
    }


    /**
     * 功能描述: 增加流水
     * 创建者: zhangwei
     * 创建时间: 2018/03/02 14:06
     * @param userId 用户id
     * @param money 消费金额
     * @param flowType 流水类型
     * @param flowSource 流水来源
     * @param flowRemark 备注
     */
    public TUserAccountFlow save(TUserAccount userAccount,Integer userId,BigDecimal money,String flowType,String flowSource,String flowRemark){

        BigDecimal oldMoney=userAccount.getMoney();
        BigDecimal beforeMoney=oldMoney.subtract(money);

        TUserAccountFlow userAccountFlow=new TUserAccountFlow();
        userAccountFlow.setUserId(userId);
        userAccountFlow.setMoney(money);
        userAccountFlow.setBeforeMoney(beforeMoney);
        userAccountFlow.setAfterMoney(userAccount.getMoney());
        userAccountFlow.setFlowType(flowType);
        userAccountFlow.setFlowSource(flowSource);
        userAccountFlow.setFlowRemark(flowRemark);
        userAccountFlow.setCreateTime(new Date());
        userAccountFlow.setAfterIntegral(new BigDecimal(0));
        userAccountFlow.setFreezingMoney(new BigDecimal(0));
        userAccountFlow.setIntegral(new BigDecimal(0));
        userAccountFlow.setAfterFreezingMoney(new BigDecimal(0));
        userAccountFlow.setFlowSourceType(0);


        return super.save(userAccountFlow);

    }


}
