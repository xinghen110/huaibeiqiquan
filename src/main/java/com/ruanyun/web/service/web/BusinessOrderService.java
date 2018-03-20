package com.ruanyun.web.service.web;

import com.ruanyun.common.model.Page;
import com.ruanyun.common.service.impl.BaseServiceImpl;
import com.ruanyun.web.model.PaymentNotifyModel;
import com.ruanyun.web.model.TBusinessOrder;
import com.ruanyun.web.model.TUserAccountFlow;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Service
public class BusinessOrderService extends BaseServiceImpl<TBusinessOrder> {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    private UserAccountFlowService userAccountFlowService;


//    @Autowired
//    UserAccountService userAccountService;
//    @Autowired
//    UserAccountFlowService userAccountFlowService;

//    public Map getMap(TBusinessOrder businessOrder) {
//        Page page = new Page();
//        page.set(2);
//        List<Map> list = list(businessOrder, page, null, null, null);
//        if (list.size() == 0) {
//            throw new BusinessLogicException("没有获取到该订单");
//        } else if (list.size() > 1) {
//            throw new BusinessLogicException("数据有误，获取到多条订单");
//        }
//        Map orderMap = list.get(0);
//        return orderMap;
//    }

//    public TBusinessOrder get(TBusinessOrder businessOrder) {
//        Map map = getMap(businessOrder);
//        businessOrder = new TBusinessOrder();
//        businessOrder.setBusinessOrderId(Integer.parseInt(map.get("businessOrderId").toString()));
//        businessOrder = baseDaoPlus.get(businessOrder);
//        if (businessOrder == null) {
//            throw new BusinessLogicException("该业务订单不存在");
//        }
//        return businessOrder;
//    }

    public static String createNo(String businessNumber) {
        Date now = new Date();
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(11, 0);
        currentDate.set(12, 0);
        currentDate.set(13, 0);
        Date beginTime = currentDate.getTime();
        long second = (now.getTime() - beginTime.getTime()) / 1000L;
        String secondNumber = String.format("%05d", second);
        String nano = String.valueOf(System.nanoTime());
        String nanoNumber = nano.substring(nano.length() - 2);
        StringBuilder number = new StringBuilder();
        number.append(businessNumber).append(DateFormatUtils.format(now, "yyMMdd")).append(secondNumber).append(nanoNumber);
        return number.toString();
    }

//    public SqlBuffer sqlWithMe(SqlBuffer sql, Integer userId) {
//        sql.append("      AND (tbo.buyer_user_id = :tbo.buyer_user_id   ", userId);
//        sql.append("      OR tbo.seller_user_id = :tbo.seller_user_id ) ", userId);
//        return sql;
//    }

//    public List<Map> list(TBusinessOrder businessOrder, Page page, String buyerUsername, String sellerUsername, String[] status) {
//        SqlBuffer sql = sqlSelect();
//        if (businessOrder.getBuyerUserId() != null && businessOrder.getSellerUserId() != null
//                && businessOrder.getBuyerUserId().intValue() == businessOrder.getSellerUserId().intValue()) {
//            sqlWithMe(sql, businessOrder.getBuyerUserId());
//        } else {
//            sql.append("      AND tbo.buyer_user_id = :tbo.buyer_user_id ", businessOrder.getBuyerUserId());
//            sql.append("      AND tbo.seller_user_id = :tbo.seller_user_id ", businessOrder.getSellerUserId());
//        }
//        sql.append("      AND buyer.mobile LIKE :buyer.mobile ", StringUtils.isEmpty(buyerUsername) ? null : buyerUsername);
//        sql.append("      AND seller.mobile LIKE :seller.mobile ", StringUtils.isEmpty(sellerUsername) ? null : sellerUsername);
//        sql.append("      AND tbo.business_order_id = :tbo.business_order_id ", businessOrder.getBusinessOrderId());
//        if (!StringUtils.isEmpty(businessOrder.getOrderSn()) && businessOrder.getOrderSn().startsWith("%"))
//            sql.append("      AND tbo.order_sn LIKE :tbo.order_sn ", StringUtils.isEmpty(businessOrder.getOrderSn()) ? null : businessOrder.getOrderSn());
//        else
//            sql.append("      AND tbo.order_sn = :tbo.order_sn ", StringUtils.isEmpty(businessOrder.getOrderSn()) ? null : businessOrder.getOrderSn());
//        sql.append("      AND tbo.type = :tbo.type ", businessOrder.getType());
//        sql.append("      AND tbo.status = :tbo.status ", businessOrder.getStatus());
//        sql.append("      AND tbo.status IN ( :statusArray ) ", ArrayUtils.isEmpty(status) ? null : status);
//        sql.append("      AND tbo.is_paid = :tbo.is_paid ", businessOrder.getIsPaid());
//        sql.append("      AND tbo.is_finish = :tbo.is_finish ", businessOrder.getIsFinish());
//        sql.append("      AND tbo.money = :tbo.money ", businessOrder.getMoney());
//        sql.append("      AND tbo.title = :tbo.title ", businessOrder.getTitle());
//        sql.append("      AND tbo.content = :tbo.content ", businessOrder.getContent());
//        sql.append("      AND tbo.image = :tbo.image ", businessOrder.getImage());
//        sql.append("      AND tbo.create_time = :tbo.create_time ", businessOrder.getCreateTime());
//        sql.append("      AND tbo.buyer_has_comment = :tbo.buyer_has_comment ", businessOrder.getBuyerHasComment());
//        sql.append("      AND tbo.seller_has_comment = :tbo.seller_has_comment ", businessOrder.getSellerHasComment());
//        sql.append("      AND tbo.is_delete = :tbo.is_delete ", businessOrder.getIsDelete());
//        sql.append("      AND tbo.refund_reason IS NOT NULL ", !StringUtils.isEmpty(businessOrder.getRefundReason()) && businessOrder.getRefundReason().equals("1") ? "" : null);
//        sql.append(" ORDER BY tbo.create_time DESC");
//        List<Map> orderList = baseDaoPlus.list(sql.toString(), sql.getParam(), page);
//        //根据orderSn，插入多个商品
//        if (orderList.size() > 0) {
//            StringBuilder orderSnArray = new StringBuilder();
//            for (int i = 0; i < orderList.size(); i++) {
//                orderSnArray.append(orderList.get(i).get("orderSn"));
//                if (i < orderList.size() - 1) {
//                    orderSnArray.append(",");
//                }
//            }
//            TBusinessOrderGoods businessOrderGoods = new TBusinessOrderGoods();
//            businessOrderGoods.setOrderSn(orderSnArray.toString());
//            List<Map> orderGoodsList = businessOrderGoodsService.list(businessOrderGoods, null);
//            for (int i = 0; i < orderList.size(); i++) {
//                Map order = orderList.get(i);
//                List<Map> filteredGoodsList = new ArrayList<Map>();
//                Iterator<Map> filteredGoodsIterator = orderGoodsList.iterator();
//                while (filteredGoodsIterator.hasNext()) {
//                    Map orderGoods = filteredGoodsIterator.next();
//                    if (orderGoods.get("orderSn").equals(order.get("orderSn"))) {
//                        filteredGoodsList.add(orderGoods);
//                        filteredGoodsIterator.remove();
//                    }
//                }
//                order.put("goods", filteredGoodsList);
//            }
//        }
//        return orderList;
//    }

    public StringBuilder sqlSelect() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("  tbo.business_order_id            AS businessOrderId, ");
        sql.append("  tbo.order_sn                     AS orderSn, ");
        sql.append("  tbo.buyer_user_id                AS buyerUserId, ");
        sql.append("  tbo.seller_user_id               AS sellerUserId, ");
        sql.append("  tbo.type                         AS type, ");
        sql.append("  tbo.status                       AS status, ");
        sql.append("  tbo.is_paid                      AS isPaid, ");
        sql.append("  tbo.is_finish                    AS isFinish, ");
        sql.append("  tbo.money                        AS money, ");
        sql.append("  tbo.title                        AS title, ");
        sql.append("  tbo.content                      AS content, ");
        sql.append("  tbo.image                        AS image, ");
        sql.append("  tbo.create_time                  AS createTime, ");
        sql.append("  tbo.paid_time                    AS paidTime, ");
        sql.append("  tbo.buyer_has_comment            AS buyerHasComment, ");
        sql.append("  tbo.seller_has_comment           AS sellerHasComment, ");
        sql.append("  tbo.flag1                        AS flag1, ");
        sql.append("  tbo.flag2                        AS flag2, ");
        sql.append("  tbo.consignee                    AS consignee, ");
        sql.append("  tbo.address_name                 AS addressName, ");
        sql.append("  tbo.mobile                       AS mobile, ");
        sql.append("  tbo.is_delete                    AS isDelete, ");
        sql.append("  tbo.is_refund                    AS isRefund, ");
        sql.append("  tbo.refund_reason                AS refundReason, ");
        sql.append("  tbo.refund_apply_time            AS refundApplyTime, ");
        sql.append("  tbo.refund_time                  AS refundTime, ");
        sql.append("  tbo.buyer_comment                AS buyerComment, ");
        sql.append("  tbo.seller_comment               AS sellerComment, ");
        sql.append("  buyerUser.login_name             AS buyerLoginName, ");
        sql.append("  buyer.userName                   AS buyerUserName, ");
        sql.append("  buyer.idNumber                   AS buyerIdNumber ");
        sql.append("FROM t_business_order tbo ");
        sql.append("    LEFT JOIN t_user buyerUser ON buyer.user_id = tbo.buyer_user_id ");
        sql.append("    LEFT JOIN t_user_info buyer ON buyer.user_id = tbo.buyer_user_id ");
        sql.append("WHERE 1 = 1 ");
        return sql;
    }

    public Page page(Page page, TBusinessOrder businessOrder) {
        page = baseDao.queryPage(page, businessOrder);
        return page;
    }

    /**
     * 创建订单
     *
     * @param businessOrder
     * @return
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TBusinessOrder save(TBusinessOrder businessOrder) {

        String orderSn = createNo("jn");
        businessOrder.setOrderSn(orderSn);

        //订单初始化
        TBusinessOrder sourceOrder = new TBusinessOrder(
                orderSn,
                businessOrder.getBuyerUserId(),
                0,
                businessOrder.getType(),        //type 0提现 1充值 提现应该是不用做的
                0,
                0,
                0,
                0,
                businessOrder.getMoney(),
                0,
                0,
                businessOrder.getTitle(),
                businessOrder.getContent(),
                "",
                new Date(),
                null,
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                businessOrder.getMobile(),
                0,
                -1,
                null,
                null,
                null,
                "",
                "",
                ""
        );

        //存订单
        sourceOrder = super.save(sourceOrder);
        return sourceOrder;
    }


    /**
     * 支付订单
     *
     * @param businessOrderSn
     * @return
     */
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    public Map pay(String businessOrderSn) {
//        TBusinessOrder businessOrder = new TBusinessOrder();
//        businessOrder.setOrderSn(businessOrderSn);
//        businessOrder = get(businessOrder);
//
//        if (businessOrder.getIsPaid().intValue() == -1) {
//            throw new BusinessLogicException("该类订单无法进行支付");
//        }
//        if (businessOrder.getIsPaid().intValue() == 1) {
//            throw new BusinessLogicException("该订单已经支付过了");
//        }
//
//        TTradeOrder tradeOrder = tradeOrderService.save(businessOrder);
//        Map map = new HashMap();
//        map.put("orderSn", tradeOrder.getTradeOrderSn());
//        map.put("subject", tradeOrder.getSubject());
//        map.put("body", tradeOrder.getBody());
//        map.put("total_fee", tradeOrder.getTotalFee());
//        return map;
//    }

    /**
     * 成功支付后处理
     *
     * @param paymentNotifyModel
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void paySuccess(PaymentNotifyModel paymentNotifyModel) {
        TBusinessOrder businessOrder = super.get(TBusinessOrder.class, "orderSn", paymentNotifyModel.getOrderNo());

        if (businessOrder.getIsPaid().intValue() == 0) {
            //更新订单状态
            businessOrder.setStatus(1);
            businessOrder.setIsPaid(1);
            businessOrder.setPaidTime(new Date());
            super.update(businessOrder);
            //增加一笔入金流水
            TUserAccountFlow flow = new TUserAccountFlow(
                    businessOrder.getBuyerUserId(),
                    businessOrder.getMoney(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "transactionOrder",
                    "",
                    businessOrder.getOrderSn(),
                    0,
                    "入金充值",
                    new Date()
            );
            userAccountFlowService.save(flow);
        } else {
            logger.debug(String.format("订单%s状态已经是已支付", businessOrder.getOrderSn()));
        }
    }


}
