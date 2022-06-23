package com.invoice.trcking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.User;
import com.invoice.trcking.service.RoleService;
import com.invoice.trcking.service.UserService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/add/role", method = RequestMethod.POST)
	public ResponseEntity<Object> createRole(@RequestBody Role role)
	{
		role = roleService.createRole(role);
		return new ResponseEntity<>(
				"User is created successfully with Id = " + role.getId(),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/get/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> getRoles()
	{
		List<Role> roleList = roleService.getRoles();
		return new ResponseEntity<>(roleList, HttpStatus.OK);
	}

}
