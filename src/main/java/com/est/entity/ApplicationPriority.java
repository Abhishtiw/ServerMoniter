package com.est.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity is meant for Application priorities which might requires
 * attention or not.
 */
@Entity
@Table(name = "app_priority")
public class ApplicationPriority extends ApplicationEntity {
	/* declaring variables for ApplicationPriority */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int priorityId;
	private String priorityName;
	private int applicationId;

	/* setters and getters for variables declared above. */
	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

}
