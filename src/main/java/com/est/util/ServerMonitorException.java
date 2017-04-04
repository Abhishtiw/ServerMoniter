package com.est.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * This Class is meant for creating Custom Exceptions .
 */
public class ServerMonitorException extends RuntimeException {
	private static final Logger logger = Logger.getLogger(ServerMonitorException.class);

	/**
	 * properties file reference to load properties from external file
	 */
	private Properties props;

	/**
	 * parameterized constructor which takes error code as parameter Displays
	 * the error messages corresponding to the errorcode
	 * 
	 * @param code
	 */
	public ServerMonitorException(String code) {
		loadProperties();
		System.out.println(props.get(code));
	}

	/**
	 * constructor which takes error code and a throwable object logs the
	 * throwable object
	 * 
	 * @param code
	 * @param e
	 */
	public ServerMonitorException(String code, Throwable e) {
		loadProperties();
		System.out.println(props.get(code));
		logger.warn(e);
	}

	/**
	 * Method that loads properties from properties file
	 */
	private void loadProperties() {
		props = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("errorcode.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
