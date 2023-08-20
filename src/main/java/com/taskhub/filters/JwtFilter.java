package com.taskhub.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.taskhub.congif.JwtUtils;
import com.taskhub.model.User;
import com.taskhub.model.UserCredentials;
import com.taskhub.serviceImpl.UserServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserServiceImpl userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	        throws ServletException, IOException, InsufficientAuthenticationException {

	    final String requestTokenHeader = request.getHeader("Authorization");
	    logger.warn(requestTokenHeader);

	    String username = null;
	    String jwtToken = null;

	    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	        jwtToken = requestTokenHeader.substring(7);
	        try {
	            username = jwtUtils.getUsernameFromToken(jwtToken);
	        } catch (IllegalArgumentException e) {
	            logger.error("Unable to get JWT Token: {}");
	        } catch (ExpiredJwtException e) {
	            logger.warn("JWT Token has expired: {}");
	            throw new InsufficientAuthenticationException("Session Expired!");
	        }
	    } else {
	        logger.warn("JWT Token does not begin with Bearer String");
	    }

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        User loginUser = userService.findByUserName(username);

	        if (loginUser != null) {
	            UserCredentials user = new UserCredentials(loginUser);

	            if (jwtUtils.validateToken(jwtToken, user)) {
	                UsernamePasswordAuthenticationToken authenticationToken =
	                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	    }

	    chain.doFilter(request, response);
	}

	
}
