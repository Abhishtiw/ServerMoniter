package com.est.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.est.entity.ApplicationEntity;
import com.est.entity.User;
import com.est.util.ErrorCode;
import com.est.util.ServerMonitorException;

/**
 * This is an implementation class of type <tt>ApplicationDao</tt> interface.It
 * overrides all the methods declared in that.
 * 
 * @author rgopalraj
 *
 */
@Repository
public class ApplicationDaoImpl implements ApplicationDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;
	private Transaction transaction;
	private Query query;
	private List<ApplicationEntity> entityList;

	@Override
	public boolean addEntity(ApplicationEntity appentity) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			/*Saving appEntity to database*/
			session.save(appentity);
			transaction.commit();
			System.out.println("Record inserted..");
			return true;
		} catch (HibernateException e) {
			System.out.println("Record not inserted..");
			/*Rollback all transactions,if any exception occurs*/
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean updateEntity(ApplicationEntity entity) {
		try {
			System.out.println("inside update");
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			System.out.println("Record updated..");
			return true;
		}  catch (HibernateException e) {
			System.err.println("Record not updated..")
			/*Rollback all transactions,if any exception occurs*/;
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteEntity(Class<? extends ApplicationEntity> entityClass, int entityId) {
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			ApplicationEntity appEntity = (ApplicationEntity) session.load(entityClass.getName(), entityId);
			session.delete(appEntity);
			transaction.commit();
			System.out.println("Record deleted");
			return true;
		} catch (HibernateException e) {
			System.err.println("Record not deleted");
			/*Rollback all transactions,if any exception occurs*/
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ApplicationEntity> getEntityList(Class<? extends ApplicationEntity> entityClass) {
		entityList = new ArrayList<ApplicationEntity>();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			System.out.println("entity===>" + entityClass.getSimpleName());
			query = session.createQuery("from " + entityClass.getSimpleName());
			System.out.println("query==>" + query);
			entityList = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Failed to load details");
			// System.out.println(e);
			/*Rollback all transactions,if any exception occurs*/
			transaction.rollback();
			throw new ServerMonitorException(ErrorCode.DB_TRANSACTION_FAILED, e);
		} finally {
			session.close();
		}
		return entityList;
	}

	@Override
	public ApplicationEntity getEntityByID(Class<? extends ApplicationEntity> entityClass, int appId) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			System.out.println("entity class   " + entityClass + "  id: " + appId);
			ApplicationEntity app = (ApplicationEntity) session.load(entityClass.getName(), appId);
			System.out.println("====>" + app);
			transaction.commit();
			System.out.println("Record loaded..");
			return app;
		} catch (HibernateException e) {
			System.err.println("Record not loaded..");
			/*Rollback all transactions,if any exception occurs*/
			transaction.rollback();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public User getNamePassword(String userName, String password) {
		User user = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query=session.createQuery("from User where userName='"+userName+"' and password='"+password+"'");
		
			query.setMaxResults(1);
			user=(User) query.uniqueResult();		
		} catch (HibernateException e) {
			e.printStackTrace();
			/*Rollback all transactions,if any exception occurs*/
			transaction.rollback();
		}
		finally {
			session.close();
		}
		return user;

	}
	
	
}
