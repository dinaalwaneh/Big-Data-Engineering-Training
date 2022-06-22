package com.invoice.trcking.mapper;

import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;

@Component
public class CustomerMapper {


	public CustomerDto convertEntityToDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setBookId(customer.getBookId());
		customerDto.setAuthor(customer.getAuthor());
		customerDto.setBookName(customer.getBookName());
		return customerDto;
	}
	
	public Customer convertDtoToEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setBookId(customerDto.getBookId());
		customer.setAuthor(customerDto.getAuthor());
		customer.setBookName(customerDto.getBookName());
		return customer;
	}
	
}
