package com.est.dto;

import java.util.Date;

public class ApplicationAndStatusDto {

	private int id;
	private String ApplicationName;
	private String ApplicationType;
	private String ApplicationURL;
	private String internalIpAddress;
	private int newStatusCode;
	private int oldStatusCode;
	private String message;
	private boolean active;
	private Date responseGeneratedTime;

	
	
	
	public String getInternalIpAddress() {
		return internalIpAddress;
	}

	public void setInternalIpAddress(String internalIpAddress) {
		this.internalIpAddress = internalIpAddress;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	

	public int getOldStatusCode() {
		return oldStatusCode;
	}

	public void setOldStatusCode(int oldStatusCode) {
		this.oldStatusCode = oldStatusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getResponseGeneratedTime() {
		return responseGeneratedTime;
	}

	public void setResponseGeneratedTime(Date responseGeneratedTime) {
		this.responseGeneratedTime = responseGeneratedTime;
	}

	@Override
	public String toString() {
		return "ApplicationAndStatusDto [id=" + id + ", ApplicationName=" + ApplicationName + ", ApplicationType="
				+ ApplicationType + ", ApplicationURL=" + ApplicationURL + ", internalIpAddress=" + internalIpAddress
				+ ", newStatusCode=" + newStatusCode + ", oldStatusCode=" + oldStatusCode + ", message=" + message
				+ ", active=" + active + ", responseGeneratedTime=" + responseGeneratedTime + "]";
	}



	
}
