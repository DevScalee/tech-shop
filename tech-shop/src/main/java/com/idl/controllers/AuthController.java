package com.idl.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  @PostMapping("/signin")
		public ResponseEntity<?> authenticateUser (@Valid @RequestBody LoginRequest loginRequest) {
			
			Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail() , loginRequest.getPassword()));
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
					 roles) );
			
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
			
			
			 User user = new User(signUpRequest.getFirstname(),signUpRequest.getLastname(),
		               signUpRequest.getEmail(),
		               encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhonenumber(), signUpRequest.getAddress(),true,
		              signUpRequest.getRole());

				
			  
			
			userRepository.save(user);

			return ResponseEntity.ok(new MessageResponse("Réussite de l'inscription de l'utilisateur.!"));
		}


}
