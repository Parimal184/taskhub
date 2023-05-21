package com.taskhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.model.User;
import com.taskhub.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody User user) {
		
		System.out.println("Test Save");
		service.saveUser(user);
		return "OK";
	}
	
	@RequestMapping(value = "/hello", method =RequestMethod.GET)
	public String hello() {
		return "hello world ";
	}
	
}
