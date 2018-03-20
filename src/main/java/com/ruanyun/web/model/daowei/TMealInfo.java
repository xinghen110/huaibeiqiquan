package com.ruanyun.web.model.daowei;
// Generated 2017-8-4 11:23:03 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 套餐信息表
 */
@Entity
@Table(name="t_meal_info"
)
public class TMealInfo  implements java.io.Serializable {


     private Integer mealInfoId;
     private String mealInfoNum;
     private Integer mealType;
     private String typeNum;
     private String title;
     private BigDecimal mealPrice;
     private Integer mealLog;
     private String mainPhoto;
     private Integer sold;
     private String fwlc;
     private String gntx;
     private String jjzz;
     private String yyxz;
     private Date createTime;
     private String shopNum;
    private Integer isRecommend;
    private Integer makeMethod;
    private Double makePrice;
    private Integer isMake;
    private String flag1;
     private String flag2;
     private String flag3;

     private Date startTime;

    public TMealInfo() {
    }

	
    public TMealInfo(Date createTime) {
        this.createTime = createTime;
    }

    public TMealInfo(String mealInfoNum, Integer mealType, String typeNum, String title, BigDecimal mealPrice, Integer mealLog, String mainPhoto, Integer sold, String fwlc, String gntx, String jjzz, String yyxz, Date createTime, String shopNum, Integer isRecommend, Integer makeMethod, Double makePrice, String flag1, String flag2, String flag3) {
        this.mealInfoNum = mealInfoNum;
        this.mealType = mealType;
        this.typeNum = typeNum;
        this.title = title;
        this.mealPrice = mealPrice;
        this.mealLog = mealLog;
        this.mainPhoto = mainPhoto;
        this.sold = sold;
        this.fwlc = fwlc;
        this.gntx = gntx;
        this.jjzz = jjzz;
        this.yyxz = yyxz;
        this.createTime = createTime;
        this.shopNum = shopNum;
        this.isRecommend = isRecommend;
        this.makeMethod = makeMethod;
        this.makePrice = makePrice;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
    }

    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="meal_info_id", unique=true, nullable=false)
    public Integer getMealInfoId() {
        return this.mealInfoId;
    }
    
    public void setMealInfoId(Integer mealInfoId) {
        this.mealInfoId = mealInfoId;
    }
    
    @Column(name="meal_info_num", length=100)
    public String getMealInfoNum() {
        return this.mealInfoNum;
    }
    
    public void setMealInfoNum(String mealInfoNum) {
        this.mealInfoNum = mealInfoNum;
    }
    
    @Column(name="meal_type")
    public Integer getMealType() {
        return this.mealType;
    }
    
    public void setMealType(Integer mealType) {
        this.mealType = mealType;
    }
    
    @Column(name="type_num", length=1000)
    public String getTypeNum() {
        return this.typeNum;
    }
    
    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }
    
    @Column(name="title", length=1000)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="meal_price", precision=10)
    public BigDecimal getMealPrice() {
        return this.mealPrice;
    }
    
    public void setMealPrice(BigDecimal mealPrice) {
        this.mealPrice = mealPrice;
    }
    
    @Column(name="meal_log")
    public Integer getMealLog() {
        return this.mealLog;
    }
    
    public void setMealLog(Integer mealLog) {
        this.mealLog = mealLog;
    }
    
    @Column(name="main_photo", length=1000)
    public String getMainPhoto() {
        return this.mainPhoto;
    }
    
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    
    @Column(name="sold")
    public Integer getSold() {
        return this.sold;
    }
    
    public void setSold(Integer sold) {
        this.sold = sold;
    }
    
    @Column(name="fwlc", length=1000)
    public String getFwlc() {
        return this.fwlc;
    }
    
    public void setFwlc(String fwlc) {
        this.fwlc = fwlc;
    }
    
    @Column(name="gntx", length=1000)
    public String getGntx() {
        return this.gntx;
    }
    
    public void setGntx(String gntx) {
        this.gntx = gntx;
    }
    
    @Column(name="jjzz", length=1000)
    public String getJjzz() {
        return this.jjzz;
    }
    
    public void setJjzz(String jjzz) {
        this.jjzz = jjzz;
    }
    
    @Column(name="yyxz", length=1000)
    public String getYyxz() {
        return this.yyxz;
    }
    
    public void setYyxz(String yyxz) {
        this.yyxz = yyxz;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    @Column(name = "is_recommend")
    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Column(name = "make_method")
    public Integer getMakeMethod() {
        return makeMethod;
    }

    public void setMakeMethod(Integer makeMethod) {
        this.makeMethod = makeMethod;
    }

    @Column(name = "make_price")
    public Double getMakePrice() {
        return makePrice;
    }

    public void setMakePrice(Double makePrice) {
        this.makePrice = makePrice;
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

    @Column(name = "is_make")
    public Integer getIsMake() {
        return isMake;
    }

    public void setIsMake(Integer isMake) {
        this.isMake = isMake;
    }
}


