package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.User;
import com.MercureIT.HR_Manager.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle the unique constraint violation (e.g., return an error message)
            throw new IllegalArgumentException("User is already in use.");
        }
        
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
