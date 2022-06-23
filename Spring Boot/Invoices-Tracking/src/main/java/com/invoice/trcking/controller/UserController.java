package com.invoice.trcking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.mapper.UserMapper;
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
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
		User user = userService.getUserById(id);

		// convert entity to DTO
		UserDto postResponse = userMapper.convertEntityToDto(user);

		return ResponseEntity.ok().body(postResponse);
	}
	
	@PutMapping("/add/user")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		// convert DTO to entity
		User userRequest = userMapper.convertDtoToEntity(userDto);

		User user = userService.createUser(userRequest);

		// convert entity to DTO
		UserDto userResponse = userMapper.convertEntityToDto(user);

		return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/put/user/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {

		// convert DTO to Entity
		User userRequest = userMapper.convertDtoToEntity(userDto);


		User user = userService.updateUser(id, userRequest);

		// entity to DTO
		UserDto userResponse = userMapper.convertEntityToDto(user);

		return ResponseEntity.ok().body(userResponse);
	}
 
	 
}
