package com.book.pages.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.pages.model.Employee;
import com.book.pages.model.Role;
import com.book.pages.model.RoleName;
import com.book.pages.repositores.EmployeeRepository;
import com.book.pages.repositores.RoleRepository;


@RestController
public class AppControler {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@PostMapping("/add/role")
	public void save(@RequestBody Role role) {
		roleRepository.save(role);
	}
	
	@PostMapping("/add/employee")
	public void save(@RequestBody Employee employee) {
		employeeRepository.save(employee);
	}
}
