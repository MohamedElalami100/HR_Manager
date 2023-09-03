package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.repositories.JobApplicationRepository;

@Service
public class JobApplicationService {
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	public List<JobApplication> getJobApplications(){
		return jobApplicationRepository.findAll();
	}
	
	public List<JobApplication> getInterviews(){
		return jobApplicationRepository.findByStatus("InterviewScheduled");
	}
	
	public void save(JobApplication jobApplication) {
		jobApplicationRepository.save(jobApplication);
	}
	
	public Optional<JobApplication> findById(Integer id) {
		return jobApplicationRepository.findById(id);
	}

	public void delete(Integer id) {
		jobApplicationRepository.deleteById(id);
	}

	public void setStatus(Integer id, String status) {
        Optional<JobApplication> jobApplicationOptional = jobApplicationRepository.findById(id);
        
        if (jobApplicationOptional.isPresent()) {
            JobApplication jobApplication = jobApplicationOptional.get();
            jobApplication.setStatus(status);
            jobApplicationRepository.save(jobApplication);
        }   
        
	}

	public List<JobApplication> getShortlisted() {
		return jobApplicationRepository.findByStatus("ShortListed");
	}
	
}
