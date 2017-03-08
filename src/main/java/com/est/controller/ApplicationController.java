package com.est.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.ApplicationStatus;
import com.est.entity.ApplicationStatusReport;
import com.est.entity.Email;
import com.est.entity.User;
import com.est.service.ApplicationService;
import com.est.service.MonitorService;
import com.est.service.NotifyService;
import com.est.util.ErrorCode;
import com.est.util.ServerMonitorException;

/**
 * Handles requests for the application home page.
 * 
 * @author rgopalraj
 *
 */
@Controller
@EnableScheduling
public class ApplicationController {

	private boolean result;
	private static final Logger logger = Logger.getLogger(ApplicationController.class);
	@Autowired
	private ApplicationService appService;

	@Autowired
	private MonitorService monitorService;

	@Autowired
	Email email;
	
	@Autowired
	private NotifyService notifyService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@Scheduled(fixedDelay=2000)
	public void doTask(){
	 System.out.println("Inside Scheduler");
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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>URL = " + url);
		logger.info("------------------------start executing add  method---------------- ");
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
		logger.info("------------------------execution completde of add application method---------------- ");
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
		logger.info("------------------------start executing saveapplication method---------------- ");
		
		result = appService.addEntity(application);
		if (result) {
			logger.info("------------------------execution completde of saveapplication  method---------------- ");
			return "redirect:displayApplication";
		}

		// return "errorPage";
		logger.warn("-------------------saveapplication method fail------------------------ ");
		throw new ServerMonitorException(ErrorCode.ADD_APPLICATION_FAIL);
	}

	/**
	 * To create an user
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("mail") String mail) {
		logger.info("------------------------start executing saveuser method---------------- ");
		boolean userResult = appService.addEntity(user);// Adding User record

		email.setEmailId(user.getEmailId());
		if (mail.equals("to")) {
			email.setEmailTo(1);
		} else {
			email.setEmailCc(1);
		}
		boolean mailResult = appService.addEntity(email);
		if ((userResult == true) && (mailResult == true)) {
			logger.info("------------------------execution completed of saveapplication  method---------------- ");
			return "redirect:displayUser";

		} else if ((userResult == false) || (mailResult == false)) {
			logger.warn("-------------------save user or save email info fail-----------");
			throw new ServerMonitorException(ErrorCode.ADD_USER_WITH_EMAIL_FAIL);
		} else {
			logger.warn("-------------------saveuser method fail------------------------ ");
			// return "errorPage";
			throw new ServerMonitorException(ErrorCode.ADD_USER_FAIL);
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
		logger.info("------------------------start executing saveStatus method---------------- ");
		result = appService.addEntity(appStatus);
		if (result) {
			logger.info("------------------------execution completde of saveStatus  method---------------- ");
			return "saveapp";
		} else {
			// return "errorPage";
			logger.warn("-------------------saveStatus method fail------------------------ ");
			throw new ServerMonitorException(ErrorCode.ADD_STATUS_FAIL);
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
		logger.info("------------------------start executing saveStatusReport method---------------- ");
		result = appService.addEntity(appStatusReport);
		if (result) {
			logger.info("------------------------execution completde of saveStatusReport  method---------------- ");
			return "saveapp";
		} else {
			// return "errorPage";
			logger.warn("-------------------saveStatusReport method fail------------------------ ");
			throw new ServerMonitorException(ErrorCode.ADD_REPORT_FAIL);
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
		logger.info("------------------------start executing saveEmailInfo method---------------- ");
		result = appService.addEntity(email);
		if (result) {
			logger.info("------------------------execution completde of saveEmailInfoss  method---------------- ");
			return "saveapp";
		} else {
			logger.warn("-------------------saveStatusReport method fail------------------------ ");
			// return "errorPage";
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
		logger.info("------------------------start executing editApp method---------------- ");
		Application application = (Application) appService.getEntityByID(Application.class, appId);
		if (application == null) {
			logger.warn("-------------------editApp method fail------------------------ ");
			throw new ServerMonitorException(ErrorCode.UPDATE_ENTITY_FAIL);
		}
		logger.info("------------------------execution completde of editApp  method---------------- ");
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
	@RequestMapping(value = "update_application", method = RequestMethod.POST)
	public String updateApp(@ModelAttribute("application") Application application, ModelMap model) {
		logger.info("------------------------start executing updateApp method---------------- ");
		System.out.println("inside update method");
		result = appService.updateEntity(application);
		if (result) {
			logger.info("------------------------execution completde of updateApp  method---------------- ");
			return "redirect:displayApplication";
		} else {

			logger.warn("-------------------updateApp method fail------------------------ ");
			// return "errorPage";
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
		logger.info("------------------------start executing editUser method---------------- ");
		User user = (User) appService.getEntityByID(User.class, userId);
		model.addAttribute("user", user);
		logger.info("------------------------execution completde of editUser  method---------------- ");
		return "edit_user";
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
		logger.info("------------------------start executing deleteApp method---------------- ");
		result = appService.deleteEntity(Application.class, appId);
		if (result) {
			logger.info("------------------------execution completde of deleteApp  method---------------- ");
			return "redirect:displayApplication";
		} else {
			// return "errorPage";
			logger.warn("-------------------deleteApp method fail------------------------ ");
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
	public String deleteUser(@RequestParam int userId, ModelMap model) {
		logger.info("------------------------start executing deleteUser method---------------- ");
		result = appService.deleteEntity(User.class, userId);
		if (result) {
			logger.info("------------------------execution completde of deleteUser  method---------------- ");
			return "redirect:displayUser";
		} else {

			logger.warn("-------------------deleteUser method fail------------------------ ");
			// return "errorPage";
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
		logger.info("------------------------start executing displayApp method---------------- ");
		
		Application ill = appService.getISPList(Application.class);
		modelMap.addAttribute("ill",ill);
		List<ApplicationEntity> application = appService.getEntityList(Application.class);
		if (application == null) {

			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		logger.info("------------------------execution completde of displayApp  method---------------- ");
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
		logger.info("------------------------start executing displayUser method---------------- ");
		List<ApplicationEntity> user = appService.getEntityList(User.class);
		if (user == null) {
			logger.warn("-------------------displayUser method fail------------------------ ");
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		logger.info("------------------------execution completde of displayUser  method---------------- ");
		modelMap.addAttribute("user", user);
		return "display_user";
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, ModelMap model) {
		logger.info("------------------------start executing updateUser method---------------- ");
		System.out.println("inside update method");
		result = appService.updateEntity(user);
		if (result) {
			logger.info("------------------------execution completde of updateUser  method---------------- ");
			return "redirect:displayUser";
		} else {
			logger.warn("-------------------updateUser method fail------------------------ ");
			// return "errorPage";
			throw new ServerMonitorException(ErrorCode.UPDATE_ENTITY_FAIL);

		}
	}
	
	@RequestMapping(value = "applicationstatus")
	public String displayApplicationstatus(ModelMap modelMap) {
		logger.info("------------------------start executing displayApplicationstatus method---------------- ");
		System.out.println("inside application status");
		List<ApplicationEntity> applicationStatus = appService.getEntityList(Application.class);
		//System.out.println(applicationStatus.toString());
		if (applicationStatus == null) {
			logger.warn("-------------------displayUser method fail------------------------ ");
			throw new ServerMonitorException(ErrorCode.DISPLAY_ENTITY_ERROR);
		}
		logger.info("------------------------execution completde of displayApplicationstatus  method---------------- ");
		modelMap.addAttribute("applicationStatus", applicationStatus);
		return "status_Report";
	}
	
	/**
	 * Redirecting to lost_password page
	 * @return
	 */
	@RequestMapping("lost_password")
	public String lostPassword(){	
		return "lost_password";
	}
	
	/**
	 * 
	 * @param emailId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="get_password",method=RequestMethod.POST)
	public String getPassword(@RequestParam("emailId") String emailId,ModelMap model){
		String password=appService.getPasswordBasedOnEmailId(emailId);
		notifyService.sendLostPassword(emailId,password);
		return "redirect:/";
		
	}

}
