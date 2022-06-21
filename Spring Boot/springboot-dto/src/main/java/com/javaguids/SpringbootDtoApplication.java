package com.javaguids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.javaguids.model.Location;
import com.javaguids.model.User;
import com.javaguids.repository.LocationRepository;
import com.javaguids.repository.UserRepository;
import com.javaguids.service.UserService;

@SpringBootApplication
public class SpringbootDtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDtoApplication.class, args);
	
	}
 
	@Bean
	CommandLineRunner run (UserRepository userRepository, LocationRepository locationRepository){
		
		return args ->{
			
		 
			Location location = new Location(1,"jenin","anza",40,40);
			locationRepository.save(location);
			User user1 = new  User(1, "dina@gmail.com", "dina","cccc","vvv",location);
		 
			userRepository.save(user1);
		 
		};
		
	}
 

}
