package com.ruanyun.web.model;
// Generated 2017-10-10 21:38:32 by Hibernate Tools 3.2.2.GA


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TUserStock generated by hbm2java
 */
@Entity
@Table(name="t_user_stock"
)
public class TUserStock  implements java.io.Serializable {


     private Integer id;
     private int userId;
     private String symbol;

    /**
     * 注入股票基本信息
     */
    private String stockName;

    public TUserStock() {
    }

	
    public TUserStock(int userId) {
        this.userId = userId;
    }
    public TUserStock(int userId, String symbol) {
       this.userId = userId;
       this.symbol = symbol;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="user_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Column(name="symbol", length=32)
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Transient
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}


