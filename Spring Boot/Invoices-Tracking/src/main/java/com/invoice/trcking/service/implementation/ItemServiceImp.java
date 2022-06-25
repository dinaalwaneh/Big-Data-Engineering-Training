package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.Item;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.ItemRepository;
import com.invoice.trcking.repository.UserRepository;
import com.invoice.trcking.service.InvoiceService;
import com.invoice.trcking.service.ItemService;

@Service
public class ItemServiceImp implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		List<Item> listOfItems = new ArrayList<Item>();
		itemRepository.findAll().forEach(user-> listOfItems.add(user));
	    return listOfItems;

	}

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item updateItem(long id, Item newItemDetails) {
		Item item = itemRepository.findById(id).get();
		
		//.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
	
		item.setId(newItemDetails.getId());
		item.setName(newItemDetails.getName());
		item.setAmmount(newItemDetails.getAmmount());
		item.setQuantity(newItemDetails.getQuantity());
		item.setIsDeleated(newItemDetails.getIsDeleated());
		return itemRepository.save(item);
	}

	@Override
	public Item getItemById(long id) {
		Optional<Item> result = itemRepository.findById(id);
		return result.get();
	}

}
