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

public class MonitorService {

	@Autowired
	private ApplicationDao appDao;

	@Autowired
	private NotifyService notifyService;

	private Application application;

	private boolean sendMail;

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
			responseCode = connection.getResponseCode(); //Getting response code by hitting web application
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
		appList = appDao.getEntityList(Application.class);
		Iterator<ApplicationEntity> appIterator = appList.iterator();
		if (appList.size() > 0) {
			while (appIterator.hasNext()) {
				application = (Application) appIterator.next();
				int oldStatus = application.getOldStatusCode();
				int newStatus = generateResponseCode(application.getApplicationURL());
				if (oldStatus != newStatus) {
					sendMail = true;
					application.setResponseGeneratedTime(new Date());
					application.setOldStatusCode(application.getNewStatusCode());
					application.setNewStatusCode(newStatus);
					appDao.updateEntity(application);
				}
			}
			if (sendMail) {
				appList = appDao.getEntityList(Application.class);
				notifyService.sendMail();
			}
		} else {
			System.out.println("Currently no application is Running on server !!");
		}
	}

}
