package com.taskhub.dto;

import java.sql.Blob;

import jakarta.persistence.Lob;

public class UserDTO {
	
	private String password;
	private String userName;	
	private String firstName;	
	private String lastName;	
	private String email;
	private String profileImageUrl;
	
	public UserDTO(String password, String userName, String firstName, String lastName, String email, String profileImageUrl) {
		super();
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
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

	public String getProfilePhoto() {
		return profileImageUrl;
	}

	public void setProfilePhoto(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
}
