package com.ruanyun.web.model;
// Generated 2017-10-1 16:29:54 by Hibernate Tools 3.2.2.GA


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
 * TUserAccountFlow generated by hbm2java
 */
@Entity
@Table(name="t_user_account_flow"
)
public class TUserAccountFlow  implements java.io.Serializable {


     private Integer flowId;
     private Integer userId;
     private BigDecimal money;
     private BigDecimal freezingMoney;
     private BigDecimal integral;
     private BigDecimal beforeMoney;
     private BigDecimal afterMoney;
     private BigDecimal afterFreezingMoney;
     private BigDecimal afterIntegral;
     private String flowType;
     private String createType;
     private String flowSource;
     private Integer flowSourceType;
     private String flowRemark;
     private Date createTime;

    public TUserAccountFlow() {
    }

    public TUserAccountFlow(Integer userId, BigDecimal money, BigDecimal freezingMoney, BigDecimal integral,BigDecimal beforeMoney, BigDecimal afterMoney, BigDecimal afterFreezingMoney, BigDecimal afterIntegral, String flowType, String createType, String flowSource, Integer flowSourceType, String flowRemark, Date createTime) {
       this.userId = userId;
       this.money = money;
       this.freezingMoney = freezingMoney;
       this.integral = integral;
       this.beforeMoney = beforeMoney;
       this.afterMoney = afterMoney;
       this.afterFreezingMoney = afterFreezingMoney;
       this.afterIntegral = afterIntegral;
       this.flowType = flowType;
       this.createType = createType;
       this.flowSource = flowSource;
       this.flowSourceType = flowSourceType;
       this.flowRemark = flowRemark;
       this.createTime = createTime;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="flow_id", unique=true, nullable=false)
    public Integer getFlowId() {
        return this.flowId;
    }
    
    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
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
    
    @Column(name="freezing_money", precision=18)
    public BigDecimal getFreezingMoney() {
        return this.freezingMoney;
    }
    
    public void setFreezingMoney(BigDecimal freezingMoney) {
        this.freezingMoney = freezingMoney;
    }
    
    @Column(name="integral", precision=18)
    public BigDecimal getIntegral() {
        return this.integral;
    }
    
    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }
    
    @Column(name="before_money", precision=18)
    public BigDecimal getBeforeMoney() {
        return this.beforeMoney;
    }
    
    public void setBeforeMoney(BigDecimal beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    @Column(name="after_money", precision=18)
    public BigDecimal getAfterMoney() {
        return this.afterMoney;
    }

    public void setAfterMoney(BigDecimal afterMoney) {
        this.afterMoney = afterMoney;
    }

    @Column(name="after_freezing_money", precision=18)
    public BigDecimal getAfterFreezingMoney() {
        return this.afterFreezingMoney;
    }
    
    public void setAfterFreezingMoney(BigDecimal afterFreezingMoney) {
        this.afterFreezingMoney = afterFreezingMoney;
    }
    
    @Column(name="after_integral", precision=18)
    public BigDecimal getAfterIntegral() {
        return this.afterIntegral;
    }
    
    public void setAfterIntegral(BigDecimal afterIntegral) {
        this.afterIntegral = afterIntegral;
    }
    
    @Column(name="flow_type", length=32)
    public String getFlowType() {
        return this.flowType;
    }
    
    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
    
    @Column(name="create_type", length=32)
    public String getCreateType() {
        return this.createType;
    }
    
    public void setCreateType(String createType) {
        this.createType = createType;
    }
    
    @Column(name="flow_source", length=65535)
    public String getFlowSource() {
        return this.flowSource;
    }
    
    public void setFlowSource(String flowSource) {
        this.flowSource = flowSource;
    }
    
    @Column(name="flow_source_type")
    public Integer getFlowSourceType() {
        return this.flowSourceType;
    }
    
    public void setFlowSourceType(Integer flowSourceType) {
        this.flowSourceType = flowSourceType;
    }
    
    @Column(name="flow_remark", length=65535)
    public String getFlowRemark() {
        return this.flowRemark;
    }
    
    public void setFlowRemark(String flowRemark) {
        this.flowRemark = flowRemark;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




}


