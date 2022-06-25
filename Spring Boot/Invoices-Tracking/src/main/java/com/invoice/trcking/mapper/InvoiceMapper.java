package com.invoice.trcking.mapper;

import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.User;

@Component
public class InvoiceMapper {

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
		invoiceDto.setUserName(invoice.getUser().getName());
		invoiceDto.setCustomerName(invoice.getCustomer().getName());
		invoiceDto.setIsDeleated(invoice.getIsDeleated());
		invoiceDto.setCustomer(invoice.getCustomer());
		invoiceDto.setUser(invoice.getUser());
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
		invoice.setCustomer(invoiceDto.getCustomer());
		invoice.setUser(invoiceDto.getUser());
		return invoice;
	}
}
