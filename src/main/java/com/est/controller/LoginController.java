package com.est.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.est.entity.User;
import com.est.service.ApplicationService;

@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private ApplicationService applicationService;

	/**
	 * Returns to login page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		logger.info("Method login Execution Starts");
		return "login";
	}

	/**
	 * This method validates for the authorized user,if yes ,then add that user
	 * to the current session Else redirects again to login page
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "validateLogin", method = RequestMethod.POST)
	public String loginValidate(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {
		logger.info("Method loginValidate Execution Starts");
		User user = applicationService.getNamePassword(userName, password);
		logger.info("Getting username And password From The User");
		if (user != null) {
			logger.info("Preparing The Session Object With User Data");
			request.getSession().setAttribute("user", user);
			logger.info("Completed Executing loginValidate  Method");
			return "redirect:applicationstatus";
		} else {
			logger.error("Invalid User Credentials");
			return "login";
		}
	}
}
