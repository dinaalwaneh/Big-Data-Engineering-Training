package com.invoice.trcking;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.RoleType;
import com.invoice.trcking.model.User;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.repository.CustomerRepository;
import com.invoice.trcking.repository.ItemRepository;
import com.invoice.trcking.repository.RoleRepository;
import com.invoice.trcking.repository.UserRepository;

@SpringBootApplication
public class InvoicesTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoicesTrackingApplication.class, args);
	}
	

	@Bean
	CommandLineRunner run (UserRepository userRepository, RoleRepository roleRepository,CustomerRepository customerRepository
			,ItemRepository itemRepository){
		
		return args ->{
			
			 
			Role role = roleRepository.save(new Role(null ,RoleType.Auditor));
			Role role2 =roleRepository.save(new Role(null , RoleType.SuperUser));
			Role role3 =roleRepository.save(new Role(null , RoleType.SupportUser));
 

			userRepository.save(new User(null , "John daron", "john", "jenin","dina1@gmail.com","0568285214", "1234",true,role));
			userRepository.save(new User(null , "will d", "will", "jenin","dina16@gmail.com","0568575214", "1234",true, role2));
			userRepository.save(new User(null , "jim d", "jim", "jenin","dina12@gmail.com","0568985214", "1234",true, role));
			userRepository.save(new User(null , "arnold d", "arnold", "jenin","dina15@gmail.com","0568595214", "1234",true,role3));
			
			/*customerRepository.save(new Customer(1 , "John d", "john"));
			customerRepository.save(new Customer(2 , "will d", "will"));
			customerRepository.save(new Customer(3 , "jim d", "jim"));
			customerRepository.save(new Customer(4 , "arnold d", "arnold"));
			
			itemRepository.save(new Item(null , "Swish Wallet", 50,10,"flase"));
			itemRepository.save(new Item(null , "sanselk Wallet", 18,30,"flase"));
			itemRepository.save(new Item(null , "noia Wallet", 10,5,"flase"));
			itemRepository.save(new Item(null , "milk Wallet", 100,10,"flase"));
			 
			 */
			/*
			userService.addRoleToUser("john", "ROLE_USER");
		
			userService.addRoleToUser("will", "ROLE_MANEGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");

			*/
		};
	}

}