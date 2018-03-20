package com.ruanyun.web.model.mall;
// Generated 2016-10-28 13:55:23 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ruanyun.web.model.sys.TUser;

/**
 * 充值套餐
 */
@Entity
@Table(name="t_recharge_meal"
)
public class TRechargeMeal  implements java.io.Serializable {


     private Integer rechargeMealId;
     private String rechargeMealNum;
     private BigDecimal amount;
     private BigDecimal presentPrice;
     private Integer score;
     private String remark;
     private String userNum;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     
     private TUser user;
     private Date startTime;
     
    public TRechargeMeal() {
    }

	
    public TRechargeMeal(Date createTime) {
        this.createTime = createTime;
    }
    public TRechargeMeal(String rechargeMealNum, BigDecimal amount, Integer score, String remark, String userNum, Date createTime, String flag1, String flag2, String flag3) {
       this.rechargeMealNum = rechargeMealNum;
       this.amount = amount;
       this.score = score;
       this.remark = remark;
       this.userNum = userNum;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="recharge_meal_id", unique=true, nullable=false)
    public Integer getRechargeMealId() {
        return this.rechargeMealId;
    }
    
    public void setRechargeMealId(Integer rechargeMealId) {
        this.rechargeMealId = rechargeMealId;
    }
    
    @Column(name="recharge_meal_num", length=100)
    public String getRechargeMealNum() {
        return this.rechargeMealNum;
    }
    
    public void setRechargeMealNum(String rechargeMealNum) {
        this.rechargeMealNum = rechargeMealNum;
    }
    
    @Column(name="amount", precision=10)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
    @Column(name="present_price", precision=10)
    public BigDecimal getPresentPrice() {
		return presentPrice;
	}


	public void setPresentPrice(BigDecimal presentPrice) {
		this.presentPrice = presentPrice;
	}


	@Column(name="score", precision=10)
    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
    
    @Column(name="remark", length=100)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Transient
	public TUser getUser() {
		return user;
	}


	public void setUser(TUser user) {
		this.user = user;
	}

	 @Transient
	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

    
    


}


