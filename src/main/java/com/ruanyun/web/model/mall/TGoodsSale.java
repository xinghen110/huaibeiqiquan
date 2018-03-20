package com.ruanyun.web.model.mall;
// Generated 2016-11-9 17:01:48 by Hibernate Tools 3.2.2.GA


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
 * 商品销量
 */
@Entity
@Table(name="t_goods_sale"
)
public class TGoodsSale  implements java.io.Serializable {


     private Integer goodsSaleId;
     private String goodsSaleNum;
     private String userNum;
     private String shopNum;
     private String goodsNum;
     private Date createTime;
     private Integer goodsCount;
     private String flag1;
     private String flag2;
     private String flag3;

    public TGoodsSale() {
    }

	
    public TGoodsSale(Date createTime) {
        this.createTime = createTime;
    }
    public TGoodsSale(String goodsSaleNum, String userNum, String shopNum, String goodsNum, Date createTime, Integer goodsCount, String flag1, String flag2, String flag3) {
       this.goodsSaleNum = goodsSaleNum;
       this.userNum = userNum;
       this.shopNum = shopNum;
       this.goodsNum = goodsNum;
       this.createTime = createTime;
       this.goodsCount = goodsCount;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="goods_sale_id", unique=true, nullable=false)
    public Integer getGoodsSaleId() {
        return this.goodsSaleId;
    }
    
    public void setGoodsSaleId(Integer goodsSaleId) {
        this.goodsSaleId = goodsSaleId;
    }
    
    @Column(name="goods_sale_num", length=100)
    public String getGoodsSaleNum() {
        return this.goodsSaleNum;
    }
    
    public void setGoodsSaleNum(String goodsSaleNum) {
        this.goodsSaleNum = goodsSaleNum;
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
    
    @Column(name="goods_num", length=100)
    public String getGoodsNum() {
        return this.goodsNum;
    }
    
    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="goods_count")
    public Integer getGoodsCount() {
        return this.goodsCount;
    }
    
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
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


