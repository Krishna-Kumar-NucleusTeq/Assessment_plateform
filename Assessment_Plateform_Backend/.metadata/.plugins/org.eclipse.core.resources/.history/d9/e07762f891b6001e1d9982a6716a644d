package com.krishna.auth.configuration;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.krishna.auth.dto.RegistrationDto;

public class CustomUserDetails implements UserDetails {
	
		
	private RegistrationDto user;
	
    
    public CustomUserDetails(RegistrationDto credential) {
	super();
	this.user = credential;
}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
    	System.out.println("reached in custmor details class.");
    	SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getUserRole());
        return Arrays.asList(simpleGrantedAuthority);
    }
    

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
    	System.out.println("called the getuser name method.");
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

