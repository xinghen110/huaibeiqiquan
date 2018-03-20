package com.push;

public class PushModel {
	private Long appKey; // 应用 appkey
	private String target;//推送目标: device:推送给设备; account:推送给指定帐号,tag:推送给自定义标签; all: 推送给全部
	private String targetValue; //根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
	private Integer deviceType;// 设备类型deviceType 取值范围为:0~3. iOS设备: 0; Android设备: 1; 全部: 3, 这是默认值.
	private Integer type;// 0:表示消息(默认为0), 1:表示通知
	private String  title;//消息的标题
	private String body; //消息的内容
	private String summary; //iOS通知内容
	public Long getAppKey() {
		return appKey;
	}
	public void setAppKey(Long appKey) {
		this.appKey = appKey;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public PushModel(Long appKey, String target, Integer deviceType,Integer type) {
		this.appKey = appKey;
		this.target = target;
		this.deviceType = deviceType;
		this.type=type;
	}
	
	
	

}
