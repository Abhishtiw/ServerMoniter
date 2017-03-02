package com.est.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.est.dao.ApplicationDao;
import com.est.entity.ApplicationEntity;
import com.est.entity.User;
/**
 * This is an implementation class of type <tt>ApplicationService</tt> interface.It
 * overrides all the methods declared in that.
 * 
 * @author rgopalraj
 *
 */
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDao appDao;

	
	@Override
	@Transactional
	public boolean addEntity(ApplicationEntity appentity) {
		boolean value = appDao.addEntity(appentity);
		return value;
	}

	@Override
	public boolean updateEntity(ApplicationEntity appEntity) {
		boolean value = appDao.updateEntity(appEntity);
		return value;

	}

	@Override
	public boolean deleteEntity(Class<? extends ApplicationEntity> entityClass, int entityId) {
		boolean value = appDao.deleteEntity(entityClass, entityId);
		return value;
	}

	@Override
	public List<ApplicationEntity> getEntityList(Class<? extends ApplicationEntity> entityClass) {
		List<ApplicationEntity> entityList = appDao.getEntityList(entityClass);
		return entityList;
	}

	@Override
	public ApplicationEntity getEntityByID(Class<? extends ApplicationEntity> entityClass, int appId) {
		ApplicationEntity appEntity = appDao.getEntityByID(entityClass, appId);
		return appEntity;
	}

	@Override
	public User getNamePassword(String userName, String password){
		User user=appDao.getNamePassword(userName, password);
		return user;
	}
}
