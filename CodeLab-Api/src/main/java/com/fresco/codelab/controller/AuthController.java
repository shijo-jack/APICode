package com.fresco.codelab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fresco.codelab.model.CodeLabUser;
import com.fresco.codelab.service.DashboardService;

@Controller
public class AuthController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("registrationpage.jsp");
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("loginpage.jsp");
	}
	
	@PostMapping("/login")
	public String login(String username, String password) {
		CodeLabUser codeLabUser = dashboardService.getCodeLabUser(username,password);
		if(codeLabUser!=null) {
			DashboardController.setUserAutoGenId(codeLabUser.getUserAutoGenId());
			return "homepage.jsp";
		}else {
			return "loginpage.jsp";
		}
	}
	 
	@PostMapping("/register")
	public String register(String fullname, String username, String password) {
		dashboardService.save(new CodeLabUser(fullname, username, password, null));
		return "loginpage.jsp";
	}

}