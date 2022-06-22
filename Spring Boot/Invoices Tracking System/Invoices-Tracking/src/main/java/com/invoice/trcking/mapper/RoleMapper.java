package com.invoice.trcking.mapper;

import org.springframework.stereotype.Component;

import com.invoice.trcking.dto.RoleDto;
import com.invoice.trcking.dto.UserDto;
import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.User;

@Component
public class RoleMapper {


	public RoleDto convertEntityToDto(Role user) {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(user.getId());
		roleDto.setName(user.getName());
		return roleDto;
	}
	
	public Role convertDtoToEntity(RoleDto roleDto) {
		Role role = new Role();
		role.setId(roleDto.getId());
		role.setName(roleDto.getName());
		return role;
	}
}
