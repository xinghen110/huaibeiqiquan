package com.ruanyun.web.model;
// Generated 2017-9-29 10:07:17 by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TStock generated by hbm2java
 */
@Entity
@Table(name="t_stock"
)
public class TStock  implements java.io.Serializable {


     private String symbol;
     private String symbolPrefix;
     private String symbolName;
     private String insureDate;
     private String optionType;
     private String priceMode;
     private String offerPriceDate;
     private String optionCode;
     private String cycle;
     private String offerPrice;
     private String expireDate;
     private String exerciseMode;

    public TStock() {
    }

	
    public TStock(String symbol) {
        this.symbol = symbol;
    }
    public TStock(String symbol,String symbolPrefix, String symbolName, String insureDate, String optionType, String priceMode, String offerPriceDate, String optionCode, String cycle, String offerPrice, String expireDate, String exerciseMode) {
       this.symbol = symbol;
       this.symbolPrefix = symbolPrefix;
       this.symbolName = symbolName;
       this.insureDate = insureDate;
       this.optionType = optionType;
       this.priceMode = priceMode;
       this.offerPriceDate = offerPriceDate;
       this.optionCode = optionCode;
       this.cycle = cycle;
       this.offerPrice = offerPrice;
       this.expireDate = expireDate;
       this.exerciseMode = exerciseMode;
    }
   
     @Id 
    
    @Column(name="symbol", unique=true, nullable=false, length=32)
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name="symbol_prefix", unique=true, nullable=false, length=32)
    public String getSymbolPrefix() {
        return this.symbolPrefix;
    }

    public void setSymbolPrefix(String symbolPrefix) {
        this.symbolPrefix = symbolPrefix;
    }
    
    @Column(name="symbol_name", length=32)
    public String getSymbolName() {
        return this.symbolName;
    }
    
    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }
    
    @Column(name="insure_date", length=32)
    public String getInsureDate() {
        return this.insureDate;
    }
    
    public void setInsureDate(String insureDate) {
        this.insureDate = insureDate;
    }
    
    @Column(name="option_type", length=32)
    public String getOptionType() {
        return this.optionType;
    }
    
    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }
    
    @Column(name="price_mode", length=32)
    public String getPriceMode() {
        return this.priceMode;
    }
    
    public void setPriceMode(String priceMode) {
        this.priceMode = priceMode;
    }
    
    @Column(name="offer_price_date", length=32)
    public String getOfferPriceDate() {
        return this.offerPriceDate;
    }
    
    public void setOfferPriceDate(String offerPriceDate) {
        this.offerPriceDate = offerPriceDate;
    }
    
    @Column(name="option_code", length=32)
    public String getOptionCode() {
        return this.optionCode;
    }
    
    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }
    
    @Column(name="cycle", length=32)
    public String getCycle() {
        return this.cycle;
    }
    
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
    
    @Column(name="offer_price", length=32)
    public String getOfferPrice() {
        return this.offerPrice;
    }
    
    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }
    
    @Column(name="expire_date", length=32)
    public String getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    
    @Column(name="exercise_mode", length=32)
    public String getExerciseMode() {
        return this.exerciseMode;
    }
    
    public void setExerciseMode(String exerciseMode) {
        this.exerciseMode = exerciseMode;
    }




}

