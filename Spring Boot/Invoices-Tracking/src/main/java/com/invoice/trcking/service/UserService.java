package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.User;

public interface UserService {
	
	List<User> getAllUsers();

	User createUser(User user);

	User updateUser(long id, User user);

	User getUserById(long id);
	
	User getUserByUsername(String username);
	
}
