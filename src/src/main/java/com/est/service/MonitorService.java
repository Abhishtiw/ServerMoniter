package com.est.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.est.dao.ApplicationDao;
import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.util.ErrorCode;

/**
 * This class provides service methods which generates the response code of
 * applications by hitting the application website.And also provides
 * functionality to compare the previous and current status of application and
 * calls the corresponding email sending functionality ,if any changes in any
 * application occured.
 * 
 */
public class MonitorService {

	@Autowired
	private ApplicationDao appDao;

	@Autowired
	private NotifyService notifyService;

	private Application application;

	private List<ApplicationEntity> appList;

	private static final Logger logger = Logger.getLogger(MonitorService.class);

	/**
	 * Getting the new status of application by establishing the new connection
	 * to the coressponding URL provided.
	 * 
	 * @param url
	 * @return <tt>responseCode</tt> ,which specifies the current status of an
	 *         application corresponding to that URL provided.
	 * @throws IOException
	 */
	private int generateResponseCode(String appUrl) {
		logger.info("Method generateResponseCode Execution Starts");
		int responseCode;
		String responseMsg;
		if ((!appUrl.equals(null)) && (!appUrl.equals(" "))) {
			/* to avoid SSL certification */
			appUrl = appUrl.replaceFirst("^https", "http");
			try {
				URL url = new URL(appUrl);// MalformedURLException
				URLConnection urlConnection = url.openConnection();// IOException
				HttpURLConnection connection = (HttpURLConnection) urlConnection;
				connection.setRequestMethod("HEAD");
				/* Getting response code by hitting web application */
				responseCode = connection.getResponseCode();
				responseMsg = connection.getResponseMessage();
				logger.info("Printing Response Message : " + responseMsg);
				return responseCode;
			} catch (MalformedURLException e) {
				logger.warn("Invalid URL");
				return ErrorCode.MALURL_INVALID_URL_EXCEPTION_CODE;
			} catch (IOException e) {
				logger.warn("Unable To Establish Connection");
				return ErrorCode.URL_IOEXCEPTION_CODE;
			}
		} else {
			logger.error("Provided URL Is Null Or Empty");
			return ErrorCode.EMPTY_URL_CODE;
		}
	}

	/**
	 * To compare and update the new status of an application.
	 */
	@Transactional
	public void compareApplicationStatus() {
		logger.info("Method compareApplicationStatus Execution Starts");
		boolean sendMail = false;
		int changedAppCount = 0;

		appList = appDao.getEntityList(Application.class);
		logger.info("Getting List Of Applications From The Database");
		if (appList.size() > 0) {

			Iterator<ApplicationEntity> appIterator = appList.iterator();
			logger.info("Iterating Each And Every Application From The List Of Applications");

			while (appIterator.hasNext()) {
				application = (Application) appIterator.next();

				if (application.isActive() == true) {
					logger.info("Getting New Status Code From The Application");
					int prevStatus = application.getNewStatusCode();
					/*
					 * Calling generateResponseCode(String url) method by
					 * passing the URL
					 */
					logger.info("Getting New Generated Response Code By Passing URL To The Application");
					int currentStatus = generateResponseCode(application.getApplicationURL());
					// Comparing mechanism for status
					logger.info("Comparing The Status Of Application");

					if (prevStatus != currentStatus) {
						logger.info("Inside if Block For Comparing Status");
						changedAppCount++;
						sendMail = true;
						System.err.println("inside if : " + changedAppCount);
						logger.info("Initialize sendEmail As True");
						application.setResponseGeneratedTime(new Date());
						logger.info("Setting Response Generated Time");
						application.setOldStatusCode(prevStatus);
						logger.info("Setting Old Status Of The Application By Passing New Status");
						application.setNewStatusCode(currentStatus);
						logger.info("Setting New Status Of The Application By Passing New Generated Status");
						/* updating the database */
						appDao.updateEntity(application);
						logger.info("Updating Application Entity Completed");
					}
				}
			}
			System.err.println(sendMail);
			System.err.println("after while completed : " + changedAppCount);
			if (sendMail) {
				/* Calling Mail functionality method */
				notifyService.sendMail(changedAppCount);
				logger.info("sendEmail = true, Calling The sendMail() Method From notifyService");
			} else {
				logger.warn("sendEmail = false, Hence No Need Of Sending Email, Continuing Monitoring The Server");
			}
		} else {
			logger.error("Currently No Application Is Running On The Server");
		}
	}

	/**
	 * Checks the network status of ISP based on the IP address
	 * 
	 * @param ipAddr
	 * @return the status code
	 */
	public int checkNetworkStatus(String ipAddr) {
		logger.info("Method checkNetworkStatus Execution Starts");
		try {
			String cmd = "";
			logger.info(
					"Trying To Check The Operating System Name By Using 'if' Block And Then Based On The OS Trying To Ping By Using OS Specfic Command");
			if (System.getProperty("os.name").startsWith("Windows")) {
				// For Windows
				cmd = "ping -n 1 " + ipAddr;
				logger.info("Operation System Is Windows, Hence Command Is 'ping -n + ipAddress' For Pinging");
			} else {
				// For Linux and OSX
				cmd = "ping -c 1 " + ipAddr;
				logger.info("Operation System is linux, Hence Command Is 'ping -c + ipAddress' For Pinging");
			}
			Process myProcess = Runtime.getRuntime().exec(cmd);
			logger.info("Executing The Command Based On OS");
			myProcess.waitFor();
			logger.info("checkNetworkStatus------->>myProcess.exitValue()>>>[" + myProcess.exitValue() + "]");
			if (myProcess.exitValue() == 0) {
				return 200;
			} else {
				logger.info(ipAddr + "is Offline");
				return 404;
			}
		} catch (Exception e) {
			logger.error("Testing Of ip Failed Due To Exception: " + e);
			e.printStackTrace();
			return 404;
		}
	}

	/**
	 * To compare and update the new status of an ISP.
	 */
	public void compareISPstatus() {
		logger.info("Method compareISPstatus Execution Starts");
		Application app = appDao.getISPList(Application.class);
		logger.info("Getting The List Of ISP's From the Database");
		String illStatus = "";
		if (app != null) {
			logger.info("Checking The List Of ISP's For Null");
			int newStatus = app.getNewStatusCode();
			int networkStatus = app.getNewStatusCode();
			networkStatus = checkNetworkStatus(String.valueOf(app.getApplicationURL()));
			System.out.println(networkStatus);
			logger.info("Printing Network Status : " + networkStatus);
			if (networkStatus == 200) {
				logger.info("Checking For The Network Status Inside 'if' Block");
				logger.info("The network status code is " + networkStatus + "ISP is working");
				logger.info("Calling compareApplicationStatus method");
				compareApplicationStatus();
			}
			if (newStatus != networkStatus) {
				logger.info(
						"Inside 'if' Block For Comparing The Status, Means If newStatus Is Not Equal To networkStatus Then Setting oldStatus As newStatus And newStatus As networkStatus To The Application Object");
				app.setResponseGeneratedTime(new Date());
				app.setOldStatusCode(newStatus);
				app.setNewStatusCode(networkStatus);
				appDao.updateEntity(app);
				logger.info("Updating The Application Entity");
				if (networkStatus == 200) {
					illStatus = "UP";
				} else {
					illStatus = "DOWN";
				}
				/* Calling Mail functionality Method */
				notifyService.sendISPErrorMail(illStatus);
				logger.info("Calling sendISPErrorMail Method On notifyService Object");
			} else {
				logger.info("Continue Monitoring The ILL");
			}
		} else {
			logger.info("Currently No IIL Info Is Present In The Database !!");
		}
	}
}
