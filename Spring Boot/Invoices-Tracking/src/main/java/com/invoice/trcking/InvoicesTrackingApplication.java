package com.invoice.trcking;

import java.security.Timestamp;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.History;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.InvoiceItemKey;
import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.RoleType;
import com.invoice.trcking.model.User;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.repository.CustomerRepository;
import com.invoice.trcking.repository.HistoryRepository;
import com.invoice.trcking.repository.InvoiceItemRepository;
import com.invoice.trcking.repository.InvoiceRepository;
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
			,ItemRepository itemRepository,InvoiceRepository invoiceRepository , InvoiceItemRepository invoiceItemRepository, HistoryRepository historyRepository){
		
		return args ->{
			
			//Post roles to database :
			Role firstRole = roleRepository.save(new Role(null ,RoleType.valueOf("AUDITORUSER")));
			Role secoundRole =roleRepository.save(new Role(null ,RoleType.valueOf("SUPERUSER")));
			Role thirdRole =roleRepository.save(new Role(null ,RoleType.valueOf("SUPPORTUSER")));
 
			//Post users to database :
			userRepository.save(new User(null , "Omar Ahmad", "omar", "jenin","dina1@gmail.com","0568285214", "123456789",true,firstRole));
			userRepository.save(new User(null , "Mahmoud Barahma", "mahmoud", "jenin","dina16@gmail.com","0568575214", "123456789",true, secoundRole));
			userRepository.save(new User(null , "jim d", "jim", "jenin","dina12@gmail.com","0568985214", "123456789",true, firstRole));
			userRepository.save(new User(null , "Ahmad Mohammad", "ahmad", "jenin","dina15@gmail.com","0568595214", "123456789",true,thirdRole));
			
			//Post items to database :
		    itemRepository.save(new Item(null , "Swish Wallet" , "new item in suuper market", 50,10,false ));
			itemRepository.save(new Item(null , "sanselk Wallet","new item in suuper market", 18,30,false));
			itemRepository.save(new Item(null , "noia Wallet","new item in suuper market", 10,5,false));
			itemRepository.save(new Item(null , "milk Wallet","new item in suuper market", 100,10,false));
			
			//Post Customers to database :
			customerRepository.save(new Customer(null , "John daron", "john", "jenin","0568285214","dina1@gmail.com",true));
			customerRepository.save(new Customer(null , "will d", "will", "jenin","0568575214","dina16@gmail.com",true));
			customerRepository.save(new Customer(null , "jim d", "jim", "jenin","0568985214","dina12@gmail.com",true));
			customerRepository.save(new Customer(null , "arnold d", "arnold", "jenin","0568595214","dina15@gmail.com",true));
	
		};
	}

}
