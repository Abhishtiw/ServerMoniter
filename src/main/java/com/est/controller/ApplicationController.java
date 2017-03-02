package com.est.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.ApplicationStatus;
import com.est.entity.ApplicationStatusReport;
import com.est.entity.Email;
import com.est.entity.User;
import com.est.service.ApplicationService;
import com.est.util.ErrorCode;
import com.est.util.ServerMonitorException;

/**
 * Handles requests for the application home page.
 * 
 * @author rgopalraj
 *
 */
@Controller
public class ApplicationController {

	private boolean result;
	private static final Logger logger = Logger.getLogger(ApplicationController.class);
	@Autowired
	private ApplicationService appService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("------------------------start executing home method---------------- ");
		model.addAttribute("message", "Welcome Home !");
		return "home";
	}*/

	/**
	 * To redirect to corresponding view based on the URL
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "{url}")
	public String add(@PathVariable String url) {
		logger.info("------------------------start executing add method---------------- ");
		if (url.equals("addApplication")) {
			return "add_application";
		} else if (url.equals("addUser")) {
			return "register_user";
		} else if (url.equals("addPriority")) {
			return "add_priority";
		} else if (url.equals("addStatusReport")) {
			return "add_status_report";
		} else if (url.equals("addEmail")) {
			return "add_email";
		}
		return "errorPage";
		
	}

	/**
	 * To create an application
	 * 
	 * @param application
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveapplication", method = RequestMethod.POST)
	public String saveApp(@ModelAttribute("application") Application application, ModelMap model) {
		result = appService.addEntity(application);
		if (result) {
			return "redirect:displayApplication";
		}
		return "errorPage";
	}

	/**
	 * To create an user
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, ModelMap model) {
		result = appService.addEntity(user);
		if (result) {
			return "saveapp";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To create new Application status code
	 * 
	 * @param appStatus
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAppStatus", method = RequestMethod.POST)
	public String saveStatus(@ModelAttribute("appStatus") ApplicationStatus appStatus, ModelMap model) {
		result = appService.addEntity(appStatus);
		if (result) {
			return "saveapp";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To create new Application status report
	 * 
	 * @param appStatusReport
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAppStatusReport", method = RequestMethod.POST)
	public String saveStatusReport(@ModelAttribute("appStatusReport") ApplicationStatusReport appStatusReport,
			ModelMap model) {
		result = appService.addEntity(appStatusReport);
		if (result) {
			return "saveapp";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To add new email ID
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveMailInfo", method = RequestMethod.POST)
	public String saveEmailInfo(@ModelAttribute("email") Email email, ModelMap model) {
		result = appService.addEntity(email);
		if (result) {
			return "saveapp";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To edit an Application
	 * 
	 * @param appId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editApp")
	public String editApp(@RequestParam int appId, ModelMap model) {
		Application application = (Application) appService.getEntityByID(Application.class, appId);
		model.addAttribute("application", application);
		return "edit_application";
	}

	/**
	 * To update an Application
	 * 
	 * @param application
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateApp", method = RequestMethod.POST)
	public String updateApp(@ModelAttribute("application") Application application, ModelMap model) {
		result = appService.updateEntity(application);
		if (result) {
			return "redirect:displayApplication";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To edit an user
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editUser")
	public String editUser(@RequestParam int userId, ModelMap model) {
		User user = (User) appService.getEntityByID(User.class, userId);
		model.addAttribute("user", user);
		return "editUSer";
	}

	/**
	 * To delete an Application
	 * 
	 * @param appId
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteApp")
	public String deleteApp(@RequestParam int appId, ModelMap model) {
		result = appService.deleteEntity(Application.class, appId);
		if (result) {
			return "redirect:displayApplication";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To delete an USer
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteUser")
	public String deleteUser(@RequestParam int userId, ModelMap model) {
		result = appService.deleteEntity(User.class, userId);
		if (result) {
			return "saveapp";
		} else {
			return "errorPage";
		}
	}

	/**
	 * To display all Applications
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "displayApplication")
	public String displayApp(ModelMap modelMap) {
		List<ApplicationEntity> application = appService.getEntityList(Application.class);
		if(application==null){
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		modelMap.addAttribute("applicationList", application);
		return "display_app";
	}

	/**
	 * To display all users
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "displayUser")
	public String displayUser(ModelMap modelMap) {
		List<ApplicationEntity> user = appService.getEntityList(User.class);
		modelMap.addAttribute("user", user);
		return "display_user";
	}
}
