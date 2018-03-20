package com.ruanyun.web.model.mall;
// Generated 2016-10-25 9:42:06 by Hibernate Tools 3.2.2.GA


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
 * 订单流水记录表
 */
@Entity
@Table(name="t_order_record"
)
public class TOrderRecord  implements java.io.Serializable {


     private Integer goodsRecordId;
     private String goodsRecordNum;
     private String orderNum;
     private Integer orderStatus;
     private String userNum;
     private String userName;
     private Date createTime;
     private String remark;
     private String flag1;
     private String flag2;
     private String flag3;

    public TOrderRecord() {
    }

	
    public TOrderRecord(Date createTime) {
        this.createTime = createTime;
    }
    public TOrderRecord(String goodsRecordNum, String orderNum, Integer orderStatus, String userNum, String userName, Date createTime, String remark, String flag1, String flag2, String flag3) {
       this.goodsRecordNum = goodsRecordNum;
       this.orderNum = orderNum;
       this.orderStatus = orderStatus;
       this.userNum = userNum;
       this.userName = userName;
       this.createTime = createTime;
       this.remark = remark;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="goods_record_id", unique=true, nullable=false)
    public Integer getGoodsRecordId() {
        return this.goodsRecordId;
    }
    
    public void setGoodsRecordId(Integer goodsRecordId) {
        this.goodsRecordId = goodsRecordId;
    }
    
    @Column(name="goods_record_num", length=100)
    public String getGoodsRecordNum() {
        return this.goodsRecordNum;
    }
    
    public void setGoodsRecordNum(String goodsRecordNum) {
        this.goodsRecordNum = goodsRecordNum;
    }
    
    @Column(name="order_num", length=100)
    public String getOrderNum() {
        return this.orderNum;
    }
    
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
    @Column(name="order_status")
    public Integer getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="user_name", length=100)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="remark", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="flag1", length=100)
    public String getFlag1() {
        return this.flag1;
    }
    
    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }
    
    @Column(name="flag2", length=100)
    public String getFlag2() {
        return this.flag2;
    }
    
    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }
    
    @Column(name="flag3", length=100)
    public String getFlag3() {
        return this.flag3;
    }
    
    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }




}


