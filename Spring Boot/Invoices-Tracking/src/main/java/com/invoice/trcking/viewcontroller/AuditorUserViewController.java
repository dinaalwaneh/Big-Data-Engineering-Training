package com.invoice.trcking.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditorUserViewController {

	@GetMapping("/AUDashboard")
	public String getAUDashboardPage() {
		return "AuditorDashboard_page";
	}
	
	@GetMapping("/AUHistory")
	public String getAUHistoryPage() {
		return "AUHistory_page";
	}
	
}
