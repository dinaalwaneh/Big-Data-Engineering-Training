package com.invoice.trcking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoice.trcking.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

		public Customer save(Customer  customer);
		public Customer findByUsername(String customerName);
	}

