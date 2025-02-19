package com.wilsonpedro.novelbr.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.wilsonpedro.novelbr.dto.AuthenticationDTO;
import com.wilsonpedro.novelbr.dto.LoginResponseDTO;
import com.wilsonpedro.novelbr.dto.UserDTO;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.infra.security.TokenService;
import com.wilsonpedro.novelbr.services.interfaces.UserService;
import com.wilsonpedro.novelbr.web.apis.AuthenticationAPI;

@RestController
public class AuthenticationController implements AuthenticationAPI {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;

	@Override
	public ResponseEntity login(AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@Override
	public ResponseEntity register(UserDTO data) {
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
		UserDTO newUser = new UserDTO(data.getPseudonym(), data.getUserType(), data.getEmail(), encryptedPassword);
		
		userService.save(new User(newUser));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
