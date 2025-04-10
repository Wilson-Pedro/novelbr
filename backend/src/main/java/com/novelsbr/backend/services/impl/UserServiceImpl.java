package com.novelsbr.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelsbr.backend.domain.dto.UserDTO;
import com.novelsbr.backend.domain.entities.User;
import com.novelsbr.backend.repositories.UserRepository;
import com.novelsbr.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(UserDTO userDTO) {
		return userRepository.save(new User(userDTO));
	}

}
