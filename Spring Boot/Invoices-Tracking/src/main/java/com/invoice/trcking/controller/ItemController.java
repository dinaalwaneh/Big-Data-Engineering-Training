package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;

import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.Item;

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

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.dto.ItemDto;
import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.exception.item.ItemAlreadyExistsException;
import com.invoice.trcking.exception.item.NoSuchItemExistsException;
import com.invoice.trcking.mapper.ItemMapper;
import com.invoice.trcking.service.ItemService;

@Controller
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemMapper itemMapper;

	// in this function i will get Items from the database as a list of Json of type Items entity
	// and then take each one and convert it to Item DTO and add it to itemDtos list and return the list as a response with status code 200.
	@GetMapping("/items")
	public ResponseEntity<Object> getItems() throws Exception{
		
		try {
			List<ItemDto> itemDtos = new ArrayList<ItemDto>();
			itemService.getAllItems().forEach(item -> itemDtos.add(itemMapper.convertEntityToDto(item)));
			return new ResponseEntity<>(itemDtos, HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// in this function i will send Item id to url and call getItemById  function in invoiceItemService after recived  json of type Item entity
	// then we will convert it to Item DTO send it as a response.
	@GetMapping("/item/{id}")
	public ResponseEntity<ItemDto> getItemById(@PathVariable Long id)throws NoSuchItemExistsException, Exception {
		
		try {
			
			//LOGGER.debug("getItemById service with id : ",id);
			Item item = itemService.getItemById(id);
 
			// convert entity to DTO
			ItemDto itemResponse = itemMapper.convertEntityToDto(item);
			
			LOGGER.info("getItemById service with id = {} is done successfully",id);
			return ResponseEntity.ok().body(itemResponse);
			
		}catch (NoSuchItemExistsException e) {
			LOGGER.error("NoSuchItemExistsException : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// in this function i will send ItemDto to body then 
	// convert ItemDto to entity and send it to addItem in itemService to save it in the database
	// and return ItemDto as a response with status code 200 if the operation completed.
	@PostMapping("/item")
	public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) throws NullPointerException, ItemAlreadyExistsException, EmptyValueException, NullValueException, Exception {
		
		
		try {
				
				//LOGGER.debug("Add item service");
				if(itemDto == null){
					throw new NullPointerException("itemDto point to null ");
				}
				// convert DTO to entity
				Item itemRequest = itemMapper.convertDtoToEntity(itemDto);
	
				Item item = itemService.addItem(itemRequest);
	
				if(item == null){
					throw new NullPointerException("item point to null");
				}
				// convert entity to DTO
				ItemDto itemResponse = itemMapper.convertEntityToDto(item);
				LOGGER.error("Item with id = {} added successfuly",itemDto.getId());
				return new ResponseEntity<ItemDto>(itemResponse, HttpStatus.CREATED);
				
		}catch(ItemAlreadyExistsException e) {
			LOGGER.error("ItemAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(NullPointerException e) {
			LOGGER.error("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(NullValueException e) {
			LOGGER.error("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(EmptyValueException e) {
			LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	// in this function i will send ItemDto to body and invoice id to url and then 
	// convert ItemDto to entity and send it to updateItem function in itemService to save it in the database.
	@PutMapping("/item/{id}")
	public ResponseEntity<ItemDto> updateItem(@PathVariable long id, @RequestBody ItemDto itemDto)  throws NullPointerException, NoSuchItemExistsException, EmptyValueException, NullValueException, Exception {
		
		try {
			//LOGGER.debug("Update Item service");
			if(itemDto == null){
				throw new NullPointerException("itemDto point to null ");
			}
			// convert DTO to entity
			Item itemRequest = itemMapper.convertDtoToEntity(itemDto);

			Item item = itemService.updateItem(id, itemRequest);

			if(item == null){
				throw new NullPointerException("item point to null");
			}
			// convert entity to DTO
			ItemDto itemResponse = itemMapper.convertEntityToDto(item);
			
		    LOGGER.info("Item with id = {} ",itemResponse.getId(),"has updated successfuly :");
        	return ResponseEntity.ok().body(itemResponse);
			
		}catch(NoSuchItemExistsException e) {
			LOGGER.error("NoSuchItemExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(NullPointerException e) {
			LOGGER.error("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(NullValueException e) {
			LOGGER.error("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(EmptyValueException e) {
			LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		}
	
	
		
	}
}
