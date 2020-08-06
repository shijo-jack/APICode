package com.fresco.codelab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fresco.codelab.model.CodeLabRepo;
import com.fresco.codelab.service.DashboardService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController{
	
	private static Long userAutoGenId;
	
	@Autowired
	private DashboardService dashboardService;
	
	public static void setUserAutoGenId(Long userAutoGenId) {
		DashboardController.userAutoGenId = userAutoGenId;
	}
	
	@GetMapping
	public String viewHomePage() {
		return "homepage.jsp";
	}

	@PostMapping("/createnewrepo")
	public String createnewrepo(String repo_name) {
		if(userAutoGenId!=null) {
			dashboardService.save(new CodeLabRepo(repo_name, userAutoGenId, null, null));
		}
		return "repodashboardpage.jsp";
	}

}
