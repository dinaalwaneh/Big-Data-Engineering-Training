package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;

import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.Item;
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
			Item item = itemService.getItemById(id);

			// convert entity to DTO
			ItemDto itemResponse = itemMapper.convertEntityToDto(item);

			return ResponseEntity.ok().body(itemResponse);
			
		}catch (NoSuchItemExistsException e) {
			System.out.println("NoSuchItemExistsException : "+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Exception : "+e.getMessage());
			e.printStackTrace();
		}

		return null;
		
	}
	
	@PostMapping("/add/item")
	public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) throws NullPointerException, ItemAlreadyExistsException, EmptyValueException, NullValueException, Exception {
		
		
			try {

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
	
				return new ResponseEntity<ItemDto>(itemResponse, HttpStatus.CREATED);
				
		}catch(ItemAlreadyExistsException e) {
			System.out.println("ItemAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullValueException e) {
			System.out.println("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		
	}
	
	@PutMapping("/put/item/{id}")
	public ResponseEntity<ItemDto> updateItem(@PathVariable long id, @RequestBody ItemDto itemDto)  throws NullPointerException, NoSuchItemExistsException, EmptyValueException, NullValueException, Exception {
		
		try {

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

			return ResponseEntity.ok().body(itemResponse);

			
		}catch(NoSuchItemExistsException e) {
			System.out.println("NoSuchItemExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullValueException e) {
			System.out.println("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
	
	return new ResponseEntity<ItemDto>(HttpStatus.BAD_REQUEST);
		
	}
}
