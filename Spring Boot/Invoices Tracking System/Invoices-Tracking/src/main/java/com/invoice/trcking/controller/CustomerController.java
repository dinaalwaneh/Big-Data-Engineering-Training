package com.invoice.trcking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.trcking.model.User;
import com.invoice.trcking.model.Role;
import com.invoice.trcking.repository.*;

import java.util.*;
import com.invoice.trcking.model.Customer;

@RestController
public class CustomerController {
/*
	
	
	@GetMapping("/client")
	public ResponseEntity<List<Client>> getClient(){
		return ResponseEntity.ok().body((List<Client>) clientRepository.findAll());
	}
	@PostMapping("/add/client")
	public void save(@RequestBody Client client) {
		clientRepository.save(client);
	}
	*/
	
	@Autowired
	private CustomerRepository customerRepository;
	
 
	List<Customer> customer = new ArrayList<>();

	@PostMapping("/saveBook")
	public Customer addBook(@RequestBody Customer customer) {
		customerRepository.save(customer);
	
		return customer;
		
	}

/*	@GetMapping("/getBooks")
	public ResponseEntity<List<Object>> getAllBooks() {
		return ResponseEntity.ok().body((List<Client>)clientRepository.findAll());
	}
	@GetMapping("/getBooks")
	public List<Client> getClients(){
		
		List<Client> employee = new ArrayList<>();
		clientRepository.findAll().forEach(custumet->employee.add(custumet));
		return employee;
	}*/
	
	@RequestMapping(value = "/getBooks", method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployees()
	{
		List<Customer> employee = new ArrayList<>();
		customerRepository.findAll().forEach(custumet->employee.add(custumet));
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

}
