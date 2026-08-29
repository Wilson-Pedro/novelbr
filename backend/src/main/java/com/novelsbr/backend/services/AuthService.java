package com.novelsbr.backend.services;

import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.domain.dto.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequest loginRequest);
}
