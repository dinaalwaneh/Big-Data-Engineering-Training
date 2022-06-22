package com.book.pages.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.pages.repositores.EmployeeRepository;



import com.book.pages.model.*;

//JWTUserDetailsService implements the Spring Security UserDetailsService interface.
//It overrides the loadUserByUsername for fetching user details from the database using the username.
//The Spring Security Authentication Manager calls this method for getting the user details from the database when authenticating the user details provided by the user.
//Here we are getting the user details from a hardcoded User List.
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository ;
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = employeeRepository.findByUsername(username);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		System.out.println("loadUserByUsername HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH   ");
		System.out.println(user.getRole());
	    authorities.add(new SimpleGrantedAuthority(user.getRole().getName().name()));
		System.out.println("loadUserByUsername HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH   "+ user.getRole().getName().name() );
		if (user.getUsername().equals(username)) {
			return new User(user.getUsername(), user.getPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}				
	}
}