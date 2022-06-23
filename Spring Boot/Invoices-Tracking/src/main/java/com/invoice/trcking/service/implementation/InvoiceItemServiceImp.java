package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.InvoiceItemRepository;
import com.invoice.trcking.service.InvoiceItemService;

public class InvoiceItemServiceImp implements InvoiceItemService {
	
	
	@Autowired
	private InvoiceItemRepository invoiceItemRepository;
	@Override
	public List<InvoiceItem> getAllInvoices() {
		List<InvoiceItem> listOfInvoiceItems = new ArrayList<InvoiceItem>();
		invoiceItemRepository.findAll().forEach(invoiceItem-> listOfInvoiceItems.add(invoiceItem));
	    return listOfInvoiceItems;
	}

	@Override
	public InvoiceItem createInvoice(InvoiceItem invoiceItem) {
		return invoiceItemRepository.save(invoiceItem);
	}

	@Override
	public InvoiceItem updateInvoice(long id, InvoiceItem newInvoiceItem) {
		InvoiceItem invoiceItem = invoiceItemRepository.findById(id).get();
		
		//.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		invoiceItem.setId(newInvoiceItem.getId());
		invoiceItem.setInvoice(newInvoiceItem.getInvoice());
		invoiceItem.setItem(newInvoiceItem.getItem());
		invoiceItem.setQuantity(newInvoiceItem.getQuantity());
		return invoiceItemRepository.save(invoiceItem);
	}

	@Override
	public InvoiceItem getInvoiceById(long id) {
		Optional<InvoiceItem> result = invoiceItemRepository.findById(id);
		return result.get();
	}

}
