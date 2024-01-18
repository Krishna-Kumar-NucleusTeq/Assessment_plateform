package com.krishna.auth.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.krishna.auth.dto.RegistrationDto;
import com.krishna.auth.errorHandler.CustomErrorDecoder;

@FeignClient(name = "REGISTRATION-SERVICE",configuration = CustomErrorDecoder.class)
public interface UserService {

	@GetMapping("/users/getUser/{email}")
	RegistrationDto getUserByEmail(@PathVariable("email") String email);

}