package com.MercureIT.HR_Manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

	@Query(
	        value = "SELECT * FROM employee_leave WHERE status = '1' AND CURDATE() BETWEEN start_date AND end_date;", 
	        nativeQuery = true
	)
	List<Leave> getCurrentLeaves();

	List<Leave> findByEmployee(Employee employee);

	List<Leave> findByStatus(String string);
}
