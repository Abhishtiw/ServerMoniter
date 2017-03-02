package com.est.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerMonitorException extends RuntimeException {

	/**
	 * properties file reference to load properties from external file
	 */
	private Properties props;

	/**
	 * parameterized constructor which takes error code as parameter
	 * 
	 * Displays the error messages corresponding to the errorcode
	 * 
	 * @param code
	 */
	public ServerMonitorException(String code) {
		loadProperties();
		System.out.println(props.get(code));
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
