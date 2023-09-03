package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Vacancy;
import com.MercureIT.HR_Manager.repositories.JobApplicationRepository;
import com.MercureIT.HR_Manager.repositories.VacancyRepository;

@Service
public class VacancyService {
	
	@Autowired
	private VacancyRepository vacancyRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	public List<Vacancy> getVacancies(){
		return vacancyRepository.findAll();
	}
	
	public void save(Vacancy vacancy) {
		vacancyRepository.save(vacancy);
	}
	
	public Optional<Vacancy> findById(Integer id) {
		return vacancyRepository.findById(id);
	}

	public void delete(Integer id) {
		// Find the job title record
        Vacancy vacancy = vacancyRepository.findById(id).orElse(null);

        if (vacancy != null) {
            List<JobApplication> jobsWithVacancy = jobApplicationRepository.findByVacancy(vacancy);
            for (JobApplication jobApplication : jobsWithVacancy) {
            	jobApplication.setVacancyid(null);
            }
        }
				
		vacancyRepository.deleteById(id);
	}
}
