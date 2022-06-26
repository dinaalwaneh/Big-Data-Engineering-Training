package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.exception.item.ItemAlreadyExistsException;
import com.invoice.trcking.exception.item.NoSuchItemExistsException;
import com.invoice.trcking.model.Customer;
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
	public Item addItem(Item item) throws ItemAlreadyExistsException , NullValueException , EmptyValueException {
		if(item.getName()==null) {
			throw new NullValueException("Item name date has null values!!");
    	}
    	if(item.getId()==0||item.getName()==""||item.getAmmount()==0) {
    		throw new EmptyValueException("added date has empty values!!");
    	}
		Item existingItem = itemRepository.findById(item.getId()).orElse(null); 
		if (existingItem == null) {
			return itemRepository.save(item);
	     }else throw new CustomerAlreadyExistsException("Item already exixts!!");		
	}

	@Override
	public Item updateItem(long id, Item newItemDetails) throws NoSuchItemExistsException , NullValueException , EmptyValueException{
		
		 Item item = itemRepository.findById(id).orElse(null);
	     if (item == null)
	            throw new NoSuchItemExistsException("No Such Item exists with id = "+id);
	     else {
	    	if(newItemDetails.getName()==null) {
	    		throw new NullValueException("updated date has null values!!");
	    	}
	    	if(newItemDetails.getId()==0||newItemDetails.getName()==""||newItemDetails.getAmmount()==0) {
	    		throw new EmptyValueException("updated date has empty values!!");
	    	}
	    	item.setId(newItemDetails.getId());
			item.setName(newItemDetails.getName());
			item.setAmmount(newItemDetails.getAmmount());
			item.setQuantity(newItemDetails.getQuantity());
			item.setDiscription(newItemDetails.getDiscription());
			item.setIsDeleated(newItemDetails.getIsDeleated());
			return itemRepository.save(item);
		}
	}	

	@Override
	public Item getItemById(long id)throws NoSuchItemExistsException {
		Optional<Item> result = itemRepository.findById(id);
		if(result.isEmpty()) {
			throw new NoSuchItemExistsException("No sush Item exist with id = "+id);
		}
		return result.get();
	}

}
