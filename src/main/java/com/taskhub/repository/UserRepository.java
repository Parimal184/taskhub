package com.taskhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskhub.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findUserByUserName(String userName);
	
	public User findUserByEmail(String email);
}
