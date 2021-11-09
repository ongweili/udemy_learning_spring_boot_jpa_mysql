package com.sha.springbootrest.service;

import java.util.List;

import com.sha.springbootrest.model.Role;
import com.sha.springbootrest.model.User;

public interface IUserService {
	
	public User saveUser(User user);
	
	public User changeRle(Role newRole, String username);
	
	public User findByUsername(String username);
	
	public User deleteUser(Long userId);
	
	 public List<User> findAllUsers();

}
