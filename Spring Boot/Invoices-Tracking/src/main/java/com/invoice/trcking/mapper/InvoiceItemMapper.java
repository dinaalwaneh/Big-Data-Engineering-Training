package com.invoice.trcking.mapper;

import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.InvoiceItemDto;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;

public class InvoiceItemMapper {

	
	public InvoiceItemDto convertEntityToDto(InvoiceItem invoiceItem) {
		InvoiceItemDto invoiceItemDto = new InvoiceItemDto();
		invoiceItemDto.setId(invoiceItem.getId());
		invoiceItemDto.setInvoice(invoiceItem.getInvoice());
		invoiceItemDto.setItem(invoiceItem.getItem());
		invoiceItemDto.setQuantity(invoiceItem.getQuantity());
		return invoiceItemDto;
	}
	
	public InvoiceItem convertDtoToEntity(InvoiceItemDto invoiceItemDto) {
		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setId(invoiceItemDto.getId());
		invoiceItem.setInvoice(invoiceItemDto.getInvoice());
		invoiceItem.setItem(invoiceItemDto.getItem());
		invoiceItem.setQuantity(invoiceItemDto.getQuantity());
		return invoiceItem;
	}
}
