package com.invoice.trcking.controller;

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

import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;
import com.invoice.trcking.model.JwtRequest;
import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.RoleType;
import com.invoice.trcking.repository.*;




@RestController
public class AppControler {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	//@Autowired
	//private PasswordEncoder passwordEncoder ;


   
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body((List<User>) userRepository.findAll());
	}
	@PostMapping("/add/role")
	public void save(@RequestBody Role role) {
		roleRepository.save(role);
	}
	
	@PostMapping("/add/employee")
	public void save(@RequestBody User employee) {
		//employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		userRepository.save(employee);
	}
}
