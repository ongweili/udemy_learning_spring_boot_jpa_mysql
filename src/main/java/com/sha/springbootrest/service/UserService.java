package com.sha.springbootrest.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sha.springbootrest.model.Role;
import com.sha.springbootrest.model.User;
import com.sha.springbootrest.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreateDate(LocalDateTime.now());
		user.setRole(Role.ROLE_USER);
		return userRepo.save(user);
	}
	
	public User changeRle(Role newRole, String username) {
		User user = userRepo.findByUsername(username)
.orElseThrow(()->new RuntimeException());
		user.setRole(newRole);
		return userRepo.save(user);
		}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username).orElse(null);
	}
	
	public User deleteUser(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new RuntimeException());
		userRepo.delete(user);
		return user;
		}
	
	 public List<User> findAllUsers(){
		 return userRepo.findAll();
	 }

}
