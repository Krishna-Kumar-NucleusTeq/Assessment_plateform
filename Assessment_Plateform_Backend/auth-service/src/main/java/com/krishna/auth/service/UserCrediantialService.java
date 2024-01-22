package com.krishna.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCrediantialService {

	
	 @Autowired
	 private JwtService jwtService;
	
	 public String generateToken(String username) {
	        return jwtService.generateToken(username);
	 }
	 

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
	
}