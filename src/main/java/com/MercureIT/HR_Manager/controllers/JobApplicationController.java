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
import com.MercureIT.HR_Manager.services.CandidateService;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;
import com.MercureIT.HR_Manager.services.LeaveService;
import com.MercureIT.HR_Manager.services.VacancyService;

@Controller
public class JobApplicationController {
	
	@Autowired
	private JobApplicationService jobApplicationService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private VacancyService vacancyService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;
	
	@GetMapping("/jobapplications")
	public String getJobApplications(Model model, Principal principal) {
		String username = principal.getName();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("jobApplications", jobApplicationService.getJobApplications());
		model.addAttribute("candidates", candidateService.getCandidates());
		model.addAttribute("vacancies", vacancyService.getVacancies());
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());

		return "job-application";
	}
	
	@PostMapping("/jobapplications/addnew")
	public String addNew(JobApplication jobApplication) {
		jobApplicationService.save(jobApplication);
		return "redirect:/jobapplications";
	}
	
	@RequestMapping("/jobapplications/findbyid")
	@ResponseBody
	public Optional<JobApplication> findById(@RequestParam Integer id) {
		return jobApplicationService.findById(id);
	}
	
	@RequestMapping(value="/jobapplications/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(JobApplication jobApplication) {
		jobApplicationService.save(jobApplication);
		return "redirect:/jobapplications";
	}
	
	@RequestMapping(value="/jobapplications/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		jobApplicationService.delete(id);
		return "redirect:/jobapplications";
	}
	
	@RequestMapping("/jobapplications/setstatus")
	public String setStatus(@RequestParam Integer id, @RequestParam String status) {
		jobApplicationService.setStatus(id, status);
		return "redirect:/jobapplications";		
	}
}
