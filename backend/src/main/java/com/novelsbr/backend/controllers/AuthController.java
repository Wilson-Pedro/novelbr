package com.novelsbr.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.domain.dto.LoginResponseDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Author) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
}
