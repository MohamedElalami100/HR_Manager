package com.MercureIT.HR_Manager.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MercureIT.HR_Manager.models.Role;
import com.MercureIT.HR_Manager.models.User;
import com.MercureIT.HR_Manager.repositories.RoleRepository;
import com.MercureIT.HR_Manager.repositories.UserRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;	
	
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}

	public void delete(Integer id) {
		roleRepository.deleteById(id);
	}

	public void assignUserRole(Integer userId, Integer roleId){
	   User user  = userRepository.findById(userId).orElse(null);
	   Role role = roleRepository.findById(roleId).orElse(null);
	   Set<Role> userRoles = user.getRoles();
	   userRoles.add(role);
	   user.setRoles(userRoles);
	   userRepository.save(user);
	}

	public void unassignUserRole(Integer userId, Integer roleId){
	    User user  = userRepository.findById(userId).orElse(null);
	    user.getRoles().removeIf(x -> x.getId()==roleId);
	    userRepository.save(user);
	}
	
	public Set<Role> getUserRoles(User user){
	    return user.getRoles();
	}
	
	public List<Role> getUserNotRoles(User user){
		return roleRepository.getUserNotRoles(user.getId());
	}
}
