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

import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.mapper.InvoiceMapper;
import com.invoice.trcking.mapper.UserMapper;
import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.User;
import com.invoice.trcking.service.InvoiceService;
import com.invoice.trcking.service.UserService;

@Controller
public class InvoiceController {


	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceMapper invoiceMapper;

	@GetMapping("/get/invoices")
	public ResponseEntity<Object> getInvoices()
	{
		List<InvoiceDto> invoiceDtos = new ArrayList<InvoiceDto>();
		invoiceService.getAllInvoices().forEach(invoice -> invoiceDtos.add(invoiceMapper.convertEntityToDto(invoice)));
		return new ResponseEntity<>(invoiceDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/invoice/{id}")
	public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable(name = "id") Long id) {
		Invoice invoice = invoiceService.getInvoiceById(id);

		// convert entity to DTO
		InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);

		return ResponseEntity.ok().body(invoiceResponse);
	}
	
	@PostMapping("/add/invoice")
	public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto) {

		// convert DTO to entity
		Invoice invoiceRequest = invoiceMapper.convertDtoToEntity(invoiceDto);

		Invoice invoice = invoiceService.createInvoice(invoiceRequest);

		// convert entity to DTO
		InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);

		return new ResponseEntity<InvoiceDto>(invoiceResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/put/invoice/{id}")
	public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable long id, @RequestBody InvoiceDto invoiceDto) {

		// convert DTO to Entity
		Invoice invoiceRequest = invoiceMapper.convertDtoToEntity(invoiceDto);


		Invoice invoice = invoiceService.updateInvoice(id, invoiceRequest);

		// entity to DTO
		InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);

		return ResponseEntity.ok().body(invoiceResponse);
	}
 
}
