package com.book.pages.repositores;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.book.pages.model.Employee;

import com.book.pages.model.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	public Client save(Client client);
	//public Employee findByUsername(String username);
}