package com.ruanyun.web.model.mall;
// Generated 2017-8-5 14:45:04 by Hibernate Tools 3.2.2.GA



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

/**
 *店铺技师信息
 */
@Entity
@Table(name="t_shop_info"
)
public class TShopInfo  implements java.io.Serializable {


     private Integer shopInfoId;
     private String shopNum;
     private Integer shopType;
     private String shopName;
     private String typeNum;
     private Integer jiedanCount;
     private Integer userSex;
     private Double score;
     private String userBirth;
     private String mainPhoto;
     private Integer isRecommond;
     private String province;
     private String city;
     private String area;
     private String longitude;
     private String latitude;
     private String address;
     private Integer jishiCount;
     private String businessTime;
     private String description;
     private String linkTel;
     private String userNum;
     private Date createTime;
     private Integer status;
     private Integer shopStatus;
     private String flag1;
     private String flag2;
     private String flag3;
     private String typName;
     private Integer memberLevel;
     private String reason;
     private Integer isHezuo;
     private Date startTime;
     
     private BigDecimal distance;//距离
     
     private String creatorNum;//提交申请者编号

    public TShopInfo() {
    }

	
    public TShopInfo(Date createTime) {
        this.createTime = createTime;
    }
    public TShopInfo(String shopNum, Integer shopType, String shopName, String typeNum, Integer jiedanCount, Integer userSex, Double score, String userBirth, String mainPhoto, Integer isRecommond, String province, String city, String area, String longitude, String latitude, String address, Integer jishiCount, String businessTime, String description, String linkTel, String userNum, Date createTime, Integer shopStatus, String flag1, String flag2, String flag3) {
       this.shopNum = shopNum;
       this.shopType = shopType;
       this.shopName = shopName;
       this.typeNum = typeNum;
       this.jiedanCount = jiedanCount;
       this.userSex = userSex;
       this.score = score;
       this.userBirth = userBirth;
       this.mainPhoto = mainPhoto;
       this.isRecommond = isRecommond;
       this.province = province;
       this.city = city;
       this.area = area;
       this.longitude = longitude;
       this.latitude = latitude;
       this.address = address;
       this.jishiCount = jishiCount;
       this.businessTime = businessTime;
       this.description = description;
       this.linkTel = linkTel;
       this.userNum = userNum;
       this.createTime = createTime;
       this.shopStatus = shopStatus;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="shop_info_id", unique=true, nullable=false)
    public Integer getShopInfoId() {
        return this.shopInfoId;
    }
    
    public void setShopInfoId(Integer shopInfoId) {
        this.shopInfoId = shopInfoId;
    }
    
    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return this.shopNum;
    }
    
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }
    
    @Column(name="shop_type")
    public Integer getShopType() {
        return this.shopType;
    }
    
    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }
    
    @Column(name="shop_name", length=100)
    public String getShopName() {
        return this.shopName;
    }
    
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    @Column(name="type_num", length=100)
    public String getTypeNum() {
        return this.typeNum;
    }
    
    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }
    
    @Column(name="jiedan_count")
    public Integer getJiedanCount() {
        return this.jiedanCount;
    }
    
    public void setJiedanCount(Integer jiedanCount) {
        this.jiedanCount = jiedanCount;
    }
    
    @Column(name="user_sex")
    public Integer getUserSex() {
        return this.userSex;
    }
    
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
    
    @Column(name="score", precision=22, scale=0)
    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }
    
    @Column(name="user_birth", length=100)
    public String getUserBirth() {
        return this.userBirth;
    }
    
    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }
    
    @Column(name="main_photo", length=100)
    public String getMainPhoto() {
        return this.mainPhoto;
    }
    
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    
    @Column(name="is_recommond")
    public Integer getIsRecommond() {
        return this.isRecommond;
    }
    
    public void setIsRecommond(Integer isRecommond) {
        this.isRecommond = isRecommond;
    }
    
    @Column(name="province", length=100)
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="city", length=100)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="area", length=100)
    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    @Column(name="longitude", length=100)
    public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    @Column(name="latitude", length=100)
    public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    @Column(name="address", length=100)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="jishi_count")
    public Integer getJishiCount() {
        return this.jishiCount;
    }
    
    public void setJishiCount(Integer jishiCount) {
        this.jishiCount = jishiCount;
    }
    
    @Column(name="business_time", length=100)
    public String getBusinessTime() {
        return this.businessTime;
    }
    
    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }
    
    @Column(name="description", length=1000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="link_tel", length=100)
    public String getLinkTel() {
        return this.linkTel;
    }
    
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
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

    @Column(name = "shop_status", length = 100)
    public Integer getShopStatus() {
        return shopStatus;
    }
    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
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
	public BigDecimal getDistance() {
		return distance;
	}


	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	@Transient
	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Transient
	public String getTypName() {
		return typName;
	}


	public void setTypName(String typName) {
		this.typName = typName;
	}

	@Column(name="member_level")
	public Integer getMemberLevel() {
		return memberLevel;
	}


	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Transient
	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name="creator_num")
	public String getCreatorNum() {
		return creatorNum;
	}


	public void setCreatorNum(String creatorNum) {
		this.creatorNum = creatorNum;
	}

	@Column(name="is_hezuo")
	public Integer getIsHezuo() {
		return isHezuo;
	}


	public void setIsHezuo(Integer isHezuo) {
		this.isHezuo = isHezuo;
	}

	@Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}


