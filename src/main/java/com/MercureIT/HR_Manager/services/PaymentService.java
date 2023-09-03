package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Payment;
import com.MercureIT.HR_Manager.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<Payment> getPayments(){
		return paymentRepository.findAll();
	}
	
	public void save(Payment payment) {
		paymentRepository.save(payment);
	}
	
	public Optional<Payment> findById(Integer id) {
		return paymentRepository.findById(id);
	}

	public void delete(Integer id) {
		paymentRepository.deleteById(id);
	}
}
