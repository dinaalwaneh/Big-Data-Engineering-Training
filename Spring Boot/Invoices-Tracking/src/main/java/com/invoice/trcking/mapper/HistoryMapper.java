package com.invoice.trcking.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.HistoryDto;
import com.invoice.trcking.dto.InvoiceItemDto;
import com.invoice.trcking.model.History;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.model.InvoiceItemKey;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.model.User;
import com.invoice.trcking.service.implementation.InvoiceServiceImp;
import com.invoice.trcking.service.implementation.ItemServiceImp;
import com.invoice.trcking.service.implementation.UserServiceImpl;

@Component
public class HistoryMapper {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private InvoiceServiceImp invoiceServiceImp;
	
	public HistoryDto convertEntityToDto(History history) {
		HistoryDto historyDto = new HistoryDto();
		historyDto.setId(history.getId());
		historyDto.setInvoiceId(history.getInvoice().getId());
		historyDto.setUserName(history.getUser().getUserName());
		historyDto.setDateOfUpdate(history.getDateOfUpdate());
		return historyDto;
	}
	
	public History convertDtoToEntity(HistoryDto historyDto) {
		History history = new History();
		history.setId(historyDto.getId());
		User user =userServiceImpl.getUserByUsername(historyDto.getUserName());
		history.setUser(user);
		Invoice invoice =invoiceServiceImp.getInvoiceById(historyDto.getInvoiceId());
		history.setInvoice(invoice);
		history.setDateOfUpdate(historyDto.getDateOfUpdate());
		return history;
	}
}
