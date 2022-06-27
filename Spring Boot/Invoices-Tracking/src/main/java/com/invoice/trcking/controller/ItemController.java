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

	@GetMapping("/get/items")
	public ResponseEntity<Object> getItems()
	{
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		itemService.getAllItems().forEach(item -> itemDtos.add(itemMapper.convertEntityToDto(item)));
		return new ResponseEntity<>(itemDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/item/{id}")
	public ResponseEntity<ItemDto> getItemById(@PathVariable Long id)throws NoSuchItemExistsException, Exception {
		
		try {
			
			//LOGGER.debug("getItemById service with id : ",id);
			Item item = itemService.getItemById(id);
 
			// convert entity to DTO
			ItemDto itemResponse = itemMapper.convertEntityToDto(item);
			
			LOGGER.info("getItemById service with id = {} is done successfully",id);
			return ResponseEntity.ok().body(itemResponse);
			
		}catch (NoSuchItemExistsException e) {
			LOGGER.error("Item not foundes with id = {} ",id);
			System.out.println("NoSuchItemExistsException : "+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			LOGGER.error("Something goes wrong in getItemById api at ItemController");
			System.out.println("Exception : "+e.getMessage());
			e.printStackTrace();
		}

		return null;
		
	}
	
	@PostMapping("/add/item")
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
			LOGGER.error("Item with id = {} already exists in item entity",itemDto.getId());
			System.out.println("ItemAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			LOGGER.error("object point to null");
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullValueException e) {
			LOGGER.error("Item data has null value!!");
			System.out.println("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			LOGGER.error("item data has empty value!!");
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			LOGGER.error("Something goes wrong in addItem service in ItemController");
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping("/put/item/{id}")
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
			LOGGER.error("No Such Item Exists with id = {}",id);
			System.out.println("NoSuchItemExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			LOGGER.error("object point to null");
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullValueException e) {
			LOGGER.error("Item data has null value!!");
			System.out.println("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			LOGGER.error("invoice data has empty value!!");
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			LOGGER.error("Something goes wrong in updateItem service in ItemController");
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
	
	return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		
	}
}
