package com.invoice.trcking.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class InvoiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private InvoiceMapper invoiceMapper;

	// in this function i will get Invoices from the database as a list of Json of type Invoice entity
	// and then take each one and convert it to invoice DTO and add it to invoiceDto list .
	@GetMapping("/invoices")
	public ResponseEntity<Object> getInvoices() throws Exception{
		try {
			List<InvoiceDto> invoiceDtos = new ArrayList<InvoiceDto>();
			invoiceService.getAllInvoices().forEach(invoice ->{invoiceDtos.add(invoiceMapper.convertEntityToDto(invoice));});
			return new ResponseEntity<>(invoiceDtos, HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	// in this function i will send invoice userName to url path and then 
    // i have called getAllInvoices funvtion from invoiceService and for each invoice returned from database i will search if it's userName == userName
	// in the path , if true return invoice as entity and then convert it to invoice DTO . 
	@GetMapping("/invoicesByUserName/{userName}")
	public ResponseEntity<Object> getInvoicesByUserName(@PathVariable String userName) throws Exception{
		try {
			List<InvoiceDto> invoiceDtos = new ArrayList<InvoiceDto>();
			invoiceService.getAllInvoices().forEach(invoice ->{
				if(invoice.getUser().getUserName().equals(userName)) {
					invoiceDtos.add(invoiceMapper.convertEntityToDto(invoice));
				}
			});
			return new ResponseEntity<>(invoiceDtos, HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// in this function i will send Invoice id to url and call getInvoiceById function from invoiceService
	// after we are received the json from invoice entity i will
	// convert it to invoice DTO and send it as a response.
	@GetMapping("/invoice/{id}")
	public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable(name = "id") Long id)throws NoSuchInvoiceExistsException, Exception {
		
		try {
			//LOGGER.debug("getInvoiceById service with id : ",id);
			Invoice invoice = invoiceService.getInvoiceById(id);
			// convert entity to DTO
			InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);
			LOGGER.info("getInvoiceById service is done successfully");
			return ResponseEntity.ok().body(invoiceResponse);
		}catch (NoSuchInvoiceExistsException e) {
			LOGGER.error("NoSuchInvoiceExistsException : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	// in this function i will send InvoiceDto to body then 
	// convert invoice DTO to entity and send it to addInvoice in invoiceService to save it in the database and return InvoiceDto as a response 
	// with status code 200 if the operation compleated.
	@PostMapping("/invoice")
	public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceDto invoiceDto) throws NullPointerException, InvoiceAlreadyExistsException, EmptyValueException, Exception {
	
		try {

			//LOGGER.debug("Add invoice service");
			if(invoiceDto == null){
				throw new NullPointerException("InvoiceDto point to null ");
			}
			// convert DTO to entity
			Invoice invoiceRequest = invoiceMapper.convertDtoToEntity(invoiceDto);

			Invoice invoice = invoiceService.addInvoice(invoiceRequest);

			if(invoice == null){
				throw new NullPointerException("invoice point to null");
			}
			// convert entity to DTO
		    InvoiceDto invoiceResponse = invoiceMapper.convertEntityToDto(invoice);
		    LOGGER.info("Invoice added to item entity successfuly");
			return new ResponseEntity<InvoiceDto>(invoiceResponse, HttpStatus.CREATED);
			
		}catch(InvoiceAlreadyExistsException e) {
			LOGGER.error("InvoiceAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}catch(NullPointerException e) {
			LOGGER.error("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}catch(EmptyValueException e) {
			LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// in this function i will send InvoiceDto to body and invoice id to url and then 
	// convert invoice DTO to entity and send it to updateInvoice in invoiceService to save it in the database.
	@PutMapping("/invoice/{id}")
	public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable long id, @RequestBody InvoiceDto invoiceDto) throws NullPointerException, NoSuchInvoiceExistsException , EmptyValueException, Exception {
		
		try {
			
			//LOGGER.debug("Update Invoice service");
			
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
			
		    LOGGER.info("Invoice with id :",invoiceResponse.getId(),"has updated successfuly :");
			return ResponseEntity.ok().body(invoiceResponse);
		}catch(NoSuchInvoiceExistsException e) {
			LOGGER.error("NoSuchInvoiceExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}catch(EmptyValueException e) {
			LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<InvoiceDto>(HttpStatus.BAD_REQUEST);
		}
		
		
	}

	// here i made the pagenation sort , so i have send (number of page'offset' , pageSize and field to sorted data according it) to
	// url path and return data according to the size i had entered in the url path and then
	// send them to findInvoicesWithPaginationAndSorting function in invoiceService class also i have convert the result
	// to invoice DTO and return it as a response .
    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private ResponseEntity<Object> getInvoicesWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) throws Exception {
	   
    	try {
		   List<InvoiceDto> invoiceDtos = new ArrayList<InvoiceDto>();
		   Page<Invoice> InvoicesWithPagination = invoiceService.findInvoicesWithPaginationAndSorting(offset, pageSize, field);
		   InvoicesWithPagination.forEach(invoice ->{invoiceDtos.add(invoiceMapper.convertEntityToDto(invoice));});
	       return  ResponseEntity.ok().body(invoiceDtos);
	    }catch(Exception e) {
	    	LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	   
    }
  
   // this function for upload file so after upload the file i will getOriginalFilename and stor it in fileName 
   // then i will transfer it to images file in the project and return text response with status code 200 . 
   @PostMapping("/upload") 
   public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file ) {

	    String fileName = file.getOriginalFilename();
	    try {
	    	   file.transferTo( new File("C:\\Users\\hp\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\Invoices-Tracking\\src\\main\\resources\\static\\images\\" + fileName));
	    } catch (Exception e) {
		      LOGGER.error("Exception : "+ e.getMessage()) ;
			  e.printStackTrace();
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	    return ResponseEntity.ok("File uploaded successfully.");
    }
  
}
