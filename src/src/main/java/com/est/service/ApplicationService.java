package com.est.service;

import java.util.List;

import com.est.dto.ApplicationAndStatusDto;
import com.est.entity.Application;
import com.est.entity.ApplicationEntity;
import com.est.entity.User;

/**
 * This interface defines all the CRUD operation methods which provides the
 * basic functionality to acheive creation,updation,deletion and retrival of any
 * entity.
 * 
 */
public interface ApplicationService {
	/**
	 * The implementation for this method ensures the creation of an appEntity.
	 * 
	 * @param appEntity
	 * @return true,if an entity is successfully created in the database.
	 */
	public boolean addEntity(ApplicationEntity appEntity);

	/**
	 * The implementation for this method ensures the updation of an
	 * appEntity,whenever an appEntity properties are modified.
	 * 
	 * @param appEntity
	 * @return true,if an entity is successfully updated with new modifications
	 *         in the database.
	 */
	public boolean updateEntity(ApplicationEntity appEntity);

	/**
	 * The implementation for this method ensures the deletion of an appEntity.
	 * 
	 * @param entityClass
	 * @param entityId
	 * @return true,if an entity is successfully deleted from the database.
	 */
	public boolean deleteEntity(Class<? extends ApplicationEntity> entityClass, int entityId);

	/**
	 * The implementation for this method is responsible to generate/fetch all
	 * the records from the repository.
	 * 
	 * @param entityClass
	 * @return a List of ApplicationEntity.
	 */
	public List<ApplicationEntity> getEntityList(Class<? extends ApplicationEntity> entityClass);

	/**
	 * The implementation for this method fetches a particular entity based on
	 * appId provided.
	 * 
	 * @param entityClass
	 * @param appId
	 * @return ApplicationEntity
	 */
	public ApplicationEntity getEntityByID(Class<? extends ApplicationEntity> entityClass, int appId);

	/**
	 * The implementation for this method returns the user record corresponding
	 * to the provided username and password.
	 * 
	 * @param userName
	 * @param password
	 * @return user record if given username and password is valid ,else it
	 *         returns null.
	 */
	public User getNamePassword(String userName, String password);

	/**
	 * The implementation for this method returns an ISP record from the
	 * database.
	 * 
	 * @return Application record
	 */
	public Application getISP();

	/**
	 * The implementation for this method returnsan Entity based on the provided
	 * email Id.
	 * 
	 * @param entityClass
	 * @param emaiId
	 * @return ApplicationEntity ,if the given email Id is correct else it
	 *         returns null.
	 */
	public ApplicationEntity getEntityBasedOnEmailId(Class<? extends ApplicationEntity> entityClass, String emaiId);

	/**
	 * The implementation for this method deletes the Email record, whenever the
	 * user related to that email Id gets deleted.
	 * 
	 * @param mailId
	 * @return true,if Email record is deleted or else it returns false.
	 */
	public boolean deleteEmailRecord(String mailId);

	/**
	 * The implementation for this method gets the Complete list of applications
	 * appended with the corresponding status of eac application.
	 * 
	 * @return List of ApplicationAndStatusDto records.
	 */
	public List<ApplicationAndStatusDto> getListApplicationAndStatus();
}
