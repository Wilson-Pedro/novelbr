package com.novelsbr.backend.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.LoginRequest;

@RequestMapping("/auth")
public interface AuthAPI {

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest);
}
