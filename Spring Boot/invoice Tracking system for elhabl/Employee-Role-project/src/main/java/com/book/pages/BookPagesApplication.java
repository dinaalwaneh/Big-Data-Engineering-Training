package com.book.pages;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.book.pages.model.*;

import com.book.pages.repositores.*;

@SpringBootApplication

public class BookPagesApplication  {

	public static void main(String[] args) {
		SpringApplication.run(BookPagesApplication.class, args);
	}




	@Bean
	CommandLineRunner run (EmployeeRepository employeeRepository, RoleRepository roleRepository,ClientRepository clientRepository){
		
		return args ->{
			
			 
			Role role = roleRepository.save(new Role(null ,RoleName.Auditor));
			Role role2 =roleRepository.save(new Role(null , RoleName.SuperUser));
			Role role3 =roleRepository.save(new Role(null , RoleName.SupportUser));
 
			employeeRepository.save(new Employee(null , "John d", "john", "1234","true",role));
	        employeeRepository.save(new Employee(null , "will d", "will", "1234","true", role2));
			employeeRepository.save(new Employee(null , "jim d", "jim", "1234","true", role));
			employeeRepository.save(new Employee(null , "arnold d", "arnold","true", "1234",role3));
			
 			clientRepository.save(new Client(1 , "John d", "john"));
			clientRepository.save(new Client(2 , "will d", "will"));
			clientRepository.save(new Client(3 , "jim d", "jim"));
			clientRepository.save(new Client(4 , "arnold d", "arnold"));
			
			
			/*
			userService.addRoleToUser("john", "ROLE_USER");
		
			userService.addRoleToUser("will", "ROLE_MANEGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");

			*/
		};
	}
}