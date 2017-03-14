package com.est.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

import com.est.dto.ApplicationAndStatusDto;
import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.Email;

public class NotifyService {

	@Autowired
	ApplicationService appService;

	@Autowired
	ServletContext servletContext;
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
	 * 
	 */
	public void sendMail() {
		logger.info("Starting sendMail method in NotifyService");
		final String fromEmail;
		final String password;
		try {
			loadProperties();
			logger.info("Loading Properties File Successful");

		} catch (FileNotFoundException ex) {
			logger.warn("properties file not found", ex);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		fromEmail = props.getProperty("username");
		logger.info("Getting Username from the Properties File");
		password = props.getProperty("password");
		logger.info("Getting Password from the Properties File");
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
			Iterator<ApplicationEntity> it = emailList.iterator();
			while (it.hasNext()) {
				logger.info("Iterating Each and Every Email object from the List of Emails");
				Email mail = (Email) it.next();
				String emailId = mail.getEmailId();
				int emailTo = mail.getEmailTo();
				int emailCc = mail.getEmailCc();
				if (emailTo == 1) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
				} else if (emailCc == 1) {
					message.setRecipient(Message.RecipientType.CC, new InternetAddress(emailId));
				} else {
					logger.info("Specify Selected User");
				}
			}
			message.setSubject("About status changes");
			message.setText("status");
			MimeMultipart multipart = new MimeMultipart("related");
			logger.info("Created Multipart Object");
			BodyPart messageBodyPart = new MimeBodyPart();
			logger.info("Created Message Bodypart Object");
			StringBuffer email = new StringBuffer();
			email.append("<html>\n" + "<body>\n" + "\n" + "<table>" + "<tr>" + "<th>" + "Application Name" + "</th>"
					+ "<th>" + "Application Type" + "</th>" + "<th>" + "Application URL" + "</th>" + "<th>"
					+ "ip_address" + "</th>" + "<th>" + "Response_Code" + "</th>" + "<th>" + "Status" + "</th>"
					+ "</tr>");

			List<ApplicationAndStatusDto> appStatusList = appService.getListApplicationAndStatus();
			logger.info("Getting List of Applications From The Database");
			Iterator<ApplicationAndStatusDto> iterator = appStatusList.iterator();
			logger.info("Iterating Each and Every Application Object From the List of Applications Objects");
			while (iterator.hasNext()) {
				ApplicationAndStatusDto appStatusDto = (ApplicationAndStatusDto) iterator.next();
				/* checks the application is active or not */
				if (appStatusDto.isActive()) {
					email.append("<tr>");
					email.append("<td>");
					email.append(appStatusDto.getApplicationName());
					logger.info("Getting Application Name from the Database");
					email.append("</td>");

					email.append("<td>");
					email.append(appStatusDto.getApplicationType());
					logger.info("Getting Application Type from the Database");
					email.append("</td>");

					email.append("<td>");
					email.append(appStatusDto.getApplicationURL());
					logger.info("Getting Application URL from the Database");
					email.append("</td>");

					email.append("<td>");
					email.append(appStatusDto.getInternalIpAddress());
					logger.info("Getting Application IP Address from the Database");
					email.append("</td>");

					email.append("<td>");
					email.append("<span title=" + appStatusDto.getMessage() + ">" + appStatusDto.getNewStatusCode());
					email.append("</span>");
					email.append("</td>");

					email.append("<td>");
					if ((appStatusDto.getNewStatusCode() >= 200) && (appStatusDto.getNewStatusCode() <= 399)) {
						email.append("<img src=\"cid:yes\" alt='up'/>");
					} else {
						email.append("<img src=\"cid:no\" alt='down'/>");
					}
					email.append("</td>");

					email.append("</tr>");
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
			logger.warn("failure in sending email due to ", e);
		}

	}

	/**
	 * 
	 */
	public void sendISPErrorMail() {
		final String fromEmail;
		final String password;
		logger.info("method sendISPErrormail (ISP mail sending block) Execution started");

		try {
			loadProperties();
			logger.info("Loading Properties File Successful");

		} catch (FileNotFoundException ex) {
			logger.error("Properties File Not Found");
			System.out.println(ex);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		fromEmail = props.getProperty("username");
		logger.info("Getting username From the Properties File");
		password = props.getProperty("password");
		logger.info("Getting Password From the Properties File");
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
		logger.info("Fetching the Email object From the Database");
		String emailId = (mail).getEmailId();
		int emailTo = ((Email) mail).getEmailTo();

		/*
		 * if it matches with to address then it will send message to the system
		 * admin
		 */
		if (emailTo == 1) {
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage message = new MimeMessage(session);
			logger.info("Created MimeMessage Object");
			try {

				message.setFrom(new InternetAddress(fromEmail));
				logger.info("Setting the 'From 'address from the InternetAddress object by passing From Email");
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
				logger.info("Added Recipient to the MimeMessage");
				// message.setRecipient(Message.RecipientType.CC, new
				// InternetAddress(emailId));
				message.setSubject("About ILL status changes");

				MimeMultipart multipart = new MimeMultipart("related");
				logger.info("Created MimeMultiPart Object");
				BodyPart messageBodyPart = new MimeBodyPart();
				logger.info("Created MimeBodyPart Object");
				StringBuffer email = new StringBuffer();

				email.append("<html>\n" + "<body>\n");
				email.append("Status change in the Internet service provider");
				email.append("<html>\n" + "<body>\n" + "\n" + "<table >" + "<tr>" + "<th>" + "Application Name"
						+ "</th>" + "<th>" + "Application Type" + "</th>" + "<th>" + "Application URL" + "</th>"
						+ "<th>" + "ip_address" + "</th>" + "<th>" + "Response_Code" + "</th>" + "<th>" + "Status"
						+ "</th>" + "</tr>");
				Application application = appService.getISPList(Application.class);
				logger.info("Getting ISP list From the Database");
				email.append("<tr>");
				email.append("<td>");
				email.append(application.getApplicationName());
				logger.info("Getting ISP Name From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getApplicationType());
				logger.info("Getting ISP Type From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getApplicationURL());
				logger.info("Getting ISP URL From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getInternalIpAddress());
				logger.info("Getting ISP IPAddress From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getNewStatusCode());
				logger.info("Getting ISP Status code From the Database");
				email.append("</td>");

				email.append("<td>");
				if ((application.getNewStatusCode() >= 200) && (application.getNewStatusCode() <= 399)) {
					email.append("<img src=\"cid:yes\" alt='up'/>");
				} else {
					email.append("<img src=\"cid:no\" alt='down'/>");
				}
				email.append("</td>");

				email.append("<tr>");
				email.append("</body>" + "</html>");
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
				logger.info("Email sent successfully");
			} catch (MessagingException e) {
				logger.warn("Failure in sending Email due to ", e);
				e.printStackTrace();
			}
		} else {
			logger.error("'To' Email Address doesn't match with the record in the Database, specify system admin.");
		}
	}

	/*
	*
	*/
	public void sendLostPassword(String emailId, String pwd) {
		final String fromEmail;
		final String password;
		logger.info("Method sendLostPassword() Execution starts");

		try {
			loadProperties();
			logger.info("loading properties file successfull");

		} catch (FileNotFoundException ex) {
			logger.info("Properties File Not Found " + ex);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		fromEmail = props.getProperty("username");
		logger.info("Getting Username From the Properties File");
		password = props.getProperty("password");
		logger.info("Getting Password From the Properties File");
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
		logger.info("Created MimeMessage Object");
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));

			System.out.println("Mail Check");

			message.setSubject("Server monitor application User password");
			message.setText(" Your Password : " + pwd);

			Transport.send(message);
			logger.info("Email sent Successfully");
		} catch (MessagingException e) {
			logger.error("Failure in Sending Email due to MessagingException " + e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void sendPrimaryServerUnreachableMessage() {
		final String fromEmail;
		final String password;
		logger.info("Method sendPrimaryServerUnreachableMessage Execution Starts");

		try {
			loadProperties();
			logger.info("Loading Properties File Successful");

		} catch (FileNotFoundException ex) {
			logger.info("Properties File Not Found");
			System.out.println(ex);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		fromEmail = props.getProperty("username");
		logger.info("Getting Username From the Properties File");
		password = props.getProperty("password");
		logger.info("Getting Password From the Properties File");
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
		logger.info("Fetching Email Object From the Database");
		String emailId = (mail).getEmailId();
		int emailTo = ((Email) mail).getEmailTo();

		/*
		 * if it matches with to address then it will send message to the system
		 * admin
		 */
		if (emailTo == 1) {
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage message = new MimeMessage(session);
			logger.info("Created MimeMessage Object");
			try {

				message.setFrom(new InternetAddress(fromEmail));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
				// message.setRecipient(Message.RecipientType.CC, new
				// InternetAddress(emailId));
				message.setSubject("india VM monitoring");

				MimeMultipart multipart = new MimeMultipart("related");

				BodyPart messageBodyPart = new MimeBodyPart();
				StringBuffer email = new StringBuffer();

				email.append("<html>\n" + "<body>\n");
				email.append("Application  details");
				email.append("<html>\n" + "<body>\n" + "\n" + "<table >" + "<tr>" + "<th>" + "Application Name"
						+ "</th>" + "<th>" + "Application Type" + "</th>" + "<th>" + "Application URL" + "</th>"
						+ "<th>" + "ip_address" + "</th>" + "<th>" + "Response_Code" + "</th>" + "<th>" + "Status"
						+ "</th>" + "</tr>");
				Application application = appService.getISPList(Application.class);
				logger.info("Getting ISP List from the Database");
				email.append("<tr>");
				email.append("<td>");
				email.append(application.getApplicationName());
				logger.info("Getting ISP Name From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getApplicationType());
				logger.info("Getting ISP Type From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getApplicationURL());
				logger.info("Getting ISP URL From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getInternalIpAddress());
				logger.info("Getting ISP IP Address From the Database");
				email.append("</td>");

				email.append("<td>");
				email.append(application.getNewStatusCode());
				logger.info("Getting ISP New Status code From the Database");
				email.append("</td>");

				email.append("<td>");
				if ((application.getNewStatusCode() >= 200) && (application.getNewStatusCode() <= 399)) {
					email.append("<img src=\"cid:yes\" alt='up'/>");
				} else {
					email.append("<img src=\"cid:no\" alt='down'/>");
				}
				email.append("</td>");

				email.append("<tr>");
				email.append("</body>" + "</html>");
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
				logger.error("Failure in Sending Email due to " + e);
				e.printStackTrace();
			}
		} else {
			logger.error(" 'To' Email Address doesn't Match with the record in  the Database-----------");
		}
	}

}
