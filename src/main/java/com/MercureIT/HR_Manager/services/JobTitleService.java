package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.JobTitle;
import com.MercureIT.HR_Manager.repositories.EmployeeRepository;
import com.MercureIT.HR_Manager.repositories.JobTitleRepository;

@Service
public class JobTitleService {
	
	@Autowired
	private JobTitleRepository jobTitleRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<JobTitle> getJobTitles(){
		return jobTitleRepository.findAll();
	}
	
	public void save(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);
	}
	
	public Optional<JobTitle> findById(Integer id) {
		return jobTitleRepository.findById(id);
	}

	public void delete(Integer id) {
		// Find the job title record
        JobTitle jobTitle = jobTitleRepository.findById(id).orElse(null);

        if (jobTitle != null) {
            List<Employee> employeesWithJobTitle = employeeRepository.findByJobTitle(jobTitle);
            for (Employee employee : employeesWithJobTitle) {
                employee.setJobtitleid(null);
            }
        }
		jobTitleRepository.deleteById(id);
	}
	
}
