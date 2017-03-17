package com.est.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.est.dto.ApplicationAndStatusDto;
import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.ApplicationStatus;
import com.est.entity.ApplicationStatusReport;
import com.est.entity.Email;
import com.est.entity.User;
import com.est.service.ApplicationService;
import com.est.service.MonitorService;
import com.est.service.NotifyService;
import com.est.service.UserXlsx;
import com.est.util.ErrorCode;
import com.est.util.ServerMonitorException;

/**
 * Handles requests for the application home page.
 *
 */
@Controller
@EnableScheduling
public class ApplicationController {
	private boolean result;
	private boolean userResult;
	private boolean mailResult;
	private static final Logger logger = Logger.getLogger(ApplicationController.class);

	@Autowired
	private ApplicationService appService;

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private Email email;

	@Autowired
	private UserXlsx excel;

	@Autowired
	private NotifyService notifyService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * Scheduled task to monitor ISP and Applications for every 2 minutes
	 */
	@Scheduled(fixedDelay = 2000)
	public void scheduledTaskForApps() {
		logger.info("Start Executing scheduledTaskForApps Method");
		monitorService.compareISPstatus();
	}

	/**
	 * To redirect to corresponding view based on the URL provided from the
	 * dashboard.
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "{url}")
	public String add(@PathVariable String url) {
		logger.info("Start Executing add Method");
		if (url.equals("addApplication")) {
			return "add_application";
		} else if (url.equals("addUser")) {
			return "register_user";
		}
		logger.info("Completed Execution of add application Method");
		return "errorPage";
	}

	/**
	 * To create an application
	 * 
	 * @param application
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveApplication", method = RequestMethod.POST)
	public String saveApp(@ModelAttribute("application") Application application, ModelMap model) {
		logger.info("Starts Executing saveApplication Method");
		result = appService.addEntity(application);
		if (result) {
			logger.info("Completed Execution of saveApplication  Method");
			return "redirect:displayApplication";
		}
		logger.error("saveApplication Method Failed");
		throw new ServerMonitorException(ErrorCode.ADD_APPLICATION_FAIL);
	}

	/**
	 * To create an user
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "saveUser", "registerMe" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("mail") String mail,
			HttpServletRequest request) {
		logger.info("Starts Executing saveUser Method");
		userResult = appService.addEntity(user);// Adding User record
		email.setEmailId(user.getEmailId());
		if (mail.equals("to")) {
			email.setEmailTo(1);
		} else {
			email.setEmailCc(1);
		}
		mailResult = appService.addEntity(email);
		if ((userResult == true) && (mailResult == true)) {
			logger.info("Execution Completed Of saveapplication Method");
			if (request.getRequestURI().equals("/servermonitor/saveUser")) {
				logger.info("Uri is saveUser,Hence redirect to displayUser page");
				return "redirect:displayUser";
			} else {
				logger.info("Uri is registerMe,Hence redirect to login page");
				return "success";
			}
		} else if ((userResult == false) || (mailResult == false)) {
			logger.error("Save User Or Save Email Info Fail");
			throw new ServerMonitorException(ErrorCode.ADD_USER_WITH_EMAIL_FAIL);
		} else {
			logger.error("saveUser Method Fail");
			throw new ServerMonitorException(ErrorCode.ADD_USER_FAIL);
		}
	}

	/**
	 * To create new Application status code
	 * 
	 * @param appStatus
	 * @return
	 */
	@RequestMapping(value = "saveAppStatus", method = RequestMethod.POST)
	public String saveStatus(@ModelAttribute("appStatus") ApplicationStatus appStatus) {
		logger.info("Start Executing saveStatus Method");
		result = appService.addEntity(appStatus);
		if (result) {
			logger.info("Execution Completed Of saveStatus Method");
			return "saveapp";
		} else {
			logger.error("saveStatus Method Fail");
			throw new ServerMonitorException(ErrorCode.ADD_STATUS_FAIL);
		}
	}

	/**
	 * To create new Application status report
	 * 
	 * @param appStatusReport
	 * @return
	 */
	@RequestMapping(value = "saveAppStatusReport", method = RequestMethod.POST)
	public String saveStatusReport(@ModelAttribute("appStatusReport") ApplicationStatusReport appStatusReport) {
		logger.info("Start Executing saveStatusReport Method");
		result = appService.addEntity(appStatusReport);
		if (result) {
			logger.info("Execution Completed Of saveStatusReport Method");
			return "saveapp";
		} else {
			logger.error("saveStatusReport Method Fail");
			throw new ServerMonitorException(ErrorCode.ADD_REPORT_FAIL);
		}
	}

	/**
	 * To add new email ID
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "saveMailInfo", method = RequestMethod.POST)
	public String saveEmailInfo(@ModelAttribute("email") Email email) {
		logger.info("Start Executing saveEmailInfo Method");
		result = appService.addEntity(email);
		if (result) {
			logger.info("Execution Completed Of saveEmailInfo Method");
			return "saveapp";
		} else {
			logger.error("saveStatusReport Method Fail");
			throw new ServerMonitorException(ErrorCode.ADD_EMAIL_FAIL);
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
		logger.info("Start Executing editApp Method");
		Application application = (Application) appService.getEntityByID(Application.class, appId);
		if (application == null) {
			logger.warn("editApp Method Fail");
			throw new ServerMonitorException(ErrorCode.UPDATE_ENTITY_FAIL);
		}
		model.addAttribute("application", application);
		logger.info("Execution Completed Of editApp Method");
		return "edit_application";
	}

	/**
	 * To update an Application
	 * 
	 * @param application
	 * @return
	 */
	@RequestMapping(value = "update_application", method = RequestMethod.POST)
	public String updateApp(@ModelAttribute("application") Application application) {
		logger.info("Start Executing updateApp Method");
		result = appService.updateEntity(application);
		if (result) {
			logger.info("Execution Completed Of updateApp Method");
			return "redirect:displayApplication";
		} else {
			logger.error("updateApp Method Fail");
			throw new ServerMonitorException(ErrorCode.UPDATE_ENTITY_FAIL);
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
		logger.info("Start Executing editUser Method");
		User user = (User) appService.getEntityByID(User.class, userId);
		model.addAttribute("user", user);
		logger.info("Execution Completed Of editUser Method");
		return "edit_user";
	}

	/**
	 * To update the User
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, @RequestParam("mailId") String mailId) {
		logger.info("Start Executing updateUser Method.And Calling updateEntity method to update User record");
		userResult = appService.updateEntity(user);
		logger.info("Calling getEntityBasedOnEmailId method to get Email entity");
		email = (Email) appService.getEntityBasedOnEmailId(Email.class, mailId);
		email.setEmailId(user.getEmailId());
		logger.info("Calling updateEntity method to update Email record");
		mailResult = appService.updateEntity(email);
		if ((userResult == true) && (mailResult == true)) {
			logger.info("Execution Completed of updateUser Method by updating both User and Email record");
			return "redirect:displayUser";
		} else if ((userResult == false) || (mailResult == false)) {
			logger.error("Either Update User Or Update Email Info Failed");
			throw new ServerMonitorException(ErrorCode.UPDATE_ENTITY_FAIL);
		} else {
			logger.warn("updateUser Method Fail");
			throw new ServerMonitorException(ErrorCode.UPDATE_ENTITY_FAIL);
		}
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
		logger.info("Start Executing deleteApp Method");
		result = appService.deleteEntity(Application.class, appId);
		if (result) {
			logger.info("Execution Completed Of deleteApp Method");
			return "redirect:displayApplication";
		} else {
			logger.warn("deleteApp Method Fail");
			throw new ServerMonitorException(ErrorCode.DELETE_APPLICATION_FAIL);
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
	public String deleteUser(@RequestParam int userId, @RequestParam String mailId) {
		logger.info("Start Executing deleteUser Method");
		userResult = appService.deleteEntity(User.class, userId);
		mailResult = appService.deleteEmailRecord(mailId);
		if ((userResult == true) && (mailResult == true)) {
			logger.info("Execution Completed Of deleteUser Method");
			return "redirect:displayUser";
		} else if ((userResult == false) || (mailResult == false)) {
			logger.warn("Delete User Or Delete Email Info Fail");
			throw new ServerMonitorException(ErrorCode.DELETE_ENTITY_FAIL);
		} else {
			logger.warn("deleteUser Method Fail");
			throw new ServerMonitorException(ErrorCode.DELETE_USER_FAIL);
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
		logger.info("Start Executing displayApp Method");
		Application ill = appService.getISP();
		modelMap.addAttribute("ill", ill);
		List<ApplicationAndStatusDto> application = appService.getListApplicationAndStatus();
		if (application == null) {
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		logger.info("Execution Completed of displayApp Method");
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
		logger.info("Start Executing displayUser Method");
		List<ApplicationEntity> user = appService.getEntityList(User.class);
		if (user == null) {
			logger.warn("displayUser Method Fail");
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		logger.info("Execution Completed of displayUser Method");
		modelMap.addAttribute("user", user);
		return "display_user";
	}

	/**
	 * To display status of all applications
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "applicationstatus")
	public String displayApplicationStatus(ModelMap modelMap) {
		logger.info("Start Executing displayApplicationStatus Method");
		List<ApplicationAndStatusDto> activeAppList = new ArrayList<ApplicationAndStatusDto>();
		ApplicationAndStatusDto application;
		logger.info("Getting ISP application list");
		Application ill = appService.getISP();
		modelMap.addAttribute("ill", ill);
		logger.info("Getting Application List");
		// List<ApplicationEntity> applEntityList =
		// appService.getEntityList(Application.class);
		List<ApplicationAndStatusDto> applEntityList = appService.getListApplicationAndStatus();
		if (applEntityList == null) {
			logger.warn("displayUser Method Fail");
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		Iterator<ApplicationAndStatusDto> appListIterator = applEntityList.iterator();
		while (appListIterator.hasNext()) {
			application = appListIterator.next();
			if (application.isActive()) {
				activeAppList.add(application);
			}
		}
		modelMap.addAttribute("applicationStatus", activeAppList);
		logger.info("Execution Completed of displayApplicationStatus Method");
		return "status_Report";
	}

	/**
	 * Redirecting to lost_password page
	 * 
	 * @return
	 */
	@RequestMapping(value = "lostPassword", method = RequestMethod.GET)
	public String lostPassword() {
		logger.info("Inside Lost Password Method");
		return "lost_password";
	}

	/**
	 * This method is used to get the password based on the given email Id.
	 * 
	 * @param emailId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "get_password", method = RequestMethod.POST)
	public String getPassword(@RequestParam("emailId") String emailId) {
		logger.info("Start Executing getPassword Method");
		User user;
		user = (User) appService.getEntityBasedOnEmailId(User.class, emailId);
		if (user != null) {
			String password = user.getPassword();
			logger.info("Sending Password to Respective EmailId");
			notifyService.sendLostPassword(emailId, password);
		} else {
			logger.info("Incorrect Password");
			return "redirect:/lostPassword";
		}
		logger.info("Execution Completed of getPassword  Method");
		return "emailSuccess";
	}

	/**
	 * Logout method
	 * 
	 * @return
	 */
	@RequestMapping(value = "signout", method = RequestMethod.GET)
	public String signOut(HttpServletRequest request, HttpSession session) {
		logger.info("Inside Executing signOut Method");
		session.invalidate();// Invalidating the session
		return "redirect:/";
	}

	/**
	 * Returning to uploadExcel jsp page
	 * 
	 * @return
	 */
	@RequestMapping(value = "addApplicationsFromExcel", method = RequestMethod.GET)
	public String addFromExcel() {
		logger.info("Inside Executing addFromExcel Method");
		return "uploadExcel";
	}

	/**
	 * This method adds the applications from excel sheet to the application
	 * list
	 * 
	 * @param filepath
	 * @param sheet
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String saveAppFromExcel(@RequestParam("filepath") String filepath, @RequestParam("sheet") String sheet,
			ModelMap model) {
		logger.info("Starts Executing saveAppFromExcel Method");
		List<Application> applist = excel.excelFile(filepath, sheet);
		Iterator<Application> iterator = applist.iterator();
		while (iterator.hasNext()) {
			Application application = iterator.next();
			result = appService.addEntity(application);
		}
		logger.info("Execution completed of saveAppFromExcel  Method");
		return "redirect:displayApplication";
	}

	@RequestMapping("applicationhealthstatus")
	public String appHealthStatus(ModelMap modelMap) {
		List<ApplicationEntity> appHealthList = appService.getEntityList(ApplicationStatusReport.class);
		if (appHealthList == null) {
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		logger.info("Execution Completed of displayApp Method");
		modelMap.addAttribute("applicationHealthStatusReport", appHealthList);
		return "application_Health_Status_Report";
	}

	@RequestMapping(value = "signUp", method = RequestMethod.GET)
	public String signUp() {
		return "signup";
	}

}
