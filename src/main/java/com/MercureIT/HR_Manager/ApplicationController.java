package com.MercureIT.HR_Manager;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;
import com.MercureIT.HR_Manager.services.LeaveService;

@Controller
public class ApplicationController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@GetMapping("/index")
	public String goHome(Model model, Principal principal) {
		String username = principal.getName();
		// Load all necessary data before rendering the view
	    Employee employeeUser = employeeService.findByUsername(username);
	    List<Employee> employees = employeeService.getEmployees();
	    List<Employee> newHiresThisYear = employeeService.getEmployeesByHireCurrentYear();
	    List<Employee> newHiresThisMonth = employeeService.getEmployeesByHireCurrentMonth();
	    List<Leave> currentLeaves = leaveService.getCurrentLeaves();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
	    Map<String, Integer> employeesNbrByJobs = employeeService.getEmployeesNbrByJobs();
	    Map<String, Integer> employeesNbrByStatus = employeeService.getEmployeesNbrByStatus();

	    model.addAttribute("employeeUser", employeeUser);
	    model.addAttribute("employeesNumber", employees.size());
	    model.addAttribute("newHiresThisYear", newHiresThisYear.size());
	    model.addAttribute("newHiresThisMonth", newHiresThisMonth.size());
	    model.addAttribute("currentLeaves", currentLeaves);
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());
		model.addAttribute("employeesByJobs", employeesNbrByJobs);
		model.addAttribute("employeesByEmployeeType", employeesNbrByStatus);

		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
	    return "pages-login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "pages-login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied(Model model, Principal principal) {
		String username = principal.getName();
		// Load all necessary data before rendering the view
	    Employee employeeUser = employeeService.findByUsername(username);
	    
	    model.addAttribute("employeeUser", employeeUser);
		return "accessDenied";
	}
	
	@GetMapping("/faq")
	public String faq(Model model, Principal principal) {
		String username = principal.getName();
		// Load all necessary data before rendering the view
	    Employee employeeUser = employeeService.findByUsername(username);
	    
	    model.addAttribute("employeeUser", employeeUser);
		return "pages-faq";
	}
}
