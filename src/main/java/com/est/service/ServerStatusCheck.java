package com.est.service;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

public class ServerStatusCheck {
	private static final String SERVER_ADDRESS = "10.10.10.77";
	private static final int TCP_SERVER_PORT = 8081;
	private static boolean connected = false;
	static Socket socket;
    @Autowired
    NotifyService notifyService;
	/**
	 * check host availability currently running or down
	 * @return
	 */
	public boolean hostAvailabilityCheck() {

	
		boolean available = true;
		try {
			if ((socket = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT)) != null) {
				available = true;
				System.out.println("---------------------------serverstatus is running------------------------");
			}
		} catch (UnknownHostException e) { // unknown host
			available = false;
			notifyService.sendPrimaryServerUnreachableMessage();
			System.out.println("---------------------------serverstatus is fail------------------------");

			socket = null;
		} catch (IOException e) { // io exception, service probably not running
			available = false;
			notifyService.sendPrimaryServerUnreachableMessage();
			System.out.println("---------------------------serverstatus is fail------------------------");
			socket = null;
		} catch (NullPointerException e) {
			notifyService.sendPrimaryServerUnreachableMessage();
			System.out.println("---------------------------serverstatus is fail------------------------");
			available = false;

			socket = null;
		}

		return available;
	}
}
