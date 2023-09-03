package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Leave;
import com.MercureIT.HR_Manager.repositories.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	public List<Leave> getLeaves(){
		return leaveRepository.findAll();
	}
	
	public List<Leave> getCurrentLeaves(){
		return leaveRepository.getCurrentLeaves();
	}
	
	public void save(Leave leave) {
		leaveRepository.save(leave);
	}
	
	public Optional<Leave> findById(Integer id) {
		return leaveRepository.findById(id);
	}

	public void delete(Integer id) {
		leaveRepository.deleteById(id);
	}

    public void setStatus(Integer id, String status) {
        Optional<Leave> leaveOptional = leaveRepository.findById(id);
        
        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            leave.setStatus(status);
            leaveRepository.save(leave);
        }        
    }

	public List<Leave> getPendingLeaves() {
		// TODO Auto-generated method stub
		return leaveRepository.findByStatus("0");
	}
	
}
