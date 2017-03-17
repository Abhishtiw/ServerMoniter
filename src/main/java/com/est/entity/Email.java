package com.est.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity is meant for email, for example; what are all the email id's an
 * application needs to send email when there is change in status sometimes
 * email may be sent as 'cc', sometimes email may be sent as 'to'.
 *
 */
@Entity
@Table(name = "email")
public class Email extends ApplicationEntity {

	/**
	 * declaring variables required for Email
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serialId")
	private int id;
	private String emailId;
	private int emailTo;
	private int emailFrom;
	private int emailCc;

	/* setters and getters for the variables declared above*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(int emailTo) {
		this.emailTo = emailTo;
	}

	public int getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(int emailFrom) {
		this.emailFrom = emailFrom;
	}

	public int getEmailCc() {
		return emailCc;
	}

	public void setEmailCc(int emailCc) {
		this.emailCc = emailCc;
	}

	// overriding toString method
	@Override
	public String toString() {
		return "Email [id=" + id + ", emailId=" + emailId + ", emailTo=" + emailTo + ", emailFrom=" + emailFrom
				+ ", emailCc=" + emailCc + "]";
	}

}
