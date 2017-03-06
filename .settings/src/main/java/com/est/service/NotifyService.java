package com.est.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.est.dao.ApplicationDao;
import com.est.entity.Email;

public class NotifyService {
	@Autowired
	private ApplicationDao appDao;
	private String emailId;
	private String from;
	private String to;
	private String cc;
	private final String subject = "STATUS REPORTS OF APPLICATIONS";
	private String mailBody;
/**
 * 
 */
	public void sendMail() {
		Email email = (Email) appDao.getEntityByID(Email.class, 1);
		emailId = email.getEmailId();
		if (email.getEmailCc() == 1) {
			cc = emailId;
		}
		if (email.getEmailFrom() == 1) {
			from = emailId;
		}
		if (email.getEmailTo() == 1) {
			to = emailId;
		}
		
	}

}
