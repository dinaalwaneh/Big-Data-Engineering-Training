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

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.dto.InvoiceDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.exception.invoive.InvoiceAlreadyExistsException;
import com.invoice.trcking.exception.invoive.NoSuchInvoiceExistsException;
import com.invoice.trcking.mapper.InvoiceMapper;
import com.invoice.trcking.mapper.UserMapper;
import com.invoice.trcking.model.Customer;
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
	public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable(name = "id") Long id)throws NoSuchInvoiceExistsException, Exception {
		try {
			Invoice invoice = invoiceService.getInvoiceById(id);

			// convert entity to DTO
			InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);

			return ResponseEntity.ok().body(invoiceResponse);
		}catch (NoSuchInvoiceExistsException e) {
			System.out.println("NoSuchInvoiceExistsException : "+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Exception : "+e.getMessage());
			e.printStackTrace();
		}

		return null;	
	}
	
	@PostMapping("/add/invoice")
	public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceDto invoiceDto) throws NullPointerException, InvoiceAlreadyExistsException, EmptyValueException, Exception {
	
		try {

			if(invoiceDto == null){
				throw new NullPointerException("InvoiceDto point to null ");
			}
			// convert DTO to entity
			Invoice invoiceRequest = invoiceMapper.convertDtoToEntity(invoiceDto);

			Invoice invoice = invoiceService.addInvoice(invoiceRequest);

			if(invoice == null){
				throw new NullPointerException("customer point to null");
			}
			// convert entity to DTO
		    InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);
	
			return new ResponseEntity<InvoiceDto>(invoiceResponse, HttpStatus.CREATED);
			
		}catch(InvoiceAlreadyExistsException e) {
			System.out.println("InvoiceAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/put/invoice/{id}")
	public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable long id, @RequestBody InvoiceDto invoiceDto) throws NullPointerException, NoSuchInvoiceExistsException , EmptyValueException, Exception {
		
		try {

			if(invoiceDto == null){
				throw new NullPointerException("InvoiceDto point to null ");
			}
			// convert DTO to Entity
			Invoice invoiceRequest = invoiceMapper.convertDtoToEntity(invoiceDto);

			Invoice invoice = invoiceService.updateInvoice(id, invoiceRequest);


			if(invoice == null){
				throw new NullPointerException("customer point to null");
			}
		 
			// entity to DTO
			InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);
			
			return ResponseEntity.ok().body(invoiceResponse);
			
			
		}catch(NoSuchInvoiceExistsException e) {
			System.out.println("NoSuchInvoiceExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
	}
 
}
