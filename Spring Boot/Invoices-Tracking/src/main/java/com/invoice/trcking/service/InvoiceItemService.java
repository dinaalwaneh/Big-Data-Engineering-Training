package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;

public interface InvoiceItemService {

	List<InvoiceItem> getAllInvoices();

	InvoiceItem createInvoice(InvoiceItem invoiceItem);

	InvoiceItem updateInvoice(long id, InvoiceItem invoiceItem);

	InvoiceItem getInvoiceById(long id);
}
