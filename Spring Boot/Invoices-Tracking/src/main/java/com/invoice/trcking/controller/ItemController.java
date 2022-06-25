package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.invoice.trcking.dto.ItemDto;
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
	public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
		Item item = itemService.getItemById(id);

		// convert entity to DTO
		ItemDto itemResponse = itemMapper.convertEntityToDto(item);

		return ResponseEntity.ok().body(itemResponse);
	}
	
	@PostMapping("/add/item")
	public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {

		// convert DTO to entity
		Item itemRequest = itemMapper.convertDtoToEntity(itemDto);

		Item item = itemService.createItem(itemRequest);

		// convert entity to DTO
		ItemDto itemResponse = itemMapper.convertEntityToDto(item);

		return new ResponseEntity<ItemDto>(itemResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/put/item/{id}")
	public ResponseEntity<ItemDto> updateItem(@PathVariable long id, @RequestBody ItemDto itemDto) {

		// convert DTO to Entity
		Item itemRequest = itemMapper.convertDtoToEntity(itemDto);


		Item item = itemService.updateItem(id, itemRequest);

		// entity to DTO
		ItemDto itemResponse = itemMapper.convertEntityToDto(item);

		return ResponseEntity.ok().body(itemResponse);
	}
}
