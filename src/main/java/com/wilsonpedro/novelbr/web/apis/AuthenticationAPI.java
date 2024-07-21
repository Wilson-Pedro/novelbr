package com.wilsonpedro.novelbr.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wilsonpedro.novelbr.dto.AuthenticationDTO;
import com.wilsonpedro.novelbr.dto.UserDTO;

import jakarta.validation.Valid;

@RequestMapping("/auth")
public interface AuthenticationAPI {

	@PostMapping(produces = "application/json", path = "/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO data);
	
	@PostMapping(produces = "application/json", path="/register")
	public ResponseEntity register(@RequestBody @Valid UserDTO data);
}
