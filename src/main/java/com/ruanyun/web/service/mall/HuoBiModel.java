package com.ruanyun.web.service.mall;

import net.sf.json.JSONObject;

public class HuoBiModel {
	private String symbol;
	private String type;
	private Object result;
	private String klineType;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public HuoBiModel(String symbol, String type, Object result) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.result = result;
	}
	public HuoBiModel(String symbol, String type, String klineType, Object result) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.result = result;
		this.klineType=klineType;
	}
	public HuoBiModel() {
		super();
	}
	@Override
	public String toString() {
		return new JSONObject().fromObject(this).toString();
	}
	public String getKlineType() {
		return klineType;
	}
	public void setKlineType(String klineType) {
		this.klineType = klineType;
	}
	
	

}
