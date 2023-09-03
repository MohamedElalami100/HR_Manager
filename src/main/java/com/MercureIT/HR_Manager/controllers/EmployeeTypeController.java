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

import com.MercureIT.HR_Manager.models.EmployeeType;
import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.EmployeeTypeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;
import com.MercureIT.HR_Manager.services.LeaveService;

@Controller
public class EmployeeTypeController {
	
	@Autowired
	private EmployeeTypeService employeeTypeService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@GetMapping("/employeetypes")
	public String getEmployeeTypes(Model model, Principal principal) {
		String username = principal.getName();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());
		
		return "employee-type";
	}
	
	@PostMapping("/employeetypes/addnew")
	public String addNew(EmployeeType employeeType) {
		employeeTypeService.save(employeeType);
		return "redirect:/employeetypes";
	}
	
	@RequestMapping("/employeetypes/findbyid")
	@ResponseBody
	public Optional<EmployeeType> findById(@RequestParam Integer id) {
		return employeeTypeService.findById(id);
	}
	
	@RequestMapping(value="/employeetypes/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(EmployeeType employeeType) {
		employeeTypeService.save(employeeType);
		return "redirect:/employeetypes";
	}
	
	@RequestMapping(value="/employeetypes/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		employeeTypeService.delete(id);
		return "redirect:/employeetypes";
	}
}
