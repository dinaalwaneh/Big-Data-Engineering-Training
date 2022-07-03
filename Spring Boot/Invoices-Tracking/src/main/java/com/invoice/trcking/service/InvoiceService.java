package com.invoice.trcking.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.invoice.trcking.model.*;

public interface InvoiceService {

	List<Invoice> getAllInvoices();

	Invoice addInvoice(Invoice invoice);

	Invoice updateInvoice(long id, Invoice invoice);

	Invoice getInvoiceById(long id);
	
    Page<Invoice> findProductsWithPaginationAndSorting(int offset,int pageSize,String field);
	    
}
