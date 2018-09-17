package com.staxter.demo.dto;

import com.staxter.userrepository.User;

public class UserDto {

	private String id;
	private String firstName;
	private String lastName;
	private String userName;

	public UserDto(User u) {
		this.id = u.getId();
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.userName = u.getUserName();
	}

	public UserDto(String id, String firstName, String lastName, String userName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "UserDtoOut [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ "]";
	}
}
