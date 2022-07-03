package com.invoice.trcking.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.CustomerRepository;
import com.invoice.trcking.repository.InvoiceRepository;
import com.invoice.trcking.service.CustomerService;
import com.invoice.trcking.service.UserService;

@Component
public class InvoiceMapper {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;

	public InvoiceDto convertEntityToDto(Invoice invoice) {
		InvoiceDto invoiceDto = new InvoiceDto();
		invoiceDto.setId(invoice.getId());
		invoiceDto.setNumber(invoice.getNumber());
		invoiceDto.setTotalAmount(invoice.getTotalAmount());
		invoiceDto.setDateOfCreate(invoice.getDateOfCreate());
		invoiceDto.setDateOfUpdate(invoice.getDateOfUpdate());
		invoiceDto.setTotalPaid(invoice.getTotalPaid());
		invoiceDto.setRemainingAmount(invoice.getRemainingAmount());
		invoiceDto.setStatus(invoice.getStatus());
		if(invoice.getUser()!=null) {
			invoiceDto.setUserName(invoice.getUser().getUserName());
		}else {
			invoiceDto.setUserName(null);
		}
		if(invoice.getCustomer()!=null) {
			invoiceDto.setCustomerName(invoice.getCustomer().getCustomerName());
		}else {
			invoiceDto.setCustomerName(null);
		}
		
		invoiceDto.setIsDeleated(invoice.getIsDeleated());
		invoiceDto.setFileName(invoice.getFileName());
		return invoiceDto;
	}
	
	public Invoice convertDtoToEntity(InvoiceDto invoiceDto) {
		Invoice invoice = new Invoice();
		invoice.setId(invoiceDto.getId());
		invoice.setNumber(invoiceDto.getNumber());
		invoice.setTotalAmount(invoiceDto.getTotalAmount());
		invoice.setDateOfCreate(invoiceDto.getDateOfCreate());
		invoice.setDateOfUpdate(invoiceDto.getDateOfUpdate());
		invoice.setTotalPaid(invoiceDto.getTotalPaid());
		invoice.setRemainingAmount(invoiceDto.getRemainingAmount());
		invoice.setStatus(invoiceDto.getStatus());
		invoice.setIsDeleated(invoiceDto.getIsDeleated());
		if(invoiceDto.getCustomerName()!=null) {
			invoice.setCustomer(customerService.getCustomerByUsername(invoiceDto.getCustomerName()));
		}
		if(invoiceDto.getUserName()!=null) {
			invoice.setUser(userService.getUserByUsername(invoiceDto.getUserName()));
		}
		
		invoice.setFileName(invoiceDto.getFileName());
		return invoice;
	}
}
