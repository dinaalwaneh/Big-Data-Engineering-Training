package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;

public interface InvoiceItemService {

	List<InvoiceItem> getAllInvoiceItems();

	InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);

	InvoiceItem updateInvoiceItem(long id, InvoiceItem invoiceItem);

	InvoiceItem getInvoiceItemById(long id);
}
