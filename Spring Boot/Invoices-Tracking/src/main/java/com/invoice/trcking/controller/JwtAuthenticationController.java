package com.invoice.trcking.controller;


import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.invoice.trcking.service.JwtUserDetailsService;

 
import com.invoice.trcking.configration.JwtTokenUtil;
import com.invoice.trcking.model.JwtRequest;
import com.invoice.trcking.model.JwtResponse;
//Expose a POST API /authenticate using the JwtAuthenticationController.
//The POST API gets username and password in the body- Using Spring Authentication Manager we authenticate the username and password.
//If the credentials are valid, a JWT token is created using the JWTTokenUtil and provided to the client.
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws UsernameNotFoundException , Exception {
		
		LOGGER.debug("authenticate service with username : ",authenticationRequest.getUsername());
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);
		
			LOGGER.info("User authenticated successfully : ");
			return new ResponseEntity<Object>(new JwtResponse(token), HttpStatus.CREATED);
		}catch(UsernameNotFoundException e) {
			LOGGER.error("User not found : ");
			System.out.println("UsernameNotFoundException : "+e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Exception : "+e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

		 
	}

	//if the user and password exist in data base or not :
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			LOGGER.error("User Disabled : ");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			LOGGER.error("Invalid credentials : ");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}