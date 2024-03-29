package com.taskhub.model;


import com.taskhub.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column
	private String profileImageUrl;
	
	public User() {};

	public User(Long id, String password, String userName, String firstName, String lastName, String email, String profileImageUrl) {
		super();
		this.id = id;
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
	}
	
	public User(UserDTO to) {
		this.password = to.getPassword();
		this.userName = to.getUserName();
		this.firstName = to.getFirstName();
		this.lastName = to.getLastName();
		this.email = to.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", profileImageUrl=" + profileImageUrl + "]";
	}
	
}
