package com.ruanyun.web.model.mall;
// Generated 2016-9-23 16:53:53 by Hibernate Tools 3.2.2.GA


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
 * 提现申请
 */
@Entity
@Table(name="t_user_application"
)
public class TUserApplication  implements java.io.Serializable {


     private Integer userApplicationId;
     private String userApplicationNum;
     private String userNum;
     private BigDecimal money;
     private BigDecimal rateMoney;
     private String accountNumber;
     private String bankName;  //银行名称
     private String branchName;  //支行名称
     private String userName;
     private Integer status;
     private Date createTime;
     private String handleNum;
     private Date  handleTime;
     private String handleRemark;
     private String flag1;
     private String flag2;
     private String flag3;

     private String payPassword;
     private TUser user;

     private TShopInfo shopInfo;
     private Date handlestartTime;
     private Date startTime;
    public TUserApplication() {
    }

	
    public TUserApplication(Date createTime) {
        this.createTime = createTime;
    }
    public TUserApplication(String userApplicationNum, String userNum, BigDecimal money, String accountNumber, String userName, Integer status, Date createTime, String handleNum, String flag1, String flag2, String flag3) {
       this.userApplicationNum = userApplicationNum;
       this.userNum = userNum;
       this.money = money;
       this.accountNumber = accountNumber;
       this.userName = userName;
       this.status = status;
       this.createTime = createTime;
       this.handleNum = handleNum;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="user_application_id", unique=true, nullable=false)
    public Integer getUserApplicationId() {
        return this.userApplicationId;
    }
    
    public void setUserApplicationId(Integer userApplicationId) {
        this.userApplicationId = userApplicationId;
    }
    
    @Column(name="user_application_num", length=100)
    public String getUserApplicationNum() {
        return this.userApplicationNum;
    }
    
    public void setUserApplicationNum(String userApplicationNum) {
        this.userApplicationNum = userApplicationNum;
    }

    @Column(name="user_num", length=100)
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="rate_money", precision=10)
    public BigDecimal getRateMoney() {
        return this.rateMoney;
    }
    
    public void setRateMoney(BigDecimal rateMoney) {
        this.rateMoney = rateMoney;
    }
    
    @Column(name="money", precision=10)
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    @Column(name="account_number", length=100)
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    @Column(name="user_name", length=100)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="handle_num", length=100)
    public String getHandleNum() {
        return this.handleNum;
    }
    
    public void setHandleNum(String handleNum) {
        this.handleNum = handleNum;
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
    public TShopInfo getShopInfo() {
		return shopInfo;
	}
    public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}

    @Transient
    public Date getStartTime() {
		return startTime;
	}
    public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


    @Column(name="bank_name")
	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	@Column(name="branch_name")
	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name="handle_time", nullable=false, length=19)
	public Date getHandleTime() {
		return handleTime;
	}

	
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	@Column(name="handle_remark")
	public String getHandleRemark() {
		return handleRemark;
	}


	public void setHandleRemark(String handleRemark) {
		this.handleRemark = handleRemark;
	}

	@Transient
	public TUser getUser() {
		return user;
	}


	public void setUser(TUser user) {
		this.user = user;
	}

	@Transient
	public Date getHandlestartTime() {
		return handlestartTime;
	}


	public void setHandlestartTime(Date handlestartTime) {
		this.handlestartTime = handlestartTime;
	}

	@Transient
	public String getPayPassword() {
		return payPassword;
	}


	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
    
	
	
	
	
	
    

}


