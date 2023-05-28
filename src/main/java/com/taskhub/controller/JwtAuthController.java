package com.taskhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskhub.congif.JwtUtils;
import com.taskhub.dto.UserDTO;
import com.taskhub.model.JwtRequest;
import com.taskhub.model.JwtResponse;
import com.taskhub.model.User;
import com.taskhub.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;				

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class JwtAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> registerUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody UserDTO userDTO) {
		
		User user = userService.loadUserByUsername(userDTO.getUserName());
		
		if(user == null) {
			user = new User();
			userDTO.toMap(user);
			userService.saveUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Username already registrred !", HttpStatus.CONFLICT);
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final User user = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtUtils.generateToken(user);

		return ResponseEntity.ok(new JwtResponse(token));
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
