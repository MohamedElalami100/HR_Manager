package com.MercureIT.HR_Manager.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MercureIT.HR_Manager.models.JobTitle;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobTitleService;

@Controller
public class JobTitleController {
	
	@Autowired
	private JobTitleService jobTitleService;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/jobtitles")
	public String getJobTitles(Model model, Principal principal) {
		String username = principal.getName();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());

		return "job-title";
	}
	
	@PostMapping("/jobtitles/addnew")
	public String addNew(JobTitle jobTitle) {
		jobTitleService.save(jobTitle);
		return "redirect:/jobtitles";
	}
	
	@RequestMapping("/jobtitles/findbyid")
	@ResponseBody
	public Optional<JobTitle> findById(@RequestParam Integer id) {
		return jobTitleService.findById(id);
	}
	
	@RequestMapping(value="/jobtitles/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(JobTitle jobTitle) {
		jobTitleService.save(jobTitle);
		return "redirect:/jobtitles";
	}
	
	@RequestMapping(value="/jobtitles/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		jobTitleService.delete(id);
		return "redirect:/jobtitles";
	}
}
