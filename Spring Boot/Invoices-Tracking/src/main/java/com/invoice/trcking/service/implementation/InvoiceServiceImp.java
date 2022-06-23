package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.InvoiceRepository;
import com.invoice.trcking.repository.UserRepository;
import com.invoice.trcking.service.InvoiceService;

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
	public Invoice createInvoice(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice updateInvoice(long id, Invoice newInvoiceDetails) {
		Invoice invoice = invoiceRepository.findById(id).get();
		
		//.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		invoice.setId(newInvoiceDetails.getId());
		invoice.setNumber(newInvoiceDetails.getNumber());
		invoice.setAmmount(newInvoiceDetails.getAmmount());
		invoice.setDateOfCreat(newInvoiceDetails.getDateOfCreat());
		invoice.setDateOfUpdate(newInvoiceDetails.getDateOfUpdate());
		invoice.setIsDeleated(newInvoiceDetails.getIsDeleated());
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice getInvoiceById(long id) {
		Optional<Invoice> result = invoiceRepository.findById(id);
		return result.get();
		/*if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("Post", "id", id);
		}*/
		
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		//return post;
	}

}
