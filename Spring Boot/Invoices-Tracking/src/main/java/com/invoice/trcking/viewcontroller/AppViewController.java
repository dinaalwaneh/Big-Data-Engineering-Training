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
		
		@GetMapping("/profile")
		public String getpersonalPage() {
			return "personal_page";
		}
		
		@GetMapping("/dashboardd")
		public String getdashboardPagee() {
			return "dashboard";
		}
		
		@GetMapping("/dashboard")
		public String getdashboardPage() {
			return "dashboard_page";
		}
		
		@GetMapping("/customer")
		public String getCustomerPage() {
			return "customer_page";
		}
		
		@GetMapping("/product")
		public String getProductPage() {
			return "product_page";
		}
		
		@GetMapping("/employee")
		public String getEmployeePage() {
			return "employee_page";
		}
		
		@GetMapping("/invoiceHistory")
		public String getInvoiceHistoryPage() {
			return "invoiceHistory_page";
		}
}
