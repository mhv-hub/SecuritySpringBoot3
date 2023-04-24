package com.mhv.testsecurity.testsecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhv.testsecurity.testsecurity.config.CustomUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CustomUserDetailsService customUserDetailsService(){
		return new CustomUserDetailsService();
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
