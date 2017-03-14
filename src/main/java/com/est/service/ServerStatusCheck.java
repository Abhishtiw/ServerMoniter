package com.est.service;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.est.controller.LoginInterceptor;

public class ServerStatusCheck {
	private static final Logger logger = Logger.getLogger(LoginInterceptor.class);
	private static final String SERVER_ADDRESS = "10.10.10.77";
	private static final int TCP_SERVER_PORT = 8081;
	private static boolean connected = false;
	private static Socket socket;
	@Autowired
	private NotifyService notifyService;

	/**
	 * check host availability currently running or down
	 * 
	 * @return
	 */
	public boolean hostAvailabilityCheck() {
		boolean available = true;
		try {
			if ((socket = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT)) != null) {
				available = true;
				logger.info("ServerStatus Is Running");
			}
		} catch (UnknownHostException e) { // unknown host
			available = false;
			notifyService.sendPrimaryServerUnreachableMessage();
			logger.info("ServerStatus Is Fail");
			socket = null;
		} catch (IOException e) { // io exception, service probably not running
			available = false;
			notifyService.sendPrimaryServerUnreachableMessage();
			logger.info("ServerStatus Is Fail");
			socket = null;
		} catch (NullPointerException e) {
			notifyService.sendPrimaryServerUnreachableMessage();
			logger.info("ServerStatus Is Fail");
			available = false;
			socket = null;
		}
		return available;
	}
}
