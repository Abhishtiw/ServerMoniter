package com.est.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Autowired
	ApplicationService applicationService;

	public void setHrService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";

	}

	@RequestMapping(value = "validateLogin", method = RequestMethod.POST)
	public String loginValidate(@RequestParam("userName") String name,
			@RequestParam("password") String password) {

		System.out.println(name);
		System.out.println(password);
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
			return "dashboard";
		} else {
			return "login";
		}
	}
}
