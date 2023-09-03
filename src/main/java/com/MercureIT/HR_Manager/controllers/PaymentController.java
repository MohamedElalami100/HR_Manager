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
import com.MercureIT.HR_Manager.models.Payment;
import com.MercureIT.HR_Manager.services.PaymentService;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;
import com.MercureIT.HR_Manager.services.LeaveService;

@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@GetMapping("/payments")
	public String getPayments(Model model, Principal principal) {
		String username = principal.getName();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("payments", paymentService.getPayments());
		model.addAttribute("employees", employeeService.getEmployees());
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());

		return "payment";
	}
	
	@PostMapping("/payments/addnew")
	public String addNew(Payment payment) {
		paymentService.save(payment);
		return "redirect:/payments";
	}
	
	@RequestMapping("/payments/findbyid")
	@ResponseBody
	public Optional<Payment> findById(@RequestParam Integer id) {
		return paymentService.findById(id);
	}
	
	@RequestMapping(value="/payments/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Payment payment) {
		paymentService.save(payment);
		return "redirect:/payments";
	}
	
	@RequestMapping(value="/payments/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		paymentService.delete(id);
		return "redirect:/payments";
	}
}
