package com.ruanyun.web.model.mall;
// Generated 2016-11-25 18:48:25 by Hibernate Tools 3.2.2.GA


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

/**
 * 店铺审核日志表
 */
@Entity
@Table(name="t_audit_shop_log"
)
public class TAuditShopLog  implements java.io.Serializable {


     private Integer auditShopLogId;
     private String auditShopLogNum;
     private String shopNum;
     private String shopName;
     private String reason;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     private String city;
     private String area;
     
     private Date startTime;

    public TAuditShopLog() {
    }

	
    public TAuditShopLog(Date createTime) {
        this.createTime = createTime;
    }
    public TAuditShopLog(String auditShopLogNum, String shopNum, String shopName, String reason, Date createTime, String flag1, String flag2, String flag3) {
       this.auditShopLogNum = auditShopLogNum;
       this.shopNum = shopNum;
       this.shopName = shopName;
       this.reason = reason;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="audit_shop_log_id", unique=true, nullable=false)
    public Integer getAuditShopLogId() {
        return this.auditShopLogId;
    }
    
    public void setAuditShopLogId(Integer auditShopLogId) {
        this.auditShopLogId = auditShopLogId;
    }
    
    @Column(name="audit_shop_log_num", length=100)
    public String getAuditShopLogNum() {
        return this.auditShopLogNum;
    }
    
    public void setAuditShopLogNum(String auditShopLogNum) {
        this.auditShopLogNum = auditShopLogNum;
    }
    
    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return this.shopNum;
    }
    
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }
    
    @Column(name="shop_name", length=100)
    public String getShopName() {
        return this.shopName;
    }
    
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    @Column(name="reason", length=1000)
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
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


