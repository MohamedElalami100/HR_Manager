package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Candidate;
import com.MercureIT.HR_Manager.repositories.CandidateRepository;

@Service
public class CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	public List<Candidate> getCandidates(){
		return candidateRepository.findAll();
	}
	
	public void save(Candidate candidate) {
		candidateRepository.save(candidate);
	}
	
	public Optional<Candidate> findById(Integer id) {
		return candidateRepository.findById(id);
	}

	public void delete(Integer id) {
		candidateRepository.deleteById(id);
	}
}
