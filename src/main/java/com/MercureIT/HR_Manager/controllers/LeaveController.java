package com.MercureIT.HR_Manager.controllers;

import java.security.Principal;
import java.util.List;
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

import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.services.LeaveService;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;

@Controller
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JobApplicationService jobApplicationService;
	
	
	@GetMapping("/leaves")
	public String getLeaves(Model model, Principal principal) {
		String username = principal.getName();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("leaves", leaveService.getLeaves());
		model.addAttribute("employees", employeeService.getEmployees());
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());

		return "leave";
	}
	
	@PostMapping("/leaves/addnew")
	public String addNew(Leave leave) {
		System.out.print(leave.getEmployeeid());
		leaveService.save(leave);
		return "redirect:/leaves";
	}
	
	@RequestMapping("/leaves/findbyid")
	@ResponseBody
	public Optional<Leave> findById(@RequestParam Integer id) {
		return leaveService.findById(id);
	}
	
	@RequestMapping(value="/leaves/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Leave leave) {
		leaveService.save(leave);
		return "redirect:/leaves";
	}
	
	@RequestMapping(value="/leaves/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		leaveService.delete(id);
		return "redirect:/leaves";
	}
	
	@RequestMapping("/leaves/setstatus")
	public String setStatus(@RequestParam Integer id, @RequestParam String status) {
		leaveService.setStatus(id, status);
		return "redirect:/leaves";		
	}
}
