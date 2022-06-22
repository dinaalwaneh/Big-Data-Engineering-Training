package com.book.pages.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.pages.model.Client;
import com.book.pages.model.Employee;
import com.book.pages.model.JwtRequest;
import com.book.pages.model.Role;
import com.book.pages.model.RoleName;
import com.book.pages.repositores.ClientRepository;
import com.book.pages.repositores.EmployeeRepository;
import com.book.pages.repositores.RoleRepository;



@RestController
public class AppControler {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	//@Autowired
	//private PasswordEncoder passwordEncoder ;


   
	@GetMapping("/users")
	public ResponseEntity<List<Employee>> getUsers(){
		return ResponseEntity.ok().body((List<Employee>) employeeRepository.findAll());
	}
	@PostMapping("/add/role")
	public void save(@RequestBody Role role) {
		roleRepository.save(role);
	}
	
	@PostMapping("/add/employee")
	public void save(@RequestBody Employee employee) {
		//employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employeeRepository.save(employee);
	}
}
