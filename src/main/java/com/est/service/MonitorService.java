package com.est.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
 * @author rgopalraj
 *
 */
public class MonitorService {

	@Autowired
	private ApplicationDao appDao;

	@Autowired
	private NotifyService notifyService;

	private Application application;

	private List<ApplicationEntity> appList;

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
		int responseCode;
		String responseMsg;
		if ((!appUrl.equals(null)) && (!appUrl.equals(" "))) {
			/* to avoid SSL certification*/
			appUrl = appUrl.replaceFirst("^https", "http"); 
			try {
				URL url = new URL(appUrl);// MalformedURLException
				URLConnection urlConnection = url.openConnection();// IOException
				HttpURLConnection connection = (HttpURLConnection) urlConnection;
				connection.setRequestMethod("HEAD");
				/* Getting response code by hitting web application */
				responseCode = connection.getResponseCode();
				responseMsg = connection.getResponseMessage();
				System.out.println("Response message>>>>>>>>>>>>>>>>>"+responseMsg);
				return responseCode;
			} catch (MalformedURLException e) {
				System.out.println("Invalid URL");
				System.out.println(e);
				return ErrorCode.MALURL_INVALID_URL_EXCEPTION_CODE;
			} catch (IOException e) {
				System.out.println("cannot able to establish URL connection");
				System.out.println(e);
				return ErrorCode.URL_IOEXCEPTION_CODE;
			}
		} else {
			System.out.println("Provided URL is null or empty !!");
			return 0;
		}
	}

	/**
	 * To compare and update the new status of an application.
	 */
	@Transactional
	public void compareApplicationStatus() {
		boolean sendMail;
		appList = appDao.getEntityList(Application.class);
		if (appList.size() > 0) {
			Iterator<ApplicationEntity> appIterator = appList.iterator();
			sendMail = false;
			while (appIterator.hasNext()) {
				application = (Application) appIterator.next();
				int newStatus = application.getNewStatusCode();
				/*
				 * Calling generateResponseCode(String url) method by passing
				 * the URL
				 */
				int newGeneratedStatus = generateResponseCode(application.getApplicationURL());
				//Comparing mechanism for status
				if (newStatus != newGeneratedStatus) { 
					System.out.println("Inside IF block");
					sendMail = true;
					application.setResponseGeneratedTime(new Date());
					application.setOldStatusCode(newStatus);
					application.setNewStatusCode(newGeneratedStatus);
					/* updating the database */
					appDao.updateEntity(application);
					System.out.println("update completed");
				}
			}
			if (sendMail) {
				notifyService.sendMail();// Calling Mail functionality method
			} else {
				System.out.println("Continue Monitoring The Server..");
			}
		} else {
			System.out.println("Currently no application is Running on server !!");
		}
	}
	
	public int checkNetworkStatus(String ipAddr) {
		// logger.info("checkNetworkStatus>>[" + ipAddr + "]");
		try {
			String cmd = "";
			if (System.getProperty("os.name").startsWith("Windows")) {
				// For Windows
				cmd = "ping -n 1 " + ipAddr;
			} else {
				// For Linux and OSX
				cmd = "ping -c 1 " + ipAddr;
			}

			Process myProcess = Runtime.getRuntime().exec(cmd);
			myProcess.waitFor();
			// logger.info("checkNetworkStatus>>myProcess.exitValue()>>>[" +
			// myProcess.exitValue() + "]");
			if (myProcess.exitValue() == 0) {
				return 200;
			} else {
				// logger.info(ipAddr + "is Offline");
				return 404;
			}

		} catch (Exception e) {
			// logger.error("testing of ip failed due to exception: " + e);
			e.printStackTrace();
			return 404;

		}
	}

	public void compareISPstatus() {
		Application app = appDao.getISPList(Application.class);
		if (app != null) {

			int newStatus = app.getNewStatusCode();
			int networkStatus = app.getNewStatusCode();
			networkStatus = checkNetworkStatus(String.valueOf(app.getApplicationURL()));
			System.out.println(networkStatus);

			if (networkStatus == 200) {
				System.out.println("internet service provider is working");
				compareApplicationStatus();
			}

			if (newStatus != networkStatus) {
				app.setResponseGeneratedTime(new Date());
				app.setOldStatusCode(newStatus);
				app.setNewStatusCode(networkStatus);
				appDao.updateEntity(app);
				notifyService.sendISPErrorMail();// Calling Mail functionality
													// method
			} else {
				System.out.println("Continue Monitoring ILL...");
			}
		}else{
			System.out.println("currently no IIL info is present in the database !");
		}

	}

}
