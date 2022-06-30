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
import com.invoice.trcking.exception.user.NoSuchUserExistsException;
import com.invoice.trcking.exception.user.UserAlreadyExistsException;
import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.UserRepository;
import com.invoice.trcking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public List<User> getAllUsers() {
		List<User> listOfUsers = new ArrayList<User>();
	    userRepository.findAll().forEach(user-> listOfUsers.add(user));
	    return listOfUsers;
	}

	@Override
	public User createUser(User user)throws UserAlreadyExistsException , NullValueException , EmptyValueException {
		if(user.getUserName()==null||user.getPhone()==null||user.getPassword()==null) {
			throw new NullValueException("added date has null values!!");
		}
		if(user.getUserName()==""||user.getPhone()==""||user.getPassword()=="") {
			throw new EmptyValueException("added date has empty values!!");
		}
		//User existingUser= userRepository.findByUsername(user.getUserName());
		//if (existingUser == null) {
			return userRepository.save(user);
	     //}else throw new UserAlreadyExistsException("User already exixts!!");
	}

	@Override
	public User updateUser(long id, User newUserDetails) throws NoSuchUserExistsException , NullValueException , EmptyValueException{
 	
		User user = userRepository.findById(id).orElse(null);
	     if (user == null)
	            throw new NoSuchUserExistsException("No Such User exists with id = "+id);
	     else {
	    	if(newUserDetails.getId()==null||newUserDetails.getUserName()==null||(newUserDetails.getPhone()==null||newUserDetails.getPassword()==null)) {
	    		throw new NullValueException("updated date has null values!!");
	    	}
	    	if(newUserDetails.getId()==0||newUserDetails.getUserName()==""||newUserDetails.getPhone()==""||newUserDetails.getPassword()=="") {
	    		throw new EmptyValueException("updated date has empty values!!");
	    	}

			//.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
			user.setId(newUserDetails.getId());
			user.setName(newUserDetails.getName());
			user.setUserName(newUserDetails.getUserName());
			user.setAddress(newUserDetails.getAddress());
			user.setPhone(newUserDetails.getPhone());
			user.setEmail(newUserDetails.getEmail());
			user.setEnabled(newUserDetails.isEnabled());
			user.setPassword(newUserDetails.getPassword());
			return userRepository.save(user);
	     }	
	}

	@Override
	public User getUserById(long id)throws NoSuchUserExistsException {
		Optional<User> result = userRepository.findById(id);
		if(result.isEmpty()) {
			throw new NoSuchUserExistsException("No sush user exist with id = "+id);
		}
		return result.get();
		/*if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("Post", "id", id);
		}*/
		
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		//return post;
	}
	

	@Override
	public User getUserByUsername(String username)throws NoSuchUserExistsException {
		User result = userRepository.findByUsername(username);
		if(result==null) {
			throw new NoSuchUserExistsException("No sush user exist with user name = "+username);
		}
		return result;
		
	}
	

}
