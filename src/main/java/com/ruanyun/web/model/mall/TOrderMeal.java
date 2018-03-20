package com.ruanyun.web.model.mall;
// Generated 2016-9-26 10:16:09 by Hibernate Tools 3.2.2.GA


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
 * 订单商品明细
 */
@Entity
@Table(name="t_order_meal"
)
public class TOrderMeal implements java.io.Serializable {


     private Integer orderGoodsId;
     private String orderGoodsNum;
     private String mealNum;
     private String shopNum;
     private String orderNum;
     private String mealName;
     private String mainPhoto;
     private BigDecimal salePrice;
     private BigDecimal singleActualPrice;  //单个商品实际支付价格
     private BigDecimal actualPrice;   //买家实际支付总商品的价格，如果没有用优惠券，则该价格与销售价一致！
     private Integer goodsCount;
     private BigDecimal totalPrice;
     private Date createTime;
     private String userNum;
     private String flag1;
     private String flag2;
     private String flag3;
     private Integer mealLog;
     private Date startTime;
     private Integer counts;
     
     private TGoodsInfo goodsInfo;
     
     private TShopInfo shopInfo;
     private String goodsTypeName;
    public TOrderMeal() {
    }

	
    public TOrderMeal(Date createTime) {
        this.createTime = createTime;
    }

    public TOrderMeal(String orderGoodsNum, String mealNum, String shopNum, String orderNum, String mealName, String mainPhoto, BigDecimal salePrice, BigDecimal singleActualPrice, BigDecimal actualPrice, Integer goodsCount, BigDecimal totalPrice, Date createTime, String userNum, String flag1, String flag2, String flag3) {
        this.orderGoodsNum = orderGoodsNum;
        this.mealNum = mealNum;
        this.shopNum = shopNum;
        this.orderNum = orderNum;
        this.mealName = mealName;
        this.mainPhoto = mainPhoto;
        this.salePrice = salePrice;
        this.singleActualPrice = singleActualPrice;
        this.actualPrice = actualPrice;
        this.goodsCount = goodsCount;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.userNum = userNum;
        this.flag1 = flag1;
        this.flag2 = flag2;
        this.flag3 = flag3;
    }

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="order_goods_id", unique=true, nullable=false)
    public Integer getOrderGoodsId() {
        return this.orderGoodsId;
    }
    public void setOrderGoodsId(Integer orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }
    
    @Column(name="order_goods_num", length=100)
    public String getOrderGoodsNum() {
        return this.orderGoodsNum;
    }
    public void setOrderGoodsNum(String orderGoodsNum) {
        this.orderGoodsNum = orderGoodsNum;
    }

    @Column(name="meal_num", length=100)
    public String getMealNum() {
        return mealNum;
    }
    public void setMealNum(String mealNum) {
        this.mealNum = mealNum;
    }
    
    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return this.shopNum;
    }
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    @Column(name="meal_name", length=100)
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Column(name="main_photo", length=100)
    public String getMainPhoto() {
        return this.mainPhoto;
    }
    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
    
    @Column(name="sale_price", precision=10)
    public BigDecimal getSalePrice() {
        return this.salePrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    @Column(name="goods_count")
    public Integer getGoodsCount() {
        return this.goodsCount;
    }
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }
    
    @Column(name="total_price", precision=10)
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name="actual_price")
    public BigDecimal getActualPrice() {
        return actualPrice;
    }
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Column(name="single_actual_price")
    public BigDecimal getSingleActualPrice() {
        return singleActualPrice;
    }
    public void setSingleActualPrice(BigDecimal singleActualPrice) {
        this.singleActualPrice = singleActualPrice;
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

    @Column(name="order_num")
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Transient
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Transient
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	
	@Transient
	public TGoodsInfo getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(TGoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Transient
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	@Transient
	public TShopInfo getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}


	@Column(name="meal_log")
	public Integer getMealLog() {
		return mealLog;
	}


	public void setMealLog(Integer mealLog) {
		this.mealLog = mealLog;
	}
	
}


