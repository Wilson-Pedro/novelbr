package com.novelsbr.backend.services.impl;

import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.domain.dto.LoginResponseDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.infra.security.TokenService;
import com.novelsbr.backend.services.AuthService;
import com.novelsbr.backend.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthorService authorService;

    @Override
    public LoginResponseDTO login(LoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Author) auth.getPrincipal());
        Long userId = authorService.findByUsername(loginRequest.getLogin()).getId();

        return new LoginResponseDTO(token, loginRequest.getLogin(), userId);
    }
}
