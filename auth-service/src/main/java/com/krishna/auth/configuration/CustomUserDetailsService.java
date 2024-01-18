package com.krishna.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.krishna.auth.dto.RegistrationDto;
import com.krishna.auth.externalServices.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	
    	System.out.println("invoke the get user by loaduserby userName.");
        RegistrationDto credential = userService.getUserByEmail(username);
        
        if(credential == null) {
        	 throw new UsernameNotFoundException("user not found with name :" + username);
        }
        System.out.println("Uesr Email id is :"+credential.getEmail());
        
        CustomUserDetails customUserDetails= new CustomUserDetails(credential);
        return customUserDetails;
    }
}