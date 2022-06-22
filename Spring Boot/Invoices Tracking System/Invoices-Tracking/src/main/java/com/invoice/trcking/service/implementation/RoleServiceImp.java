package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.RoleRepository;
import com.invoice.trcking.repository.UserRepository;
import com.invoice.trcking.service.RoleService;

@Service
public class RoleServiceImp implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getRoles() {
		List<Role> listOfRoles = new ArrayList<Role>();
	    roleRepository.findAll().forEach(user-> listOfRoles.add(user));
	    return listOfRoles;
	}
	
	

}
