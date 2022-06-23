package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.Item;

public interface ItemService {

	List<Item> getAllItems();

	Item createItem(Item item);

	Item updateItem(long id, Item item);

	Item getItemById(long id);
}
