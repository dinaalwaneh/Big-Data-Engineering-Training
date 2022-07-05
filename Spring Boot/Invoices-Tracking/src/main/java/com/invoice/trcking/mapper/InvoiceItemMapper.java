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
		// here i send item name to getItemByName function in itemServiceImp to received object from item that has this name and send it to setItem function .
		Item item =itemServiceImp.getItemByName(invoiceItemDto.getItemName());
		invoiceItem.setItem(item);
		// here i send invoice id to getInvoiceById function in invoiceServiceImp to received object from invoice that has this id and send it to setInvoice function .
		invoiceItem.setInvoice(invoiceServiceImp.getInvoiceById(invoiceItemDto.getInvoiceId()));
		InvoiceItemKey invoiceItemKey = new InvoiceItemKey(invoiceItem.getInvoice().getId(),invoiceItem.getItem().getId());
		invoiceItem.setId(invoiceItemKey);
		invoiceItem.setQuantity(invoiceItemDto.getQuantity());
		// here i have calculate the total amont for the order by get the amount of one item from item "item.getAmmount()" and multiply it with quantity.
		// to send it to setAmount function in invoiceItem .
		long totaAmount = invoiceItem.getQuantity()*item.getAmmount();
		invoiceItem.setAmount(totaAmount);
		return invoiceItem;
	}
}
