package com.invoice.trcking.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditorUserViewController {

	@GetMapping("/aUDashboard")
	public String getAUDashboardPage() {
		return "AuditorDashboard_page";
	}
}
