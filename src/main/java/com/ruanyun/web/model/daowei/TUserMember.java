package com.ruanyun.web.model.daowei;
// Generated 2017-8-17 15:56:08 by Hibernate Tools 3.2.2.GA


import com.ruanyun.web.model.sys.TUser;

import java.util.Date;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *用户会员卡设置
 */
@Entity
@Table(name="t_user_member"
)
public class TUserMember  implements java.io.Serializable {


     private Integer userMemberId;
     private String userMemberNum;
     private String cardFeeNum;
     private String userNum;
     private Integer userMember;
     private Date beginTime;
     private Date endTime;
     private Integer status;
     private String flag1;
     private String flag2;
     private String flag3;

     private TUser user;

    public TUserMember() {
    }

    public TUserMember(String userMemberNum, String userNum, Integer userMember, Date beginTime, Date endTime, String flag1, String flag2, String flag3) {
       this.userMemberNum = userMemberNum;
       this.userNum = userNum;
       this.userMember = userMember;
       this.beginTime = beginTime;
       this.endTime = endTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="user_member_id", unique=true, nullable=false)
    public Integer getUserMemberId() {
        return this.userMemberId;
    }
    
    public void setUserMemberId(Integer userMemberId) {
        this.userMemberId = userMemberId;
    }
    
    @Column(name="user_member_num", length=100)
    public String getUserMemberNum() {
        return this.userMemberNum;
    }
    
    public void setUserMemberNum(String userMemberNum) {
        this.userMemberNum = userMemberNum;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
        
    @Column(name="card_fee_num", length=100)
    public String getCardFeeNum() {
		return cardFeeNum;
	}

	public void setCardFeeNum(String cardFeeNum) {
		this.cardFeeNum = cardFeeNum;
	}

	@Column(name="user_member")
    public Integer getUserMember() {
        return this.userMember;
    }
    
    public void setUserMember(Integer userMember) {
        this.userMember = userMember;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="begin_time", length=19)
    public Date getBeginTime() {
        return this.beginTime;
    }
    
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_time", length=19)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="status")
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
}


