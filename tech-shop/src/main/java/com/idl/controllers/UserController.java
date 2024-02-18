package com.idl.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idl.models.User;
import com.idl.services.UserService;

import jakarta.validation.Valid;


@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	
	@Autowired
	UserService userS;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> pro = userS.findAllUsers();

        return pro;
	    
	}

	@PostMapping("/adduser")
	public User createUser(@Valid @RequestBody User user) {
	    return userS.saveUser(user);
	}
	
}
