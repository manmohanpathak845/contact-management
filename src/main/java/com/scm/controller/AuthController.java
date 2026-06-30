package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.dto.LoginRequest;
import com.scm.dto.RegisterRequest;
import com.scm.jwt.JwtResponse;
import com.scm.jwt.JwtService;
import com.scm.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/login")
	public JwtResponse login(@RequestBody LoginRequest request) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		UserDetails user = (UserDetails)authentication.getPrincipal();
		
		return new JwtResponse(jwtService.generateToken(user));
		
	}
	
	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest){
		userService.register(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");
		
	}

}

