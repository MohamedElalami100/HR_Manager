package com.MercureIT.HR_Manager.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.MercureIT.HR_Manager.models.Candidate;
import com.MercureIT.HR_Manager.services.CandidateService;

@Controller
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@PostMapping("/candidates/addnew")
	public String addNew(
	        @RequestParam("firstname") String firstName,
	        @RequestParam("lastname") String lastName,
	        @RequestParam("phone") String phone,
	        @RequestParam("email") String email,
	        @RequestParam("resumeFile") MultipartFile resumeFile,
	        @ModelAttribute Candidate candidate
	) {
	    // Set other form fields on the candidate object
	    candidate.setFirstname(firstName);
	    candidate.setLastname(lastName);
	    candidate.setPhone(phone);
	    candidate.setEmail(email);
		
	    if (!resumeFile.isEmpty()) {
	        try {
	            // Process the resume file
	            String originalFilename = resumeFile.getOriginalFilename();
	            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	            String newFilename = UUID.randomUUID().toString() + fileExtension;
	            
	            byte[] bytes = resumeFile.getBytes();
                Path path = Paths.get("src/main/resources/static/assets/pdf/" + newFilename);
                Files.write(path, bytes);
                System.out.println(path);

	            // Set resume file name in candidate
	            candidate.setResume(newFilename);	            

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		candidateService.save(candidate);
		return "redirect:/jobapplications";
	}
	
	@RequestMapping("/candidates/findbyid")
	@ResponseBody
	public Optional<Candidate> findById(@RequestParam Integer id) {
		return candidateService.findById(id);
	}
	
	@RequestMapping(value="/candidates/update", method= {RequestMethod.POST, RequestMethod.GET})
	public String update(
	        @RequestParam("firstname") String firstName,
	        @RequestParam("lastname") String lastName,
	        @RequestParam("phone") String phone,
	        @RequestParam("email") String email,
	        @RequestParam("resumeFile") MultipartFile resumeFile,
	        @RequestParam("resume") String resume,
	        @ModelAttribute Candidate candidate
	) {
	    // Set other form fields on the candidate object
	    candidate.setFirstname(firstName);
	    candidate.setLastname(lastName);
	    candidate.setPhone(phone);
	    candidate.setEmail(email);
		
	    if (!resumeFile.isEmpty()) {
	        try {
	        	
	        	byte[] newContentBytes = resumeFile.getBytes();
	            Path filePath = Paths.get("src/main/resources/static/assets/pdf/" + resume);
	         
                // Write the updated content back to the file, overwriting the existing content
                Files.write(filePath, newContentBytes);

	            // Set resume file name in candidate
	            candidate.setResume(resume);	            

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		candidateService.save(candidate);
		return "redirect:/jobapplications";
	}
	
	@RequestMapping(value="/candidates/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		candidateService.delete(id);
		return "redirect:/jobapplications";
	}
}
