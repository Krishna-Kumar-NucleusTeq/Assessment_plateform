package com.krishna.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.auth.dto.AuthRequest;
import com.krishna.auth.service.UserCrediantialService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



@EnableMethodSecurity
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserCrediantialService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmailId());
        } else {
            throw new RuntimeException("Invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
    
    @GetMapping("/admin/test")
    public String getAuthenticatedAdmin(){
    	System.out.println("reached in admin Test contoller method.");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("Authorities: " + authentication.getAuthorities());
    	return "Current Admin role is : "+authentication.getAuthorities();

    }
    
    @GetMapping("/user/test")
    public String getAuthenticatedUser(){
    	System.out.println("reached in admin Test contoller method.");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("Authorities: " + authentication.getAuthorities());
    	return "Current User role is : "+authentication.getAuthorities();

    }
}
