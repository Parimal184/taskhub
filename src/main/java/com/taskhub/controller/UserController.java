package com.taskhub.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taskhub.common.Constants;
import com.taskhub.dto.UserDTO;
import com.taskhub.model.User;
import com.taskhub.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping(value = Constants.API_URL)
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> registerUser(@RequestPart("userDetails") UserDTO userDTO, @RequestPart MultipartFile profilePhoto) {
		
		User user = userService.findByEmail(userDTO.getEmail());
		if(user == null) {
			user = new User(userDTO);
			try {
				userService.registerUser(user, profilePhoto);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Username already registrred !", HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> updateProfilePhoto(@RequestPart("userEmail") String userEmail, @RequestPart MultipartFile profilePhoto) throws IOException {
		
		Map<String, String> json = new HashMap<>();
		User user = userService.findByEmail(userEmail);
		String updatedImageURL = userService.updateProfilePhoto(user, profilePhoto);
		json.put("updatedImageURL", updatedImageURL);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
}
