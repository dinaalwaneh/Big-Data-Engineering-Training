package com.invoice.trcking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.invoice.trcking.service.CustomerService;

import java.io.Console;
import java.util.*;

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.mapper.CustomerMapper;
import com.invoice.trcking.model.Customer;

@ControllerAdvice
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
	
	@GetMapping("/get/customer/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id)  {
		try {
			Customer customer = customerService.getCustomerById(id);
			// convert entity to DTO
			CustomerDto postResponse = customerMapper.convertEntityToDto(customer);

			return ResponseEntity.ok().body(postResponse);
			
		}catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException : "+new NoSuchElementException("There is no custemer with id = "+id)) ;
		}catch (Exception e) {
			System.out.println("Exception : "+e.getMessage()) ;
		}

		return null;
	}
	
	@PostMapping("/add/customer")
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {

		// convert DTO to entity
		Customer customerRequest = customerMapper.convertDtoToEntity(customerDto);

		Customer user = customerService.createCustomer(customerRequest);

		// convert entity to DTO
		CustomerDto customerResponse = customerMapper.convertEntityToDto(user);

		return new ResponseEntity<CustomerDto>(customerResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/customer/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {

		// convert DTO to Entity
		Customer customerRequest = customerMapper.convertDtoToEntity(customerDto);


		Customer customer = customerService.updateCustomer(id, customerRequest);

		// entity to DTO
		CustomerDto customerResponse = customerMapper.convertEntityToDto(customer);

		return ResponseEntity.ok().body(customerResponse);
	}
}
