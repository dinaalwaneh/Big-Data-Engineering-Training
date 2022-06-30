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
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.InvoiceItemKey;
import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.RoleType;
import com.invoice.trcking.model.User;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.repository.CustomerRepository;
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
			,ItemRepository itemRepository,InvoiceRepository invoiceRepository , InvoiceItemRepository invoiceItemRepository){
		
		return args ->{
			
			
			
			Role role = roleRepository.save(new Role(null ,RoleType.valueOf("AUDITORUSER")));
			Role role2 =roleRepository.save(new Role(null ,RoleType.valueOf("SUPERUSER")));
			Role role3 =roleRepository.save(new Role(null ,RoleType.valueOf("SUPPORTUSER")));
 

			User u =userRepository.save(new User(null , "John daron", "john", "jenin","dina1@gmail.com","0568285214", "1234",true,role));
			userRepository.save(new User(null , "will d", "will", "jenin","dina16@gmail.com","0568575214", "1234",true, role2));
			userRepository.save(new User(null , "jim d", "jim", "jenin","dina12@gmail.com","0568985214", "1234",true, role));
			userRepository.save(new User(null , "arnold d", "arnold", "jenin","dina15@gmail.com","0568595214", "1234",true,role3));
			
		Item item = itemRepository.save(new Item(null , "Swish Wallet" , "new item in suuper market", 50,10,false ));
			itemRepository.save(new Item(null , "sanselk Wallet","new item in suuper market", 18,30,false));
			itemRepository.save(new Item(null , "noia Wallet","new item in suuper market", 10,5,false));
			itemRepository.save(new Item(null , "milk Wallet","new item in suuper market", 100,10,false));
			
			Customer c=customerRepository.save(new Customer(null , "John daron", "john", "jenin","0568285214","dina1@gmail.com",true));
			customerRepository.save(new Customer(null , "will d", "will", "jenin","0568575214","dina16@gmail.com",true));
			customerRepository.save(new Customer(null , "jim d", "jim", "jenin","0568985214","dina12@gmail.com",true));
			customerRepository.save(new Customer(null , "arnold d", "arnold", "jenin","0568595214","dina15@gmail.com",true));
			 
		 
			LocalDateTime localDateTime = LocalDateTime.now();
			LocalDateTime myDateObj = LocalDateTime.now();
		    System.out.println("Before formatting: " + myDateObj);
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		    String formattedDate = myDateObj.format(myFormatObj);
		    System.out.println("After formatting: " + formattedDate);

		    Invoice invoice=	invoiceRepository.save(new Invoice(null, formattedDate,formattedDate,111,0,0,0,"Empty","false",c,u));
			
			/*
			invoiceRepository.save(new Invoice(null, formattedDate,formattedDate,5585,100,0,100,"fff","false",c,u));
			 
			
			InvoiceItemKey h = new InvoiceItemKey(invoice.getId(),item.getId());
			invoiceItemRepository.save(new InvoiceItem(h,invoice,item,20,10));
			
			
			 
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
