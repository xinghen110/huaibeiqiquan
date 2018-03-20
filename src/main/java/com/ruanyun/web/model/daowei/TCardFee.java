package com.ruanyun.web.model.daowei;
// Generated 2017-8-17 15:56:08 by Hibernate Tools 3.2.2.GA


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
 *会员卡年费设置
 */
@Entity
@Table(name="t_card_fee"
)
public class TCardFee  implements java.io.Serializable {


     private Integer cardFeeId;
     private String cardFeeNum;
     private Integer memberLevel;
     private Integer cardFee;
     private Integer cardType;
     private Date createTime;
     private String userNum;
     private String flag1;
     private String flag2;
     private String flag3;

    public TCardFee() {
    }

    public TCardFee(String cardFeeNum, Integer memberLevel, Integer cardFee, Date createTime, String userNum, String flag1, String flag2, String flag3) {
       this.cardFeeNum = cardFeeNum;
       this.memberLevel = memberLevel;
       this.cardFee = cardFee;
       this.createTime = createTime;
       this.userNum = userNum;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="card_fee_id", unique=true, nullable=false)
    public Integer getCardFeeId() {
        return this.cardFeeId;
    }
    
    public void setCardFeeId(Integer cardFeeId) {
        this.cardFeeId = cardFeeId;
    }
    
    @Column(name="card_fee_num", length=100)
    public String getCardFeeNum() {
        return this.cardFeeNum;
    }
    
    public void setCardFeeNum(String cardFeeNum) {
        this.cardFeeNum = cardFeeNum;
    }
    
    @Column(name="member_level")
    public Integer getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }
    
    @Column(name="card_fee")
    public Integer getCardFee() {
        return this.cardFee;
    }
    
    public void setCardFee(Integer cardFee) {
        this.cardFee = cardFee;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
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

    @Column(name="card_type")
	public Integer getCardType() {
		return cardType;
	}

    
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

    


}


