package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.History;
public interface HistoryService {
	
	List<History> getAllInvoicesHistory();

	History addInvoiceHistory(History history);

}
