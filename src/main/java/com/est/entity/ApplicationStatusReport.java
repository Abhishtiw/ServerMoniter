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
 * This entity is meant for generating complete status report of the application
 * includes at what time the response has generated and what is the status at
 * that particular time. and to whom the email are sent when there is a change
 * in the application status along with the message of status change.
 */
@Entity
@Table(name = "app_status_report")
public class ApplicationStatusReport extends ApplicationEntity {
	
	/* decalreing variables for ApplicationStatusReport */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reportId")
	private int id;
	private int applicationId;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date generatedTime;
	private String applicationName;
	private int currentStatus;
	private int emailTo;
	private int emailCc;
	private String emailId;
	private String message;

	/* setters and getters for the variables declared above.*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getMessage() {
		return message;
	}

	public int getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(int emailTo) {
		this.emailTo = emailTo;
	}

	public int getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(int emailCc) {
		this.emailCc = emailCc;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public Date getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

}
