package com.est.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ill")
public class InternetLeaseLine extends ApplicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "illId")
	private int id;
	private String providerName;
	@Column(name = "ipaddress")
	private String internalIpAddress;
	private String impact;
	private String location;
	private int currentStatus;
	private int previousStatus;
	private boolean primaryIll;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getInternalIpAddress() {
		return internalIpAddress;
	}

	public void setInternalIpAddress(String internalIpAddress) {
		this.internalIpAddress = internalIpAddress;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(int previousStatus) {
		this.previousStatus = previousStatus;
	}

	public boolean isPrimaryIll() {
		return primaryIll;
	}

	public void setPrimaryIll(boolean primaryIll) {
		this.primaryIll = primaryIll;
	}

	@Override
	public String toString() {
		return "InternetLeaseLine [id=" + id + ", providerName=" + providerName + ", internalIpAddress="
				+ internalIpAddress + ", impact=" + impact + ", location=" + location + ", currentStatus="
				+ currentStatus + ", previousStatus=" + previousStatus + ", primaryIll=" + primaryIll + "]";
	}
}
