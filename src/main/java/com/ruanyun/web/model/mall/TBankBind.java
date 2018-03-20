package com.ruanyun.web.model.mall;
// Generated 2016-12-3 13:35:24 by Hibernate Tools 3.2.2.GA


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
 *银行卡绑定
 */
@Entity
@Table(name="t_bank_bind"
)
public class TBankBind  implements java.io.Serializable {


     private Integer bankBindId;
     private String bankBindNum;
     private String nickName;
     private String commonNum;
     private String bankBranch;
     private String bankName;
     private String cardNo;
     private String userName;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     private String city;
     private String area;

    public TBankBind() {
    }

	
    public TBankBind(Date createTime) {
        this.createTime = createTime;
    }
    public TBankBind(String bankBindNum, String nickName, String commoNum, String bankBranch, String bankName, String cardNo, String userName, Date createTime, String flag1, String flag2, String flag3) {
       this.bankBindNum = bankBindNum;
       this.nickName = nickName;
       this.commonNum = commoNum;
       this.bankBranch = bankBranch;
       this.bankName = bankName;
       this.cardNo = cardNo;
       this.userName = userName;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="bank_bind_id", unique=true, nullable=false)
    public Integer getBankBindId() {
        return this.bankBindId;
    }
    
    public void setBankBindId(Integer bankBindId) {
        this.bankBindId = bankBindId;
    }
    
    @Column(name="bank_bind_num", length=100)
    public String getBankBindNum() {
        return this.bankBindNum;
    }
    
    public void setBankBindNum(String bankBindNum) {
        this.bankBindNum = bankBindNum;
    }
    
    @Column(name="nick_name", length=100)
    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    @Column(name="common_num", length=100)
    public String getCommonNum() {
        return this.commonNum;
    }
    
    public void setCommonNum(String commonNum) {
        this.commonNum = commonNum;
    }
    
    @Column(name="bank_branch", length=100)
    public String getBankBranch() {
        return this.bankBranch;
    }
    
    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
    
    @Column(name="bank_name", length=100)
    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    @Column(name="card_no", length=100)
    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    @Column(name="city")
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="area")
	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}

    


}


