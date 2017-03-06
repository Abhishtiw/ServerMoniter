package com.est.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email")
public class Email extends ApplicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int serialId;
	private String emailId;
	private int emailTo;
	private int emailFrom;
	private int emailCc;

	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
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

	@Override
	public String toString() {
		return "Email [serialId=" + serialId + ", emailId=" + emailId + ", emailTo=" + emailTo + ", emailFrom="
				+ emailFrom + ", emailCc=" + emailCc + "]";
	}

}
