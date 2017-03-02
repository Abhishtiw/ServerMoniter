package com.est.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "application")
public class Application extends ApplicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int applicationId;
	private String applicationName;
	@Column(name = "ipaddress")
	private int internalIpAddress;
	private String applicationURL;
	private String applicationType;
	private int oldStatusCode;
	private int newStatusCode;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date responseGeneratedTime;
	private String emailId;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public int getInternalIpAddress() {
		return internalIpAddress;
	}

	public void setInternalIpAddress(int internalIpAddress) {
		this.internalIpAddress = internalIpAddress;
	}

	public String getApplicationURL() {
		return applicationURL;
	}

	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public int getOldStatusCode() {
		return oldStatusCode;
	}

	public void setOldStatusCode(int oldStatusCode) {
		this.oldStatusCode = oldStatusCode;
	}

	public int getNewStatusCode() {
		return newStatusCode;
	}

	public void setNewStatusCode(int newStatusCode) {
		this.newStatusCode = newStatusCode;
	}

	public Date getResponseGeneratedTime() {
		return responseGeneratedTime;
	}

	public void setResponseGeneratedTime(Date responseGeneratedTime) {
		this.responseGeneratedTime = responseGeneratedTime;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicationName=" + applicationName
				+ ", internalIpAddress=" + internalIpAddress + ", applicationURL=" + applicationURL
				+ ", applicationType=" + applicationType + ", oldStatusCode=" + oldStatusCode + ", newStatusCode="
				+ newStatusCode + ", responseGeneratedTime=" + responseGeneratedTime + ", emailId=" + emailId + "]";
	}
	
	

}
