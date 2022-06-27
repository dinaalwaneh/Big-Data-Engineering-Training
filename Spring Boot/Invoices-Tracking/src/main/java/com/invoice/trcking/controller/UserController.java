package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.invoice.trcking.dto.CustomerDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.exception.EmptyValueException;
import com.invoice.trcking.exception.NullValueException;
import com.invoice.trcking.exception.customer.CustomerAlreadyExistsException;
import com.invoice.trcking.exception.customer.NoSuchCustomerExistsException;
import com.invoice.trcking.exception.user.NoSuchUserExistsException;
import com.invoice.trcking.exception.user.UserAlreadyExistsException;
import com.invoice.trcking.mapper.UserMapper;
import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;
import com.invoice.trcking.service.UserService;

@Controller
public class UserController {
	
/*
	@RequestMapping(value = "/add/user", method = RequestMethod.POST)
	public ResponseEntity<Object> createEmployee(@RequestBody User user)
	{
		user = userService.createUser(user);
		return new ResponseEntity<>(
				"User is created successfully with Id = " + user.getId(),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/get/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployees()
	{
		List<User> userList = userService.getUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}*/
	/********************************************************************/
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/get/users")
	public ResponseEntity<Object> getUsers()
	{
		List<UserDto> userDtos = new ArrayList<UserDto>();
		userService.getAllUsers().forEach(user -> userDtos.add(userMapper.convertEntityToDto(user)));
		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws NoSuchUserExistsException, Exception { 
		try {
			
			//LOGGER.debug("getUserById service with id = {} ",id);
			User user = userService.getUserById(id);
	
			// convert entity to DTO
			UserDto postResponse = userMapper.convertEntityToDto(user);
			
			LOGGER.info("getCustomerById service with id = {} is done successfully",id);
			return ResponseEntity.ok().body(postResponse);
		}catch (NoSuchUserExistsException e) {
			LOGGER.error("User not foundes with id = {} ",id);
			System.out.println("NoSuchUserExistsException : "+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			LOGGER.error("Something goes wrong in getUserById api at User Controller");
			System.out.println("Exception : "+e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
	
	@PostMapping("/add/user")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws NullPointerException, UserAlreadyExistsException, EmptyValueException, NullValueException, Exception {

		try {
			//LOGGER.debug("Add User service");
			if(userDto == null){
				throw new NullPointerException("userDto point to null ");
			}
			// convert DTO to entity
			User userRequest = userMapper.convertDtoToEntity(userDto);

			 
			User user = userService.createUser(userRequest);
			
			if(user == null){
				throw new NullPointerException("customer point to null");
			}

			// convert entity to DTO
			UserDto userResponse = userMapper.convertEntityToDto(user);
			LOGGER.error("User with id = {} added successfuly",userDto.getId());
			return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
			
		}catch(UserAlreadyExistsException e) {
			LOGGER.error("User with id = {} already exists in item entity",userDto.getId());
			System.out.println("UserAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			LOGGER.error("object point to null");
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullValueException e) {
			LOGGER.error("User data has null value!!");
			System.out.println("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			LOGGER.error("User data has empty value!!");
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			LOGGER.error("Something goes wrong in addUser service in UserController");
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/put/user/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto)throws NullPointerException, NoSuchCustomerExistsException, EmptyValueException, NullValueException, Exception {
		
		try {
			//LOGGER.debug("Update User service");
			if(userDto == null){
				throw new NullPointerException("userDto point to null ");
			}
			// convert DTO to Entity
			User userRequest = userMapper.convertDtoToEntity(userDto);

			User user = userService.updateUser(id, userRequest);

			if(user == null){
				throw new NullPointerException("customer point to null");
			}

			// entity to DTO
			UserDto userResponse = userMapper.convertEntityToDto(user);

		    LOGGER.info("User with id = {} ",userResponse.getId()," has updated successfuly :");
			return ResponseEntity.ok().body(userResponse);
		}catch(NoSuchCustomerExistsException e) {
			LOGGER.error("No Such User Exists with id = {}",id);
			System.out.println("NoSuchCustomerExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullPointerException e) {
			LOGGER.error("object point to null");
			System.out.println("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(NullValueException e) {
			LOGGER.error("User data has null value!!");
			System.out.println("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(EmptyValueException e) {
			LOGGER.error("User data has empty value!!");
			System.out.println("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
		}catch(Exception e) {
			LOGGER.error("Something goes wrong in updateUser service in UserController");
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		
		return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
	}
	
 
	 

	@GetMapping("/user/{username}")
	public ResponseEntity<UserDto> getUserByUserName(@PathVariable String username) {
		try {
			LOGGER.info("user name = {}",username);
			User user = userService.getUserByUsername(username);
			
			// convert entity to DTO
			UserDto postResponse = userMapper.convertEntityToDto(user);

			return ResponseEntity.ok().body(postResponse);
		}catch (Exception e) {
			LOGGER.error("Something goes wrong in getUserByUserName service in UserController");
			System.out.println("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		return null;
		
	}
	 
}
