package com.ruanyun.web.model.mall;
// Generated 2016-9-23 16:53:53 by Hibernate Tools 3.2.2.GA


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

import com.ruanyun.web.model.sys.TUser;

/**
 * 订单
 */
@Entity
@Table(name="t_order_info"
)
public class TOrderInfo  implements java.io.Serializable {

	//物流费用  大于15公里买家支付10元，物流人员提3元，平台得7元
    //小于等于15公里买家支付3元，物流人员提1元，平台得2元

     private Integer orderId;
     private String orderNum;
	private String shopNum;
	private String shopTypeName;//对于跑腿才用到
     private Integer orderStatus;
	private String orderLoginName;
	private Integer orderType;   //订单类型
	private String userNum;
	private String orderUserName;
	private String orderRemark;
	private Date orderCreateTime;
	private Integer totalCount;
	private BigDecimal totalPrice;
	private BigDecimal singlePrice;//单价
	private BigDecimal updatePrice;  //修改前价格，如果商家不修改价格，则该价格与实际价格一致！
	private BigDecimal actualPrice;   //此价格为订单实际价格，若商家修改价格，则该价格为客户端传过来的
	private Integer payMethod;
	private Date payTime;
	private String payThirtAccount;
	private String orderLinkTel;
	private String orderLinkMan; //联系人
	private String orderAddress; //收货地址
	private String orderLongitude; // 经度
	private String orderLatitude;  // 纬度
	private Date updateTime;
	private String payParamsMap;  //第三方支付返回信息
	private BigDecimal freight;  //运费
	private String city;
	private String area;
	private Integer serviceType;
	private Date arrivaTime;
	private Date receiveTime;
	private Date completeTime;
	private Date cancelTime;
	private BigDecimal returnPrice;
	private String cancelUserNum;
	private String cancelReason;
	private String falg1;
	private String flag2;
	private String flag3;

	private String orderStatusString;
	private TShopInfo shopInfo;
	private TUser user;
     private TOrderTrain orderTrain;
     private TOrderMeal orderMeal;
     
     private Date startTime;
     private Date paystartTime;
     private BigDecimal totalActurePrice;

     
     
     private String payMethodName;
     
     private String orderStatusName;
     
     private String orderTime;
     
     private String orderPayTime;
     private Integer count;
     
     private String shopName;
     
     private String money;
     
     private String rs;
     
     private String logisticsName;

     
    public TOrderInfo() {
    }

	public TOrderInfo(String orderNum, String shopNum, String shopTypeName, Integer orderStatus, String orderLoginName, Integer orderType, String userNum, String orderUserName, String orderRemark, Date orderCreateTime, Integer totalCount, BigDecimal totalPrice, BigDecimal updatePrice, BigDecimal actualPrice, Integer payMethod, Date payTime, String payThirtAccount, String orderLinkTel, String orderLinkMan, String orderAddress, String orderLongitude, String orderLatitude, Date updateTime, String payParamsMap, BigDecimal freight, String city, String area, Integer serviceType, Date arrivaTime, Date receiveTime, Date completeTime, Date cancelTime, BigDecimal returnPrice, String cancelUserNum, String cancelReason, String falg1, String flag2, String flag3) {
		this.orderNum = orderNum;
		this.shopNum = shopNum;
		this.shopTypeName = shopTypeName;
		this.orderStatus = orderStatus;
		this.orderLoginName = orderLoginName;
		this.orderType = orderType;
		this.userNum = userNum;
		this.orderUserName = orderUserName;
		this.orderRemark = orderRemark;
		this.orderCreateTime = orderCreateTime;
		this.totalCount = totalCount;
		this.totalPrice = totalPrice;
		this.updatePrice = updatePrice;
		this.actualPrice = actualPrice;
		this.payMethod = payMethod;
		this.payTime = payTime;
		this.payThirtAccount = payThirtAccount;
		this.orderLinkTel = orderLinkTel;
		this.orderLinkMan = orderLinkMan;
		this.orderAddress = orderAddress;
		this.orderLongitude = orderLongitude;
		this.orderLatitude = orderLatitude;
		this.updateTime = updateTime;
		this.payParamsMap = payParamsMap;
		this.freight = freight;
		this.city = city;
		this.area = area;
		this.serviceType = serviceType;
		this.arrivaTime = arrivaTime;
		this.receiveTime = receiveTime;
		this.completeTime = completeTime;
		this.cancelTime = cancelTime;
		this.returnPrice = returnPrice;
		this.cancelUserNum = cancelUserNum;
		this.cancelReason = cancelReason;
		this.falg1 = falg1;
		this.flag2 = flag2;
		this.flag3 = flag3;
	}

	public TOrderInfo(Date orderCreateTime, Date payTime) {
        this.orderCreateTime = orderCreateTime;
        this.payTime = payTime;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="order_id", unique=true, nullable=false)
    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    @Column(name="order_num", length=100)
    public String getOrderNum() {
        return this.orderNum;
    }
    
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    
    @Column(name="order_status")
    public Integer getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    @Column(name="order_login_name", length=100)
    public String getOrderLoginName() {
        return this.orderLoginName;
    }
    
    public void setOrderLoginName(String orderLoginName) {
        this.orderLoginName = orderLoginName;
    }
    
    @Column(name="user_num", length=100)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="order_user_name", length=100)
    public String getOrderUserName() {
        return this.orderUserName;
    }
    
    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }
    
    @Column(name="order_remark", length=100)
    public String getOrderRemark() {
        return this.orderRemark;
    }
    
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="order_create_time", nullable=false, length=19)
    public Date getOrderCreateTime() {
        return this.orderCreateTime;
    }
    
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }
    
    @Column(name="shop_num", length=100)
    public String getShopNum() {
        return this.shopNum;
    }
    
    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }
    
    @Column(name="total_count")
    public Integer getTotalCount() {
        return this.totalCount;
    }
    
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    @Column(name="total_price", precision=10)
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
        
    @Column(name="single_price", precision=10)
    public BigDecimal getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(BigDecimal singlePrice) {
		this.singlePrice = singlePrice;
	}

	@Column(name="actual_price", precision=10)
    public BigDecimal getActualPrice() {
        return this.actualPrice;
    }
    
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
    
    @Column(name="pay_method")
    public Integer getPayMethod() {
        return this.payMethod;
    }
    
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pay_time", length=19)
    public Date getPayTime() {
        return this.payTime;
    }
    
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    @Column(name="pay_thirt_account", length=100)
    public String getPayThirtAccount() {
        return this.payThirtAccount;
    }
    
    public void setPayThirtAccount(String payThirtAccount) {
        this.payThirtAccount = payThirtAccount;
    }
    
    
    @Column(name="falg1", length=100)
    public String getFalg1() {
        return this.falg1;
    }
    
    public void setFalg1(String falg1) {
        this.falg1 = falg1;
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
    public Date getStartTime() {
		return startTime;
	}
    public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Transient
	public TOrderMeal getOrderMeal() {
		return orderMeal;
	}

	public void setOrderMeal(TOrderMeal orderMeal) {
		this.orderMeal = orderMeal;
	}

	@Transient
	public String getOrderStatusString() {
		return orderStatusString;
	}


	public void setOrderStatusString(String orderStatusString) {
		this.orderStatusString = orderStatusString;
	}

	 @Column(name="order_link_tel", length=100)
	public String getOrderLinkTel() {
		return orderLinkTel;
	}


	public void setOrderLinkTel(String orderLinkTel) {
		this.orderLinkTel = orderLinkTel;
	}

	@Column(name="order_link_man", length=100)
	public String getOrderLinkMan() {
		return orderLinkMan;
	}


	public void setOrderLinkMan(String orderLinkMan) {
		this.orderLinkMan = orderLinkMan;
	}

	@Column(name="order_address", length=100)
	public String getOrderAddress() {
		return orderAddress;
	}


	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	@Transient
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}

	@Column(name="order_longitude", length=100)
	public String getOrderLongitude() {
		return orderLongitude;
	}


	public void setOrderLongitude(String orderLongitude) {
		this.orderLongitude = orderLongitude;
	}

	@Column(name="order_latitude", length=100)
	public String getOrderLatitude() {
		return orderLatitude;
	}


	public void setOrderLatitude(String orderLatitude) {
		this.orderLatitude = orderLatitude;
	}


	@Column(name="update_price", precision=10)
	public BigDecimal getUpdatePrice() {
		return updatePrice;
	}


	public void setUpdatePrice(BigDecimal updatePrice) {
		this.updatePrice = updatePrice;
	}

	@Transient
	public String getPayMethodName() {
		return payMethodName;
	}


	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	@Transient
	public String getOrderStatusName() {
		return orderStatusName;
	}


	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	@Transient
	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Transient
	public String getOrderPayTime() {
		return orderPayTime;
	}


	public void setOrderPayTime(String orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time", nullable=false, length=19)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	@Column(name="pay_params_map")
	public String getPayParamsMap() {
		return payParamsMap;
	}
	public void setPayParamsMap(String payParamsMap) {
		this.payParamsMap = payParamsMap;
	}
	
	@Transient
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name="freight")
	public BigDecimal getFreight() {
		return freight;
	}


	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	@Column(name="order_type")
	public Integer getOrderType() {
		return orderType;
	}


	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}


	@Column(name="shop_type_name")
	public String getShopTypeName() {
		return shopTypeName;
	}


	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
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


	@Transient
	public Date getPaystartTime() {
		return paystartTime;
	}


	public void setPaystartTime(Date paystartTime) {
		this.paystartTime = paystartTime;
	}

	@Transient
	public TOrderTrain getOrderTrain() {
		return orderTrain;
	}


	public void setOrderTrain(TOrderTrain orderTrain) {
		this.orderTrain = orderTrain;
	}

	@Transient
	public BigDecimal getTotalActurePrice() {
		return totalActurePrice;
	}


	public void setTotalActurePrice(BigDecimal totalActurePrice) {
		this.totalActurePrice = totalActurePrice;
	}

	@Transient
	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Transient
	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}

	

	@Transient
	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	@Transient
	public String getRs() {
		return rs;
	}


	public void setRs(String rs) {
		this.rs = rs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="arriva_time")
	public Date getArrivaTime() {
		return arrivaTime;
	}


	public void setArrivaTime(Date arrivaTime) {
		this.arrivaTime = arrivaTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="receive_time")
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "complete_time")
	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancel_time")
	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	@Column(name = "return_price")
	public BigDecimal getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(BigDecimal returnPrice) {
		this.returnPrice = returnPrice;
	}

	@Column(name = "cancel_userNum")
	public String getCancelUserNum() {
		return cancelUserNum;
	}

	public void setCancelUserNum(String cancelUserNum) {
		this.cancelUserNum = cancelUserNum;
	}

	@Column(name = "cancel_reason")
	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@Column(name = "service_type")
	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
}


