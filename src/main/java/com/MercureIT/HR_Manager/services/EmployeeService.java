package com.MercureIT.HR_Manager.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Employee;
import com.MercureIT.HR_Manager.models.EmployeeType;
import com.MercureIT.HR_Manager.models.JobTitle;
import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.models.Payment;
import com.MercureIT.HR_Manager.models.User;
import com.MercureIT.HR_Manager.models.Vacancy;
import com.MercureIT.HR_Manager.repositories.EmployeeRepository;
import com.MercureIT.HR_Manager.repositories.EmployeeTypeRepository;
import com.MercureIT.HR_Manager.repositories.JobTitleRepository;
import com.MercureIT.HR_Manager.repositories.LeaveRepository;
import com.MercureIT.HR_Manager.repositories.PaymentRepository;
import com.MercureIT.HR_Manager.repositories.UserRepository;
import com.MercureIT.HR_Manager.repositories.VacancyRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JobTitleRepository jobTiteRepository;
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private VacancyRepository vacancyRepository;
	
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	public List<Employee> getEmployeesByHireCurrentYear(){
		return employeeRepository.getByYearHireDate();
	}
	
	public List<Employee> getEmployeesByHireCurrentMonth(){
		return employeeRepository.getByMonthHireDate();
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public Optional<Employee> findById(Integer id) {
		return employeeRepository.findById(id);
	}

	public void delete(Integer id) {
		// Find the job title record
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee != null) {
            List<Leave> leavesWithEmployee = leaveRepository.findByEmployee(employee);
            for (Leave leave : leavesWithEmployee) {
                leave.setEmployeeid(null);
            }
            
            List<Payment> paymentsWithEmployee = paymentRepository.findByEmployee(employee);
            for (Payment payment : paymentsWithEmployee) {
                payment.setEmployeeid(null);
            }
            
            List<Vacancy> jobApplicationsWithEmployee = vacancyRepository.findByHiringManager(employee);
            for (Vacancy vacancy : jobApplicationsWithEmployee) {
            	vacancy.setHiringmanagerid(null);
            }
            
        }
		
		employeeRepository.deleteById(id);
	}

	public Employee findByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}
	
	public void assignUsername(int id){
		Employee employee = employeeRepository.findById(id).orElse(null);
		User user = userRepository.findByFirstnameAndLastname(
				employee.getFirstname(),
				employee.getLastname());
		if(user != null ) {
			employee.setUsername(user.getUsername());
		}
		employeeRepository.save(employee);
	}

	public Map<String, Integer> getEmployeesNbrByJobs(){
		Map<String, Integer> data = new HashMap<>();
		List<JobTitle> jobTitles = jobTiteRepository.findAll();
		
		for (JobTitle jobTitle : jobTitles) {
			if(employeeRepository.findByJobTitle(jobTitle).size() > 0) {
			    data.put(jobTitle.getName(), employeeRepository.findByJobTitle(jobTitle).size());
			}
		}
		return data;
	}
	
	public Map<String, Integer> getEmployeesNbrByStatus(){
		Map<String, Integer> data = new HashMap<>();
		List<EmployeeType> employeeTypes = employeeTypeRepository.findAll();
		
		for (EmployeeType employeeType : employeeTypes) {
			if(employeeRepository.findByEmployeeType(employeeType).size() > 0) {
			    data.put(employeeType.getName(), employeeRepository.findByEmployeeType(employeeType).size());
			}
		}
		return data;
	}
}
