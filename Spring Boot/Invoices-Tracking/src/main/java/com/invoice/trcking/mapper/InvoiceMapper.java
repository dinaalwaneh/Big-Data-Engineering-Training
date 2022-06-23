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
		invoiceDto.setAmmount(invoice.getAmmount());
		invoiceDto.setDateOfCreat(invoice.getDateOfCreat());
		invoiceDto.setDateOfUpdate(invoice.getDateOfUpdate());
		invoiceDto.setUserName(invoice.getUser().getName());
		invoiceDto.setCustomerName(invoice.getCustomer().getBookName());
		invoiceDto.setIsDeleated(invoice.getIsDeleated());
		return invoiceDto;
	}
	
	public Invoice convertDtoToEntity(InvoiceDto invoiceDto) {
		Invoice invoice = new Invoice();
		invoice.setId(invoiceDto.getId());
		invoice.setNumber(invoiceDto.getNumber());
		invoice.setAmmount(invoiceDto.getAmmount());
		invoice.setDateOfCreat(invoiceDto.getDateOfCreat());
		invoice.setDateOfUpdate(invoiceDto.getDateOfUpdate());
		invoice.setIsDeleated(invoiceDto.getIsDeleated());
		return invoice;
	}
}
