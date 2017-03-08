package com.est.dto;

import java.util.Date;

public class ApplicationAndStatusDto {

	private int ApplicationId;
	private String ApplicationName;
	private String ApplicationType;
	private String ApplicationURL;
	private String internalIpAddress;
	private int newStatusCode;
	private String message;
	private Date responseGeneratedTime;
	
	
	
	public String getInternalIpAddress() {
		return internalIpAddress;
	}
	public void setInternalIpAddress(String internalIpAddress) {
		this.internalIpAddress = internalIpAddress;
	}
	public int getApplicationId() {
		return ApplicationId;
	}
	public void setApplicationId(int applicationId) {
		ApplicationId = applicationId;
	}
	public String getApplicationName() {
		return ApplicationName;
	}
	public void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}
	public String getApplicationType() {
		return ApplicationType;
	}
	public void setApplicationType(String applicationType) {
		ApplicationType = applicationType;
	}
	public String getApplicationURL() {
		return ApplicationURL;
	}
	public void setApplicationURL(String applicationURL) {
		ApplicationURL = applicationURL;
	}
	public int getNewStatusCode() {
		return newStatusCode;
	}
	public void setNewStatusCode(int newStatusCode) {
		this.newStatusCode = newStatusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getResponseGeneratedTime() {
		return responseGeneratedTime;
	}
	public void setResponseGeneratedTime(Date responseGeneratedTime) {
		this.responseGeneratedTime = responseGeneratedTime;
	}
	
	
	@Override
	public String toString() {
		return "ApplicationAndStatusDto [ApplicationId=" + ApplicationId + ", ApplicationName=" + ApplicationName
				+ ", ApplicationType=" + ApplicationType + ", ApplicationURL=" + ApplicationURL + ", internalIpAddress="
				+ internalIpAddress + ", newStatusCode=" + newStatusCode + ", message=" + message
				+ ", responseGeneratedTime=" + responseGeneratedTime + "]";
	}
	
	
	
	
	
	
		
}
