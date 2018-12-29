package com.geroza.samples.springsecurityauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class SpringSecurityAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthorizationApplication.class, args);
	}

}

