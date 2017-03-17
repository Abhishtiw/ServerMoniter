package com.est.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is meant for adding user. Since an user visits the status of the
 * application.
 *
 */
@Entity
@Table(name = "user")
public class User extends ApplicationEntity {

	/* declaring variables required to add an user*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private int id;
	private String userName;
	private String emailId;
	private String password;

	/* setters and getters for the variables declared above*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// overriding toString method
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password + "]";
	}
}
