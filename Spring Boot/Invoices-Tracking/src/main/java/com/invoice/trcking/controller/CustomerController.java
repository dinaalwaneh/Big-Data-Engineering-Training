package com.invoice.trcking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.trcking.service.CustomerService;


import java.util.*;

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.mapper.CustomerMapper;
import com.invoice.trcking.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerMapper customerMapper;

	@GetMapping("/get/customers")
	public ResponseEntity<Object> getCustomers()
	{
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		customerService.getAllCustomers().forEach(user -> customerDtos.add(customerMapper.convertEntityToDto(user)));
		return new ResponseEntity<>(customerDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/customers/{id}")
	public ResponseEntity<CustomerDto> getPostById(@PathVariable(name = "id") Long id) {
		Customer customer = customerService.getCustomerById(id);

		// convert entity to DTO
		CustomerDto postResponse = customerMapper.convertEntityToDto(customer);

		return ResponseEntity.ok().body(postResponse);
	}
	
	public ResponseEntity<CustomerDto> createPost(@RequestBody CustomerDto customerDto) {

		// convert DTO to entity
		Customer customerRequest = customerMapper.convertDtoToEntity(customerDto);

		Customer user = customerService.createCustomer(customerRequest);

		// convert entity to DTO
		CustomerDto customerResponse = customerMapper.convertEntityToDto(user);

		return new ResponseEntity<CustomerDto>(customerResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/customers/{id}")
	public ResponseEntity<CustomerDto> updatePost(@PathVariable long id, @RequestBody CustomerDto customerDto) {

		// convert DTO to Entity
		Customer customerRequest = customerMapper.convertDtoToEntity(customerDto);


		Customer customer = customerService.updateCustomer(id, customerRequest);

		// entity to DTO
		CustomerDto customerResponse = customerMapper.convertEntityToDto(customer);

		return ResponseEntity.ok().body(customerResponse);
	}
}
