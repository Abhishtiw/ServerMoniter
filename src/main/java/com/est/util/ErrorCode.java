package com.est.util;

public interface ErrorCode {

	String NO_IIL_FOUND = "NO_IIL_FOUND" ;
	
	// database related errors
	String DB_DRIVER_ERROR = "DB_DRIVER_ERROR";
	String DB_CONNECTION_FAIL = "DB_CONNECTION_FAIL";
	String DB_NOT_FOUND = "DB_NOT_FOUND";
	String DB_TABLE_NOT_FOUND = "DB_TABLE_NOT_FOUND";
	String DB_DUPLICATE_USER = "DB_DUPLICATE_USER";
	String DB_INVALID_QUERY = "DB_INVALID_QUERY";
	String DB_RESULTSET_EMPTY = "DB_RESULTSET_EMPTY";
	String DB_TRANSACTION_FAILED = "DB_TRANSACTION_FAILED";

	//login related errors
	String LOGIN_DB_CONNECTION_FAIL = "LOGIN_DB_CONNECTION_FAIL";
	String LOGIN_INVALID_ID = "LOGIN_INVALID_ID";
	String LOGIN_INVALID_NAME = "LOGIN_INVALID_NAME";
	String LOGIN_INVALID_PASSWORD = "LOGIN_INVALID_PASSWORD";
	String LOGIN_SESSION_TIMEOUT = "LOGIN_SESSION_TIMEOUT";
	
	//role based permission  errors
	String ACCESS_DENIED = "ACCESS_DENIED";

	//file IO errors
	String FILE_NOT_FOUND_PROPS = "FILE_NOT_FOUND_PROPS";
	String FILE_NOT_FOUND_JSP = "FILE_NOT_FOUND_JSP";
	String FILEPATH_LOG = "FILEPATH_LOG";
	String FILEPATH_DB_DUMP = "FILEPATH_DB_DUMP";
	
	//logging related errors
	String LOG_CREATION_ERROR = "LOG_CREATION_ERROR";
	String LOG_CLEAR_FAILED = "LOG_CLEAR_FAILED";
	
	//sending email related errors
	String EMAIL_ID_INVALID = "EMAIL_ID_INVALID";
	String EMAIL_SENDING_FAILED = "EMAIL_SENDING_FAILED";

	//configuration errors
	String JAR_NOT_FOUND = "JAR_NOT_FOUND";
	String HIBERNATE_CONFIG_ERROR = "HIBERNATE_CONFIG_ERROR";
	
	//dependency injection errors
	String DEPENDENCY_INJECTION_FAILED = "DEPENDENCY_INJECTION_FAILED";
	
	//URL connection and response code errors
	String URL_CONNECTION_FAILED = "URL_CONNECTION_FAILED";
	String URL_STATUS_CODE_NOT_FOUND = "URL_STATUS_CODE_NOT_FOUND";
	String URL_INVALID = "URL_INVALID";
	
	//adding records errors
	String ADD_APPLICATION_FAIL = "ADD_APPLICATION_FAIL";
	String ADD_USER_FAIL = "ADD_USER_FAIL";
	String ADD_EMAIL_FAIL = "ADD_EMAIL_FAIL";
	String ADD_STATUS_FAIL = "ADD_STATUS_FAIL";
	String ADD_REPORT_FAIL = "ADD_REPORT_FAIL";
	String ADD_PRIORITY_FAIL = "ADD_PRIORITY_FAIL";
	String ADD_USER_WITH_EMAIL_FAIL = " ADD_USER_WITH_EMAIL_FAIL";
	
	//deleting record error
	String DELETE_APPLICATION_FAIL = "DELETE_APPLICATION_FAIL";
	String DELETE_USER_FAIL = "DELETE_USER_FAIL";
	String DELETE_EMAIL_FAIL = "DELETE_EMAIL_FAIL";
	String DELETE_STATUS_FAIL = "DELETE_STATUS_FAIL";
	String DELETE_REPORT_FAIL = "DELETE_REPORT_FAIL";
	String DELETE_PRIORITY_FAIL = "DELETE_PRIORITY_FAIL";
	
	//CRUD operations on entities
	String ADD_ENTITY_FAIL = "ADD_ENTITY_FAIL";
	String DELETE_ENTITY_FAIL = "DELETE_ENTITY_FAIL";
	String UPDATE_ENTITY_FAIL = "UPDATE_ENTITY_FAIL";
	String DISPLAY_ENTITY_ERROR = "DISPLAY_ENTITY_ERROR";
	
	int MALURL_INVALID_URL_EXCEPTION_CODE=999;
	int URL_IOEXCEPTION_CODE=888;
	int EMPTY_URL_CODE=000;
	
}
