package com.ruanyun.web.model.mall;
// Generated 2017-4-5 11:29:48 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 版本更新
 */
@Entity
@Table(name="t_version_update"
)
public class TVersionUpdate  implements java.io.Serializable {


     private Integer versionUpdateId;
     private String versionUpdateNum;
     private Integer type;
     private String url;
     private Integer versionCode;
     private String shopUrl;
     private Integer shopCode;
     private String wuliuUrl;
     private Integer wuliuCode;

    public TVersionUpdate() {
    }

    public TVersionUpdate(String versionUpdateNum, Integer type, String url, Integer versionCode) {
       this.versionUpdateNum = versionUpdateNum;
       this.type = type;
       this.url = url;
       this.versionCode = versionCode;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="version_update_id", unique=true, nullable=false)
    public Integer getVersionUpdateId() {
        return this.versionUpdateId;
    }
    
    public void setVersionUpdateId(Integer versionUpdateId) {
        this.versionUpdateId = versionUpdateId;
    }
    
    @Column(name="version_update_num", length=100)
    public String getVersionUpdateNum() {
        return this.versionUpdateNum;
    }
    
    public void setVersionUpdateNum(String versionUpdateNum) {
        this.versionUpdateNum = versionUpdateNum;
    }
    
    @Column(name="type")
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    @Column(name="url", length=100)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="version_code")
    public Integer getVersionCode() {
        return this.versionCode;
    }
    
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
    @Column(name="shop_url")
	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	@Column(name="shop_code")
	public Integer getShopCode() {
		return shopCode;
	}

	public void setShopCode(Integer shopCode) {
		this.shopCode = shopCode;
	}
	@Column(name="wuliu_url")
	public String getWuliuUrl() {
		return wuliuUrl;
	}

	public void setWuliuUrl(String wuliuUrl) {
		this.wuliuUrl = wuliuUrl;
	}
	@Column(name="wuliu_code")
	public Integer getWuliuCode() {
		return wuliuCode;
	}

	public void setWuliuCode(Integer wuliuCode) {
		this.wuliuCode = wuliuCode;
	}

    



}


