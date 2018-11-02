package com.ruoyi.framework.message.yy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="sms.yy")
public class YySmsConfig implements java.io.Serializable {

	private String signature;
	private String url;
	private String appKey;
	private String appSecrect;
	/**是否打开*/
	private boolean isOpen;
	
	public boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecrect() {
		return appSecrect;
	}
	public void setAppSecrect(String appSecrect) {
		this.appSecrect = appSecrect;
	}
}
