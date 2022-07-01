package com.invoice.trcking.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.invoive.NoSuchInvoiceExistsException;
import com.invoice.trcking.model.History;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.repository.HistoryRepository;
import com.invoice.trcking.repository.InvoiceRepository;
import com.invoice.trcking.service.HistoryService;

@Service
public class HistoryServiceImp implements HistoryService {

	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public List<History> getAllInvoicesHistory() {
		List<History> listOfInvoicesHistory = new ArrayList<History>();
		historyRepository.findAll().forEach(invoiceHistory -> listOfInvoicesHistory.add(invoiceHistory));
	    return listOfInvoicesHistory;
	}

	@Override
	public History addInvoiceHistory(History history) {
		LocalDateTime localDateTime = LocalDateTime.now();
		history.setDateOfUpdate(localDateTime);
		//Invoice existingInvoice = invoiceRepository.fi(invoice.getId()).orElse(null); 
		//if (existingInvoice == null) {
		return historyRepository.save(history);
	    // }else throw new CustomerAlreadyExistsException("Invoice already exixts!!");		
	}

}
