package com.ruanyun.web.model.mall;
// Generated 2016-9-29 16:24:16 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ruanyun.web.model.sys.TAttachInfo;

/**
 * 商品基础表
 */
@Entity
@Table(name="t_goods_info" 
)
public class TGoodsInfo  implements java.io.Serializable {


     private Integer goodsId;
     private String goodsNum;
     private String shopNum;
     private String goodsName;
     private String viteName;
     private String parentNum;
     private String goodsTypeNum;
     private String parentName;
     private String goodsTypeName;
     private String mainPhoto;
     private BigDecimal marketPrice;
     private BigDecimal salePrice;
     private Integer goodsStatus;
     private Integer isEssxsdh;
     private Integer isQtkt;
     private Integer isHome;
     private Integer isNew;  //标识是否是最新商品 （1是 2否）
     private Integer monthSaleCount;
     private Integer commentCount;
     private String unit;
     private String brandNum;
     private String productDetail;
     private String graphicDetail;
     private Date createTime;
     private String userNum;
     private Integer saleCount;
     private String flag1;
     private String flag2;
     private String flag3;
     private Date startTime;
     private Integer isFlow=2; //1收藏 2不收藏
     
     private String city;
     private String area;
     
     private TShopInfo shopInfo;
     public List<TAttachInfo> photoList;
     
     public String goodsAttributeNum;//传递   商品属性关联表  所需的 属性num
     private Integer shopCarCount;  //购物车中的数量
     private String shopCarAttribute; //购物车中选中属性
     private String shopCartNum; //购物车编号
       
     private Date updateTime;
    public TGoodsInfo() {
    }

	
    public TGoodsInfo(Date createTime) {
        this.createTime = createTime;
    }
    public TGoodsInfo(String goodsNum, String shopNum, String goodsName, String viteName, String parentNum, String goodsTypeNum, String mainPhoto, BigDecimal marketPrice, BigDecimal salePrice, Integer goodsStatus, Integer isEssxsdh, Integer isQtkt, Integer isHome, Integer monthSaleCount, Integer commentCount, String unit, Date createTime, String userNum, String flag1, String flag2, String flag3,Integer saleCount) {
       this.goodsNum = goodsNum;
       this.shopNum = shopNum;
       this.goodsName = goodsName;
       this.viteName = viteName;
       this.parentNum = parentNum;
       this.goodsTypeNum = goodsTypeNum;
       this.mainPhoto = mainPhoto;
       this.marketPrice = marketPrice;
       this.salePrice = salePrice;
       this.goodsStatus = goodsStatus;
       this.isEssxsdh = isEssxsdh;
       this.isQtkt = isQtkt;
       this.isHome = isHome;
       this.monthSaleCount = monthSaleCount;
       this.commentCount = commentCount;
       this.unit = unit;
       this.createTime = createTime;
       this.userNum = userNum;
       this.saleCount=saleCount;
       this.flag1 = flag1;
       this.flag2 = flag2;
       this.flag3 = flag3;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="goods_id", unique=true, nullable=false)
    public Integer getGoodsId() {
        return this.goodsId;
    }
    
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    
    @Column(name="goods_num", length=100)
    public String getGoodsNum() {
        return this.goodsNum;
    }
    
    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }
    
    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return this.shopNum;
    }
    
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }
    
    @Column(name="goods_name", length=100)
    public String getGoodsName() {
        return this.goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    @Column(name="vite_name", length=100)
    public String getViteName() {
        return this.viteName;
    }
    
    public void setViteName(String viteName) {
        this.viteName = viteName;
    }
    
    @Column(name="parent_num", length=100)
    public String getParentNum() {
        return this.parentNum;
    }
    
    public void setParentNum(String parentNum) {
        this.parentNum = parentNum;
    }
    
    @Column(name="goods_type_num", length=100)
    public String getGoodsTypeNum() {
        return this.goodsTypeNum;
    }
    
    public void setGoodsTypeNum(String goodsTypeNum) {
        this.goodsTypeNum = goodsTypeNum;
    }
    
    @Column(name="main_photo", length=100)
    public String getMainPhoto() {
        return this.mainPhoto;
    }
    
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    
    @Column(name="market_price", precision=10)
    public BigDecimal getMarketPrice() {
        return this.marketPrice;
    }
    
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }
    
    @Column(name="sale_price", precision=10)
    public BigDecimal getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    @Column(name="goods_status")
    public Integer getGoodsStatus() {
        return this.goodsStatus;
    }
    
    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
    
    @Column(name="is_essxsdh")
    public Integer getIsEssxsdh() {
        return this.isEssxsdh;
    }
    
    public void setIsEssxsdh(Integer isEssxsdh) {
        this.isEssxsdh = isEssxsdh;
    }
    
    @Column(name="is_qtkt")
    public Integer getIsQtkt() {
        return this.isQtkt;
    }
    
    public void setIsQtkt(Integer isQtkt) {
        this.isQtkt = isQtkt;
    }
    
    @Column(name="is_home")
    public Integer getIsHome() {
        return this.isHome;
    }
    
    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }
    
    @Column(name="month_sale_count")
    public Integer getMonthSaleCount() {
        return this.monthSaleCount;
    }
    
    public void setMonthSaleCount(Integer monthSaleCount) {
        this.monthSaleCount = monthSaleCount;
    }
    
    @Column(name="comment_count")
    public Integer getCommentCount() {
        return this.commentCount;
    }
    
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    
    @Column(name="unit", length=100)
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    @Column(name="product_detail", length=2000)
    public String getProductDetail() {
        return this.productDetail;
    }
    
    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }
    
    @Column(name="graphic_detail", length=65535)
    public String getGraphicDetail() {
        return this.graphicDetail;
    }
    
    public void setGraphicDetail(String graphicDetail) {
        this.graphicDetail = graphicDetail;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", nullable=false, length=19)
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

    
    @Column(name="brand_num", length=100)
    public String getBrandNum() {
		return brandNum;
	}


	public void setBrandNum(String brandNum) {
		this.brandNum = brandNum;
	}


	@Transient
    public Date getStartTime() {
		return startTime;
	}
    public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

    @Transient
    public TShopInfo getShopInfo() {
		return shopInfo;
	}
    public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
    @Transient
    public List<TAttachInfo> getPhotoList() {
		return photoList;
	}
    public void setPhotoList(List<TAttachInfo> photoList) {
		this.photoList = photoList;
	}


    @Transient
	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Transient
	public String getGoodsTypeName() {
		return goodsTypeName;
	}


	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}


	@Column(name="is_new")
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	
	@Transient
	public String getGoodsAttributeNum() {
		return goodsAttributeNum;
	}
	public void setGoodsAttributeNum(String goodsAttributeNum) {
		this.goodsAttributeNum = goodsAttributeNum;
	}
	

	@Transient
	public Integer getShopCarCount() {
		return shopCarCount;
	}


	public void setShopCarCount(Integer shopCarCount) {
		this.shopCarCount = shopCarCount;
	}

	@Transient
	public String getShopCarAttribute() {
		return shopCarAttribute;
	}


	public void setShopCarAttribute(String shopCarAttribute) {
		this.shopCarAttribute = shopCarAttribute;
	}
	
	@Transient
	public Integer getIsFlow() {
		return isFlow;
	}


	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}

	@Transient
	public String getShopCartNum() {
		return shopCartNum;
	}


	public void setShopCartNum(String shopCartNum) {
		this.shopCartNum = shopCartNum;
	}

	@Column(name="sale_count")
	public Integer getSaleCount() {
		return saleCount;
	}


	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time", nullable=false, length=19)
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}


