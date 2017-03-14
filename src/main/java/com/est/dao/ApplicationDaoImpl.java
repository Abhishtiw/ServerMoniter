package com.est.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.est.controller.ApplicationController;
import com.est.dto.ApplicationAndStatusDto;
import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.ApplicationStatus;
import com.est.entity.User;
import com.est.util.ErrorCode;
import com.est.util.ServerMonitorException;

/**
 * This is an implementation class of type <tt>ApplicationDao</tt> interface.It
 * overrides all the methods declared in that.
 * 
 */
@Repository
public class ApplicationDaoImpl implements ApplicationDao {
	private static final Logger logger = Logger.getLogger(ApplicationController.class);
	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	private Transaction transaction;
	private Query query;
	private List<ApplicationEntity> entityList;

	@Override
	public boolean addEntity(ApplicationEntity appentity) {
		logger.info("Start Executing addEntity Method In ApplicationDaoImpl");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			/* Saving appEntity to database */
			session.save(appentity);
			transaction.commit();
			logger.info("Record Inserted");
			logger.info("Execution Completed addEntity Method In ApplicationDaoImpl");
			return true;
		} catch (HibernateException e) {
			logger.warn("Record not inserted..");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean updateEntity(ApplicationEntity entity) {
		logger.info("Start Executing updateEntity Method In ApplicationDaoImpl");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			logger.info("Record Updated");
			logger.info("Execution Completed updateEntity Method In ApplicationDaoImpl");
			return true;
		} catch (HibernateException e) {
			logger.warn("Record Not Updated");
			/* Rollback all transactions,if any exception occurs */;
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteEntity(Class<? extends ApplicationEntity> entityClass, int entityId) {
		logger.info("Start Executing deleteEntity Method In ApplicationDaoImpl");
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			ApplicationEntity appEntity = (ApplicationEntity) session.get(entityClass.getName(), entityId);
			session.delete(appEntity);
			transaction.commit();
			logger.info("Record deleted..");
			logger.info("Execution Completed deleteEntity Method In ApplicationDaoImpl");
			return true;
		} catch (HibernateException e) {
			logger.warn("Record Not Deleted");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ApplicationEntity> getEntityList(Class<? extends ApplicationEntity> entityClass) {
		logger.info("Start Executing getEntityList Method In ApplicationDaoImpl");
		entityList = new ArrayList<ApplicationEntity>();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if (entityClass.getSimpleName().equals("Application")) {
				query = session.createQuery("from Application where applicationType <> 'ill'");
			} else {
				query = session.createQuery("from " + entityClass.getSimpleName());
			}
			entityList = query.list();
			transaction.commit();
			logger.info("Execution Completed getEntityList Method In ApplicationDaoImpl");
		} catch (HibernateException e) {
			logger.warn("Failed to load details");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
		return entityList;
	}

	@Override
	public ApplicationEntity getEntityByID(Class<? extends ApplicationEntity> entityClass, int appId) {
		logger.info("Start Executing getEntityByID Method In ApplicationDaoImpl");
		ApplicationEntity appEntity;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
		    appEntity = (ApplicationEntity) session.get(entityClass.getName(), appId);
			transaction.commit();
			logger.info("Record Loaded");
			logger.info("Execution Completed getEntityByID Method In ApplicationDaoImpl");
			return appEntity;
		} catch (HibernateException e) {
			logger.warn("Record Not Loaded");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	public User getNamePassword(String userName, String password) {
		logger.info("Start Executing getNamePassword Method In ApplicationDaoImpl");
		User user = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("from User where userName='" + userName + "' and password='" + password + "'");
			query.setMaxResults(1);
			user = (User) query.uniqueResult();
			logger.info("User Record Loaded");
			logger.info("Execution Completed getNamePassword Method In ApplicationDaoImpl");
		} catch (HibernateException e) {
			logger.warn("User Record Not Loaded");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
		return user;

	}

	@Override
	public Application getISPList(Class<Application> class1) {
		logger.info("Start Executing getISPList Method In ApplicationDaoImpl");
		Application application = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("from Application where applicationType = 'ill'");
			query.setMaxResults(1);
			application = (Application) query.uniqueResult();
			transaction.commit();
			logger.info("ISP Record Loaded");
			logger.info("Execution Completed getISPList Method In ApplicationDaoImpl");
		} catch (HibernateException e) {
			logger.warn("Failed To Load Details");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.NO_IIL_FOUND, e);
		} finally {
			session.close();
		}
		return application;
	}

	@Override
	public String getPasswordBasedOnEmailId(String emailId) {
		logger.info("Start Executing getPasswordBasedOnEmailId Method In ApplicationDaoImpl");
		User user = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("from User where emailId='" + emailId + "'");
			query.setMaxResults(1);
			user = (User) query.uniqueResult();
			if (user != null) {
				logger.info("User Record Loaded");
				logger.info("Execution Completed getPasswordBasedOnEmailId Method In ApplicationDaoImpl");
				return user.getPassword();
			} else {
				logger.info("User Record Loaded With Null Value");
				return "";
			}
		} catch (HibernateException e) {
			logger.warn("User Record Not Loaded");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteEmailRecord(String mailId) {
		logger.info("Start Executing deleteEmailRecord Method In ApplicationDaoImpl");
		int result;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("delete from Email where emailId= :emailid");
			query.setString("emailid", mailId);
			result = query.executeUpdate();
			transaction.commit();
			logger.info("Execution Completed deleteEmailRecord Method In ApplicationDaoImpl");
			if (result > 0) {
				logger.info("Record deleted..");
				return true;
			} else {
				logger.warn("Record with email Id " + mailId + "  is not deleted!!");
				return false;
			}
		} catch (HibernateException e) {
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	/*
	 * this method will joins both the application entity and the application
	 * status entity by using HQL
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ApplicationAndStatusDto> getListApplicationAndStatus() {
		logger.info("Start Executing getListApplicationAndStatus Method In ApplicationDaoImpl");
		List<ApplicationAndStatusDto> appStatusList = new ArrayList<ApplicationAndStatusDto>();
		ApplicationAndStatusDto dto;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Application app = null;
			ApplicationStatus status = null;
			query = session.createQuery("from Application a, ApplicationStatus s where a.newStatusCode = s.statusCode");
			List<Object[]> list = query.list();
			for (Object[] obj : list) {
				dto = new ApplicationAndStatusDto();
				app = (Application) obj[0];
				if (!app.getApplicationType().equals("ill")) {
					status = (ApplicationStatus) obj[1];
					dto.setApplicationId(app.getApplicationId());
					dto.setApplicationName(app.getApplicationName());
					dto.setApplicationType(app.getApplicationType());
					dto.setApplicationURL(app.getApplicationURL());
					dto.setInternalIpAddress(app.getInternalIpAddress());
					dto.setResponseGeneratedTime(app.getResponseGeneratedTime());
					dto.setNewStatusCode(app.getNewStatusCode());
					dto.setMessage(status.getStatusMessage());
					dto.setActive(app.isActive());
					appStatusList.add(dto);
				}
			}
			logger.info("Execution Completed getListApplicationAndStatus Method In ApplicationDaoImpl");
		} catch (HibernateException e) {
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
		return appStatusList;
	}
}
