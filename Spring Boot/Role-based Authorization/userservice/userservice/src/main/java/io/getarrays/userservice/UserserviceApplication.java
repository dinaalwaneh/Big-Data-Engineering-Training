package io.getarrays.userservice;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.getarrays.userservice.domain.*;
import io.getarrays.userservice.service.UserService;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run (UserService userService){
		
		return args ->{
			
			userService.saveRole(new Role(null , "ROLE_USER"));
			userService.saveRole(new Role(null , "ROLE_MANEGER"));
			userService.saveRole(new Role(null , "ROLE_ADMIN"));
			userService.saveRole(new Role(null , "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null , "John d", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null , "will d", "will", "1234", new ArrayList<>()));
			userService.saveUser(new User(null , "jim d", "jim", "1234", new ArrayList<>()));
			userService.saveUser(new User(null , "arnold d", "arnold", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANEGER");
			userService.addRoleToUser("will", "ROLE_MANEGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");	
			
		};
		
	}

}
