package com.est.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;

import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.Email;

public class NotifyService {

	@Autowired
	ApplicationService appService;
	private Properties props = null;

	/**
	 * Loading mail related properties:User name and password of sender
	 * 
	 * @throws IOException
	 */
	private void loadProperties() throws IOException {
		props = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("mail.properties");
		props.load(input);
	}

	/**
	 * 
	 */
	public void sendMail() {
		final String fromEmail;
		final String password;
		System.out.println("Mail sending Block");

		try {
			loadProperties();
			System.out.println("loading properties successfull!!");


		} catch (FileNotFoundException ex) {
			System.out.println("Properties file is not found");
			System.out.println(ex);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	
		fromEmail = props.getProperty("username");
		password = props.getProperty("password");
		props.put("mail.smtp.host", props.getProperty("smtpHost"));
		props.put("mail.smtp.port", props.getProperty("TLSport"));
		props.put("mail.smtp.auth", props.getProperty("authValue"));
		props.put("mail.smtp.starttls.enable", props.getProperty("TLSvalue"));
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		// fetching to system admin address from the database

		Email mail = (Email) appService.getEntityByID(Email.class, 1);
		String emailId = (mail).getEmailId();
		int emailTo = ((Email) mail).getEmailTo();

		/*
		 * if it matches with to address then it will send message to the system
		 * admin
		 */
		if (emailTo == 1) {
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage message = new MimeMessage(session);
			try {

				message.setFrom(new InternetAddress(fromEmail));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
				// message.setRecipient(Message.RecipientType.CC, new
				// InternetAddress(emailId));
				message.setSubject("About status changes");
				message.setText("status");
				StringBuffer email = new StringBuffer();
				email.append("<html>\n" + "<body>\n" + "\n" + "<table>" + "<tr>" + "<th>" + "Application Name" + "</th>"
						+ "<th>" + "Application Type" + "</th>" + "<th>" + "Application URL" + "</th>" + "<th>"
						+ "ip_address" + "</th>" + "<th>" + "Response_Code" + "</th>" + "<th>" + "Status" + "</th>"
						+ "</tr>");

				List<ApplicationEntity> applicationList = appService.getEntityList(Application.class);
				Iterator<ApplicationEntity> iterator = applicationList.iterator();
				while (iterator.hasNext()) {
					Application application = (Application) iterator.next();
					email.append("<tr>");
					email.append("<td>");
					email.append(application.getApplicationName());
					email.append("</td>");

					email.append("<td>");
					email.append(application.getApplicationType());
					email.append("</td>");

					email.append("<td>");
					email.append(application.getApplicationURL());
					email.append("</td>");

					email.append("<td>");
					email.append(application.getInternalIpAddress());
					email.append("</td>");

					email.append("<td>");
					email.append(application.getNewStatusCode());
					email.append("</td>");

					email.append("<td>");
					if ((application.getNewStatusCode() >= 200) && (application.getNewStatusCode() <= 399)) {
						email.append("<img src='/ServerMonitor/src/main/webapp/resources/image/green_yes.jpg' alt='up'/>");
					} else {
						email.append("<img src='/ServerMonitor/src/main/webapp/resources/image/red_no.jpg' alt='down'/>");
					}
					email.append("</td>");

					email.append("<tr>");
				}
				email.append("</table>" + "</body>" + "</html>");
				message.setContent(email.toString(), "text/html");
				Transport.send(message);
				System.out.println("Mail Sent");
			} catch (MessagingException e) {
				System.out.println("Mail fail");
				e.printStackTrace();
			}
		} else {
			System.out.println("Your To address doesnot match with database " + emailTo);
			System.out.println("specify the system admin");
		}
	}
}
