package com.ruanyun.web.model.mall;
// Generated 2016-12-1 16:38:47 by Hibernate Tools 3.2.2.GA


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
 * 记录短信发送次数
 */
@Entity
@Table(name="t_sms_count"
)
public class TSmsCount  implements java.io.Serializable {


     private Integer smsCountId;
     private String smsCountNum;
     private String userNum;
     private Integer sendCount;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     private Date startTime;
     private TUser user;
     private String linkTel;
     private Integer sendStatus;
     private String sendContent;

    public TSmsCount() {
    }

	
    public TSmsCount(Date createTime) {
        this.createTime = createTime;
    }
    public TSmsCount(String smsCountNum, String userNum, Integer sendCount, Date createTime, String flag1, String flag2, String flag3) {
       this.smsCountNum = smsCountNum;
       this.userNum = userNum;
       this.sendCount = sendCount;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="sms_count_id", unique=true, nullable=false)
    public Integer getSmsCountId() {
        return this.smsCountId;
    }
    
    public void setSmsCountId(Integer smsCountId) {
        this.smsCountId = smsCountId;
    }
    
    @Column(name="sms_count_num", length=100)
    public String getSmsCountNum() {
        return this.smsCountNum;
    }
    
    public void setSmsCountNum(String smsCountNum) {
        this.smsCountNum = smsCountNum;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="send_count")
    public Integer getSendCount() {
        return this.sendCount;
    }
    
    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
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


	@Column(name="link_tel")
	public String getLinkTel() {
		return linkTel;
	}


	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}


	@Column(name="send_status")
	public Integer getSendStatus() {
		return sendStatus;
	}


	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Column(name="send_content")
	public String getSendContent() {
		return sendContent;
	}


	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	
	

	
    



}


