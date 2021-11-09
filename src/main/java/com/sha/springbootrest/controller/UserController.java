package com.sha.springbootrest.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sha.springbootrest.dto.UserDto;
import com.sha.springbootrest.model.Role;
import com.sha.springbootrest.model.User;
import com.sha.springbootrest.service.IUserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid UserDto user){
		if(userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		userService.saveUser(user.convertToUser());
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@GetMapping("login")
	public ResponseEntity<?> login(HttpServletRequest request){
		Principal principal = request.getUserPrincipal();
		if(principal==null || principal.getName()==null) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		User user = userService.findByUsername(principal.getName());
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("{username}/change/{role}")
	public ResponseEntity<?> changeRole(@PathVariable String username, @PathVariable Role role){
		User user = userService.changeRle(role, username);
		return ResponseEntity.ok(user);
	}
}
