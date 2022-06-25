package com.invoice.trcking.mapper;

import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.ItemDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.model.User;

@Component
public class ItemMapper {

	public ItemDto convertEntityToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setName(item.getName());
		itemDto.setAmmount(item.getAmmount());
		itemDto.setQuantity(item.getQuantity());
		itemDto.setDiscription(item.getDiscription());
		itemDto.setDeleated(item.getIsDeleated());
		return itemDto;
	}
	
	public Item convertDtoToEntity(ItemDto itemDto) {
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setName(itemDto.getName());
		item.setAmmount(itemDto.getAmmount());
		item.setQuantity(itemDto.getQuantity());
		item.setDiscription(itemDto.getDiscription());
		item.setIsDeleated(itemDto.isDeleated());
		return item;
	}
}
