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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.InvoiceItemDto;
import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.invoice.item.NoSuchInvoiceItemExistsException;
import com.invoice.trcking.mapper.InvoiceItemMapper;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.service.InvoiceItemService;

@Controller
public class InvoiceItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceItemController.class);

	@Autowired
	private InvoiceItemService invoiceItemService;

	@Autowired
	private InvoiceItemMapper invoiceItemMapper;

	@GetMapping("/get/invoiceitems")
	public ResponseEntity<Object> getInvoiceItems() throws Exception{
		
		try {
			List<InvoiceItemDto> invoiceItemDtos = new ArrayList<InvoiceItemDto>();
			invoiceItemService.getAllInvoiceItems().forEach(invoiceItem -> invoiceItemDtos.add(invoiceItemMapper.convertEntityToDto(invoiceItem)));
			return new ResponseEntity<>(invoiceItemDtos, HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/get/invoiceitem/{id}")
	public ResponseEntity<Object> getInvoiceItemById(@PathVariable Long id) throws NoSuchInvoiceItemExistsException{
		try {
			List<InvoiceItemDto> invoiceItemDtos = new ArrayList<InvoiceItemDto>();
			
			invoiceItemService.getAllInvoiceItems().forEach(invoiceItem ->{
				if(invoiceItem.getInvoice().getId()==id) {
					invoiceItemDtos.add(invoiceItemMapper.convertEntityToDto(invoiceItem));
				}
			});
			if(invoiceItemDtos.size()==0) {
				throw new NoSuchInvoiceItemExistsException("No such invoice itim exist with id = "+id);
			}
			return new ResponseEntity<>(invoiceItemDtos, HttpStatus.OK);
		}catch (NoSuchInvoiceItemExistsException e) {
			LOGGER.error("NoSuchInvoiceItemExistsException : "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
			
 
	}
	
	@PostMapping("/add/invoiceitem")
	public ResponseEntity<InvoiceItemDto> createInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDto) throws EmptyValueException , Exception{
		try {
			// convert DTO to entity
			InvoiceItem invoiceItemRequest = invoiceItemMapper.convertDtoToEntity(invoiceItemDto);

			InvoiceItem invoiceItem = invoiceItemService.createInvoiceItem(invoiceItemRequest);

			// convert entity to DTO
			InvoiceItemDto invoiceItemResponse = invoiceItemMapper.convertEntityToDto(invoiceItem);

			return new ResponseEntity<InvoiceItemDto>(invoiceItemResponse, HttpStatus.CREATED);
		}catch (EmptyValueException e) {
			LOGGER.error("EmptyValueException"+e.getMessage());
			return new ResponseEntity<InvoiceItemDto>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			LOGGER.error("Exception"+e.getMessage());
			return new ResponseEntity<InvoiceItemDto>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/put/invoiceitem/{id}")
	public ResponseEntity<InvoiceItemDto> updateInvoiceItem(@PathVariable long id, @RequestBody InvoiceItemDto invoiceItemDto) throws NoSuchInvoiceItemExistsException, Exception {
		
		try {
			// convert DTO to Entity
			InvoiceItem invoiceItemRequest = invoiceItemMapper.convertDtoToEntity(invoiceItemDto);
	
	
			InvoiceItem invoiceItem = invoiceItemService.updateInvoiceItem(id, invoiceItemRequest);
	
			// entity to DTO
			InvoiceItemDto invoiceItemResponse = invoiceItemMapper.convertEntityToDto(invoiceItem);
	
			return ResponseEntity.ok().body(invoiceItemResponse);
		}catch (NoSuchInvoiceItemExistsException e) {
			LOGGER.error("NoSuchInvoiceItemExistsException"+e.getMessage());
			return new ResponseEntity<InvoiceItemDto>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			LOGGER.error("Exception"+e.getMessage());
			return new ResponseEntity<InvoiceItemDto>(HttpStatus.BAD_REQUEST);
		}
			
	}
	
}
