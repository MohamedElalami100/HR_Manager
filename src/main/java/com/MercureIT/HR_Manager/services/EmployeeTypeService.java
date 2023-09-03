package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.EmployeeType;
import com.MercureIT.HR_Manager.repositories.EmployeeRepository;
import com.MercureIT.HR_Manager.repositories.EmployeeTypeRepository;

@Service
public class EmployeeTypeService {
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<EmployeeType> getEmployeeTypes(){
		return employeeTypeRepository.findAll();
	}
	
	public void save(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);
	}
	
	public Optional<EmployeeType> findById(Integer id) {
		return employeeTypeRepository.findById(id);
	}

	public void delete(Integer id) {
		// Find the job title record
        EmployeeType employeeType = employeeTypeRepository.findById(id).orElse(null);

        if (employeeType != null) {
            List<Employee> employeesWithType = employeeRepository.findByEmployeeType(employeeType);
            for (Employee employee : employeesWithType) {
                employee.setJobtitleid(null);
            }
        }
		employeeTypeRepository.deleteById(id);
	}
}
