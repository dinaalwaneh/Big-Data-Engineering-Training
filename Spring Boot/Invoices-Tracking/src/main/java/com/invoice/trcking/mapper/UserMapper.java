package com.invoice.trcking.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.RoleType;
import com.invoice.trcking.model.User;
import com.invoice.trcking.service.implementation.RoleServiceImp;
import com.invoice.trcking.service.implementation.UserServiceImpl;

@Component
public class UserMapper {

	
	@Autowired
	private RoleServiceImp roleServiceImp;
	
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
		userDto.setRoleName(user.getRole().getName().name());
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
		// here i send customer roleType to getRoleByRoleName function in roleServiceImp to received object from role that has the same type and send it to setRole function .
		user.setRole(roleServiceImp.getRoleByRoleName(RoleType.valueOf(userDto.getRoleName())));
		return user;
	}
}
