package com.ruanyun.web.model.daowei;

import com.ruanyun.web.model.mall.TShopInfo;
import java.util.Date;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *商家下基础信息
 */
@Entity
@Table(name="t_jishi_info"
)
public class TJishiInfo  implements java.io.Serializable {


     private Integer jishiInfoId;
     private String jishiInfoNum;
     private String userName;
     private String mainPhoto;
     private Integer userSex;
     private Date userBirth;
     private String typeNum;
     private String description;
     private String shopInfoNum;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;
     private String typeName;

     private TShopInfo shopInfo;

    public TJishiInfo() {
    }

    public TJishiInfo(String jishiInfoNum, String userName, String mainPhoto, Integer userSex, Date userBirth, String typeNum, String description, String shopInfoNum, Date createTime, String flag1, String flag2, String flag3) {
       this.jishiInfoNum = jishiInfoNum;
       this.userName = userName;
       this.mainPhoto = mainPhoto;
       this.userSex = userSex;
       this.userBirth = userBirth;
       this.typeNum = typeNum;
       this.description = description;
       this.shopInfoNum = shopInfoNum;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="jishi_info_id", unique=true, nullable=false)
    public Integer getJishiInfoId() {
        return this.jishiInfoId;
    }
    
    public void setJishiInfoId(Integer jishiInfoId) {
        this.jishiInfoId = jishiInfoId;
    }
    
    @Column(name="jishi_info_num", length=100)
    public String getJishiInfoNum() {
        return this.jishiInfoNum;
    }
    
    public void setJishiInfoNum(String jishiInfoNum) {
        this.jishiInfoNum = jishiInfoNum;
    }
    
    @Column(name="user_name", length=100)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="main_photo", length=100)
    public String getMainPhoto() {
        return this.mainPhoto;
    }
    
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    
    @Column(name="user_sex")
    public Integer getUserSex() {
        return this.userSex;
    }
    
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="user_birth", length=19)
    public Date getUserBirth() {
        return this.userBirth;
    }
    
    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }
    
    @Column(name="type_num", length=100)
    public String getTypeNum() {
        return this.typeNum;
    }
    
    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }
    
   
	@Column(name="description", length=1000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="shop_info_num", length=100)
    public String getShopInfoNum() {
        return this.shopInfoNum;
    }
    
    public void setShopInfoNum(String shopInfoNum) {
        this.shopInfoNum = shopInfoNum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", length=19)
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
    public TShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(TShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }
    @Transient
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
    
}


