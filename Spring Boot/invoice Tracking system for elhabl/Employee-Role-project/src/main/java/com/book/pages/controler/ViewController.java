package com.book.pages.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	
	   @GetMapping("/home")
	    public String viewHomePage(){
	        return "index";
	    }
	   
	  
}
