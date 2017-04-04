package com.est.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.mail.PasswordAuthentication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.est.dao.ApplicationDao;
import com.est.dto.ApplicationAndStatusDto;
import com.est.entity.ApplicationEntity;
import com.est.entity.ApplicationStatusReport;
import com.est.entity.Email;
import com.est.entity.InternetLeaseLine;

public class NotifyService {
	@Autowired
	private ApplicationService appService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	ApplicationStatusReport appStatusReport;
	@Autowired
	private ApplicationDao appDao;
	private Properties props = null;
	private static final Logger logger = Logger.getLogger(NotifyService.class);

	/**
	 * Loading mail related properties:User name and password of sender
	 * 
	 * @throws IOException
	 */
	private void loadProperties() throws IOException {
		logger.info("Loading Properties File in NotifyService");
		props = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("mail.properties");
		props.load(input);
	}

	/**
	 * Mail sending block which contains the status of applications List and Ill
	 * list.
	 */
	public void sendMail(int changedAppCount) {
		logger.info("Starting sendMail method in NotifyService");
		final String fromEmail;
		final String password;
		try {
			loadProperties();
			logger.info("Loading Properties File Successful");
		} catch (FileNotFoundException ex) {
			logger.error("properties file not found", ex);
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
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			List<ApplicationEntity> emailList = appService.getEntityList(Email.class);
			logger.info("Getting Email List From the Database");
			for (ApplicationEntity entity : emailList) {
				Email mail = (Email) entity;
				String emailId = mail.getEmailId();
				int emailTo = mail.getEmailTo();
				int emailCc = mail.getEmailCc();
				if (emailTo == 1) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
				} else if (emailCc == 1) {
					message.setRecipient(Message.RecipientType.CC, new InternetAddress(emailId));
				} else {
					logger.info("Specify User");
				}
			}
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			StringBuffer email = new StringBuffer();
			email.append("<html>\n" + "<body>\n");
			email.append(
					"<div style=' background-color: rgba(109, 109, 109, 0.66); width:860px; height:40px;text-align:left;color:white; padding-top: 1px;'>"
							+ "<h3>" + "Following is the Internet Leased Line Status as on: " + new Date() + "</h3>"
							+ "</div>");

			email.append(
					"<table align='center' border='1' width='100%'style='border-collapse: collapse;background: #efefef;border: ridge #7f8486 2.0pt;'>"
							+ "<tr style='color:rgba(48, 87, 154, 0.97);border: none; border-bottom: solid #7f8486 1.0pt;padding-bottom: 10px;padding-top: 10px;width: 58px;height: 33px; font-weight: bold;' align='left'>"
							+ "<th>" + "Provider" + "</th>" + "<th>" + "Location" + "</th>" + "<th>" + "ip_address"
							+ "</th>" + "<th>" + "Impact" + "</th>" + "<th>" + "Current Status" + "</th>" + "</tr>");
			List<ApplicationAndStatusDto> appStatusList = appService.getListApplicationAndStatus();
			int noOfApplication = appStatusList.size();
			List<ApplicationEntity> appList = appService.getEntityList(InternetLeaseLine.class);
			Iterator<ApplicationEntity> iter = appList.iterator();
			while (iter.hasNext()) {
				logger.info("Getting ILL Details And Setting And Sending Email");
				InternetLeaseLine illApp = (InternetLeaseLine) iter.next();
				if (illApp.getCurrentStatus() != 200) {
					email.append(
							"<tr style='color:red;border: none; border-bottom: solid #7f8486 1.0pt; font-family: arial,sans-serif;'>");
				} else {
					email.append(
							"<tr style ='border: none; border-bottom: solid #7f8486 1.0pt; font-family: arial,sans-serif;'>");
				}
				email.append("<td>" + illApp.getProviderName() + "</td>");
				email.append("<td>" + illApp.getLocation() + "</td>");
				email.append("<td>" + illApp.getInternalIpAddress() + "</td>");
				email.append("<td>" + illApp.getImpact() + "</td>");
				email.append("<td align='center'>");
				// int toolTipMsg = illApp.getCurrentStatus();
				if ((illApp.getCurrentStatus() >= 200) && (illApp.getCurrentStatus() <= 399)) {
					email.append("<span title = ILL_UP >" + "<img src=\"cid:yes\" alt='up'/>" + "</span>");
				} else {
					email.append("<span title = ILL_DOWN >" + "<img src=\"cid:no\" alt='down'/>" + "</span>");
				}
				email.append("</td>");
				
				for (ApplicationEntity entity : emailList) {
					logger.info("Inserting ILL details to app_status_report");
					String mailId = null;
					Email emailId = (Email) entity;
					int cc = emailId.getEmailCc();
					int to = emailId.getEmailTo();
					List<String> ccList = new ArrayList<String>();
					List<String> toList = new ArrayList<String>();
					if (cc == 1) {
						mailId = emailId.getEmailId();
						ccList.add(mailId);
					}
					if (to == 1) {
						mailId = emailId.getEmailId();
						toList.add(mailId);
					}
					appStatusReport.setApplicationId(illApp.getId());
					appStatusReport.setApplicationName(illApp.getProviderName());
					appStatusReport.setCurrentStatus(illApp.getCurrentStatus());
					appStatusReport.setGeneratedTime(new Date());
					int code = illApp.getCurrentStatus();
					String msg = appDao.getStatusMsg(code);
					appStatusReport.setMessage(msg);

					if (ccList != null && !ccList.isEmpty()) {
						for (String eId : ccList) {
							appStatusReport.setEmailId(eId);
							appStatusReport.setEmailTo(0);
							appStatusReport.setEmailCc(1);
						}
					}
					if (toList != null && !toList.isEmpty()) {
						for (String eId : toList) {
							appStatusReport.setEmailId(eId);
							appStatusReport.setEmailTo(1);
							appStatusReport.setEmailCc(0);
						}
					}
					appDao.addEntity(appStatusReport);
					logger.info("Inserted ILL Details into app_status_report");
				}
				logger.info("Inserted Into Email Body Completed");
				if (illApp.isPrimaryIll()) {
					if (illApp.getCurrentStatus() == 200) {
						if (changedAppCount > 0) {
							message.setSubject(" AHS US Status -  <" + changedAppCount
									+ "> application(s) has changed their status  [alpha test]");
						} else {
							message.setSubject("AHS US Status - UP[alpha test]");
						}

					} else {
						message.setSubject(" AHS US Status - DOWN, and <" + noOfApplication
								+ "> Applications Are Down [alpha test]");
					}
				}
			}

			email.append("</table>" + "</body>" + "</html>");
			email.append("<br>");

			email.append(
					"<div style=' background-color: rgba(109, 109, 109, 0.66); width:860px; height:40px;text-align:left;color:white;padding-top: 1px;'>");
			email.append("<h3>" + "Following is the Application Health Status as on" + new Date() + "&nbsp;"
					+ "<a href ='https://apps.estuate.com'>Estuate Apps</a>" + "</h3>");
			email.append("</div>");

			email.append("<html>\n" + "<body>\n" + "\n"
					+ "<table align='center' border='1' width='100%'style='border-collapse: collapse;background: #efefef;border: ridge #7f8486 2.0pt;'>"
					+ "<tr style='color:rgba(48, 87, 154, 0.97);border: none; border-bottom: solid #7f8486 1.0pt;padding-bottom: 10px;padding-top: 10px;width: 58px;height: 33px; font-weight: bold;' align='left'>"
					+ "<th>" + "Application Name" + "</th>" + "<th>" + "Application Type" + "</th>" + "<th>"
					+ "ip_address" + "</th>"
					+ "<th align='center'>" + "Current Status" + "</th>" + "</tr>"
					+ "</thead>");

			for (ApplicationAndStatusDto appStatusDto : appStatusList) {
				logger.info("Inserting Application Record to app_status_report");
				for (ApplicationEntity entity : emailList) {
					String mailId = null;
					Email emailId = (Email) entity;
					int cc = emailId.getEmailCc();
					int to = emailId.getEmailTo();
					List<String> ccList = new ArrayList<String>();
					List<String> toList = new ArrayList<String>();
					if (cc == 1) {
						mailId = emailId.getEmailId();
						ccList.add(mailId);
					}
					if (to == 1) {
						mailId = emailId.getEmailId();
						toList.add(mailId);
					}
					appStatusReport.setApplicationId(appStatusDto.getId());
					appStatusReport.setApplicationName(appStatusDto.getApplicationName());
					appStatusReport.setCurrentStatus(appStatusDto.getNewStatusCode());
					appStatusReport.setGeneratedTime(new Date());
					int code = appStatusDto.getNewStatusCode();
					String msg = appDao.getStatusMsg(code);
					appStatusReport.setMessage(msg);
					if (ccList != null && !ccList.isEmpty()) {
						for (String eId : ccList) {
							appStatusReport.setEmailId(eId);
							appStatusReport.setEmailTo(0);
							appStatusReport.setEmailCc(1);
						}
					}
					if (toList != null && !toList.isEmpty()) {
						for (String eId : toList) {
							appStatusReport.setEmailId(eId);
							appStatusReport.setEmailTo(1);
							appStatusReport.setEmailCc(0);
						}
					}
					appDao.addEntity(appStatusReport);
				}
				logger.info("Inserting Application into app_status_report is Completed");

				message.setText("status");

				/* checks the application is active or not */
				if (appStatusDto.isActive()) {

					if (appStatusDto.getNewStatusCode() != 200) {
						email.append(
								"<tr style='color:red;border: none; border-bottom: solid #7f8486 1.0pt; font-family: arial,sans-serif;'>");
					} else {
						email.append(
								"<tr style='border: none; border-bottom: solid #7f8486 1.0pt; font-family: arial,sans-serif;'>");
					}

					email.append("<td>" + "<a href= '" + appStatusDto.getApplicationURL() + "'>"
							+ appStatusDto.getApplicationName() + "</a>" + "</td>");

					email.append("<td>" + appStatusDto.getApplicationType() + "</td>");

					email.append("<td>" + appStatusDto.getInternalIpAddress() + "</td>");

					Iterator<ApplicationEntity> illIterator = appList.iterator();
					while (illIterator.hasNext()) {
						InternetLeaseLine illApp = (InternetLeaseLine) illIterator.next();
						if (illApp.isPrimaryIll()) {
							int statusCode = illApp.getCurrentStatus();
							if (statusCode != 200) {
								email.append("<td align='center'>");
								email.append("<span title=" + appStatusDto.getMessage() + ">"
										+ "<img src=\"cid:no\" alt='down'/>" + "</span>");
								email.append("</td>");
							} else {
								email.append("<td align='center'>");
								if ((appStatusDto.getNewStatusCode() >= 200)
										&& (appStatusDto.getNewStatusCode() <= 399)) {
									email.append("<span title=" + appStatusDto.getMessage() + ">"
											+ "<img src=\"cid:yes\" alt='up'/>" + "</span>");
								} else {
									email.append("<span title=" + appStatusDto.getMessage() + ">"
											+ "<img src=\"cid:no\" alt='down'/>" + "</span>");
								}
								email.append("</td>");

							}
							break;
						}
						
					}

				}
			}
			email.append("</table>" + "</body>" + "</html>");
			messageBodyPart.setContent(email.toString(), "text/html");
			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			BodyPart yesPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(servletContext.getRealPath("resources/image/up.png"));

			yesPart.setDataHandler(new DataHandler(fds));
			yesPart.setHeader("Content-ID", "<yes>");

			BodyPart noPart = new MimeBodyPart();
			DataSource fds2 = new FileDataSource(servletContext.getRealPath("resources/image/down.png"));

			noPart.setDataHandler(new DataHandler(fds2));
			noPart.setHeader("Content-ID", "<no>");

			// add image to the multipart
			multipart.addBodyPart(yesPart);
			multipart.addBodyPart(noPart);

			// put everything together
			message.setContent(multipart);
			Transport.send(message);
			logger.info("Email Sent Successfully");
		} catch (MessagingException e) {
			logger.error("failure in sending email due to ", e);
		}
	}

	/**
	 * Mail sending block which sends the Password
	 */
	public void sendLostPassword(String emailId, String pwd) {
		final String fromEmail;
		final String password;
		logger.info("Method sendLostPassword() Execution starts");

		try {
			loadProperties();
		} catch (FileNotFoundException ex) {
			logger.info("Properties File Not Found " + ex);
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

		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));

			message.setSubject("AHS User Password");
			message.setText(" Dear User, \n\n \t Your AHS Password is : " + pwd
					+ "\n\n\t Kindly go through this link http://localhost:8080/servermonitor/ for login");

			Transport.send(message);
			logger.info("Email sent Successfully");
		} catch (MessagingException e) {
			logger.error("Failure in Sending Email due to MessagingException " + e);
			e.printStackTrace();
		}
	}

}