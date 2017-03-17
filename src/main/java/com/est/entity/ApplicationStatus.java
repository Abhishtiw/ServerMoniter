package com.est.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity is meant for displaying the status of the application i.e what is
 * the status code that the application returns and what is the meaning of that
 * status code.
 */
@Entity
@Table(name = "app_status")
public class ApplicationStatus extends ApplicationEntity {
	/* declaring variables required for ApplicationStatus */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "statusId")
	private int id;
	private int statusCode;
	private String statusMessage;

	/* setters and getters for the decalred variables above */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
