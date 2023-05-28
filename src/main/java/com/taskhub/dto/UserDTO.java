package com.taskhub.dto;

import com.taskhub.model.User;

public class UserDTO {
	
	private String password;
	private String userName;	
	private String firstName;	
	private String lastName;	
	private String email;
	
	public UserDTO(String password, String userName, String firstName, String lastName, String email) {
		super();
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void toMap(User user) {
		user.setEmail(this.getEmail());
		user.setFirstName(this.getFirstName());
		user.setPassword(this.getPassword());
		user.setUserName(this.getUserName());
		user.setLastName(this.getLastName());
	}

}
