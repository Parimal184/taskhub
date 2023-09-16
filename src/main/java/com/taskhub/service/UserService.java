package com.taskhub.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.taskhub.model.User;

public interface UserService {

	void registerUser(User user, MultipartFile file) throws SerialException, SQLException, IOException;

	User findByUserName(String userName) throws SQLException;

	User findByEmail(String email);
	
	User findById(Long id);

}
