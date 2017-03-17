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

/**
 * This entity is meant for the details of the application which we want to add
 * for monitoring.
 */
@Entity
@Table(name = "application")
public class Application extends ApplicationEntity {

	/* decalring variabled required for Application*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "applicationId")
	private int id;
	private String applicationName;
	@Column(name = "ipaddress")
	private String internalIpAddress;
	private String applicationURL;
	private String applicationType;
	private int oldStatusCode;
	private int newStatusCode;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date responseGeneratedTime;
	private boolean active;

	/* getters and setters for the variables declared above.*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getInternalIpAddress() {
		return internalIpAddress;
	}

	public void setInternalIpAddress(String internalIpAddress) {
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// overriding toString method
	@Override
	public String toString() {
		return "Application [id=" + id + ", applicationName=" + applicationName + ", internalIpAddress="
				+ internalIpAddress + ", applicationURL=" + applicationURL + ", applicationType=" + applicationType
				+ ", oldStatusCode=" + oldStatusCode + ", newStatusCode=" + newStatusCode + ", responseGeneratedTime="
				+ responseGeneratedTime + ", active=" + active + "]";
	}

}
