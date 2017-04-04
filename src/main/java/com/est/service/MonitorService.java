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
import com.est.entity.InternetLeaseLine;
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
				logger.info("Response Code is : " + responseCode + " And corresponding Response Message is : "
						+ responseMsg);
				return responseCode;
			} catch (MalformedURLException e) {
				logger.error("Invalid URL");
				return ErrorCode.MALURL_INVALID_URL_EXCEPTION_CODE;
			} catch (IOException e) {
				logger.error("Unable To Establish Connection");
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
	public int compareApplicationStatus() {
		logger.info("Method compareApplicationStatus Execution Starts");
		int changedAppCount = 0;
		appList = appDao.getEntityList(Application.class);
		logger.info("Getting List Of Applications From The Database");
		if (appList.size() > 0) {
			Iterator<ApplicationEntity> appIterator = appList.iterator();
			while (appIterator.hasNext()) {
				application = (Application) appIterator.next();
				if (application.isActive() == true) {
					int prevStatus = application.getNewStatusCode();
					/*
					 * Calling generateResponseCode(String url) method by
					 * passing the URL
					 */
					int currentStatus = generateResponseCode(application.getApplicationURL());
					// Comparing mechanism for status
					logger.info("Comparing The Status Of Application");
					if (prevStatus != currentStatus) {
						changedAppCount++;
						application.setResponseGeneratedTime(new Date());
						application.setOldStatusCode(prevStatus);
						application.setNewStatusCode(currentStatus);
						/* updating the database */
						appDao.updateEntity(application);
						logger.info("Updating Application Entity Completed");
					}
				}
			}
		} else {
			logger.error("Currently No Application Is Running On The Server");
		}
		return changedAppCount;
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
			if (myProcess.exitValue() == 0) {
				return 200;
			} else {
				logger.info(ipAddr + " is Offline");
				return 404;
			}
		} catch (Exception e) {
			logger.error("Testing Of ip Failed Due To Exception: " + e);
			e.printStackTrace();
			return 404;
		}
	}

	/**
	 * 
	 */
	public void compareISPstatus() {
		int prevStatus;
		int currStatus;
		boolean illMail = false;
		boolean appMail = false;
		boolean appChangeMail = false;
		int appChangeCount = 0;
		List<ApplicationEntity> illList = appDao.getEntityList(InternetLeaseLine.class);
		if (illList.size() > 0) {
			Iterator<ApplicationEntity> listIterator = illList.iterator();
			while (listIterator.hasNext()) {
				InternetLeaseLine ill = (InternetLeaseLine) listIterator.next();

				prevStatus = ill.getCurrentStatus();
				currStatus = checkNetworkStatus(ill.getInternalIpAddress());
				if (ill.isPrimaryIll()) {
					if (currStatus != prevStatus) {
						if (currStatus == 404) {
							// appChangeCount = compareApplicationStatus();
							illMail = true;
							// appMail = true;
						}
						if (currStatus == 200) {
							appMail = true;
							appChangeCount = compareApplicationStatus();
						}
					}
					if (currStatus == 200) {
						appChangeCount = compareApplicationStatus();
						if (appChangeCount > 0) {
							appChangeMail = true;
						}
					}
				}
				ill.setPreviousStatus(prevStatus);
				ill.setCurrentStatus(currStatus);
				appDao.updateEntity(ill);
			}
			if (illMail) {
				// mail only ill table
				notifyService.sendMail(appChangeCount);
			} else if (appMail == true || appChangeMail == true) {
				// mail both
				notifyService.sendMail(appChangeCount);
			} else {
				logger.info("Continue Monitoring The ILL");
			}
		} else {
			logger.error("Currently No IIL Info Is Present In The Database !!");
		}

	}
}