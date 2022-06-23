package com.invoice.trcking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoice.trcking.model.InvoiceItem;

@Repository
public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {

}
