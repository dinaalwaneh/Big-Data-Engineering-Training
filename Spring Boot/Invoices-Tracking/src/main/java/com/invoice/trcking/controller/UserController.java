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

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	// in this function i will getUsers from the database as a list of Json of type user entity
	// and then take each one and convert it to user DTO and add it to userDtos list .
	@GetMapping("/users")
	public ResponseEntity<Object> getUsers() throws Exception{
		try {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			userService.getAllUsers().forEach(user -> userDtos.add(userMapper.convertEntityToDto(user)));
			return new ResponseEntity<>(userDtos, HttpStatus.OK);
		}catch (Exception e) {
			LOGGER.error("NoSuchUserExistsException : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	// in this function i will getUserById from the database as a Json of type customer entity by send user id to url path
	// and call getUserById function in userService class , after we received the user we will convert it to user DTO .
	@GetMapping("/userr/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws NoSuchUserExistsException, Exception { 
		try {
			
			//LOGGER.debug("getUserById service with id = {} ",id);
			User user = userService.getUserById(id);
	
			// convert entity to DTO
			UserDto postResponse = userMapper.convertEntityToDto(user);
			
			LOGGER.info("getCustomerById service with id = {} is done successfully",id);
			return ResponseEntity.ok().body(postResponse);
		}catch (NoSuchUserExistsException e) {
			LOGGER.error("NoSuchUserExistsException : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			LOGGER.error("Exception : "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// in this function i will send UserDto to body then 
	// convert user DTO to entity and send it to createUser in userService to save it in the database abd return user Dto as a response with status 200.
	@PostMapping("/user")
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
			LOGGER.error("UserAlreadyExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(NullPointerException e) {
			LOGGER.error("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(NullValueException e) {
			LOGGER.error("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(EmptyValueException e) {
			LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	// in this function i will send UserDto to body and user id to url and then 
	// convert user DTO to entity and send it to updateUser in userService to save it in the database.
	@PutMapping("/user/{id}")
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
			LOGGER.error("NoSuchCustomerExistsException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(NullPointerException e) {
			LOGGER.error("NullPointerException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(NullValueException e) {
			LOGGER.error("NullValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(EmptyValueException e) {
			LOGGER.error("EmptyValueException : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
		
		
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
			LOGGER.error("Exception : "+ e.getMessage()) ;
			e.printStackTrace();
		}
		return null;
		
	}
	 
}
