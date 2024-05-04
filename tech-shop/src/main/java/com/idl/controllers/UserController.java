package com.idl.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idl.models.Product;
import com.idl.models.User;
import com.idl.services.UserService;

import jakarta.validation.Valid;


@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService userS;
	
	  @Autowired
	  PasswordEncoder encoder;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userS.findAllUsers();

	}
	
	
	@GetMapping("/user/{id}")
	public Optional<User> findUser (@PathVariable Long id )

	{
		return userS.getUserById(id);
	}
	@PostMapping("/adduser")
	public User createUser(@Valid @RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
	    return userS.saveUser(user);
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user ) {
	    
	    	 try {
	             User editUser = userS.updateUser(id,user);
	             return ResponseEntity.ok(editUser);
	         } catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	         }
	    	
	    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userS.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@GetMapping("/user/search")
	public ResponseEntity<?> searchUsers(@RequestParam String searchTerm) {
	    try {
	        List<User> users = userS.searchUser(searchTerm);
	        return ResponseEntity.ok(users);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No matching result!");
	    }
	}
	

	
}
