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
		customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());
		customerDto.setCustomerName(customer.getCustomerName());
		customerDto.setAddress(customer.getAddress());
		customerDto.setPhone(customer.getPhone());
		customerDto.setEmail(customer.getEmail());
		customerDto.setEnabled(customer.isEnabled());
		return customerDto;
	}
	
	public Customer convertDtoToEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setAddress(customerDto.getAddress());
		customer.setPhone(customerDto.getPhone());
		customer.setEmail(customerDto.getEmail());
		customer.setEnabled(customerDto.isEnabled());
		return customer;
	}
	
}
