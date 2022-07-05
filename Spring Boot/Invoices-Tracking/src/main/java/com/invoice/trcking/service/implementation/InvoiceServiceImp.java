package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.exception.invoive.InvoiceAlreadyExistsException;
import com.invoice.trcking.exception.invoive.NoSuchInvoiceExistsException;
import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.InvoiceRepository;
import com.invoice.trcking.repository.UserRepository;
import com.invoice.trcking.service.InvoiceService;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;

import java.awt.print.Pageable;
import java.io.Console;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InvoiceServiceImp implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> getAllInvoices() {
		List<Invoice> listOfInvoices = new ArrayList<Invoice>();
	    invoiceRepository.findAll().forEach(user-> listOfInvoices.add(user));
	    return listOfInvoices;
	}

	@Override
	public Invoice addInvoice(Invoice invoice) throws InvoiceAlreadyExistsException , EmptyValueException {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		invoice.setDateOfCreate(localDateTime);
		invoice.setDateOfUpdate(localDateTime);
		if(invoice.getNumber()==0) {
			throw new EmptyValueException("Invoice date has null values!!");
    	}
		
		return invoiceRepository.save(invoice);		
	}

	@Override
	public Invoice updateInvoice(long id, Invoice newInvoiceDetails) throws NoSuchInvoiceExistsException , EmptyValueException {

		    Invoice invoice = invoiceRepository.findById(id).get();
		  
	    	if(newInvoiceDetails.getId()==0||newInvoiceDetails.getNumber()==0) {
	    		throw new EmptyValueException("updated date has empty values!!");
	    	}
	    	LocalDateTime localDateTime = LocalDateTime.now();
			invoice.setId(newInvoiceDetails.getId());
			invoice.setNumber(newInvoiceDetails.getNumber());
			invoice.setTotalAmount(newInvoiceDetails.getTotalAmount());
			invoice.setDateOfCreate(newInvoiceDetails.getDateOfCreate());
			invoice.setDateOfUpdate(localDateTime);
			invoice.setTotalPaid(newInvoiceDetails.getTotalPaid());
			invoice.setRemainingAmount(newInvoiceDetails.getRemainingAmount());
			invoice.setStatus(newInvoiceDetails.getStatus());
			invoice.setIsDeleated(newInvoiceDetails.getIsDeleated());
			invoice.setCustomer(newInvoiceDetails.getCustomer());
			invoice.setUser(newInvoiceDetails.getUser());
			return invoiceRepository.save(invoice);
	     }	


	@Override
	public Invoice getInvoiceById(long id) throws NoSuchInvoiceExistsException{
		Optional<Invoice> result = invoiceRepository.findById(id);
		if(result.isEmpty()) {
			throw new NoSuchInvoiceExistsException("No sush Invoice exist with id = "+id);
		}
		return result.get();
	}
	
	// here i  send (offset , pageSize and field to sorting according it) to findAll in invoiceRepository witch needs the PageRequest 
	// of offset and pageSize and sort by field in a DESC order :) .
	@Override
	public Page<Invoice> findInvoicesWithPaginationAndSorting(int offset,int pageSize,String field){
	        Page<Invoice> invoices = invoiceRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC,field)));
	        return  invoices;
	}

}
