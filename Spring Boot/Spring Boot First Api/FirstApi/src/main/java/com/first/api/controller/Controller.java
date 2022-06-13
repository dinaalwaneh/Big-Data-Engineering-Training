package com.first.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//annotation to know the program that's this class is restFul api :
@RestController
public class Controller {

	//I want to convert this function to web service => i put @GetMapping and method or controller name ("/hello") :
	@GetMapping("/hello")
	public String tellHrllo() {
		return " Hello World !!! ";
	}
	//to tell wep service that it's take a parameter i put @RequestParam annotation :
	@GetMapping("/helloWithParameter")
	public String tellHrllo(@RequestParam String name,@RequestParam long id) {
		return " Hello World !!! " + name + " with id : " + id;
	}
	
	@GetMapping("/welcomeWithParameter")
	public String tellWelcome(@RequestParam String name,@RequestParam long id) {
		return " Welcome !!! " + name + " with id : " + id;
	}
}
