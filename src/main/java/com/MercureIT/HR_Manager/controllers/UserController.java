package com.MercureIT.HR_Manager.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.MercureIT.HR_Manager.models.User;
import com.MercureIT.HR_Manager.services.EmployeeService;
import com.MercureIT.HR_Manager.services.RoleService;
import com.MercureIT.HR_Manager.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/user")
	public String getUsers(Model model, Principal principal) {
		String username = principal.getName();
		
		model.addAttribute("employeeUser", employeeService.findByUsername(username));
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("roles", roleService.getRoles());

		return "users";
	}
	
	@RequestMapping("/users/list")
	@ResponseBody
	public List<User> getUsersList(Model model, Principal principal) {
		return userService.getUsers();
	}
	
	//Modified method to Add a new user User
	@PostMapping(value="users/addnew")
	public RedirectView addNew(User user, RedirectAttributes redir) {
		
		userService.save(user);	
		RedirectView  redirectView= new RedirectView("/login",true);
	        redir.addFlashAttribute("message",
	    		"You successfully registered! You can now login");
	    return redirectView;				
	}
	
	@RequestMapping("/user/findbyid")
	@ResponseBody
	public Optional<User> findById(@RequestParam Integer id) {
		return userService.findById(id);
	}
	
	@RequestMapping(value="/user/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(User user) {
		userService.save(user);
		return "redirect:/user";
	}
	
	@RequestMapping(value="/user/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		userService.delete(id);
		return "redirect:/user";
	}
	
	@RequestMapping("/user/assignrole")
	public String assignRole(@RequestParam Integer userId, @RequestParam Integer roleId){
	    roleService.assignUserRole(userId, roleId);
	    roleService.unassignUserRole(userId, (roleId+1)%2);
	    return "redirect:/user";
	}
	
	@RequestMapping(value="/user/update-password", method= {RequestMethod.POST, RequestMethod.GET})
	public String updatePassword(	        
			@RequestParam("username") String username,
	        @RequestParam("password") String password,
	        @RequestParam("newPassword") String newPassword
	) {
		User user = userService.findByUsername(username);
		if(user.getPassword() == password) user.setPassword(newPassword);
		
		userService.save(user);
		return "redirect:/profile";
	}
}
