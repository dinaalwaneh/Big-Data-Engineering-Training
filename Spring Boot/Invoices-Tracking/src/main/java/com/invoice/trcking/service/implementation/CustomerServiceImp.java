package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.exception.user.NoSuchUserExistsException;
import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.CustomerRepository;
import com.invoice.trcking.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> listOfCustomers = new ArrayList<Customer>();
	    customerRepository.findAll().forEach(user-> listOfCustomers.add(user));
	    return listOfCustomers;
	}

	@Override
	public Customer addCustomer(Customer customer)throws CustomerAlreadyExistsException , NullValueException , EmptyValueException {
		
		if(customer.getCustomerName()==null||customer.getPhone()==null) {
			throw new NullValueException("updated date has null values!!");
    	}
    	if(customer.getCustomerName()==""||customer.getPhone()=="") {
    		throw new EmptyValueException("updated date has empty values!!");
    	}
	
	    return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(long id, Customer newCustomerDetails)throws NoSuchCustomerExistsException , NullValueException , EmptyValueException{
		
		 Customer customer = customerRepository.findById(id).orElse(null);
	     if (customer == null)
	            throw new NoSuchCustomerExistsException("No Such Customer exists with id = "+id);
	     else {
	    	if(newCustomerDetails.getId()==null||newCustomerDetails.getCustomerName()==null||(newCustomerDetails.getPhone()==null)) {
	    		throw new NullValueException("updated date has null values!!");
	    	}
	    	if(newCustomerDetails.getId()==0||newCustomerDetails.getCustomerName()==""||newCustomerDetails.getPhone()=="") {
	    		throw new EmptyValueException("updated date has empty values!!");
	    	}
	    	customer.setId(newCustomerDetails.getId());
	 		customer.setName(newCustomerDetails.getName());
	 		customer.setCustomerName(newCustomerDetails.getCustomerName());
	 		customer.setAddress(newCustomerDetails.getAddress());
	 		customer.setPhone(newCustomerDetails.getPhone());
	 		customer.setEmail(newCustomerDetails.getEmail());
	 		customer.setEnabled(newCustomerDetails.isEnabled());
	 		return customerRepository.save(customer);
	     }	
	}

	@Override
	public Customer getCustomerById(long id) throws NoSuchCustomerExistsException{
		Optional<Customer> result = customerRepository.findById(id);
		if(result.isEmpty()) {
			throw new NoSuchCustomerExistsException("No sush customer exist with id = "+id);
		}
		return result.get();
	}
	

	@Override
	public Customer getCustomerByUsername(String customerName)throws NoSuchUserExistsException {
		Customer result = customerRepository.findByUsername(customerName);
		if(result==null) {
			throw new NoSuchUserExistsException("No sush user exist with user name = "+customerName);
		}
		return result;
		
	}

}
