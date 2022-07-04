package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.invoice.item.NoSuchInvoiceItemExistsException;
import com.invoice.trcking.exception.invoiceItem.InvoiceItemAlreadyExistsException;
import com.invoice.trcking.exception.invoive.InvoiceAlreadyExistsException;
import com.invoice.trcking.exception.invoive.NoSuchInvoiceExistsException;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.InvoiceItemRepository;
import com.invoice.trcking.service.InvoiceItemService;

@Service
public class InvoiceItemServiceImp implements InvoiceItemService {
	
	
	@Autowired
	private InvoiceItemRepository invoiceItemRepository;
	@Override
	public List<InvoiceItem> getAllInvoiceItems() {
		List<InvoiceItem> listOfInvoiceItems = new ArrayList<InvoiceItem>();
		invoiceItemRepository.findAll().forEach(invoiceItem-> listOfInvoiceItems.add(invoiceItem));
	    return listOfInvoiceItems;
	}

	@Override
	public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) throws InvoiceItemAlreadyExistsException , EmptyValueException {
		
		if(invoiceItem.getQuantity()==0) {
			throw new EmptyValueException("Item quantity must be mor than zero!!");
    	}
		return invoiceItemRepository.save(invoiceItem);
	}

	@Override
	public InvoiceItem updateInvoiceItem(long id, InvoiceItem newInvoiceItem) {
		InvoiceItem invoiceItem = invoiceItemRepository.findById(id).get();
		
		invoiceItem.setId(newInvoiceItem.getId());
		invoiceItem.setInvoice(newInvoiceItem.getInvoice());
		invoiceItem.setItem(newInvoiceItem.getItem());
		invoiceItem.setQuantity(newInvoiceItem.getQuantity());
		return invoiceItemRepository.save(invoiceItem);
	}

	@Override
	public InvoiceItem getInvoiceItemById(long id) throws NoSuchInvoiceItemExistsException{
		Optional<InvoiceItem> result = invoiceItemRepository.findById(id);
		if(result.isEmpty()) {
			throw new NoSuchInvoiceItemExistsException("No sush Invoice Item exist with id = "+id);
		}
		return result.get();
	}

}
