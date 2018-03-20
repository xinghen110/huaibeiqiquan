package com.ruanyun.web.service.web;

import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.model.TUserAccountFlow;
import com.ruanyun.web.model.TUserAccountOrder;
import com.ruanyun.web.model.sys.TDictionary;
import com.ruanyun.web.service.sys.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserAccountOrderService extends BaseServiceImpl<TUserAccountOrder> {


    @Autowired
    private UserAccountFlowService userAccountFlowService;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public TUserAccountOrder save(TUserAccountOrder x) {
        x = new TUserAccountOrder(
                x.getUserId(),
                x.getMoney(),
                x.getRemark(),
                x.getOrderType(),
                "0",
                x.getBankId(),
                x.getBankCardNumber(),
                new Date(),
                0,
                "",
                null
        );
        super.save(x);

        if (x.getOrderType().equals("withdrawOrder")) {
            //当流水的来源是提现订单时,需要额外增加扣取手续费功能

            //提现金额不能小于10元,不能大于100000
            if (x.getMoney().compareTo(BigDecimal.TEN) < 0) {
                throw new RuntimeException("提现金额不能小于10元");
            } else if (x.getMoney().compareTo(new BigDecimal(100000)) > 0) {
                throw new RuntimeException("单笔提现金额不能大于10万元");
            }

            //手续费 CHARGE_FEE
            TDictionary chargeFeeData = dictionaryService.getDictionary("CHARGE_FEE", true);
            BigDecimal chargeFee = new BigDecimal(chargeFeeData.getItemCode());

            //存提现金额流水
            TUserAccountFlow withdrawFlow = new TUserAccountFlow(
                    x.getUserId(),
                    x.getMoney().subtract(chargeFee).negate(),//从提现金额中扣除手续费
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "withdrawOrder",
                    "",
                    x.getOrderId().toString(),
                    0,
                    "用户申请提现",
                    new Date()
            );
            userAccountFlowService.save(withdrawFlow, false);

            //存提现手续费流水
            TUserAccountFlow serviceFeeFlow = new TUserAccountFlow(
                    x.getUserId(),
                    chargeFee.negate(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "withdrawOrderFee",
                    "",
                    x.getOrderId().toString(),
                    0,
                    "提现手续费",
                    new Date()
            );
            userAccountFlowService.save(serviceFeeFlow, false);
        }
        return x;
    }

    @Transactional
    public TUserAccountOrder handleWithdraw(TUserAccountOrder x) {
        TUserAccountOrder userAccountOrder = get(TUserAccountOrder.class, x.getOrderId());

        if (!userAccountOrder.getOrderStatus().equals("0")) {
            throw new RuntimeException("不能重复审批订单");
        }

        userAccountOrder.setUpdateTime(new Date());
        userAccountOrder.setOrderStatus(x.getOrderStatus());
        userAccountOrder.setHandleUserId(x.getHandleUserId());
//        userAccountOrder.setHandleResult(x.getHandleResult());
        userAccountOrder = super.update(userAccountOrder);

        if (userAccountOrder.getOrderStatus().equals("1")) {
            //确认允许提现成功
            //由于金额改为在用户申请提现的时候就已经扣过了.这里不再扣除
        } else {
            //拒绝提现时,进行退还
            TUserAccountFlow flow = new TUserAccountFlow(
                    userAccountOrder.getUserId(),
                    userAccountOrder.getMoney(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "withdrawOrder",
                    "",
                    userAccountOrder.getOrderId().toString(),
                    0,
                    "用户申请提现被拒绝",
                    new Date()
            );
            userAccountFlowService.save(flow);
        }
        return userAccountOrder;
    }
}
