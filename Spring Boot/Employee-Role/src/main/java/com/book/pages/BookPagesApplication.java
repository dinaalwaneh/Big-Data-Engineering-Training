package com.book.pages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import com.book.pages.model.*;

import com.book.pages.repositores.*;

 

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookPagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookPagesApplication.class, args);
	}

	@Bean
	CommandLineRunner run (EmployeeRepository employeeRepository, RoleRepository roleRepository){
		
		return args ->{
			
			 
			Role role = roleRepository.save(new Role(null ,RoleName.Auditor));
			Role role2 =roleRepository.save(new Role(null , RoleName.SuperUser));
			Role role3 =roleRepository.save(new Role(null , RoleName.SupportUser));
			 
			
			employeeRepository.save(new Employee(null , "John d", "john", "1234",role));
	        employeeRepository.save(new Employee(null , "will d", "will", "1234", role2));
			employeeRepository.save(new Employee(null , "jim d", "jim", "1234", role));
			employeeRepository.save(new Employee(null , "arnold d", "arnold", "1234",role3));
			/*
			userService.addRoleToUser("john", "ROLE_USER");
		
			userService.addRoleToUser("will", "ROLE_MANEGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");

			*/
		};
	}
}