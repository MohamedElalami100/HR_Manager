package com.MercureIT.HR_Manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.EmployeeType;
import com.MercureIT.HR_Manager.models.JobTitle;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByUsername(String username);
	
	List<Employee> findByJobTitle(JobTitle jobTitle);

	List<Employee> findByEmployeeType(EmployeeType employeeType);

	@Query(
	        value = "SELECT * FROM employee WHERE YEAR(hire_date) = YEAR(CURRENT_DATE());", 
	        nativeQuery = true
	)
	List<Employee> getByYearHireDate();
	
	@Query(
	        value = "SELECT * FROM employee WHERE MONTH(hire_date) = MONTH(CURRENT_DATE()) AND YEAR(hire_date) = YEAR(CURRENT_DATE());", 
	        nativeQuery = true
	)
	List<Employee> getByMonthHireDate();

}
