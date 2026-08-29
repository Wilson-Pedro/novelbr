package com.novelsbr.backend.web.controllers;

import com.novelsbr.backend.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.web.api.AuthAPI;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}")
public class AuthController implements AuthAPI {
	
	@Autowired
	AuthService authService;

	@Operation(
			summary = "Autenticar usuário"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Autenticado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao autenticar"),
			@ApiResponse(responseCode = "500", description = "Error ao autenticar"),
	})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.login(loginRequest));
	}
}
