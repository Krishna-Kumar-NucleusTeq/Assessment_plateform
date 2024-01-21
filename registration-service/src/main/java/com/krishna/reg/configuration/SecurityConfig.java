package com.krishna.reg.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	
//    	http.formLogin(Customizer.withDefaults());
//		http.csrf().disable();
//		http.headers().frameOptions().disable();
//		http.authorizeHttpRequests()
//				.requestMatchers(  "/users/save","/users/login","/auth/token","/auth/validate").permitAll()
//				.requestMatchers("/users/get/all").hasRole("ADMIN")
//				.requestMatchers("/auth/user/**").hasRole("USER")
//				.anyRequest().authenticated().and().formLogin();
//
//		http.httpBasic();
//
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
