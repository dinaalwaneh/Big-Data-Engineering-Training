package com.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.model.Employee;
import com.hr.repositories.EmployeeRepository;

//tell spring boot that's this class service :
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository ;
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
}
