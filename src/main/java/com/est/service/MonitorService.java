package com.est.service;

import java.io.IOException;
import java.net.HttpURLConnection;
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
	 */
	public int generateResponseCode(String url) {
		int responseCode;
		url = url.replaceFirst("^https", "http"); // to avoid SSL certification
		try {
			URL ulr = new URL(url);
			URLConnection urlConnection = ulr.openConnection();
			HttpURLConnection connection = (HttpURLConnection) urlConnection;
			connection.setRequestMethod("HEAD");
			/* Getting response code by hitting web application */
			responseCode = connection.getResponseCode();
			return responseCode;
		} catch (IOException exception) {
			System.out.println("cannot establish connection");
			System.out.println(exception);
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
				/*
				 * Calling generateResponseCode(String url) method by passing
				 * the URL
				 */
				int newGeneratedStatus = generateResponseCode(application.getApplicationURL());
				int newStatus = application.getNewStatusCode();
				if (newStatus != newGeneratedStatus) {
					System.out.println("Inside IF block");
					sendMail = true;
					application.setResponseGeneratedTime(new Date());
					application.setOldStatusCode(newStatus);
					application.setNewStatusCode(newGeneratedStatus);
					appDao.updateEntity(application);
					System.out.println("update completed");
				}
			}
			if (sendMail) {
				appList = appDao.getEntityList(Application.class);
				notifyService.sendMail();// Calling Mail functionality method
			} else {
				System.out.println("Continue Monitoring The Server..");
			}
		} else {
			System.out.println("Currently no application is Running on server !!");
		}
	}

}
