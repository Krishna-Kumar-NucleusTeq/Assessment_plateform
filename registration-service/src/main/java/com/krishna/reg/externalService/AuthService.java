package com.krishna.reg.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.krishna.reg.dto.AuthRequest;
import com.krishna.reg.errorHandler.CustomErrorDecoder;

@FeignClient(name = "AUTH-SERVICE",configuration = CustomErrorDecoder.class)
public interface AuthService {

	@PostMapping("auth/token")
	String getToken(@RequestBody AuthRequest authRequest) ;
}
