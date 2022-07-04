package com.invoice.trcking.viewcontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupportUserViewController {

	
	 
	@GetMapping("/sUDashboard")
	public String getSUDashboardPage() {
		return "supportUserDashBoard_page";
	}
	
	@GetMapping("/sUInvoicesHistory")
	public String getSUInvoicesHistortPage() {
		return "sUInvoicesHistory_page";
	}
	
}
