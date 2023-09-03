package com.MercureIT.HR_Manager.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.EmployeeTypeService;
import com.MercureIT.HR_Manager.services.JobApplicationService;
import com.MercureIT.HR_Manager.services.JobTitleService;
import com.MercureIT.HR_Manager.services.LeaveService;


@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private JobTitleService jobTitleService;
	@Autowired
	private EmployeeTypeService employeeTypeService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@GetMapping("/employees")
	public String getEmployees(Model model, Principal principal) {
		String username = principal.getName();
	    List<Leave> pendingLeaves = leaveService.getPendingLeaves();
	    List<JobApplication> interviews = jobApplicationService.getInterviews();
	    List<JobApplication> shortlisted = jobApplicationService.getShortlisted();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("employees", employeeService.getEmployees());
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());
		model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
	    model.addAttribute("pendingLeavesNumber", pendingLeaves.size());
	    model.addAttribute("interviews", interviews);
	    model.addAttribute("shortlistedNumber", shortlisted.size());

		return "employee";
	}
	
	@PostMapping("/employees/addnew")
	public String addNew(
	        @RequestParam("firstname") String firstName,
	        @RequestParam("lastname") String lastName,
	        @RequestParam("socialSecurityNumber") String socialSecurityNumber,
	        @RequestParam("gender") String gender,
	        @RequestParam("maritalStatus") String maritalStatus,
	        @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
	        @RequestParam("city") String city,
	        @RequestParam("address") String address,
	        @RequestParam("mobile") String mobile,
	        @RequestParam("phone") String phone,
	        @RequestParam("email") String email,
	        @RequestParam("photoFile") MultipartFile photoFile,
	        @RequestParam("hireDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate,
	        @RequestParam("jobtitleid") Integer jobTitleId,
	        @RequestParam("employeetypeid") Integer employeeTypeId,
	        @ModelAttribute Employee employee
	) {
	    // Set other form fields on the employee object
	    employee.setFirstname(firstName);
	    employee.setLastname(lastName);
	    employee.setSocialSecurityNumber(socialSecurityNumber);
	    employee.setGender(gender);
	    employee.setMaritalStatus(maritalStatus);
	    employee.setDateOfBirth(dateOfBirth);
	    employee.setCity(city);
	    employee.setPhone(phone);
	    employee.setAddress(address);
	    employee.setMobile(mobile);
	    employee.setEmail(email);
	    employee.setHireDate(hireDate);
	    // Set job title and employee type using the provided IDs
	    employee.setJobtitleid(jobTitleId);
	    employee.setEmployeetypeid(employeeTypeId);

	    if (!photoFile.isEmpty()) {
	        try {
	            // Process the photo file
	            String originalFilename = photoFile.getOriginalFilename();
	            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	            String newFilename = UUID.randomUUID().toString() + fileExtension;
	            
	            byte[] bytes = photoFile.getBytes();
                Path path = Paths.get("src/main/resources/static/assets/img/photos/" + newFilename);
                Files.write(path, bytes);

	            // Set photo file name in employee
	            employee.setPhoto(newFilename);
                System.out.print(path);
	            

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Save employee and redirect
	    employeeService.save(employee);
	    return "redirect:/employees";
	}

	@RequestMapping("/employees/findbyid")
	@ResponseBody
	public Optional<Employee> findById(@RequestParam Integer id) {
		return employeeService.findById(id);
	}
	
	@RequestMapping(value="/employees/update", method= {RequestMethod.POST, RequestMethod.GET})
	public String update(
	        @RequestParam("firstname") String firstName,
	        @RequestParam("lastname") String lastName,
	        @RequestParam("socialSecurityNumber") String socialSecurityNumber,
	        @RequestParam("gender") String gender,
	        @RequestParam("maritalStatus") String maritalStatus,
	        @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
	        @RequestParam("city") String city,
	        @RequestParam("address") String address,
	        @RequestParam("mobile") String mobile,
	        @RequestParam("phone") String phone,
	        @RequestParam("email") String email,
	        @RequestParam("photoFile") MultipartFile photoFile,
	        @RequestParam("photo") String photo,
	        @RequestParam("hireDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate,
	        @RequestParam("jobtitleid") Integer jobTitleId,
	        @RequestParam("employeetypeid") Integer employeeTypeId,
	        @ModelAttribute Employee employee
	) {
		// Set other form fields on the employee object
	    employee.setFirstname(firstName);
	    employee.setLastname(lastName);
	    employee.setSocialSecurityNumber(socialSecurityNumber);
	    employee.setGender(gender);
	    employee.setMaritalStatus(maritalStatus);
	    employee.setDateOfBirth(dateOfBirth);
	    employee.setCity(city);
	    employee.setPhone(phone);
	    employee.setAddress(address);
	    employee.setMobile(mobile);
	    employee.setEmail(email);
	    employee.setHireDate(hireDate);
	    // Set job title and employee type using the provided IDs
	    employee.setJobtitleid(jobTitleId);
	    employee.setEmployeetypeid(employeeTypeId);

	    if (!photoFile.isEmpty()) {
	        try {
	        	
	        	byte[] newContentBytes = photoFile.getBytes();
	            Path filePath = Paths.get("src/main/resources/static/assets/img/photos/" + photo);
	         
                // Write the updated content back to the file, overwriting the existing content
                Files.write(filePath, newContentBytes);
                
        	    employee.setPhoto(photo);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    // Save employee and redirect
	    employeeService.save(employee);
	    return "redirect:/employees";
	}
	
	@RequestMapping(value="/employees/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}
	
	@RequestMapping(value = "/employees/assignusername")
	public  String assignUsername(@RequestParam int id){
		employeeService.assignUsername(id);
		return "redirect:/employees";
	}
}
