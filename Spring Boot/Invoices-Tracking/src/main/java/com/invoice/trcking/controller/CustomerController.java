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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.invoice.trcking.service.CustomerService;

import lombok.extern.log4j.Log4j2;

import java.io.File;
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
	
	// in this function i will getAllCustomers from the database as a list of Json of type customer entity
	// and then take each one and convert it to customer DTO and add it to customerDtos list .
	@GetMapping("/get/customers")
	public ResponseEntity<Object> getCustomers() throws Exception{
		
		try {
			List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
			customerService.getAllCustomers().forEach(user -> customerDtos.add(customerMapper.convertEntityToDto(user)));
			return new ResponseEntity<>(customerDtos, HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	// in this function i will getCustomerById from the database as a Json of type customer entity
	// and then convert it to customer DTO .
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
				LOGGER.error("NoSuchCustomerExistsException : "+e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch (Exception e) {
				LOGGER.error("Exception : "+e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}
	
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
				LOGGER.error("CustomerAlreadyExistsException : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(NullPointerException e) {
				LOGGER.error("NullPointerException : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(NullValueException e) {
				LOGGER.error("NullValueException : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(EmptyValueException e) {
				LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				LOGGER.error("Exception : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}
			
			
	}
	
	// in this function i will send CustomerDto to body and customer id to url and then 
	// convert customer DTO to entity and send it to updateCustomer in customerService.
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
				LOGGER.error("NoSuchCustomerExistsException : "+ e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(NullPointerException e) {
				LOGGER.error("NullPointerException : "+ e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(NullValueException e) {
				LOGGER.error("NullValueException : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(EmptyValueException e) {
				LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				LOGGER.error("Exception : "+ e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
			}

	}

}
