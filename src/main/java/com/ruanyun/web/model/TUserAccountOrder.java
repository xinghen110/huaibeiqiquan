package com.ruanyun.web.model;
// Generated 2017-10-7 10:38:30 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TUserAccountOrder generated by hbm2java
 */
@Entity
@Table(name="t_user_account_order")
public class TUserAccountOrder  implements java.io.Serializable {


     private Integer orderId;
     private Integer userId;
     private BigDecimal money;
     private String remark;
     private String orderType;
     private String orderStatus;
     private String bankId;
     private String bankCardNumber;
     private Date createTime;
     private Integer handleUserId;
     private String handleResult;
     private Date updateTime;

    public TUserAccountOrder() {
    }

    public TUserAccountOrder(Integer userId, BigDecimal money, String remark, String orderType, String orderStatus, String bankId, String bankCardNumber, Date createTime, Integer handleUserId, String handleResult, Date updateTime) {
       this.userId = userId;
       this.money = money;
       this.remark = remark;
       this.orderType = orderType;
       this.orderStatus = orderStatus;
       this.bankId = bankId;
       this.bankCardNumber = bankCardNumber;
       this.createTime = createTime;
       this.handleUserId = handleUserId;
       this.handleResult = handleResult;
       this.updateTime = updateTime;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="order_id", unique=true, nullable=false)
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    @Column(name="user_id")
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="money", precision=18)
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    @Column(name="remark", length=65535)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="order_type", length=32)
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    @Column(name="order_status", length=32)
    public String getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    @Column(name="bank_id", length=32)
    public String getBankId() {
        return this.bankId;
    }
    
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    
    @Column(name="bank_card_number", length=32)
    public String getBankCardNumber() {
        return this.bankCardNumber;
    }
    
    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="handle_user_id")
    public Integer getHandleUserId() {
        return this.handleUserId;
    }
    
    public void setHandleUserId(Integer handleUserId) {
        this.handleUserId = handleUserId;
    }
    
    @Column(name="handle_result", length=32)
    public String getHandleResult() {
        return this.handleResult;
    }
    
    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time", length=19)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }




}


