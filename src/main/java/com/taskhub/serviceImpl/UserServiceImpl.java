package com.taskhub.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskhub.model.User;
import com.taskhub.model.UserCredentials;
import com.taskhub.repository.UserRepository;
import com.taskhub.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByUserName(username);
		UserCredentials credentials = new UserCredentials(user);
		if(credentials != null) {
			return credentials;
		}
		return null;
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
	
}
