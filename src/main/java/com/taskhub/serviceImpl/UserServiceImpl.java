package com.taskhub.serviceImpl;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taskhub.common.Utils;
import com.taskhub.model.User;
import com.taskhub.model.UserCredentials;
import com.taskhub.repository.UserRepository;
import com.taskhub.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void registerUser(User user, MultipartFile profilePhoto) throws SerialException, SQLException, IOException {
				
		String imageUrl = Utils.getImageURL(user, profilePhoto); 
		user.setProfileImageUrl(imageUrl);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public String updateProfilePhoto(User user, MultipartFile profilePhoto) throws IOException {
		String updateImageURL = Utils.getImageURL(user, profilePhoto);
		user.setProfileImageUrl(updateImageURL);
		userRepository.save(user);
		
		return updateImageURL;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByUserName(username);
		UserCredentials credentials = new UserCredentials(user);
		return credentials;
	}

	@Override
	public User findByUserName(String userName) {
		User user = userRepository.findUserByUserName(userName);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
}
