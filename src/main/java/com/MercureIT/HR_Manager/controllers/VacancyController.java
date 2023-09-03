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

import com.MercureIT.HR_Manager.models.Vacancy;
import com.MercureIT.HR_Manager.services.VacancyService;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.JobTitleService;

@Controller
public class VacancyController {
	
	@Autowired
	private VacancyService vacancyService;
	@Autowired
	private JobTitleService jobTitleService;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/vacancies")
	public String getVacancys(Model model, Principal principal) {
		String username = principal.getName();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("vacancies", vacancyService.getVacancies());
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());
		model.addAttribute("employees", employeeService.getEmployees());

		return "vacancy";
	}
	
	@PostMapping("/vacancies/addnew")
	public String addNew(Vacancy vacancy) {
		vacancyService.save(vacancy);
		return "redirect:/vacancies";
	}
	
	@RequestMapping("/vacancies/findbyid")
	@ResponseBody
	public Optional<Vacancy> findById(@RequestParam Integer id) {
		return vacancyService.findById(id);
	}
	
	@RequestMapping(value="/vacancies/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Vacancy vacancy) {
		vacancyService.save(vacancy);
		return "redirect:/vacancies";
	}
	
	@RequestMapping(value="/vacancies/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vacancyService.delete(id);
		return "redirect:/vacancies";
	}
}