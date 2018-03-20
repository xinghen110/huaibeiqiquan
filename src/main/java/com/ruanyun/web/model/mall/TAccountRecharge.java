package com.ruanyun.web.model.mall;


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
import javax.persistence.Transient;

import com.ruanyun.web.model.sys.TUser;

/**
 * 账户充值表
 */
@Entity
@Table(name="t_account_recharge"
    
)
public class TAccountRecharge  implements java.io.Serializable {


     private Integer rechargeId;
     private String rechargeNum;
     private String rechargeMealNum;
     private BigDecimal amount;
     private Integer score;
     private String userNum;
     private Date createTime;
     private Date startTime;
     
     private TOrderTrain orderTrain;
     private TUser user;
	public TAccountRecharge() {
    }

    public TAccountRecharge(String rechargeNum, String rechargeMealNum, BigDecimal amount, Integer score, String userNum, Date createTime) {
       this.rechargeNum = rechargeNum;
       this.rechargeMealNum = rechargeMealNum;
       this.amount = amount;
       this.score = score;
       this.userNum = userNum;
 
       this.createTime = createTime;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="recharge_id", unique=true, nullable=false)
    public Integer getRechargeId() {
        return this.rechargeId;
    }
    
    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
    }
    
    @Column(name="recharge_num", length=50)
    public String getRechargeNum() {
        return this.rechargeNum;
    }
    
    public void setRechargeNum(String rechargeNum) {
        this.rechargeNum = rechargeNum;
    }
    
    @Column(name="recharge_meal_num", length=50)
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
    
    @Column(name="score")
    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
    
    @Column(name="user_num", length=50)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
   
    @Temporal(TemporalType.DATE)
    @Column(name="create_time", length=10)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Transient
	public TOrderTrain getOrderTrain() {
		return orderTrain;
	}

	public void setOrderTrain(TOrderTrain orderTrain) {
		this.orderTrain = orderTrain;
	}

	@Transient
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Transient
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
}


