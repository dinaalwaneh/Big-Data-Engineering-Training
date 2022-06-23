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
		userDto.setUsername(user.getUsername());
		userDto.setEnabled(user.getEnabled());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	
	public User convertDtoToEntity(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setEnabled(userDto.getEnabled());
		user.setPassword(userDto.getPassword());
		return user;
	}
}
