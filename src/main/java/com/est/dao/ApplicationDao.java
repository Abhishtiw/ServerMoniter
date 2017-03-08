package com.est.dao;

import java.util.List;

import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.User;

/**
 * This interface defines all the CRUD operation methods which provides the
 * basic functionality to acheive creation,updation,deletion and retrival of any
 * entity.
 * 
 * @author rgopalraj
 *
 */
public interface ApplicationDao {
	/**
	 * This method ensures the creation of an appEntity.
	 * 
	 * @param appEntity
	 * @return true,if an entity is successfully created in the database.
	 */
	public boolean addEntity(ApplicationEntity appEntity);

	/**
	 * This method ensures the updation of an appEntity,whenever an appEntity
	 * properties are modified.
	 * 
	 * @param appEntity
	 * @return true,if an entity is successfully updated with new modifications
	 *         in the database.
	 */
	public boolean updateEntity(ApplicationEntity appEntity);

	/**
	 * This method ensures the deletion of an appEntity.
	 * 
	 * @param entityClass
	 * @param entityId
	 * @return true,if an entity is successfully deleted from the database.
	 */
	public boolean deleteEntity(Class<? extends ApplicationEntity> entityClass, int entityId);

	/**
	 * This method is responsible to generate/fetch all the records from the
	 * repository.
	 * 
	 * @param entityClass
	 * @return a List of ApplicationEntity.
	 */
	public List<ApplicationEntity> getEntityList(Class<? extends ApplicationEntity> entityClass);

	/**
	 * This method fetches a particular entity based on appId provided.
	 * 
	 * @param entityClass
	 * @param appId
	 * @return ApplicationEntity
	 */
	public ApplicationEntity getEntityByID(Class<? extends ApplicationEntity> entityClass, int appId);
	
	public User getNamePassword(String userName, String password);
	
	public Application getISPList(Class<Application> class1);
	
	public String getPasswordBasedOnEmailId(String emailId);

}
