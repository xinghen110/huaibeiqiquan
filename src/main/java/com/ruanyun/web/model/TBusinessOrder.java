package com.ruanyun.web.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_business_order"
        , uniqueConstraints = @UniqueConstraint(columnNames = "order_sn")
)
public class TBusinessOrder implements java.io.Serializable {


    private Integer businessOrderId;
    private String orderSn;
    private Integer buyerUserId;
    private Integer sellerUserId;
    private Integer type;
    private Integer status;
    private Integer isPaid;
    private Integer isFinish;
    private Integer transportStatus;
    private BigDecimal money;
    private Integer integral;
    private Integer giveIntegral;
    private String title;
    private String content;
    private String image;
    private Date createTime;
    private Date paidTime;
    private Integer buyerHasComment;
    private Integer sellerHasComment;
    private String flag1;
    private String flag2;
    private String consignee;
    private String areaCode;
    private String areaName;
    private String addressName;
    private String mobile;
    private Integer isDelete;
    private Integer isRefund;
    private String refundReason;
    private Date refundApplyTime;
    private Date refundTime;
    private String refundHandleRemark;
    private String buyerComment;
    private String sellerComment;

    public TBusinessOrder() {
    }

    public TBusinessOrder(String orderSn, Integer buyerUserId, Integer sellerUserId, Integer type, Integer status, Integer isPaid, Integer isFinish, Integer transportStatus, BigDecimal money, Integer integral, Integer giveIntegral, String title, String content, String image, Date createTime, Date paidTime, Integer buyerHasComment, Integer sellerHasComment, String flag1, String flag2, String consignee, String areaCode, String areaName, String addressName, String mobile, Integer isDelete, Integer isRefund, String refundReason, Date refundApplyTime, Date refundTime, String refundHandleRemark, String buyerComment, String sellerComment) {
        this.orderSn = orderSn;
        this.buyerUserId = buyerUserId;
        this.sellerUserId = sellerUserId;
        this.type = type;
        this.status = status;
        this.isPaid = isPaid;
        this.isFinish = isFinish;
        this.transportStatus = transportStatus;
        this.money = money;
        this.integral = integral;
        this.giveIntegral = giveIntegral;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createTime = createTime;
        this.paidTime = paidTime;
        this.buyerHasComment = buyerHasComment;
        this.sellerHasComment = sellerHasComment;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.consignee = consignee;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.addressName = addressName;
        this.mobile = mobile;
        this.isDelete = isDelete;
        this.isRefund = isRefund;
        this.refundReason = refundReason;
        this.refundApplyTime = refundApplyTime;
        this.refundTime = refundTime;
        this.refundHandleRemark = refundHandleRemark;
        this.buyerComment = buyerComment;
        this.sellerComment = sellerComment;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "business_order_id", unique = true, nullable = false)
    public Integer getBusinessOrderId() {
        return this.businessOrderId;
    }

    public void setBusinessOrderId(Integer businessOrderId) {
        this.businessOrderId = businessOrderId;
    }

    @Column(name = "order_sn", unique = true, length = 32)
    public String getOrderSn() {
        return this.orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Column(name = "buyer_user_id")
    public Integer getBuyerUserId() {
        return this.buyerUserId;
    }

    public void setBuyerUserId(Integer buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    @Column(name = "seller_user_id")
    public Integer getSellerUserId() {
        return this.sellerUserId;
    }

    public void setSellerUserId(Integer sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    @Column(name = "type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "is_paid")
    public Integer getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @Column(name = "is_finish")
    public Integer getIsFinish() {
        return this.isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    @Column(name = "transport_status")
    public Integer getTransportStatus() {
        return this.transportStatus;
    }

    public void setTransportStatus(Integer transportStatus) {
        this.transportStatus = transportStatus;
    }

    @Column(name = "money", precision = 18)
    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Column(name = "integral")
    public Integer getIntegral() {
        return this.integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Column(name = "give_integral")
    public Integer getGiveIntegral() {
        return this.giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    @Column(name = "title", length = 65535)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", length = 65535)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "image", length = 65535)
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paid_time", length = 19)
    public Date getPaidTime() {
        return this.paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    @Column(name = "buyer_has_comment")
    public Integer getBuyerHasComment() {
        return this.buyerHasComment;
    }

    public void setBuyerHasComment(Integer buyerHasComment) {
        this.buyerHasComment = buyerHasComment;
    }

    @Column(name = "seller_has_comment")
    public Integer getSellerHasComment() {
        return this.sellerHasComment;
    }

    public void setSellerHasComment(Integer sellerHasComment) {
        this.sellerHasComment = sellerHasComment;
    }

    @Column(name = "flag1", length = 65535)
    public String getFlag1() {
        return this.flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    @Column(name = "flag2", length = 65535)
    public String getFlag2() {
        return this.flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    @Column(name = "consignee", length = 60)
    public String getConsignee() {
        return this.consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Column(name = "area_code", length = 16)
    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "area_name", length = 16)
    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "address_name", length = 65535)
    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Column(name = "mobile", length = 16)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "is_delete")
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "is_refund")
    public Integer getIsRefund() {
        return this.isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    @Column(name = "refund_reason", length = 65535)
    public String getRefundReason() {
        return this.refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "refund_apply_time", length = 19)
    public Date getRefundApplyTime() {
        return this.refundApplyTime;
    }

    public void setRefundApplyTime(Date refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "refund_time", length = 19)
    public Date getRefundTime() {
        return this.refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    @Column(name = "refund_handle_remark", length = 65535)
    public String getRefundHandleRemark() {
        return this.refundHandleRemark;
    }

    public void setRefundHandleRemark(String refundHandleRemark) {
        this.refundHandleRemark = refundHandleRemark;
    }

    @Column(name = "buyer_comment", length = 65535)
    public String getBuyerComment() {
        return this.buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    @Column(name = "seller_comment", length = 65535)
    public String getSellerComment() {
        return this.sellerComment;
    }

    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }


}


