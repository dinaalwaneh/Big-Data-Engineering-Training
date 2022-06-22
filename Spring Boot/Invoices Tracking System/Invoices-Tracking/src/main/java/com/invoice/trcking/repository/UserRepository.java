package com.invoice.trcking.repository;

import com.invoice.trcking.model.*;


import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User save(User user);
	public User findByUsername(String username);
}