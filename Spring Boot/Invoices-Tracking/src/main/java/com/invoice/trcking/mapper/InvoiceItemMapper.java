package com.invoice.trcking.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.InvoiceItemDto;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.InvoiceItemKey;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.service.implementation.InvoiceServiceImp;
import com.invoice.trcking.service.implementation.ItemServiceImp;

@Component
public class InvoiceItemMapper {

	@Autowired
	private ItemServiceImp itemServiceImp;
	
	@Autowired
	private InvoiceServiceImp invoiceServiceImp;
	
	public InvoiceItemDto convertEntityToDto(InvoiceItem invoiceItem) {
		InvoiceItemDto invoiceItemDto = new InvoiceItemDto();
		invoiceItemDto.setInvoiceId(invoiceItem.getInvoice().getId());
		invoiceItemDto.setItemName(invoiceItem.getItem().getName());
		invoiceItemDto.setQuantity(invoiceItem.getQuantity());
		invoiceItemDto.setAmount(invoiceItem.getAmount());
		return invoiceItemDto;
	}
	
	public InvoiceItem convertDtoToEntity(InvoiceItemDto invoiceItemDto) {
		InvoiceItem invoiceItem = new InvoiceItem();
		Item item =itemServiceImp.getItemByName(invoiceItemDto.getItemName());
		invoiceItem.setItem(item);
		invoiceItem.setInvoice(invoiceServiceImp.getInvoiceById(invoiceItemDto.getInvoiceId()));
		InvoiceItemKey invoiceItemKey = new InvoiceItemKey(invoiceItem.getInvoice().getId(),invoiceItem.getItem().getId());
		invoiceItem.setId(invoiceItemKey);
		invoiceItem.setQuantity(invoiceItemDto.getQuantity());
		long amount_ = invoiceItem.getQuantity()*item.getAmmount();
		invoiceItem.setAmount(amount_);
		return invoiceItem;
	}
}
