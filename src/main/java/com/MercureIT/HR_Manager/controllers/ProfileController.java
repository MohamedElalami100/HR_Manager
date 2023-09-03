package com.MercureIT.HR_Manager.controllers;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;
import com.MercureIT.HR_Manager.services.LeaveService;

@Controller
public class ProfileController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@RequestMapping(value="/profile")
	public String profile(Model model, Principal principal) {
		String username = principal.getName();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());
		
		return "profile";
	}
	
}
