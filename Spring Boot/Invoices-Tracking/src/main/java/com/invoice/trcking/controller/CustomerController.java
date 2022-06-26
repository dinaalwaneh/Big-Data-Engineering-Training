package com.invoice.trcking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.invoice.trcking.service.CustomerService;

import lombok.extern.log4j.Log4j2;
import java.util.*;

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.mapper.CustomerMapper;
import com.invoice.trcking.model.Customer;

@ControllerAdvice
@RestController
@Log4j2
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
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id) throws NoSuchCustomerExistsException, Exception {
		
			try {
 				Customer customer = customerService.getCustomerById(id);
				// convert entity to DTO
				CustomerDto postResponse = customerMapper.convertEntityToDto(customer);
	
				return ResponseEntity.ok().body(postResponse);
				
			}catch (NoSuchCustomerExistsException e) {
				System.out.println("NoSuchCustomerExistsException : "+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("Exception : "+e.getMessage());
				e.printStackTrace();
			}
	
			return null;
	}
	
	@PostMapping("/add/customer")
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) throws NullPointerException, CustomerAlreadyExistsException, EmptyValueException, NullValueException, Exception {

			try {

				if(customerDto == null){
					throw new NullPointerException("customerDto point to null ");
				}
				// convert DTO to entity
				Customer customerRequest = customerMapper.convertDtoToEntity(customerDto);
		
				 
				Customer customer = customerService.addCustomer(customerRequest);
				
				if(customer == null){
					throw new NullPointerException("customer point to null");
				}
				// convert entity to DTO
				CustomerDto customerResponse = customerMapper.convertEntityToDto(customer);
	
				return new ResponseEntity<CustomerDto>(customerResponse, HttpStatus.CREATED);
				
			}catch(CustomerAlreadyExistsException e) {
				System.out.println("CustomerAlreadyExistsException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(NullPointerException e) {
				System.out.println("NullPointerException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(NullValueException e) {
				System.out.println("NullValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(EmptyValueException e) {
				System.out.println("EmptyValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(Exception e) {
				System.out.println("Exception : "+ e.getMessage()) ;
				e.printStackTrace();
			}
			
			return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/customer/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) throws NullPointerException, NoSuchCustomerExistsException, NullValueException , EmptyValueException, Exception {

			try {
				
				if(customerDto == null){
					throw new NullPointerException("customerDto point to null value :");
				}
				// convert DTO to Entity
				Customer customerRequest = customerMapper.convertDtoToEntity(customerDto);
	
				Customer customer = customerService.updateCustomer(id, customerRequest);
				
				if(customer == null){
					throw new NullPointerException("customer point to null");
				}
				// entity to DTO
				CustomerDto customerResponse = customerMapper.convertEntityToDto(customer);
	
				return ResponseEntity.ok().body(customerResponse);
			}catch(NoSuchCustomerExistsException e) {
				System.out.println("NoSuchCustomerExistsException : "+ e.getMessage());
				e.printStackTrace();
			}catch(NullPointerException e) {
				System.out.println("NullPointerException : "+ e.getMessage());
				e.printStackTrace();
			}catch(NullValueException e) {
				System.out.println("NullValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(EmptyValueException e) {
				System.out.println("EmptyValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(Exception e) {
				System.out.println("Exception : "+ e.getMessage());
				e.printStackTrace();
			}
			
			return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
		
	}
}
