package com.hr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hr.model.Employee;
import com.hr.service.EmployeeService;
 
@RestController
public class EmployyesController {

	@Autowired 
	private EmployeeService employeeService;
	
	@GetMapping("/staticEmployees")
	public List<Employee> getAllEmployeesStctic(){
		
		List<Employee> emps = new ArrayList<Employee>();
		//Add static data :
		emps.add(new Employee(1, "Dina", "Alwaneh", 53000, "Nablus"));
		emps.add(new Employee(2, "Ahmad", "Alwaneh", 53000, "Jenin"));
		emps.add(new Employee(3, "Mohammad", "Alwaneh", 53100, "Jenin"));
		emps.add(new Employee(4, "Eleen", "Kmail", 53001, "Nablus"));
		return emps;
	}
	
	@GetMapping("/Employees")
	public List<Employee> getAllEmployees(){
		
		return employeeService.getAllEmployees();
	}
}
