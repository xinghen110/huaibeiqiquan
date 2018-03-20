package com.ruanyun.web.model.mall;
// Generated 2016-9-29 9:25:51 by Hibernate Tools 3.2.2.GA


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
 * 会员收货地址
 */
@Entity
@Table(name="t_user_address"
)
public class TUserAddress  implements java.io.Serializable {


     private Integer userAddressId;
     private String userAddressNum;
     private String userNum;
     private String linkMan;
     private String linkTel;
     private String province;
     private String city;
     private String areas;
     private String address;
     private String longitude;
     private String latitude;
     private Integer isDeafult;
     private Date createTime;
     private String flag1;
     private String flag2;
     private String flag3;

    public TUserAddress() {
    }

	
    public TUserAddress(Date createTime) {
        this.createTime = createTime;
    }
    public TUserAddress(String userAddressNum, String userNum, String linkMan, String linkTel, String province, String city, String areas, String address, String longitude, String latitude, Integer isDeafult, Date createTime, String flag1, String flag2, String flag3) {
       this.userAddressNum = userAddressNum;
       this.userNum = userNum;
       this.linkMan = linkMan;
       this.linkTel = linkTel;
       this.province = province;
       this.city = city;
       this.areas = areas;
       this.address = address;
       this.longitude = longitude;
       this.latitude = latitude;
       this.isDeafult = isDeafult;
       this.createTime = createTime;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="user_address_id", unique=true, nullable=false)
    public Integer getUserAddressId() {
        return this.userAddressId;
    }
    
    public void setUserAddressId(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }
    
    @Column(name="user_address_num", length=100)
    public String getUserAddressNum() {
        return this.userAddressNum;
    }
    
    public void setUserAddressNum(String userAddressNum) {
        this.userAddressNum = userAddressNum;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="link_man", length=100)
    public String getLinkMan() {
        return this.linkMan;
    }
    
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }
    
    @Column(name="link_tel", length=100)
    public String getLinkTel() {
        return this.linkTel;
    }
    
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
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
    
    @Column(name="areas", length=100)
    public String getAreas() {
        return this.areas;
    }
    
    public void setAreas(String areas) {
        this.areas = areas;
    }
    
    @Column(name="address", length=100)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
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
    
    @Column(name="is_deafult")
    public Integer getIsDeafult() {
        return this.isDeafult;
    }
    
    public void setIsDeafult(Integer isDeafult) {
        this.isDeafult = isDeafult;
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




}


