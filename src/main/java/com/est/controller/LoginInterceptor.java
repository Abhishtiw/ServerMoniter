package com.est.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.est.entity.User;

/**
 * This LoginInterceptor will perform these tasks:- 1) Check user exist in
 * session or not. 2) Runs before every action to check .
 * 
 * If someone try to access direct URL of welcome page and if he is not present
 * in session, then it will redirect towards login page. If user already in
 * session then, call the action called by user.
 * 
 * @author nkumar
 *
 */

/*
 * For any class to act like an Interceptor, it has to extend the class
 * HandlerIntercepterAdapter provided by SpringMvc
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

	/*
	 * we need to override preHandle() method.
	 * 
	 * preHandle means, it handles each and every request before the request
	 * hits the controller.
	 * 
	 * Intercept the execution of a handler. Called after HandlerMapping
	 * determined an appropriate handler object, but before HandlerAdapter
	 * invokes the handler. DispatcherServlet processes a handler in an
	 * execution chain, consisting of any number of interceptors, with the
	 * handler itself at the end. With this method, each interceptor can decide
	 * to abort the execution chain, typically sending a HTTP error or writing a
	 * custom response.
	 * 
	 * Parameters: request - current HTTP request response - current HTTP
	 * response handler - chosen handler to execute, for type and/or instance
	 * evaluation Returns: true if the execution chain should proceed with the
	 * next interceptor or the handler itself. Else, DispatcherServlet assumes
	 * that this interceptor has already dealt with the response itself.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Inside preHandle()");
		/*
		 * initially, we are taking the uri from the incoming request by calling
		 * getRequestURI() on incoming request object.
		 */
		String uri = request.getRequestURI();
		logger.info("Getting The URI From The Incoming Request And The URI Is : " + uri);
		/*
		 * Now, we are getting a session which is already existed, hence
		 * getSession(true) if there is no existed session, then new session
		 * will be created. Why we are taking session means? when a user login a
		 * session object has to be created, through which we can easily
		 * identify the user. here for intercepting the incoming request, we are
		 * checking the value inside the session which is already
		 * created/existed in LoginController.
		 */
		HttpSession session = request.getSession(true);
		logger.info("Getting The Session From The Incoming Request  : " + session);

		/*
		 * if we just look into the immediate previous statement, we are getting
		 * the already existed session, and we are getting the value inside the
		 * session by calling getAttribute("key") on session Object
		 * 
		 * so, if there is a value inside a session, then we need to typecast
		 * the value to corresponding object of what we want.
		 * 
		 * here we want to get user Object from the session, based on that our
		 * logic moves ahead.
		 * 
		 * Here, the main logic comes into picture, uri ends with '/' i.e it is
		 * login page uri ends with '/validateLogin' i.e it is validating
		 * credentials after user login
		 * 
		 * the logic is if uri doesn't ends with '/' and uri doesn't ends with
		 * '/validateLogin' it means, the user doesn't login, hence there is no
		 * chance of session containing user credentials. in this case, we have
		 * to restrict the user to access other pages of the application by
		 * redirecting the user request to login page.
		 * 
		 * if user login, then the credentials are validated and the user
		 * credentials are stored in a session object, and the user can access
		 * the other pages of the application.
		 * 
		 * now we want to check whether already existed session contains any
		 * value or not,
		 * 
		 * if the session value is null, it clearly tells that user is not
		 * logged in.
		 * 
		 * hence we will redirect the response to login page.
		 * 
		 * return false means we will not let the user to proceed forward if he
		 * is not logged in
		 * 
		 * 
		 * 
		 * coming to the else part, if uri doesn't ends with '/' and uri doesn't
		 * ends with '/validateLogin' then also we check the value inside the
		 * session, if session value is not null, means session contains value,
		 * means user already logged in. so we have to let him to access the
		 * other pages of the application.hence we return true.
		 * 
		 * 
		 */
		if (!uri.endsWith("/") && !uri.endsWith("/validateLogin")) {
			logger.info("Executing Login Interceptor Logic");
			User userData = (User) request.getSession().getAttribute("user");
			logger.info("Getting The Value From The Session Which Is Already Existed");
			if (userData == null) {
				logger.warn("Null Value Inside Session");
				response.sendRedirect("/servermonitor/");
				logger.warn("Redirected To Login Page");
				return false;
			} else {
				logger.info("Session Contains Value");
				logger.info("Already Logged In!!!!!, No Need To Login Again");
				return true;
			}
		}
		logger.info("Intercepting Finished");
		return true;
	}
}
