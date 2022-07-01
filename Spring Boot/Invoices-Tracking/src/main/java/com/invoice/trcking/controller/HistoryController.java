package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.invoice.trcking.dto.HistoryDto;
import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.InvoiceItemDto;
import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.invoive.InvoiceAlreadyExistsException;
import com.invoice.trcking.mapper.HistoryMapper;
import com.invoice.trcking.model.History;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.service.implementation.HistoryServiceImp;

@Controller
public class HistoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);

	@Autowired
	private HistoryServiceImp historyService;
	@Autowired 
	private HistoryMapper historyMapper; 
	
	@GetMapping("/get/invoicehistory/{invoiceid}")
	public ResponseEntity<Object> getInvoiceItemById(@PathVariable Long invoiceId) {
		
		List<HistoryDto> historyDto = new ArrayList<HistoryDto>();
		
		historyService.getAllInvoicesHistory().forEach(invoicehistiry ->{
			if(invoicehistiry.getInvoice().getId()==invoiceId) {
				historyDto.add(historyMapper.convertEntityToDto(invoicehistiry));
			}
			
		});
		return new ResponseEntity<>(historyDto, HttpStatus.OK);
		/*InvoiceItem invoiceItem = invoiceItemService.getInvoiceItemById(id);

		// convert entity to DTO
		InvoiceItemDto invoiceItemResponse = invoiceItemMapper.convertEntityToDto(invoiceItem);

		return ResponseEntity.ok().body(invoiceItemResponse);*/
	}
	
	@PostMapping("/add/invoicehistory")
	public ResponseEntity<HistoryDto> addInvoiceHistory(@RequestBody HistoryDto historyDto) throws NullPointerException, Exception {
	
		try {

			//LOGGER.debug("Add invoice service");
			if(historyDto == null){
				throw new NullPointerException("historyDto point to null ");
			}
			// convert DTO to entity
			History historyRequest = historyMapper.convertDtoToEntity(historyDto);

			History history = historyService.addInvoiceHistory(historyRequest);

			if(history == null){
				throw new NullPointerException("Invoice History point to null");
			}
			// convert entity to DTO
		    HistoryDto invoiceHistoryResponse = historyMapper.convertEntityToDto(history);
		    LOGGER.info("Invoice History added to history entity successfuly");
			return new ResponseEntity<HistoryDto>(invoiceHistoryResponse, HttpStatus.CREATED);
			
		}catch(NullPointerException e) {
			LOGGER.error("object point to null");
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			LOGGER.error("Something goes wrong in addInvoice service in InvoiceController");
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<HistoryDto>(HttpStatus.BAD_REQUEST);

	}
	
}
