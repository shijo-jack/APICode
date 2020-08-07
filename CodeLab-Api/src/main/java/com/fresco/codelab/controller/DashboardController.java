package com.fresco.codelab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView viewHomePage() {
		ModelAndView modelAndView = new ModelAndView("homepage.jsp");
		modelAndView.addObject("myrepos", dashboardService.getCodeLabRepoWithOwnerId(userAutoGenId));
		modelAndView.addObject("allrepos", dashboardService.getAllCodeLabRepo());
		return modelAndView;
	}

	@PostMapping("/createnewrepo")
	public ModelAndView createnewrepo(String repo_name) {
		if(userAutoGenId!=null) {
			dashboardService.save(new CodeLabRepo(repo_name, userAutoGenId, null, null));
		}
		return new ModelAndView("redirect:/dashboard/");
	}
	
	
	@GetMapping(value = "/openrepo/{repoId}")
	public ModelAndView openrepo(@PathVariable String repoId) {
		ModelAndView modelAndView = new ModelAndView("repodashboardpage.jsp");
		modelAndView.addObject("repo", dashboardService.getCodeLabUserWithUserId(userAutoGenId).get(0));
		modelAndView.addObject("repoOwner", dashboardService.getCodeLabRepoWithRepoId(userAutoGenId).get(0));
		return modelAndView;
		    
	}

}
