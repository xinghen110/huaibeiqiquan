package com.ruanyun.web.model.mall;
// Generated 2016-10-28 13:55:23 by Hibernate Tools 3.2.2.GA


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
 * 用户流水记录
 */
@Entity
@Table(name="t_user_record"
)
public class TUserRecord  implements java.io.Serializable {

	//物流费用  大于15公里买家支付10元，物流人员提3元，平台得7元
    //      小于等于15公里买家支付3元，物流人员提1元，平台得2元


     private Integer userRecordId;
     private String userRecordNum;

  
     private String userNum;
     private Integer consumType;
     private String consumTypeString;
     private Integer payType;
     private BigDecimal consumPrice;
     private BigDecimal accountBancle;
     private String title;
     private String orderNum;
     private String shopNum;//店铺编号
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     private Date startTime;
     private TUser user;
     private Integer userType;
    public TUserRecord() {
    }

	
    public TUserRecord(Date createTime) {
        this.createTime = createTime;
    }
    public TUserRecord(String userRecordNum, String userNum, Integer consumType, Integer payType, BigDecimal consumPrice, BigDecimal accountBancle, String title, Date createTime, String flag1, String flag2, String flag3) {
       this.userRecordNum = userRecordNum;
       this.userNum = userNum;
       this.consumType = consumType;
       this.payType = payType;
       this.consumPrice = consumPrice;
       this.accountBancle = accountBancle;
       this.title = title;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="user_record_id", unique=true, nullable=false)
    public Integer getUserRecordId() {
        return this.userRecordId;
    }
    
    public void setUserRecordId(Integer userRecordId) {
        this.userRecordId = userRecordId;
    }
    
    @Column(name="user_record_num", length=100)
    public String getUserRecordNum() {
        return this.userRecordNum;
    }
    
    public void setUserRecordNum(String userRecordNum) {
        this.userRecordNum = userRecordNum;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return this.shopNum;
    }
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }
    
    @Column(name="consum_type")
    public Integer getConsumType() {
        return this.consumType;
    }
    
    public void setConsumType(Integer consumType) {
        this.consumType = consumType;
    }
    
    @Column(name="pay_type")
    public Integer getPayType() {
        return this.payType;
    }
    
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    
    @Column(name="consum_price", precision=10)
    public BigDecimal getConsumPrice() {
        return this.consumPrice;
    }
    
    public void setConsumPrice(BigDecimal consumPrice) {
        this.consumPrice = consumPrice;
    }
    
    @Column(name="account_bancle", precision=10)
    public BigDecimal getAccountBancle() {
        return this.accountBancle;
    }
    
    public void setAccountBancle(BigDecimal accountBancle) {
        this.accountBancle = accountBancle;
    }
    
    @Column(name="title", length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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

    
    @Column(name="order_num")
    public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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



	@Column(name="user_type")
	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}

    @Transient
	public String getConsumTypeString() {
		return consumTypeString;
	}
	public void setConsumTypeString(String consumTypeString) {
		this.consumTypeString = consumTypeString;
	}
	
	

}


