package com.invoice.trcking.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppViewController {


	   @GetMapping("/home")
	    public String viewHomePage(){
	        return "home_page";
	    }
	   
		@GetMapping("/authenticate")
		public String getLoginPage() {
			return "login_page";
		}
		
		@GetMapping("/register")
		public String getRegisterPage() {
			return "register_page";
		}
		
}
