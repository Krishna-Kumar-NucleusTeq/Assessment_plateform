package com.krishna.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

//import org.apache.xml.security.algorithms.Algorithm;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.krishna.auth.dto.RegistrationDto;
import com.krishna.auth.externalServices.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtService {

	 @Autowired
	 private UserService userService;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public void validateToken(final String token) {
    	Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
    }


    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        System.out.println("reached in generate token method.");
        RegistrationDto user = userService.getUserByEmail(userName);
        
        System.out.println("Current users role is :"+user.getUserRole());
        
        claims.put("role", user.getUserRole());
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {

        return JWT.create()
        	    .withSubject(userName)
        	    .withClaim("role", (String) claims.get("role"))
        	    .withIssuer(userName)
        	    .withIssuedAt(new Date())
        	    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 1000)) // 30 minutes from now
        	    .sign(Algorithm.HMAC256(getSignKey().getEncoded()));
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}