package com.darwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* UserApiApplication is the SpringBoot application for 
* darwin - for user related RESTful services
* 
* @author Pavani
*/
@SpringBootApplication
public class UserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}

}
