package com.novelsbr.backend.services;

import com.novelsbr.backend.domain.dto.UserDTO;
import com.novelsbr.backend.domain.entities.User;

public interface UserService {

	User save(UserDTO userDTO);
}
