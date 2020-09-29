package com.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class OAuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthDemoApplication.class, args);
	}

}
