package com.book.pages.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.pages.model.Employee;
import com.book.pages.model.Role;
import com.book.pages.repositores.ClientRepository;
import com.book.pages.repositores.RoleRepository;

import java.util.*;
import com.book.pages.model.Client;

@RestController
public class ClientController {
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
	private ClientRepository clientRepository;
	
 
	List<Client> client = new ArrayList<>();

	@PostMapping("/saveBook")
	public Client addBook(@RequestBody Client client) {
		clientRepository.save(client);
	
		return client;
		
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
		List<Client> employee = new ArrayList<>();
		clientRepository.findAll().forEach(custumet->employee.add(custumet));
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

}
