package com.invoice.trcking.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupperUserViewController {

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
