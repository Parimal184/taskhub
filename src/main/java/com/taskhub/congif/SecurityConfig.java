package com.taskhub.congif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.taskhub.service.UserService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/user/hello", "/user/register")
				.permitAll().anyRequest().authenticated().and().formLogin();

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthProvider(UserService userService) {
	  DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	  provider.setUserDetailsService(userService);
	  provider.setPasswordEncoder(new BCryptPasswordEncoder());
	  return provider;
	}

}
