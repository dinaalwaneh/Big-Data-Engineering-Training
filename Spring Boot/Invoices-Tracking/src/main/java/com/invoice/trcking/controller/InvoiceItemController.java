package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.invoice.trcking.dto.InvoiceItemDto;
import com.invoice.trcking.mapper.InvoiceItemMapper;
import com.invoice.trcking.model.InvoiceItem;
import com.invoice.trcking.service.InvoiceItemService;

@Controller
public class InvoiceItemController {

	@Autowired
	private InvoiceItemService invoiceItemService;

	@Autowired
	private InvoiceItemMapper invoiceItemMapper;

	@GetMapping("/get/invoiceitems")
	public ResponseEntity<Object> getInvoiceItems()
	{
		List<InvoiceItemDto> invoiceItemDtos = new ArrayList<InvoiceItemDto>();
		invoiceItemService.getAllInvoiceItems().forEach(invoiceItem -> invoiceItemDtos.add(invoiceItemMapper.convertEntityToDto(invoiceItem)));
		return new ResponseEntity<>(invoiceItemDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/invoiceitem/{id}")
	public ResponseEntity<Object> getInvoiceItemById(@PathVariable Long id) {
		
		List<InvoiceItemDto> invoiceItemDtos = new ArrayList<InvoiceItemDto>();
		
		invoiceItemService.getAllInvoiceItems().forEach(invoiceItem ->{
			if(invoiceItem.getInvoice().getId()==id) {
				invoiceItemDtos.add(invoiceItemMapper.convertEntityToDto(invoiceItem));
			}
			
		});
		return new ResponseEntity<>(invoiceItemDtos, HttpStatus.OK);
		/*InvoiceItem invoiceItem = invoiceItemService.getInvoiceItemById(id);

		// convert entity to DTO
		InvoiceItemDto invoiceItemResponse = invoiceItemMapper.convertEntityToDto(invoiceItem);

		return ResponseEntity.ok().body(invoiceItemResponse);*/
	}
	
	@PostMapping("/add/invoiceitem")
	public ResponseEntity<InvoiceItemDto> createInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDto) {

		// convert DTO to entity
		InvoiceItem invoiceItemRequest = invoiceItemMapper.convertDtoToEntity(invoiceItemDto);

		InvoiceItem invoiceItem = invoiceItemService.createInvoiceItem(invoiceItemRequest);

		// convert entity to DTO
		InvoiceItemDto invoiceItemResponse = invoiceItemMapper.convertEntityToDto(invoiceItem);

		return new ResponseEntity<InvoiceItemDto>(invoiceItemResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/put/invoiceitem/{id}")
	public ResponseEntity<InvoiceItemDto> updateInvoiceItem(@PathVariable long id, @RequestBody InvoiceItemDto invoiceItemDto) {

		// convert DTO to Entity
		InvoiceItem invoiceItemRequest = invoiceItemMapper.convertDtoToEntity(invoiceItemDto);


		InvoiceItem invoiceItem = invoiceItemService.updateInvoiceItem(id, invoiceItemRequest);

		// entity to DTO
		InvoiceItemDto invoiceItemResponse = invoiceItemMapper.convertEntityToDto(invoiceItem);

		return ResponseEntity.ok().body(invoiceItemResponse);
	}
	
}
