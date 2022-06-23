package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.Invoice;

public interface InvoiceService {

	List<Invoice> getAllInvoices();

	Invoice createInvoice(Invoice invoice);

	Invoice updateInvoice(long id, Invoice invoice);

	Invoice getInvoiceById(long id);
}
