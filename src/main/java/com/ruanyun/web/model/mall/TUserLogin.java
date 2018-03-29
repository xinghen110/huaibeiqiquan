package com.ruanyun.web.model.mall;
// Generated 2016-10-9 11:31:00 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TUserLogin generated by hbm2java
 */
@Entity
@Table(name="t_user_login")
public class TUserLogin  implements java.io.Serializable {


     private Integer userLoginId;
     private String userLoginNum;
     private String userNum;
     private String thirdNum;
     private Integer thirdType;

    public TUserLogin() {
    }

    public TUserLogin(String userLoginNum, String userNum, String thirdNum, Integer thirdType) {
       this.userLoginNum = userLoginNum;
       this.userNum = userNum;
       this.thirdNum = thirdNum;
       this.thirdType = thirdType;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="user_login_id", unique=true, nullable=false)
    public Integer getUserLoginId() {
        return this.userLoginId;
    }
    
    public void setUserLoginId(Integer userLoginId) {
        this.userLoginId = userLoginId;
    }
    
    @Column(name="user_login_num", length=50)
    public String getUserLoginNum() {
        return this.userLoginNum;
    }
    
    public void setUserLoginNum(String userLoginNum) {
        this.userLoginNum = userLoginNum;
    }
    
    @Column(name="user_num", length=50)
    public String getUserNum() {
        return this.userNum;
    }
    
    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    @Column(name="third_num", length=200)
    public String getThirdNum() {
        return this.thirdNum;
    }
    
    public void setThirdNum(String thirdNum) {
        this.thirdNum = thirdNum;
    }
    
    @Column(name="third_type")
    public Integer getThirdType() {
        return this.thirdType;
    }
    
    public void setThirdType(Integer thirdType) {
        this.thirdType = thirdType;
    }




}

