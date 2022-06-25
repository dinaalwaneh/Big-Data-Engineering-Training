package com.invoice.trcking.mapper;

import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.User;

@Component
public class UserMapper {

	
	public UserDto convertEntityToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserName(user.getUserName());
		userDto.setAddress(user.getAddress());
		userDto.setPhone(user.getPhone());
		userDto.setEmail(user.getEmail());
		userDto.setEnabled(user.isEnabled());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	
	public User convertDtoToEntity(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setUserName(userDto.getUserName());
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setEmail(userDto.getEmail());
		user.setEnabled(userDto.isEnabled());
		user.setPassword(userDto.getPassword());
		return user;
	}
}
