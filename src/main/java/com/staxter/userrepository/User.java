package com.staxter.userrepository;

import javax.validation.constraints.NotEmpty;

public class User {

	private String id;
	private String firstName;
	private String lastName;
	@NotEmpty(message = "userName is a required field")
	private String userName;
	@NotEmpty(message = "plainTextPassword is a required field")
	private String plainTextPassword;
	private String hashedPassword;

	public User() {

	}

	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.plainTextPassword = user.getPlainTextPassword();
		this.hashedPassword = user.getHashedPassword();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlainTextPassword() {
		return plainTextPassword;
	}

	public void setPlainTextPassword(String plainTextPassword) {
		this.plainTextPassword = plainTextPassword;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", plainTextPassword=" + plainTextPassword != null ? "****" : null 
				+ ", hashedPassword=" + hashedPassword != null ? "****" : null + "]";
	}
}
