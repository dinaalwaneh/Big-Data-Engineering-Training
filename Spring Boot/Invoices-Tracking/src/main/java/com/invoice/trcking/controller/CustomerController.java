package com.invoice.trcking.controller;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

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
				//LOGGER.debug("getCustomerById service with id = {} ",id);
 				Customer customer = customerService.getCustomerById(id);
				// convert entity to DTO
				CustomerDto postResponse = customerMapper.convertEntityToDto(customer);
				
				LOGGER.info("getCustomerById service with id = {} is done successfully",id);

				return ResponseEntity.ok().body(postResponse);
				
			}catch (NoSuchCustomerExistsException e) {
				LOGGER.error("Customer not foundes with id = {} ",id);
				System.out.println("NoSuchCustomerExistsException : "+e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				LOGGER.error("Something goes wrong in getCustomerById api at Customer Controller");
				System.out.println("Exception : "+e.getMessage());
				e.printStackTrace();
			}
	
			return null;
	}
	
	@PostMapping("/add/customer")
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) throws NullPointerException, CustomerAlreadyExistsException, EmptyValueException, NullValueException, Exception {

			try {

				//LOGGER.debug("Add Customer service");
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
				LOGGER.error("Customer with id = {} added successfuly",customerDto.getId());
				return new ResponseEntity<CustomerDto>(customerResponse, HttpStatus.CREATED);
				
			}catch(CustomerAlreadyExistsException e) {
				LOGGER.error("Customer with id = {} already exists in item entity",customerDto.getId());
				System.out.println("CustomerAlreadyExistsException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(NullPointerException e) {
				LOGGER.error("object point to null");
				System.out.println("NullPointerException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(NullValueException e) {
				LOGGER.error("Customer data has null value!!");
				System.out.println("NullValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(EmptyValueException e) {
				LOGGER.error("Customer data has empty value!!");
				System.out.println("EmptyValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(Exception e) {
				LOGGER.error("Something goes wrong in addItem service in ItemController");
				System.out.println("Exception : "+ e.getMessage()) ;
				e.printStackTrace();
			}
			
			return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/customer/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) throws NullPointerException, NoSuchCustomerExistsException, NullValueException , EmptyValueException, Exception {

			try {
				
				//LOGGER.debug("Update Customer service");
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
			    LOGGER.info("Customer with id = {} ",customerResponse.getId()," has updated successfuly :");
				return ResponseEntity.ok().body(customerResponse);
			}catch(NoSuchCustomerExistsException e) {
				LOGGER.error("No Such Customer Exists with id = {}",id);
				System.out.println("NoSuchCustomerExistsException : "+ e.getMessage());
				e.printStackTrace();
			}catch(NullPointerException e) {
				LOGGER.error("object point to null");
				System.out.println("NullPointerException : "+ e.getMessage());
				e.printStackTrace();
			}catch(NullValueException e) {
				LOGGER.error("Customer data has null value!!");
				System.out.println("NullValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(EmptyValueException e) {
				LOGGER.error("Customer data has empty value!!");
				System.out.println("EmptyValueException : "+ e.getMessage()) ;
				e.printStackTrace();
			}catch(Exception e) {
				LOGGER.error("Something goes wrong in updateCustomer service in CustomerController");
				System.out.println("Exception : "+ e.getMessage());
				e.printStackTrace();
			}
			
			return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
		
	}
}
