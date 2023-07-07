package com.taskhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.common.Constants;
import com.taskhub.dto.UserDTO;
import com.taskhub.model.User;
import com.taskhub.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = Constants.API_URL)
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/hello", method =RequestMethod.GET)
	public String hello() {
		return "hello world ";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody UserDTO userDTO) {
		
		User user = userService.getUserByEmail(userDTO.getEmail());
		
		if(user == null) {
			user = new User();
			userDTO.toMap(user);
			userService.saveUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Username already registrred !", HttpStatus.CONFLICT);
		
	}
	
}
