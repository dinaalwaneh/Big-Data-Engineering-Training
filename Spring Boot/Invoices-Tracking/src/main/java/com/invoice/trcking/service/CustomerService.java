package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer addCustomer(Customer customer);

	Customer updateCustomer(long id, Customer customer);

	Customer getCustomerById(long id);
	
	Customer getCustomerByUsername(String customerName);

}
