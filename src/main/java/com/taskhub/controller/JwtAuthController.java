package com.taskhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.common.Constants;
import com.taskhub.congif.JwtUtils;
import com.taskhub.dto.UserDTO;
import com.taskhub.model.JwtRequest;
import com.taskhub.model.JwtResponse;
import com.taskhub.model.User;
import com.taskhub.model.UserCredentials;
import com.taskhub.service.UserService;
import com.taskhub.serviceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;				

@RestController
@RequestMapping(Constants.API_URL)
@CrossOrigin("http://localhost:4200")
public class JwtAuthController {

	public static final Logger logger = LoggerFactory.getLogger(JwtAuthController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		logger.warn("authenticationRequest ::"+authenticationRequest);
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final User user = userService
				.findByUserName(authenticationRequest.getUsername());
		
		UserCredentials credentials = new UserCredentials(user); 
		HttpHeaders headers = getJwtHeader(credentials);
		logger.warn("Headers :"+headers);
		return new ResponseEntity<>(user, headers, HttpStatus.OK);
	}
	
	private HttpHeaders getJwtHeader(UserCredentials user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Jwt-Token", jwtUtils.generateToken(user));
        return headers;
    }

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
