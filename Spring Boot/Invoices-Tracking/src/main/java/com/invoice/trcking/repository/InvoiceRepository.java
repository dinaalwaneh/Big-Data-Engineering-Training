package com.invoice.trcking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoice.trcking.model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

	//public Invoice save(Invoice invoice);
	//public Invoice findByInvoice(String name);

}
