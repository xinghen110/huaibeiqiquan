package com.ruanyun.web.model.sys;
// Generated 2016-9-20 18:31:06 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TProvince generated by hbm2java
 */
@Entity
@Table(name="t_province"
)
public class TProvince  implements java.io.Serializable {


     private int provinceId;
     private String provinceCode;
     private String provinceName;
     private Integer status;

    public TProvince() {
    }

	
    public TProvince(int provinceId) {
        this.provinceId = provinceId;
    }
    public TProvince(int provinceId, String provinceCode, String provinceName, Integer status) {
       this.provinceId = provinceId;
       this.provinceCode = provinceCode;
       this.provinceName = provinceName;
       this.status = status;
    }
   
     @Id 
    
    @Column(name="province_id", unique=true, nullable=false)
    public int getProvinceId() {
        return this.provinceId;
    }
    
    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
    
    @Column(name="province_code", length=10)
    public String getProvinceCode() {
        return this.provinceCode;
    }
    
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    @Column(name="province_name", length=100)
    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }




}


