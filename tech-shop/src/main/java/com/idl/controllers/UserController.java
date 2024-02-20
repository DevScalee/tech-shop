package com.idl.controllers;

import java.util.List;


import com.idl.models.Userr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public List<Userr> getAllUsers() {
		List<Userr> pro = userS.findAllUsers();

        return pro;
	    
	}

	@PostMapping("/adduser")
	public Userr createUser(@Valid @RequestBody Userr user) {
	    return userS.saveUser(user);
	}

	@PutMapping("/updateuser")
	public ResponseEntity<Userr> updateUser(@RequestBody Userr user ) {
		Userr updateUser = userS.updateUser(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userS.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}





	
}
