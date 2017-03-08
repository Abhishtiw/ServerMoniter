package com.est.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.est.entity.User;
import com.est.service.ApplicationService;

@Controller
public class LoginController {

	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	ApplicationService applicationService;

	public void setHrService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("---------------start executing login method-------------------- ");
		
		return "login";

	}

	@RequestMapping(value = "validateLogin", method = RequestMethod.POST)
	public String loginValidate(@RequestParam("userName") String name,
			@RequestParam("password") String password) {
		

		logger.info("---------------start executing loginValidate  method-------------------- ");
		User user=null;
		user = applicationService.getNamePassword(name,password);

		System.out.println("inside login method");

		/*if (session != null && session.getAttribute("name") != null) {
			return "index";
		} else if (employee != null && name.equals(employee.getFirstName())
				&& password.equals(employee.getPassword())) {
			session.setAttribute("name", name);
			session.setAttribute("id", employee.getId());
			role = employee.getRole();
			if (role == 1) {
				session.setAttribute("type", "employee");
				session.setAttribute("role", 1);
			} else {
				session.setAttribute("type", "hr");
				session.setAttribute("role", 2);
			}*/
		
		if(user!=null){
			logger.info("---------------completed executing loginValidate  method-------------------- ");
			return "redirect:applicationstatus";
		} else {
			logger.warn("-------------------invalid identity  ------------------------ ");
			return "login";
		}
	}
}
