package com.MercureIT.HR_Manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MercureIT.HR_Manager.models.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
