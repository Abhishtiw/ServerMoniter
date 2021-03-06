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
	private static final Logger logger = Logger.getLogger(ApplicationDaoImpl.class);
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
			int value = (Integer) session.save(appentity);
			transaction.commit();
			logger.info("Record Inserted.Execution Completed addEntity Method In ApplicationDaoImpl");
			if (value > 0) {
				return true;
			}
			return false;
		} catch (HibernateException e) {
			logger.error("Record not inserted..");
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
			logger.info("Record Updated.Execution Completed updateEntity Method In ApplicationDaoImpl");
			return true;
		} catch (HibernateException e) {
			logger.error("Record Not Updated");
			/* Rollback all transactions,if any exception occurs */
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
			logger.info("Record deleted.Execution Completed deleteEntity Method In ApplicationDaoImpl");
			return true;
		} catch (HibernateException e) {
			logger.error("Record Not Deleted");
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
			if (entityClass.getSimpleName().equals("ApplicationStatusReport")) {
				query = session.createQuery("from " + entityClass.getSimpleName() + " order by id");
			} if(entityClass.getSimpleName().equals("InternetLeaseLine")){
				query=session.createQuery("from "+ entityClass.getSimpleName() + " order by primaryIll desc");
			}
			else {
				query = session.createQuery("from " + entityClass.getSimpleName() + " order by id");
			}
			entityList = query.list();
			transaction.commit();
			logger.info("Execution Completed getEntityList Method In ApplicationDaoImpl");
		} catch (HibernateException e) {
			logger.error("Failed to load details");
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
			logger.info("Record Loaded.Execution Completed getEntityByID Method In ApplicationDaoImpl");
			return appEntity;
		} catch (HibernateException e) {
			logger.error("Record Not Loaded");
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
			logger.info("User Record Loaded.Execution Completed getNamePassword Method In ApplicationDaoImpl");
		} catch (HibernateException e) {
			logger.error("User Record Not Loaded");
			/* Rollback all transactions,if any exception occurs */
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
		return user;

	}

	@Override
	public ApplicationEntity getEntityBasedOnEmailId(Class<? extends ApplicationEntity> entityClass, String emailId) {
		logger.info("Start Executing getEntityBasedOnEmailId Method In ApplicationDaoImpl");
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("from " + entityClass.getSimpleName() + " where emailId='" + emailId + "'");
			query.setMaxResults(1);
			ApplicationEntity  appEntity = (ApplicationEntity) query.uniqueResult();
			
			logger.info("ApplicationEntity Record Loaded. Execution Completed getEntityBasedOnEmailId Method In ApplicationDaoImpl");
			return appEntity;
		} catch (HibernateException e) {
			logger.error("ApplicationEntity Record Not Loaded");
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
				logger.error("Record with email Id " + mailId + "  is not deleted!!");
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

	/**
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
			if (list.size() > 0) {
				for (Object[] obj : list) {
					dto = new ApplicationAndStatusDto();
					app = (Application) obj[0];
					status = (ApplicationStatus) obj[1];
						dto.setId(app.getId());
						dto.setApplicationName(app.getApplicationName());
						dto.setApplicationType(app.getApplicationType());
						dto.setApplicationURL(app.getApplicationURL());
						dto.setInternalIpAddress(app.getInternalIpAddress());
						dto.setResponseGeneratedTime(app.getResponseGeneratedTime());
						dto.setNewStatusCode(app.getNewStatusCode());
						dto.setOldStatusCode(app.getOldStatusCode());
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

	/**
	 * 
	 * getting status message from applicationStatus table by passing status
	 * code
	 */
	public String getStatusMsg(int responseCode) {
		logger.info("Start Executing getStatusMsg Method in ApplicationDaoImpl");
		String statusMessage = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("select statusMessage from ApplicationStatus where statusCode = " + responseCode);
			query.setMaxResults(1);
			statusMessage = (String) query.uniqueResult();
			return statusMessage;
		} catch (HibernateException e) {
			logger.error("Status Message not loaded");
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}
}