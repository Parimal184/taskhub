package com.taskhub.service;

import com.taskhub.model.User;

public interface UserService {

	void registerUser(User user);

	User findByUserName(String userName);

	User findByEmail(String email);

}
