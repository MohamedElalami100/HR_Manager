package com.MercureIT.HR_Manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.Vacancy;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

	List<Vacancy> findByHiringManager(Employee hiringManager);

}
