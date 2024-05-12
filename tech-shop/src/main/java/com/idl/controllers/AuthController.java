package com.idl.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.idl.models.Cart;
import com.idl.repository.CartRepository;
import com.idl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.idl.models.User;
import com.idl.payload.request.LoginRequest;
import com.idl.payload.request.SignupRequest;
import com.idl.payload.response.JwtResponse;
import com.idl.payload.response.MessageResponse;
import com.idl.repository.UserRepository;
import com.idl.security.jwt.JwtUtils;
import com.idl.services.UserDetailsImpl;

import jakarta.validation.Valid;




@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api")
public class AuthController {


	@Autowired
	UserService userS;

	@Autowired
	CartRepository cartRepository;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getName(),
					userDetails.getUsername(),
					roles));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().body("Error: Incorrect email or password!");
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body("Error: Authentication failed!");
		}
	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser (@Valid @RequestBody SignupRequest signUpRequest)
	{



		if (userRepository.existsByEmail(signUpRequest.getEmail()))
		{
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse ("Erreur : email existe déjà ! "));
		}


		User user = new User(signUpRequest.getName(),signUpRequest.getLastName(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber(), signUpRequest.getAddress(),true,
				signUpRequest.getRole());


		User registeredUser = userRepository.save(user);

		// Create a cart for the user
		Cart cart = new Cart();
		cart.setUser(registeredUser);

		Cart savedCart = cartRepository.save(cart);

		return ResponseEntity.ok(registeredUser.getId()
				);
	}


	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email) {

		String response = userS.forgotPassword(email);

		if (!response.startsWith("Invalid")) {
			response = "http://localhost:9050/api/reset-password?token=" + response;
		}
		return response;
	}

	@PutMapping("/reset-password")
	public String resetPassword(@RequestParam String token,
								@RequestParam String password) {

		return userS.resetPassword(token, password);
	}


}