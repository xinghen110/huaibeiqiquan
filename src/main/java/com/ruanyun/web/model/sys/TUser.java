package com.ruanyun.web.model.sys;
// Generated 2013-11-22 9:55:13 by Hibernate Tools 3.2.2.GA


import java.util.ArrayList;
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

import com.ruanyun.web.model.mall.TShopInfo;

/**
 * 用户基础信息
 */
@Entity
@Table(name="t_user"
)
public class TUser  implements java.io.Serializable {



	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userNum;
	private String nickName;
	 private String loginName;
	 private String loginPass;
	 private String shopName;
	 private String shopNum;
	 private Integer userSex;
	 private String userPhone;
	 private String createUserNum;
	 private Date createTime;
	 private Integer userStatus;
	private String userCode;
	private String parentCode;//父级code
	 private String userPhoto;
	 private Integer userType;   //查看Constants里配置
	 private String city;
	 private String province;
	 private String area;
	private Date userBirth;
	 private TShopInfo shopInfo;
	 private String payPassword;  //支付密码
	 private Integer workStatus;  //工作状态
	 private String longitude;// 经度
	 private String latitude;//纬度
	 private Integer auditShopStatus;   //用户有无店铺状态 1有店铺且审核通过  2有店铺且待审核
     private Integer bindStatus;    //是否绑定银行卡 ，只针对店铺用户
     private Integer sendMsgStatus;
	private Date startTime;
	private Integer isDefaultAddress;//是否有默认地址  1是  2否
	private Integer shopType;
	private String registrationId;//极光推送设备标识号
	private String typeNum;
	private String areaid;

     /**
 	 * 用户类型 在登录时候设置
 	 */
 	private String requestType;
 	private Integer isMember;  //是否会员  1是  2否
 	private Integer memberLevel;//会员等级（1游客  2普通会员 3高级会员 4自尊会员）
 	private Integer jishiCount;//技师数量
	private Integer cfOrderCount;//购买会员卡的数量

 	/**
 	 * 角色
 	 */
 	private TRole role;
 	/**
 	 * 权限
 	 */
 	private List<TAuthority> auths = new ArrayList<TAuthority>(0);
 	/**
 	 * url
 	 */
 	private List<TAuthority> urls = new ArrayList<TAuthority>(0);
 	/**
 	 * 手机用户登录返回信息
 	 */
 	private String loginError;
 	
 	/**
 	 * 结束时间
 	 */
 	public Date endDate;
 	
 	private String validityDate;//会员有效期
 	
    public TUser() {
    }
    
    public TUser(String loginName, String nickName, String loginPass,   Integer userSex,  String createUserNum, Date createTime, Integer userStatus,String userPhoto) {
       this.loginName = loginName;
       this.nickName = nickName;
       this.loginPass = loginPass;
     
       this.userSex = userSex;
     
       this.createUserNum = createUserNum;
       this.createTime = createTime;
       this.userStatus = userStatus;
        
    }

	public TUser(String userNum, String nickName, String loginName, String loginPass, String shopName, String shopNum, Integer userSex, String userPhone, String createUserNum, Date createTime, Integer userStatus, String userPhoto, Integer userType, String city, String province, String area, Date userBirth, String payPassword, Integer workStatus, Integer auditShopStatus, Integer bindStatus, Integer sendMsgStatus, Integer isMember) {
		this.userNum = userNum;
		this.nickName = nickName;
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.shopName = shopName;
		this.shopNum = shopNum;
		this.userSex = userSex;
		this.userPhone = userPhone;
		this.createUserNum = createUserNum;
		this.createTime = createTime;
		this.userStatus = userStatus;
		this.userPhoto = userPhoto;
		this.userType = userType;
		this.city = city;
		this.province = province;
		this.area = area;
		this.userBirth = userBirth;
		this.payPassword = payPassword;
		this.workStatus = workStatus;
		this.auditShopStatus = auditShopStatus;
		this.bindStatus = bindStatus;
		this.sendMsgStatus = sendMsgStatus;
		this.isMember = isMember;
	}

	public TUser(String userNum) {
		super();
		this.userNum = userNum;
	}

	@Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="user_id", unique=true, nullable=false)
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="login_name", length=100)
    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    @Column(name="nick_name", length=100)
    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    @Column(name="login_pass", length=100)
    public String getLoginPass() {
        return this.loginPass;
    }
    
    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }
    
 
    @Column(name="parent_code", length=100)
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name="user_sex")
    public Integer getUserSex() {
        return this.userSex;
    }
    
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
       
    @Column(name="member_level")
	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Column(name="create_user_num")
    public String getCreateUserNum() {
        return this.createUserNum;
    }
    
    public void setCreateUserNum(String createUserNum) {
        this.createUserNum = createUserNum;
    }
    @Column(name="create_time")
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="user_status")
    public Integer getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    
   

    
	 @Column(name="user_photo", length=200)
	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	@Transient
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	@Transient
	public TRole getRole() {
		return role;
	}

	public void setRole(TRole role) {
		this.role = role;
	}
	@Transient
	public List<TAuthority> getAuths() {
		return auths;
	}

	public void setAuths(List<TAuthority> auths) {
		this.auths = auths;
	}
	@Transient
	public List<TAuthority> getUrls() {
		return urls;
	}

	public void setUrls(List<TAuthority> urls) {
		this.urls = urls;
	}
 
	@Transient
	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
    @Transient
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name="shop_name")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name="shop_num")
	public String getShopNum() {
		return shopNum;
	}

	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}

	@Column(name="user_type")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name="user_num")
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	 

	@Transient
	public TShopInfo getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(TShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="user_birth", length=19)
	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	
	@Column(name="user_phone")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name="pay_password")
	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	@Column(name="work_status")
	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}
	
	@Column(name="city")
	 public String getCity() {
			return city;
	}
	 public void setCity(String city) {
			this.city = city;
	}
	 @Transient
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Transient
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="audit_shop_status")
	public Integer getAuditShopStatus() {
		return auditShopStatus;
	}

	public void setAuditShopStatus(Integer auditShopStatus) {
		this.auditShopStatus = auditShopStatus;
	}

	@Column(name="province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	
	@Column(name="bind_status")
	public Integer getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(Integer bindStatus) {
		this.bindStatus = bindStatus;
	}

	
	@Column(name="area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	@Column(name="send_msg_status")
	public Integer getSendMsgStatus() {
		return sendMsgStatus;
	}

	public void setSendMsgStatus(Integer sendMsgStatus) {
		this.sendMsgStatus = sendMsgStatus;
	}

	
	@Column(name="is_member")
	public Integer getIsMember() {
		return isMember;
	}

	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}

	@Transient
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "is_default_address")
	public Integer getIsDefaultAddress() {
		return isDefaultAddress;
	}

	public void setIsDefaultAddress(Integer isDefaultAddress) {
		this.isDefaultAddress = isDefaultAddress;
	}
		
	@Column(name = "registration_id")
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@Transient
	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	
	@Transient
	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}
	
	@Transient
	public String getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(String typeNum) {
		this.typeNum = typeNum;
	}

	@Transient
	public Integer getJishiCount() {
		return jishiCount;
	}

	public void setJishiCount(Integer jishiCount) {
		this.jishiCount = jishiCount;
	}

	@Column(name = "user_code")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "areaid")
	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	private String flag1;
    private String flag2;
    private String flag3;

    @Transient
	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	@Transient
	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	@Transient
	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}
	private TUser user;
	@Transient
	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	@Transient
	public Integer getCfOrderCount() {
		return cfOrderCount;
	}

	public void setCfOrderCount(Integer cfOrderCount) {
		this.cfOrderCount = cfOrderCount;
	}
}


