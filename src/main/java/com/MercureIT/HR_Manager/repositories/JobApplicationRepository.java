package com.MercureIT.HR_Manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MercureIT.HR_Manager.models.JobApplication;
import com.MercureIT.HR_Manager.models.Vacancy;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

	List<JobApplication> findByVacancy(Vacancy vacancy);

	List<JobApplication> findByStatus(String string);
}
