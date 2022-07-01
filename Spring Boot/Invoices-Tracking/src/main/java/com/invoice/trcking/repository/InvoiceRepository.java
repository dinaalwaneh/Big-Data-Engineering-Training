package com.invoice.trcking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoice.trcking.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	//Page<Invoice> findAll(PageRequest withSort);

	//public Invoice save(Invoice invoice);
	//public Invoice findByInvoice(String name);

}
