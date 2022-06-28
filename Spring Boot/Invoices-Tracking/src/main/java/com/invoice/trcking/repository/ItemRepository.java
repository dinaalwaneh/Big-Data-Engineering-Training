package com.invoice.trcking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoice.trcking.model.Item;
import com.invoice.trcking.model.User;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

	public Item findByName(String name);

}
