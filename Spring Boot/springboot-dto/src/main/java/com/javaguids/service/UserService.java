package com.javaguids.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaguids.dto.UserLocationDTO;
import com.javaguids.repository.UserRepository;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.javaguids.model.*;
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<UserLocationDTO> getAllUsersLocation() {
		
		return userRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}
	
	private UserLocationDTO convertEntityToDto(User user) {
		UserLocationDTO userLocationDTO = new UserLocationDTO();
		userLocationDTO.setUserId(user.getId());
		userLocationDTO.setEmail(user.getEmail());
		userLocationDTO.setEmail(user.getEmail());
		userLocationDTO.setPlace(user.getLocation().getPlace());
		userLocationDTO.setLongitude(user.getLocation().getLongitude());
		userLocationDTO.setLatitude(user.getLocation().getLatitude());
		return userLocationDTO;
	}
	
}
