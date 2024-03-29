package com.krishna.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import com.krishna.gateway.exception.ResourceNotFoundException;
import com.krishna.gateway.exception.UnauthorizedAccessException;
import com.krishna.gateway.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				
				 
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new ResourceNotFoundException("Access denied: Missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				try {
					jwtUtil.validateToken(authHeader);

				} catch (ExpiredJwtException e) {
					throw new UnauthorizedAccessException("Access denied: expired token");
	            } catch (MalformedJwtException e) {
	            	throw new UnauthorizedAccessException("Some changed has done in token !! Invalid Token");
	            }catch (Exception e) {
					throw new UnauthorizedAccessException("Access denied: Invalid token");
				}

				String userRole = jwtUtil.extractUserRole(authHeader);
				String path = exchange.getRequest().getPath().toString();

				if (path.contains("/update/user/") || path.contains("/user/delete/")
						|| path.contains("/users/get/all")) {

					if (!"ROLE_ADMIN".equals(userRole)) {
						throw new UnauthorizedAccessException("Access denied: Insufficient privileges for this URL");
					} else {
						return chain.filter(exchange);
					}
				}
			}
			return chain.filter(exchange);
		});
	}

	public static class Config {

	}
}