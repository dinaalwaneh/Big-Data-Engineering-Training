package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.model.Customer;
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
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(long id, Customer newCustomerDetails) {
		Customer customer = customerRepository.findById(id).get();
	
		//.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		customer.setBookId(newCustomerDetails.getBookId());
		customer.setAuthor(newCustomerDetails.getAuthor());
		customer.setBookName(newCustomerDetails.getBookName());
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(long id) {
		Optional<Customer> result = customerRepository.findById(id);
		return result.get();
		/*if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("Post", "id", id);
		}*/
		
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		//return post;
	}

}
