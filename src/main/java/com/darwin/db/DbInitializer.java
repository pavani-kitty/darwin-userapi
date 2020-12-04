package com.darwin.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.darwin.user.data.User;
import com.darwin.user.data.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
* DbInitializer is the class which preloads the H2 in memory database 
* with some sample data.
* 
* @author Pavani
*/

@Configuration
@Slf4j
public class DbInitializer implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.deleteAll();

		User user1 = new User("John Doe", "john@fx.com", "password", "07867530387", "sales", "salesman");
		User user2 = new User("Dheeraj Shah", "dheeraj@gmail.com", "password", "046-582-68787", "hr", "hrrep");
		User user3 = new User("Alex Smith", "alex@yahoo.com", "password", "0757860387", "electrical", "electrician");
		User user4 = new User("Rachel H ", "rachel@tui.com", "password", "04567530387", "it", "QualityAnalyst");
		User user5 = new User("Smitha Patil", "smitha@dheere.com", "password", "09876230387", "software", "programmer");
		User user6 = new User("Andy Haigh", "andy@google.com", "password", "09237530387", "logistics", "analyst");

		log.info("Preloading " + this.userRepository.save(user1));
		log.info("Preloading " + this.userRepository.save(user2));
		log.info("Preloading " + this.userRepository.save(user3));
		log.info("Preloading " + this.userRepository.save(user4));
		log.info("Preloading " + this.userRepository.save(user5));
		log.info("Preloading " + this.userRepository.save(user6));
	}
}
