package com.javaguids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguids.dto.UserLocationDTO;
import com.javaguids.service.UserService;
import java.util.*;
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	

	@GetMapping("/users-location")
	public List<UserLocationDTO> getAllUserLocation(){
		return userService.getAllUsersLocation();
	}
}
