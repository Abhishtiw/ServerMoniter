package com.test.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import com.est.controller.ApplicationController;
import com.est.dao.ApplicationDao;
import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.User;
import com.est.service.ApplicationService;

import junit.framework.Assert;

/**
 *  we are using Mockito framework to test the functionality of our application.
 *  
 * Mockito just mocks the actual Object or actual interface and create a scenario 
 * of we are working with actual objects., but actually speaking, we are working 
 * with mock objects or dummy objects to test the application.
 * 
 * @author nkumar
 *
 */
//@RunWith engages our test class to run with MockitoJUnitRunner class
@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

	// @Mock annotation creates a Mock Object for ApplicationService
	@Mock
	ApplicationService applicationService;

	/*
	 * @InjectMocks tells us that the Mock Objects are being injected into
	 * ApplicationController
	 */
	@InjectMocks
	ApplicationController applicationController;

	Application application = Mockito.mock(Application.class);

	private MockMvc mockMvc;

	/*
	 * Here we are creating Application objects for our testing purpose. we are
	 * creating 3 objects because of clear understanding of what will happen
	 */
	Application application1 = new Application();
	Application application2 = new Application();
	Application application3 = new Application();

	
	List<ApplicationEntity> applications = null;

	/*
	 * @Before indicates that this method must be executed before each test in the class,
	 * so as to execute some preconditions necessary for the test.
	 */
	@Before
	public void setUp() {
		//initMocks() is a static method of MockitoAnnotations which initializes the mocking
		MockitoAnnotations.initMocks(this);
		//standaloneSetup(mock object).build() builds the application to be test is standalone application while testing.
		mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();
		//initializing the list.
		applications = new ArrayList<ApplicationEntity>();
	}
	

	/*
	 * @Test tells JUnit that the public void method to which it is attached can be run as a test case
	 */
	/*
	 * this method tests the functionality of fetching all the existed entities.
	 */
	@Test
	public void testGetEntityList() throws Exception {

		application1 = new Application();
		application1.setApplicationId(1001);
		application1.setApplicationName("google");
		application1.setApplicationType("external");
		application1.setApplicationURL("www.google.com");
		application1.setEmailId(1);
		application1.setInternalIpAddress("10");
		application1.setNewStatusCode(200);
		application1.setOldStatusCode(404);

		application2 = new Application();
		application2.setApplicationId(1002);
		application2.setApplicationName("tutorials");
		application2.setApplicationType("external");
		application2.setApplicationURL("www.tutorials.com");
		application2.setEmailId(1);
		application2.setInternalIpAddress("10");
		application2.setNewStatusCode(200);
		application2.setOldStatusCode(404);
		
		//adding all the above application objects to the list 
		applications.add(application1);
		applications.add(application2);

		/*
		 * here notice that 'when' and 'then' are static methods provided by Mockito framework
		 * when(method calls).thenReturn(something) is the logic behind it. 
		 */
		when(applicationService.getEntityList(Application.class)).thenReturn(applications);
		/*
		 * when we perform /displayAction and we are expecting the status of application as Ok and we expect the view as display_app.
		 */
		mockMvc.perform(get("/displayApplication")).andExpect(status().isOk()).andExpect(view().name("display_app"));
		/*
		 * by using assertEquals static method we are asserting the functionality of the method.so that we can test the method execution 
		 */
		assertEquals(applications, applicationService.getEntityList(Application.class));
		System.out.println(applications);

	}
	

	/*
	 * this method test the whether the application object is fetched  based on id or not.  
	 */
	@Test
	public void testGetEntityById() {

		application1.setApplicationId(1001);
		application1.setApplicationName("google");
		application1.setApplicationType("external");
		application1.setApplicationURL("www.google.com");
		application1.setEmailId(1);
		application1.setInternalIpAddress("10");
		application1.setNewStatusCode(200);
		application1.setOldStatusCode(404);

		/*
		 * when we call getEntityById then the method should return application object based on id  
		 */
		when(applicationService.getEntityByID(Application.class, 1001)).thenReturn(application1);
		/*
		 * again using assertEquals() we are asserting whether the outcome is correct or not.
		 */
		assertEquals(application1, applicationService.getEntityByID(Application.class, 1001));
		System.out.println(application1);

	}
	

	/*
	 * this method tests the functionlity of updating the application object
	 */
	@Test
	public void testUpdateEntity() throws Exception {

		application1.setApplicationId(1001);
		application1.setApplicationName("googlee");
		application1.setApplicationType("external");
		application1.setApplicationURL("www.google.com");
		application1.setEmailId(1);
		application1.setInternalIpAddress("10");
		application1.setNewStatusCode(200);
		application1.setOldStatusCode(404);

		/*
		 * when we call updateEntity then the method if it updates it should return true
		 * */ 
		when(applicationService.updateEntity(application1)).thenReturn(true);
		assertEquals(true, applicationService.updateEntity(application1));
		assertEquals("googleee", application1.getApplicationName());

		System.out.println(application1.getApplicationName());
	}
	

	/*
	 * this method test the functionality of adding a new entity(whether it may be application or other,
	 *  but it should extends entity).
	 */
	@Test
	public void testAddEntity() throws Exception {

		application3.setApplicationId(1003);
		application3.setApplicationName("yahoo");
		application3.setApplicationType("external");
		application3.setApplicationURL("www.yahoo.com");
		application3.setEmailId(1);
		application3.setInternalIpAddress("10");
		application3.setNewStatusCode(200);
		application3.setOldStatusCode(404);

		applications.add(application3);

		/*
		 * when we call addEntity then the method if it adds it should return true
		 * */ 
		when(applicationService.addEntity(application3)).thenReturn(true);

		assertEquals(true, applicationService.addEntity(application3));

		System.out.println(applications.size());
	}

	
	/*
	 * this method test the functionality of deleting the existed entity(whether it may be application or other,
	 *  but it should extends entity).
	 */
	@Test
	public void testDeleteEntity() {
		System.out.println(applications);

		application3.setApplicationId(1003);
		application3.setApplicationName("yahoo");
		application3.setApplicationType("external");
		application3.setApplicationURL("www.yahoo.com");
		application3.setEmailId(1);
		application3.setInternalIpAddress("10");
		application3.setNewStatusCode(200);
		application3.setOldStatusCode(404);

		System.out.println(application3);
		/*
		 * when we call deleteEntity then the method if it deletes it should return true
		 * */
		when(applicationService.deleteEntity(Application.class, 1003)).thenReturn(true);
		assertEquals(true, applicationService.deleteEntity(Application.class, 1003));

		System.out.println(applications);
	}

	
	/*
	 * @After gets executed after execution of every test.
	 */
	@After
	public void tearDown() {
		applications = null;
		application1 = null;
		application2 = null;
		application3 = null;
		System.out.println("tear down");
	}

}